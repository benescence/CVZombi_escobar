package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import java.sql.Date;

import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorFallecidoAM implements ControladorExterno {
	private VentanaFallecidoAM ventana;
	private FallecidoInvocable invocador;
	private Fallecido modificar;
	private VFallecidoUbicacion original;
	
	public ControladorFallecidoAM(FallecidoInvocable invocador, VFallecidoUbicacion fallecido) {
		this.invocador = invocador;
		this.original= fallecido;
		this.modificar = FallecidoUbicacionManager.extraerFallecido(fallecido);
		ventana = new VentanaFallecidoAM(modificar);
		inicializar();
	}
	
	public ControladorFallecidoAM(FallecidoInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaFallecidoAM();
		inicializar();
	}
	
	private void inicializar() {
		ventana.getCod_Fallecido().setEnabled(false);
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		ventana.getCod_Fallecido().setText(Integer.toString(FallecidoManager.traerUltimoCodFallecido()+1));		
	} 
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			Fallecido fallecido = traerFallecidoVerificado();
			if (fallecido.getCod_fallecido() == 0) {
				Popup.mostrar("El codigo solo puede consistir de numeros");
				return;
			}
			
			// Es un alta
			if (modificar == null) {
				
				// Guardo la ubicacion y consigo el id_ubicacion
				Ubicacion ubicacion = traerUbicacionVerificada();
				UbicacionManager.guardar(ubicacion);
				ubicacion = UbicacionManager.traerMasReciente();
				fallecido.setUbicacion(ubicacion.getID());
				
				// Guardo el fallecido y obtengo su id_fallecido
				FallecidoManager.guardar(fallecido);
				fallecido.setID(FallecidoManager.traerMasReciente().getID());
				
				invocador.actualizarFallecidos(fallecido);
			}
			
			// Es una modificacion
			else {
				FallecidoManager.modificar(fallecido);
				original.setApellido(fallecido.getApellido());
				original.setNombre(fallecido.getNombre());
				invocador.actualizarFallecidos();
			}
			
			ventana.dispose();
			invocador.mostrar();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private Ubicacion traerUbicacionVerificada() throws Exception {
		Sector sector = (Sector) ventana.getSector().getSelectedItem();
		SubSector subsector = Localizador.mapearSector2(sector);
		String otroCementerio = ventana.getCementerio().getValor();
		Integer nicho = (ventana.getNicho().isEnabled() ? ventana.getNicho().getValor() : null);
		Integer fila = (ventana.getFila().isEnabled() ? ventana.getFila().getValor() : null);
		String seccion = (ventana.getSeccion().isEnabled() ? ventana.getSeccion().getTextField().getText() : null);
		Integer sepultura = (ventana.getSepultura().isEnabled() ? ventana.getSepultura().getValor() : null);
		Integer boveda = (ventana.getBoveda().isEnabled() ? ventana.getBoveda().getValor() : null);
		Integer pozo = (ventana.getPozo().isEnabled() ? ventana.getPozo().getValor(): null);
		Date vencimiento = ventana.getVencimiento().getValor();

		Ubicacion ubicacion = new Ubicacion(-1, subsector, otroCementerio, nicho, fila, seccion,
				 sepultura, pozo,boveda, vencimiento);
		
		return Verificador.ubicacion(ubicacion);		
	}	
	
	private Fallecido traerFallecidoVerificado() throws Exception {
		String nombre = ventana.getNombreFallecido().getText();;
		String apellido = ventana.getApellidoFallecido().getText();
		String DNI = "";
		String cocheria = ventana.getCocheria().getText();
		TipoFallecimiento tipo = (TipoFallecimiento) ventana.getInTipoFallecimiento().getSelectedItem();
		Integer cod_fallecido = Validador.cod_fallecido(ventana.getCod_Fallecido().getText()) ? Integer.parseInt(ventana.getCod_Fallecido().getText()) : 0;
		Date fFallecimiento = ventana.getInFechaFallecimiento().getValor();
		Date fIngreso = ventana.getInFechaIngreso().getValor();
		
		Fallecido fallecido = new Fallecido(-1, -1, tipo,cod_fallecido, DNI, apellido, nombre, cocheria, fFallecimiento, fIngreso);
		if (modificar != null)
			fallecido.setID(modificar.getID());
		
		return Verificador.fallecido(fallecido);
	}
	
	private void cancelar() {
		if (Popup.confirmar("??Seguro de que desea cancelar la operaci??n?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}
	
}