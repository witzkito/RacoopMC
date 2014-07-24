package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import base.CensoPlanInfo;
import base.CensoPlantacion;
import base.Ciudad;
import base.Empresa;
import base.Socio;
import base.Variedad;

/**
 * @author Adrian
 * <b>Descripcion</b>
 * Clase encargada de almacenar los detalles del censo de plantacion
 */
public class DbCensoPlanInfo {
	//Atributos
	private Coneccion con;
	private Empresa empresa;
	
	//Constructor
	public DbCensoPlanInfo(Coneccion con, Empresa empresa){
		this.con = con;
		this.empresa = empresa;
	}
	
	//Metodos
	
	/**
	 * Inserta un CensoPlantacionInfo dentro de la base de datos
	 * @param Socio unSocio
	 * @param CensoPlanInfo censo
	 */
	public void Insert(Socio unSocio, CensoPlanInfo censo, CensoPlantacion censoPlantacion){ 
		String sql;
		System.out.println(censo.getPa());
                sql = "INSERT INTO CensoPlantacionDetalles VALUES( " + censo.getNumero() + " , " + censo.getHectareas() + ", '" + censo.getVariedad().getNombre() +
			"', " + censo.getPorcentaje() + ", " + censo.getGerminacion() + ", '" + censo.getLote() + "', '" + censo.getMunicipio().getNombre() + "', '" + censo.getPa() + "', " + censo.getAño() +
			", " + unSocio.getNroSocio() + ", " + censoPlantacion.getNumero() + ")";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbCensoPlanInfo - " + ex);
		}
	}
	
	/**
	 * Inserta un Map de CensoPlantacionInfo dentro de la base de datos
	 * @param Socio unSocio
	 * @param Map unMap
	 */
	public void InsertAll(Socio unSocio, CensoPlantacion censoPlan,  Map unMap){
		Iterator it = unMap.values().iterator();
		while (it.hasNext()){
			this.Insert(unSocio,(CensoPlanInfo)it.next(), censoPlan);
		}
	}
	
	/**
	 * Trae todos los CensosPlanInfo de un CensoPlantacion
	 * @param Socio unSocio
	 * @return Map
	 */
	public Map getCensosPlanInfos(Socio unSocio, CensoPlantacion censoPlan){
		ResultSet re; String sql; Map retornar = new HashMap();
		CensoPlanInfo censo; int numero; double hectareas; Variedad variedad; double cubierto;
		String lote; Ciudad municipio; char pa; int año; double germinacion;
		sql = "SELECT * FROM CensoPlantacionDetalles where nroSocio = " + unSocio.getNroSocio() + " and nroCensoPlantacion = " + censoPlan.getNumero();
		try{
			re = con.select(sql);
			while (re.next()){
				numero = re.getInt(1);
				hectareas = re.getDouble(2);
				variedad = empresa.getConf().getVariedad(re.getString(3));
				cubierto = re.getDouble(4);
                                germinacion = re.getDouble(5);
				lote  = re.getString(6);
				municipio = empresa.getConf().getCiudad(re.getString(7));
				pa = re.getString(8).charAt(0);
				año = re.getInt(9);
				censo = new CensoPlanInfo(numero, hectareas, variedad, cubierto, germinacion, lote, municipio, pa, año);
				retornar.put(censo.getNumero(), censo);
			}
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbCensoPlanInfo - " + ex);
		}
		return retornar;
	}
	
	/**
	 * Modifica un CensoPlanInfo en la base de datos
	 * @param Socio unSocio
	 * @param CensoPlantacion censoPlan
	 * @param CensoPlanInfo censo
	 */
	public void actualizar(Socio unSocio, CensoPlantacion censoPlan, CensoPlanInfo censo){
		String sql;
		sql = "UPDATE CensoPlantacionDetalles set hectareas = " + censo.getHectareas() + ", variedad = '" + censo.getVariedad().getNombre() +
		"', cubierto = " + censo.getPorcentaje() + ", germinado=  " + censo.getGerminacion() +
                ", lote = '" + censo.getLote() + "', municipio = '" + censo.getMunicipio().getNombre() + "', pa = '" +
		censo.getPa() + "', anio = " + censo.getAño() + " WHERE numero = " + censo.getNumero() + " and nroSocio = " + unSocio.getNroSocio() + " and nroCensoPlantacion = " + censoPlan.getNumero(); 
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbCensoPlanInfo - " + ex.getMessage());
		}
	}
	
	/**
	 * Borra un CensoPlanInfo de la base de datos
	 * @param Socio unSocio
	 * @param CensoPlantacion censoPla
	 * @param CensoPlanInfo censo
	 */
	public void borrar(Socio unSocio, CensoPlantacion censoPlan, CensoPlanInfo censo){
		String sql;
		sql = "DELETE FROM CensoPlantacionDetalles WHERE numero = " + censo.getNumero() + " and nroSocio = " + unSocio.getNroSocio() + " and nroCensoPlantacion = " +
		censoPlan.getNumero();  
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbCensoPlanInfo - " + ex.getMessage());
		}
	}
	
	/**
	 * Borra todas los CensoPlanInfo
	 * @param Socio un Socio
	 * @param CensoEntrega un censoEntrega
	 */
	public void borrarTodo(Socio unSocio, CensoPlantacion censoPlantacion){
		String sql;
		sql = "DELETE FROM CensoPlantacionDetalles WHERE nroSocio = " + unSocio.getNroSocio() + " and nroCensoPlantacion = " + censoPlantacion.getNumero();
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Todos los Datos - DbCensoPlanInfo - " + ex.getMessage());
		}
	}
	
	/**
	 * Trae un boolean que representa que existe y que esta correcta la tabla
	 * @return boolean
	 */
	public boolean estado(){
		String sql;
		sql = "SELECT * FROM CensoPlantacionDetalles";
		try{
			con.select(sql);
			return true;
		}catch(Exception ex){
			System.out.println("Error comprobando tabla " + ex);
			return false;
		}
	}

}
