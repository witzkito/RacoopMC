package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import base.CensoPlantacion;
import base.Fecha_wi;
import base.Socio;

/**
 * @author Adrian
 * <p><b>Descripcion:</b></p>
 * Clase encargada de almacenar los CensoPlantacion dento de la base de datos
 *
 */
public class DbCensoPlantacion {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbCensoPlantacion(Coneccion con){
		this.con = con;
	}
	
	//Metodos
	
	/**
	 * Inserta un CensoPlantacion dentro de la base de datos
	 * @param Socio unSocio
	 * @param CensoPlantacion censo
	 */
	public void Insert(Socio unSocio, CensoPlantacion censo){ 
		String sql;
		sql = "INSERT INTO CensosPlantaciones VALUES( " + censo.getNumero() + " , '" + censo.getFecha_wi().getDia() + "', '" + censo.getFecha_wi().getMes() + 
		"', '" + censo.getFecha_wi().getAño() + "', '" + censo.getTemporada() + "', " + unSocio.getNroSocio() + " )";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbCensoPlantacion - " + ex);
		}
	}
	
	/**
	 * Inserta un Map de CensoPlantaciones dentro de la base de datos
	 * @param Socio unSocio
	 * @param Map unMap
	 */
	public void InsertAll(Socio unSocio, Map unMap){
		Iterator it = unMap.values().iterator();
		while (it.hasNext()){
			this.Insert(unSocio,(CensoPlantacion)it.next());
		}
	}
	
	/**
	 * Trae todos los Censos plantaciones de un socio
	 * @param Socio unSocio
	 * @return Map
	 */
	public Map getCensosPlantaciones(Socio unSocio){
		ResultSet re; String sql; Map retornar = new HashMap(); String temporada; String dia; String mes;
		CensoPlantacion censo; long numero; String año; Fecha_wi fecha = new Fecha_wi(); 
		sql = "SELECT * FROM CensosPlantaciones where nroSocio = " + unSocio.getNroSocio();
		try{
			re = con.select(sql);
			while (re.next()){
				numero = re.getLong(1);
				dia = re.getString(2);
				mes = re.getString(3);
				año = re.getString(4);
				fecha.asignar_fecha_hora(dia, mes, año, "00", "00", "00");
				temporada = re.getString(5);
				censo = new CensoPlantacion(numero, fecha, temporada);
				retornar.put(censo.getNumero(), censo);
			}
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbCensoPlantacion - " + ex);
		}
		return retornar;
	}
	
	/**
	 * Modifica un CensoPlantacion en la base de datos
	 * @param Socio unSocio
	 * @param CensoPlantacion censoPlan
	 */
	public void actualizar(Socio unSocio, CensoPlantacion censoPlan){
		String sql;
		sql = "UPDATE CensosPlantaciones set dia = '" + censoPlan.getFecha_wi().getDia() + "', mes = '" + censoPlan.getFecha_wi().getMes() + "',  anio = '" + censoPlan.getFecha_wi().getAño() + 
		"', temporada = '" + censoPlan.getTemporada() + "' WHERE numero = " + censoPlan.getNumero() + " and nroSocio = " + unSocio.getNroSocio(); 
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbCensoPlantacion - " + ex.getMessage());
		}
	}
	
	/**
	 * Borra un CensoPlantacion de la base de datos
	 * @param Socio unSocio
	 * @param CensoPlantacion censoPla
	 */
	public void borrar(Socio unSocio, CensoPlantacion censoPla){
		String sql;
		sql = "DELETE FROM CensosPlantaciones WHERE numero = " + censoPla.getNumero() + " and nroSocio = " + unSocio.getNroSocio();  
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbCensoPlantacion - " + ex.getMessage());
		}
	}
	
	/**
	 * Trae un boolean que representa que existe y que esta correcta la tabla
	 * @return boolean
	 */
	public boolean estado(){
		String sql;
		sql = "SELECT * FROM CensosPlantaciones";
		try{
			con.select(sql);
			return true;
		}catch(Exception ex){
			System.out.println("Error comprobando tabla " + ex);
			return false;
		}
	}
}

