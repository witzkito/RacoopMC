package Gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import Reportes.ReporteCensoPlantacionSocio;
import base.CensoPlanInfo;
import base.CensoPlantacion;
import base.Ciudad;
import base.Empresa;
import base.Fecha_wi;
import base.MovInformativo;
import base.Socio;
import base.Variedad;
import com.toedter.calendar.JDateChooser;
import db.DbCensoPlanInfo;
import db.DbCensoPlantacion;
import db.DbMovInformativo;
import db.DbRepCensoPlantacionSocio;

/**
 * @author Adrian
 * 
 *<p>Clase del Panel Visual del Censo de Plantacion</p>
 */
public class V_CensoPlantacion extends JFrame{
	
	private JPanel jContentPane;
	private JPanel pnlSocio;
	private JPanel pnlCensoPlantacion;
	private JPanel pnlTbl;
	private JPanel pnlAcciones;
	private JLabel lblSocio;
	private JLabel lblNumero;
	private JLabel lblFecha;
	private JLabel lblIngreso;
	private JLabel lblLote;
	private JLabel lblMunicipio;
	private JLabel lblPA;
        private JLabel lblCubierto;
        private JLabel lblGerminacion;
      	private JTextField txtNumero;
	private JDateChooser txtFecha;
	private JFormattedTextField txtTemporada;
	private JTextField txtHectareas;
	private JFormattedTextField txtCubierto;
        private JFormattedTextField txtGerminacion;
        private JTextField txtLote;
	private JComboBox cmbMunicipio;
	private JFormattedTextField txtPA;
	private JComboBox cmbVariedad;
	private JRadioButton rd1Año;
	private JRadioButton rd2Año;
	private ButtonGroup rdGroup;
	private JButton bttAgregar;
	private JButton bttBorrar;
	private JButton bttAceptar;
	private JButton bttCancelar;
	private JButton bttListado;
	private JButton bttArriba;
	private JButton bttAbajo;
	private JTable tblCensoPlantacion;
	private JScrollPane scrTblCenso;
	private Empresa empresa;
        private String temporada;
	private V_Principal principal;
	private CensoPlantacion censoPlantacion = null;
	private Socio unSocio;
	private DbCensoPlantacion dbCensoPlantacion;
	private DbCensoPlanInfo dbCensoInfo;
	private DbMovInformativo dbMovInfor;
	private Map censoGuardar;
	
	
	public V_CensoPlantacion(Empresa emp,Socio unSocio, V_Principal principal){
		super();
		this.empresa = emp;
		this.principal = principal;
		this.unSocio = unSocio;
		this.dbCensoPlantacion = new DbCensoPlantacion(Empresa.con);
		this.dbCensoInfo = new DbCensoPlanInfo(Empresa.con, this.empresa);
		this.dbMovInfor = new DbMovInformativo(Empresa.con);
		this.censoGuardar = new HashMap();
		this.setIconImage(this.empresa.getOptions().getIcono().getImage());
                
		ubicar(false);
		if (this.censoPlantacion != null){
			this.censoGuardar.putAll(this.censoPlantacion.getAllInfo());
		}
		initialize();
	}
        
        public V_CensoPlantacion(Empresa emp,Socio unSocio, String temporada,  V_Principal principal){
		super();
		this.empresa = emp;
		this.principal = principal;
		this.unSocio = unSocio;
                this.temporada = temporada;
		this.dbCensoPlantacion = new DbCensoPlantacion(Empresa.con);
		this.dbCensoInfo = new DbCensoPlanInfo(Empresa.con, this.empresa);
		this.dbMovInfor = new DbMovInformativo(Empresa.con);
		this.censoGuardar = new HashMap();
		this.setIconImage(this.empresa.getOptions().getIcono().getImage());
		System.out.println("Essot");
                ubicar(false);
		if (this.censoPlantacion != null){
			this.censoGuardar.putAll(this.censoPlantacion.getAllInfo());
		}
		initialize();
	}
	
	private void initialize(){
		this.setTitle("Censo Plantacion");
		this.setSize(400,560);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
	}
	
	private JPanel getJContentPane(){
		jContentPane = new JPanel();
		jContentPane.setBackground(empresa.getOptions().getColor_form());
		jContentPane.setLayout(null);
		jContentPane.add(getPnlSocio());
		jContentPane.add(getPnlPlantacion());
		jContentPane.add(getPnlAcciones());
		return jContentPane;
	}
	
