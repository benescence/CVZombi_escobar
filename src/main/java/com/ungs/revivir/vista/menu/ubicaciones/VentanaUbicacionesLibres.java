package com.ungs.revivir.vista.menu.ubicaciones;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.tablas.TablaUbicacionesLibres;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaNumeroEntre;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaUbicacionesLibres extends VentanaInterna{
	private static final long serialVersionUID = 1L;
	private TablaUbicacionesLibres tabla;
	private EntradaLista<Sector> inSector;
	private EntradaLista<SubSector> inSubsector;
	private EntradaNumeroEntre inPozo, inFila, inNicho, inSepultura, inBoveda;
	private EntradaTexto inSeccion;
	private Boton btnBuscar, btnLimpiar;
	private JCheckBox inCheckMostrarTodo;
	
	public VentanaUbicacionesLibres() {
		super("Ubicaciones libres", 500, 500);
		Dimension dimBoton = new Dimension(100, 25);
		
		tabla = new TablaUbicacionesLibres (new ArrayList<Ubicacion>() );
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
		//inSubsector = new EntradaLista<>("Sub Sector", dimTexto, dimEntrada);
		
		for (Sector sector : Localizador.traerSectores())
			inSector.getComboBox().addItem(sector);
		
		/*inSector.getComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inSubsector.getComboBox().removeAllItems();
				Sector sector = (Sector) inSector.getComboBox().getSelectedItem();
				for (SubSector elemento : Localizador.traerSubSectores(sector))
					inSubsector.getComboBox().addItem(elemento);
			}
		});*/

		inSector.getComboBox().setSelectedIndex(0);
		//inSubsector.getComboBox().setSelectedIndex(0);

		// Inicializo el esto de las entradas
		inPozo = new EntradaNumeroEntre("Circ", dimTexto, dimEntradaDoble);
		inFila = new EntradaNumeroEntre("Fila", dimTexto, dimEntradaDoble);
		inNicho = new EntradaNumeroEntre("Nicho", dimTexto, dimEntradaDoble);
		inSepultura = new EntradaNumeroEntre("Sepultura", dimTexto, dimEntradaDoble);
		inBoveda= new EntradaNumeroEntre("Inhumacion", dimTexto, dimEntradaDoble);
		inSeccion = new EntradaTexto("Seccion", dimTexto, dimEntrada);
		inCheckMostrarTodo = new JCheckBox("MostrarTodo");
		
		
		PanelVertical ret1 = new PanelVertical();
		ret1.setBorder(new EmptyBorder(0, 0, 0, 10));
		ret1.add(inSector);
		//ret1.add(inSubsector);
		ret1.add(inPozo);
		ret1.add(inFila);
		
		PanelVertical ret2 = new PanelVertical();
		ret2.setBorder(new EmptyBorder(0, 10, 0, 0));
		ret2.add(inSeccion);
		ret2.add(inNicho);
		ret2.add(inSepultura);
		ret2.add(inBoveda);
		ret2.add(inCheckMostrarTodo);
		
		PanelHorizontal ret3 = new PanelHorizontal();
		ret3.add(ret1);
		ret3.add(ret2);
		
		
		PanelVertical ret = new PanelVertical();
		ret.add(ret3);
		return ret;
	}

	public TablaUbicacionesLibres getTabla() {
		return tabla;
	}

	public EntradaLista<Sector> getSector() {
		return inSector;
	}

	public EntradaLista<SubSector> getSubsector() {
		return inSubsector;
	}

	public EntradaNumeroEntre getCirc() {
		return inPozo;
	}



	public EntradaNumeroEntre getFila() {
		return inFila;
	}


	public EntradaNumeroEntre getNicho() {
		return inNicho;
	}



	public EntradaNumeroEntre getSepultura() {
		return inSepultura;
	}

	public EntradaNumeroEntre getInhumacion() {
		return inBoveda;
	}

	public EntradaTexto getSeccion() {
		return inSeccion;
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