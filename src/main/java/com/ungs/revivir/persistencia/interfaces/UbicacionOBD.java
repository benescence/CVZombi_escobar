package com.ungs.revivir.persistencia.interfaces;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public interface UbicacionOBD {

	// METODOS COMUNES
	
	public void insert(Ubicacion ubicacion);

	public void update(Ubicacion ubicacion);
	
	public void delete(Ubicacion ubicacion);
		
	public Ubicacion selectByID(Integer ID);

	public Ubicacion ultimoInsertado();
		
	public List<Ubicacion> select();
	
	// METODOS ESPECIFICOS
	
	public Ubicacion selectByFallecido(Fallecido fallecido);
	
	public List<Ubicacion> selectBySubsectorEntreFechas(SubSector subSector, Date desde, Date hasta);

	public List<Ubicacion> selectBySubsectorEntreFechasSinLimite(SubSector subSector, Date desde, Date hasta);
	
	public Ubicacion selectByUbicacion(Ubicacion ubicacion);
	
	public List<Ubicacion>selectByrangos(
			Integer nichoMax,
			Integer nichoMin,
			Integer pozoMax,
			Integer pozoMin,
			Integer filaMax,
			Integer filaMin,
			Integer sepulturaMax,
			Integer sepulturaMin,
			Integer bovedaMax,
			Integer bovedaMin,
			String seccion,
			SubSector subsector
		);
	
}