package com.dotel.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.dotel.model.LeituraRFID;
import com.dotel.model.Parametro;
import com.dotel.model.RFIDProduto;
import com.dotel.model.SessaoRFID;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
	
		private static final String databaseName = "receptor.db";
		private static final int databaseVersion = 20;
		
		public DataBaseHelper(Context context) {
			super(context, databaseName, null, databaseVersion);		
		}
		
		public static int getDatabaseVersion(){
			return databaseVersion;
		}
		
		@Override
		public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {
			try {
				TableUtils.createTable(cs, LeituraRFID.class);
				TableUtils.createTable(cs, SessaoRFID.class);
				TableUtils.createTable(cs, Parametro.class);
				TableUtils.createTable(cs, RFIDProduto.class);
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase sd, ConnectionSource cs, int oldVersion, int newVersion) {
			try {
				TableUtils.dropTable(cs, LeituraRFID.class,true);
				TableUtils.dropTable(cs, SessaoRFID.class,true);
				TableUtils.dropTable(cs, Parametro.class,true);
				TableUtils.dropTable(cs, RFIDProduto.class,true);
				onCreate(sd, cs);
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}

}
