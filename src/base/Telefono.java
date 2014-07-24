package base;

/**
 * Clase del Objeto Telefono 28-12-2007
 * @author Witzkito
 */
public class Telefono {
    //Atributos
    private String tipo;
    private String telefono;
    
    
    //Constructor
    public Telefono(String tipo, String telefono){
        this.tipo = tipo;
        this.telefono = telefono;
    }
    
    //Metodos
    public void setTipo(String tipo){this.tipo = tipo;}
    public String getTipo(){ return this.tipo;}
    
    public void setTelefono(String telefono){this.telefono = telefono;}
    public String getTelefono(){ return this.telefono;}
    
    public static String tipoFijo(){ return "Fijo";}
    public static String tipoCelular(){ return "Celular";}
    
}
