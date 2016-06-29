
package com.dotel.dao;

import java.sql.SQLException;

import com.dotel.model.LeituraRFID;
import com.dotel.model.Parametro;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ParametroDao extends BaseDaoImpl<Parametro, Integer> {

	public ParametroDao(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Parametro.class);
		setConnectionSource(connectionSource);
		initialize();
	}

}
	


