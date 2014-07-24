package base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Witzke Fernando Adrian
 * <p><b> Descripcion: </b></p> 
 *	Clase cel censo de Entrega, en este caso solo aplicable a Cooperativas
 *	cuya plantacion es la Mandioca. 
 */
 
public class CensoEntrega {
	//Argumentos
		private long numero; //numero del censo de plantacion
		private Fecha_wi fecha; //fecha del censo
		private String temporada; //Temporada de la Plantacion
                private long nroCenso; //Numero de censo por llegada
                private String mes1Año; //Mes de entrega de la plantacion de 1 Año
                private double porMes1; //Porcentaje del primer mes de entrega
                private String mes1AñoB; //Mes de Entrega de la plantacion de 1 Año, opcion B
                private double porMes1B; //Porcentaje del primer mes de entrega, opcion 2
                private String mes2Años; //Mes de entrega de la plantacion de 2 Años
                private double porMes2; //Porcentaje del segundo mes de entrega
                private String mes2AñosB; //Mes de Entrega de la plantacion de 2 Años, opcion B
                private double porMes2B; //Porcentaje del segundo mes de entrega, opcion B
                private long kgPorDia; //Cantidad de Kg por Dia
		private Map censoEntreInfos;  //map del detalle del censo (engorroso explicarlo)
		
	//Constructor
		public CensoEntrega(long numero, Fecha_wi fecha, String temporada, long nroCenso,
                        String mes1Año, double porMes1, String mes1AñoB, double porMes1B,
                        String mes2Años, double porMes2, String mes2AñosB, double porMes2B, long kgPorDia){
			this.numero = numero;
			this.fecha = fecha;
			this.temporada = temporada;
                        this.nroCenso = nroCenso;
			this.mes1Año = mes1Año;
                        this.porMes1 = porMes1;
                        this.mes1AñoB = mes1AñoB;
                        this.porMes1B = porMes1B;
                        this.mes2Años = mes2Años;
                        this.porMes2 = porMes2;
                        this.mes2AñosB = mes2AñosB;
                        this.porMes2B = porMes2B;
                        this.kgPorDia = kgPorDia;
                        censoEntreInfos = new HashMap();
		}
		
	//Metodos
		public void setNumero(long numero){ this.numero = numero;}
		public long getNumero(){ return this.numero;}
		
		public void setFecha(Fecha_wi fecha){ this.fecha = fecha;}
		public Fecha_wi getFecha(){ return this.fecha;}
		
		public void setTemporada(String temporada){ this.temporada = temporada;}
		public String getTemporada(){ return this.temporada;}
                
                public void setNroCenso(long nroCenso){ this.nroCenso = nroCenso;}
                public long getNroCenso(){ return this.nroCenso;}
                
                public void setMes1Año(String mes1Año){ this.mes1Año = mes1Año;}
                public String getMes1Año(){ return this.mes1Año;}
                
                public void setPorMes1(double porcentaje){ this.porMes1 = porcentaje;}
                public double getPorMes1(){ return this.porMes1;}
                
                public void setMes1AñoB(String mes1Año){ this.mes1AñoB = mes1Año;}
                public String getMes1AñoB(){ return this.mes1AñoB;}
                
                public void setPorMes1B(double porcentaje){ this.porMes1B = porcentaje;}
                public double getPorMes1B(){ return this.porMes1B;}
                
                public void setMes2AñosB(String mes2AñosB){ this.mes2AñosB = mes2AñosB;}
                public String getMes2AñosB(){ return this.mes2AñosB;}
                
                public void setMes2Años(String mes2Años){ this.mes2Años = mes2Años;}
                public String getMes2Años(){ return this.mes2Años;}
                
                public void setPorMes2(double porcentaje){ this.porMes2 = porcentaje;}
                public double getPorMes2(){ return this.porMes2;}
                
                public void setPorMes2B(double porcentaje){ this.porMes2B = porcentaje;}
                public double getPorMes2B(){ return this.porMes2B;}
                
