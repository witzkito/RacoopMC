package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ConeccionDbf {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rst;
		
	public ConeccionDbf(String ubicacion){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException ex){
			System.out.println("Error class not found");
		}
		try{
			String db = "jdbc:odbc:Driver={Microsoft dBase Driver (*.dbf)};DBQ=C:\\";
			conn = DriverManager.getConnection(db); 
			
			
		}catch(SQLException ex){
			System.out.println("Error Coneccion Base de Datos" + " " + ex);
		}try{
			stmt = conn.createStatement();
		}catch(SQLException ex){
			System.out.println("Error Creando el Statment");
		}
	}
	
	public void insert(String sql) throws SQLException{
		stmt.executeUpdate(sql);
	}
	public void delete(String sql) throws SQLException{
		stmt.executeUpdate(sql);
		
		}
	public void update(String sql) throws SQLException{
		stmt.executeUpdate(sql);
		
	}
	public ResultSet select(String sql) throws SQLException{
		rst = stmt.executeQuery(sql);
		
		return rst;
	}
	
	public Connection getConeccion(){
		return this.conn;
	}
}


