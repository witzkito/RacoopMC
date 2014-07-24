package db;

import base.Empresa;
import base.Entrega;
import base.Fecha_wi;
import base.Socio;
import base.Variedad;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Witzke Fernando Adrian
 * <p><b>Descripcion:</b></p>
 * Clase encargada de almacenar las Entrega dento de la base de datos
 *
 */
public class DbEntrega {
	//Atributos
	private Coneccion con;
        private Empresa emp;
	
	//Constructor
	public DbEntrega(Coneccion con, Empresa emp){
            	this.con = con;
                this.emp = emp;
	}
        
        public DbEntrega(Coneccion con){
            	this.con = con;
	}
        
        public void insertar(Socio unSocio, Entrega unaEnt){
            String sql;
            sql = "INSERT INTO Entregas VALUES(" + unaEnt.getNumero() + ", " +
                    unSocio.getNroSocio() + ", '" + unaEnt.getVariedad().getNombre() +
                    "', " + unaEnt.getCiclo() + ", " + unaEnt.getKg() + ", " +
                    unaEnt.getRinde() + ", '" + unaEnt.getFecha().getDia() + 
                    "', '" + unaEnt.getFecha().getMes() + "', '" + unaEnt.getFecha().getAño() +
                    "', '" + unaEnt.getTemporada() + "')";
            try{
                con.insert(sql);
            }catch (Exception ex){
                System.out.println("Error Insertando datos en la Base de Datos - DbEntrega - " + ex);
            }
        }
        
        public void actualizar(Socio unSocio, Entrega unaEnt){
            String sql;
            sql = "UPDATE Entregas SET variedad = '" + unaEnt.getVariedad().getNombre() + "' " +
                    "WHERE nroTicket = " + unaEnt.getNumero() + " and nroSocio = " + unSocio.getNroSocio()
                    + " and temporada = '" + unaEnt.getTemporada() + "'";
            try{
                this.con.insert(sql);
            }catch(Exception ex){
                System.out.println("Error Actualizando datos de la Base de Datos - DbEntrega - " + ex);
            }
        }
        
        /**
         * Metodo que trae las entregas de un socio
         * @param unSocio Socio
         * @return unMapa Map
         */
        public Map getEntrega(Socio unSocio){
            String sql; ResultSet rs; Entrega entrega; long numero; Variedad variedad;
            int ciclo; long kg; double rinde; Fecha_wi fecha = new Fecha_wi();
            String temporada; Map retornar = new HashMap();
            sql = "SELECT * FROM Entregas WHERE nroSocio = " + unSocio.getNroSocio();
            try{
                rs = this.con.select(sql);
                while (rs.next()){
                    numero = rs.getLong(1);
                    variedad = this.emp.getConf().getVariedad(rs.getString(3));
                    ciclo = rs.getInt(4);
                    kg = rs.getLong(5);
                    rinde = rs.getDouble(6);
                    fecha.asignar_fecha_hora(rs.getString(7),rs.getString(8), rs.getString(9),"00", "00", "00");
                    temporada = rs.getString(10);
                    entrega = new Entrega(numero, variedad, ciclo, kg, rinde, fecha, temporada);
                    retornar.put(entrega.getNumero(), entrega);
                }
            }catch(Exception ex){
                System.out.println("Error Trayendo datos de la Base de Datos - DbEntrega - " + ex);
            }
            return retornar;
        }
        
        /**
         * Borra todas las Entregas de la Base de Datos
         */
        public void borrarAll(){
            String sql;
            sql = "DELETE FROM Entregas";
            try{
                this.con.delete(sql);
            }catch(Exception ex){
                System.out.println("Error Borrando datos de la Base de Datos - DbEntrega - " + ex);
            }
        }
        
        /**
        * Procedimiento que retorna el ultimo ticket 
        * @return long numero
        */
       public long getUltimoTicket(){
           String sql; ResultSet re;
           sql = "SELECT MAX(nroTicket) FROM Entregas";
           try{
               re = this.con.select(sql);
               re.next();
               return re.getLong(1);
           }catch (Exception ex){
               System.out.println("Error Trayendo datos de la Base de Datos - DbEntrega.getUltimoTicket - " + ex);
               return 0;
           }
       }
        
       
	
	
}

