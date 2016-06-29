package com.dotel.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class RFIDProduto {
	
	@DatabaseField
	private String tag;
	@DatabaseField
	private String codigo;
	@DatabaseField
	private String produto;
	@DatabaseField
	private Double estoque;
	@DatabaseField
	private String imei;
	
	
	public RFIDProduto() {
	}

	public RFIDProduto(String tag, String codigo, String produto,
			Double estoque, String imei) {
		super();
		this.tag = tag;
		this.codigo = codigo;
		this.produto = produto;
		this.estoque = estoque;
		this.imei = imei;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getEstoque() {
		return estoque;
	}

	public void setEstoque(Double estoque) {
		estoque = estoque;
	}

}

