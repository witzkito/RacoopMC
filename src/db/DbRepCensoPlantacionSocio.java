package db;

import java.sql.SQLException;
import base.CensoPlanInfo;
import base.CensoPlantacion;
import base.Empresa;
import base.Socio;

public class DbRepCensoPlantacionSocio {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbRepCensoPlantacionSocio(Coneccion con){
		this.con = con;
	}
	
	//Metodos
	
	/**
	 * Inserta un registro dentro de la base de datos para mostrarla en el reporte
	 * @param ceDe CensoPlanInfo
	 * @param censoPla CensoPlantacion
	 * @param unSocio unSocio
	 * @param emp Empresa
	 */
	public void Insert(CensoPlanInfo ceDe, CensoPlantacion censoPla,Socio unSocio,Empresa emp){ 
		String sql;
		sql = "INSERT INTO RepCensoPlantacionSocio VALUES(" + ceDe.getNumero() + ", " + ceDe.getHectareas() + ", '" + ceDe.getVariedad().getNombre() + "', " +
		ceDe.getPorcentaje() + ", " + ceDe.getGerminacion() + ", '" + ceDe.getLote() + "', '" + ceDe.getMunicipio() + "' , '" + ceDe.getPa() + "', " +  ceDe.getAño() +  ", '" + censoPla.getFecha_wi().getDia() + "', '" +
		censoPla.getFecha_wi().getMes() + "', '" + censoPla.getFecha_wi().getAño() + "', " + unSocio.getNroSocio() + ", " + censoPla.getNumero() + ")";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbRepCensoPlantacionSocio - " + ex);
		}
	}
		
	/**
	 * Borra todos los registros de la tabla SociosRep
	 */
	public void borrarAll(){
		String sql;
		sql = "DELETE FROM RepCensoPlantacionSocio";
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbRepCensoPlantacionSocio - " + ex.getMessage());
		}
	}

}
