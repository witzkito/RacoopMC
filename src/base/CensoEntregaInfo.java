package base;

public class CensoEntregaInfo {
	//Argumentos
	private int numero; //numero del detalle del Censo de Entrega
	private String lote; //lote de ubicacion de la plantacion
	private Ciudad municipio; //municipio de la plantacion
	private char pa; //P = plantacion propia, A = plantacion arrendada
	private Variedad variedad; //variedad del tipo de plantacion
	private double kg; //Cantidad de kilos entregados
	private int año; //Año de la plantacion
	
		
	//Constructor
		public CensoEntregaInfo(int numero, String lote, Ciudad municipio, char pa, Variedad variedad, double kg, int año){
			this.numero = numero;
			this.lote = lote;
			this.municipio = municipio;
			this.pa = pa;
			this.variedad = variedad;
			this.kg = kg;
			this.año = año;
		}
		
	//Metodos
		public void setNumero(int numero){ this.numero = numero;}
		public int getNumero(){ return this.numero;}
		
		public void setLote(String lote){ this.lote = lote;}
		public String getLote(){ return lote;}
		
		public void setMunicipio(Ciudad municipio){ this.municipio = municipio;}
		public Ciudad getMunicipio(){ return this.municipio;}
		
		public void setPa(char pa){ this.pa = pa;}
		public char getPa(){ return this.pa;}
		
		public void setVariedad(Variedad variedad){ this.variedad = variedad;}
		public Variedad getVariedad(){ return this.variedad;}
		
		public void setKg(double kg){ this.kg = kg;}
		public double getKg(){ return this.kg;}
		
		public void setAño(int año){ this.año = año;}
		public int getAño(){ return this.año;}
                
                
}