	private JPanel getPnlSocio(){
		pnlSocio = new JPanel();
		pnlSocio.setBorder(BorderFactory.createTitledBorder("Socio"));
		pnlSocio.setLocation(0, 0);
		pnlSocio.setSize(395,50);
		pnlSocio.setBackground(empresa.getOptions().getColor_form());
		pnlSocio.setLayout(new FlowLayout());
		pnlSocio.add(getLblSocio());
		return pnlSocio;
	}
	
	//Panel table en donde se agregan los datos para la tabla
	private JPanel getPnlTbl(){
		pnlTbl = new JPanel();
		pnlTbl.setBorder(BorderFactory.createTitledBorder(""));
		pnlTbl.setLocation(1, 100);
		pnlTbl.setSize(392,300);
		pnlTbl.setBackground(empresa.getOptions().getColor_form());
		pnlTbl.setLayout(null);
		pnlTbl.add(getTxtHectarea());
		pnlTbl.add(getLblIngreso());
		pnlTbl.add(getLblLote());
		pnlTbl.add(getLblMunicipio());
		pnlTbl.add(getLblPA());
		pnlTbl.add(getLblCubierto());
                pnlTbl.add(getLblGerminacion());
                pnlTbl.add(getCmbVariedad());
		pnlTbl.add(getTxtCubierto());
		pnlTbl.add(getTxtLote());
		pnlTbl.add(getTxtMunicipio());
		pnlTbl.add(getTxtPA());
                pnlTbl.add(getTxtGerminacion());
		pnlTbl.add(getBttAgregar());
		pnlTbl.add(getBttBorrar());
		pnlTbl.add(getTblCensoPlantacion());
		pnlTbl.add(getRd1Año());
		pnlTbl.add(getRd2Año());
		rdGroup = new ButtonGroup();
		rdGroup.add(rd1Año);
		rdGroup.add(rd2Año);
		return pnlTbl;
	}
	
	private JPanel getPnlPlantacion(){
		pnlCensoPlantacion = new JPanel();
		pnlCensoPlantacion.setBorder(BorderFactory.createTitledBorder("Plantacion"));
		pnlCensoPlantacion.setBackground(empresa.getOptions().getColor_form());
		pnlCensoPlantacion.setLayout(null);
		pnlCensoPlantacion.setSize(395, 410);
		pnlCensoPlantacion.setLocation(0,50);
		pnlCensoPlantacion.add(getPnlTbl());
		pnlCensoPlantacion.add(getLblNumero());
		pnlCensoPlantacion.add(getLblFecha());
               	pnlCensoPlantacion.add(getTxtNumero());
		pnlCensoPlantacion.add(getTxtFecha());
		pnlCensoPlantacion.add(getTxtTemporada());
		pnlCensoPlantacion.add(getBttArriba());
		pnlCensoPlantacion.add(getBttAbajo());
		return pnlCensoPlantacion;
	}
	
	private JPanel getPnlAcciones(){
		pnlAcciones = new JPanel();
		pnlAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
		pnlAcciones.setBackground(empresa.getOptions().getColor_form());
		pnlAcciones.setSize(395,65);
		pnlAcciones.setLocation(0,460);
		pnlAcciones.add(getBttAceptar());
		pnlAcciones.add(getBttCancelar());
		pnlAcciones.add(getBttListado());
		return pnlAcciones;
	}
	
	private JLabel getLblSocio(){
		lblSocio = new JLabel(unSocio.getNomApe() , JLabel.CENTER);
		lblSocio.setFont(empresa.getOptions().getFontLbl());
		lblSocio.setLocation(0, 20);
		lblSocio.setSize(304, 20);
		return lblSocio;
	}
	
	private JLabel getLblNumero(){
		lblNumero = new JLabel("Numero:");
		lblNumero.setFont(empresa.getOptions().getFontLbl());
		lblNumero.setLocation(10, 37);
		lblNumero.setSize(50, 20);
		return lblNumero;
	}
	
	private JLabel getLblFecha(){
		lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(empresa.getOptions().getFontLbl());
		lblFecha.setLocation(10, 67);
		lblFecha.setSize(50, 20);
		return lblFecha;
	}
	
	private JLabel getLblIngreso(){
		lblIngreso = new JLabel("Ha. Mandioca ");
		lblIngreso.setFont(empresa.getOptions().getFontLbl());
		lblIngreso.setLocation(65, 17);
		lblIngreso.setSize(100,20);
		return lblIngreso;
	}
	
