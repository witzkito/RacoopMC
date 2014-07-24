package db;

import Gui.V_Inicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import base.Empresa;
import base.Entrega;
import base.Fecha_wi;
import base.Variedad;

public class DbImportEntrega {
	//Atributos
	private ConeccionBascula con;
        private Empresa emp;
        private V_Inicio vInicio;
	
	//Constructor
	public DbImportEntrega(Empresa emp, ConeccionBascula con, V_Inicio vInicio){
		this.con = con;
                this.emp = emp;
                this.vInicio = vInicio;
	}
	
		
	/**
	 * Trae las Entregas
	 * @return Map
	 */
	public void getEntrega(){
		ResultSet re; String sql;
		long numero; long numeroSo; Variedad variedad; int años; String articulo;
                long kg; double rinde; Fecha_wi fecha; String temporada; Entrega entrega;
                DbEntrega dbEntrega = new DbEntrega(Empresa.con);
		sql = "SELECT * FROM PES_PESO, PES_ART WHERE PES_PESO.P_ARTIC = " +
                        " PES_ART.CODIGO";
		try{
			re = con.select(sql);
                        
			while (re.next()){
                                articulo = re.getString(30);
				if (getVariedad(articulo) != null){
                                    numero = re.getLong(2);
                                    numeroSo = re.getLong(3) - 10000;
                                    if (numeroSo >= 10000){
                                        numeroSo = numeroSo - 10000;
                                    }
                                    variedad = getVariedad(articulo);
                                    años = getAños(articulo);
                                    kg = re.getLong(7);
                                    rinde = re.getDouble(21);
                                    fecha = getFecha(re.getString(1));
                                    temporada = String.valueOf(re.getLong(28));
                                    entrega = new Entrega(numero, variedad, años, kg, rinde, fecha, temporada);
                                    if (this.emp.getSocio(numeroSo) != null){
                                        dbEntrega.insertar(this.emp.getSocio(numeroSo), entrega);
                                        this.vInicio.incrementar("Actualizando Datos desde el Programa Bascula...");
                                    }
                                
                                }
                        }
		}catch(SQLException ex){
			System.out.println("Error Trayendo datos de la Base de Datos - DbImportEntrega - " + ex);
		}
		
	}
	
        
	private Variedad getVariedad(String palabras){
            Iterator it = this.emp.getConf().getAllVariedades().values().iterator();
            Variedad variedad = null; String var;
             if (!(palabras.equals("Leña Seca") || (palabras.equals("Leña Verde")
                        || (palabras.equals("Leña Seca Eucalipto") ||
                        (palabras.equals("Leña Verde Eucalipto")))))){
                
                while (it.hasNext()){
                    variedad = (Variedad)it.next();
                    var = variedad.getNombre().toUpperCase();
                    if (palabras.contains(var)){
                        return variedad;
                    }
                }
            }
            return null;
        }
        
        private int getAños(String palabras){
            if (palabras.contains("1 año") 
                    || palabras.contains("1 Año")
                    || palabras.contains("1 Años")){
                return 1;
            }else{
                return 2;
            }
        }
        
       private Fecha_wi getFecha(String fecha){
           Fecha_wi retornar = new Fecha_wi();
           retornar.asignar_fecha_hora(fecha.substring(8,10), fecha.substring(5,7), fecha.substring(0, 4), "00", "00","00");
           return retornar;
        }
       
       /**
        * Procedimiento que retorna el ultimo ticket 
        * @return long numero
        */
       public long getUltimoTicket(){
           String sql; ResultSet re;
           sql = "SELECT MAX(P_TICKET) FROM PES_PESO WHERE P_ARTIC NOT IN ('121','122','131','132') AND" +
                   " P_SOCIO < 10300";
           try{
               re = this.con.select(sql);
               re.next();
               return re.getLong(1);
           }catch (Exception ex){
               System.out.println("Error Trayendo datos de la Base de Datos - DbImportEntrega.getUltimoTicket - " + ex);
               return 0;
           }
       }
       
       /**
        * Metodo que retorna la cantidad de Registros de la Base de Datos
        * @return
        */
       public long getCount(){
           String sql; ResultSet re;
           sql = "SELECT count(P_TICKET) FROM PES_PESO WHERE P_ARTIC NOT IN ('121','122','131','132') AND" +
                   " P_SOCIO < 10300";
           try{
               re = this.con.select(sql);
               re.next();
               return re.getLong(1);
           }catch(Exception ex){
               System.out.println("Error contando atos de la Base de Datos - DbImportEntrega.getCount - " + ex);
               return 0;
           }
       }
       
}

