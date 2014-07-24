package Gui;

import base.Ciudad;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import base.Variedad;

/**
 * @author Fernando A. Witzke
 * 
 * 10/10/2007
 * Clase Encargada de crear el Renderisador para las tablas
 * 
 */
public class TableRender implements TableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable tabla, Object value, boolean isSelected,
			boolean hasFocus, int row, int column){
		JLabel lblAux;
		JLabel label = new JLabel();
		
		 if (value instanceof String){
			if (((String)value).equals("E1")){
				label = this.asigIcons("", JLabel.CENTER, "img/ok.gif", "El censo es correcto");
			}else if(((String)value).equals("E2")){
				label = this.asigIcons("", JLabel.CENTER, "img/atencion.gif", "Se paso de la Tolerancia");
			}else if(((String)value).equals("E3")){
				label = this.asigIcons("", JLabel.CENTER, "img/peligro.gif", "No se encontro el correspondiente censo Plantacion");
			}else if(((String)value).equals("P1")){
				label = this.asigIcons("", JLabel.CENTER, "img/ok.gif", "Plantacion entregada");
			}else if(((String)value).equals("P2")){
				label = this.asigIcons("", JLabel.CENTER, "img/peligro.gif", "Plantacion no Entregada");
			}else if(((String)value).contains("%")){
				JLabel aux = new JLabel(String.valueOf(value), JLabel.RIGHT);
				aux.setToolTipText((String)value);
				label = aux;
			}else{
				label.setText((String)value);
				label.setToolTipText((String)value);
			}
		 }
			
			
		
		if (value instanceof Double){
			lblAux = new JLabel(String.valueOf(value), JLabel.RIGHT);
			lblAux.setToolTipText(String.valueOf(value));
			label = lblAux;
		}
		
		if (value instanceof Integer){
			lblAux = new JLabel(String.valueOf(value), JLabel.RIGHT);
			lblAux.setText(String.valueOf(value));
			lblAux.setToolTipText(String.valueOf(value));
			label = lblAux;
		}
		
		if (value instanceof Long){
			lblAux = new JLabel(String.valueOf(value), JLabel.RIGHT);
			lblAux.setText(String.valueOf(value));
			lblAux.setToolTipText(String.valueOf(value));
			label = lblAux;
		}
		
		if (value instanceof Character){
			label.setText(String.valueOf(value));
			label.setToolTipText(String.valueOf(value));
		}
		
		if (value instanceof Variedad){
			label.setText(((Variedad)value).getNombre());
			label.setToolTipText(((Variedad)value).getNombre());
			
		}
                
                if (value instanceof Ciudad){
                   label.setText(String.valueOf(value));
                   label.setToolTipText(String.valueOf(value));
                }
		
                label.setOpaque(true);
		if (isSelected){
			label.setBackground(new Color(155, 136,113));
		}else{
			label.setBackground(new Color(200,221,242));
		}
		
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		return label;
                
               
	}
	
	private JLabel asigIcons(String txt, int centrado, String direccion, String toolTipText){
		JLabel aux = new JLabel(txt, centrado);
		aux.setIcon(new ImageIcon(direccion));
		aux.setToolTipText(toolTipText);
		return aux;
	}
        
        public boolean isCellEditable (int row, int column){
	    return false;
	}
	

}
