package com.ungs.revivir.persistencia.entidades.vista;

import java.sql.Date;
import com.ungs.revivir.persistencia.definidos.SubSector;

public class VPagoReporte {
	
	// Representa un pago extendido con todos los datos para msotrar en un reporte
	
	// Pago
	private Integer pagoID;
	private Double pagoImporte;
	private String pagoObservaciones;
	private Date pagoFecha;
	
	// Cargo
	private Integer cargoID;
	private String cargoObservaciones;
	private Boolean cargoPagado;

	// Fallecido
	private Integer fallecidoID;
	private Integer fallecidoCodigo;
	private String fallecidoNombre;
	private String fallecidoApellido;
	private String fallecidoDNI;

	// Servicio
	private Integer servicioID;
	private String servicioNombre;
	private Boolean servicioHistorico;

	// Ubicacion
	private Integer ubicacionID;
	private SubSector ubicacionSubsector;
	private Integer ubicacionPozo;
	private String ubicacionSeccion;
	private Integer ubicacionFila;
	private Integer ubicacionNicho;
	private Integer ubicacionSepultura;
	private Integer ubicacionBoveda;
	private String ubicacionCementerio;
	private Date ubicacionVencimiento;

	
	public VPagoReporte(
			
			// Parametros pago
			Integer pagoID, Double pagoImporte, String pagoObservaciones, Date pagoFecha,

			// Parametros cargo			
			Integer cargoID, String cargoObservaciones, Boolean cargoPagado,
			
			// Parametros fallecido
			Integer fallecidoID, Integer fallecidoCodigo, String fallecidoNombre, String fallecidoApellido, String fallecidoDNI,
			
			// Parametros servicio
			Integer servicioID, String servicioNombre, Boolean servicioHistorico,

			// Parametros ubicacion
			Integer ubicacionID, SubSector ubicacionSubsector, Integer ubicacionPozo,
			String ubicacionSeccion, Integer ubicacionFila,Integer ubicacionNicho, Integer ubicacionSepultura,
			Integer ubicacionBoveda, String ubicacionCementerio, Date ubicacionVencimiento
			) {
		
		this.pagoID = pagoID;
		this.pagoImporte = pagoImporte;
		this.pagoObservaciones = pagoObservaciones;
		this.pagoFecha = pagoFecha;
		
		this.cargoID = cargoID;
		this.cargoObservaciones = cargoObservaciones;
		this.cargoPagado = cargoPagado;
		
		this.fallecidoID = fallecidoID;
		this.fallecidoCodigo= fallecidoCodigo;
		this.fallecidoNombre = fallecidoNombre;
		this.fallecidoApellido = fallecidoApellido;
		this.fallecidoDNI = fallecidoDNI;
		
		this.servicioID = servicioID;
		this.servicioNombre = servicioNombre;
		this.servicioHistorico = servicioHistorico;
		
		this.ubicacionID = ubicacionID;
		this.ubicacionSubsector = ubicacionSubsector;
		this.ubicacionPozo = ubicacionPozo;
		this.ubicacionSeccion = ubicacionSeccion;
		this.ubicacionFila = ubicacionFila;
		this.ubicacionNicho = ubicacionNicho;
		this.ubicacionSepultura = ubicacionSepultura;
		this.ubicacionBoveda = ubicacionBoveda;
		this.ubicacionCementerio = ubicacionCementerio;
		this.ubicacionVencimiento = ubicacionVencimiento;
	}

	public Integer getPagoID() {
		return pagoID;
	}

	public void setPagoID(Integer pagoID) {
		this.pagoID = pagoID;
	}

	public Double getPagoImporte() {
		return pagoImporte;
	}

	public void setPagoImporte(Double pagoImporte) {
		this.pagoImporte = pagoImporte;
	}

	public String getPagoObservaciones() {
		return pagoObservaciones;
	}

	public void setPagoObservaciones(String pagoObservaciones) {
		this.pagoObservaciones = pagoObservaciones;
	}

	public Date getPagoFecha() {
		return pagoFecha;
	}

	public void setPagoFecha(Date pagoFecha) {
		this.pagoFecha = pagoFecha;
	}

	public Integer getCargoID() {
		return cargoID;
	}

