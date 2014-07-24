package base;


/**
 * @author Fernando Adrian Witzke 23-Octubre-2007
 * <p>Clase encargada de Manejar el Objeto Variedad de mandioca.</p>
 */
public class Variedad {
	//Argumentos
		private int numero; //numero de la variedad
		private String nombre; //nombre de la variedad
		private double kgHec1Año; //kilos de Mandioca posibles en 1 año
		private double kgHec2Año; //kilos de Mandioca posibles en 2 años
		
	//Constructor
		/**
		 * @param numero - Numero de la Variedad 
		 * @param nombre - Nombre de la Variedad
		 * @param kgHec1año - Kilos en una hectarea de una plantacion de 1 año
		 * @param kgHec2año - Kilos en una hectarea de una plantacion de 2 años
		 */
		public Variedad(int numero, String nombre, double kgHec1año, double kgHec2año){
			this.numero = numero;
			this.nombre = nombre;
			this.kgHec1Año = kgHec1año;
			this.kgHec2Año = kgHec2año;
		}
		
	//Metodos
		public void setNumero(int numero){this.numero = numero;}
		public int getNumero(){ return this.numero;}
		public void setNombre(String nombre){this.nombre = nombre;}
		public String getNombre(){ return this.nombre;}
		public void setKgHec1Año(double kg){ this.kgHec1Año = kg;}
		public double getKgHec1Año(){ return this.kgHec1Año;}
		public void setKgHec2Año(double kg){ this.kgHec2Año = kg;}
		public double getKgHec2Año(){ return this.kgHec2Año;}
		
	public String toString(){
		return this.numero + " " + this.nombre;
	}
}
