package db;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Gui.V_ImportarSocios;
import base.Fecha_wi;
import base.Socio;

import com.svcon.jdbf.DBFReader;



public class DbImportSocios {
	//Atributos
	
	private String ubicacion;
	private boolean estado;
	private JFrame panel;
	private DBFReader reader;
	
//Constructor
	public DbImportSocios(String ubicacion, JFrame panel){
		this.ubicacion = ubicacion;
		this.panel = panel;
		this.estado = true;
		
	}
	
//Metodos
	
	public Map getAllSocios(V_ImportarSocios importarS){ 
		Object[] so; long nroSocio; String nombre; String tipoDoc; String cuit; String iva; 
		Socio socio; String dni; String domicilio; String actaI; String actaE;
		String obser; Map retornar = new HashMap(); StringBuffer nSocio; String estado;
		Fecha_wi fechaI = new Fecha_wi(); Fecha_wi fechaE = new Fecha_wi();
                Fecha_wi fechaIn = new Fecha_wi(); fechaIn.asignar_fecha_hora("00", "00", "0000", "00", "00", "00");
		try{
			this.reader = new DBFReader(ubicacion);
			while (reader.hasNextRecord()){
				fechaI = new Fecha_wi();
				so = reader.nextRecord();
				nSocio = new StringBuffer(((Long)(so[0])).toString());
				if (((Long)(so[0])).toString().startsWith("10")){
					nSocio.delete(0, 2);
					estado = "activo";
				}else{
					estado = "baja";
				}
				nroSocio = nroSocio = new Long(nSocio.toString()).longValue();
				nombre = ((String)(so[1]));
				tipoDoc = ((String)(so[2]));
				cuit = ((String)(so[3]));
				iva = ((String)(so[4]));
				dni = ((Double)(so[5])).toString();
				domicilio = ((String)(so[6]));
				fechaI = traerFecha((Long)(so[7]));
				actaI = ((Long)(so[8])).toString();
				fechaE = traerFecha((Long)(so[9]));
				actaE = ((Long)(so[10])).toString();
				obser = ((String)(so[11])).toString() + " - " + ((String)(so[12])).toString() + " - " + ((String)(so[13])).toString();
				socio = new Socio(nroSocio, nombre, domicilio, tipoDoc, dni, cuit, iva, fechaI , actaI,fechaIn, " " , fechaE , actaE, obser ,estado);
				importarS.incrementarProgress(1);
				retornar.put(new Long(socio.getNroSocio()), socio);
			}
			reader.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(panel, "Error importando Socios " + ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
		}
		
		return retornar;
	}
	
	public Fecha_wi traerFecha(long nro){
		Fecha_wi fecha = new Fecha_wi();
		if (nro != 0){
			nro = nro - 36163;
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1900); 
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.DAY_OF_MONTH, new Long(nro).intValue());
			fecha.asignar_fecha_hora(new Integer(cal.get(Calendar.DAY_OF_MONTH)).toString(), new Integer(cal.get(Calendar.MONTH) + 1).toString(), new Integer(cal.get(Calendar.YEAR)).toString(), "00", "00", "00");
		}else{
			fecha.asignar_fecha_hora("00", "00", "0000", "00", "00", "00");
		}
		return fecha;
	}
	
		
	public long getCount(){
		long nro = 0; Object[] co;
		try{
			this.reader = new DBFReader(ubicacion);
			while (reader.hasNextRecord()){
				co = reader.nextRecord();
				nro = nro + 1;
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(panel, "Error importando Cantidad de Socios " + ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
			estado = false;
		}
		return nro;
	}
	
	
	
	public boolean getError(){ return this.estado;}
}
