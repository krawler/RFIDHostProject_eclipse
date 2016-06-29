package com.dotel.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="parametro")
public class Parametro {
	
	public static final String actionRequestProdutos = "listar-rfidprodutos-empresa";
	public static final String actionRequestQtdeProdutos = "qtde-rfidprodutos-empresa";
	public static final String actionRequestStatusListagemProduto = "status-listagem-rfidprodutos-empresa";
	public static final String aplicacao = "/WSRFIDHSTelecom/rfid/";
	public static final String protocoloWebService = "http://";
	
	@DatabaseField(generatedId=true)
	private long id;
	@DatabaseField
	private String urlWebService;
	@DatabaseField
	private String portWebService;
	@DatabaseField
	private String codEmpresa;
	@DatabaseField
	private String jsonFromBD;
	@DatabaseField
	private boolean versaoTeste;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isVersaoTeste() {
		return versaoTeste;
	}
	public void setVersaoTeste(boolean versaoTeste) {
		this.versaoTeste = versaoTeste;
	}
	public String getUrlWebService() {
		return urlWebService;
	}
	public void setUrlWebService(String urlWebService) {
		this.urlWebService = urlWebService;
	}
	public String getPortWebService() {
		return portWebService;
	}
	public void setPortWebService(String portWebService) {
		this.portWebService = portWebService;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getJsonFromBD() {
		return jsonFromBD;
	}
	public void setJsonFromBD(String jsonFromBD) {
		this.jsonFromBD = jsonFromBD;
	}

}
