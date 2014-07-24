package db;

import java.sql.SQLException;


public class DbRepToleranciaIndividual {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbRepToleranciaIndividual(Coneccion con){
		this.con = con;
	}
	
	//Metodos
	
	/**
         * Metodo que inserta un registro en la Base de Datoss
         * @param numero long
         * @param nombre String
         * @param nombreVariedad String
         * @param hectareas String
         * @param lote String
         * @param kgCensado String 
         * @param kgEntregado String
         */
	public void Insert(long numero, String nombre,String nombreVariedad,
                String hectareas, String lote, String cubierto, String kgCensado, String kgEntregado,
                String totalTolerancia){ 
		String sql;
                sql = "INSERT INTO RepToleranciaIndividual(numero, nomApe, nomVariedad, hectareas, " +
                        "lote, cubierto, kgCensado, kgEntregado, tolerancia) VALUES(" + numero + ", '" + nombre + "', '" + nombreVariedad +
                        "','" + hectareas + "', '" + lote + "', '" + cubierto + "', '" + kgCensado + "','" + kgEntregado + "','" +
                        totalTolerancia + "')";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbRepToleranciaIndividual - " + ex);
		}
	}
		
	/**
	 * Borra todos los registros de la tabla
	 */
	public void borrarAll(){
		String sql;
		sql = "DELETE FROM RepToleranciaIndividual";
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbRepIndividual - " + ex.getMessage());
		}
	}

}
