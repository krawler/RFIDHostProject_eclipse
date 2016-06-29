package com.dotel.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="LeituraRFID")
public class LeituraRFID {
	
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField
	private String codigoLeitura;
	@DatabaseField(foreign=true)
	private SessaoRFID sessao;
	@DatabaseField
	private Integer codProd;

	public LeituraRFID() {
		super();
	}

	public LeituraRFID(Integer id, String codigoLeitura, SessaoRFID sessao, Integer codProd) {
		super();
		this.id = id;
		this.codigoLeitura = codigoLeitura;
		this.sessao = sessao;
		this.codProd = codProd;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigoLeitura() {
		return codigoLeitura;
	}
	public void setCodigoLeitura(String codigoLeitura) {
		this.codigoLeitura = codigoLeitura;
	}
	public SessaoRFID getSessao() {
		return this.sessao;
	}
	public void setCodigoSessao(Integer codigoSessao) {
		this.sessao = sessao;
	}
	public Integer getCodProd() {
		return codProd;
	}

	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}

	@Override
	public String toString() {
		return "LeituraRFID [id=" + id + ", codigoLeitura=" + codigoLeitura
				+ ", codigoSessao=" + sessao.getCodigo() + "]";
	}

}
