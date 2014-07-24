package Reportes;

import base.CensoEntrega;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import base.Empresa;
import base.Socio;
import base.Variedad;
import java.util.Date;

public class ReportePlanillaEntrega extends Thread {
	int numero;
        String nombre;
        String unaVariedad;
        String ciclo;
	private String titulo;
	private Empresa emp;
	
	public ReportePlanillaEntrega(int numero, String nombre, String unaVariedad, String ciclo,Empresa emp, String titulo) {
		this.numero = numero;
                this.nombre = nombre;
                this.unaVariedad = unaVariedad;
                this.ciclo = ciclo;
		this.titulo = titulo;
		this.emp = emp;
	}
	
	public void run(){
		try
		{
			//Ruta de Archivo Jasper
			String fileName="Reportes/PlanillaEntrega.jasper";
			//Obtner una conexi�n a la base de datos
			Connection con = Empresa.con.getConeccion();
			//Pasamos parametros al reporte Jasper.
                        java.util.Date utilDate = new java.util.Date();
                        long lnMilisegundos = utilDate.getTime();
                        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
                        
			Map parameters = new HashMap();
			parameters.put("numero_socio", String.valueOf(numero));
			parameters.put("nombre", "Socio " + nombre);
			parameters.put("variedad", this.unaVariedad);
                        parameters.put("ciclo", String.valueOf(ciclo));
                        parameters.put("titulo", titulo);
                        parameters.put("fecha",sqlTimestamp.toString());
                        
			//Preparacion del reporte (en esta etapa llena el dise�o de reporte)
			//Reporte dise�ado y compilado con iReport
			JasperPrint jasperPrint = JasperFillManager.fillReport(fileName,parameters,con);
			//Se lanza el Viewer de Jasper, no termina aplicaci�n al salir
                        JasperViewer jviewer = new JasperViewer(jasperPrint,false);
			jviewer.setTitle("Reporte Control de Materia Prima");
                        jviewer.setIconImage(this.emp.getOptions().getIcono().getImage());
                        jviewer.setVisible(true);
		}
		catch (Exception j)
		{
			System.out.println("Mensaje de Error:"+j.getMessage());
		}
		finally{
			/*conexion.closeConecction();*/
		}
	}
}

