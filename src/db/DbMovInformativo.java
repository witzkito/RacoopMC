package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import base.Fecha_wi;
import base.MovInformativo;
import base.Socio;

public class DbMovInformativo {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbMovInformativo(Coneccion con){
		this.con = con;
	}
	
	/**
	 * Inserta un Movimiento Informativo en la Base de Datos
	 * @param MovInformativo
	 * @param Socio
	 */
	public void Insert(MovInformativo unMov, Socio unSocio){ 
		String sql;
		sql = "INSERT INTO MovInformativos VALUES(" + unMov.getNumero() + ", '" + unMov.getFecha().getDia() + "', '" + unMov.getFecha().getMes() + "', '" +
		unMov.getFecha().getAño() + "', '" + unMov.getDescripcion() + "', " + unSocio.getNroSocio() + ")";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbMovInformativo - " + ex);
		}
	}
	
	/**
	 * Inserta Varios Movimientos almacenados en un Map, en la base de datos
	 * @param Map de MovInformativo
	 * @param socio
	 */
	public void InsertAll(Map unMap, Socio unSocio){
		Iterator it = unMap.values().iterator(); MovInformativo unMov;
		String sql;
		while (it.hasNext()){
			unMov = (MovInformativo)it.next();
			sql = "INSERT INTO MovInformativos VALUES(" + unMov.getNumero() + ", '" + unMov.getFecha().getDia() + "', '" + unMov.getFecha().getMes() + "', '" +
					unMov.getFecha().getAño() + "', '" + unMov.getDescripcion() + "', " + unSocio.getNroSocio() + ")";
			try{
				con.insert(sql);
			}catch(Exception ex){
				System.out.println("Error Insertando todos los datos en la Base de Datos - DbMovInformativo - " + ex);
			}
		}
	}
	
	/**
	 * Trae los Movimientos Informatvos de un Socio
	 * @param Socio
	 * @return Map
	 */
	public Map getMovInformativos(Socio unSocio){
		ResultSet re; String sql; Map retornar = new HashMap();
		MovInformativo mo; long numero; Fecha_wi fecha = new Fecha_wi(); String dia; String mes; String año;
		String concepto; 
		sql = "SELECT * FROM MovInformativos WHERE nroSocio = " + unSocio.getNroSocio();
		try{
			re = con.select(sql);
			while (re.next()){
				fecha = new Fecha_wi();
				numero = re.getLong(1);
				dia = re.getString(2);
				if (dia.equals("null")){
					dia = "00";
				}
				mes = re.getString(3);
				if (mes.equals("null")){
					mes = "00";
				}
				año = re.getString(4);
				if (año.equals("null")){
					año = "0000";
				}
				fecha.asignar_fecha_hora(dia,mes, año, "00", "00", "00");
				concepto = re.getString(5);
				mo = new MovInformativo (numero, fecha, concepto);
				retornar.put(new Long(numero), mo);
			}
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbMovInformativo - " + ex);
		}
		return retornar;
	}
	
	/**
	 * Actualiza los datos de la Base de Datos
	 * @param MovInformativo
	 * @param Socio
	 */
	public void actualizar(MovInformativo unMov, Socio unSocio){
		String sql;
		sql = "UPDATE MovInformativos set dia = '" + unMov.getFecha().getDia() + "', mes = '" + unMov.getFecha().getMes() +
		"', anio = '" + unMov.getFecha().getAño() + "', descripcion = '" + unMov.getDescripcion() +  "' WHERE numero = " + unMov.getNumero() +
		" and nroSocio = " + unSocio.getNroSocio();
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbMovInformativo - " + ex.getMessage());
		}
	}
	
	public void borrar(MovInformativo unMov, Socio unSocio){
		String sql;
		sql = "DELETE FROM MovInformativos WHERE numero = " + unMov.getNumero() + " and nroSocio = " + unSocio.getNroSocio();
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando MovInformativos - DbMovInformativo - " + ex.getMessage());
		}
	}
	
	public void borrarPorNombre(MovInformativo unMov, Socio unSocio){
		String sql;
		sql = "DELETE FROM MovInformativos WHERE descripcion = '" + unMov.getDescripcion() + "' and nroSocio = " + unSocio.getNroSocio();
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando MovInformativos - DbMovInformativo por nombre - " + ex.getMessage());
		}
	}
	
}

