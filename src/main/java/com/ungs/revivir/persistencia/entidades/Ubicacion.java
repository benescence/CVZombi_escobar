package com.ungs.revivir.persistencia.entidades;

import java.sql.Date;
import com.ungs.revivir.persistencia.definidos.SubSector;

public class Ubicacion {
	private Integer ID, nicho, fila, sepultura, pozo, boveda;
	private String  cementerio, seccion;
	private SubSector subsector;
	private Date vencimiento;
	
	public Ubicacion(Integer ID, SubSector subsector, String cementerio, Integer nicho, Integer fila,
			String seccion, Integer sepultura,
			 Integer pozo, Integer boveda, Date vencimiento) {
		this.ID = ID;
		this.subsector = subsector;
		this.cementerio = cementerio;
		this.nicho = nicho;
		this.fila = fila;
		this.seccion = seccion;
		this.sepultura = sepultura;
		this.pozo = pozo;
		this.boveda = boveda;
		this.vencimiento = vencimiento;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getNicho() {
		return nicho;
	}

	public void setNicho(Integer nicho) {
		this.nicho = nicho;
	}

	public Integer getFila() {
		return fila;
	}

	public void setFila(Integer fila) {
		this.fila = fila;
	}

	public Integer getSepultura() {
		return sepultura;
	}

	public void setSepultura(Integer sepultura) {
		this.sepultura = sepultura;
	}

	public Integer getPozo() {
		return pozo;
	}

	public void setPozo(Integer pozo) {
		this.pozo = pozo;
	}

	public Integer getBoveda() {
		return boveda;
	}

	public void setBoveda(Integer boveda) {
		this.boveda = boveda;
	}

	public String getCementerio() {
		return cementerio;
	}

	public void setCementerio(String cementerio) {
		this.cementerio = cementerio;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public SubSector getSubsector() {
		return subsector;
	}

	public void setSubsector(SubSector subsector) {
		this.subsector = subsector;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

}