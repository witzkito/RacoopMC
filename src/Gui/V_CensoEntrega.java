package Gui;

import Reportes.ReporteCensoEntrega;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
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
import db.DbCensoEntrega;
import db.DbCensoEntregaInfo;
import db.DbMovInformativo;
import base.CensoEntrega;
import base.CensoEntregaInfo;
import base.CensoPlanInfo;
import base.Ciudad;
import base.Empresa;
import base.Fecha_wi;
import base.MovInformativo;
import base.Socio;
import base.Variedad;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowListener;
import java.util.Date;

public class V_CensoEntrega extends JFrame{
	

	private JPanel jContentPane;
	private JPanel pnlSocio;
	private JPanel pnlCensoEntrega;
	private JPanel pnlTbl;
	private JPanel pnlAcciones;
	private JLabel lblSocio;
	private JLabel lblNumero;
        private JLabel lblNumeroCenso;
	private JLabel lblFecha;
        private JLabel lblEnt1Año;
        private JLabel lblEnt2Años;
        private JLabel lblCantDia;
	private JLabel lblLote;
	private JLabel lblPA;
	private JLabel lblKg;
	private JTextField txtNumero;
        private JTextField txtNumeroCenso;
	private JDateChooser txtFecha;
	private JFormattedTextField txtTemporada;
        private JFormattedTextField txtPor1AñoA;
        private JFormattedTextField txtPor1AñoB;
        private JFormattedTextField txtPor2AñoA;
        private JFormattedTextField txtPor2AñoB;
        private JComboBox cmbEnt1Año;
        private JComboBox cmbEnt1AñoB;
        private JComboBox cmbEnt2Años;
        private JComboBox cmbEnt2AñosB;
        private JTextField txtCantDia;
	private JTextField txtKg;
	private JTextField txtLote;
	private JComboBox cmbMunicipio;
	private JRadioButton rd1Año;
	private JRadioButton rd2Año;
	private ButtonGroup rdGroup;
	private JFormattedTextField txtPA;
	private JComboBox cmbVariedad;
	private JButton bttAgregar;
	private JButton bttBorrar;
	private JButton bttAceptar;
	private JButton bttCancelar;
        private JButton bttListado;
	private JButton bttArriba;
	private JButton bttAbajo;
	private JTable tblCensoEntrega;
	private JScrollPane scrTblCenso;
	private Empresa empresa;
        private String temporada;
	private V_Principal principal;
	private CensoEntrega censoEntrega = null;
	private Map censoEntregaInfos;
	private Socio unSocio;
	private DbCensoEntrega dbCensoEntrega;
	private DbMovInformativo dbMovInfor;
	private DbCensoEntregaInfo dbCensoEntregaInfo;
        private Calendar cal = Calendar.getInstance();
	private V_Entrega2Años v_Entrega2Años;
        
	public V_CensoEntrega(Empresa emp,Socio unSocio, V_Principal principal){
		super();
		this.empresa = emp;
		this.principal = principal;
		this.unSocio = unSocio;
		this.dbMovInfor = new DbMovInformativo(Empresa.con);
		this.dbCensoEntrega = new DbCensoEntrega(Empresa.con);
		this.dbCensoEntregaInfo = new DbCensoEntregaInfo(Empresa.con, emp);
		this.censoEntregaInfos = new HashMap();
                this.setIconImage(this.empresa.getOptions().getIcono().getImage());
		ubicar(true);
		initialize();
		this.llenarCensoEntrega();
        }
        
        public V_CensoEntrega(Empresa emp,Socio unSocio, String temporada, V_Principal principal){
		super();
		this.empresa = emp;
		this.principal = principal;
                this.temporada = temporada;
		this.unSocio = unSocio;
		this.dbMovInfor = new DbMovInformativo(Empresa.con);
		this.dbCensoEntrega = new DbCensoEntrega(Empresa.con);
		this.dbCensoEntregaInfo = new DbCensoEntregaInfo(Empresa.con, emp);
		this.censoEntregaInfos = new HashMap();
                this.setIconImage(this.empresa.getOptions().getIcono().getImage());
		ubicar(true);
		initialize();
		this.llenarCensoEntrega();
        }
        
        private void ubicar(boolean bol){
            if (bol){
                this.censoEntrega = this.unSocio.getCensoEntrega(cal.get(Calendar.YEAR));
                if (this.censoEntrega != null){
                   this.censoEntregaInfos.putAll(this.censoEntrega.getAllCensoEntreInfo());
		}else{
                   crearNuevoCenso(bol);
                }
            }else{
              String temporada = txtTemporada.getText().replaceAll("Temporada ", "");
              this.censoEntrega = this.unSocio.getCensoEntrega(temporada);
              this.censoEntregaInfos.clear();
              if (this.censoEntrega != null){
                  this.censoEntregaInfos.putAll(this.censoEntrega.getAllCensoEntreInfo());
              }else{
                  crearNuevoCenso(bol);
              }
            }
        }
        