                public void setKgPorDia(long kgPorDia){ this.kgPorDia = kgPorDia;}
                public long getKgPorDia(){ return this.kgPorDia;}
		
		
		/**
		 * Agrega un CensoEntreInfo en el map
		 * @param CensoEntregaInfo
		 */
		public void addCensoEntreInfos(CensoEntregaInfo entre){ this.censoEntreInfos.put(entre.getNumero(), entre);}
		/**
		 * Agrega un map dentro del map censoEntreInfos
		 * @param Map
		 */
		public void addAll(Map entres){ this.censoEntreInfos.putAll(entres);}
		/**
		 * Retorna un CensoEntreInfo
		 * @param int numero
		 */
		public CensoEntregaInfo getCensoEntreInfo(int numero){ return (CensoEntregaInfo)this.censoEntreInfos.get(new Integer(numero));}
		/**
		 * Retorna todo el map de CensoEntreInfo
		 * @return Map
		 */
		public Map getAllCensoEntreInfo(){ return this.censoEntreInfos;}
		/**
		 * Borra un CensoEntreInfo del Map
		 * @param int numero
		 */
		public void remCensoEntreInfo(int numero){ this.censoEntreInfos.remove(new Integer(numero));}
		
		/**
		 * Borra muchos censoEntreInfo, pasandole un Map 
		 * @param Map censoEntreInfos
		 */
		public void remAllCensoEntreInfos(Map censoEntreInfos){
			Iterator it = censoEntreInfos.values().iterator();
			CensoEntregaInfo censo; long numero = 0;
			while (it.hasNext()){
				censo = (CensoEntregaInfo)it.next();
				this.remCensoEntreInfo(censo.getNumero());
			}
		}
		
		/**
		 * Borra todos los censoEntreInfo 
		 * @param Map censoEntreInfos
		 */
		public void remAllCensoEntreInfos(){
			this.censoEntreInfos.clear();
		}
		
		
		/**
		 * Borra un CensoEntreInfo del Map sin poder conocer el numero
		 * @param Variedad una variedad
		 * @param double el kg
		 * @param int el año
		 */
		public void remCensoEntreInfo(Variedad variedad, double kg, int año){
			CensoEntregaInfo censoInfo = null;
			Iterator it = this.censoEntreInfos.values().iterator();
			while(it.hasNext()){
				censoInfo = (CensoEntregaInfo)it.next();
				if ((censoInfo.getVariedad().equals(variedad)) && (censoInfo.getKg() == kg) &&
						(censoInfo.getAño() == año)){
					break;
				}
			}
			this.remCensoEntreInfo(censoInfo.getNumero());
		}
		
		/**
		 * Retorna el ultimo id de un Mapa
		 * @param Map
		 * @return int
		 */
		public int getLastCount(Map unMap){
			int a = 0; CensoEntregaInfo censo; 
		    Iterator it = unMap.values().iterator();
			while (it.hasNext()){
				censo = (CensoEntregaInfo)it.next();
				if (censo.getNumero() > a){
					a = censo.getNumero();
				}
			}
			return a + 1;
		}
		
		/**
		 * Calcula el total de todas las CensoEntregaInfo con 1 año
		 * @return double el total
		 */
		public double getTotalKg1Año(){
			double total = 0; CensoEntregaInfo info;
			Iterator it = this.censoEntreInfos.values().iterator();
			while (it.hasNext()){
				info = (CensoEntregaInfo)it.next();
				if (info.getAño() == 1){
					total = total + info.getKg();
				}
			}
			return total;
		}
                
                /**
		 * Calcula el total de todas las CensoEntregaInfo con 1 año pasandole
                 * la Variedad
                 * @param var Variedad
		 * @return double el total
		 */
		public double getTotalKg1Año(Variedad var){
			double total = 0; CensoEntregaInfo info;
			Iterator it = this.censoEntreInfos.values().iterator();
			while (it.hasNext()){
				info = (CensoEntregaInfo)it.next();
				if (info.getAño() == 1){
                                    if (info.getVariedad().getNombre().equals(var.getNombre())){
                                        total = total + info.getKg();
                                    }
				}
			}
			return total;
		}
		
		/**
		 * Calcula el total de todas las CensoEntregaInfo con 2 año
		 * @return double el total
		 */
		public double getTotalKg2Año(){
			double total = 0; CensoEntregaInfo info;
			Iterator it = this.censoEntreInfos.values().iterator();
			while (it.hasNext()){
				info = (CensoEntregaInfo)it.next();
				if (info.getAño() == 2){
					total = total + info.getKg();
				}
			}
			return total;
		}
                
