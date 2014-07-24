package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import base.Variedad;

/**
 * @author Fernando Adrian Witzke
 * Clase encargada de Manejar el Almacenaje de los Datos de la Clase Variedad
 */
public class DbVariedad {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbVariedad(Coneccion con){
		this.con = con;
	}
	
	//Metodos
	
	public void Insert(Variedad unaVariedad){ 
		String sql;
		sql = "INSERT INTO Variedades" +
		      " VALUES("+ unaVariedad.getNumero()+ ", '" + unaVariedad.getNombre() + "', " +
				unaVariedad.getKgHec1Año() + ", " + unaVariedad.getKgHec2Año() + ")";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbVariedad - " + ex);
		}
	}
	
	public void InsertAll(Map unMap){
		Iterator it = unMap.values().iterator();
		while (it.hasNext()){
			this.Insert((Variedad)it.next());
		}
	}
	
	public Map getVariedades(){
		ResultSet re; String sql; Map retornar = new HashMap();
		Variedad unaVariedad; int numero; String nombre; Double kg1Año; Double kg2Años; 
		sql = "SELECT * FROM Variedades";
		try{
			re = con.select(sql);
			while (re.next()){
				numero = re.getInt(1);
				nombre = re.getString(2);
				kg1Año = re.getDouble(3);
				kg2Años = re.getDouble(4);
				unaVariedad = new Variedad(numero, nombre, kg1Año, kg2Años);
				retornar.put(unaVariedad.getNumero(), unaVariedad);
			}
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbVariedad - " + ex);
		}
                                      
		return retornar;
	}
	
	public void actualizar(Variedad unaVariedad){
		String sql;
		sql = "UPDATE Variedades set nombre = '" + unaVariedad.getNombre() + "', kg1Año = " + unaVariedad.getKgHec1Año() +
		", kg2Años = " + unaVariedad.getKgHec2Año() + " WHERE numero = " + unaVariedad.getNumero();
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbVariedad - " + ex.getMessage());
		}
	}
	
	public void borrar(Variedad unaVariedad){
		String sql;
		sql = "DELETE FROM Variedades WHERE numero = " + unaVariedad.getNumero();
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbVariedad - " + ex.getMessage());
		}
	}
        
        
        
	
	/**
	 * Trae un boolean que representa que existe y que esta correcta la tabla
	 * @return boolean
	 */
	public boolean estado(){
		String sql;
		sql = "SELECT * FROM Variedades";
		try{
			con.select(sql);
			return true;
		}catch(Exception ex){
			System.out.println("Error Comprobando Tabla");
			return false;
		}
	}
        
}
