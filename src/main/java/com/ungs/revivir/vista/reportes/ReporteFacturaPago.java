package com.ungs.revivir.vista.reportes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.util.Formato;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.text.SimpleDateFormat;

public class ReporteFacturaPago {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;

	public ReporteFacturaPago(List<Pago> pagos) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(Almanaque.hoy());
    	Map <String, Object> parametros = new HashMap<String, Object>();
    	Pago pago = pagos.get(0);
    	parametros.put("fecha", fecha );
    	parametros.put("cargo",Formato.servicioDesdeCargo(pago));
    	parametros.put("monto", pago.getImporte());
    	parametros.put("observaciones", pago.getObservaciones());
    	parametros.put("fallecido", Formato.fallecido(pagos.get(0)));
    	parametros.put("ubicacion", Formato.ubicaciondesdePago(pagos.get(0)));
    	parametros.put("DNIFallecido", Formato.DNIfallecido(pagos.get(0)));
    	
    	try	{
    		
        	this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\FacturaPago.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametros, 
					new JRBeanCollectionDataSource(pagos));
    		System.out.println("Sere cargo correctamente la factura de pago.");
		}
		
    	catch( JRException ex )	{
			System.out.println("Ocurrio un error mientras se cargaba el archivo FacturaPago.Jasper \n "+ex);
		}
    	
    }       
	   
    public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}