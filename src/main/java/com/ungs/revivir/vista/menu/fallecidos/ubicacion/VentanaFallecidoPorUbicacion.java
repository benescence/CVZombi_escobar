package com.ungs.revivir.vista.menu.fallecidos.ubicacion;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;
import com.ungs.revivir.vista.tablas.TablaFallecidos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaNumeroEntre;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaFallecidoPorUbicacion extends VentanaInterna{
	private static final long serialVersionUID = 1L;
	private TablaFallecidos tabla;
	private EntradaLista<Sector> inSector;
	private EntradaLista<SubSector> inSubsector;
	private EntradaNumeroEntre inPozo, inMacizo, inParcela, inFila, inUnidad, inNicho, inMueble, inSepultura, inBoveda;
	private EntradaTexto inSeccion;
	private Boton btnBuscar, btnLimpiar;
	private JCheckBox inCheckMostrarTodo;
	private JCheckBox inCheckMacizo_bis;
	private JCheckBox inCheckbis;
	
	public VentanaFallecidoPorUbicacion() {
		super("Fallecidos por ubicacion", 500, 500);
		Dimension dimBoton = new Dimension(100, 25);
		
		tabla = new TablaFallecidos (new ArrayList<VFallecidoUbicacion>() );
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		btnBuscar = new Boton("Buscar", dimBoton);
		btnLimpiar = new Boton("Limpiar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnBuscar);
		panelBotones.add(btnLimpiar);
		panelBotones.setBorder(new EmptyBorder(10, 0, 10, 0));
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelBusqueda());
		panelPrincipal.add(panelBotones);
		panelPrincipal.add(panelTabla);
	}
	
	private PanelVertical panelBusqueda() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntradaDoble = new Dimension(125, 25);
		Dimension dimEntrada = new Dimension(330, 25);
		
		// Inicializo las listas de sectores
		inSector = new EntradaLista<>("Sector", dimTexto, dimEntrada);
		
		for (Sector sector : Localizador.traerSectores())
			inSector.getComboBox().addItem(sector);
		
		inSector.getComboBox().setSelectedIndex(0);

		// Inicializo el esto de las entradas
		inPozo = new EntradaNumeroEntre("Pozo", dimTexto, dimEntradaDoble);
		inMacizo = new EntradaNumeroEntre("Macizo", dimTexto, dimEntradaDoble);
		inFila = new EntradaNumeroEntre("Fila", dimTexto, dimEntradaDoble);
		inNicho = new EntradaNumeroEntre("Nicho", dimTexto, dimEntradaDoble);
		inSepultura = new EntradaNumeroEntre("Sepultura", dimTexto, dimEntradaDoble);
		inBoveda= new EntradaNumeroEntre("Boveda", dimTexto, dimEntradaDoble);
		inSeccion = new EntradaTexto("Seccion", dimTexto, dimEntrada);
		
		PanelVertical ret1 = new PanelVertical();
		ret1.setBorder(new EmptyBorder(0, 0, 0, 10));
		ret1.add(inSector);
		ret1.add(inPozo);
		ret1.add(inMacizo);
		ret1.add(inFila);
		
		PanelVertical ret2 = new PanelVertical();
		ret2.setBorder(new EmptyBorder(0, 10, 0, 0));
		ret2.add(inSeccion);
		ret2.add(inNicho);
		ret2.add(inSepultura);
		ret2.add(inBoveda);
		
		PanelHorizontal ret3 = new PanelHorizontal();
		ret3.add(ret1);
		ret3.add(ret2);
		
		PanelVertical ret = new PanelVertical();
		ret.add(ret3);
		return ret;
	}

	public TablaFallecidos getTabla() {
		return tabla;
	}

	public EntradaLista<Sector> getSector() {
		return inSector;
	}

	public EntradaLista<SubSector> getSubsector() {
		return inSubsector;
	}

	public EntradaNumeroEntre getPozo() {
		return inPozo;
	}

	public EntradaNumeroEntre getMacizo() {
		return inMacizo;
	}

	public EntradaNumeroEntre getParcela() {
		return inParcela;
	}

	public EntradaNumeroEntre getFila() {
		return inFila;
	}

	public EntradaNumeroEntre getUnidad() {
		return inUnidad;
	}

	public EntradaNumeroEntre getNicho() {
		return inNicho;
	}

	public EntradaNumeroEntre getMueble() {
		return inMueble;
	}

	public EntradaNumeroEntre getSepultura() {
		return inSepultura;
	}

	public EntradaNumeroEntre getBoveda() {
		return inBoveda;
	}

	public EntradaTexto getSeccion() {
		return inSeccion;
	}

	public JCheckBox getInCheck_macizoBis() {
		return inCheckMacizo_bis;
	}
	
	public JCheckBox getInCheckBis() {
		return inCheckbis;
	}
	
	public JCheckBox getInCheckMostrarTodo() {
		return inCheckMostrarTodo;
	}
	
	public Boton botonBuscar() {
		return btnBuscar;
	}

	public Boton botonLimpiar() {
		return btnLimpiar;
	}
	
}