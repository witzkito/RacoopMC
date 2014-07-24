package tools;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DbfFilter extends FileFilter{
	/**
 	* Establece que ficheros seran aceptador
 	* @param File f
 	* @return boolean accept 
 	*/
	public boolean accept(File f){
		return f.getName().toLowerCase().endsWith(".dbf") || 			f.isDirectory();
	}
	/** Describe el tipo de ficheros que se van
	* a mostrar
	* @return String descripcion
	*/ 
	public String getDescription(){
		return "Base de Datos DbaseIII";
	}
}