	private JLabel getLblLote(){
		lblLote = new JLabel("Lote Nro.");
		lblLote.setFont(empresa.getOptions().getFontLbl());
		lblLote.setLocation(20,47);
		lblLote.setSize(70, 20);
		return lblLote;
	}
	
	private JLabel getLblMunicipio(){
		lblMunicipio = new JLabel("Municipio");
		lblMunicipio.setFont(empresa.getOptions().getFontLbl());
		lblMunicipio.setLocation(155, 47);
		lblMunicipio.setSize(70,20);
		return lblMunicipio;
	}
	
	private JLabel getLblPA(){
		lblPA = new JLabel("P/A");
		lblPA.setFont(empresa.getOptions().getFontLbl());
		lblPA.setLocation(315, 17);
                lblPA.setSize(40, 20);
		return lblPA;
	}
        
        private JLabel getLblCubierto(){
		lblCubierto = new JLabel("Cubierto");
		lblCubierto.setFont(empresa.getOptions().getFontLbl());
		lblCubierto.setLocation(20, 77);
                lblCubierto.setSize(60, 20);
		return lblCubierto;
	}
        
         private JLabel getLblGerminacion(){
		lblGerminacion = new JLabel("Germinacion");
		lblGerminacion.setFont(empresa.getOptions().getFontLbl());
		lblGerminacion.setLocation(155, 77);
                lblGerminacion.setSize(100, 20);
		return lblGerminacion;
	}
	
	private JTextField getTxtNumero(){
		txtNumero = new JTextField();
		txtNumero.setLocation(70, 37);
		txtNumero.setSize(empresa.getOptions().getX_TamTxt() - 50, empresa.getOptions().getY_TamTxt());
		txtNumero.setEditable(false);
		txtNumero.setText(new Long(this.censoPlantacion.getNumero()).toString());
		return txtNumero;
	}
	
	private JDateChooser getTxtFecha(){
		txtFecha = new JDateChooser();
		txtFecha.setLocation(70, 67);
		txtFecha.setSize(empresa.getOptions().getX_TamTxt() - 5, empresa.getOptions().getY_TamTxt());
                txtFecha.setCalendar(this.censoPlantacion.getFecha_wi().getFecha());
		return txtFecha;
	}
	
