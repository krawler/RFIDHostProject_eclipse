package com.dotel.rfid;

/***********************************************************************************
* revision history                                                                 *
*************+*************+********+***********************************************
* 2012.12.12	ver 1.0.0  	  eric     1. Generated(First release)                 *                     
************************************************************************************/

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.dotel.controller.RFIDController;
import com.dotel.rfid.R900Status;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class RFIDStartActivity extends Activity implements
	View.OnClickListener
{
	private Context mContext;
	
	// --- Interface Mode
	public static final int MODE_NOT_DETECTED = 0;
	public static final int MODE_BT_INTERFACE = 1;
	public static final int MODE_USB_INTERFACE = 2;
	
	
	// --- Mode 
	private Button mBtnBT;
	private Button mBtnUSB;
	private ImageView mDoth900;
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		setContentView(R.layout.start);		
		
		//mDoth900 = (ImageView)findViewById(R.id.drawable_r900);		
		//mDoth900.setScaleType(ImageView.ScaleType.FIT_XY);
		
		mBtnBT = (Button)findViewById(R.id.btn_BTMode);
		mBtnBT.setOnClickListener(this);
		
		//mBtnUSB = (Button) findViewById(R.id.btn_USBMode);
		//mBtnUSB.setOnClickListener(this);
		
		// interface mode
		R900Status.setInterfaceMode(MODE_NOT_DETECTED);
		
	}
	
	@Override
	public void onBackPressed()
	{
		new AlertDialog.Builder(this)
				.setTitle("SAIDA")
				.setMessage("Você deseja sair do programa?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener()
				{
					public void onClick( DialogInterface dialog, int whichButton )
					{
						finish();
					}
				})
				.setNegativeButton("Não", new DialogInterface.OnClickListener()
				{
					public void onClick( DialogInterface dialog, int whichButton )
					{
					}
				}).create().show();
	}
	
	public void clickSessoes(View v){
		
		Intent sessoesIntent = new Intent(mContext.getApplicationContext(), RFIDSessoesActivity.class);
		startActivity(sessoesIntent);	
	}
	
	public void clickParametros(View view){

		Intent parametroIntent = new Intent(mContext.getApplicationContext(), ParametrosActivity.class);
		startActivity(parametroIntent);	
	}
	
	public void onClick( View v )
	{
		switch( v.getId() )
		{
			case R.id.btn_BTMode:
				Intent rfidProdutosRFIDIntent = new Intent(mContext.getApplicationContext(), RFIDProdutosActivity.class);
				startActivity(rfidProdutosRFIDIntent);				
				
				break;						
		}
	}

}