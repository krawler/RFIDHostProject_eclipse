package com.dotel.dao;

import java.sql.SQLException;

import com.dotel.model.LeituraRFID;
import com.dotel.model.Parametro;
import com.dotel.model.RFIDProduto;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class RFIDProdutoDao extends BaseDaoImpl<RFIDProduto, Integer> {

	public RFIDProdutoDao(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, RFIDProduto.class);
		setConnectionSource(connectionSource);
		initialize();
	}
	
	public boolean limpaTabelaProdutos(){
		
		try {
			executeRaw("delete from rfidproduto");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}
	


