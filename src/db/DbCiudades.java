package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import base.Ciudad;

public class DbCiudades {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbCiudades(Coneccion con){
		this.con = con;
	}
	
	/**
	 * Inserta una Ciudad en la Base de Datos
	 * @param ciudad Ciudad
	*/
	public void Insert(Ciudad ciudad){ 
		String sql;
		sql = "INSERT INTO Ciudades VALUES(" + ciudad.getNumero() +
                ", '" + ciudad.getNombre() + "')";
		try{
			con.insert(sql);
		}catch(Exception ex){
                    System.out.println("Error Insertando datos en la Base de Datos - DbCiudad - " + ex);
		}
	}
	
	/**
	 * Inserta Varias Ciudades almacenados en un Map, en la base de datos
	 * @param Map de Ciudades
	 */
	public void InsertAll(Map unMap){
		Iterator it = unMap.values().iterator(); Ciudad unaCiudad;
		String sql;
		while (it.hasNext()){
                    unaCiudad = (Ciudad)it.next();
			try{
                            this.Insert(unaCiudad);
			}catch(Exception ex){
                            System.out.println("Error Insertando todos los datos en la Base de Datos - DbCiudad - " + ex);
			}
		}
	}
	
	/**
	 * Trae las ciudades
	 * @return Map
	 */
	public Map getCiudades(){
		ResultSet re; String sql; Map retornar = new HashMap();
		Ciudad unaCi; int numero;  String nombre;
		sql = "SELECT * FROM Ciudades";
		try{
			re = con.select(sql);
			while (re.next()){
				numero = re.getInt(1);
				nombre = re.getString(2);
				unaCi = new Ciudad (numero, nombre);
				retornar.put(unaCi.getNumero(),unaCi);
			}
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbCiudades - " + ex);
		}
		return retornar;
	}
	
	/**
	 * Actualiza la Ciudad de la Base de Datos
	 * @param Ciudad unaCiudad
	 */
	public void actualizar(Ciudad unaCiudad){
		String sql;
		sql = "UPDATE Ciudades set nombre = '" + unaCiudad.getNombre() +
                        "' WHERE numero = " + unaCiudad.getNumero();
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbCiudad - " + ex.getMessage());
		}
	}
	
        /**
         * Borra una Ciudad de la Base de Datos
         * @param Ciudad unaCiudad 
         */
	public void borrar(Ciudad unaCiudad){
		String sql;
		sql = "DELETE FROM Ciudades WHERE numero = " + unaCiudad.getNumero();
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando de la Bae de Datos - DbCiudades - " + ex.getMessage());
		}
	}
}

