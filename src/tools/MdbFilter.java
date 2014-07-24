package tools;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Adrian - 11 de Diciembre del 2007
 * <p>Clase encargada de filtrar los archivos al elegirlos. En este caso a extension Acces </p>
 */
public class MdbFilter extends FileFilter{
	/**
 	* Establece qué ficheros serán aceptador
 	* @param File f
 	* @return boolean accept 
 	*/
	public boolean accept(File f){
		return f.getName().toLowerCase().endsWith(".mdb") || 			f.isDirectory();
	}
	/** Describe el tipo de ficheros que se van
	* a mostrar
	* @return String descripcion
	*/ 
	public String getDescription(){
		return "Base de Datos Access 2003";
	}
}
