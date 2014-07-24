package db;

import Gui.V_Inicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import base.Empresa;
import base.Entrega;
import base.Fecha_wi;
import base.Tercero;
import base.Variedad;
import java.util.HashMap;
import java.util.Map;

public class DbImportTerceros {
	//Atributos
	private ConeccionBascula con;
        private Empresa emp;
        private V_Inicio vInicio;
	
	//Constructor
	public DbImportTerceros(ConeccionBascula con){
		this.con = con;
	}
	
		
	
	public Tercero getTercero(int id){
		ResultSet re; String sql; Tercero terceros = null;
		sql = "SELECT * FROM PES_CLI WHERE EsSocio = false and CLIENTEId = " + id;
		try{
			re = con.select(sql);
                        while (re.next()){
                                terceros = new Tercero(re.getInt(1), re.getString(2), re.getString(5) + " - " +re.getString(6) );               
                                
                        }
                        
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbImportTerceros - " + ex);
		}
                return terceros;
	}
        
        public Map getMapTercero(String nombre){
		ResultSet re; String sql; Tercero tercero = null; Map terceros = new HashMap();
		sql = "SELECT * FROM PES_CLIE WHERE EsSocio = false and NOMBRE LIKE '% " + nombre + "%'";
		try{
			re = con.select(sql);
                        while (re.next()){
                                tercero = new Tercero(re.getInt(1), re.getString(2), re.getString(5) + re.getString(6) );               
                                terceros.put(tercero.getId(), tercero);
                        }
                        
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbImportTerceros - " + ex);
		}
                return terceros;
	}
        
         public Map getMapTerceros(){
		ResultSet re; String sql; Tercero tercero = null; Map terceros = new HashMap();
		sql = "SELECT * FROM PES_CLIE WHERE EsSocio = false";
		try{
			re = con.select(sql);
                        if (re != null){
                            while (re.next()){
                                    tercero = new Tercero(re.getInt(1), re.getString(2), re.getString(5) + re.getString(6) );               
                                    terceros.put(tercero.getId(), tercero);
                            }
                        }
                        
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbImportTerceros - " + ex);
		}
                return terceros;
	}
	
        
              
}

