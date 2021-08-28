package com.ungs.revivir.persistencia.mysql;

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
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class UbicacionOBDMySQL extends OBD implements UbicacionOBD{
	private final String campos = "subsector, cementerio, nicho, fila,"
			+ "seccion, sepultura, pozo, boveda, vencimiento";
	private final String tabla = "rev_ubicaciones";
	
	@Override
	public void insert(Ubicacion ubicacion) {
		String cementerio = (ubicacion.getCementerio() != null) ? "'"+ubicacion.getCementerio()+"'" : null;
		String seccion = (ubicacion.getSeccion() != null) ? "'"+ubicacion.getSeccion()+"'" : null;
		String vencimiento = (ubicacion.getVencimiento() != null) ? "'"+ubicacion.getVencimiento()+"'" : null;
		
		String valores = Definido.subsector(ubicacion.getSubsector())
				+", "+cementerio
				+", "+ubicacion.getNicho()
				+", "+ubicacion.getFila()
				+", "+seccion
				+", "+ubicacion.getSepultura()
				+", "+ubicacion.getPozo()
				+", "+ubicacion.getBoveda()
				+", "+vencimiento;
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Ubicacion ubicacion) {
		String cementerio = (ubicacion.getCementerio() != null) ? "'"+ubicacion.getCementerio()+"'" : null;
		String seccion = (ubicacion.getSeccion() != null) ? "'"+ubicacion.getSeccion()+"'" : null;
		String vencimiento = (ubicacion.getVencimiento() != null) ? "'"+ubicacion.getVencimiento()+"'" : null;
		
		String condicion = "ID = "+ubicacion.getID();
		String valores = " subsector = "+Definido.subsector(ubicacion.getSubsector())
				+", cementerio = "+cementerio
				+", nicho = "+ubicacion.getNicho()
				+", fila = "+ubicacion.getFila()
				+", seccion = "+seccion
				+", sepultura = "+ubicacion.getSepultura()
				+", pozo = "+ubicacion.getPozo()
				+", boveda = "+ubicacion.getBoveda()
				+", vencimiento = "+vencimiento;
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Ubicacion ubicacion) {
		String condicion = "ID = "+ubicacion.getID();
		String consulta = " delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);	
	}

	@Override
	public Ubicacion selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<Ubicacion> lista = selectByCondicion(condicion, limite);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public Ubicacion  selectByUbicacion(Ubicacion ubicacion) {
			
		String condicion =" subsector = "+Definido.subsector(ubicacion.getSubsector())
				+" and nicho "+((ubicacion.getNicho() != null) ? (" = " +ubicacion.getNicho()) : " is null")
				+" and fila "+((ubicacion.getFila() != null) ? (" = " +ubicacion.getFila()) : " is null")
				+" and seccion" + ((ubicacion.getSeccion() != null) ? (" = "+ubicacion.getSeccion()) :  " is null")
				+" and sepultura" + ((ubicacion.getSepultura() != null) ? (" = "+ubicacion.getSepultura()) :  " is null")
				+" and pozo" + ((ubicacion.getPozo() != null) ? (" = "+ubicacion.getPozo()) :  " is null")
				+" and boveda" + ((ubicacion.getBoveda() != null) ? (" = "+ubicacion.getBoveda()) :  " is null");
				
		List<Ubicacion> lista = selectByCondicion(condicion, limite);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
	@Override
	public List<Ubicacion> selectByrangos(Integer nichoMax,
									Integer nichoMin,
									Integer pozoMax,
									Integer pozoMin,
									Integer filaMax,
									Integer filaMin,
									Integer sepulturaMax,
									Integer sepulturaMin,
									Integer bovedaMax,
									Integer bovedaMin,
									String seccion,
									SubSector subsector) {
			
		String condicionSubsector =" subsector = "+Definido.subsector(subsector);
		String condicionSeccion = (seccion != null) ? (" and "+  "seccion = '" + seccion+"'") : "";
		String condicionNicho = (nichoMin!= null && nichoMax != null) ? (" and nicho > "+nichoMin +" and nicho <" + nichoMax) : "";
		String condicionFila = (filaMin!= null && filaMax != null) ? (" and fila > "+filaMin +" and fila <" + filaMax) : "";
		String condicionPozo = (pozoMin!= null && pozoMax != null) ? (" and boveda > "+pozoMin +" and boveda < " + pozoMax) : "";
		String condicionboveda = (bovedaMin!= null && bovedaMax != null) ? (" and pozo > "+bovedaMin +" and pozo <" + bovedaMax) : "";
		String condicionSepultura = (sepulturaMin!= null && sepulturaMax != null) ? (" and nicho > "+sepulturaMin +" and nicho <" + sepulturaMax) : "";
		
		String condicion =  condicionSubsector 
							+ condicionSeccion
							+ condicionNicho
							+ condicionFila
							+ condicionPozo
							+ condicionboveda
							+ condicionSepultura ;
		
		return selectByCondicion(condicion, limite);		
	}
	
	@Override
	public Ubicacion ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}

	@Override
	public Ubicacion selectByFallecido(Fallecido fallecido) {
		String condicion = "ID = "+fallecido.getUbicacion();
		List<Ubicacion> lista = selectByCondicion(condicion, limite);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
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
				
				Integer boveda = resultados.getInt("pozo");
				boveda = (resultados.wasNull())? null: boveda;

				Integer pozo = resultados.getInt("boveda");
				pozo = (resultados.wasNull())? null: pozo;

				ret.add(new Ubicacion(
						resultados.getInt("ID"),
						Definido.subsector(resultados.getInt("subsector")),
						resultados.getString("cementerio"),
						nicho,
						fila,
						resultados.getString("seccion"),
						sepultura,
						boveda,
						pozo,
						Date.valueOf(resultados.getObject("vencimiento", LocalDate.class))
					));
			}

			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return ret;
	}
	
	@Override
	public List<Ubicacion> selectBySubsectorEntreFechas(SubSector subSector, Date desde, Date hasta) {
		String condicion = "subsector = "+Definido.subsector(subSector)
			+ " and vencimiento between '"+desde+"' and '"+hasta+"'";
		return selectByCondicion(condicion, limite);
	}
	
	@Override
	public List<Ubicacion> selectBySubsectorEntreFechasSinLimite(SubSector subSector, Date desde, Date hasta) {
		String condicion = "subsector = "+Definido.subsector(subSector)
			+ " and vencimiento between '"+desde+"' and '"+hasta+"'";
		return selectByCondicion(condicion, 1000000);
	}
	
}