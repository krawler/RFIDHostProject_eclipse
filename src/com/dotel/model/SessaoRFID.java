package com.dotel.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="SessaoRFID")
public class SessaoRFID {
	
	@DatabaseField(generatedId=true)
	private Integer codigo;
	@DatabaseField
	private Date inicio;
	@DatabaseField
	private Date fim;
	@DatabaseField
	private Integer codEmpresa;
	@DatabaseField
	private Integer codigoSessaoWebService;

	public SessaoRFID() {
		super();
	}
	
	public SessaoRFID(Integer codigo, Date inicio, Date fim, Integer codEmpresa, Integer codigoSessaoWebService) {
		super();
		this.codigo = codigo;
		this.inicio = inicio;
		this.fim = fim;
		this.codEmpresa = codEmpresa;
		this.codigoSessaoWebService = codigoSessaoWebService;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public Integer getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(Integer codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public Integer getCodigoSessaoWebService() {
		return codigoSessaoWebService;
	}

	public void setCodigoSessaoWebService(Integer codigoSessaoWebService) {
		this.codigoSessaoWebService = codigoSessaoWebService;
	}
	@Override
	public String toString() {
		return "SessaoRFID [codigo=" + codigo + ", inicio=" + inicio + ", fim="
				+ fim + ", codEmpresa=" + codEmpresa + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codEmpresa == null) ? 0 : codEmpresa.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((fim == null) ? 0 : fim.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessaoRFID other = (SessaoRFID) obj;
		if (codEmpresa == null) {
			if (other.codEmpresa != null)
				return false;
		} else if (!codEmpresa.equals(other.codEmpresa))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (fim == null) {
			if (other.fim != null)
				return false;
		} else if (!fim.equals(other.fim))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		return true;
	}
	

}

