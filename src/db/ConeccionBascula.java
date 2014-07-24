package db;

import base.Empresa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConeccionBascula{
	private Connection conn;
	private Statement stmt;
	private ResultSet rst;
        public boolean error = false;
        
	
	public ConeccionBascula(Empresa emp){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException ex){
			System.out.println("Error class not found");
		}
		try{
			String db = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + emp.getConf().getBascula();
			conn = DriverManager.getConnection(db, "", ""); 
                        stmt = conn.createStatement();
                 
		
		}catch(SQLException ex){
			System.out.println("Error Coneccion Base de Datos");
                        error= true;
                       
		
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
		if (this.stmt != null){
                    rst = stmt.executeQuery(sql);
                    return rst;
                }
                return null;
	}
	
	public Connection getConeccion(){
		return this.conn;
	}
}
