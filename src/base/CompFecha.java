package base;

import com.toedter.calendar.JDateChooser;

/**
 * Clase encargada de comprobar las fechas del JDate Chooser
 * @author Fernando Adrian Witzke
 */
public class CompFecha {
    
    /**
     * Funcion que devuelve un string si tiene un error y un espasio en blanco
     * si esta correcto
     * @param date JDateChooser
     * @return String
     */
    public static String getComprobacion(JDateChooser date){
        if (date.getCalendar() == null){
            return "Error en Fecha Comienzo";
        }else{
            return " ";
        }
    }
    
    public static String getComprobacion(JDateChooser fechaInicio, JDateChooser fechaFin){
        if (fechaInicio.getCalendar() == null){
            return "Error en Fecha Comienzo";
        }else{
            if (fechaFin.getCalendar() == null){
                return "Error en Fecha Fin";
            }else{
                return " ";
            }
        }
    }
    
    

}
