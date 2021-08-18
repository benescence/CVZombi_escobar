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
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;
import com.ungs.revivir.persistencia.interfaces.vista.FallecidoUbicacionVOBD;

public class FallecidoUbicacionVOBDMySQL extends OBD implements FallecidoUbicacionVOBD{
	private final String campos = " ubicacion, DNI, apellido, nombre, fecha_fallecimiento, tipo_fallecimiento, cod_fallecido, "
			+ "cocheria, fecha_ingreso, subsector, cementerio, nicho, fila, seccion, macizo, unidad, bis, bis_macizo, "
			+ "sepultura, parcela, mueble, boveda, pozo, vencimiento";
	private final String tabla = "rev_v_fallecidos";
		
	@Override
	public List<VFallecidoUbicacion> selectByNombreApellidoCOD(String nombre, String apellido, Integer codFallecido) {
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
	
	private List<VFallecidoUbicacion> selectByCondicion(String condicion) {
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		return selectSQL(comandoSQL);
	}
	
	private List<VFallecidoUbicacion> selectByCondicion(String condicion, int limite) {
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		return selectSQL(comandoSQL);
	}
	
	public VFallecidoUbicacion selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<VFallecidoUbicacion> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public List<VFallecidoUbicacion> selectBySubsectorEntreFechas(SubSector subSector, Date desde, Date hasta) {
		String condicion = "subsector = "+Definido.subsector(subSector)
			+ " and vencimiento between '"+desde+"' and '"+hasta+"'";
		return selectByCondicion(condicion, limite);
	}

	@Override
	public List<VFallecidoUbicacion> selectBySubsectorEntreFechasSinLimite(SubSector subSector, Date desde, Date hasta) {
		String condicion = "subsector = "+Definido.subsector(subSector)
			+ " and vencimiento between '"+desde+"' and '"+hasta+"'";
		return selectByCondicion(condicion, 1000000);
	}
	
	// METODOS PRIVADOS
	
	private List<VFallecidoUbicacion> selectSQL(String SQL) {
		List<VFallecidoUbicacion> ret = new ArrayList<VFallecidoUbicacion>();
		
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

				Integer macizo = resultados.getInt("macizo");
				macizo = (resultados.wasNull())? null: macizo;
				
				Integer unidad = resultados.getInt("unidad");
				unidad = (resultados.wasNull())? null: unidad;
				
				Integer sepultura = resultados.getInt("sepultura");
				sepultura = (resultados.wasNull())? null: sepultura;
				
				Integer parcela = resultados.getInt("parcela");
				parcela = (resultados.wasNull())? null: parcela;
				
				Integer mueble = resultados.getInt("mueble");
				mueble = (resultados.wasNull())? null: mueble;
				
				Integer boveda = resultados.getInt("boveda");
				boveda = (resultados.wasNull())? null: boveda;

				Integer pozo = resultados.getInt("pozo");
				pozo = (resultados.wasNull())? null: pozo;

				Boolean bis = resultados.getBoolean("bis");
				bis = (resultados.wasNull())? null: bis;

				Boolean bisMacizo = resultados.getBoolean("bis_macizo");
				bisMacizo = (resultados.wasNull())? null: bisMacizo;
				
				LocalDate vencimientoLocal = (LocalDate) resultados.getObject("vencimiento", LocalDate.class);
				Date vencimiento = vencimientoLocal == null ? null : Date.valueOf(vencimientoLocal);
				
				ret.add(new VFallecidoUbicacion(
						resultados.getInt("ID"),
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
						macizo,
						unidad,
						bis,
						bisMacizo,
						sepultura,
						parcela,
						mueble,
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