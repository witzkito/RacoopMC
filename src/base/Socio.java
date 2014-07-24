package base;
/*
 * Created on 02-jul-2007
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
import java.util.TreeMap;



@SuppressWarnings("unchecked")
public class Socio {
	//Argumentos
		private long nroSocio; /* Numero del socio */
		private String nomApe; /* Nombre y Apellido del Socio */
		private String domicilio; /* domicilio del socio */
		private String tipoDoc; /* tipo de documento del socio: DNI LE LC CI */
		private String nroIden; /* numero de identificacion del documento */
		private String cuit; /* cuit del socio */
		private String tipoIva; /* situacion ante el iva del socio: RI RNI EXE MTR CF */
		private Fecha_wi fechaIngreso; /* fecha de ingreso del asocioado a la cooperativa */
		private String actaIngreso; /* numero del acta por el ingreso */
                private Fecha_wi fechaInactivo; /* fecha de inactivo del asocioado */
		private String actaInactivo; /* numero del acta por el inactivo */
		private Fecha_wi fechaEgreso; /* fecha de salida del asociado a la cooperativa */
		private String actaEgreso;  /* numero de acta por el egreso */
		private Map movimientos; /* movimientos de los socios */
		private String observaciones; /* algunas obvservaciones */
		private String estado; /* el estado del socio */
		private Map informaciones; /* informaciones del socio */
		private Map censoPlantacion; /* censos de plantacion */
                private Map censoEntrega; /* censos de entrega */
		private Map telefonos; /*telefono del socio */
                private Map entrega; /*Entrega del material en fabrica */
		
	//Constructor
		public Socio(long nroSocio, String nomApe, String domicilio, String tipoDoc, String nroIden, String cuit,
					 String tipoIva, Fecha_wi fechaIngreso, String actaIngreso,Fecha_wi fechaInactivo, String actaInactivo, Fecha_wi fechaEgreso,
					 String actaEgreso, String observaciones, String estado){
			this.nroSocio = nroSocio;
			this.nomApe = nomApe;
			this.domicilio = domicilio;
			this.tipoDoc = tipoDoc;
			this.nroIden = nroIden;
			this.cuit = cuit;
			this.tipoIva = tipoIva;
			this.fechaIngreso = fechaIngreso;
			this.actaIngreso = actaIngreso;
                        this.fechaInactivo = fechaInactivo;
                        this.actaInactivo = actaInactivo;
			this.fechaEgreso = fechaEgreso;
			this.actaEgreso = actaEgreso;
			this.observaciones = observaciones;
			this.estado = estado;
                       	this.movimientos = new HashMap();
			this.informaciones = new HashMap();
			this.censoPlantacion = new HashMap();
			this.censoEntrega = new HashMap();
                        this.entrega = new HashMap();
	}
	
	//Gets y sets
		//nroSocio
		public void setNroSocio(long nroSocio){this.nroSocio = nroSocio; }
		public long getNroSocio(){ return this.nroSocio;}
		
		//nomApe
		public void setNomApe(String nomApe){this.nomApe = nomApe;}
		public String getNomApe(){return this.nomApe;}
		
		//domicilio
		public void setDomicilio(String domi){this.domicilio = domi;}
		public String getDomicilio(){return this.domicilio;}
		
		//tipoDoc
		public void setTipoDoc(String tipo){this.tipoDoc = tipo;}
		public String getTipoDoc(){return this.tipoDoc;}
		
		//nroIden
		public void setNroIden(String nroIden){this.nroIden = nroIden;}
		public String getNroIde(){return this.nroIden;}
		
		//cuit
		public void setCuit(String cuit){this.cuit = cuit;}
		public String getCuit(){return this.cuit;}
		
		//tipoIva
		public void setTipoIva(String tipoIva){this.tipoIva = tipoIva;}
		public String getTipoIva(){return this.tipoIva;}
		
		//fechaIngreso
		public void setFechaIngreso(Fecha_wi fechaIngreso){this.fechaIngreso = fechaIngreso;}
		public Fecha_wi getFechaIngreso(){return this.fechaIngreso;}
		
		//actaIngreso
		public void setActaIngreso(String actaIngreso){this.actaIngreso = actaIngreso;}
		public String getActaIngreso(){return this.actaIngreso;}
                
                 //fechaInactivo
		public void setFechaInactivo(Fecha_wi fechaInactivo){this.fechaInactivo = fechaInactivo;}
		public Fecha_wi getFechaInactivo(){return this.fechaInactivo;}
		
                //actaInactivo
		public void setActaInactivo(String actaInactivo){this.actaInactivo = actaInactivo;}
		public String getActaInactivo(){return this.actaInactivo;}
                		
		//fechaEgreso
		public void setFechaEgreso(Fecha_wi fechaEgreso){this.fechaEgreso = fechaEgreso;}
		public Fecha_wi getFechaEgreso(){return this.fechaEgreso;}
		
		//actaEgreso
		public void setActaEgreso(String actaEgreso){this.actaEgreso = actaEgreso;}
		public String getActaEgreso(){return this.actaEgreso;}
		
		//observaciones
		public void setObservaciones(String obser){ this.observaciones = obser;}
		public String getObservaciones(){ return this.observaciones;}
		
		//Movimientos de los socios
		public void setAllMovimientos(Map movimientos){ this.movimientos = movimientos;}
		public Map getAllMovimientos(){ return this.movimientos;}
		
		//estado
		public void setEstado(String estado){ this.estado = estado;}
		public String getEstado(){ return this.estado;}
		
		
		
		//informaciones
		public void setAllInformaciones(Map informaciones){this.informaciones = informaciones;}
		public Map getAllInformaciones(){ return this.informaciones;}
		public void addInformacion(MovInformativo info){ this.informaciones.put(info.getNumero(),info);} 
		public MovInformativo getInformacion(long nroInfo){ return (MovInformativo)this.informaciones.get(nroInfo);}
		public void remInformacion(long nroInfo){ this.informaciones.remove(nroInfo);}
		public void remMovInfo(String descripcion){ 
			MovInformativo mov; MovInformativo aux = null;
			Iterator it = this.informaciones.values().iterator();
			while (it.hasNext()){
				mov= (MovInformativo)it.next();
				if (mov.getDescripcion().equals(descripcion)){
					aux = mov;
				}
			}
			if (aux != null){
				this.remInformacion(aux.getNumero());
			}
		}
		//censo plantacion
		public void setAllCensoPlantacion(Map censoPlantacion){this.censoPlantacion = censoPlantacion;}
		public Map getAllCensoPlantacion(){ return this.censoPlantacion;}
		public void addCensoPlantacion(CensoPlantacion censo){ this.censoPlantacion.put(censo.getNumero(), censo);} 
		public CensoPlantacion getCensoPlantacion(long nroCenso){ return (CensoPlantacion)this.censoPlantacion.get(new Long(nroCenso));}
		public void remCensoPlantacion(long nroCenso){ this.censoPlantacion.remove(nroCenso);}
		
                //Telefono
                public void setAllTelefonos(Map telefonos){this.telefonos = telefonos;}
		public Map getAllTelefonos(){ return this.telefonos;}
		public void addTelefono(Telefono tel){ this.telefonos.put(tel.getTipo(), tel);} 
		public Telefono getTelefono(String tipo){ return (Telefono)this.telefonos.get(String.valueOf(tipo));}
		public void remTelefono(String tipo){ this.telefonos.remove(tipo);}
		
                //Entrega
                public void setAllEntrega(Map entregas){ this.entrega = entregas;}
                public Map getAllEntregas(){ return this.entrega;}
                public void addEntrega(Entrega ent){ this.entrega.put(ent.getNumero(), ent);}
                public void remEntrega(long numero){ this.entrega.remove(numero);}
                
                /**
                 * Trae todas las Entregas de una Temporada
                 * @param temporada String
                 * @return Map
                 */
                public Map getAllEntregas(String temporada){
                    Map retornar = new HashMap(); Entrega ent;
                    Iterator it = this.entrega.values().iterator();
                    while (it.hasNext()){
                        ent = (Entrega)it.next();
                        if (ent.getTemporada().equals(temporada)){
                            retornar.put(ent.getNumero(), ent);
                        }
                    }
                    return retornar;
                }
  
                /**
		 * Busca un Censo Plantacion pasandole una temporada
		 * @param String temporada
		 * @return CensoPlantacion
		 */
		public CensoPlantacion getCensoPlantacion(String temporada){ 
			CensoPlantacion censo = null; CensoPlantacion retornar = null;
			Iterator it = this.censoPlantacion.values().iterator();
			while (it.hasNext()){
				censo = (CensoPlantacion)it.next();
				if(censo.getTemporada().equals(temporada)){
					retornar = censo;
				}
			}
			return retornar;
		}
		
		
	//Metodos
		
				
		
		/**
		 * Trae el ultimo id de informaciones
		 * @return long
		 */
		public long getLastIdMovInformativo(){
			Iterator it; long a = 0; MovInformativo mo;
			Collection co = informaciones.values();
			it  = co.iterator();
			while (it.hasNext()){
				mo = (MovInformativo)it.next();
				if (mo.getNumero() > a){
					a = mo.getNumero();
				}
			}
			return a + 1;
		}
		
		/**
		 * Trae el ultimo id de CensoPlantacion
		 * @return long
		 */
		public long getLastIdCensoPlantacion(){
			Iterator it; long a = 0; CensoPlantacion ce;
			it  = this.censoPlantacion.values().iterator();
			while (it.hasNext()){
				ce = (CensoPlantacion)it.next();
				if (ce.getNumero() > a){
					a = ce.getNumero();
				}
			}
			return a + 1;
		}
                
                
                /**
                 * Procedimiento que retorna la suma de hectareas para un Socio
                 * determinado pasandole una temporada y el tipo de cubierto
                 * @param String temporada
                 * @param Double cubierto
                 * @return double
                 */
                public double getTotalHec(String temporada, double cubierto){
                    return this.getCensoPlantacion(temporada).getTotalHec(cubierto);
                }
                
                
                 /**
                 * Procedimiento que retorna el total de Hectareas censado, para 
                 * una temporada, 1 año
                 * @param temporada String
                 * @return total double
                 */
                public double getTotal1Hec(String temporada){
                   CensoPlantacion censo;
                   censo = this.getCensoPlantacion(temporada);
                   if (censo != null){
                        return (censo.getHec1año());
                   }else{
                       return 0;
                   }
                 }
                
                 /**
                 * Procedimiento que retorna el total de Hectareas censado, para 
                 * una temporada, 2 año
                 * @param temporada String
                 * @return total double
                 */
                public double getTotal2Hec(String temporada){
                   CensoPlantacion censo;
                   censo = this.getCensoPlantacion(temporada);
                   if (censo != null){
                        return (censo.getHec2año());
                   }else{
                       return 0;
                   }
                 }
                
                public double getTotal1KgCensoEntrega(String temporada){
                   CensoEntrega censo;
                   censo = this.getCensoEntrega(temporada);
                   if (censo != null){
                        return (censo.getKg1año());
                   }else{
                       return 0;
                   }
                 }
                
                 public double getTotal1KgCensoEntrega(String temporada, Variedad var){
                   CensoEntrega censo;
                   censo = this.getCensoEntrega(temporada);
                   if (censo != null){
                       return (censo.getKg1año(var));
                   }else{
                       return 0;
                   }
                 }
                
                /**
                 * Procedimiento que retorna el total de Hectareas censado, para 
                 * una temporada, 2 año
                 * @param temporada String
                 * @return total double
                 */
                public double getTotal2KgCensoEntrega(String temporada){
                   CensoEntrega censo;
                   censo = this.getCensoEntrega(temporada);
                   if (censo != null){
                        return (censo.getKg2año());
                   }else{
                       return 0;
                   }
                 }
                
                /**
                 * Procedimiento que retorna el total de Hectareas censado, para 
                 * una temporada, 2 año
                 * @param temporada String
                 * @param var Variedad
                 * @return total double
                 */
                public double getTotal2KgCensoEntrega(String temporada, Variedad var){
                   CensoEntrega censo;
                   censo = this.getCensoEntrega(temporada);
                   if (censo != null){
                       return (censo.getKg2año(var));
                   }else{
                       return 0;
                   }
                 }
                
                //censo entrega
		public void setAllCensoEntrega(Map censoEntrega){this.censoEntrega = censoEntrega;}
		public Map getAllCensoEntrega(){ return this.censoEntrega;}
		public void addCensoEntrega(CensoEntrega censo){ this.censoEntrega.put(censo.getNumero(), censo);} 
		public void remCensoEntrega(long nroCenso){ this.censoEntrega.remove(nroCenso);}
		/**
		 * Busca un Censo Entrega pasandole una temporada
		 * @param String temporada
		 * @return CensoEntrega
		 */
		public CensoEntrega getCensoEntrega(String temporada){ 
			CensoEntrega censo = null; CensoEntrega retornar = null;
			Iterator it = this.censoEntrega.values().iterator();
			while (it.hasNext()){
				censo = (CensoEntrega)it.next();
				if(censo.getTemporada().equals(temporada)){
					retornar = censo;
				}
			}
			return retornar;
		}
                
                /**
		 * Busca un Censo Entrega pasandole una temporada
		 * @param int temporada
		 * @return CensoEntrega
		 */
		public CensoEntrega getCensoEntrega(int temporada){ 
			CensoEntrega censo = null; CensoEntrega retornar = null;
			Iterator it = this.censoEntrega.values().iterator();
			while (it.hasNext()){
				censo = (CensoEntrega)it.next();
				if(censo.getTemporada().equals(String.valueOf(temporada))){
					retornar = censo;
				}
			}
			return retornar;
		}


                
                /**
		 * Trae el ultimo id de CensoEntrega
		 * @return long
		 */
		public long getLastIdCensoEntrega(){
			Iterator it; long a = 0; CensoEntrega ce;
			it  = this.censoEntrega.values().iterator();
			while (it.hasNext()){
				ce = (CensoEntrega)it.next();
				if (ce.getNumero() > a){
					a = ce.getNumero();
				}
			}
			return a + 1;
		}
                
                /**
                 * Retorna el numero del Censo Entrega, si no existe censo
                 * retorna 0, sirve para traer el numero del censo entrega de una
                 * temporada especifica.
                 * @param String temporada
                 * @return long numeroCenso
                 */
                public long getNroCensoEntrega(int temporada){
                    if (this.getCensoEntrega(temporada) != null){
                        return this.getCensoEntrega(temporada).getNroCenso();
                    }else{
                        return 0;
                    }
                }
                
                /**
                 * Procedimiento que retorna el total de Kg censado, para 
                 * una temporada
                 * @param temporada String
                 * @return total double
                 */
                public double getTotal1KgCensoP(String temporada){
                   CensoPlantacion censo;
                   censo = this.getCensoPlantacion(temporada);
                   if (censo != null){
                        return (censo.getKg1año());
                   }else{
                       return 0;
                   }
                   
                 }
                
                /**
                 * Procedimiento que retorna el total de Kg censado, para 
                 * una temporada
                 * @param temporada String
                 * @return total double
                 */
                public double getTotal2KgCensoP(String temporada){
                    CensoPlantacion censo;
                    censo = this.getCensoPlantacion(temporada);
                     if (censo != null){
                        return (censo.getKg2año());
                     }else{
                        return 0;
                     }
                 }
                
                /**
                 * Procedimiento que retorna un List con todos los cubierto de 
                 * un Socio  en una temporada dada
                 * @param Strig temporada
                 * @return List
                 */
                public List getCubierto(String temporada){
                    List vector = new ArrayList();
                    if(this.getCensoPlantacion(temporada) != null){
                        vector.addAll(this.getCensoPlantacion(temporada).getCubierto());
                    }
                    return vector;
                }
                
                /**
                 * Procedimiento que retorna un boolean si el socio tiene cubierto
                 * en una temporada dada
                 * @param String temporada
                 * @param double cubierto
                 * @return
                 */
                public boolean tieneCubierto(String temporada, double cubierto){
                    if (this.getCensoPlantacion(temporada) != null){
                        if(this.getCensoPlantacion(temporada).tieneCubierto(cubierto)){
                            return true;
                        }
                    }
                    return false;
                    
                }
                
                /**
                 * Funcion que retornar el total de Hectareas calculando junto
                 * al cubierto y germinado.
                 * @param temporada String
                 * @param ciclo String
                 * @return total double
                 */
                public double getRankingCensoPlantacion(String temporada, String ciclo){
                    CensoPlantacion censo; censo = this.getCensoPlantacion(temporada);
                    double retornar = 0;
                    if (censo == null){
                        return retornar;
                    }else{
                        if (ciclo.equals("Todos")){
                            retornar = censo.getTotalHec();
                        }else if (ciclo.equals("1 Año")){
                            retornar = censo.getTotal1AñoHec();
                        }else{
                            retornar = censo.getTotal2AñoHec();
                        }
                    }
                    return retornar;
                }
                
                           
                
                 /**
                 * Funcion que retornar el total de Kg
                 * @param temporada String
                 * @param ciclo String
                 * @return total double
                 */
                public double getRankingCensoEntrega(String temporada, String ciclo){
                    CensoEntrega censo; censo = this.getCensoEntrega(temporada);
                    double retornar = 0;
                    if (censo == null){
                        return retornar;
                    }else{
                        if (ciclo.equals("Todos")){
                            retornar = censo.getTotalKg1Año() + censo.getTotalKg2Año();
                        }else if (ciclo.equals("1 Año")){
                            retornar = censo.getTotalKg1Año();
                        }else{
                            retornar = censo.getTotalKg2Año();
                        }
                    }
                    return retornar;
                }
                
                 /**
                 * Funcion que retornar el total de Hectareas calculando junto
                 * al cubierto y germinado.
                 * @param temporada String
                 * @return total double
                 */
                public double getRankingCensoPlantacionAll(String ciclo){
                    double retornar = 0; CensoPlantacion censo;
                    Iterator it = this.censoPlantacion.values().iterator();
                    while (it.hasNext()){
                        censo = (CensoPlantacion)it.next();
                        if (ciclo.equals("Todos")){
                            retornar = retornar + censo.getTotalHec();
                        }else if (ciclo.equals("1 Año")){
                            retornar = retornar + censo.getTotal1AñoHec();
                        }else{
                            retornar = retornar + censo.getTotal2AñoHec();
                        }
                    }
                    return retornar;
                }
                
                 /**
                 * Funcion que retornar el total de Kg para el ranking
                 * @param ciclo String 
                 * @return total double
                 */
                public double getRankingCensoEntregaAll(String ciclo){
                    CensoEntrega censo; Iterator it = this.censoEntrega.values().iterator();
                    double retornar = 0;
                    while (it.hasNext()){
                        censo = (CensoEntrega)it.next();
                        if (ciclo.equals("Todos")){
                            retornar = retornar + (censo.getTotalKg1Año() + censo.getTotalKg2Año());
                        }else if (ciclo.equals("1 Año")){
                            retornar = retornar + (censo.getTotalKg1Año());
                        }else{
                            retornar = retornar + (censo.getTotalKg2Año());
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Metodo que debuelve el total de kg de una Entrega pasandole
                 * una Temporada
                 * @param temporada String
                 * @param ciclo String
                 * @return double
                 */
                public double getTotalEntrega(String temporada, String ciclo){
                    Entrega ent; Iterator it = this.getAllEntregas(temporada).values().iterator();
                    double retornar = 0;
                    while (it.hasNext()){
                        ent = (Entrega)it.next();
                        if (ciclo.equals("Todos")){
                            retornar = retornar + ent.getKg();
                        }else if (ciclo.equals("1 Año")){
                            if (ent.getCiclo() == 1){
                                retornar = retornar + ent.getKg();
                            }
                        }else{
                            if (ent.getCiclo() == 2){
                                retornar = retornar + ent.getKg();
                            }
                        }
                        
                    }
                    return retornar;
                }
                
                /**
                 * Metodo que debuelve el total de kg de una Entrega pasandole
                 * una Temporada, el Ciclo y la variedad
                 * "1 Año", "Todos", "2 Años"
                 * @param temporada String
                 * @param ciclo String
                 * @param var Variedad
                 * @return double
                 */
                public double getTotalEntrega(String temporada, String ciclo, Variedad var){
                    Entrega ent; Iterator it = this.getAllEntregas(temporada).values().iterator();
                    double retornar = 0;
                    while (it.hasNext()){
                        ent = (Entrega)it.next();
                        if (ciclo.equals("Todos")){
                            if (ent.getVariedad().getNombre().equals(var.getNombre())){
                                retornar = retornar + ent.getKg();
                            }
                        }else if (ciclo.equals("1 Año")){
                            if (ent.getCiclo() == 1){
                                if (ent.getVariedad().getNombre().equals(var.getNombre())){
                                    retornar = retornar + ent.getKg();
                                }
                            }
                        }else{
                            if (ent.getCiclo() == 2){
                                if (ent.getVariedad().getNombre().equals(var.getNombre())){
                                    retornar = retornar + ent.getKg();
                                }
                            }
                        }
                        
                    }
                    return retornar;
                }
                
                /**
                 * Metodo que retorna la el total de entrega de un Socio
                 * @param temporada String
                 * @return total double
                 */
                public double getTotalEntregas(String temporada){
                    Entrega ent; Iterator it = this.getAllEntregas(temporada).values().iterator();
                    double retornar = 0;
                    while (it.hasNext()){
                        ent = (Entrega)it.next();
                        retornar = retornar + ent.getKg();
                        
                        
                    }
                    return retornar;
                }
                
                /**
                 * Metodo que debuelve el total de kg de una Entrega
                 * @param ciclo String
                 * @return double
                 */
                public double getTotalEntrega(String ciclo){
                    Entrega ent; Iterator it = this.entrega.values().iterator();
                    double retornar = 0;
                    while (it.hasNext()){
                        ent = (Entrega)it.next();
                        if (ciclo.equals("Todos")){
                            retornar = retornar + ent.getKg();
                        }else if (ciclo.equals("1 Año")){
                            if (ent.getCiclo() == 1){
                                retornar = retornar + ent.getKg();    
                            }
                        }else{
                            if (ent.getCiclo() == 2){
                                retornar = retornar + ent.getKg();
                            }
                        }
                    }
                    return retornar;
                }
                
                
                /**
                 * Funcion que retorna el numero de Entrega mas alto del socio
                 * @return long Numero
                 */
                public long getUltimoNumeroEntrega(){
                    Map sorted = new TreeMap(this.entrega); Entrega ent;
                    Iterator it = sorted.values().iterator(); long retornar = 0;
                    while (it.hasNext()){
                        ent = (Entrega)it.next();
                        if (retornar < ent.getNumero()){
                            retornar = ent.getNumero();
                        }
                    }
                    return retornar;
                }
                
                /**
                 * Metodo que calcula el porcentaje de tolerancia del Socio,
                 * (censoEntrega * 100)/entrega
                 * @param temporda String
                 * @return porcentaje int
                 */
                public int getTolerancia(String temporda){
                    double censoE = (this.getTotal1KgCensoEntrega(temporda) + 
                            this.getTotal2KgCensoEntrega(temporda));
                    double ent = this.getTotalEntregas(temporda);
                    double total;
                    total = (ent * 100) / censoE;
                    if (censoE == 0 && ent != 0){
                        return 100;
                    }else{
                        return new Double(total - 100).intValue();
                    }
                }
                
                /**
                 * Metodo que calcula el porcentaje de tolerancia del Socio,
                 * (censoEntrega * 100)/entrega
                 * @param temporda String
                 * @param var Variedad
                 * @return porcentaje int
                 */
                public int getTolerancia(String temporda, Variedad var){
                    double censoE = (this.getTotal1KgCensoEntrega(temporda, var) + 
                            this.getTotal2KgCensoEntrega(temporda, var));
                    double ent = this.getTotalEntrega(temporda, "Todos", var);
                    double total;
                    total = (ent * 100) / censoE;
                    if (censoE == 0 && ent != 0){
                        return 100;
                    }else{
                        return new Double(total - 100).intValue();
                    }
                }
                
                /**
                 * Metodo que calcula el porcentaje de tolerancia del Socio,
                 * (censoEntrega * 100)/entrega
                 * @param temporda String
                 * @return porcentaje int
                 */
                public int getTolerancia1Año(String temporda){
                    double censoE = (this.getTotal1KgCensoEntrega(temporda));
                    double ent = this.getTotalEntrega(temporda, "1 Año");
                    double total;
                    total = (ent * 100) / censoE;
                    if (censoE == 0 && ent != 0){
                        return 100;
                    }else{
                        return new Double(total - 100).intValue();
                    }
                }
                
                /**
                 * Metodo que calcula el porcentaje de tolerancia del Socio,
                 * (censoEntrega * 100)/entrega
                 * @param temporda String
                 * @param variedad Variedad
                 * @return porcentaje int
                 */
                public int getTolerancia1Año(String temporda, Variedad var){
                    double censoE = (this.getTotal1KgCensoEntrega(temporda, var));
                    double ent = this.getTotalEntrega(temporda, "1 Año", var);
                    double total;
                    total = (ent * 100) / censoE;
                    if (censoE == 0 && ent != 0){
                        return 100;
                    }else{
                        return new Double(total - 100).intValue();
                    }
                }
                
                /**
                 * Metodo que calcula el porcentaje de tolerancia del Socio,
                 * (censoEntrega * 100)/entrega
                 * @param temporda String
                 * @return porcentaje int
                 */
                public int getTolerancia2Años(String temporda){
                    double censoE = (this.getTotal2KgCensoEntrega(temporda));
                    double ent = this.getTotalEntrega(temporda, "2 Años");
                    double total;
                    total = (ent * 100) / censoE;
                    if (censoE == 0 && ent != 0){
                        return 100;
                    }else{
                        return new Double(total - 100).intValue();
                    }
                }
                
                /**
                 * Metodo que calcula el porcentaje de tolerancia del Socio,
                 * (censoEntrega * 100)/entrega
                 * @param temporda String
                 * @param variedad Variedad
                 * @return porcentaje int
                 */
                public int getTolerancia2Años(String temporda, Variedad var){
                    double censoE = (this.getTotal2KgCensoEntrega(temporda, var));
                    double ent = this.getTotalEntrega(temporda, "2 Años", var);
                    double total;
                    total = (ent * 100) / censoE;
                    if (censoE == 0 && ent != 0){
                        return 100;
                    }else{
                        return new Double(total - 100).intValue();
                    }
                }
                
                /**
                 * Metodo que retorna si hay una entrega con una variedad pasandole
                 * una temporada
                 * @param var Variedad
                 * @param temporada String
                 * @return boolean
                 */
                public boolean getTieneVariedadEntrega(Variedad var, String temporada, int ciclo){
                    Iterator it = this.getAllEntregas(temporada).values().iterator();
                    Entrega ent;
                    while (it.hasNext()){
                        ent = (Entrega)it.next();
                        if (ent.getVariedad().getNombre().equals(var.getNombre()) &&
                                ent.getCiclo() == ciclo){
                            return true;
                        }
                    }
                    return false;
                }
                
               
                
                
}

                