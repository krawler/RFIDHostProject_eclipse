package com.dotel.rfid;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dotel.controller.RFIDController;
import com.dotel.dao.DataBaseHelper;
import com.dotel.dao.ParametroDao;
import com.dotel.dao.RFIDProdutoDao;
import com.dotel.model.Parametro;
import com.dotel.model.RFIDProduto;
import com.dotel.util.Funcoes;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class RFIDProdutosActivity extends Activity {

//	public static String NOMETABELA;
	
	// --- Interface Mode
	public static final int MODE_NOT_DETECTED = 0;
	public static final int MODE_BT_INTERFACE = 1;
	public static final int MODE_USB_INTERFACE = 2;
	
	private TextView editEtext1;
	private TextView tvImportando;
	private Button btn_inventario;
	private String url;
	private String urlRequestProdutos;
	private String urlRequestQtdeProdutos;
	private String urlRequestStatusListagemProduto;
	private String aplicacao = "/WSRFIDHSTelecom/rfid/";
	private String statusProdutos;
	private HttpResponse resposta;
	private HttpResponse respostaQtdeProdutos;
	private HttpResponse respostaStatusListagemProdutos;
	private String strRetorno;
	private Integer qtdeRequisicaoStatusImportacao = 0;
	private Integer qtdeProdutosParaImportar = 0;
	private HttpClient httpClient = new DefaultHttpClient();
	private HttpGet httpGet;
	private Handler handler;
	private BaseAdapter adapterProdutos;
	
	private Parametro parametro;
	private ParametroDao dao;
	private RFIDProduto rfidProduto;
	private RFIDProdutoDao rfidProdutoDao;
	private DataBaseHelper dh;
	private RFIDController rfidController;

	private ListView lvRFIDProdutos;
	private ProgressBar progressBarProdutos;
	private ProgressDialog progressDialogProdutos;
	
	private List<Map<String, Object>> rfidprodutos = new ArrayList<Map<String, Object>>();
	private long startTime = 0L;
	private long timeInMiliSeconds = 0L;
	private Handler timerHandler = new Handler();

	private Runnable exibeUiErroException(final Exception exception, final String msg){
	
		return new Runnable() {						
					@Override
					public void run() {					
							
							new AlertDialog.Builder(RFIDProdutosActivity.this)
							.setTitle("Conexão")
							.setMessage(msg)
							.setNeutralButton("OK", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									finish();
									
								}
							}).create().show();	
					 }
					
				};
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rfidprodutos);		
		
		progressBarProdutos = (ProgressBar) findViewById(R.id.progressBarProdutos);
		btn_inventario      = (Button) findViewById(R.id.btn_inventario);
		lvRFIDProdutos      = (ListView) findViewById(R.id.lvRFIDProdutos);
		tvImportando	    = (TextView) findViewById(R.id.tvImportando);
		dh = new DataBaseHelper(RFIDProdutosActivity.this);
		try {
			rfidController = new RFIDController(RFIDProdutosActivity.this);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		try {
			dao = new ParametroDao(dh.getConnectionSource());
			rfidProdutoDao = new RFIDProdutoDao(dh.getConnectionSource());
			parametro = dao.queryForAll().size() > 0 ? dao.queryForAll().get(0) : null;			
			if(parametro != null){
				parametro.setJsonFromBD(null);
				dao.update(parametro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//String metodo = localizaTabelaProdutos(parametro);
		
		if(parametro != null){		
			url = parametro.protocoloWebService + parametro.getUrlWebService().trim() + ":" + parametro.getPortWebService().trim();
			url += aplicacao;			
		}		
		else{
		   
			new AlertDialog.Builder(this)
			.setTitle("Parametro")
			.setMessage("Cadastro dos Parametro não encontrado")
			.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
					
				}
			}).create().show();
			
		}
		
		//chamada da lista de produtos			
			
		new Thread(){
			
			@Override
			public void run() {              
				
				try{
					
					resposta = null;
					urlRequestProdutos = url + Parametro.actionRequestProdutos;
					urlRequestProdutos += "/" + parametro.getCodEmpresa().trim();	
					
					//verifica a url é valida
					URL url = new URL(urlRequestProdutos);
					URLConnection connection = url.openConnection();
					new URI(urlRequestProdutos);
					
					httpGet = new HttpGet(urlRequestProdutos);	
					
					try{
					
						resposta = httpClient.execute(httpGet);		
							
						parametro.setJsonFromBD(EntityUtils.toString(resposta.getEntity()));
						dao.update(parametro);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								if(parametro.getJsonFromBD() != null && !parametro.getJsonFromBD().equals("null")){
									try {
										JSONObject jaProdutos = new JSONObject(parametro.getJsonFromBD());																	
										JSONObject jsonProduto = null;
										JSONArray jaArray = new JSONArray();
										
										jsonProduto = jaProdutos.optJSONObject("rfidProduto");
									    
										if(jsonProduto == null){
										   jaArray = jaProdutos.getJSONArray("rfidProduto");									    										
										}else{
											rfidprodutos.add(rfidController.importaProduto(jsonProduto, parametro));	 
										}
										
										for (int i = 0; i < jaArray.length(); i++) {
											 jsonProduto = jaArray.getJSONObject(i);
											
											String tagId = null;
											if(!parametro.isVersaoTeste()){
											   tagId = jsonProduto.get("tag").toString().trim().substring(0, 28);	
											}else{
											   tagId = jsonProduto.get("tag").toString().trim();	
											}
											
											
											if(Funcoes.isNumber(tagId)){
												rfidProduto = new RFIDProduto();
												rfidProduto.setCodigo(jsonProduto.get("codigo").toString());
												rfidProduto.setProduto(jsonProduto.get("produto").toString());
												rfidProduto.setTag(tagId);
												rfidProduto.setImei((String) jsonProduto.get("imei"));
												
												Map<String,Object> map = new HashMap<String, Object>();
												map.put("codigo", jsonProduto.get("codigo").toString());
												map.put("produto", jsonProduto.get("produto").toString());
												map.put("tag",tagId);
												map.put("imei", jsonProduto.get("imei").toString());
												
												rfidprodutos.add(map);											
											}														
										}
										
										adapterProdutos = new SimpleAdapter(RFIDProdutosActivity.this ,rfidprodutos,
												R.layout.item_rfidprodutos, 
												new String[]{ "codigo", "produto", "tag" }, 
												new int[]{ R.id.tvCodigoProduto, R.id.tvProduto, R.id.tvTag });
										
										lvRFIDProdutos.setAdapter(adapterProdutos);
										
										try {
											Thread.sleep(1000L);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}									
										
										progressBarProdutos.setVisibility(View.GONE);
									    tvImportando.setVisibility(View.GONE);
										btn_inventario.setVisibility(View.VISIBLE);
										
										Toast.makeText(RFIDProdutosActivity.this, String.valueOf(jaArray.length()) + " produtos importados", Toast.LENGTH_SHORT).show();
										
									} catch (JSONException e) {								
										e.printStackTrace();
										runOnUiThread(new Runnable() {						
											@Override
											public void run() {
												
												new AlertDialog.Builder(RFIDProdutosActivity.this)
												.setTitle("Processando dados")
												.setMessage("Ocorreu um erro ao receber os dados do servidor")
												.setNeutralButton("OK", new DialogInterface.OnClickListener() {
													
													@Override
													public void onClick(DialogInterface dialog, int which) {
														finish();
														
													}
												}).create().show();	
											}
										});
									} 
								}else
								 if(parametro.getJsonFromBD().equals("null")){
									runOnUiThread(exibeUiErroException(new Exception(), "Nenhum registro foi retornado pelo servidor"));  
								 }
							}
						});
													
						
					}catch(Exception ex){						
						final Exception exConnectionHost = ex;					
						runOnUiThread(exibeUiErroException(ex, "Não foi possível conectar ao servidor, o servidor não respondeu"));
						
					}
				}catch(MalformedURLException e){					     
						final MalformedURLException ex = e;
					    runOnUiThread(exibeUiErroException(ex, "Erro na sintaxe da URL ou na conexão com o servidor. msg: " + ex.getMessage()));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					final URISyntaxException ex = e1;
				    runOnUiThread(exibeUiErroException(ex, "Erro na sintaxe da URL ou na conexão com o servidor. msg: " + ex.getMessage()));
				}
			}
			
		}.start();
		
		qtdeRequisicaoStatusImportacao = 30;
		
		startTime = SystemClock.uptimeMillis();
		//timerHandler.postDelayed( startShowTagEvent, 9000);		
	}
	
	
	private Runnable startShowTagEvent = new Runnable(){

		@Override
		public void run() {
			
			timeInMiliSeconds = SystemClock.uptimeMillis() - startTime;
			
			if(qtdeRequisicaoStatusImportacao > 0){
				qtdeRequisicaoStatusImportacao--;	
			}else{
				timerHandler.removeCallbacks(this);
			}
			timerHandler.postDelayed(this, 9000);
		}
		
	};

		
	public void chamaInventario(View view){
		R900Status.setInterfaceMode(MODE_BT_INTERFACE);
		
		try {
			new RFIDProdutoDao(dh.getConnectionSource()).executeRawNoArgs("delete from rfidproduto");
		} catch (SQLException e) {
			Log.d("RFIDProdutos", e.getMessage());
			e.printStackTrace();
		}
		
		Intent intent = new Intent(RFIDProdutosActivity.this, RFIDHostActivity.class);
		RFIDHostActivity.LIST_RFIDPRODUTOS = rfidprodutos;
		startActivity(intent);
		finish();
	}
	
	@Override
	public void onBackPressed(){
		finish();
	}
}

