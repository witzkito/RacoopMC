package Reportes;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import base.Empresa;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ReporteToleranciaIndividual extends Thread {
        private Empresa emp;	
        private String titulo;
        private String temporada;
	
	
	public ReporteToleranciaIndividual(Empresa emp, String titulo, String temporada) {
                this.emp = emp;
               	this.titulo = titulo;
                this.temporada = temporada;
		
	}
	
	public void run(){
		try
		{
			//Ruta de Archivo Jasper
			String fileName="Reportes/ToleranciaIndividual.jasper";
			//Obtner una conexion a la base de datos
			Connection con = Empresa.con.getConeccion();
			//Pasamos parametros al reporte Jasper.
			Map parameters = new HashMap();
			parameters.put("empresa", emp.getNombre());
			parameters.put("version", this.emp.getOptions().getVersion());
			parameters.put("direccion", emp.getDireccion());
			parameters.put("telefono", emp.getTelefono());
			parameters.put("titulo", titulo);
			parameters.put("temporada",this.temporada);
			//Preparacion del reporte (en esta etapa llena el dise�o de reporte)
			//Reporte dise�ado y compilado con iReport
			JasperPrint jasperPrint = JasperFillManager.fillReport(fileName,parameters,con);
			//Se lanza el Viewer de Jasper, no termina aplicaci�n al salir
			JasperViewer jviewer = new JasperViewer(jasperPrint,false);
                        jviewer.setTitle("Reporte Tolerancia Individual");
                        jviewer.setIconImage(this.emp.getOptions().getIcono().getImage());
                        jviewer.setVisible(true);
                }
		catch (Exception j)
		{
                    JOptionPane.showMessageDialog(new JFrame(),"Error en el Reporte: "+ 
                            j.getMessage() + "-" + j.getCause(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
