package db;

import base.Configuraciones;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbConfiguraciones {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbConfiguraciones(Coneccion con){
		this.con = con;
	}
	
	
	
	/**
	 * Trae las configuraciones
	 * @return Map
	 */
	public Configuraciones getConfiguraciones(){
		ResultSet re; String sql;
		Configuraciones unaCo = null; String bascula; 
                sql = "SELECT * FROM Configuraciones";
		try{
			re = con.select(sql);
                        re.next();
			bascula = re.getString(2);
                        unaCo = new Configuraciones (bascula);
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbConfiguraciones - " + ex);
		}
		return unaCo;
	}
	
	/**
	 * Actualiza la direccion de bascula en la base de datos
	 * @param direccion String
	 */
	public void actualizarBascula(String direccion){
		String sql;
		sql = "UPDATE Configuraciones set bascula = '" + direccion + "' WHERE numero = 1";
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbConfiguraciones.bascula - " + ex.getMessage());
		}
	}
        
        
	
        
}

