package base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Configuraciones {
	//Atributos
		private Map variedades; //Variedades de plantacion
                private Map ciudades; //Ciudades de las plantaciones o entregas
                private String bascula; //direccion de la base de datos de bascula
		
	//Constructor
				
		/**
		 * Constructor para el Modulo Censo
		 */
		public Configuraciones(String bascula){
			variedades = new HashMap();
                        ciudades = new HashMap();
                        this.bascula = bascula;
		}
		
	//Metodos
		/**
		 * Agrega una variedad al map
		 * @param Varieadad var
		 */
		public void addVariedad(Variedad var){ this.variedades.put(var.getNumero(),var);}
		/**
		 * Retorna una variedad del map
		 * @param int nro
		 * @return Variedad var
		 */
		public Variedad getVariedad(int nro){ return (Variedad)this.variedades.get(new Integer(nro));}
		/**
		 * Elimina una Variedad del Map
		 * @param int nro
		 */
		public void remVaridad(int nro){ this.variedades.remove(new Integer(nro));} 
		/** 
		 * Trae todos las variedades del Map
		 * @return Map variedades
		 */
		public Map getAllVariedades(){ return this.variedades;}
		/**
		 * Agrega muchas variedades al map
		 * @param Map variedades
		 */
		public void addAllVariedades(Map variedades){ this.variedades.putAll(variedades);}
                
                public void setBascula(String bascula){ this.bascula = bascula;}
                public String getBascula(){ return this.bascula;}
				
		/**
		 * Retorna el ultimo numero + 1 de Variedad
		 * @return int Numero
		 */
		public int getLastCountVariedad(){
			Iterator it; int a = 0; Variedad va;
			it  = this.variedades.values().iterator();
			while (it.hasNext()){
				va = (Variedad)it.next();
				if ((va.getNumero() > a)){
					a = va.getNumero();
				}
			}
			return a + 1;
		}
		
		/**
		 * Retorna una Variedad tras pasarte el nombre de la variedad
		 * @param String variedad
		 * @return Variedad
		 */
		public Variedad getVariedad(String variedad){
			Variedad var; Variedad ret = null;
			Iterator it = this.variedades.values().iterator();
			while (it.hasNext()){
				var = (Variedad)it.next();
				if (var.getNombre().equals(variedad)){
					ret = var;
				}
			}
			return ret;
		}
                
                /**
		 * Agrega una Ciudad al map
		 * @param Ciudad unaCiudad
		 */
		public void addCiudad(Ciudad unaCiudad){
                    this.ciudades.put(unaCiudad.getNumero(), unaCiudad);
                }
		/**
		 * Retorna una Ciudad del map
		 * @param int nro
		 * @return Ciudad unaCiudad
		 */
		public Ciudad getCiudad(int nro){
                    return (Ciudad)this.ciudades.get(nro);
                }
                
                /**
                 * Retorna una ciudad pasandole el nombre
                 * @param nombre String
                 * @return Ciudad
                 */
                public Ciudad getCiudad(String nombre){
                    Iterator it = this.ciudades.values().iterator();
                    Ciudad ciudad;
                    while (it.hasNext()){
                        ciudad = (Ciudad)it.next();
                        if (ciudad.getNombre().equals(nombre)){
                            return ciudad;
                        }
                    }
                    return null;
                }
		/**
		 * Elimina una Ciudad del Map
		 * @param int nro
		 */
		public void remCiudad(int nro){ this.ciudades.remove(new Integer(nro));} 
		/** 
		 * Trae todos las Ciudades del Map
		 * @return Map ciudades
		 */
		public Map getAllCiudades(){ return this.ciudades;}
		/**
		 * Agrega muchas Ciudades al map
		 * @param Map Ciudades
		 */
		public void addAllCiudades(Map ciudades){
                    this.ciudades.putAll(ciudades);
                }
				
		/**
		 * Retorna el ultimo numero + 1 de unaCiudad
		 * @return int Numero
		 */
		public int getLastCountCiudad(){
                    Iterator it; int a = 0; Ciudad unaCiudad;
                	it  = this.ciudades.values().iterator();
			while (it.hasNext()){
                            unaCiudad = (Ciudad)it.next();
                            if ((unaCiudad.getNumero() > a)){
                                a = unaCiudad.getNumero();
                            }
			}
                    return a + 1;
		}
}
