package base;

/**
 * @author Witzkito
 * 	Clase encargada de los detalles del censo, como ser las hectareas plantadas, variedade,
 * 	porcentajes de cubierto, lote, ubicacion de la plantacion, si es propia o arrendada...
 */
public class CensoPlanInfo {
	//Argumentos
		private int numero; //numero del detalle del Censo de Plantacion
		private double hectareas; //numero de hectareas plantadas
		private Variedad variedad; //variedad del tipo de plantacion
		private double porcentaje; //porcentaje de cubierto de la plantacion
                private double germinacion; //porcentaje de cubierto garminado
		private String lote; //lote de ubicacion de la plantacion
		private Ciudad municipio; //municipio de la plantacion
		private char pa; //P = plantacion propia, A = plantacion arrendada
		private int año;	//edad de la cosecha
		
	//Constructor
		public CensoPlanInfo(int numero, double hectareas, Variedad variedad, double porcentaje, double germinacion, String lote, Ciudad municipio
								, char pa, int año){
			this.numero = numero;
			this.hectareas = hectareas;
			this.variedad = variedad;
			this.porcentaje = porcentaje;
                        this.germinacion = germinacion;
			this.lote = lote;
			this.municipio = municipio;
			this.pa = pa;
			this.año = año;
		}
		
	//Metodos
		//Get's y Set's
			public void setNumero(int numero){ this.numero = numero;}
			public int getNumero(){ return this.numero;}
			public void setHectareas(double hectareas){ this.hectareas = hectareas;}
			public double getHectareas(){ return this.hectareas;}
			public void setVariedad(Variedad variedad){ this.variedad = variedad;}
			public Variedad getVariedad(){ return this.variedad;}
			public void setPorcentaje(double porcentaje){ this.porcentaje = porcentaje;}
			public double getPorcentaje(){ return this.porcentaje;}
			public void setGerminacion(double germinacion){ this.germinacion = germinacion;}
                        public double getGerminacion(){ return this.germinacion;}
                        public void setLote(String lote){ this.lote = lote;}
			public String getLote(){ return this.lote;}
			public void setMunicipio(Ciudad municipio){ this.municipio = municipio;}
			public Ciudad getMunicipio(){ return this.municipio;}
			public void setPA(char pa){ this.pa = pa;}
			public char getPa(){ return this.pa;}
			public void setAño(int año){ this.año = año;}
			public int getAño(){ return this.año;}
                        
           /**
            * Procedimiento que retorna el total de PorcentajeGerminado
            * @return double
            */             
           public double getPorcentajeTotal(){
               return ((this.getPorcentaje() * this.getGerminacion())/100);
           }
		
		
}
