package db;

import java.sql.SQLException;


public class DbRepRanking {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbRepRanking(Coneccion con){
		this.con = con;
	}
	
	//Metodos
	
	/**
	 * Inserta un registro dentro de la base de datos para mostrarla en el reporte
	 * @param numero String
	 * @param nombre String
	 * @param total String
	 */
	public void Insert(String numero, String nombre,String total, String puesto){ 
		String sql;
                sql = "INSERT INTO RepRanking VALUES('" + numero + "', '" + nombre + "', '" + total +
                        "','" + puesto + "')";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbRepRanking - " + ex);
		}
	}
		
	/**
	 * Borra todos los registros de la tabla SociosRep
	 */
	public void borrarAll(){
		String sql;
		sql = "DELETE FROM RepRanking";
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbRepRanking - " + ex.getMessage());
		}
	}

}
