package Gui;

import base.CensoEntrega;
import base.CensoEntregaInfo;
import base.CensoPlanInfo;
import base.CensoPlantacion;
import base.CensoPlantacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import db.DbVariedad;
import base.Empresa;
import base.Entrega;
import base.Socio;
import base.Variedad;
import db.DbCensoEntregaInfo;
import db.DbCensoPlanInfo;
import db.DbEntrega;

/**
 * @author <p>Witzke Fernando Adrian  29/11/2007 </p>
 * <b><p> Clase encargada de mostrar una tabla con alta, baja modificacion de Variedades
 * para la configuracion de parametros del Sistema </p><b>
 */
public class V_Variedad extends JFrame {

	
	private JPanel pnlDatos;
	private JPanel pnlBotones;
	private JLabel lblNumero;
	private JLabel lblDescripcion;
	private JLabel lblKg1Año;
	private JLabel lblKg2Años;
	private JTextField txtNumero;
	private JTextField txtDescripcion;
	private JTextField txtKg1Año;
	private JTextField txtKg2Años;
	private JButton bttAgregar;
	private JButton bttModificar;
	private JButton bttBorrar;
	private JButton bttCancelar;
	private Variedad unaVariedad;
	private JTable tblItems;
	private JPanel jContentPane;
	private DbVariedad dbVariedad;
	private V_Configuracion v_conf;
	private Empresa empresa;
	
	
	
	/**
	 * Constuctor
	 * @param emp La empresa
	 * @param v_conf Ventana de Configuracion
	 */
	public V_Variedad(Empresa emp,V_Configuracion v_conf){
		super();
		this.empresa = emp;
		this.v_conf = v_conf;
		initialize();
	}
	