	public void setCargoID(Integer cargoID) {
		this.cargoID = cargoID;
	}

	public String getCargoObservaciones() {
		return cargoObservaciones;
	}

	public void setCargoObservaciones(String cargoObservaciones) {
		this.cargoObservaciones = cargoObservaciones;
	}

	public Boolean getCargoPagado() {
		return cargoPagado;
	}

	public void setCargoPagado(Boolean cargoPagado) {
		this.cargoPagado = cargoPagado;
	}

	public Integer getFallecidoID() {
		return fallecidoID;
	}

	public void setFallecidoID(Integer fallecidoID) {
		this.fallecidoID = fallecidoID;
	}

	public Integer getFallecidoCodigo() {
		return fallecidoCodigo;
	}

	public void setFallecidoCodigo(Integer fallecidoCodigo) {
		this.fallecidoCodigo = fallecidoCodigo;
	}

	public String getFallecidoNombre() {
		return fallecidoNombre;
	}

	public void setFallecidoNombre(String fallecidoNombre) {
		this.fallecidoNombre = fallecidoNombre;
	}

	public String getFallecidoApellido() {
		return fallecidoApellido;
	}

	public void setFallecidoApellido(String fallecidoApellido) {
		this.fallecidoApellido = fallecidoApellido;
	}

	public String getFallecidoDNI() {
		return fallecidoDNI;
	}

	public void setFallecidoDNI(String fallecidoDNI) {
		this.fallecidoDNI = fallecidoDNI;
	}

	public Integer getServicioID() {
		return servicioID;
	}

	public void setServicioID(Integer servicioID) {
		this.servicioID = servicioID;
	}

	public String getServicioNombre() {
		return servicioNombre;
	}

	public void setServicioNombre(String servicioNombre) {
		this.servicioNombre = servicioNombre;
	}

	public Boolean getServicioHistorico() {
		return servicioHistorico;
	}

	public void setServicioHistorico(Boolean servicioHistorico) {
		this.servicioHistorico = servicioHistorico;
	}

	public Integer getUbicacionID() {
		return ubicacionID;
	}

	public void setUbicacionID(Integer ubicacionID) {
		this.ubicacionID = ubicacionID;
	}

	public SubSector getUbicacionSubsector() {
		return ubicacionSubsector;
	}

	public void setUbicacionSubsector(SubSector ubicacionSubsector) {
		this.ubicacionSubsector = ubicacionSubsector;
	}

	public Integer getUbicacionPozo() {
		return ubicacionPozo;
	}

	public void setUbicacionPozo(Integer ubicacionPozo) {
		this.ubicacionPozo = ubicacionPozo;
	}

	public String getUbicacionSeccion() {
		return ubicacionSeccion;
	}

	public void setUbicacionSeccion(String ubicacionSeccion) {
		this.ubicacionSeccion = ubicacionSeccion;
	}



	public Integer getUbicacionFila() {
		return ubicacionFila;
	}

	public void setUbicacionFila(Integer ubicacionFila) {
		this.ubicacionFila = ubicacionFila;
	}




	public Integer getUbicacionNicho() {
		return ubicacionNicho;
	}

	public void setUbicacionNicho(Integer ubicacionNicho) {
		this.ubicacionNicho = ubicacionNicho;
	}



	public Integer getUbicacionSepultura() {
		return ubicacionSepultura;
	}

	public void setUbicacionSepultura(Integer ubicacionSepultura) {
		this.ubicacionSepultura = ubicacionSepultura;
	}

	public Integer getUbicacionBoveda() {
		return ubicacionBoveda;
	}

	public void setUbicacionBoveda(Integer ubicacionBoveda) {
		this.ubicacionBoveda = ubicacionBoveda;
	}

	public String getUbicacionCementerio() {
		return ubicacionCementerio;
	}

	public void setUbicacionCementerio(String ubicacionCementerio) {
		this.ubicacionCementerio = ubicacionCementerio;
	}

	public Date getUbicacionVencimiento() {
		return ubicacionVencimiento;
	}

	public void setUbicacionVencimiento(Date ubicacionVencimiento) {
		this.ubicacionVencimiento = ubicacionVencimiento;
	}

	
}