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


public class ReporteTotales extends Thread {
        private Empresa emp;	
        private String temporada;
        private String titulo;
        private String hec1añoP;
        private String hec2añoP;
        private String kg1añoP;
        private String kg2añoP;
        private String kg1AñoCE;
        private String kg2AñosCE;
        private String kg1AñoEntrega;
        private String kg2AñosEntrega;
        private String totalHecCensoPlantacion;
        private String totalKgCensoPlantacion;
	private String totalCensoEntrega;
        private String entrega1Año;
        private String entrega2Años;
        private String totalEntrega;
	
	public ReporteTotales(Empresa emp, String temporada, String titulo,
                double hec1añoP, double hec2añoP, double kg1añoP, double kg2añoP,
                double kg1AñoCE, double kg2AñosCE, double kg1AñoEntrega,
                double kg2AñosEntrega, double totalHecCensoPlantacion,
                double totalKgCensoPlantacion, double totalCensoEntrega,
                double entrega1Año, double entrega2Años, double totalEntrega) {
                this.emp = emp;
                this.temporada = temporada;
		this.titulo = titulo;
		this.hec1añoP = String.valueOf(new Double(hec1añoP).longValue());
                this.hec2añoP = String.valueOf(new Double(hec2añoP).longValue());
                this.kg1añoP = String.valueOf(new Double(kg1añoP).longValue());
                this.kg2añoP = String.valueOf(new Double(kg2añoP).longValue());
                this.kg1AñoCE = String.valueOf(new Double(kg1AñoCE).longValue());
                this.kg2AñosCE = String.valueOf(new Double(kg2AñosCE).longValue());
                this.kg1AñoEntrega = String.valueOf(new Double(kg1AñoEntrega).longValue());
                this.kg2AñosEntrega = String.valueOf(new Double(kg2AñosEntrega).longValue());
                this.totalHecCensoPlantacion = String.valueOf(new Double(totalHecCensoPlantacion).longValue());
                this.totalKgCensoPlantacion = String.valueOf(new Double(totalKgCensoPlantacion).longValue());
                this.totalCensoEntrega = String.valueOf(new Double(totalCensoEntrega).longValue());
                this.entrega1Año = String.valueOf(new Double(entrega1Año).longValue());
                this.entrega2Años = String.valueOf(new Double(entrega2Años).longValue());
                this.totalEntrega = String.valueOf(new Double(totalEntrega).longValue());

	}
	
	public void run(){
		try
		{
			//Ruta de Archivo Jasper
			String fileName="Reportes/Totales.jasper";
			//Obtner una conexi�n a la base de datos
			Connection con = Empresa.con.getConeccion();
			//Pasamos parametros al reporte Jasper.
			Map parameters = new HashMap();
			parameters.put("temporada", temporada);
			parameters.put("empresa", emp.getNombre());
			parameters.put("version", this.emp.getOptions().getVersion());
			parameters.put("direccion", emp.getDireccion());
			parameters.put("telefono", emp.getTelefono());
			parameters.put("titulo", titulo);
			parameters.put("hec1añoP", this.hec1añoP);
                        parameters.put("hec2añoP", this.hec2añoP);
                        parameters.put("kg1añoP", this.kg1añoP);
                        parameters.put("kg2añoP", this.kg2añoP);
                        parameters.put("kg1añoCE", this.kg1AñoCE);
                        parameters.put("kg2AñosCE", this.kg2AñosCE);
                        parameters.put("kg1AñoEntrega", this.kg1AñoEntrega);
                        parameters.put("kg2AñosEntrega", this.kg2AñosEntrega);
                        parameters.put("totalHecCensoPlantacion", this.totalHecCensoPlantacion);
                        parameters.put("TotalCensoPlantacion", this.totalKgCensoPlantacion);
                        parameters.put("totalCensoEntrega", this.totalCensoEntrega);
                        parameters.put("entrega1Año", this.entrega1Año);
                        parameters.put("entrega2Años", this.entrega2Años);
                        parameters.put("totalEntregas", this.totalEntrega);
                        
			//Preparacion del reporte (en esta etapa llena el dise�o de reporte)
			//Reporte dise�ado y compilado con iReport
			JasperPrint jasperPrint = JasperFillManager.fillReport(fileName,parameters,con);
			//Se lanza el Viewer de Jasper, no termina aplicaci�n al salir
			JasperViewer jviewer = new JasperViewer(jasperPrint,false);
                        jviewer.setTitle("Reporte Totales");
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

