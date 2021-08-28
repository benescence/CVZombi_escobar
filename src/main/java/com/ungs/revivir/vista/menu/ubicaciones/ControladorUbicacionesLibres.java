package com.ungs.revivir.vista.menu.ubicaciones;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Busqueda;
import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;

public class ControladorUbicacionesLibres implements ControladorInterno {
	private VentanaUbicacionesLibres ventana;
	
	public ControladorUbicacionesLibres(ControladorPrincipal invocador) {
		ventana = new VentanaUbicacionesLibres();
		ventana.botonLimpiar().setAccion(e -> limpiar());
		ventana.botonBuscar().setAccion(e -> buscar());
	}
	
	private void limpiar() {
		ventana.getCirc().setValorMin("");
		ventana.getCirc().setValorMax("");
		
		ventana.getFila().setValorMin("");
		ventana.getFila().setValorMax("");
		
		ventana.getNicho().setValorMin("");
		ventana.getNicho().setValorMax("");
			
		ventana.getSepultura().setValorMin("");
		ventana.getSepultura().setValorMax("");

		ventana.getInhumacion().setValorMin("");
		ventana.getInhumacion().setValorMax("");
		
		ventana.getSeccion().setValor("");
		ventana.getInCheckMostrarTodo().setSelected(false);
	}
	
	private void buscar() {
		Integer circMin = ventana.getCirc().getValorMin();
		Integer circMax = ventana.getCirc().getValorMax();


		Integer filaMin = ventana.getFila().getValorMin();
		Integer filaMax = ventana.getFila().getValorMax();

		Integer nichoMin = ventana.getNicho().getValorMin();
		Integer nichoMax = ventana.getNicho().getValorMax();

		Integer sepulturaMin= ventana.getSepultura().getValorMin();
		Integer sepulturaMax = ventana.getSepultura().getValorMax();
		
		Integer inhumacionMin = ventana.getInhumacion().getValorMin();
		Integer inhumacionMax= ventana.getInhumacion().getValorMax();
		
		Sector sector = (Sector) ventana.getSector().getComboBox().getSelectedItem();
		SubSector subSector = Localizador.mapearSector2(sector);
 		String seccion = ventana.getSeccion().getValor();
		seccion = (seccion.equals("") ) ? null : seccion;
		boolean mostrar= ventana.getInCheckMostrarTodo().isSelected();

		List<Ubicacion> ubicaciones = Busqueda.ubicaciones(circMin, circMax,
				 filaMin, filaMax,nichoMin, nichoMax,
				sepulturaMin, sepulturaMax, inhumacionMin, inhumacionMax, subSector, seccion,mostrar);
		
		ventana.getTabla().recargar(ubicaciones);
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