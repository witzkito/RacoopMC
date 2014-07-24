/*
 * Construido esta clase el 21-05-2012 debido a especificaciones del Cliente
 * F. A. Witzke
 */
package base;

/**
 *
 * @author witzkito
 */
public class Tercero {
    
    //Atributos
    private int id; //El Id del Tercero
    private String nombre; //El nombre del Tercero
    private String direccion; //La direccion del Tercero
    
    
    //Constructor
    public Tercero (int id, String nombre, String dir)
    {
        this.id = id;
        this.nombre = nombre;
        this.direccion= dir;
    }
    
    //Metodos
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
