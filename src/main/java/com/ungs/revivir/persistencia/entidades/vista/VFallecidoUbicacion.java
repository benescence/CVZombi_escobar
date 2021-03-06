package com.ungs.revivir.persistencia.entidades.vista;

import java.sql.Date;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;

public class VFallecidoUbicacion {
	
	// Representa un fallecido extendido con todos los datos de su ubicacion
	
	// Fallecido
	private Integer ID, ubicacion, codFallecido;
	private String DNI, apellido, nombre, cocheria;
	private Date fechaFallecimiento, fechaIngreso;
	private TipoFallecimiento tipoFallecimiento;
	
	// Ubicacion vinculada a ese fallecido
	private Integer nicho, fila, sepultura, boveda, pozo;
	private String  cementerio, seccion;
	private SubSector subsector;
	private Date vencimiento;
		
	public VFallecidoUbicacion(
			
			// Parametros del fallecido
			Integer ID, Integer ubicacion, TipoFallecimiento tipoFallecimiento, Integer codFallecido, String DNI,
			String apellido, String nombre, String cocheria, Date fechaFallecimiento, Date fechaIngreso,
			
			// Parametros de la ubicacion
			SubSector subsector, String cementerio, Integer nicho, Integer fila, String seccion,
			Integer sepultura,Integer boveda, Integer pozo, Date vencimiento) {
		
		// Datos del fallecido
		this.ID = ID;
		this.ubicacion = ubicacion;
		this.tipoFallecimiento = tipoFallecimiento;
		this.codFallecido = codFallecido;
		this.DNI = DNI;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cocheria = cocheria;
		this.fechaFallecimiento = fechaFallecimiento;
		this.fechaIngreso = fechaIngreso;
		
		// Datos de la ubicacion
		this.subsector = subsector;
		this.cementerio = cementerio;
		this.nicho = nicho;
		this.fila = fila;
		this.seccion = seccion;
		this.sepultura = sepultura;
		this.boveda = boveda;
		this.pozo = pozo;
		this.vencimiento = vencimiento;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Integer ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getCodFallecido() {
		return codFallecido;
	}

	public void setCodFallecido(Integer codFallecido) {
		this.codFallecido = codFallecido;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCocheria() {
		return cocheria;
	}

	public void setCocheria(String cocheria) {
		this.cocheria = cocheria;
	}

	public Date getFechaFallecimiento() {
		return fechaFallecimiento;
	}

	public void setFechaFallecimiento(Date fechaFallecimiento) {
		this.fechaFallecimiento = fechaFallecimiento;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public TipoFallecimiento getTipoFallecimiento() {
		return tipoFallecimiento;
	}

	public void setTipoFallecimiento(TipoFallecimiento tipoFallecimiento) {
		this.tipoFallecimiento = tipoFallecimiento;
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


	public Integer getBoveda() {
		return boveda;
	}

	public void setBoveda(Integer boveda) {
		this.boveda = boveda;
	}

	public Integer getPozo() {
		return pozo;
	}

	public void setPozo(Integer pozo) {
		this.pozo = pozo;
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