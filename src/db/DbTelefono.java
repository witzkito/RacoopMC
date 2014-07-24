package db;

import base.Socio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import base.Telefono;

public class DbTelefono {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbTelefono(Coneccion con){
		this.con = con;
	}
	
	/**
	 * Inserta un Telefono en la Base de Datos
	 * @param ciudad Ciudad
         * @param Socio unSocio
	*/
	public void Insert(Telefono tel, Socio unSocio){ 
		String sql;
		sql = "INSERT INTO Telefonos VALUES('" + tel.getTipo() +
                "', '" + tel.getTelefono() + "', " + unSocio.getNroSocio() + ")";
		try{
                    con.insert(sql);
		}catch(Exception ex){
                    System.out.println("Error Insertando datos en la Base de Datos - DbTelefono - " + ex);
		}
	}
	
	/**
	 * Inserta Varios Telefonos almacenados en un Map, en la base de datos
	 * @param Map de Ciudades
         * @param Socio unSocio
	 */
	public void InsertAll(Map unMap, Socio unSocio){
		Iterator it = unMap.values().iterator(); Telefono tel;
		String sql;
		while (it.hasNext()){
                    tel = (Telefono)it.next();
			try{
                            this.Insert(tel, unSocio);
			}catch(Exception ex){
                            System.out.println("Error Insertando todos los datos en la Base de Datos - DbTelefono - " + ex);
			}
		}
	}
	
	/**
	 * Trae los Telefonos
	 * @return Map
	 */
	public Map getTelefonos(Socio unSocio){
		ResultSet re; String sql; Map retornar = new HashMap();
		Telefono unTel; String tipo;  String telefono;
		sql = "SELECT * FROM Telefonos WHERE nroSocio = " + unSocio.getNroSocio();
		try{
			re = con.select(sql);
			while (re.next()){
				tipo = re.getString(1);
				telefono = re.getString(2);
				unTel = new Telefono (tipo, telefono);
				retornar.put(unTel.getTipo(),unTel);
			}
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbTelefono - " + ex);
		}
		return retornar;
	}
	
	/**
	 * Actualiza el Telefono de la Base de Datos
	 * @param Telefono unTelefono
         * @param Socio unSocio
	 */
	public void actualizar(Telefono unTel, Socio unSocio){
		String sql;
		sql = "UPDATE Telefonos set telefono = '" + unTel.getTelefono() +
                        "' WHERE tipo = " + unTel.getTipo() + " and nroSocio =" + 
                        unSocio.getNroSocio();
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbTelefono - " + ex.getMessage());
		}
	}
	
        /**
         * Borra un Telefono de la Base de Datos
         * @param Telefono unTelefono
         * @param Socio unSocio 
         */
	public void borrar(Telefono unTel, Socio unSocio){
		String sql;
		sql = "DELETE FROM Telefonos WHERE tipo = '" + unTel.getTipo() + 
                        "' and nroSocio = " + unSocio.getNroSocio();
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando de la Base de Datos - DbTelefonos - " + ex.getMessage());
		}
	}
}

