package base;

public class MovInformativo {
	//Atributos
		
		private long numero; /* Numero de la informacion */
		private Fecha_wi fecha; /* Fecha de la informacion */
		private String descripcion; /* Descripcion de la informacion */
	
	//Constructor
		
		public MovInformativo (long numero, Fecha_wi fecha, String descripcion){
			this.numero = numero;
			this.fecha = fecha;
			this.descripcion = descripcion;
		}
		
	//Gets y Sets
		//Numero
		public void setNumero(long nro){ this.numero = nro;}
		public long getNumero(){ return this.numero;}
	
		//Fecha
		public void setFecha(Fecha_wi fecha){ this.fecha = fecha;}
		public Fecha_wi getFecha(){ return this.fecha;}
		
		//Descripcin
		public void setDescripcion(String descripcion){ this.descripcion= descripcion;}
		public String getDescripcion(){ return this.descripcion;}
		
}
