package com.ungs.revivir.vista.menu.fallecidos.ubicacion;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;

public class ControladorFallecidoPorUbicacion implements ControladorInterno {
	private VentanaFallecidoPorUbicacion ventana;
	
	public ControladorFallecidoPorUbicacion(ControladorPrincipal invocador) {
		ventana = new VentanaFallecidoPorUbicacion();
		ventana.botonLimpiar().setAccion(e -> limpiar());
		ventana.botonBuscar().setAccion(e -> buscar());
	}
	
	private void limpiar() {
		ventana.getPozo().setValorMin("");
		ventana.getPozo().setValorMax("");
		
		ventana.getMacizo().setValorMin("");
		ventana.getMacizo().setValorMax("");
		
		ventana.getParcela().setValorMin("");
		ventana.getParcela().setValorMax("");
		
		ventana.getFila().setValorMin("");
		ventana.getFila().setValorMax("");

		ventana.getUnidad().setValorMin("");
		ventana.getUnidad().setValorMax("");
		
		ventana.getNicho().setValorMin("");
		ventana.getNicho().setValorMax("");
		
		ventana.getMueble().setValorMin("");
		ventana.getMueble().setValorMax("");
		
		ventana.getSepultura().setValorMin("");
		ventana.getSepultura().setValorMax("");

		ventana.getBoveda().setValorMin("");
		ventana.getBoveda().setValorMax("");
		
		ventana.getSeccion().setValor("");
		ventana.getInCheckMostrarTodo().setSelected(false);
		ventana.getInCheck_macizoBis().setSelected(false);
		ventana.getInCheckBis().setSelected(false);
	}
	
	private void buscar() {
		Integer pozoMin = ventana.getPozo().getValorMin();
		Integer pozoMax = ventana.getPozo().getValorMax();
		
		Integer filaMin = ventana.getFila().getValorMin();
		Integer filaMax = ventana.getFila().getValorMax();

		Integer nichoMin = ventana.getNicho().getValorMin();
		Integer nichoMax = ventana.getNicho().getValorMax();

		Integer sepulturaMin= ventana.getSepultura().getValorMin();
		Integer sepulturaMax = ventana.getSepultura().getValorMax();
		
		Integer bovedaMin = ventana.getBoveda().getValorMin();
		Integer bovedaMax= ventana.getBoveda().getValorMax();
		
		Sector sector = (Sector) ventana.getSector().getComboBox().getSelectedItem();
		SubSector subSector = Localizador.mapearSector2(sector);
		String seccion = ventana.getSeccion().getValor();
		seccion = (seccion.equals("") ) ? null : seccion;
		
		List<VFallecidoUbicacion> FallecidosUbicacion =  FallecidoUbicacionManager.traer(pozoMin, pozoMax, null, null, null,
				null, filaMin, filaMax, null, null, nichoMin, nichoMax, null,
				null, sepulturaMin, sepulturaMax, bovedaMin, bovedaMax, subSector, seccion,null,null,null);
		
		ventana.getTabla().recargar(FallecidosUbicacion);
	}
	
	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

}