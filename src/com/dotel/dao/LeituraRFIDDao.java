package com.dotel.dao;

import java.sql.SQLException;

import com.dotel.model.LeituraRFID;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class LeituraRFIDDao extends BaseDaoImpl<LeituraRFID, Integer> {

	public LeituraRFIDDao(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, LeituraRFID.class);
		setConnectionSource(connectionSource);
		initialize();
	}

}
