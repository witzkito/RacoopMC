package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import base.CensoEntrega;
import base.Fecha_wi;
import base.Socio;

/**
 * @author Adrian
 * <p><b>Descripcion:</b></p>
 * Clase encargada de almacenar los CensoEntrega dento de la base de datos
 *
 */
public class DbCensoEntrega {
	//Atributos
	private Coneccion con;
	
	//Constructor
	public DbCensoEntrega(Coneccion con){
		this.con = con;
	}
	
	//Metodos
	
	/**
	 * Inserta un CensoEntrega dentro de la base de datos
	 * @param Socio unSocio
	 * @param CensoEntrega censo
	 */
	public void Insert(Socio unSocio, CensoEntrega censo){ 
		String sql;
		sql = "INSERT INTO CensosEntregas VALUES( " + censo.getNumero() +
                      " , " + censo.getNroCenso() + ",  '" + censo.getFecha().getDia() +
                      "', '" + censo.getFecha().getMes() + "', '" + censo.getFecha().getAño() +
                      "', '" + censo.getTemporada() + "', '" + censo.getMes1Año() + "', " +
                      censo.getPorMes1() + ", '" + censo.getMes1AñoB() + "', " + censo.getPorMes1B() +
                      ", '" + censo.getMes2Años() + "', " + censo.getPorMes2() + ", '" + censo.getMes2AñosB()+
                      "', " + censo.getPorMes2B() + ", " + censo.getKgPorDia() + ", " + unSocio.getNroSocio() + " )";
		try{
			con.insert(sql);
		}catch(Exception ex){
			System.out.println("Error Insertando datos en la Base de Datos - DbCensoEntrega - " + ex);
		}
	}
	
	/**
	 * Inserta un Map de CensoEntrega dentro de la base de datos
	 * @param Socio unSocio
	 * @param Map unMap
	 */
	public void InsertAll(Socio unSocio, Map unMap){
		Iterator it = unMap.values().iterator();
		while (it.hasNext()){
			this.Insert(unSocio,(CensoEntrega)it.next());
		}
	}
	
	/**
	 * Trae todos los Censos Entregas de un socio
	 * @param Socio unSocio
	 * @return Map
	 */
	public Map getCensosEntrega(Socio unSocio){
		ResultSet re; String sql; Map retornar = new HashMap(); String temporada; String dia; String mes;
		CensoEntrega censo; long numero; String año; Fecha_wi fecha = new Fecha_wi(); 
                long nroCenso; String mes1Año; String mes1AñoB; int porMes1; int porMes1B;
                String mes2Años; String mes2AñosB; int porMes2; int porMes2B; long kgPorDia; 
		sql = "SELECT * FROM CensosEntregas where nroSocio = " + unSocio.getNroSocio();
		try{
			re = con.select(sql);
			while (re.next()){
				numero = re.getLong(1);
				nroCenso = re.getLong(2);
                                dia = re.getString(3);
				mes = re.getString(4);
				año = re.getString(5);
				fecha.asignar_fecha_hora(dia, mes, año, "00", "00", "00");
				temporada = re.getString(6);
                                mes1Año = re.getString(7);
                                porMes1 = re.getInt(8);
                                mes1AñoB = re.getString(9);
                                porMes1B = re.getInt(10);
                                mes2Años = re.getString(11);
                                porMes2 = re.getInt(12);
                                mes2AñosB = re.getString(13);
                                porMes2B = re.getInt(14);
                                kgPorDia = re.getLong(15);
				censo = new CensoEntrega(numero, fecha, temporada, nroCenso, mes1Año, 
                                                        porMes1, mes1AñoB, porMes1B, mes2Años, porMes2,
                                                        mes2AñosB, porMes2B, kgPorDia);
				retornar.put(censo.getNumero(), censo);
			}
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbCensoEntrega - " + ex);
		}
		return retornar;
	}
	
	/**
	 * Modifica un CensoEntrega en la base de datos
	 * @param Socio unSocio
	 * @param CensoEntrega censoPlan
	 */
	public void actualizar(Socio unSocio, CensoEntrega censoEnt){
		String sql;
		sql = "UPDATE CensosEntregas set dia = '" + censoEnt.getFecha().getDia() + 
                      "', mes = '" + censoEnt.getFecha().getMes() + "',  anio = '"
                      + censoEnt.getFecha().getAño() + "', temporada = '" + censoEnt.getTemporada() +
                      "', mes1Año = '" + censoEnt.getMes1Año() + "', porMes1 = " + censoEnt.getPorMes1() +
                      "', mes1AñoB = '" + censoEnt.getMes1AñoB() + "', porMes1B = " + censoEnt.getPorMes1B() +
                      ", mes2Años = '" + censoEnt.getPorMes2() + "', porMes2 = " + censoEnt.getPorMes2() + 
                      ", mes2AñosB = '" + censoEnt.getMes2AñosB() + "', porMes2B = " + censoEnt.getPorMes2B() +
                      " WHERE numero = " + censoEnt.getNumero() + " and nroSocio = " + unSocio.getNroSocio(); 
		try{
			con.update(sql);
		}catch(SQLException ex){
			System.out.println("Error Actualizando Datos - DbCensoEntrega - " + ex.getMessage());
		}
	}
	
	/**
	 * Borra un CensoEntrega de la base de datos
	 * @param Socio unSocio
	 * @param CensoEntrega censoEnt
	 */
	public void borrar(Socio unSocio, CensoEntrega censoEnt){
		String sql;
		sql = "DELETE FROM CensosEntregas WHERE numero = " + censoEnt.getNumero() + " and nroSocio = " + unSocio.getNroSocio();  
		try{
			con.delete(sql);
		}catch(SQLException ex){
			System.out.println("Error Borrando Datos - DbCensoEntrega - " + ex.getMessage());
		}
	}
}

