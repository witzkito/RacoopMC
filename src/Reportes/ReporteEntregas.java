package Reportes;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import base.Empresa;
import base.Socio;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ReporteEntregas extends Thread {
        private Empresa emp;	
        private String titulo;
        private Socio unSocio;
	
	
	public ReporteEntregas(Empresa emp, String titulo, Socio unSocio) {
                this.emp = emp;
               	this.titulo = titulo;
                this.unSocio = unSocio;
		
	}
	
	public void run(){
		try
		{
			//Ruta de Archivo Jasper
			String fileName="Reportes/RepEntregas.jasper";
			//Obtner una conexion a la base de datos
			Connection con = Empresa.con.getConeccion();
			//Pasamos parametros al reporte Jasper.
			Map parameters = new HashMap();
			parameters.put("empresa", emp.getNombre());
			parameters.put("version", this.emp.getOptions().getVersion());
			parameters.put("direccion", emp.getDireccion());
			parameters.put("telefono", emp.getTelefono());
			parameters.put("titulo", titulo);
			parameters.put("nroSocio",String.valueOf(this.unSocio.getNroSocio()));
                        parameters.put("nombre", this.unSocio.getNomApe());
			//Preparacion del reporte (en esta etapa llena el diseño de reporte)
			//Reporte diseñado y compilado con iReport
			JasperPrint jasperPrint = JasperFillManager.fillReport(fileName,parameters,con);
			//Se lanza el Viewer de Jasper, no termina aplicaci�n al salir
			JasperViewer jviewer = new JasperViewer(jasperPrint,false);
                        jviewer.setTitle("Reporte Entregas Socio " + this.unSocio.getNroSocio()
                                + " " + unSocio.getNomApe());
                        jviewer.setIconImage(this.emp.getOptions().getIcono().getImage());
                        jviewer.setVisible(true);
                }
		catch (Exception j)
		{
                    JOptionPane.showMessageDialog(new JFrame(),"Error en el Reporte: "+ 
                            j.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}

