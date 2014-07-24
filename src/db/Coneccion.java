package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Coneccion{
	private Connection conn;
	private Statement stmt;
	private ResultSet rst;
	
	public Coneccion(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException ex){
			System.out.println("Error class not found: " + ex);
		}
		try{
			String db = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=db\\db.MDB";
			conn = DriverManager.getConnection(db, "witzkito", ""); 
			
			
		}catch(SQLException ex){
                        JOptionPane.showMessageDialog(new JPanel(),"Error Coneccion Base de Datos: " + ex, "Error Base de datos",
                                JOptionPane.ERROR_MESSAGE);
		}try{
			stmt = conn.createStatement();
		}catch(SQLException ex){
			System.out.println("Error Creando el Statment: " + ex);
		}
	}
	
	public Coneccion(String ubicacion){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException ex){
			System.out.println("Error class not found: " + ex);
		}
		try{
			String db = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + ubicacion;
			conn = DriverManager.getConnection(db, "witzkito", "adrianWI"); 
		}catch(SQLException ex){
			System.out.println("Error Coneccion Base de Datos: " + ex);
		}try{
			stmt = conn.createStatement();
		}catch(SQLException ex){
			System.out.println("Error Creando el Statment: " + ex);
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