	private JFormattedTextField getTxtTemporada(){
		try{
			MaskFormatter mask = new MaskFormatter("Temporada ####");
			txtTemporada = new JFormattedTextField(mask);
			txtTemporada.setLocation(175, 67);
			txtTemporada.setSize(empresa.getOptions().getX_TamTxt(), empresa.getOptions().getY_TamTxt());
			if (temporada == null){
                            txtTemporada.setText(this.censoPlantacion.getTemporada());
                        }else{
                            txtTemporada.setText(temporada);
                        }
			txtTemporada.setEditable(false);
		}catch (Exception ex){
			JOptionPane.showMessageDialog(this, ex, "Error en el mask", JOptionPane.ERROR_MESSAGE);
		}
		return txtTemporada;
	}
	
	
	private JButton getBttArriba(){
		ImageIcon img = new ImageIcon("img/fleArriba.gif");
		bttArriba = new JButton(img);
		bttArriba.setSize(15, 10);
		bttArriba.setLocation(280, 67);
		bttArriba.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttArriba_Click(e);
			}
		});
		return bttArriba;
	}
	
	private JButton getBttAbajo(){
		ImageIcon img = new ImageIcon("img/fleAbajo.gif");
		bttAbajo = new JButton(img);
		bttAbajo.setSize(15, 10);
		bttAbajo.setLocation(280, 77);
		bttAbajo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttAbajo_Click(e);
			}
		});
		return bttAbajo;
	}
	
	private JTextField getTxtHectarea(){
		txtHectareas = new JTextField();
		txtHectareas.setLocation(20, 17);
		txtHectareas.setSize(empresa.getOptions().getX_TamTxt() - 70, empresa.getOptions().getY_TamTxt());
		return txtHectareas;
	}
	
	private JTextField getTxtLote(){
		txtLote = new JTextField();
		txtLote.setLocation(85, 47);
		txtLote.setSize(empresa.getOptions().getX_TamTxt() - 40, empresa.getOptions().getY_TamTxt());
		return txtLote;
	}
	
	private JComboBox getTxtMunicipio(){
		cmbMunicipio = new JComboBox(this.empresa.getConf().getAllCiudades().values().toArray());
		cmbMunicipio.setLocation(240, 47);
		cmbMunicipio.setSize(empresa.getOptions().getX_TamTxt() + 25, empresa.getOptions().getY_TamTxt());
		return cmbMunicipio;
	}
	
	private JFormattedTextField getTxtPA(){
		try{
			MaskFormatter mask = new MaskFormatter("U");
			txtPA = new JFormattedTextField(mask);
			txtPA.setToolTipText("P= Propio A= Asindado");
			txtPA.setLocation(345, 17);
                        txtPA.setSize(empresa.getOptions().getX_TamTxt() - 80, empresa.getOptions().getY_TamTxt());
			txtPA.addKeyListener(new KeyAdapter(){
				public void keyTyped(KeyEvent e){txtPA_change(e);}
			});
			txtPA.addFocusListener(new FocusAdapter(){
				public void focusLost(FocusEvent e){
					txtPA.setText(txtPA.getText().toUpperCase());
				}
                                public void focusGained(FocusEvent e){
                                  txtPA.setCaretPosition(0);
                                    
                                }
			});
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return txtPA;
	}
        
        
		
	private JComboBox getCmbVariedad(){
		cmbVariedad = new JComboBox(empresa.getConf().getAllVariedades().values().toArray());
		cmbVariedad.setLocation(175,17);
		cmbVariedad.setSize(120, 20);
		cmbVariedad.setFont(empresa.getOptions().getFontLbl());
		return cmbVariedad;
	}
	
	private JFormattedTextField getTxtCubierto(){
		txtCubierto = new JFormattedTextField(formato());
		txtCubierto.setLocation(85, 77);
		txtCubierto.setSize(empresa.getOptions().getX_TamTxt() - 40, empresa.getOptions().getY_TamTxt());
		txtCubierto.setValue(new Double(0));
		txtCubierto.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				if (txtCubierto.getText() != null){
					txtCubierto.setValue((Double)(txtCubierto.getValue()) * 100);
				}
			}
			public void focusLost(FocusEvent e){
				txtCubierto.setValue(new Double(txtCubierto.getText()) / 100);
			    if ((Double)(txtCubierto.getValue()) > 100 || (Double)(txtCubierto.getValue()) < 0){
			    	JOptionPane.showMessageDialog(jContentPane, "Error en Cubierto, porcentaje no valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    	this.focusGained(e);
			    }
			}
		});
		return txtCubierto;
	}
        
        private JFormattedTextField getTxtGerminacion(){
		txtGerminacion = new JFormattedTextField(formato());
		txtGerminacion.setLocation(240, 77);
		txtGerminacion.setSize(empresa.getOptions().getX_TamTxt() - 40, empresa.getOptions().getY_TamTxt());
		txtGerminacion.setValue(new Double(0));
		txtGerminacion.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				if (txtGerminacion.getText() != null){
					txtGerminacion.setValue((Double)(txtGerminacion.getValue()) * 100);
				}
			}
			public void focusLost(FocusEvent e){
				txtGerminacion.setValue(new Double(txtGerminacion.getText()) / 100);
			    if ((Double)(txtGerminacion.getValue()) > 100 || (Double)(txtGerminacion.getValue()) < 0){
			    	JOptionPane.showMessageDialog(jContentPane, "Error en Cubierto, porcentaje no valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    	this.focusGained(e);
			    }
			}
		});
		return txtGerminacion;
	}
	
	private JRadioButton getRd1Año(){
		rd1Año = new JRadioButton("1 Año");
		rd1Año.setLocation(130,115);
		rd1Año.setSize(70,15);
		rd1Año.setBackground(empresa.getOptions().getColor_form());
		rd1Año.setFont(empresa.getOptions().getFontLbl());
		rd1Año.setSelected(true);
		return rd1Año;
	}
	
	private JRadioButton getRd2Año(){
		rd2Año = new JRadioButton("2 Año");
		rd2Año.setLocation(198,115);
		rd2Año.setSize(70,15);
		rd2Año.setBackground(empresa.getOptions().getColor_form());
		rd2Año.setFont(empresa.getOptions().getFontLbl());
		return rd2Año;
	}
	
	/**
	 * Trae el formato para el porcentaje
	 * @return DefaultFormatterFactory
	 */
	private DefaultFormatterFactory formato(){
		NumberFormat dispFormat = NumberFormat.getPercentInstance();
		NumberFormat editFormat = NumberFormat.getNumberInstance();
		editFormat.setGroupingUsed(false);
		NumberFormatter dnFormat = new NumberFormatter(dispFormat);
		NumberFormatter enFormat = new NumberFormatter(editFormat);
		DefaultFormatterFactory formato = new DefaultFormatterFactory(dnFormat, dnFormat, enFormat);
		return formato;
	}
	
	private JButton getBttAgregar(){
		ImageIcon img = new ImageIcon("img/flecha_abajo.png");
		bttAgregar = new JButton("Agregar", img);
		bttAgregar.setSize(100, 23);
		bttAgregar.setLocation(90, 147);
		bttAgregar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttAgregar_Click(e);
			}
		});
		return bttAgregar;
	}
	
	private JButton getBttBorrar(){
		ImageIcon img = new ImageIcon("img/flecha_arriba.png");
		bttBorrar = new JButton("Borrar", img);
		bttBorrar.setSize(100, 23);
		bttBorrar.setLocation(200, 147);
		bttBorrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttBorrar_Click(e);
			}
		});
		return bttBorrar;
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
		tblCensoPlantacion.setSelectionMode(0);
		scrTblCenso = new JScrollPane(tblCensoPlantacion);
		scrTblCenso.setSize(370, 120);
		scrTblCenso.setLocation(10, 180);
		tblCensoPlantacion.setBackground(empresa.getOptions().getColorJMenu());
		tblCensoPlantacion.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblCensoPlantacion.getColumnModel().getColumn(1).setPreferredWidth(10);
		tblCensoPlantacion.getColumnModel().getColumn(2).setPreferredWidth(20);
		tblCensoPlantacion.getColumnModel().getColumn(3).setPreferredWidth(20);
		tblCensoPlantacion.getColumnModel().getColumn(4).setPreferredWidth(50);
		tblCensoPlantacion.getColumnModel().getColumn(5).setPreferredWidth(50);
		tblCensoPlantacion.getColumnModel().getColumn(6).setPreferredWidth(70);
		tblCensoPlantacion.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){tblCensoPlantacion(null);}
		});
		tblCensoPlantacion.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e){tblCensoPlantacion(e);}
		});
		this.llenarCenso();
		return scrTblCenso;
	}
	
	private JButton getBttAceptar(){
		ImageIcon img = new ImageIcon("img/Ok.png");
		bttAceptar = new JButton("Aceptar", img);
		bttAceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttAceptar_Click(e);
			}
		});
		
		return bttAceptar;
	}
	
	private JButton getBttCancelar(){
		ImageIcon img = new ImageIcon("img/RedCross.png");
		bttCancelar = new JButton("Cancelar", img);
		bttCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttCancelar_Click(e);
			}
		});
		return bttCancelar;
	}
	
	private JButton getBttListado(){
		bttListado = new JButton("Listar", new ImageIcon("img/listado.png"));
		bttListado.setToolTipText("Listado");
            	bttListado.addActionListener(new ActionListener(){
            		public void actionPerformed(ActionEvent e){
            			bttListado_Click(e);
                        }
		});
		return bttListado;
	}
	
	private void txtPA_change(KeyEvent e){
		char c =e.getKeyChar();
                System.out.println(c);
                if (!((c == 'a') || (c == 'p') || (c == 'A') || (c == 'P') || 
                   (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))){
			e.consume();
		}
		
	}
	
	/**
	 * Procedimiento para encontrar el actual censo a ser utilizado
	 * @param boolean bol = en el caso de que si se cuenta con fecha o no
	 */
	private void ubicar(boolean bol){
		CensoPlantacion censo = null; Calendar cal = Calendar.getInstance();
		this.censoPlantacion = null;
		this.censoGuardar.clear();
                Map censos = unSocio.getAllCensoPlantacion();
		Iterator it = censos.values().iterator();
                while (it.hasNext()){
			censo = (CensoPlantacion)it.next();
                        if (!(bol)){
                                if ((cal.get(Calendar.MONTH)) < 7){
                                  if (censo.getTemporada().equals(String.valueOf(cal.get(Calendar.YEAR)))){
					this.censoPlantacion = censo;
					this.censoGuardar.putAll(censo.getAllInfo());
                                  }
				}else{
                                   if (censo.getTemporada().equals(String.valueOf(cal.get(Calendar.YEAR) + 1))){
					this.censoPlantacion = censo;
					this.censoGuardar.putAll(censo.getAllInfo());
                                    }
                                }
			}else{
				if (censo.getTemporada().equals(txtTemporada.getText().replaceAll("Temporada ",	""))){
					this.censoPlantacion = censo;
					this.censoGuardar.putAll(censo.getAllInfo());
				}
			}
                }
		if (this.censoPlantacion == null){
                        System.out.print("Entre por el null");
			long numero; Fecha_wi fecha = new Fecha_wi(); String temporada;
			numero = this.unSocio.getLastIdCensoPlantacion();
			fecha.asignar_fecha_hora(new Integer(cal.get(Calendar.DAY_OF_MONTH)).toString(),new Integer(cal.get(Calendar.MONTH) + 1).toString(), new Integer(cal.get(Calendar.YEAR)).toString(), "00", "00", "00");
			if ((cal.get(Calendar.MONTH)) < 7){
                            temporada = new Integer(cal.get(Calendar.YEAR)).toString();
                        }else{
                            temporada = new Integer(cal.get(Calendar.YEAR) + 1).toString();
                        }
                        this.censoPlantacion = new CensoPlantacion(numero, fecha,temporada);
                        this.censoGuardar.putAll(censoPlantacion.getAllInfo());
		}
	}
	
	/**
	 * Agrega un CensoPlanInfo, en el caso de haber uno que tiene igual la variedad
	 * y el porcentaje de cubierto, no se agrega sino se suma al ya existente
	 * @param ActionEvent e
	 */
	private void bttAgregar_Click(ActionEvent e){
		String lote; Variedad variedad; double cubierto; double hectarea; 
		Ciudad municipio; int numero;  char pa; Iterator it = this.censoGuardar.values().iterator();
		CensoPlanInfo censoAux; boolean bandera = true; int año; double germinacion;
		numero = censoPlantacion.getLastCount(this.censoGuardar);
		System.out.println((Double)(txtCubierto.getValue()));
		if (txtHectareas.getText().length() <= 0){
			JOptionPane.showMessageDialog(this, "El ingreso de Hectareas no es Valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}else if ((((Double)(txtCubierto.getValue())) < 0.01) || (((Double)txtCubierto.getValue()) > 1.0)){
			JOptionPane.showMessageDialog(this, "El ingreso de Cubierto no es Valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}else{
                    if (txtLote.getText().length() == 0){
                        JOptionPane.showMessageDialog(this, "El ingreso de Lote no es Valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
		    }else{
                        if (txtPA.getText().length()<= 0){
                            JOptionPane.showMessageDialog(this, "El ingreso de P/A no es Valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }else{
                            lote = txtLote.getText();
                            variedad = (Variedad)cmbVariedad.getSelectedItem();
                            cubierto = (((Double)(txtCubierto.getValue()))*100);
                            germinacion = (((Double)(txtGerminacion.getValue()))*100);
                            hectarea = new Double(txtHectareas.getText());
                            municipio = (Ciudad)cmbMunicipio.getSelectedItem();
                            pa = txtPA.getText().charAt(0);
                            if (rd1Año.isSelected()){
                                    año = 1;
                            }else{
                                    año = 2;
                            }
                            while (it.hasNext()){
                                    censoAux = (CensoPlanInfo)it.next();
                                    if ((censoAux.getVariedad().equals(variedad)) && (censoAux.getPorcentaje() == cubierto) &&
                                            (censoAux.getGerminacion() == germinacion) && (lote.equals(censoAux.getLote())) &&
                                            (municipio.equals(censoAux.getMunicipio())) && (año == censoAux.getAño())){
                                            this.censoGuardar.remove(censoAux.getNumero());
                                            censoAux.setHectareas(censoAux.getHectareas() + hectarea);
                                            this.censoGuardar.put(censoAux.getNumero(),censoAux);
                                            bandera = false;
                                            break;
                                    }
                            }
                            if (bandera){
                                    CensoPlanInfo censoInfo = new CensoPlanInfo(numero, hectarea, variedad, cubierto, germinacion, lote, municipio, pa, año);
                                    this.censoGuardar.put(censoInfo.getNumero(), censoInfo);
                            }
                            llenarCenso();
                        }
                    }
		}
		txtHectareas.grabFocus();
	}
	
	/**
	 * Procedimiento encargado de llenar la tabla de censo
	 * @param so
	 */
	public void llenarCenso(){
		Map sorted; Iterator it; CensoPlanInfo info;
		sorted = new TreeMap(this.censoGuardar); Vector row;
		NumberFormat format = NumberFormat.getPercentInstance();
		it = sorted.values().iterator();
		((DefaultTableModel)tblCensoPlantacion.getModel()).setNumRows(0);
		while (it.hasNext()){
			info = (CensoPlanInfo)it.next();
			row = new Vector();
			row.addElement(info.getHectareas());
			row.addElement(info.getAño());
			row.addElement((format.format(new Double(info.getPorcentaje()) / 100)));
			row.addElement((format.format(new Double(info.getGerminacion()) / 100)));
			row.addElement(info.getVariedad());
			row.addElement(info.getLote());
			row.addElement(info.getMunicipio());
			((DefaultTableModel)tblCensoPlantacion.getModel()).addRow(row);
		}
	}
	
	
	/**
	 *  Limpia los txt y el de txtNumero actualiza el numero
	 */
	public void limpiarTxt(){
		txtNumero.setText(new Long(this.censoPlantacion.getNumero()).toString());
                txtFecha.setCalendar(this.censoPlantacion.getFecha_wi().getFecha());
		txtHectareas.setText("");
		txtLote.setText("");
		cmbMunicipio.setSelectedIndex(1);
		txtPA.setText("");
		txtCubierto.setValue(new Double(0));
	}
	
	
	/**
	 * LLena los txt al seleccionar un censo de la tabla
	 * @param KeyEvent e
	 */
	public void tblCensoPlantacion(KeyEvent e){
		int nroFila; CensoPlanInfo censoInfo = null; double cubierto; Variedad variedad; CensoPlanInfo censoAux;
		Iterator it = this.censoGuardar.values().iterator(); String lote;
		nroFila = this.tblCensoPlantacion.getSelectedRow(); 
                if (nroFila > -1){
			variedad = (Variedad)this.tblCensoPlantacion.getValueAt(nroFila, 4);
                        cubierto = new Double(this.limpiarPorcentage((String)this.tblCensoPlantacion.getValueAt(nroFila, 2)));
			lote = (String)this.tblCensoPlantacion.getValueAt(nroFila, 5);
			while (it.hasNext()){
				censoAux = (CensoPlanInfo)it.next();
                                if ((censoAux.getVariedad().equals(variedad)) && (censoAux.getPorcentaje() == cubierto) && (lote.equals(censoAux.getLote()))){
                                    System.out.println("Encontre uno");	
                                    censoInfo = censoAux;
				}
			}
			txtHectareas.setText(new Double(censoInfo.getHectareas()).toString());
			cmbVariedad.setSelectedItem(censoInfo.getVariedad());
			txtCubierto.setValue(new Double(censoInfo.getPorcentaje())/100);
			txtGerminacion.setValue(new Double(censoInfo.getGerminacion())/100);
                        txtLote.setText(censoInfo.getLote());
			cmbMunicipio.setSelectedItem(censoInfo.getMunicipio());
			txtPA.setText(String.valueOf((censoInfo.getPa())));
			if (censoInfo.getAño() == 1){
				rd1Año.setSelected(true);
			}else{
				rd2Año.setSelected(true);
			}
		}
	}
	
	public void bttAceptar_Click(ActionEvent e){
		Fecha_wi fecha = new Fecha_wi();
		if (this.txtFecha.getCalendar() == null){
                    JOptionPane.showMessageDialog(this, "Existe un error en el Formulario, revise si la fecha esta correcta", "Error", JOptionPane.WARNING_MESSAGE);
                }else{
                    fecha.asignar_fecha(txtFecha.getCalendar());
                    this.censoPlantacion.setFecha_wi(fecha);
                    this.censoPlantacion.setTemporada(txtTemporada.getText().replaceAll("Temporada ", ""));
                    this.censoPlantacion.remAllInfoCenso();
                    this.censoPlantacion.addAllInfoCenso(censoGuardar);
                    this.dbCensoInfo.borrarTodo(unSocio, censoPlantacion);
                    this.dbCensoInfo.InsertAll(unSocio, censoPlantacion, censoGuardar);
                    unSocio.addCensoPlantacion(censoPlantacion);
                    this.dbCensoPlantacion.borrar(unSocio, censoPlantacion);
                    this.dbCensoPlantacion.Insert(unSocio, censoPlantacion);
                    MovInformativo movInfo = new MovInformativo(unSocio.getLastIdMovInformativo(), fecha, "Temporada " + this.censoPlantacion.getTemporada() + ", se realizo el Censo de Plantacion");
                    unSocio.remMovInfo(movInfo.getDescripcion());
                    movInfo.setNumero(unSocio.getLastIdCensoPlantacion());
                    unSocio.addInformacion(movInfo);
                    dbMovInfor.borrarPorNombre(movInfo, unSocio);
                    dbMovInfor.Insert(movInfo, unSocio);
                    this.principal.llenarMovInformacion(unSocio);
                    if ((JOptionPane.showConfirmDialog(this, "Desea imprimir lo Censado?", "Mensaje", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION){
                            bttListado_Click(null);
                        }
                    this.dispose();
               }
		
	}
	
	
	public void bttBorrar_Click(ActionEvent e){
		double cubierto; Variedad variedad;Iterator it = this.censoGuardar.values().iterator();
		int nroFila; CensoPlanInfo censoInfo;  String lote; Ciudad muni;
		if (tblCensoPlantacion.getSelectedRowCount() <= 0){
			JOptionPane.showMessageDialog(this, "Por favor seleccione un item del censo", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}else{
			nroFila = this.tblCensoPlantacion.getSelectedRow();
			variedad = (Variedad)this.tblCensoPlantacion.getValueAt(nroFila,4);
			cubierto = new Double(this.limpiarPorcentage((String)this.tblCensoPlantacion.getValueAt(nroFila, 2)));
			lote = (String)this.tblCensoPlantacion.getValueAt(nroFila, 5);
			muni = (Ciudad)this.tblCensoPlantacion.getValueAt(nroFila, 6); 
                        while (it.hasNext()){
				censoInfo = (CensoPlanInfo)it.next();
				if ((censoInfo.getVariedad().equals(variedad)) && (censoInfo.getPorcentaje() == cubierto) &&
                                        (lote.equals(censoInfo.getLote())) && (muni.equals(censoInfo.getMunicipio()))){
					this.censoGuardar.remove(censoInfo.getNumero());
					this.llenarCenso();
					break;
				}
			}
		}
	}
	
	public void bttCancelar_Click(ActionEvent e){
		if (this.censoGuardar.size() != 0){
			if ((JOptionPane.showConfirmDialog(this, "Se cancelaran todos los cambios, desea continuar", "Advertencia", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION){
				this.dispose();
			}
		}else{
			this.dispose();
		}
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
	
	/**
	 * Boton para sumar a la temporada + 1
	 * @param ActionEvent e
	 */
	public void bttArriba_Click(ActionEvent e){
		int año = new Integer(txtTemporada.getText().replaceAll("Temporada ", "")).intValue();
		año = año + 1;
		txtTemporada.setText(new Integer(año).toString());
		ubicar(true);
		this.limpiarTxt();
		this.llenarCenso();
	}
	
	/**
	 * Boton para restar a la temporada - 1
	 * @param ActionEvent e
	 */
	public void bttAbajo_Click(ActionEvent e){
		int año = new Integer(txtTemporada.getText().replaceAll("Temporada ", "")).intValue();
		año = año - 1;
		txtTemporada.setText(new Integer(año).toString());
		ubicar(true);
		this.limpiarTxt();
		this.llenarCenso();
	}
	
	//Accion del Boton bttListado
	private void bttListado_Click(ActionEvent e){
		CensoPlanInfo censoInfo;
		DbRepCensoPlantacionSocio dbRepSo = new DbRepCensoPlantacionSocio(Empresa.con);
		String año = txtTemporada.getText().replaceAll("Temporada ", "");
		dbRepSo.borrarAll();
		if (unSocio.getCensoPlantacion(año) != null){
                    Map map = unSocio.getCensoPlantacion(año).getAllInfo();
                    Iterator it = map.values().iterator();
                    while (it.hasNext()){
                       	censoInfo = (CensoPlanInfo)it.next();
			dbRepSo.Insert(censoInfo, unSocio.getCensoPlantacion(txtTemporada.getText().replaceAll("Temporada ", "")), unSocio, this.empresa);
                    }
		}
		ReporteCensoPlantacionSocio rePo = new ReporteCensoPlantacionSocio(unSocio,txtTemporada.getText().replaceAll("Temporada ", ""), empresa, "Censo Plantacion Socio");
		rePo.start();
	}
        
        
	
}
