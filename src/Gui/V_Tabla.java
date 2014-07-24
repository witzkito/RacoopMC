package Gui;




import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * Clase encargada de las tablas. La caracteristica es que no son editables
 * @author Adrian
 */
public class V_Tabla extends DefaultTableModel {
	
	public V_Tabla(Vector row, Vector column){
		super(row, column);
	}

	
	public boolean isCellEditable (int row, int column){
	    return false;
	}
	
	public Class getColumnClass(int comunIndex){
		return String.class;
	}
	
	
	

}

