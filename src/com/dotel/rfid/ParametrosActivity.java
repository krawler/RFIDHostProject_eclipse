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
import com.dotel.dao.ParametroDao;
import com.dotel.dao.SessaoRFIDDao;
import com.dotel.model.Parametro;
import com.dotel.model.SessaoRFID;
//import com.dotel.util.WebService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ParametrosActivity extends Activity {
	
	private List<HashMap<String, String>> listSessoes;
	private ListView mListDevice;
	private SimpleAdapter adapterSessoes;
	private ListView mListSessoes;
	private DataBaseHelper dh;
	private Parametro parametro;
	private ParametroDao parametroDao;
	private EditText etUrlWebService;
	private EditText etPortaWebService;
	private EditText etCodigoEmpresa;
	private CheckBox chkVersaoTeste;

	@Override
	public void onBackPressed(){
		
		finish();
		
	}//pressione botao voltar
	
	@Override
	public void  onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.parametros);
		
		dh = new DataBaseHelper(this);
		try {
			parametroDao = new ParametroDao(dh.getConnectionSource());
		} catch (SQLException e) {		
			e.printStackTrace();
		}	
				
		etCodigoEmpresa = (EditText) findViewById(R.id.etCodigoEmpresa);
		etPortaWebService = (EditText) findViewById(R.id.etPortaWebService);
		etUrlWebService = (EditText) findViewById(R.id.etUrlWebService);
		chkVersaoTeste = (CheckBox) findViewById(R.id.chkVersaoTeste);
		
		try {
			parametro = parametroDao.queryForAll().size() > 0 ? parametroDao.queryForAll().get(0) : null;
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		if(parametro != null){
		   etCodigoEmpresa.setText(parametro.getCodEmpresa());
		   etPortaWebService.setText(parametro.getPortWebService());
		   etUrlWebService.setText(parametro.getUrlWebService());
		   chkVersaoTeste.setChecked(parametro.isVersaoTeste());
		}
		
	} //ao criar a activity
	
	
	public void createNewParametro(View view){
		
		if(parametro == null){
			
			parametro = new Parametro();
			parametro.setUrlWebService(etUrlWebService.getText().toString());
			parametro.setPortWebService(etPortaWebService.getText().toString());
			parametro.setCodEmpresa(etCodigoEmpresa.getText().toString());
			parametro.setVersaoTeste(chkVersaoTeste.isChecked());
			
			try {
				parametroDao.create(parametro);
				Toast.makeText(this, "Parâmetro criado com secesso", Toast.LENGTH_LONG).show();
			} catch (SQLException e) {		
				e.printStackTrace();
			}	
			
		}else{
			
			parametro.setUrlWebService(etUrlWebService.getText().toString());
			parametro.setPortWebService(etPortaWebService.getText().toString());
			parametro.setCodEmpresa(etCodigoEmpresa.getText().toString());
			parametro.setVersaoTeste(chkVersaoTeste.isChecked());
			
			try {
				parametroDao.update(parametro);
				Toast.makeText(this, "Parâmetro atualizado com secesso", Toast.LENGTH_LONG).show();
			} catch (SQLException e) {		
				e.printStackTrace();
			}	
		}	
		
	}
	
	public void cancel(View view){
		finish();
	}
		
	
}
