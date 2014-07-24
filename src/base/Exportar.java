package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase encargada de la Exportacion de la Base de Datos
 * @author Fernando Adrian Witzke
 */
public class Exportar implements Runnable{

    public Exportar() {
    }
 
   
    /**
     * Realiza Exportacion de la Base de Datos en una Carpeta
     */
    public void run(){
       try{
        Calendar cal = new GregorianCalendar();   
        FileInputStream fis = new FileInputStream("db/db.mdb"); 
        File file = new File("db/backup/");
        System.out.println(file);
        FileOutputStream fos = new FileOutputStream("db/backUp/" + cal.get(Calendar.DAY_OF_MONTH) + ".mdb");
        FileChannel canalFuente = fis.getChannel(); 
        FileChannel canalDestino = fos.getChannel(); 
        canalFuente.transferTo(0, canalFuente.size(), canalDestino);
        
        fis.close(); 
        fos.close();
       }catch (Exception ex){
           JOptionPane.showMessageDialog(new JPanel(), "Error realizando BackUp de la Base: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
       }
    }

}
