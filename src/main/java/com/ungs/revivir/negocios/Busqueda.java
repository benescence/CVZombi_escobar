package com.ungs.revivir.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;
import com.ungs.revivir.persistencia.interfaces.vista.UbicacionLibreVOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionTotalOBD;

public class Busqueda {
	
	public static List<Fallecido> fallecidos(String DNI, String nombres, String apellido,Integer cod_fallecido) {
		DNI = Verificador.anular(DNI);
		cod_fallecido = Verificador.anularInt(cod_fallecido);
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByNombreApellidoCOD(nombres, apellido, cod_fallecido);
	}

	public static List<Ubicacion> ubicaciones(
			Integer pozoMin, Integer pozoMax,
			Integer filaMin, Integer filaMax,
			Integer nichoMin, Integer nichoMax,
			Integer sepulturaMin, Integer sepulturaMax,
			Integer bovedaMin, Integer bovedaMax,
			SubSector subSector,
			String seccion,
			boolean mostrar) {
		
		// Si esta activado el FLAG mostrar trae todas las ubicaciones posibles (no importa si esta ocupado o no)
		UbicacionTotalOBD obd_total = FactoryOBD.crearUbicacionTotalOBD();
		if(mostrar)
			return obd_total.selectByrangos(
					nichoMax, nichoMin,
					pozoMax, pozoMin,
					filaMax, filaMin,
					sepulturaMax, sepulturaMin,
					bovedaMax, bovedaMin,
					seccion,
					subSector);

		// De lo contrario trae solo las ubicaciones que no estan ocupado
		UbicacionLibreVOBD obd_libre = FactoryOBD.crearUbicacionLibreOBD();
		return obd_libre.selectByrangos(
				nichoMax, nichoMin,
				pozoMax, pozoMin,
				filaMax, filaMin,
				sepulturaMax, sepulturaMin,
				bovedaMax, bovedaMin,
				seccion,
				subSector);
	}


	public static List<Pago> pagos( Fallecido fallecido, Date fecha) throws Exception {

		// validaciones
		if (fallecido == null && fecha == null)
			throw new Exception("Debe llenar al menos uno de los 2 campos: cliente, fallecido o fecha.");
		
		// Solo lleno solo la fecha
		if ( fallecido == null)
			return PagoManager.traerPorFecha(fecha);
		else
			return traerPagos(fallecido, fecha);
	}
		
	public static List<Pago> traerPagos(Fallecido fallecido, Date fecha) {
		List<Pago> pagos = Relacionador.traerPagos(fallecido);
		
		if (fecha == null)
			return pagos;
		
		List<Pago> ret = new ArrayList<>();
		fecha = Almanaque.normalizar(fecha);
		
		for (Pago pago : pagos)
			if (Almanaque.normalizar(pago.getFecha()).equals(fecha))
				ret.add(pago);
		
		return ret;
	}
	
	public static List<Pago> traerPagos(Fallecido fallecido, Date fechaDesde, Date fechaHasta) {
		List<Cargo> cargos = CargoManager.traerPorFallecido(fallecido);
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		return obd.selectByCargosDesdeHasta(cargos, fechaDesde, fechaHasta);
	}
	
	public static List<Pago> traerPagosFallecido(Fallecido fallecido) {
		return Relacionador.traerPagos(fallecido);
	}

}