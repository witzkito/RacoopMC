package db;

import base.Empresa;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author <p>Witzke Fernando Adrian - 06 de Mayo del 2008</p>
 * <p><b>Clase encargada de Almacenar en la Base de Datos la clase Empresa</b></p> 
 *
 */
public class DbEmpresa {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbEmpresa(Coneccion con){
		this.con = con;
	}
	
	//Metodos
	
	/**
	 * Inserta una Empresa en la Base de Datos
	 * @param unMotivo
	 */
	public void Insert(Empresa emp){ 
		String sql;
		sql = "INSERT INTO Empresas VALUES(1, '"+ emp.getNombre() + "', '"
                        + emp.getTelefono() + "', '" +	emp.getLocalidad() + "', '"
                        + emp.getDireccion() + "')";  
                        
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbEmpresa - " + ex);
		}
	}
	
	/**
	 * Trae de la Base de Datos la Empresa
	 * @return Empresa
	 */
	public Empresa getEmpresa(){
		ResultSet re; String sql; Empresa emp = null;
		String nombre; String localidad; String direccion; String telefono;
		sql = "SELECT * FROM Empresas WHERE numero = 1";
		try{
			re = con.select(sql);
                        if (re.next()){
                            nombre = re.getString(2);
                            telefono = re.getString(3);
                            localidad = re.getString(4);
                            direccion = re.getString(5);
                            emp = new Empresa(nombre, direccion, localidad, telefono);
                        }else{
                            return null;
                        }
			
		}catch(SQLException ex){
			System.out.println("Error Trayendo empresa - DbEmpresa - " + ex);
		}
		return emp;
	}
	
	/**
	 * Modifica los datos de la Empresa en la Base de Datos
	 * @param Empresa unaEmp
	 */
	public void actualizar(Empresa emp){
            String sql;
            sql = "UPDATE Empresas set nombre = '" + emp.getNombre() +
                    "', localidad = '" + emp.getLocalidad() + "', direccion = '" +
                     emp.getDireccion() + "', telefono = '" + emp.getTelefono() +
                     "' WHERE numero = 1"; 
            try{
                    con.update(sql);
            }catch(SQLException ex){
                    System.out.println("Error Actualizando Datos - DbEmpresa - " + ex.getMessage());
            }
	}
	
	
}
