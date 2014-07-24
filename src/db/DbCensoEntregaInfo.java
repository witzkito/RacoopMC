package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import base.CensoEntregaInfo;
import base.CensoEntrega;
import base.Ciudad;
import base.Empresa;
import base.Socio;
import base.Variedad;

/**
 * @author Adrian
 * <b>Descripcion</b>
 * Clase encargada de almacenar los detalles del censo de entrega
 */
public class DbCensoEntregaInfo {
	//Atributos
	private Coneccion con;
	private Empresa empresa;
	
	//Constructor
	public DbCensoEntregaInfo(Coneccion con, Empresa empresa){
		this.con = con;
		this.empresa = empresa;
	}
	
	//Metodos
	
	/**
	 * Inserta un CensoEntregaInfo dentro de la base de datos
	 * @param Socio unSocio
	 * @param CensoEntregagaInfo censo
	 */
	public void Insert(Socio unSocio, CensoEntregaInfo censo, CensoEntrega censoEntrega){ 
		String sql;
		sql = "INSERT INTO CensoEntregaDetalles VALUES( " + censo.getNumero() + " , " + censo.getKg() + ", '" + censo.getVariedad().getNombre() +
			"', '" + censo.getLote() + "', '" + censo.getMunicipio().getNombre() + "', '" + censo.getPa() + "' , " + censo.getAño() +  
			", " + unSocio.getNroSocio() + ", " + censoEntrega.getNumero() + ")";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbCensoEntregaInfo - " + ex);
		}
	}
	
	/**
	 * Inserta un Map de CensoEntregaInfo dentro de la base de datos
	 * @param Socio unSocio
	 * @param Map unMap
	 */
	public void InsertAll(Socio unSocio, CensoEntrega censoEntrega,  Map unMap){
		Iterator it = unMap.values().iterator();
		while (it.hasNext()){
			this.Insert(unSocio,(CensoEntregaInfo)it.next(), censoEntrega);
		}
	}
	
	/**
	 * Trae todos los CensosEntregaInfo de un CensoEnrega
	 * @param Socio unSocio
	 * @return Map
	 */
	public Map getCensosEntregaInfos(Socio unSocio, CensoEntrega censoEntrega){
		ResultSet re; String sql; Map retornar = new HashMap();
		CensoEntregaInfo censo; int numero; double kg; Variedad variedad; double cubierto;
		String lote; Ciudad municipio; char pa; int año; 
		sql = "SELECT * FROM CensoEntregaDetalles where nroSocio = " + unSocio.getNroSocio() + " and nroCensoEntrega = " + censoEntrega.getNumero();
		try{
			re = con.select(sql);
			while (re.next()){
				numero = re.getInt(1);
				kg = re.getDouble(2);
				variedad = empresa.getConf().getVariedad(re.getString(3));
				lote  = re.getString(4);
				municipio = this.empresa.getConf().getCiudad(re.getString(5));
				pa = re.getString(6).charAt(0);
				año = re.getInt(7);
				censo = new CensoEntregaInfo(numero,lote,municipio, pa,variedad, kg, año);
				retornar.put(censo.getNumero(), censo);
			}
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbCensoEntregaInfo - " + ex);
		}
		return retornar;
	}
	
	/**
	 * Modifica un CensoEntregaInfo en la base de datos
	 * @param Socio unSocio
	 * @param CensoEntrega censoEntrega
	 * @param CensoEntregaInfo censo
	 */
	public void actualizar(Socio unSocio, CensoEntrega censoEntrega, CensoEntregaInfo censo){
		String sql;
		sql = "UPDATE CensoEntregaDetalles set kg = " + censo.getKg() + ", variedad = '" + censo.getVariedad().getNombre() +
		"', lote = '" + censo.getLote() + "', municipio = '" + censo.getMunicipio().getNombre() + "', pa = '" + 
		censo.getPa() + "', año = " + censo.getAño() + "  WHERE numero = " + censo.getNumero() + " and nroSocio = " + unSocio.getNroSocio() + " and nroCensoEntrega = " + censoEntrega.getNumero(); 
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbCensoEntregaInfo - " + ex.getMessage());
		}
	}
	
	/**
	 * Borra un CensoEntregaInfo de la base de datos
	 * @param Socio unSocio
	 * @param CensoEntrega censoEntrega
	 * @param CensoEntregaInfo censo
	 */
	public void borrar(Socio unSocio, CensoEntrega censoEntrega, CensoEntregaInfo censo){
		String sql;
		sql = "DELETE FROM CensoEntregaDetalles WHERE numero = " + censo.getNumero() + " and nroSocio = " + unSocio.getNroSocio() + " and nroCensoEntrega = " +
		censoEntrega.getNumero();  
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbCensoEntrega - " + ex.getMessage());
		}
	}
	
	/**
	 * Borra todas los CensoEntregaInfo
	 * @param Socio un Socio
	 * @param CensoEntrega un censoEntrega
	 */
	public void borrarTodo(Socio unSocio, CensoEntrega censoEntrega){
		String sql;
		sql = "DELETE FROM CensoEntregaDetalles WHERE nroSocio = " + unSocio.getNroSocio() + " and nroCensoEntrega = " + censoEntrega.getNumero();
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Todos los Datos - DbCensoEntrega - " + ex.getMessage());
		}
	}

}
