package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author Witzkito
 * <p><b> Descripcion: </b></p> 
 *	Clase cel censo de plantacion, en este caso solo aplicable a Cooperativas
 *	cuya plantacion es la Mandioca. 
 */
public class CensoPlantacion {
	//Atributos
		private long numero; //Numero del censo de Plantacion
		private Fecha_wi fecha; //Fecha del censo de Plantacion
		private String temporada; //Temporada de la plantacion
		private Map infoCensos; //Informacion del Censo (cant - tipo - porcentaje)
		
		
	//Constructor
		public CensoPlantacion(long numero, Fecha_wi fecha, String temporada ){
			this.numero = numero;
			this.fecha = fecha;
			this.temporada = temporada;
			infoCensos = new HashMap();
		}
		
	//Metodos
		public void setNumero(long numero){ this.numero = numero;}
		public long getNumero(){ return this.numero;}
		public void setFecha_wi(Fecha_wi fecha){ this.fecha = fecha;}
		public Fecha_wi getFecha_wi(){ return this.fecha;}
		public void setTemporada(String temporada){ this.temporada = temporada;}
		public String getTemporada(){ return this.temporada;}
		
		
		
		/**
		 * Ingresa un InfoCenso al Map
		 * @param CensoPlanInfo 
		 */
		public void addInfoCenso(CensoPlanInfo info){ this.infoCensos.put(info.getNumero(), info);}
		/**
		 * Borra del Mapa un InfoCenso
		 * @param int nroInfo
		 */
		public void remInfoCenso(int nroInfo){ this.infoCensos.remove(new Integer(nroInfo));}
		/**
		 * Borra Todos los InfoCenso
		 */
		public void remAllInfoCenso(){ this.infoCensos.clear();}
		
		/**
		 * Ingresa un muchos InfoCenso al Map
		 * @param CensoPlanInfo
		 */
		public void addAllInfoCenso(Map infoCensos){ this.infoCensos.putAll(infoCensos);}
		/**
		 * Trae un InfoCenso del Mapa
		 * @param int nroInfo
		 * @return InfoCenso
		 */
		public CensoPlanInfo getInfoCenso(int nroInfo){ return (CensoPlanInfo)this.infoCensos.get(new Integer(nroInfo));}
		/**
		 * Trae todos los InfoCenso del Mapa
		 * @return Map
		 */
		public Map getAllInfo(){ return this.infoCensos;}
		
		/**
		 * Retorna el ultimo id de un Mapa
		 * @param Map
		 * @return int
		 */
		public int getLastCount(Map unMap){
			int a = 0; CensoPlanInfo censo;
			Iterator it = unMap.values().iterator();
			while (it.hasNext()){
				censo = (CensoPlanInfo)it.next();
				if (censo.getNumero() > a){
                                    a = censo.getNumero();
                                }
                        }
			return a + 1;
		}
		
		/**
		 * Devuelve un CensoPlanInfo para el caso de que no tengamos el numero pero si el lote, el cubirto y
		 * la variedad
		 * @param lote
		 * @param variedad
		 * @param cubierto
		 * @return CensoPlanInfo censoPlanInfo
		 */
		public CensoPlanInfo getCensoPlanInfo(String lote, Variedad variedad, Double cubierto){
			CensoPlanInfo censoAux = null; CensoPlanInfo censoInfo = null;
			Iterator it = infoCensos.values().iterator(); 
			while (it.hasNext()){
				censoAux = (CensoPlanInfo)it.next();
					if ((censoAux.getVariedad().equals(variedad)) && (censoAux.getPorcentaje() == cubierto)  && (lote.equals(censoAux.getLote()))){
						censoInfo = censoAux;
					}
				}
			return censoInfo;
		}
		
