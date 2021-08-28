package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public interface UbicacionTotalOBD {

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