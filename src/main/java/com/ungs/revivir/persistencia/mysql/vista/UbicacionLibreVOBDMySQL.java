package com.ungs.revivir.persistencia.mysql.vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.revivir.persistencia.Definido;
import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.vista.UbicacionLibreVOBD;

public class UbicacionLibreVOBDMySQL extends OBD implements UbicacionLibreVOBD {
	private final String campos = "subsector, nicho, fila,"
			+ "seccion, sepultura, boveda, pozo";
	private final String tabla = "rev_v_ubicaciones_libres";
	
	@Override
	public List<Ubicacion> select() {
		return selectByCondicion("true", limite);
	}
	
	private List<Ubicacion> selectByCondicion(String condicion, int limite) {
		List<Ubicacion> ret = new ArrayList<>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				
				Integer fila = resultados.getInt("fila");
				fila = (resultados.wasNull())? null: fila;
				
				Integer nicho = resultados.getInt("nicho");
				nicho = (resultados.wasNull())? null: nicho;

				Integer sepultura = resultados.getInt("sepultura");
				sepultura = (resultados.wasNull())? null: sepultura;
				
				Integer inhumacion = resultados.getInt("boveda");
				inhumacion = (resultados.wasNull())? null: inhumacion;

				Integer circ = resultados.getInt("pozo");
				circ = (resultados.wasNull())? null: circ;
				
				ret.add(
					new Ubicacion(
						resultados.getInt("ID"),
						Definido.subsector(resultados.getInt("subsector")),
						"",
						nicho,
						fila,
						resultados.getString("seccion"),
						sepultura,
						inhumacion,
						circ,
						null
					)
				);
				
			}

			resultados.close();
			sentencia.close();
			conexion.close();
			
		} catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return ret;
	}
	
	@Override
	public List<Ubicacion> selectByrangos(
									Integer nichoMax, Integer nichoMin,
									Integer circMax, Integer circMin,
									Integer filaMax, Integer filaMin,
									Integer sepulturaMax, Integer sepulturaMin,
									Integer inhumacionMax, Integer inhumacionMin,
									String seccion,
									SubSector subsector) {
			
		String condicionSubsector =" subsector = " + Definido.subsector(subsector);
		String condicionSeccion = (seccion != null) ? (" and "+  "seccion = '" + seccion + "'") : "";
		String condicionNichoMin = (nichoMin!= null ) ? (" and nicho >= "+nichoMin ): "";
		String condicionNichoMax = ( nichoMax != null) ? (" and nicho <= " + nichoMax) : "";
		String condicionFilaMax = (filaMax != null) ? ("  and fila <= " + filaMax) : "";
		String condicionFilaMin = (filaMin!= null ) ? (" and fila >= "+filaMin) : "";
		String condicionCircMax = (circMax != null) ? (" and pozo <= " + circMax) : "";
		String condicionCircMin = (circMin!= null) ? (" and pozo >= "+circMin ) : "";
		String condicioninhumacionMax = (inhumacionMax != null) ? ("  and boveda <= " + inhumacionMax) : "";
		String condicioninhumacionMin = (inhumacionMin!= null) ? (" and boveda >= "+inhumacionMin) : "";
		String condicionSepulturaMax = (sepulturaMax != null) ? ("  and sepultura <= " + sepulturaMax) : "";
		String condicionSepulturaMin = (sepulturaMin!= null) ? (" and sepultura >= "+sepulturaMin ) : "";
		String condicion =  condicionSubsector 
							+ condicionSeccion
							+ condicionNichoMax
							+ condicionNichoMin
							+ condicionFilaMin
							+ condicionFilaMax
							+ condicionCircMax
							+ condicionCircMin
							+ condicioninhumacionMax
							+ condicioninhumacionMin
							+ condicionSepulturaMax 
							+ condicionSepulturaMin;

		return selectByCondicion(condicion, limite);		
	}

}