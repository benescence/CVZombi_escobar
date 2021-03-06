package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaFallecidoAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnAceptar, btnCancelar;
	
	// DATOS DEL DIFUNTO
	private EntradaTexto inNombre, inApellido, inCod, inCocheria;
	private EntradaFecha inFechaFallecimiento, inFechaIngreso;
	private EntradaLista<TipoFallecimiento> inTipo;
	
	// DATOS DE UBICACION
	private EntradaNumero inUnidad, inFila, inNicho, inSepultura, inPozo,inBoveda; 
	private EntradaTexto inSeccion, inCementerio;
	private EntradaFecha inVencimiento;
	private EntradaLista<Sector> inSector;
	
	public VentanaFallecidoAM() {
		super("Alta de fallecido", 450, 300);
		inicializar(null);
	}
	
	public VentanaFallecidoAM(Fallecido fallecido) {
		super("Alta de fallecido", 450, 300);
		inicializar(fallecido);
		inNombre.getTextField().setText(fallecido.getNombre());
		inApellido.getTextField().setText(fallecido.getApellido());
		inCocheria.getTextField().setText(fallecido.getCocheria());
		inTipo.getComboBox().setSelectedItem(fallecido.getTipoFallecimiento());
	}
	
	public void inicializar(Fallecido fallecido) {
		Dimension dimBoton = new Dimension(100, 25);

		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
	
		
		// PANELES
		PanelVertical panelPrincipal = crearPanelPrincipal();
		panelPrincipal.add(crearPanelFallecido());
		
		if (fallecido == null) {
			panelPrincipal.add(new JSeparator());
			panelPrincipal.add(crearPanelUbicacion());			
		}
		
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	private PanelVertical crearPanelFallecido() {
		Dimension dimTexto = new Dimension(150, 25);
		Dimension dimEntrada = new Dimension(380, 25);
		
		inNombre = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellido = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inCod = new EntradaTexto("Cod Fallecido", dimTexto, dimEntrada);
		inCocheria = new EntradaTexto("Cocher??a", dimTexto, dimEntrada);
		inFechaFallecimiento = new EntradaFecha(null, "Fecha de fallecimiento", dimTexto, dimEntrada);
		inFechaIngreso = new EntradaFecha(Almanaque.hoy(), "Fecha de Ingreso", dimTexto, dimEntrada);
		inTipo = new EntradaLista<>("Tipo de fallecimiento", dimTexto, dimEntrada);
		for (TipoFallecimiento tipo : TipoFallecimiento.values())
			inTipo.getComboBox().addItem(tipo);
		inTipo.getComboBox().setSelectedItem(TipoFallecimiento.NO_TRAUMATICO);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelFallecido = new PanelVertical();
		panelFallecido.setBorder(new EmptyBorder(0, 0, 10, 0));
		panelFallecido.add(new TextoCentrado("Datos del fallecido"));
		panelFallecido.add(inNombre);
		panelFallecido.add(inApellido);
		panelFallecido.add(inCod);
		panelFallecido.add(inCocheria);
		panelFallecido.add(inTipo);
		panelFallecido.add(inFechaFallecimiento);
		panelFallecido.add(inFechaIngreso);
		return panelFallecido;
	}
	
	private PanelVertical crearPanelUbicacion() {
		Dimension dimTexto1 = new Dimension(100, 25);
		Dimension dimTexto2 = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(150, 25);
		Dimension dimEntradaVencimiento = new Dimension(430, 25);

		inPozo = new EntradaNumero("Pozo", dimTexto2, dimEntrada);
		inSeccion = new EntradaTexto("Secci??n", dimTexto1, dimEntrada);
		inUnidad = new EntradaNumero("Unidad", dimTexto1, dimEntrada);
		inNicho = new EntradaNumero("Nicho", dimTexto2, dimEntrada);
		inFila = new EntradaNumero("Fila", dimTexto2, dimEntrada);
		inSepultura = new EntradaNumero("Sepultura", dimTexto1, dimEntrada);
		inCementerio = new EntradaTexto("Cementerio", dimTexto1, dimEntrada);
		inVencimiento = new EntradaFecha(Almanaque.hoy(), "Vencimiento", dimTexto1, dimEntradaVencimiento);

	
		PanelHorizontal panelCheck = new PanelHorizontal();
		
		inSector = new EntradaLista<>("Sector", dimTexto1, dimEntrada);

		for (Sector sector : Localizador.traerSectores())
			inSector.getComboBox().addItem(sector);
		
		// EL SUB SECTOR DEPENDE DEL SECTOR ESCOGIDO
		inSector.getComboBox().addActionListener(e -> seleccionarSector());
		inSector.getComboBox().setSelectedIndex(0);

	
		// ORGANIZACION DE PANELES
		PanelVertical panelIzquierdo = new PanelVertical();
		panelIzquierdo.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelIzquierdo.add(inSector);
		panelIzquierdo.add(inPozo);
		panelIzquierdo.add(inSeccion);
		panelIzquierdo.add(inUnidad);
		
		PanelVertical panelDerecho = new PanelVertical();
		panelDerecho.setBorder(new EmptyBorder(10, 30, 0, 0));
		panelDerecho.add(inNicho);
		panelDerecho.add(inFila);
		panelDerecho.add(inSepultura);
		panelDerecho.add(panelCheck);
		panelDerecho.add(inCementerio);
		
		PanelHorizontal panelHorizontal = new PanelHorizontal();
		panelHorizontal.add(panelIzquierdo);
		panelHorizontal.add(panelDerecho);

		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(10, 0, 0, 0));
		ret.add(new TextoCentrado("Datos de la ubicaci??n"));
		ret.add(panelHorizontal);
		ret.add(inVencimiento);
		return ret;
	}
	
	private void seleccionarSector() {
		Sector sector = (Sector) inSector.getComboBox().getSelectedItem();
		habilitarCamposUbicacion(false);
		
		// Seccion esta habilitado para los 3 sectores
		inSeccion.habilitado(true);
		
		if (sector == Sector.SEPULTURAS) {
			inFila.habilitado(true);
			inSepultura.habilitado(true);
			inPozo.habilitado(true);
		}

		if (sector == Sector.NICHERA) {
			inFila.habilitado(true);
			inNicho.habilitado(true);
		}

		if (sector == Sector.BOVEDA) {
			inBoveda.habilitado(true);
		}
		
	}
	
	private void habilitarCamposUbicacion(boolean habilitado) {
		inSeccion.habilitado(habilitado);
		inCementerio.habilitado(habilitado);
		inUnidad.habilitado(habilitado);
		inSepultura.habilitado(habilitado);
		inNicho.habilitado(habilitado);
		inFila.habilitado(habilitado);
		inPozo.habilitado(habilitado);
	}
	
	public JTextField getCod_Fallecido() {
		return inCod.getTextField();
	}

	public JTextField getApellidoFallecido() {
		return inApellido.getTextField();
	}

	public JTextField getNombreFallecido() {
		return inNombre.getTextField();
	}

	public JTextField getCocheria() {
		return inCocheria.getTextField();
	}

	public EntradaFecha getInFechaFallecimiento() {
		return inFechaFallecimiento;
	}
	
	public EntradaFecha getInFechaIngreso() {
		return inFechaIngreso;
	}
	
	public JComboBox<TipoFallecimiento> getInTipoFallecimiento() {
		return inTipo.getComboBox();
	}

	public EntradaTexto getSeccion() {
		return inSeccion;
	}

	public EntradaTexto getCementerio() {
		return inCementerio;
	}


	public EntradaNumero getUnidad() {
		return inUnidad;
	}

	public EntradaNumero getSepultura() {
		return inSepultura;
	}

	public EntradaNumero getNicho() {
		return inNicho;
	}
	public EntradaNumero getBoveda() {
		return inBoveda;
	}

	public EntradaNumero getFila() {
		return inFila;
	}

	public EntradaNumero getPozo() {
		return inPozo;
	}

	public JComboBox<Sector> getSector() {
		return inSector.getComboBox();
	}

	public Boton botonAceptar() {
		return btnAceptar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}

	public EntradaFecha getVencimiento() {
		return inVencimiento;
	}
		
}