package com.ungs.revivir.persistencia.mysql.vista;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.Definido;
import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.vista.VClienteNotificacion;
import com.ungs.revivir.persistencia.interfaces.vista.ClienteNotificacionVOBD;

public class ClienteNotificacionVOBDMySQL extends OBD implements ClienteNotificacionVOBD {
	private final String campos = "cli_nombre,cli_apellido,cli_dni, cli_telefono,domicilio,email,ubicacion, DNI, "
			+ "apellido, nombre, fecha_fallecimiento, tipo_fallecimiento, cod_fallecido, "
			+ "cocheria, fecha_ingreso, subsector, cementerio, nicho, fila, seccion, "
			+ "sepultura, boveda, pozo, vencimiento";
	private final String tabla = "rev_v_cliente_notificaciones";
		
	@Override
	public List<VClienteNotificacion> selectByNombreApellidoCOD(String nombre, String apellido, Integer codFallecido) {
		String condicion = "";
		if (nombre != null)
			condicion += "upper(nombre) like '"+nombre.toUpperCase()+"%'";
		
		if (apellido != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "upper(apellido) like '"+apellido.toUpperCase()+"%'";
		}
		
		if (codFallecido != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "cod_fallecido like '"+codFallecido+"%'";
		}
		
		return selectByCondicion(condicion);
	}
	
	private List<VClienteNotificacion> selectByCondicion(String condicion) {
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		return selectSQL(comandoSQL);
	}
	
	private List<VClienteNotificacion> selectByCondicion(String condicion, int limite) {
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		return selectSQL(comandoSQL);
	}
	
	public VClienteNotificacion selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<VClienteNotificacion> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public List<VClienteNotificacion> selectBySubsectorEntreFechas(SubSector subSector, Date desde, Date hasta) {
		String condicion = "subsector = "+Definido.subsector(subSector)
			+ " and vencimiento between '"+desde+"' and '"+hasta+"'";
		return selectByCondicion(condicion, limite);
	}

	@Override
	public List<VClienteNotificacion> selectBySubsectorEntreFechasSinLimite(SubSector subSector, Date desde, Date hasta) {
		String condicion = "subsector = "+Definido.subsector(subSector)
			+ " and vencimiento between '"+desde+"' and '"+hasta+"'";
		return selectByCondicion(condicion, 1000000);
	}
	
	// METODOS PRIVADOS
	
	private List<VClienteNotificacion> selectSQL(String SQL) {
		List<VClienteNotificacion> ret = new ArrayList<VClienteNotificacion>();
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(SQL);			

			while (resultados.next()) {
				Integer fila = resultados.getInt("fila");
				fila = (resultados.wasNull())? null: fila;
				
				Integer nicho = resultados.getInt("nicho");
				nicho = (resultados.wasNull())? null: nicho;

				
				Integer sepultura = resultados.getInt("sepultura");
				sepultura = (resultados.wasNull())? null: sepultura;
				
				Integer boveda = resultados.getInt("boveda");
				boveda = (resultados.wasNull())? null: boveda;

				Integer pozo = resultados.getInt("pozo");
				pozo = (resultados.wasNull())? null: pozo;

				
				LocalDate vencimientoLocal = (LocalDate) resultados.getObject("vencimiento", LocalDate.class);
				Date vencimiento = vencimientoLocal == null ? null : Date.valueOf(vencimientoLocal);
				
				ret.add(new VClienteNotificacion(
						resultados.getInt("ID"),
						resultados.getString("cli_nombre"),
						resultados.getString("cli_apellido"),
						resultados.getString("cli_dni"),
						resultados.getString("cli_telefono"),
						resultados.getString("domicilio"),
						resultados.getString("email"),
					
						resultados.getInt("ubicacion"),
						Definido.tipoFallecimiento(resultados.getInt("tipo_fallecimiento")),
						resultados.getInt("cod_fallecido"),
						resultados.getString("cod_fallecido"), // revisar, deberia ir DNI
						resultados.getString("apellido"),
						resultados.getString("nombre"),
						resultados.getString("cocheria"),
						resultados.getDate("fecha_fallecimiento"),
						resultados.getDate("fecha_ingreso"),
						Definido.subsector(resultados.getInt("subsector")),
						resultados.getString("cementerio"),
						nicho,
						fila,
						resultados.getString("seccion"),
						sepultura,
						boveda,
						pozo,
						vencimiento
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(SQL);
			e.printStackTrace();
		}
			
		return ret;
	}

}