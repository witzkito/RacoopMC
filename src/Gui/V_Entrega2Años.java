package Gui;

/*
 * Created on 17-02-2008
*/

/**
 * @author Fernando Adrian Witzke
**/
import base.CensoPlanInfo;
import base.CensoPlantacion;
import base.Ciudad;
import base.Empresa;
import base.Socio;
import base.Variedad;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * @author Fernando Adrian Witzke
 * <p>Clase de la Ventanita para la elleccion de las entrgas de 2 Años</p>
 */

public class V_Entrega2Años extends JFrame{
	
    private JTable tblCensoPlantacion;
    private Empresa empresa;
    private Socio socio;
    private String temporada;
    private V_CensoEntrega censo;
	
	public V_Entrega2Años(Empresa emp, Socio so, String temporada, V_CensoEntrega censo) {
		super();
                this.empresa = emp;
                this.socio = so;
                this.temporada = String.valueOf((new Integer(temporada).intValue()));
                this.censo = censo;
                this.iniciar();
	}
	
	public void iniciar() {
                this.setSize(500,220);
		this.setLocationRelativeTo(null);
		this.setLayout(null);	
                Container contenido = getContentPane();
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 500, 220);
                panel.setBackground(this.empresa.getOptions().getColor_form());
                panel.setLayout(null);
                panel.add(getTblCensoPlantacion());
		contenido.add(panel);
                
        }
        
        private JScrollPane getTblCensoPlantacion(){
		Vector colNames = new Vector(6);
		colNames.addElement("Hectareas");
                colNames.addElement("Años");
                colNames.addElement("Cubierto");
		colNames.addElement("Germinacion");
                colNames.addElement("Variedad");
		colNames.addElement("Lote");
		colNames.addElement("Municipio");
		Vector rows = new Vector();
		V_Tabla tabla = new V_Tabla(rows, colNames);
		tblCensoPlantacion = new JTable(tabla);
                tblCensoPlantacion.removeAll();
                tblCensoPlantacion.setSelectionMode(0);
                JScrollPane scrTblCenso = new JScrollPane(tblCensoPlantacion);
		scrTblCenso.setSize(480, 200);
		scrTblCenso.setLocation(10, 10);
		tblCensoPlantacion.setBackground(empresa.getOptions().getColorJMenu());
		tblCensoPlantacion.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblCensoPlantacion.getColumnModel().getColumn(1).setPreferredWidth(10);
		tblCensoPlantacion.getColumnModel().getColumn(2).setPreferredWidth(20);
		tblCensoPlantacion.getColumnModel().getColumn(3).setPreferredWidth(20);
		tblCensoPlantacion.getColumnModel().getColumn(4).setPreferredWidth(50);
		tblCensoPlantacion.getColumnModel().getColumn(5).setPreferredWidth(50);
		tblCensoPlantacion.getColumnModel().getColumn(6).setPreferredWidth(70);
		tblCensoPlantacion.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){tblCensoPlantacion_Click(e);}
		});
		tblCensoPlantacion.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e){tblCensoPlantacion_Click(null);}
		});
		this.llenarTabla();
		return scrTblCenso;
	}
        
        private void llenarTabla(){
            CensoPlanInfo info; Vector row; NumberFormat format = NumberFormat.getPercentInstance();
            CensoPlantacion censo = this.socio.getCensoPlantacion(temporada);
            if (censo != null){
                Iterator it = censo.getAllInfo().values().iterator();
                while (it.hasNext()){
                    info = (CensoPlanInfo)it.next();
                    if (info.getAño() == 2){
                        row = new Vector();
                        row.addElement(info.getHectareas());
                        row.addElement(info.getAño());
                        row.addElement((format.format(new Double(info.getPorcentaje()) / 100)));
                        row.addElement((format.format(new Double(info.getGerminacion()) / 100)));
                        row.addElement(info.getVariedad());
                        row.addElement(info.getLote());
                        row.addElement(info.getMunicipio());
                        ((DefaultTableModel)tblCensoPlantacion.getModel()).addRow(row);
                        this.setVisible(true);
                    }
                }
                if (this.tblCensoPlantacion.getRowCount() == 0){
                    this.dispose();
                    JOptionPane.showMessageDialog(new JPanel(), "El Socio no tiene registrado censos de 2 Ciclos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }else{
                JOptionPane.showMessageDialog(new JPanel(), "El Socio no tiene registrado el censo de Plantacion", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            
        }
        
        private void tblCensoPlantacion_Click(MouseEvent e){
            int nroFila; String lote; Variedad var;
            Ciudad municipio; nroFila = this.tblCensoPlantacion.getSelectedRow(); 
            if (nroFila > -1){
                    var = (Variedad)this.tblCensoPlantacion.getValueAt(nroFila, 4);
                    lote = (String)this.tblCensoPlantacion.getValueAt(nroFila, 5);
                    municipio = (Ciudad)this.tblCensoPlantacion.getValueAt(nroFila, 6);
                    this.censo.cargar(var, lote, municipio);
            }
            this.dispose();            
            
        }
        
        /**
	 * Limpia el campo del signo de porcentaje, para transformar un string "40%" a Double "40"
	 * @param String porcentaje
	 * @return String filtrado
	 */
	public String limpiarPorcentage(String porcentaje){
		StringBuffer buff;
		buff = new StringBuffer(porcentaje);
		buff.deleteCharAt(buff.length() - 1);
		return buff.toString();
	}
        
}


			
		