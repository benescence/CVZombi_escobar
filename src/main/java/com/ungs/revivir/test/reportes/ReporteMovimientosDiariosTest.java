package com.ungs.revivir.test.reportes;

import java.sql.Date;
import java.time.LocalDate;

import com.ungs.revivir.vista.reportes.ReporteMovimientosDiarios;

public class ReporteMovimientosDiariosTest {

	public static void main(String[] args) {
		Date fecha = Date.valueOf(LocalDate.of(2019, 8, 10));
		new ReporteMovimientosDiarios(fecha);
	}

}