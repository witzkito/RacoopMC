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


public class ReporteSociosCensos extends Thread {
        private Empresa emp;	
        private String temporada;
        private String titulo;
        private int nroRep;
      
	
	
	public ReporteSociosCensos(Empresa emp, String temporada, String titulo, int nroRep) {
                this.emp = emp;
                this.temporada = temporada;
		this.titulo = titulo;
                this.nroRep = nroRep;
		
	}
	
	public void run(){
		try
		{
			String fileName= " "; String tituloRep = " ";
                        Connection con = Empresa.con.getConeccion();
                        Map parameters = new HashMap();
                        parameters.put("temporada", temporada);
                        parameters.put("empresa", emp.getNombre());
			parameters.put("version", this.emp.getOptions().getVersion());
			parameters.put("direccion", emp.getDireccion());
			parameters.put("telefono", emp.getTelefono());
                        parameters.put("titulo", titulo);
			if (nroRep == 1){
                            fileName="Reportes/SociosConCensoPlantacion.jasper";
                            tituloRep = "Reporte Socios con Censo Plantacion";
                        }else if (nroRep == 2){
                            fileName="Reportes/SociosSinCensoPlantacion.jasper";
                            tituloRep = "Reporte Socios Sin Censo Plantacion";
                        }else if (nroRep == 3){
                            fileName="Reportes/SociosConCensoEntregas.jasper";
                            tituloRep = "Reporte Socios Con Censo Entrega";
                        }else{
                            fileName="Reportes/SociosSinCensoEntrega.jasper";
                            tituloRep = "Reporte Socios Sin Censo Entrega";
                        }			
			//Preparacion del reporte (en esta etapa llena el dise�o de reporte)
			//Reporte dise�ado y compilado con iReport
			JasperPrint jasperPrint = JasperFillManager.fillReport(fileName,parameters,con);
			//Se lanza el Viewer de Jasper, no termina aplicaci�n al salir
			JasperViewer jviewer = new JasperViewer(jasperPrint,false);
                        jviewer.setTitle(tituloRep);
                        jviewer.setIconImage(this.emp.getOptions().getIcono().getImage());
			jviewer.setVisible(true);
		}
		catch (Exception j)
		{
                    JOptionPane.showMessageDialog(new JFrame(),"Error en el Reporte: "+ 
                            j.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		finally{
			/*conexion.closeConecction();*/
		}
	}
}

