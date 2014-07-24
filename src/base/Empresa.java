package base;
/*
 * Created on 02-Julio-2007
*/

/**
 * @author Adrian
**/

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



@SuppressWarnings("unchecked")
public class Empresa {
	//Argumentos
		
		private String nombre; /* nombre de la empresa */
		private String direccion; /* direccion de la empresa */
		private String localidad; /* localidad de ubicacion de la empresa */
		private String telefono; /* telefono de la empresa */
		private Map socios; /* todos los socios de la empresa */
		private Map terceros; /* todos los terceros de la empresa */
                private Options options; /* las opciones de la pantallas */
		public Configuraciones configruaciones; /* parametros del sistema */
		public static db.Coneccion con; /* coneccion a la base de datos */
		public static db.ConeccionDbf conXls; /* coneccion a sistema de Strieder */
               
                
		
		
	//Constructor
		
		public Empresa(String nombre, String direccion, String localidad, String telefono, Options options, Configuraciones conf){
			this.nombre = nombre;
			this.direccion = direccion;
			this.localidad = localidad;
			this.telefono = telefono;
			this.options = options;
			this.configruaciones = conf;
			socios = new HashMap();
                        terceros = new HashMap();
                        
			
		}
                
                public Empresa(String nombre, String direccion, String localidad, String telefono){
                    this.nombre = nombre;
                    this.direccion = direccion;
                    this.localidad = localidad;
                    this.telefono = telefono;
                    socios = new HashMap();
                    terceros = new HashMap();
                }
        
                
                public Empresa(){}
		
	//gets y sets
		//Nombre
			public void setNombre(String nombre){ this.nombre = nombre;}
			public String getNombre(){return this.nombre;}
		
		//Direccion
			public void setDireccion(String direccion){ this.direccion = direccion;}
			public String getDireccion(){ return this.direccion;}
		
		//Localidad
                        
                    
			public void setLocalidad(String localidad){ this.localidad = localidad;}
			public String getLocalidad(){ return this.localidad;}
		
		//Telefono
			public void setTelefono(String telefono){ this.telefono = telefono;}
			public String getTelefono(){ return this.telefono;}
		
		//Map socios
			public void addMapSocios(Map socios){this.socios.putAll(socios);}
			public Map getMapSocios(){ return this.socios;}
			public void addSocio(Socio unSocio){this.socios.put(new Long(unSocio.getNroSocio()), unSocio);}
			public Socio getSocio(long nroSocio){ return (Socio)this.socios.get(new Long(nroSocio));}
		
		//Map Terceros
			public void addMapTerceros(Map terceros){this.terceros.putAll(terceros);}
			public Map getMapTerceros(){ return this.terceros;}
			public void addTercero(Tercero unTercero){this.terceros.put(unTercero.getId(), unTercero);}
			public Tercero getTercero(int id){ return (Tercero)this.terceros.get(id);}

                //Options
                        public void setOptions(Options op){this.options = op;}
			public Options getOptions(){return this.options;}
		
		//Configuraciones
			public Configuraciones getConf(){ return this.configruaciones;}
			public void setConf(Configuraciones conf){ this.configruaciones = conf;}
			
			

			/**
			 * Retorna el id a utilizar para un nuevo socio
			 * @return long
			 */
			public long getLastIdSocio(){
				Iterator it; long a = 0; Socio so;
				Collection co = socios.values();
				it  = co.iterator();
				while (it.hasNext()){
					so = (Socio)it.next();
					if ((so.getNroSocio() > a) && (so.getNroSocio() < 20000)){
						a = so.getNroSocio();
					}
				}
				return a + 1;
			}
						
			
                        