	//Se Inicialisa la Ventana
	private void initialize(){
		this.setTitle("Configurar Variedades");
		this.setSize(330,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setIconImage(this.empresa.getOptions().getIcono().getImage());
		//inicializa la Base de Datos
		this.dbVariedad = new DbVariedad(Empresa.con);
		//Cuando se cierra la Ventana
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				v_conf.actualizarVariedad();
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
		pnlDatos.add(getLblDescripcion());
		pnlDatos.add(getTxtDescripcion());
		pnlDatos.add(getLblKg1Año());
		pnlDatos.add(getLblKg2Años());
		pnlDatos.add(getTxtKg1Año());
		pnlDatos.add(getTxtKg2Años());
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
		Map sorted; Vector row; Variedad unaVa; 
		Vector vecCol = new Vector();
		Vector vecRows  = new Vector();
		vecCol.addElement("Nro");
		vecCol.addElement("Nombre");
		vecCol.addElement("1 Año");
		vecCol.addElement("2 Años");
		sorted = new TreeMap(this.empresa.configruaciones.getAllVariedades());
		Iterator it = sorted.values().iterator();
		while (it.hasNext()){
			unaVa = (Variedad)it.next();
			row = new Vector();
			row.addElement(new Integer(unaVa.getNumero()));
			row.addElement(new String(unaVa.getNombre()));
			row.addElement(new Double(unaVa.getKgHec1Año()));
			row.addElement(new Double(unaVa.getKgHec2Año()));
			vecRows.addElement(row);
		}
		V_Tabla tabla = new V_Tabla(vecRows, vecCol);
		tblItems = new JTable(tabla);
		tblItems.setBackground(empresa.getOptions().getColorJMenu());
		JScrollPane scroll = new JScrollPane(tblItems);
		tblItems.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblItems.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblItems.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblItems.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblItems.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){tblItems(null);}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}
		
		});
		tblItems.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){}
			public void keyReleased(KeyEvent e){tblItems(null);}
			public void keyTyped(KeyEvent e){}
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
	private JLabel getLblDescripcion(){
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(empresa.getOptions().getFontLbl());
		lblDescripcion.setLocation(10, 255);
		lblDescripcion.setSize(100, 20);
		return lblDescripcion;
	}
	
	//Seteo del Label Kg1Año
	private JLabel getLblKg1Año(){
		lblKg1Año = new JLabel("Kg X Hec. 1 Año");
		lblKg1Año.setFont(empresa.getOptions().getFontLbl());
		lblKg1Año.setLocation(10, 280);
		lblKg1Año.setSize(130, 20);
		return lblKg1Año;
	}
	
	//Seteo del Label Kg2Años
	private JLabel getLblKg2Años(){
		lblKg2Años = new JLabel("Kg X Hec. 2 Años");
		lblKg2Años.setFont(empresa.getOptions().getFontLbl());
		lblKg2Años.setLocation(10, 305);
		lblKg2Años.setSize(130, 20);
		return lblKg2Años;
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
	private JTextField getTxtDescripcion(){
		txtDescripcion = new JTextField();
		txtDescripcion.setLocation(140, 255);
		txtDescripcion.setEnabled(false);
		txtDescripcion.setSize(empresa.getOptions().getX_TamTxt() + 10, empresa.getOptions().getY_TamTxt());
		return txtDescripcion;
	}
	
	//Seteo del JTextField Kg1Año
	private JTextField getTxtKg1Año(){
		txtKg1Año = new JTextField();
		txtKg1Año.setLocation(140, 280);
		txtKg1Año.setEnabled(false);
		txtKg1Año.setSize(empresa.getOptions().getX_TamTxt() + 10, empresa.getOptions().getY_TamTxt());
		return txtKg1Año;
	}
	
	//Seteo del JTextField Numero
	private JTextField getTxtKg2Años(){
		txtKg2Años = new JTextField();
		txtKg2Años.setLocation(140, 305);
		txtKg2Años.setEnabled(false);
		txtKg2Años.setSize(empresa.getOptions().getX_TamTxt() + 10, empresa.getOptions().getY_TamTxt());
		return txtKg2Años;
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
	 * Trae un Dato al hacer click de la Tabla de Motivos
	 * @param e ActionEvent
	 */
	private void tblItems(ActionEvent e){
		int nroFila; String numero; String descripcion;
		int numeroI; 
		bttModificar.setEnabled(true);
		bttBorrar.setEnabled(true);
		bttAgregar.setEnabled(false);
		habilitarTxt();
		nroFila = tblItems.getSelectedRow();
		numeroI = ((Integer)(tblItems.getValueAt(nroFila,0))).intValue();
		descripcion = (String)(tblItems.getValueAt(nroFila, 1));
		unaVariedad = (Variedad)this.empresa.getConf().getVariedad(numeroI);
		llenarInfo();
	}
	
	/**
	 * Limpia los Txt y variables
	 */
	private void limpiar(){
		txtNumero.setText("");
		txtDescripcion.setText("");
		txtKg1Año.setText("");
		txtKg2Años.setText("");
		unaVariedad = null;
	}
	
	/**
	 * Habilita los Txt
	 */
	private void habilitarTxt(){
		txtDescripcion.setEnabled(true);
		txtKg1Año.setEnabled(true);
		txtKg2Años.setEnabled(true);
	}
	
	/**
	 * Desabilita los Txt
	 */
	private void desabilitarTxt(){
		txtDescripcion.setEnabled(false);
		txtKg1Año.setEnabled(false);
		txtKg2Años.setEnabled(false);
	}
	
	/**
	 * LLena los JTextField con datos de la variable unMotivo
	 */
	private void llenarInfo(){
		txtNumero.setText(String.valueOf(unaVariedad.getNumero()));
		txtDescripcion.setText(unaVariedad.getNombre());
		txtKg1Año.setText(String.valueOf(unaVariedad.getKgHec1Año()));
		txtKg2Años.setText(String.valueOf(unaVariedad.getKgHec2Año()));
	}
	
	//accion para el boton Agregar
	private void bttAgregar_Click(ActionEvent e){
		if (txtDescripcion.isEnabled()){
			if (txtKg1Año.getText().length() == 0){
                           txtKg1Año.setText(String.valueOf(0));
                         }
                        if (txtKg2Años.getText().length() == 0){
                            txtKg2Años.setText(String.valueOf(0));
                        }
                        Variedad var = new Variedad(new Integer(txtNumero.getText()), String.valueOf(txtDescripcion.getText()), new Double(txtKg1Año.getText()),
										new Double(txtKg2Años.getText()));
			this.empresa.configruaciones.addVariedad(var);
			//Guarda en la base de datos
			this.dbVariedad.Insert(var);
			actualizarTblItems();
			this.desabilitarTxt();
			this.limpiar();
			this.bttModificar.setEnabled(false);
			this.bttBorrar.setEnabled(false);
		}else{
			this.habilitarTxt();
			this.bttModificar.setEnabled(false);
			this.bttBorrar.setEnabled(false);
			this.txtNumero.setText(String.valueOf(this.empresa.getConf().getLastCountVariedad()));
		}
	}
	
	/**
	 * Actualiza el tabla Items
	 */
	private void actualizarTblItems(){
		Map sorted;Iterator it; Variedad va;
		sorted = new TreeMap(this.empresa.getConf().getAllVariedades()); Vector row;
		it = sorted.values().iterator();
		((DefaultTableModel)tblItems.getModel()).setNumRows(0);
		while (it.hasNext()){
			va = (Variedad)it.next();
			row = new Vector();
			row.addElement(new Integer(va.getNumero()));
			row.addElement(new String(va.getNombre()));
			row.addElement(new Double(va.getKgHec1Año()));
			row.addElement(new Double(va.getKgHec2Año()));
			((DefaultTableModel)tblItems.getModel()).addRow(row);
		}
	}
	
	//Accion para el boton Modificar
	private void bttModificar_Click(ActionEvent e){
            Variedad var = unaVariedad;
            unaVariedad.setNombre(txtDescripcion.getText());
            if (txtKg1Año.getText().length() == 0){
                txtKg1Año.setText(String.valueOf(0));
            }
            if (txtKg2Años.getText().length() == 0){
                txtKg2Años.setText(String.valueOf(0));
            }
            unaVariedad.setKgHec1Año(new Double(txtKg1Año.getText()));
            unaVariedad.setKgHec2Año(new Double(txtKg2Años.getText()));
            this.empresa.configruaciones.remVaridad(unaVariedad.getNumero());
            this.empresa.configruaciones.addVariedad(unaVariedad);
            //Actualiza en la Base de Datos
            this.dbVariedad.actualizar(unaVariedad);
            //Actualiza en todos los socios
            actualizarVariedad(var, unaVariedad);
            actualizarTblItems();
            this.desabilitarTxt();
            this.bttAgregar.setEnabled(true);
            this.bttModificar.setEnabled(false);
            this.bttBorrar.setEnabled(false);
            this.limpiar();
	}
        
        private void actualizarVariedad(Variedad antes, Variedad actual){
            Iterator cp; Iterator cpD; Iterator ce; Iterator cpE; Socio socio; Iterator e;
            CensoPlantacion censoPlan; CensoEntrega censoEntre; Entrega entre;
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
                e = socio.getAllEntregas().values().iterator();
                while (e.hasNext()){
                    actualizarEntrega(socio, (Entrega)e.next(), antes, actual);
                }
            }
        }
        
        private void actualizarCensoP(Socio unSocio, CensoPlantacion censoPlan, CensoPlanInfo censo, Variedad antes, Variedad actual){
            DbCensoPlanInfo dbCenso = new DbCensoPlanInfo(Empresa.con, this.empresa);
            if (censo.getVariedad().getNombre().equals(antes.getNombre())){
                censo.setVariedad(actual);
                censoPlan.addInfoCenso(censo);
                dbCenso.actualizar(unSocio, censoPlan, censo);
            }   
        }
        
        private void actualizarCensoE(Socio unSocio, CensoEntrega censoEntre, CensoEntregaInfo censo, Variedad antes, Variedad actual){
            DbCensoEntregaInfo dbCenso = new DbCensoEntregaInfo(Empresa.con, this.empresa);
            if (censo.getVariedad().getNombre().equals(antes.getNombre())){
                censo.setVariedad(actual);
                censoEntre.addCensoEntreInfos(censo);
                dbCenso.actualizar(unSocio, censoEntre, censo);
            }
        }
        
        private void actualizarEntrega(Socio unSocio, Entrega entrega, Variedad antes, Variedad actual){
            DbEntrega dbEntrega = new DbEntrega(Empresa.con, this.empresa);
            if (entrega.getVariedad().getNombre().equals(antes.getNombre())){
                entrega.setVariedad(actual);
                unSocio.addEntrega(entrega);
                dbEntrega.actualizar(unSocio, entrega);
            }
        }
        
	
	//Accion para el Boton Borrar
	private void bttBorrar_Click(ActionEvent e){
		this.empresa.configruaciones.remVaridad(unaVariedad.getNumero());
		//Borra el Dato de la Base de Datos
		this.dbVariedad.borrar(unaVariedad);
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
