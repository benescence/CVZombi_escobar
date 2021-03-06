package com.ungs.revivir.persistencia.mysql;

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
import com.ungs.revivir.persistencia.interfaces.UbicacionTotalOBD;

public class UbicacionTotalOBDMySQL extends OBD implements UbicacionTotalOBD {
	
	private final String campos = "subsector, nicho, fila,"
			+ " seccion, sepultura, boveda, pozo";
	private final String tabla = "rev_ubicaciones_totales";
	

	@Override
	public List<Ubicacion> selectByrangos(
									Integer nichoMax, Integer nichoMin,
									Integer pozoMax, Integer pozoMin,
									Integer filaMax, Integer filaMin,
									Integer sepulturaMax, Integer sepulturaMin,
									Integer bovedaMax, Integer bovedaMin,
									String seccion,
									SubSector subsector) {
			
		String condicionSubsector =" subsector = " + Definido.subsector(subsector);
		String condicionSeccion = (seccion != null) ? (" and "+  "seccion = '" + seccion + "'") : "";
		String condicionNichoMin = (nichoMin != null ) ? (" and nicho >= "+nichoMin ): "";
		String condicionNichoMax = ( nichoMax != null) ? (" and nicho <= " + nichoMax) : "";
		String condicionFilaMax = (filaMax != null) ? ("  and fila <= " + filaMax) : "";
		String condicionFilaMin = (filaMin != null ) ? (" and fila >= "+filaMin) : "";
		String condicionpozoMax = (pozoMax != null) ? (" and pozo <= " + pozoMax) : "";
		String condicionpozoMin = (pozoMin != null) ? (" and pozo >= "+pozoMin ) : "";
		String condicionbovedaMax = (bovedaMax != null) ? (" and boveda <= " + bovedaMax) : "";
		String condicionbovedaMin = (bovedaMin != null) ? (" and boveda >= "+bovedaMin) : "";
		String condicionSepulturaMax = (sepulturaMax != null) ? ("  and sepultura <= " + sepulturaMax) : "";
		String condicionSepulturaMin = (sepulturaMin != null) ? (" and sepultura >= "+sepulturaMin ) : "";
		String condicion =  condicionSubsector 
							+ condicionSeccion
							+ condicionNichoMax
							+ condicionNichoMin
							+ condicionFilaMin
							+ condicionFilaMax
							+ condicionpozoMax
							+ condicionpozoMin
							+ condicionbovedaMax
							+ condicionbovedaMin
							+ condicionSepulturaMax 
							+ condicionSepulturaMin;
		return selectByCondicion(condicion, limite);		
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
				
				ret.add(
					new Ubicacion(
						resultados.getInt("ID"),
						Definido.subsector(resultados.getInt("subsector")),
						"",
						nicho,
						fila,
						resultados.getString("seccion"),
						sepultura,
						boveda,
						pozo,
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
}
