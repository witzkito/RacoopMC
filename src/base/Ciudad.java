package base;

/**
 * @author Witzkito
 * Clase del Objeto Ciudad, con el Nombre y el codigo
 */
public class Ciudad {
    
    //atributos
    
    private int numero; //Numero de la ciudad
    private String nombre; //Nombre de la ciudad
    
    //Constructor
    
    /**
     * Constructor de la ciudad
     * @param numero int
     * @param nombre String
     */
    public Ciudad(int numero, String nombre){
        this.numero = numero;
        this.nombre = nombre;
    }
    
    //gets y sets
    
    public void setNumero(int numero){ this.numero = numero;}
    public int getNumero (){ return this.numero;}
    
    public void setNombre(String nombre){ this.nombre = nombre;}
    public String getNombre(){ return this.nombre;}
    
    public String toString(){
        return this.numero + " " + this.nombre;
    }

}