                /**
		 * Calcula el total de todas las CensoEntregaInfo con 2 año pasandole
                 * la Variedad
                 * @param var Variedad
		 * @return double el total
		 */
		public double getTotalKg2Año(Variedad var){
                    double total = 0; CensoEntregaInfo info;
                    Iterator it = this.censoEntreInfos.values().iterator();
                    while (it.hasNext()){
                            info = (CensoEntregaInfo)it.next();
                            if (info.getAño() == 2){
                                if (info.getVariedad().getNombre().equals(var.getNombre())){
                                    total = total + info.getKg();
                                }
                            }
                    }
                    return total;
		}
		
		/**
		 * Devuelve un CensoEntregaInfo
		 * @param lote
		 * @param variedad
		 * @param año
		 * @return CensoEntregaInfo censoEntregaInfo
		 */
		public CensoEntregaInfo getCensoEntregaInfo(String lote, Variedad variedad,int año){
			CensoEntregaInfo censoAux = null; CensoEntregaInfo censoInfo = null;
			Iterator it = this.censoEntreInfos.values().iterator(); 
			while (it.hasNext()){
				censoAux = (CensoEntregaInfo)it.next();
					if ((censoAux.getVariedad().equals(variedad)) &&  (lote.equals(censoAux.getLote())) && (censoAux.getAño() == año )){
						censoInfo = censoAux;
					}
				}
			return censoInfo;
		}

                /**
		 * Devuelve Map con CensoEntregaInfo
		 * @param variedad
		 * @param año
		 * @return Map HashMap
		 */
                public Map getCensoEntregaInfo(Variedad var, int año){
                    Map retornar = new HashMap(); CensoEntregaInfo censo;
                    Iterator it = this.censoEntreInfos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoEntregaInfo)it.next();
                        if ((censo.getVariedad().equals(var)) && (censo.getAño() == año)){
                            retornar.put(censo.getNumero(), censo);
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Procedimiento para que retorne la Cantidad de Kg del censo
                 * Entrega de 1 Año
                 * @return total Double
                 */
                public double getKg1año(){
                    CensoEntregaInfo censo; double kg = 0;
                    Iterator it = this.censoEntreInfos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoEntregaInfo)it.next();
                        if (censo.getAño() == 1){
                            kg = kg + censo.getKg();
                            
                        }
                    }
                    return kg;
                }
                
                /**
                 * Procedimiento para que retorne la Cantidad de Kg del censo
                 * Entrega de 1 Año pasandole la Variedad
                 * @param var Variedad
                 * @return total Double
                 */
                public double getKg1año(Variedad var){
                    CensoEntregaInfo censo; double kg = 0;
                    Iterator it = this.censoEntreInfos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoEntregaInfo)it.next();
                        if (censo.getAño() == 1 && 
                            censo.getVariedad().getNombre().equals(var.getNombre())){
                            
                            kg = kg + censo.getKg();
                        }
                    }
                    return kg;
                }
                
                 /**
                 * Procedimiento para que retorne la Cantidad de Kg del censo
                 * Entrega de 2 Año
                 * @return total Double
                 */
                public double getKg2año(){
                    CensoEntregaInfo censo; double kg = 0;
                    Iterator it = this.censoEntreInfos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoEntregaInfo)it.next();
                        if (censo.getAño() == 2){
                            kg = kg + censo.getKg();
                            
                        }
                    }
                    return kg;
                }
                
                /**
                 * Procedimiento para que retorne la Cantidad de Kg del censo
                 * Entrega de 2 Año pasandole la Variedad
                 * @param var Variedad
                 * @return total Double
                 */
                public double getKg2año(Variedad var){
                    CensoEntregaInfo censo; double kg = 0;
                    Iterator it = this.censoEntreInfos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoEntregaInfo)it.next();
                        if (censo.getAño() == 2 && 
                            censo.getVariedad().getNombre().equals(var.getNombre())){
                            
                            kg = kg + censo.getKg();
                        }
                    }
                    return kg;
                }
                
                /**
                 * Metodo que retorna si un Censo tiene una determinada variedad
                 * @param var Variedad
                 * @return boolean
                 */
                public boolean tieneVariedad(Variedad var, int ciclo){
                    CensoEntregaInfo censo;
                    Iterator it = this.censoEntreInfos.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoEntregaInfo)it.next();
                        if (censo.getVariedad().getNombre().equals(var.getNombre()) &&
                                censo.getAño() == ciclo){
                            return true;
                        }
                    }
                    return false;
                }
		
		
		
		
		

}
