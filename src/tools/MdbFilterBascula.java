package tools;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Adrian - 03 de Abril del 2008
 * <p>Clase encargada de filtrar los archivos al elegirlos. En este caso Base de datos
 *  de Bascula</p>
 */
public class MdbFilterBascula extends FileFilter{
	/**
 	* Establece que ficheros seran aceptador
 	* @param File f
 	* @return boolean accept 
 	*/
	public boolean accept(File f){
		return f.getName().toLowerCase().equals("bascula.mdb") || 			f.isDirectory();
	}
	/** Describe el tipo de ficheros que se van
	* a mostrar
	* @return String descripcion
	*/ 
	public String getDescription(){
		return "Base de Datos Bascula";
	}
}
