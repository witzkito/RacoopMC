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

public class ReporteCensoEntrega extends Thread {
	Socio unSocio;
	String temporada;
        CensoEntrega censo;
	private String titulo;
	private Empresa emp;
	
	public ReporteCensoEntrega(Socio unSocio, CensoEntrega censo, String temporada,Empresa emp, String titulo) {
		this.unSocio = unSocio;
		this.temporada = temporada;
		this.titulo = titulo;
		this.emp = emp;
                this.censo = censo;
	}
	
	public void run(){
		try
		{
			//Ruta de Archivo Jasper
			String fileName="Reportes/CensoEntrega.jasper";
			//Obtner una conexi�n a la base de datos
			Connection con = Empresa.con.getConeccion();
			//Pasamos parametros al reporte Jasper.
			Map parameters = new HashMap();
			parameters.put("nroSo", String.valueOf(unSocio.getNroSocio()));
			parameters.put("nombre", unSocio.getNomApe());
			parameters.put("temporada", temporada);
			parameters.put("empresa", emp.getNombre());
			parameters.put("version", this.emp.getOptions().getVersion());
			parameters.put("direccion", emp.getDireccion());
			parameters.put("telefono", emp.getTelefono());
			parameters.put("titulo", titulo);
                        parameters.put("nroCenso", String.valueOf(censo.getNumero()));
                        parameters.put("nroOrdenCenso", censo.getNroCenso());
			
			//Preparacion del reporte (en esta etapa llena el dise�o de reporte)
			//Reporte dise�ado y compilado con iReport
			JasperPrint jasperPrint = JasperFillManager.fillReport(fileName,parameters,con);
			//Se lanza el Viewer de Jasper, no termina aplicaci�n al salir
                        JasperViewer jviewer = new JasperViewer(jasperPrint,false);
			jviewer.setTitle("Reporte Censo Plantacion");
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

