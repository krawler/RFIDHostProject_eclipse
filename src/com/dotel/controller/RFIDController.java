package com.dotel.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.dotel.dao.DataBaseHelper;
import com.dotel.dao.LeituraRFIDDao;
import com.dotel.dao.SessaoRFIDDao;
import com.dotel.model.LeituraRFID;
import com.dotel.model.Parametro;
import com.dotel.model.RFIDProduto;
import com.dotel.model.SessaoRFID;
import com.dotel.rfid.RFIDHostActivity;
import com.dotel.util.Funcoes;
//import com.dotel.util.WebService;

public class RFIDController {
	
	private DataBaseHelper dh;
	private LeituraRFIDDao leituraDao;
	private SessaoRFIDDao sessaoDao;
	private SessaoRFID sessao;
	//private WebService webservice;
	private Handler handler = new Handler();
	
	public RFIDController(){
		
	}

	public RFIDController(Context context) throws SQLException {
		super();
		dh = new DataBaseHelper(context);
		leituraDao = new LeituraRFIDDao(dh.getConnectionSource());
		sessaoDao = new SessaoRFIDDao(dh.getConnectionSource());
	//	webservice = new WebService(context);
	}
	
	public void registraLeitura(SessaoRFID sessao,String codigoLeitura,String codProd){
		
		try {
			leituraDao.create(new LeituraRFID(null, codigoLeitura, sessao, Integer.valueOf(codProd)));
		} catch (SQLException e) {
			System.out.println("Erro ao inserir leitura");
			e.printStackTrace();
		}
		
	}
	
	
	public List<LeituraRFID> obtemLeiturasPorSessao(int codigo){
		
		List<LeituraRFID> leituras = new ArrayList<LeituraRFID>();
		
		try {
			leituras = leituraDao.queryForEq("sessao_id", codigo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return leituras;
	}
	
	public List<SessaoRFID> obtemTodasSessoes(String sessao){
		
		List<SessaoRFID> sessoes = new ArrayList<SessaoRFID>();
		
		try {
			sessoes = sessaoDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sessoes;
	}

	public Map<String,Object> importaProduto(JSONObject jsonProduto, Parametro parametro) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		RFIDProduto rfidProduto = null; 
		String tagId = null;
		
		try {
			if(!parametro.isVersaoTeste()){
				tagId = jsonProduto.get("tag").toString().trim().substring(0, 28);	
			}else{
			   tagId = jsonProduto.get("tag").toString().trim();	
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try{
			if(Funcoes.isNumber(tagId)){
				rfidProduto = new RFIDProduto();
				rfidProduto.setCodigo(jsonProduto.get("codigo").toString());
				rfidProduto.setProduto(jsonProduto.get("produto").toString());
				rfidProduto.setTag(tagId);
				rfidProduto.setImei((String) jsonProduto.get("imei"));
				
				map.put("codigo", jsonProduto.get("codigo").toString());
				map.put("produto", jsonProduto.get("produto").toString());
				map.put("tag",tagId);
				map.put("imei", jsonProduto.get("imei").toString());											
			}
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		return map;
		
	}
	
}
