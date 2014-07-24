package Gui;

import base.CensoEntrega;
import base.CensoEntregaInfo;
import base.CensoPlanInfo;
import base.CensoPlantacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import db.DbCiudades;
import base.Empresa;
import base.Ciudad;
import base.Socio;
import db.DbCensoEntregaInfo;
import db.DbCensoPlanInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * @author <p>Witzke Fernando Adrian  25/12/2007 </p>
 * <b><p> Clase encargada de mostrar una tabla con alta, baja modificacion de
 * Ciudades para la configuracion de parametros del Sistema </p><b>
 */
public class V_Ciudad extends JFrame {

	
	private JPanel pnlDatos;
	private JPanel pnlBotones;
	private JLabel lblNumero;
	private JLabel lblNombre;
	private JTextField txtNumero;
	private JTextField txtNombre;
	private JButton bttAgregar;
	private JButton bttModificar;
	private JButton bttBorrar;
	private JButton bttCancelar;
	private Ciudad unaCiudad;
	private JTable tblItems;
	private JPanel jContentPane;
	private DbCiudades dbCiudad;
	private V_Configuracion v_conf;
	private Empresa empresa;
	
	
	
	/**
	 * Constuctor
	 * @param emp La empresa
	 * @param v_conf Ventana de Configuracion
	 */
	public V_Ciudad(Empresa emp,V_Configuracion v_conf){
		super();
		this.empresa = emp;
		this.v_conf = v_conf;
		initialize();
	}
	
