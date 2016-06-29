package com.dotel.rfid;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dotel.dao.DataBaseHelper;
import com.dotel.dao.SessaoRFIDDao;
import com.dotel.model.SessaoRFID;
//import com.dotel.util.WebService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RFIDSessoesActivity extends Activity {
	
	private List<HashMap<String, String>> listSessoes;
	private ListView mListDevice;
	private SimpleAdapter adapterSessoes;
	private ListView mListSessoes;
	private DataBaseHelper dh;


	@Override
	public void onBackPressed(){
		
		finish();
		
	}//pressione botao voltar
	
	@Override
	public void  onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		dh = new DataBaseHelper(this);
		
		setContentView(R.layout.list_sessoes);
				
		mListSessoes = (ListView) findViewById(R.id.list_sessoes);
		
		listSessoes = getListSessoes();
		
		adapterSessoes = new SimpleAdapter(this, listSessoes,
										R.layout.item_sessao, 
										new String[]{ "codigosessao", "inicio", "codigo"}, 
										new int[]{ R.id.tvCodigo, R.id.tvInicio, R.id.tvId });
		mListSessoes.setAdapter(adapterSessoes);
		
		mListSessoes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(RFIDSessoesActivity.this, RFIDLeiturasSessao.class);
				RFIDLeiturasSessao.ID_SESSAO = Integer.parseInt(listSessoes.get(arg2).get("codigo"));
				startActivity(intent);
			}
		});
		
	} //ao criar a activity
	
	
	private List<HashMap<String,String>> getListSessoes(){	
		
		List<SessaoRFID> sessoes = null;
		List<HashMap<String,String>> listSessoes = new ArrayList<HashMap<String, String>>();
		
		try {
			sessoes = new SessaoRFIDDao(dh.getConnectionSource()).queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (SessaoRFID sessao : sessoes) {
			 HashMap<String, String> map = new HashMap<String, String>();
			 if(sessao.getFim() != null){
				 map.put("codigo", sessao.getCodigo().toString());
				 map.put("inicio", new SimpleDateFormat().format(sessao.getInicio()));
				 map.put("fim", new SimpleDateFormat().format(sessao.getFim()));
				 map.put("codigosessao", sessao.getCodigoSessaoWebService() == null ? "" : sessao.getCodigoSessaoWebService().toString());
				 listSessoes.add(map);
			 }			 
		}		
	
		return listSessoes;		
	}
	

}