        private void crearNuevoCenso(boolean bol){
            Fecha_wi fecha = new Fecha_wi();
            String temporada;
            if (bol){
                temporada = String.valueOf(cal.get(Calendar.YEAR));
            }else{
                temporada =  txtTemporada.getText().replaceAll("Temporada ", "");
            }
            fecha.asignar_fecha_hora(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)),
                                    String.valueOf(cal.get(Calendar.MONTH) + 1),
                                    String.valueOf(cal.get(Calendar.YEAR))
                                    , "00", "00", "00");
            censoEntrega = new CensoEntrega(unSocio.getLastIdCensoEntrega(),fecha,
                  temporada, this.empresa.getLastNroCensoEntrega(new Integer(temporada)), 
                  "Enero", 0, " ", 0, "Enero" , 0, " ", 0,0);
        }
	
	private void initialize(){
		this.setTitle("Censo Entrega");
		this.setSize(400,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
                this.addWindowListener(new WindowListener(){
                    public void windowOpened(WindowEvent e) {}
                    public void windowClosing(WindowEvent e) {
                        if (v_Entrega2Años != null){
                            v_Entrega2Años.dispose();}
                    }
                    public void windowClosed(WindowEvent e) {}
                    public void windowIconified(WindowEvent e) {}
                    public void windowDeiconified(WindowEvent e) {}
                    public void windowActivated(WindowEvent e) {}
                    public void windowDeactivated(WindowEvent e) {}
                });
          
	}
	
	private JPanel getJContentPane(){
		jContentPane = new JPanel();
		jContentPane.setBackground(empresa.getOptions().getColor_form());
		jContentPane.setLayout(null);
		jContentPane.add(getPnlSocio());
		jContentPane.add(getPnlEntrega());
		jContentPane.add(getPnlAcciones());
		return jContentPane;
	}
	
	private JPanel getPnlSocio(){
		pnlSocio = new JPanel();
		pnlSocio.setBorder(BorderFactory.createTitledBorder("Socio"));
		pnlSocio.setLocation(2, 0);
		pnlSocio.setSize(390,50);
		pnlSocio.setBackground(empresa.getOptions().getColor_form());
		pnlSocio.setLayout(null);
		pnlSocio.add(getLblSocio());
		return pnlSocio;
	}
	
	//Panel table en donde se agregan los datos para la tabla
	private JPanel getPnlTbl(){
		pnlTbl = new JPanel();
		pnlTbl.setBorder(BorderFactory.createTitledBorder(""));
		pnlTbl.setLocation(1, 170);
		pnlTbl.setSize(388,280);
		pnlTbl.setBackground(empresa.getOptions().getColor_form());
		pnlTbl.setLayout(null);
		pnlTbl.add(getLblKg());
		pnlTbl.add(getTxtKg());
		pnlTbl.add(getLblLote());
		pnlTbl.add(getLblPA());
		pnlTbl.add(getCmbVariedad());
		pnlTbl.add(getTxtLote());
		pnlTbl.add(getCmbMunicipio());
		pnlTbl.add(getTxtPA());
		pnlTbl.add(getBttAgregar());
		pnlTbl.add(getBttBorrar());
		pnlTbl.add(getRd1Año());
		pnlTbl.add(getRd2Año());
		pnlTbl.add(getTblCensoEntrega());
		rdGroup = new ButtonGroup();
		rdGroup.add(rd1Año);
		rdGroup.add(rd2Año);
		return pnlTbl;
	}
	
	private JPanel getPnlEntrega(){
		pnlCensoEntrega = new JPanel();
		pnlCensoEntrega.setBorder(BorderFactory.createTitledBorder("Datos"));
		pnlCensoEntrega.setBackground(empresa.getOptions().getColor_form());
		pnlCensoEntrega.setLayout(null);
		pnlCensoEntrega.setSize(390, 450);
		pnlCensoEntrega.setLocation(2,50);
		pnlCensoEntrega.add(getPnlTbl());
		pnlCensoEntrega.add(getLblNumero());
                pnlCensoEntrega.add(getLblNumeroCenso());
                pnlCensoEntrega.add(getLblFecha());
                pnlCensoEntrega.add(getLblEnt1Año());
                pnlCensoEntrega.add(getLblEnt2Años());
                pnlCensoEntrega.add(getLblCantDia());
                pnlCensoEntrega.add(getTxtNumero());
                pnlCensoEntrega.add(getTxtNumeroCenso());
		pnlCensoEntrega.add(getTxtFecha());
                pnlCensoEntrega.add(getCmbEnt1Año());
                pnlCensoEntrega.add(getTxtPor1AñoA());
                pnlCensoEntrega.add(getTxtPor1AñoB());
                pnlCensoEntrega.add(getCmbEnt1AñoB());
                pnlCensoEntrega.add(getCmbEnt2Años());
                pnlCensoEntrega.add(getCmbEnt2AñosB());
                pnlCensoEntrega.add(getTxtPor2AñoA());
                pnlCensoEntrega.add(getTxtPor2AñoB());
                pnlCensoEntrega.add(getTxtCantDia());
                pnlCensoEntrega.add(getTxtTemporada());
		pnlCensoEntrega.add(getBttArriba());
		pnlCensoEntrega.add(getBttAbajo());
		return pnlCensoEntrega;
	}
	
	private JPanel getPnlAcciones(){
		pnlAcciones = new JPanel();
		pnlAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
		pnlAcciones.setBackground(empresa.getOptions().getColor_form());
		pnlAcciones.setSize(390,65);
		pnlAcciones.setLocation(2,500);
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
		lblNumero.setLocation(10, 27);
		lblNumero.setSize(50, 20);
		return lblNumero;
	}
        
        private JLabel getLblNumeroCenso(){
		lblNumeroCenso = new JLabel("Numero Censo:");
		lblNumeroCenso.setFont(empresa.getOptions().getFontLbl());
		lblNumeroCenso.setLocation(140, 27);
		lblNumeroCenso.setSize(150, 20);
		return lblNumeroCenso;
	}
	
	private JLabel getLblFecha(){
		lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(empresa.getOptions().getFontLbl());
		lblFecha.setLocation(10, 57);
		lblFecha.setSize(50, 20);
		return lblFecha;
	}
        
        private JLabel getLblEnt1Año(){
		lblEnt1Año = new JLabel("Mes 1 Año:");
		lblEnt1Año.setFont(empresa.getOptions().getFontLbl());
		lblEnt1Año.setLocation(10, 87);
		lblEnt1Año.setSize(90, 20);
		return lblEnt1Año;
	}
        
        private JLabel getLblEnt2Años(){
		lblEnt2Años = new JLabel("Mes 2 Años:");
		lblEnt2Años.setFont(empresa.getOptions().getFontLbl());
		lblEnt2Años.setLocation(10, 117);
		lblEnt2Años.setSize(90, 20);
		return lblEnt2Años;
	}
        
        private JLabel getLblCantDia(){
		lblCantDia = new JLabel("Cant. por Dia:");
		lblCantDia.setFont(empresa.getOptions().getFontLbl());
		lblCantDia.setLocation(10, 147);
		lblCantDia.setSize(110, 20);
		return lblCantDia;
	}
        	
	private JLabel getLblKg(){
		lblKg = new JLabel("Kg");
		lblKg.setFont(empresa.getOptions().getFontLbl());
		lblKg.setLocation(35, 17);
		lblKg.setSize(50, 20);
		return lblKg;
	}
	
	private JLabel getLblLote(){
		lblLote = new JLabel("Lote Nro.");
		lblLote.setFont(empresa.getOptions().getFontLbl());
		lblLote.setLocation(35,47);
		lblLote.setSize(70, 20);
		return lblLote;
	}
	
	private JLabel getLblPA(){
		lblPA = new JLabel("P/A");
		lblPA.setFont(empresa.getOptions().getFontLbl());
		lblPA.setLocation(295, 17);
		lblPA.setSize(40, 20);
		return lblPA;
	}
	
	private JTextField getTxtNumero(){
		txtNumero = new JTextField();
		txtNumero.setLocation(70, 27);
		txtNumero.setSize(empresa.getOptions().getX_TamTxt() - 50, empresa.getOptions().getY_TamTxt());
		txtNumero.setEditable(false);
		txtNumero.setText(new Long(this.censoEntrega.getNumero()).toString());
		return txtNumero;
	}
        
        private JTextField getTxtNumeroCenso(){
		txtNumeroCenso = new JTextField();
		txtNumeroCenso.setLocation(250, 27);
		txtNumeroCenso.setSize(empresa.getOptions().getX_TamTxt() - 50, empresa.getOptions().getY_TamTxt());
		txtNumeroCenso.setEditable(false);
                txtNumeroCenso.setText(String.valueOf(this.censoEntrega.getNroCenso()));
		return txtNumeroCenso;
	}
	
	private JDateChooser getTxtFecha(){
		txtFecha = new JDateChooser();
		txtFecha.setLocation(70, 57);
		txtFecha.setSize(empresa.getOptions().getX_TamTxt() - 10, empresa.getOptions().getY_TamTxt());
		txtFecha.grabFocus();
                txtFecha.setCalendar(this.censoEntrega.getFecha().getFecha());
                return txtFecha;
	}
        
        private void setFecha(){
             if (this.censoEntrega.getFecha().getFecha() == null){
                    txtFecha.setDate(new Date());
                }else{
                    txtFecha.setCalendar(this.censoEntrega.getFecha().getFecha());
             }
        }
        
       
	private JFormattedTextField getTxtTemporada(){
		try{
			MaskFormatter mask = new MaskFormatter("Temporada ####");
			txtTemporada = new JFormattedTextField(mask);
			txtTemporada.setLocation(170, 57);
			txtTemporada.setSize(empresa.getOptions().getX_TamTxt(), empresa.getOptions().getY_TamTxt());
                        if(temporada == null){
                            txtTemporada.setText(this.censoEntrega.getTemporada());
                        }else{
                            txtTemporada.setText(temporada);
                        }
			txtTemporada.setEditable(false);
		}catch (Exception ex){
			JOptionPane.showMessageDialog(this, ex, "Error en el mask", JOptionPane.ERROR_MESSAGE);
		}
		return txtTemporada;
	}
        
        private JComboBox getCmbEnt1Año(){
            cmbEnt1Año = new JComboBox(getMeses().values().toArray());
            cmbEnt1Año.setLocation(100, 87);
            cmbEnt1Año.setSize(empresa.getOptions().getX_TamTxt() - 5, empresa.getOptions().getY_TamTxt());
            cmbEnt1Año.setSelectedItem(this.censoEntrega.getMes1Año());
            return cmbEnt1Año;
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
        
        private JFormattedTextField getTxtPor1AñoA(){
		txtPor1AñoA = new JFormattedTextField(formato());
		txtPor1AñoA.setLocation(200, 87);
                txtPor1AñoA.setSize(empresa.getOptions().getX_TamTxt() - 60, empresa.getOptions().getY_TamTxt());
		txtPor1AñoA.setValue(new Double(this.censoEntrega.getPorMes1()) / 100);
		txtPor1AñoA.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				if (txtPor1AñoA.getText() != null){
					txtPor1AñoA.setValue((Double)(txtPor1AñoA.getValue()) * 100);
				}
			}
			public void focusLost(FocusEvent e){
				txtPor1AñoA.setValue(new Double(txtPor1AñoA.getText()) / 100);
			    if ((Double)(txtPor1AñoA.getValue()) > 100 || (Double)(txtPor1AñoA.getValue()) < 0){
			    	JOptionPane.showMessageDialog(jContentPane, "Error en Porcentaje, valor no valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    	this.focusGained(e);
			    }
			}
		});
		return txtPor1AñoA;
	}
        
        private JComboBox getCmbEnt1AñoB(){
            Map meses = new HashMap();
            meses.put(1, " "); meses.putAll(getMeses());
            cmbEnt1AñoB = new JComboBox(meses.values().toArray());
            cmbEnt1AñoB.setLocation(244, 87);
            cmbEnt1AñoB.setSize(empresa.getOptions().getX_TamTxt() - 5, empresa.getOptions().getY_TamTxt());
            cmbEnt1AñoB.setSelectedItem(this.censoEntrega.getMes1AñoB());
            cmbEnt1AñoB.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (cmbEnt1AñoB.getSelectedItem().equals(" ")){
                        txtPor1AñoB.setValue(new Double(0));
                    }
                }
            });
            return cmbEnt1AñoB;
	}
        
        private JFormattedTextField getTxtPor1AñoB(){
		txtPor1AñoB = new JFormattedTextField(formato());
		txtPor1AñoB.setLocation(343, 87);
                txtPor1AñoB.setSize(empresa.getOptions().getX_TamTxt() - 60, empresa.getOptions().getY_TamTxt());
		txtPor1AñoB.setValue(new Double(this.censoEntrega.getPorMes1B()) / 100);
		txtPor1AñoB.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				if (txtPor1AñoB.getText() != null){
					txtPor1AñoB.setValue((Double)(txtPor1AñoB.getValue()) * 100);
				}
			}
			public void focusLost(FocusEvent e){
				txtPor1AñoB.setValue(new Double(txtPor1AñoB.getText()) / 100);
			}
		});
                return txtPor1AñoB;
	}
        
        private JComboBox getCmbEnt2Años(){
            cmbEnt2Años = new JComboBox(getMeses().values().toArray());
            cmbEnt2Años.setLocation(100, 117);
            cmbEnt2Años.setSize(empresa.getOptions().getX_TamTxt() - 5, empresa.getOptions().getY_TamTxt());
            cmbEnt2Años.setSelectedItem(this.censoEntrega.getMes2Años());
            return cmbEnt2Años;
	}
        
        private JComboBox getCmbEnt2AñosB(){
            Map meses = new HashMap();
            meses.put(1, " "); meses.putAll(getMeses());
            cmbEnt2AñosB = new JComboBox(meses.values().toArray());
            cmbEnt2AñosB.setLocation(244, 117);
            cmbEnt2AñosB.setSize(empresa.getOptions().getX_TamTxt() - 5, empresa.getOptions().getY_TamTxt());
            cmbEnt2AñosB.setSelectedItem(this.censoEntrega.getMes2AñosB());
            cmbEnt2AñosB.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (cmbEnt2AñosB.getSelectedItem().equals(" ")){
                        txtPor2AñoB.setValue(new Double(0));
                    }
                }
            });
            return cmbEnt2AñosB;
	}
        
        private JFormattedTextField getTxtPor2AñoA(){
		txtPor2AñoA = new JFormattedTextField(formato());
		txtPor2AñoA.setLocation(200, 117);
		txtPor2AñoA.setSize(empresa.getOptions().getX_TamTxt() - 60, empresa.getOptions().getY_TamTxt());
		txtPor2AñoA.setValue(new Double(this.censoEntrega.getPorMes2()) / 100);
		txtPor2AñoA.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				if (txtPor2AñoA.getText() != null){
					txtPor2AñoA.setValue((Double)(txtPor2AñoA.getValue()) * 100);
				}
			}
			public void focusLost(FocusEvent e){
				txtPor2AñoA.setValue(new Double(txtPor2AñoA.getText()) / 100);
			    if ((Double)(txtPor2AñoA.getValue()) > 100 || (Double)(txtPor2AñoA.getValue()) < 0){
			    	JOptionPane.showMessageDialog(jContentPane, "Error en Porcentaje, valor no valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    	this.focusGained(e);
			    }
			}
		});
		return txtPor2AñoA;
	}
        
        private JFormattedTextField getTxtPor2AñoB(){
		txtPor2AñoB = new JFormattedTextField(formato());
		txtPor2AñoB.setLocation(343, 117);
		txtPor2AñoB.setSize(empresa.getOptions().getX_TamTxt() - 60, empresa.getOptions().getY_TamTxt());
		txtPor2AñoB.setValue(new Double(this.censoEntrega.getPorMes2B()) / 100);
		txtPor2AñoB.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				if (txtPor2AñoB.getText() != null){
					txtPor2AñoB.setValue((Double)(txtPor2AñoB.getValue()) * 100);
				}
			}
			public void focusLost(FocusEvent e){
				txtPor2AñoB.setValue(new Double(txtPor2AñoB.getText()) / 100);
			    if ((Double)(txtPor2AñoB.getValue()) > 100 || (Double)(txtPor2AñoB.getValue()) < 0){
			    	JOptionPane.showMessageDialog(jContentPane, "Error en Porcentaje, valor no valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    	this.focusGained(e);
			    }
			}
		});
		return txtPor2AñoB;
	}
        
         
        private JTextField getTxtCantDia(){
            txtCantDia = new JTextField();
            txtCantDia.setLocation(115, 147);
            txtCantDia.setSize(empresa.getOptions().getX_TamTxt() - 20, empresa.getOptions().getY_TamTxt());
            txtCantDia.setText(String.valueOf(this.censoEntrega.getKgPorDia()));
            return txtCantDia;
	}
        
        private Map getMeses(){
            Map meses = new HashMap();
            meses.put(2, "Enero"); meses.put(3, "Febrero"); meses.put(4, "Marzo");
            meses.put(5, "Abril"); meses.put(6, "Mayo"); meses.put(7, "Junio");
            meses.put(8, "Julio"); meses.put(9, "Agosto"); meses.put(10, "Septiembre");
            meses.put(11, "Octubre"); meses.put(12, "Noviembre"); meses.put(13, "Diciembre");
            return meses;
        }
	
	
	private JButton getBttArriba(){
		ImageIcon img = new ImageIcon("img/fleArriba.gif");
		bttArriba = new JButton(img);
		bttArriba.setSize(15, 10);
		bttArriba.setLocation(275, 57);
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
		bttAbajo.setLocation(275, 67);
		bttAbajo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttAbajo_Click(e);
			}
		});
		return bttAbajo;
	}
	
	private JTextField getTxtKg(){
		txtKg = new JTextField();
		txtKg.setLocation(65, 17);
		txtKg.setSize(empresa.getOptions().getX_TamTxt() - 30, empresa.getOptions().getY_TamTxt());
		return txtKg;
	}
	
	private JTextField getTxtLote(){
		txtLote = new JTextField();
		txtLote.setLocation(105, 47);
		txtLote.setSize(empresa.getOptions().getX_TamTxt() - 40, empresa.getOptions().getY_TamTxt());
		return txtLote;
	}
	
	private JComboBox getCmbMunicipio(){
		cmbMunicipio = new JComboBox(this.empresa.getConf().getAllCiudades().values().toArray());
		cmbMunicipio.setLocation(183, 47);
		cmbMunicipio.setSize(empresa.getOptions().getX_TamTxt() + 60, empresa.getOptions().getY_TamTxt());
		return cmbMunicipio;
	}
	
	private JFormattedTextField getTxtPA(){
		try{
			MaskFormatter mask = new MaskFormatter("U");
			txtPA = new JFormattedTextField(mask);
			txtPA.setLocation(325, 17);
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
		cmbVariedad.setLocation(160,17);
		cmbVariedad.setSize(120, 20);
		cmbVariedad.setFont(empresa.getOptions().getFontLbl());
		return cmbVariedad;
	}
	
		
		
	private JRadioButton getRd1Año(){
		rd1Año = new JRadioButton("1 Año");
		rd1Año.setLocation(130,85);
		rd1Año.setSize(70,15);
                rd1Año.setSelected(true);
		rd1Año.setBackground(empresa.getOptions().getColor_form());
		rd1Año.setFont(empresa.getOptions().getFontLbl());
		return rd1Año;
	}
	
	private JRadioButton getRd2Año(){
		rd2Año = new JRadioButton("2 Años");
		rd2Año.setLocation(198, 85);
		rd2Año.setSize(75,15);
		rd2Año.setBackground(empresa.getOptions().getColor_form());
		rd2Año.setFont(empresa.getOptions().getFontLbl());
                rd2Año.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        rd2Años_Click(e);
                    }
                });
		return rd2Año;
	}
	
	private JButton getBttAgregar(){
                ImageIcon img = new ImageIcon("img/flecha_abajo.png");
		bttAgregar = new JButton("Agregar", img);
		bttAgregar.setSize(100, 23);
		bttAgregar.setLocation(90, 112);
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
		bttBorrar.setLocation(200, 112);
		bttBorrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bttBorrar_Click(e);
			}
		});
		return bttBorrar;
	}
	
	private JScrollPane getTblCensoEntrega(){
		Vector colNames = new Vector(6);
		colNames.addElement("Kg");
		colNames.addElement("Ciclo");
		colNames.addElement("Variedad");
		colNames.addElement("P/A");
		colNames.addElement("Lote");
		colNames.addElement("Municipio");
		Vector rows = new Vector();
		V_Tabla t = new V_Tabla(rows, colNames);
		tblCensoEntrega = new JTable(t);
		tblCensoEntrega.setDefaultRenderer(String.class, new TableRender());
		tblCensoEntrega.setSelectionMode(0);
		scrTblCenso = new JScrollPane(tblCensoEntrega);
		scrTblCenso.setSize(373, 120);
		scrTblCenso.setLocation(10, 152);
		tblCensoEntrega.setBackground(empresa.getOptions().getColorJMenu());
		tblCensoEntrega.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblCensoEntrega.getColumnModel().getColumn(1).setPreferredWidth(1);
		tblCensoEntrega.getColumnModel().getColumn(2).setPreferredWidth(40);
		tblCensoEntrega.getColumnModel().getColumn(3).setPreferredWidth(1);
		tblCensoEntrega.getColumnModel().getColumn(4).setPreferredWidth(30);
		tblCensoEntrega.getColumnModel().getColumn(5).setPreferredWidth(40);
		tblCensoEntrega.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){tblCensoEntrega(null);}
		});
		tblCensoEntrega.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e){tblCensoEntrega(e);}
		});
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
                if (!((c == 'a') || (c == 'p') || (c == 'A') || (c == 'P') || 
                   (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))){
			e.consume();
		}
	}
	
	
	
	/**
	 * Agrega un CensoEntregaInfo a la taba de 1 año o de 2 años
	 * @param ActionEvent e
	 */
	private void bttAgregar_Click(ActionEvent e){
		String lote; Variedad variedad; double kg; Ciudad municipio; int año;
		int numero;  char pa;
		numero = censoEntrega.getLastCount(this.censoEntregaInfos);
		if (txtKg.getText().length() <= 0){
			JOptionPane.showMessageDialog(this, "El ingreso de Kg no es Valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}else{
                    if (txtLote.getText().length() <= 0){
                        JOptionPane.showMessageDialog(this, "El ingreso del Lote no es Valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }else{
                         if ((txtPA.getValue() == null) || (txtPA.getText().equals(" "))) {
                        JOptionPane.showMessageDialog(this, "El ingreso del Propio/Arrendado no es Valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }else{
                                kg = new Double(txtKg.getText());
                                lote = txtLote.getText();
                                variedad = (Variedad)cmbVariedad.getSelectedItem();
                                municipio = (Ciudad)cmbMunicipio.getSelectedItem();
                                pa = txtPA.getText().charAt(0);
                                if (rd1Año.isSelected()){
                                    año = 1;
                                }else{
                                    año = 2;
                                }
                                if (censoEntrega.getCensoEntregaInfo(lote, variedad, año) == null){
                                    CensoEntregaInfo censoInfo = new CensoEntregaInfo(numero, lote, municipio, pa,variedad, kg, año);
                                    this.censoEntregaInfos.put(numero, censoInfo);
                                    llenarCensoEntrega();
                                }else{
                                    JOptionPane.showMessageDialog(this, "Ya se encuentra la entrega", "Mensaje", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                    }
		}
	}
	
	
	
	/**
	 * Busca correspondencias entre censoPlanInfo y CensoEntregaInfo para dibujar
	 * el Icon y saber que ya se asigno a esa un censoEntregaInfo
	 * @param CensoPlanInfo censo
	 * @return String (I1, I2)
	 */
	private String getEstadoPlantacion(CensoPlanInfo censo){
		CensoEntregaInfo censoInfos = null; String retornar = "P2";
		Iterator it = this.censoEntregaInfos.values().iterator();
		while (it.hasNext()){
			censoInfos = (CensoEntregaInfo)it.next();
			if (censo.getVariedad().equals(censoInfos.getVariedad()) && censo.getLote().equals(censoInfos.getLote()) && (censo.getAño() == censoInfos.getAño()) &&
                                (censo.getMunicipio().equals(censoInfos.getMunicipio()))){
				retornar = "P1";
			}else{
				
			}
		}
		return retornar;
	}

	/**
	 *Procedimiento encargado de llenar las tablas de censo Entrega 
	 */
	public void llenarCensoEntrega(){
		Map sorted; Iterator it; CensoEntregaInfo info;
		sorted = new TreeMap(this.censoEntregaInfos); Vector row;
		((DefaultTableModel)tblCensoEntrega.getModel()).setNumRows(0);
                it = sorted.values().iterator();
		while (it.hasNext()){
			info = (CensoEntregaInfo)it.next();
			row = new Vector();
                        row.addElement(info.getKg());
                        row.addElement(info.getAño());
                        row.addElement(info.getVariedad());
                        row.addElement(info.getPa());
			row.addElement(info.getLote());
			row.addElement(info.getMunicipio());
                       ((DefaultTableModel)tblCensoEntrega.getModel()).addRow(row);
		}
	}
	
	
	/**
	 *  Limpia los txt y el de txtNumero actualiza el numero
	 */
	public void limpiarTxt(){
		txtNumero.setText(new Long(this.censoEntrega.getNumero()).toString());
                txtNumeroCenso.setText(String.valueOf(this.censoEntrega.getNroCenso()));
                cmbEnt1Año.setSelectedItem(this.censoEntrega.getMes1Año());
                cmbEnt1AñoB.setSelectedItem(this.censoEntrega.getMes1AñoB());
		cmbEnt2Años.setSelectedItem(this.censoEntrega.getMes2Años());
                cmbEnt2AñosB.setSelectedItem(this.censoEntrega.getMes2AñosB());
                txtPor2AñoB.setValue(new Double(this.censoEntrega.getPorMes2B()) / 100);
                txtPor2AñoA.setValue(new Double(this.censoEntrega.getPorMes2()) / 100);
                txtPor1AñoB.setValue(new Double(this.censoEntrega.getPorMes1B()) / 100);
                txtPor1AñoA.setValue(new Double(this.censoEntrega.getPorMes1()) / 100);
                txtCantDia.setText(String.valueOf(this.censoEntrega.getKgPorDia()));
                txtKg.setText("");
		txtLote.setText("");
		txtPA.setText("");
                
	}
	
	
	/**
	 * LLena los txt al seleccionar un censo de la tabla
	 * @param KeyEvent e
	 */
	public void tblCensoEntrega(KeyEvent e){
		int nroFila; CensoEntregaInfo censoInfo = null; double cubierto; Variedad variedad; CensoEntregaInfo censoAux;
		String lote; int año;
                nroFila = this.tblCensoEntrega.getSelectedRow(); 
		if (nroFila > -1){
			variedad = (Variedad)this.tblCensoEntrega.getValueAt(nroFila, 2);
			lote = (String)this.tblCensoEntrega.getValueAt(nroFila, 4);
			año = (Integer)(this.tblCensoEntrega.getValueAt(nroFila, 1));
                      	censoInfo = this.censoEntrega.getCensoEntregaInfo(lote, variedad, año);
			if (censoInfo == null){
                            censoInfo = buscarCenso(variedad,lote,año);
                        }
                        cmbVariedad.setSelectedItem(censoInfo.getVariedad());
			txtLote.setText(censoInfo.getLote());
			cmbMunicipio.setSelectedItem(censoInfo.getMunicipio());
			txtPA.setText(String.valueOf((censoInfo.getPa())));
			txtKg.setText(String.valueOf(censoInfo.getKg()));
                        if (censoInfo.getAño() == 1){
				rd1Año.setSelected(true);
			}else if (censoInfo.getAño() == 2){
				rd2Año.setSelected(true);
			}
		}
	}
        
        private CensoEntregaInfo buscarCenso(Variedad variedad, String lote, int año){
            CensoEntregaInfo censoAux; CensoEntregaInfo censoInfo = null;
            Iterator it = this.censoEntregaInfos.values().iterator();
            while (it.hasNext()){
		censoAux = (CensoEntregaInfo)it.next();
		if ((censoAux.getVariedad().equals(variedad)) &&  (lote.equals(censoAux.getLote())) && (censoAux.getAño() == año )){
                    censoInfo = censoAux;
		}
            }
            return censoInfo;
        }
	
	public void bttAceptar_Click(ActionEvent e){
		Fecha_wi fecha = new Fecha_wi();
		try{
                    String comp = base.CompFecha.getComprobacion(txtFecha);
                    if (comp.equals(" ")){
			if (this.censoEntregaInfos.size() == 0){
                            int dev = JOptionPane.showConfirmDialog(this, "No se registraron movimientos, desea guardar de todos modos?", "Mensaje", JOptionPane.YES_NO_CANCEL_OPTION);
                            if (dev == JOptionPane.YES_OPTION){
                                guardarCenso();
                            }else if (dev == JOptionPane.NO_OPTION){
                                this.unSocio.remCensoEntrega(new Integer(this.txtNumero.getText()));
                                unSocio.remMovInfo(new String("Temporada " + this.censoEntrega.getTemporada() + ", se realizo el Censo de Entrega"));
                                dbMovInfor.borrarPorNombre(new MovInformativo(0,fecha,"Temporada " + this.censoEntrega.getTemporada() + ", se realizo el Censo de Entrega"), unSocio);
                                dbCensoEntrega.borrar(unSocio, censoEntrega);
                                this.dispose();
                            }
                        }else{
                            guardarCenso();                            
                        }
                    }else{
                        JOptionPane.showMessageDialog(this,"Error en la fecha", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    }
  		}catch (Exception ex){
			JOptionPane.showMessageDialog(this, "Error Inexperado en la fecha", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
        public void guardarCenso(){
            Fecha_wi fecha = new Fecha_wi();
            fecha.asignar_fecha(txtFecha.getCalendar());
            this.censoEntrega.setFecha(fecha);
            this.censoEntrega.setTemporada(txtTemporada.getText().replaceAll("Temporada ", ""));
            this.censoEntrega.setMes1Año(String.valueOf(this.cmbEnt1Año.getSelectedItem()));
            this.censoEntrega.setPorMes1((Double)(this.txtPor1AñoA.getValue()) * 100);
            this.censoEntrega.setMes1AñoB(String.valueOf(this.cmbEnt1AñoB.getSelectedItem()));
            this.censoEntrega.setPorMes1B((Double)(this.txtPor1AñoB.getValue()) * 100);
            this.censoEntrega.setMes2Años(String.valueOf(this.cmbEnt2Años.getSelectedItem()));
            this.censoEntrega.setPorMes2((Double)(this.txtPor2AñoA.getValue()) * 100);
            this.censoEntrega.setMes2AñosB(String.valueOf(this.cmbEnt2AñosB.getSelectedItem()));
            this.censoEntrega.setPorMes2B((Double)(this.txtPor2AñoB.getValue()) * 100);
            this.censoEntrega.setKgPorDia(new Double(this.txtCantDia.getText()).longValue());
            censoEntrega.remAllCensoEntreInfos();
            censoEntrega.addAll(censoEntregaInfos);
            unSocio.addCensoEntrega(censoEntrega);
            dbCensoEntrega.borrar(unSocio, censoEntrega);
            dbCensoEntrega.Insert(unSocio, censoEntrega);
            dbCensoEntregaInfo.borrarTodo(unSocio, censoEntrega);
            dbCensoEntregaInfo.InsertAll(unSocio, censoEntrega, censoEntrega.getAllCensoEntreInfo());
            MovInformativo movInfo = new MovInformativo(unSocio.getLastIdMovInformativo(), fecha, "Temporada " + this.censoEntrega.getTemporada() + ", se realizo el Censo de Entrega");
            unSocio.remMovInfo(movInfo.getDescripcion());
            movInfo.setNumero(unSocio.getLastIdMovInformativo());
            unSocio.addInformacion(movInfo);
            dbMovInfor.borrarPorNombre(movInfo, unSocio);
            dbMovInfor.Insert(movInfo, unSocio);
            this.principal.llenarMovInformacion(unSocio);
            if ((JOptionPane.showConfirmDialog(this, "Desea imprimir lo Censado?", "Mensaje", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION){
                bttListado_Click(null);
            }
            this.dispose();
        }
        
	public void bttBorrar_Click(ActionEvent e){
		Variedad variedad; double kg; int año;
		if (txtKg.getText().length() <= 0){
			JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun Item del censo", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}else{
			variedad = (Variedad)cmbVariedad.getSelectedItem();
			kg = new Double(txtKg.getText());
			if(rd1Año.isSelected()){
				año = 1;
			}else{
				año = 2;
			}
			int numeroBorrar = -1;
			Iterator it = this.censoEntregaInfos.values().iterator();
			CensoEntregaInfo censoInfo;
			while (it.hasNext()){
				censoInfo = (CensoEntregaInfo)it.next();
				if ((censoInfo.getVariedad().equals(variedad)) && (censoInfo.getKg() == kg) && (censoInfo.getAño() == año)){
					numeroBorrar = censoInfo.getNumero();
				}
			}
			if (numeroBorrar != -1){
				this.censoEntregaInfos.remove(numeroBorrar);
			}
			this.llenarCensoEntrega();
			this.limpiarTxt();
		}
	}
	
	public void bttCancelar_Click(ActionEvent e){
		if (this.censoEntregaInfos.size() != 0){
			if ((JOptionPane.showConfirmDialog(this, "Se cancelaran todos los cambios, desea continuar?", "Advertencia", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION){
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
                ubicar(false);
                this.limpiarTxt();
		this.llenarCensoEntrega();
                setFecha();
	}
	
	/**
	 * Boton para restar a la temporada - 1
	 * @param ActionEvent e
	 */
	public void bttAbajo_Click(ActionEvent e){
		int año = new Integer(txtTemporada.getText().replaceAll("Temporada ", "")).intValue();
		año = año - 1;
		txtTemporada.setText(new Integer(año).toString());
		ubicar(false);
                this.limpiarTxt();
		this.llenarCensoEntrega();
                setFecha();
        }
        
        //Accion del Boton bttListado
	private void bttListado_Click(ActionEvent e){
            ReporteCensoEntrega rePo = new ReporteCensoEntrega(unSocio,this.censoEntrega, 
                    txtTemporada.getText().replaceAll("Temporada ", ""),
                    empresa, "Censo Entrega");
            rePo.start();
	}
        
        private void rd2Años_Click(ActionEvent e){
            Map censoInfo = new HashMap();
            if (v_Entrega2Años == null){ 
                v_Entrega2Años = new V_Entrega2Años(this.empresa,this.unSocio,
                     txtTemporada.getText().replaceAll("Temporada ", ""), this);
            }else{
                v_Entrega2Años.dispose();
                v_Entrega2Años = null;
                v_Entrega2Años = new V_Entrega2Años(this.empresa,this.unSocio,
                     txtTemporada.getText().replaceAll("Temporada ", ""), this);
           }
        }
        
        /**
         * Procedimiento que carga la variedad, el lote y el municipio
         * @param variedad Variedad
         * @param lote String
         * @param municipio Ciudad
          */
        public void cargar(Variedad variedad, String lote, Ciudad municipio){
            this.cmbVariedad.setSelectedItem(variedad);
            this.txtLote.setText(lote);
            this.cmbMunicipio.setSelectedItem(municipio);            
        }
        
        
        
	
	

}
