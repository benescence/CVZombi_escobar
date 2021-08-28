package com.ungs.revivir.persistencia.interfaces.vista;

import java.util.List;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public interface UbicacionLibreVOBD {
	
	public List<Ubicacion> select();
	
	public List<Ubicacion>selectByrangos(
			Integer nichoMax,
			Integer nichoMin,
			Integer circMax,
			Integer circMin,
			Integer filaMax,
			Integer filaMin,
			Integer sepulturaMax,
			Integer sepulturaMin,
			Integer inhumacionMax,
			Integer inhumacionMin,
			String seccion,
			SubSector subsector);

}