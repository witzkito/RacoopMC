package db;

import java.sql.SQLException;


public class DbRepTolerancia {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbRepTolerancia(Coneccion con){
		this.con = con;
	}
	
	//Metodos
	
	/**
         * Inserta un Registro en la Base de Datos
         * @param numero long
         * @param nombre String
         * @param censoPlantacion String
         * @param censoEntrega String
         * @param entrega String
         * @param tolerancia String
         */
	public void Insert(long numero, String nombre,String censoPlantacion,
                String censoEntrega, String entrega, String tolerancia){ 
		String sql;
                sql = "INSERT INTO RepTolerancia VALUES(" + numero + ", '" + nombre + "', '" + censoPlantacion +
                        "','" + censoEntrega + "', '" + entrega + "','" + tolerancia + "')";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbRepTolerancia - " + ex);
		}
	}
		
	/**
	 * Borra todos los registros de la tabla
	 */
	public void borrarAll(){
		String sql;
		sql = "DELETE FROM RepTolerancia";
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbRepTolerancia - " + ex.getMessage());
		}
	}

}