		/**
		 * Devuelve un CensoPlanInfo para el caso de que no tengamos el numero pero si el lote y
		 * la variedad
		 * @param String lote
		 * @param String variedad
		 * @return CensoPlanInfo censoPlanInfo
		 */
		public CensoPlanInfo getCensoPlanInfo(String lote, Variedad variedad){
			CensoPlanInfo censoAux = null; CensoPlanInfo censoInfo = null;
			Iterator it = infoCensos.values().iterator(); 
			while (it.hasNext()){
				censoAux = (CensoPlanInfo)it.next();
					if ((censoAux.getVariedad().equals(variedad)) && (lote.equals(censoAux.getLote()))){
						censoInfo = censoAux;
					}
				}
			return censoInfo;
		}
                
                /**
                 * Devuelve un CensoPlanInfo pasandole una variedad y un ciclo
                 * @param var Variedad
                 * @param ciclo Int
                 * @return CensoPlanInfo
                 */
                public CensoPlanInfo getCensoPlanInfo(Variedad var, int ciclo){
                    CensoPlanInfo censo = null; CensoPlanInfo censoInfo = null;
                    Iterator it = this.infoCensos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == ciclo && censo.getVariedad().getNombre().equals(var.getNombre())){
                            return censo;
                        }
                    }
                    return null;
                }
		
		
		/**
		 * Metodo que retorna un Mapa con los CensoPlanInfo que sean igual al lote y a la variedad sin importar
		 * el cubierto
		 * @param String lote
		 * @param Variedad variedad
		 * @return Map
		 */
		public Map getCensosPlanInfo(String lote, Variedad variedad){
			Map retornar = new HashMap();
			CensoPlanInfo censoInfo = null;
			Iterator it = infoCensos.values().iterator();
			while (it.hasNext()){
				censoInfo = (CensoPlanInfo)it.next();
				if ((censoInfo.getVariedad().equals(variedad)) && (censoInfo.getLote().equals(lote))){
					retornar.put(censoInfo.getNumero(), censoInfo);
				}
			}
			return retornar;
		}
                 
		/**
                 * Procedimiento para que retorne la Cantidad de Kg del censo
                 * Plantacion de 1 Año
                 * @return total Double
                 */
                public double getKg1año(){
                    double retornar = 0; CensoPlanInfo censo; double hectareas;
                    Iterator it = this.infoCensos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == 1){
                            hectareas = (censo.getHectareas() * censo.getPorcentajeTotal()) / 100;
                            retornar = retornar + (hectareas * +
                                  censo.getVariedad().getKgHec1Año());
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Procedimiento para que retorne la Cantidad de Kg del censo
                 * Plantacion de 2 Año
                 * @return total Double
                 */
                public double getKg2año(){
                    double retornar = 0; CensoPlanInfo censo; double hectareas;
                    Iterator it = this.infoCensos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == 2){
                            hectareas = (censo.getHectareas() * censo.getPorcentajeTotal()) / 100;
                            retornar = retornar + (hectareas * +
                                  censo.getVariedad().getKgHec2Año());
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Procedimiento para que retorne la Cantidad de Hectareas del censo
                 * Plantacion de 1 Año
                 * @return total Double
                 */
                public double getHec1año(){
                    CensoPlanInfo censo; double hectareas = 0;
                    Iterator it = this.infoCensos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == 1){
                            hectareas = hectareas + censo.getHectareas();
                            
                        }
                    }
                    return hectareas;
                }
                
                 /**
                 * Procedimiento para que retorne la Cantidad de Hectareas del censo
                 * Plantacion de 2 Año
                 * @return total Double
                 */
                public double getHec2año(){
                    CensoPlanInfo censo; double hectareas = 0;
                    Iterator it = this.infoCensos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == 2){
                            hectareas = hectareas + censo.getHectareas();
                            
                        }
                    }
                    return hectareas;
                }
                
               /**
                * Procedimiento que retorna un ArrayList con los distintos cubierto
                * de un censo Plantacion
                * @return ArrayList
                */
                public List getCubierto(){
                    List retornar = new ArrayList(); CensoPlanInfo censo;
                    Iterator it = this.infoCensos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (!(retornar.contains(censo.getPorcentaje()))){
                            retornar.add(censo.getPorcentaje());
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Procedimiento que retorna un boolean si el censo tiene un
                 * cubierto determinado
                 * @param Double cubierto
                 * @return boolean
                 */
                        
                public boolean tieneCubierto(double cubierto){
                    Iterator it = this.getAllInfo().values().iterator();
                    CensoPlanInfo censo;
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if(cubierto == censo.getPorcentaje()){
                           return true;
                        }
                    }
                    return false;
                }
                
                /**
                 * Retornar el total de hectareas de un cubierto
                 * @param double cubierto
                 * @return double
                 */
                public double getTotalHec(double cubierto){
                    CensoPlanInfo censo; double retornar = 0;
                    Iterator it = this.getAllInfo().values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getPorcentaje() == cubierto){
                            retornar = retornar + censo.getHectareas();
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Funcion que retornar el total de hectareas calculando junto con
                 * el cubierto y germinado
                 * @return double total
                 */
                public double getTotalHec(){
                    CensoPlanInfo censo; double retornar = 0;
                    Iterator it = this.getAllInfo().values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        retornar = retornar +  ((censo.getHectareas() * censo.getPorcentajeTotal())/100);
                    }
                    return retornar;
                }
                
                /**
                 * Funcion que retornar el total de hectareas calculando junto con
                 * el cubierto y germinado
                 * @return double total
                 */
                public double getTotal1AñoHec(){
                    CensoPlanInfo censo; double retornar = 0;
                    Iterator it = this.getAllInfo().values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == 1){
                            retornar = retornar +  ((censo.getHectareas() * censo.getPorcentajeTotal())/100);
                        }
                    }
                    return retornar;
                }
                
                 /**
                 * Funcion que retornar el total de hectareas calculando junto con
                 * el cubierto y germinado pasandole la Variedad
                 * @param variedad Var
                 * @return double total
                 */
                public double getTotal1AñoHec(Variedad var){
                    CensoPlanInfo censo; double retornar = 0;
                    Iterator it = this.getAllInfo().values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == 1){
                            if (censo.getVariedad().getNombre().equals(var.getNombre())){
                                retornar = retornar +  ((censo.getHectareas() * censo.getPorcentajeTotal())/100);
                            }
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Funcion que retornar el total de hectareas calculando junto con
                 * el cubierto y germinado
                 * @return double total
                 */
                public double getTotal2AñoHec(){
                    CensoPlanInfo censo; double retornar = 0;
                    Iterator it = this.getAllInfo().values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == 2){
                            retornar = retornar +  ((censo.getHectareas() * censo.getPorcentajeTotal())/100);
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Funcion que retornar el total de hectareas calculando junto con
                 * el cubierto y germinado pasandole la Variedad
                 * @param variedad Var
                 * @return double total
                 */
                public double getTotal2AñoHec(Variedad var){
                    CensoPlanInfo censo; double retornar = 0;
                    Iterator it = this.getAllInfo().values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getAño() == 2){
                            if (censo.getVariedad().getNombre().equals(var.getNombre())){
                                retornar = retornar +  ((censo.getHectareas() * censo.getPorcentajeTotal())/100);
                            }
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Metodo que retorna si el censo tiene la variedad indicada
                 * @param var Variedad
                 * @return boolean
                 */ 
                public boolean getTieneVariedad(Variedad var, int ciclo){
                    CensoPlanInfo censo;
                    Iterator it = this.infoCensos.values().iterator();
                    if (it.hasNext()){
                        censo = (CensoPlanInfo)it.next();
                        if (censo.getVariedad().getNombre().equals(var.getNombre()) &&
                                censo.getAño() == ciclo){
                            return true;
                        }
                    }
                    return false;
                }
                
		
		
		
}
