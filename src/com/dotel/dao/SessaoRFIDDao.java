package com.dotel.dao;

import java.sql.SQLException;

import com.dotel.model.SessaoRFID;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class SessaoRFIDDao extends BaseDaoImpl<SessaoRFID, Integer> {

	public SessaoRFIDDao(ConnectionSource cs) throws SQLException {
		super(cs, SessaoRFID.class);
		setConnectionSource(cs);
		initialize();
	}

}
