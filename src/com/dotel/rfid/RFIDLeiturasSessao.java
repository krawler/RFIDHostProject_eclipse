package com.dotel.rfid;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.dotel.dao.DataBaseHelper;
import com.dotel.dao.LeituraRFIDDao;
import com.dotel.dao.SessaoRFIDDao;
import com.dotel.model.LeituraRFID;
import com.dotel.model.SessaoRFID;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class RFIDLeiturasSessao extends Activity {
	
	public static int ID_SESSAO;
	private List<HashMap<String, String>> listLeituras;
	private SimpleAdapter adapter;
	private ListView mListLeituras;
	private DataBaseHelper dh;
	private TextView tvNumSessao;
	private TextView tvNumTags;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leituras_sessao);
		
		dh = new DataBaseHelper(this);
				
		mListLeituras = (ListView) findViewById(R.id.list_leituras_sessao);
		tvNumSessao   = (TextView) findViewById(R.id.tvNumSessao);
		tvNumTags     = (TextView) findViewById(R.id.tvNumTags);
		
		List<HashMap<String, String>> lista = getListLeituras(ID_SESSAO);
		tvNumSessao.setText(String.valueOf(ID_SESSAO));
		tvNumTags.setText(String.valueOf(lista.size()));
		
		adapter = new SimpleAdapter(this, lista,
										R.layout.item_leitura, 
										new String[]{ "tag"}, 
										new int[]{ R.id.tvTag_item });
		mListLeituras.setAdapter(adapter);		
	}
	
	private List<HashMap<String,String>> getListLeituras(int idSessao){	
		
		List<LeituraRFID> leituras = null;
		List<HashMap<String,String>> listLeituras = new ArrayList<HashMap<String, String>>();
		
		try {
			leituras = new LeituraRFIDDao(dh.getConnectionSource()).queryForEq("sessao_id", idSessao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (LeituraRFID leitura : leituras) {
			 HashMap<String, String> map = new HashMap<String, String>();			 
			 map.put("tag", leitura.getCodigoLeitura().toString());
			 listLeituras.add(map);
		}		
	
		return listLeituras;		
	}

}