                        /**
                         * Procedimiento que retorna el total de kg censado de
                         * todos los socios pasandole una temporada
                         * @param temporada String
                         * @return total Double
                         */
                        public double getTotal1KgCensoEntrega(String temporada){
                            double retornar = 0; Socio so;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotal1KgCensoEntrega(temporada);
                            }
                            return retornar;
                        }
                        
                        /**
                         * Procedimiento que retorna el total de kg censado de
                         * todos los socios pasandole una temporada
                         * @param temporada String
                         * @return total Double
                         */
                        public double getTotal2KgCensoEntrega(String temporada){
                            double retornar = 0; Socio so;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotal2KgCensoEntrega(temporada);
                            }
                            return retornar;
                        }
                        
                         /**
                         * Procedimiento que retorna el total de Hectareas
                         * censadas de todos los socios pasandole una temporada
                         * @param temporada String
                         * @return total Double
                         */
                        public double getTotal1Hec(String temporada){
                            double retornar = 0; Socio so;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotal1Hec(temporada);
                            }
                            return retornar;
                        }
                        
                        /**
                         * Procedimiento que retorna el total de Hectareas 2 años
                         * censadas de todos los socios pasandole una temporada
                         * @param temporada String
                         * @return total Double
                         */
                        public double getTotal2Hec(String temporada){
                            double retornar = 0; Socio so;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotal2Hec(temporada);
                            }
                            return retornar;
                        }
              /**
               * Retorna el numero del Censo Entrega, si no existe censo
               * retorna 0, sirve para traer el numero del censo entrega de una
               * temporada especifica.
               * @param temporada
               * @return
               */          
              public long getLastNroCensoEntrega(int temporada){
                  Socio unSocio; Iterator it = this.socios.values().iterator();
                  long retornar = 0; long aux;
                  while (it.hasNext()){
                      unSocio = (Socio)it.next();
                      aux = unSocio.getNroCensoEntrega(temporada);
                      if (aux > retornar){
                          retornar = aux;
                      }
                  }
                  return retornar + 1;
              }
              
               /**
                         * Procedimiento que retorna el total de kg censado de
                         * todos los socios pasandole una temporada
                         * @param temporada String
                         * @return total Double
                         */
                        public double getTotal1KgCensoP(String temporada){
                            double retornar = 0; Socio so;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotal1KgCensoP(temporada);
                            }
                            return retornar;
                        }
                        
                        /**
                         * Procedimiento que retorna el total de kg censado de
                         * todos los socios pasandole una temporada
                         * @param temporada String
                         * @return total Double
                         */
                        public double getTotal2KgCensoP(String temporada){
                            double retornar = 0; Socio so;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotal2KgCensoP(temporada);
                            }
                            return retornar;
                        }

                        /**
                         * Procedimiento que retorna el total de kg de Entregas
                         * de manidoca de 1 ciclo para una determinada temporada
                         * @param temporada - String
                         * @return  total - Double
                         */
                        public double getTotal1AñoEntrega(String temporada){
                            Socio so; double retornar = 0;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotalEntrega(temporada, "1 Año");
                            }
                            return retornar;
                        }

                        /**
                         * Procedimiento que retorna el total de kg de Entregas
                         * de manidoca de 2 ciclos para una determinada temporada
                         * @param temporada - String
                         * @return  total - Double
                         */
                        public double getTotal2AñoEntrega(String temporada){
                            Socio so; double retornar = 0;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotalEntrega(temporada, "2 Años");
                            }
                            return retornar;
                        }

                        /**
                         * Procedimiento que retorna el total de kg de Entregas
                         * de manidoca para una determinada temporada
                         * @param temporada - String
                         * @return  total - Double
                         */
                        public double getTotalEntrega(String temporada){
                            Socio so; double retornar = 0;
                            Iterator it = this.socios.values().iterator();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                retornar = retornar + so.getTotalEntrega(temporada, "Todos");
                            }
                            return retornar;
                        }
                        
                        /**
                         * Procedimiento que retornan todos los tipos de Cubierto
                         * en una temporada
                         * @param String temporada
                         * @return List
                         */
                        public List getCubierto(String temporada){
                            List retornar = new ArrayList(); Socio unSocio;
                            List vector = new ArrayList(); Iterator itAux;
                            Iterator it = this.socios.values().iterator();
                            Object ob;
                            while(it.hasNext()){
                                unSocio = (Socio)it.next();
                                vector.clear();
                                vector.addAll(unSocio.getCubierto(temporada));
                                itAux = vector.iterator();
                                while (itAux.hasNext()){
                                    ob = itAux.next();
                                    if (!(retornar.contains(ob))){
                                        retornar.add(ob);
                                    }
                                }
                                
                            }
                            return retornar;
                        }
                        
                        /**
                         * Procedimiento que retorna un conjunto de socios que 
                         * tiene un porcentaje de cubierto en una temporada dada
                         * @param String temporada
                         * @param double cubierto
                         * @return
                         */
                        public Map getSocios(String temporada, double cubierto){
                            Iterator it = this.socios.values().iterator();
                            Socio so; Map retornar = new HashMap();
                            while (it.hasNext()){
                                so = (Socio)it.next();
                                if (so.tieneCubierto(temporada, cubierto)){
                                    retornar.put(so.getNroSocio(), so);
                                }
                            }
                            return retornar;
                        }
                        
                        
                        
                 /**
                 * Procedimiento que retorna los socios que tienen censo plantacion en una temporada
                 * @param String temporada
                 * @return Map Socios
                 */
                public Map getSociosConCensoPlantacion(String temporada){
                    Iterator it = this.socios.values().iterator();
                    Socio unSocio; Map retornar = new HashMap();
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        if(unSocio.getCensoPlantacion(temporada) != null){
                            retornar.put(unSocio.getNroSocio(), unSocio);
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Procedimiento que retorna los socios que No tienen censo plantacion en una temporada
                 * @param String temporada
                 * @return Map Socios
                 */
                public Map getSociosSinCensoPlantacion(String temporada){
                    Iterator it = this.socios.values().iterator();
                    Socio unSocio; Map retornar = new HashMap();
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        if((unSocio.getCensoPlantacion(temporada) == null) &&
                        ((unSocio.getEstado().equals("activo") || (unSocio.getEstado().equals("inactivo"))))){
                            retornar.put(unSocio.getNroSocio(), unSocio);
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Procedimiento que retorna los socios que tienen censo Entrega en una temporada
                 * @param String temporada
                 * @return Map Socios
                 */
                public Map getSociosConCensoEntrega(String temporada){
                    Iterator it = this.socios.values().iterator();
                    Socio unSocio; Map retornar = new HashMap();
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        if(unSocio.getCensoEntrega(temporada) != null){
                            retornar.put(unSocio.getNroSocio(), unSocio);
                        }
                    }
                    return retornar;
                }
                
                 /**
                 * Procedimiento que retorna los socios que No tienen censo entrega en una temporada
                 * @param String temporada
                 * @return Map Socios
                 */
                public Map getSociosSinCensoEntrega(String temporada){
                    Iterator it = this.socios.values().iterator();
                    Socio unSocio; Map retornar = new HashMap();
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        if((unSocio.getCensoEntrega(temporada) == null) &&
                        ((unSocio.getEstado().equals("activo") || (unSocio.getEstado().equals("inactivo"))))){
                            retornar.put(unSocio.getNroSocio(), unSocio);
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Funcion que retorna un mapa de los socios, en cuya llave del map
                 * esta ordenado de mayor a menor en cantidad de Hectareas
                 * @param temporada String
                 * @param ciclo String
                 * @return mapa Map
                 */
                public Map getRankingCensoPlantacion(String temporada, String ciclo){
                    Socio unSocio; Iterator it = this.socios.values().iterator();
                    Map retornar = new HashMap(); double nro;
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        nro = unSocio.getRankingCensoPlantacion(temporada, ciclo);
                        if (nro != 0){
                            while (retornar.get(nro) != null){
                                nro = nro + 0.1;
                            }
                            retornar.put(nro, unSocio);
                        }
                    }
                    return retornar;
                }
                
                 /**
                 * Funcion que retorna un mapa de los socios, en cuya llave del map
                 * esta ordenado de mayor a menor en cantidad de Kg
                 * @param temporada String
                 * @param ciclo String 
                 * @return mapa Map
                 */
                public Map getRankingCensoEntrega(String temporada, String ciclo){
                    Socio unSocio; Iterator it = this.socios.values().iterator();
                    Map retornar = new HashMap(); double nro;
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        nro = unSocio.getRankingCensoEntrega(temporada, ciclo);
                        if (nro != 0){
                            while (retornar.get(nro) != null){
                                nro = nro + 0.1;
                            }
                            retornar.put(nro, unSocio);
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Metodo que retornar un map de los socios, en cuya llave del mao
                 * esta ordenado de Mayor a Menor en cantidad de Kg de Entrega
                 * @param temporada String
                 * @param ciclo String
                 * @return Map
                 */
                public Map getRankingEntrega(String temporada, String ciclo){
                    Socio unSocio; Iterator it = this.socios.values().iterator();
                    Map retornar = new HashMap(); double nro;
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        nro = unSocio.getTotalEntrega(temporada, ciclo);
                        if (nro != 0){
                            while (retornar.get(nro) != null){
                                nro = nro + 0.1;
                            }
                            retornar.put(nro, unSocio);
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Metodo que retornar un map de los socios, en cuya llave del map
                 * esta ordenado de Mayor a Menor en cantidad de Kg de Entrega
                 * @return Map
                 */
                public Map getRankingEntregaAll(String ciclo){
                    Socio unSocio; Iterator it = this.socios.values().iterator();
                    Map retornar = new HashMap(); double nro;
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        nro = unSocio.getTotalEntrega(ciclo);
                        if (nro != 0){
                            while (retornar.get(nro) != null){
                                nro = nro + 0.1;
                            }
                            retornar.put(nro, unSocio);
                        }
                    }
                    return retornar;
                }
                
                  /**
                 * Funcion que retorna un mapa de los socios, en cuya llave del map
                 * esta ordenado de mayor a menor en cantidad de Hectareas
                 * @return mapa Map
                 */
                public Map getRankingCensoPlantacionAll(String ciclo){
                    Socio unSocio; Iterator it = this.socios.values().iterator();
                    Map retornar = new HashMap(); double nro;
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        nro = unSocio.getRankingCensoPlantacionAll(ciclo);
                        if (nro != 0){
                            while (retornar.get(nro) != null){
                                nro = nro + 0.1;
                            }
                            retornar.put(nro, unSocio);
                        }
                    }
                    return retornar;
                }
                
                
                
                 /**
                 * Funcion que retorna un mapa de los socios, en cuya llave del map
                 * esta ordenado de mayor a menor en cantidad de Kg
                 * @parameters ciclo String 
                 * @return mapa Map
                 */
                public Map getRankingCensoEntregaAll(String ciclo){
                    Socio unSocio; Iterator it = this.socios.values().iterator();
                    Map retornar = new HashMap(); double nro;
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        nro = unSocio.getRankingCensoEntregaAll(ciclo);
                        if (nro != 0){
                            while (retornar.get(nro) != null){
                                nro = nro + 0.1;
                            }
                            retornar.put(nro, unSocio);
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Funcion que retorna el Numero de entrega mas alto, el ultimo
                 * @return long numero;
                 */
                public long getUltimoNumeroEntrega(){
                    Iterator it = this.socios.values().iterator(); Socio unSocio;
                    long retornar = 0;
                    while (it.hasNext()){
                        unSocio = (Socio)it.next();
                        if (retornar < unSocio.getUltimoNumeroEntrega()){
                            retornar = unSocio.getUltimoNumeroEntrega();
                        }
                    }
                    return retornar;
                }
                
                
                static{
                    con = new db.Coneccion();
                    
		}
                
                           
                        
                        
	
}
