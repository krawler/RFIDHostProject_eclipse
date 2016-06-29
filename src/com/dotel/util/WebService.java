package com.dotel.util;

/*
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Request.Priority;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dotel.dao.DataBaseHelper;
import com.dotel.dao.ParametroDao;
import com.dotel.model.Parametro;
import com.dotel.model.RFIDProduto;

public class WebService {
	
	
	private RequestQueue rq;
	private Map<String, String> params;	
	private Parametro parametro;
	private Handler handler;
	private RFIDProduto rfidproduto;
	
	private String ipServidor = "192.168.1.102";
	private String porta = "8081";
	private String url = "http://"+ ipServidor +":" + porta + "/WSRFIDHSTelecom/rfid/";	
	
	public WebService(Context context) {
		
		rq = Volley.newRequestQueue(context);
		
		//parametro = getParametroCadastrado(context);
	}
	
	private Parametro getParametroCadastrado(Context context){
		
		try {
			parametro = new ParametroDao(new DataBaseHelper(context).getConnectionSource()).queryForAll().get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return parametro;		
	}

	public void callByJsonObjectRequest(final Context context, String method, Map<String,String> paramValue){
		
		url = url + method;
		
		CustomJsonObjectRequest request = new CustomJsonObjectRequest(Method.POST, url, paramValue, 
												new Response.Listener<JSONObject>() {
				
													@Override
													public void onResponse(JSONObject response) {
														Log.i("Script", "SUCCESS: " + response);									
													}
											
												}, 
												new Response.ErrorListener() {
				
													@Override
													public void onErrorResponse(VolleyError error) {
														Toast.makeText(context, "Error " + error.getMessage(), Toast.LENGTH_LONG).show();										
													}
										});
		request.setTag("tag");
		rq.add(request);
	} 
	
	public void callByJsonArrayRequest(final Context context, String method, Map<String,String> paramValue){
		
		String retorno;
		url = url + method;
		
		CustomJsonArrayRequest request = new CustomJsonArrayRequest(Method.POST, url, paramValue, 
											new Response.Listener<JSONArray>() {
			
												@Override
												public void onResponse(JSONArray response) {
													Log.i("Script", "SUCCESS: " + response);
													Toast.makeText(context, "Envio " + "Tags enviadas com secesso", Toast.LENGTH_LONG).show();
												}
										
											}, 
											new Response.ErrorListener() {
			
												@Override
												public void onErrorResponse(VolleyError error) {
													Toast.makeText(context, "Error " + error.getMessage(), Toast.LENGTH_LONG).show();										
												}
								});
		request.setTag("tag");
		rq.add(request);
		
	}
	
	/*
	public void callByStringRequest(final Context context, String method, final Map<String,String> paramValue){
		
		url = url + method;
		
		StringRequest request = new StringRequest(Method.POST, url, 
								new Response.Listener<String>() {

									@Override
									public void onResponse(String response) {
										Log.i("Script", "SUCCESS: " + response);										
									}
								},
								new Response.ErrorListener() {

									@Override
									public void onErrorResponse(
											VolleyError error) {
										Toast.makeText(context, "Error " + error.getMessage(), Toast.LENGTH_LONG).show();										
									}
								} 
						){
							@Override
							public Map<String,String> getParams() throws AuthFailureError{
								
								params = paramValue;
								
								return params;								
							}
							
							@Override
							public Map<String,String> getHeaders() throws AuthFailureError{
								
								HashMap<String,String> header = new HashMap<String,String>();
								header.put("apiKey", "Essa e minha apikey: svjkarpcsfb");
								
								return header;								
							}
							
							@Override
							public Priority getPriority(){
								return Priority.NORMAL;
							}
			};
			
			request.setTag("tag");
			rq.add(request);
	}
	*/
	
/*
	public RFIDProduto retornaJsonWebService(String metodo){
		
		final HttpClient httpClient = new DefaultHttpClient();
		final HttpGet httpGet = new HttpGet(url + metodo);
		final RFIDProduto rfidProduto = new RFIDProduto();
		httpGet.addHeader("accept","application/json");		
				
		new Thread(){
			
			String jsonRetorno;
			
			@Override
			public void run(){			
				
				try{
					
					HttpResponse resposta = httpClient.execute(httpGet);
					jsonRetorno = EntityUtils.toString(resposta.getEntity());			
					
				}catch(ClientProtocolException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}				
			}
			
		}.start();					
		
		return rfidProduto;
	}
	
	public String retornaJsonWebService(String metodo, HashMap<String,Object> param){	
		
		String json = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://192.168.1.102:8081/WSRFIDHSTelecom/rfid/listar-rfidprodutos");
		httpGet.addHeader("accept","application/json");
		
		try{
			ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
			//valores.add(new BasicNameValuePair("tag", tag));			
			
			final HttpResponse resposta = httpClient.execute(httpGet);
			
			Log.i("sistema","Resposta do serviço: " + EntityUtils.toString(resposta.getEntity()));
			
			return EntityUtils.toString(resposta.getEntity());			
			
		}catch(ClientProtocolException e){
			
		}catch(IOException e){
			
		}
		
		return json;
	}	
}
*/