	//Se Inicialisa la Ventana
	private void initialize(){
            this.setTitle("Configurar Ciudades");
            this.setSize(330,500);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setContentPane(getJContentPane());
            this.setIconImage(this.empresa.getOptions().getIcono().getImage());
            //inicializa la Base de Datos
            this.dbCiudad = new DbCiudades(Empresa.con);
            //Cuando se cierra la Ventana
            this.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                	v_conf.actualizarCiudad();
		}
            });
    	}
	
	//Se setea el contenedor de la ventana
	private JPanel getJContentPane(){
            jContentPane = new JPanel();
            jContentPane.setBackground(empresa.getOptions().getColor_form());
            jContentPane.setLayout(null);
            jContentPane.add(getPnlGeneral());
            jContentPane.add(getPnlBotones());
            return jContentPane;
	}
	
	//Seteo del panel general con los datos
	private JPanel getPnlGeneral(){
            pnlDatos = new JPanel();
            pnlDatos.setBorder(BorderFactory.createTitledBorder("Datos"));
            pnlDatos.setLocation(0, 0);
            pnlDatos.setSize(323,400);
            pnlDatos.setBackground(empresa.getOptions().getColor_form());
            pnlDatos.setLayout(null);
            pnlDatos.add(getTblItems());
            pnlDatos.add(getLblNumero());
            pnlDatos.add(getTxtNumero());
            pnlDatos.add(getLblNombre());
            pnlDatos.add(getTxtNombre());
            return pnlDatos;
	}
	
	//Seteo del panel de Botones
	private JPanel getPnlBotones(){
            pnlBotones = new JPanel();
            pnlBotones.setBorder(BorderFactory.createTitledBorder(""));
            pnlBotones.setLocation(0,403);
            pnlBotones.setSize(323,70);
            pnlBotones.setBackground(empresa.getOptions().getColor_form());
            pnlBotones.add(getBttAgregar());
            pnlBotones.add(getBttModificar());
            pnlBotones.add(getBttBorrar());
            pnlBotones.add(getBttCancelar());
            return pnlBotones;
	}
	
	//Seteo de la Tabla
	private JScrollPane getTblItems(){
            Map sorted; Vector row; Ciudad unaCi; 
            Vector vecCol = new Vector();
            Vector vecRows  = new Vector();
            vecCol.addElement("Numero");
            vecCol.addElement("Nombre");
            sorted = new TreeMap(this.empresa.configruaciones.getAllCiudades());
            Iterator it = sorted.values().iterator();
            while (it.hasNext()){
            	unaCi = (Ciudad)it.next();
		row = new Vector();
		row.addElement(new Integer(unaCi.getNumero()));
		row.addElement(new String(unaCi.getNombre()));
		vecRows.addElement(row);
            }
            V_Tabla tabla = new V_Tabla(vecRows, vecCol);
            tblItems = new JTable(tabla);
            tblItems.setBackground(empresa.getOptions().getColorJMenu());
            JScrollPane scroll = new JScrollPane(tblItems);
            tblItems.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblItems.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblItems.addMouseListener(new MouseAdapter(){
            	public void mouseClicked(MouseEvent e){tblItems(null);}
	    });
            tblItems.addKeyListener(new KeyAdapter(){
		public void keyReleased(KeyEvent e){tblItems(null);}
            });
            scroll.setLocation(10,20);
            scroll.setSize(300, 200);
            return scroll;
	}
	
	//Seteo del Label Numero
	private JLabel getLblNumero(){
            lblNumero = new JLabel("Numero");
            lblNumero.setFont(empresa.getOptions().getFontLbl());
            lblNumero.setLocation(10, 230);
            lblNumero.setSize(70, 20);
            return lblNumero;
	}
	
	//Seteo del Label Descripcion
	private JLabel getLblNombre(){
            lblNombre = new JLabel("Nombre");
            lblNombre.setFont(empresa.getOptions().getFontLbl());
            lblNombre.setLocation(10, 255);
            lblNombre.setSize(100, 20);
            return lblNombre;
	}
	
	//Seteo del JTextField Numero
	private JTextField getTxtNumero(){
            txtNumero = new JTextField();
            txtNumero.setLocation(140, 230);
            txtNumero.setEnabled(false);
            txtNumero.setSize(empresa.getOptions().getX_TamTxt() - 60, empresa.getOptions().getY_TamTxt());
            return txtNumero;
	}
	
	//Seteo del JTextField Descripcion
	private JTextField getTxtNombre(){
            txtNombre = new JTextField();
            txtNombre.setLocation(140, 255);
            txtNombre.setEnabled(false);
            txtNombre.setSize(empresa.getOptions().getX_TamTxt() + 10, empresa.getOptions().getY_TamTxt());
            return txtNombre;
	}
	
	//Seteo del Boton Agregar
	private JButton getBttAgregar(){
		bttAgregar = new JButton("Agregar");
		bttAgregar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttAgregar_Click(e);
			}
		});
		
		return bttAgregar;
	}
	
	//Seteo del Boton Modificar
	private JButton getBttModificar(){
		bttModificar = new JButton("Modificar");
		bttModificar.setEnabled(false);
		bttModificar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttModificar_Click(e);
			}
		});
		
		return bttModificar;
	}
	
	//Seteo del Boton Borrar
	private JButton getBttBorrar(){
		bttBorrar = new JButton("Borrar");
		bttBorrar.setEnabled(false);
		bttBorrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttBorrar_Click(e);
			}
		});
		
		return bttBorrar;
	}
	
	//Seteo del Boton Cancelar
	private JButton getBttCancelar(){
		bttCancelar = new JButton("Cancelar");
		bttCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttCancelar_Click(e);
			}
		});
		return bttCancelar;
	}
	
	
	/**
	 * Trae un Dato al hacer click de la Tabla
	 * @param e ActionEvent
	 */
	private void tblItems(ActionEvent e){
            int nroFila; String numero; String nombre;
            int numeroI; 
            bttModificar.setEnabled(true);
            bttBorrar.setEnabled(true);
            bttAgregar.setEnabled(false);
            habilitarTxt();
            nroFila = tblItems.getSelectedRow();
            numeroI = ((Integer)(tblItems.getValueAt(nroFila,0))).intValue();
            nombre = (String)(tblItems.getValueAt(nroFila, 1));
            unaCiudad = (Ciudad)this.empresa.getConf().getCiudad(numeroI);
            llenarInfo();
	}
	
	/**
	 * Limpia los Txt y variables
	 */
	private void limpiar(){
            txtNumero.setText("");
            txtNombre.setText("");
            unaCiudad = null;
	}
	
	/**
	 * Habilita los Txt
	 */
	private void habilitarTxt(){
            txtNombre.setEnabled(true);
	}
	
	/**
	 * Desabilita los Txt
	 */
	private void desabilitarTxt(){
            txtNombre.setEnabled(false);
	}
	
	/**
	 * LLena los JTextField con datos de la variable unaCiudad
	 */
	private void llenarInfo(){
            txtNumero.setText(String.valueOf(unaCiudad.getNumero()));
            txtNombre.setText(unaCiudad.getNombre());
	}
	
	//accion para el boton Agregar
	private void bttAgregar_Click(ActionEvent e){
            if (txtNombre.isEnabled()){
            	Ciudad ci = new Ciudad(new Integer(txtNumero.getText())
                        , String.valueOf(txtNombre.getText()));
            	this.empresa.configruaciones.addCiudad(ci);
		//Guarda en la base de datos
		this.dbCiudad.Insert(ci);
		actualizarTblItems();
		this.desabilitarTxt();
		this.limpiar();
		this.bttModificar.setEnabled(false);
		this.bttBorrar.setEnabled(false);
            }else{
		this.habilitarTxt();
		this.bttModificar.setEnabled(false);
		this.bttBorrar.setEnabled(false);
		this.txtNumero.setText(String.valueOf(this.empresa.getConf().getLastCountCiudad()));
            }
	}
	
	/**
	 * Actualiza el tabla Items
	 */
	private void actualizarTblItems(){
            Map sorted;Iterator it; Ciudad ci;
            sorted = new TreeMap(this.empresa.getConf().getAllCiudades());
            Vector row;
            it = sorted.values().iterator();
            ((DefaultTableModel)tblItems.getModel()).setNumRows(0);
            while (it.hasNext()){
		ci = (Ciudad)it.next();
		row = new Vector();
		row.addElement(new Integer(ci.getNumero()));
		row.addElement(new String(ci.getNombre()));
		((DefaultTableModel)tblItems.getModel()).addRow(row);
            }
	}
	
	//Accion para el boton Modificar
	private void bttModificar_Click(ActionEvent e){
            Ciudad unaCi = unaCiudad;
            unaCiudad.setNombre(txtNombre.getText());
            this.empresa.configruaciones.remCiudad(unaCiudad.getNumero());
            this.empresa.configruaciones.addCiudad(unaCiudad);
            //Actualiza en la Base de Datos
            this.dbCiudad.actualizar(unaCiudad);
            //Actualiza en todos los socios
            actualizarCiudad(unaCi, unaCiudad);
            actualizarTblItems();
            this.desabilitarTxt();
            this.bttAgregar.setEnabled(true);
            this.bttModificar.setEnabled(false);
            this.bttBorrar.setEnabled(false);
            this.limpiar();
	}
        
        private void actualizarCiudad(Ciudad antes, Ciudad actual){
            Iterator cp; Iterator cpD; Iterator ce; Iterator cpE; Socio socio; 
            CensoPlantacion censoPlan; CensoEntrega censoEntre;
            Iterator so = this.empresa.getMapSocios().values().iterator();
            while (so.hasNext()){
                socio = ((Socio)so.next());
                cp = socio.getAllCensoPlantacion().values().iterator();
                while (cp.hasNext()){
                    censoPlan = ((CensoPlantacion)cp.next()); 
                    cpD = censoPlan.getAllInfo().values().iterator();
                    while (cpD.hasNext()){
                        actualizarCensoP(socio, censoPlan, (CensoPlanInfo)cpD.next(), antes, actual);
                    }
                }
                ce = socio.getAllCensoEntrega().values().iterator();
                while (ce.hasNext()){
                    censoEntre = ((CensoEntrega)ce.next());
                    cpE = censoEntre.getAllCensoEntreInfo().values().iterator();
                    while (cpE.hasNext()){
                        actualizarCensoE(socio, censoEntre, (CensoEntregaInfo)cpE.next(), antes, actual);
                    }
                }
            }
        }
        
        private void actualizarCensoP(Socio unSocio, CensoPlantacion censoPlan, CensoPlanInfo censo, Ciudad antes, Ciudad actual){
            DbCensoPlanInfo dbCenso = new DbCensoPlanInfo(Empresa.con, this.empresa);
            
            if (censo.getMunicipio().getNombre().equals(antes.getNombre())){
                censo.setMunicipio(actual);
                censoPlan.addInfoCenso(censo);
                dbCenso.actualizar(unSocio, censoPlan, censo);
            }   
        }
        
        private void actualizarCensoE(Socio unSocio, CensoEntrega censoEntre, CensoEntregaInfo censo, Ciudad antes, Ciudad actual){
            DbCensoEntregaInfo dbCenso = new DbCensoEntregaInfo(Empresa.con, this.empresa);
            if (censo.getMunicipio().getNombre().equals(antes.getNombre())){
                censo.setMunicipio(actual);
                censoEntre.addCensoEntreInfos(censo);
                dbCenso.actualizar(unSocio, censoEntre, censo);
            }
        }
        
       
	
	//Accion para el Boton Borrar
	private void bttBorrar_Click(ActionEvent e){
            this.empresa.configruaciones.remCiudad(unaCiudad.getNumero());
            //Borra el Dato de la Base de Datos
            this.dbCiudad.borrar(unaCiudad);
            this.actualizarTblItems();
            this.desabilitarTxt();
            this.bttAgregar.setEnabled(true);
            this.bttModificar.setEnabled(false);
            this.bttBorrar.setEnabled(false);
            this.limpiar();
	}
	
	//Accion para el Boton Cancelar
	private void bttCancelar_Click(ActionEvent e){
		this.limpiar();
		this.bttAgregar.setEnabled(true);
		this.bttModificar.setEnabled(false);
		this.bttBorrar.setEnabled(false);
		this.desabilitarTxt();
	}
}
