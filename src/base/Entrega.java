package base;

/**
 * Clase encaragada del Objeto Entrega
 * 27/03/2008
 * @author Fernando Adrian Witzke
 * 
 */
public class Entrega {
    
    //Atributos
        private long numero; //numero de Ticket de la entrega
        private Variedad variedad; //Variedad de la entrega
        private int ciclo; //Ciclo de la mandioca, 1 año o 2 años
        private long kg; //Peso de la entrega
        private double rinde; //Rinde de la cosecha
        private Fecha_wi fecha; //fecha de la entrega
        private String temporada; //Temporada de la entrega
        
     //Constructor
        public Entrega(long numero, Variedad variedad, int ciclo, long kg,
                double rinde, Fecha_wi fecha, String temporada){
            this.numero = numero;
            this.variedad = variedad;
            this.ciclo = ciclo;
            this.kg = kg;
            this.rinde = rinde;
            this.fecha = fecha;
            this.temporada = temporada;
        }
        
      //Metodos
        public void setNumero(long numero){ this.numero = numero;}
        public long getNumero(){ return this.numero;}
        
        public void setVariedad(Variedad variedad){this.variedad = variedad;}
        public Variedad getVariedad(){ return this.variedad;}
        
        public void setCiclo(int ciclo){ this.ciclo = ciclo;}
        public int getCiclo(){ return this.ciclo;}
        
        public void setKg(long kg){ this.kg = kg;}
        public long getKg(){ return this.kg;}
        
        public void setRinde(double rinde){ this.rinde = rinde;}
        public double getRinde(){ return this.rinde;}
        
        public void setFecha(Fecha_wi fecha){ this.fecha = fecha;}
        public Fecha_wi getFecha(){ return this.fecha;}
        
        public void setTemporada(String temporada){ this.temporada = temporada;}
        public String getTemporada(){ return this.temporada;}
        
        

}
