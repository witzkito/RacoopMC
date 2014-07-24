package Gui;
//Libro de cuotas Sociales          Racoop V1.0
/*
 * Clase encargada de mostrar el Principal
 * Created on 09-jul-2007
*/

/**
 * @author Adrian
**/
import Reportes.ReporteEntregas;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import base.Empresa;
import base.Entrega;
import base.MovInformativo;
import base.Socio;
import base.Telefono;
import base.Tercero;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;



/**
 * @author Adrian
 * Clase Visual de la Ventana Principal del Sistema
 */
@SuppressWarnings("serial")
public class V_Principal extends JFrame {
	//Atributos
		private JPanel jContentPane;
		private JSplitPane pnlSociosS;
		private JPanel pnlSocios;
		private JPanel pnlAD;
		private JPanel pnlSuperior;
		private JPanel pnlDatos;
		private JTabbedPane pnlTbMovInfo;
		private JTable tblInformacion;
                private JTable tblEntrega;
		private JMenuBar jMenuBar;
		private JMenu jMenu;
		private JMenuItem item;
		private JTabbedPane tpDatos;
		private JPanel pnlInformacion;
                private JScrollPane scrTblEnt;
		private JScrollPane scrTblInfo;
		private JLabel lblNumeroB;
		private JTextField txtNumeroB;
		private JLabel lblNombreB;
		private JTextField txtNombreB;
		public JRadioButton chkActivosB;
		private JTable tblSocios;
		private JPanel pnlAcciones;
                private JPanel pnlEntrega;
                private JPanel pnlListadoEntrega;
		private JButton bttCensoPlantacion;
		private JButton bttCensoEntrega;
		private JButton bttTelefono;
                private JButton bttListadoEntrega;
                private JButton bttPlanillaEntrega;
                private JLabel lblNumeroI;
		private JLabel lblNumeroIR;
		private JLabel lblNombreI;
		private JLabel lblNombreIR;
		private JLabel lblTipoDocI;
		private JRadioButton rdDniI;
		private JRadioButton rdLeI;
		private JRadioButton rdLcI;
		private JRadioButton rdCiI;
		private JLabel lblNroDocumentoI;
		private JLabel lblNroDocumentoIR;
		private JLabel lblIvaI;
		private JRadioButton rdRiI;
		private JRadioButton rdRniI;
		private JRadioButton rdExeI;
		private JRadioButton rdMoI;
		private JRadioButton rdCfI;
		private JRadioButton chkSocios;
                private JRadioButton chkTerceros;
                private JLabel lblCuitI;
		private JLabel lblCuitIR;
		private JLabel lblDomicilioI;
		private JLabel lblDomicilioIR;
		private JLabel lblFechaIngresoI;
		private JLabel lblFechaIngresoIR;
		private JLabel lblActaIngresoI;
		private JLabel lblActaIngresoIR; 
		private Socio unSocio;
		private Tercero unTercero;
                private Empresa empresa;
		public MovInformativo movInfor = null;
		private V_Inicio inicio;
		private V_CensoPlantacion censoPlantacion;
                private V_CensoEntrega censoEntrega;
                private V_PlanillaEntrega planillaEntrega;
                private V_PlanillaEntregaTer planillaEntregaTer;
                private V_VerCensado verCensado;
                
                
			
				
	//Constructor
		public V_Principal(Empresa empresa, V_Inicio inicio){
			super();
			this.empresa = empresa;
			this.inicio = inicio;
                        initialize();
			
		}
		
	//Metodos
		//Creacion del panel principal
		private void initialize(){ 
			this.setSize(800,600);
			this.setLocationRelativeTo(null);
			this.setTitle(this.empresa.getOptions().getVersion());
			this.setContentPane(getJContentPane());
			this.setVisible(true);
			this.setIconImage(this.empresa.getOptions().getIcono().getImage());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					try{
						Empresa.con.getConeccion().close();
					}catch(SQLException ex){
						JOptionPane.showMessageDialog(jContentPane, "Error al cerrar la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		
		//Inicialisacion de JContntPane
		private JPanel getJContentPane(){
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBackground(empresa.getOptions().getColor_frame());
			jContentPane.add(getJJMenuBar(), BorderLayout.NORTH);
			jContentPane.add(getPnlSociosS(), BorderLayout.CENTER);
			return this.jContentPane;
		}
		
		//MenuBar de la parte superior de la ventana
		private JMenuBar getJJMenuBar(){
			jMenuBar = new JMenuBar();
			jMenuBar.setPreferredSize(new Dimension(500,25));
			jMenuBar.setBackground(empresa.getOptions().getColorJMenu());
			jMenu = new JMenu("Archivo");
			jMenu.setMnemonic('a');
			jMenuBar.add(jMenu);
			item = new JMenuItem("Importar");
			item.setToolTipText("Importaciones desde otros sistemas");
			item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttImportar_Click(e);
				}
			});
			jMenu.add(item);
			item = new JMenuItem("Exportar");
			item.setToolTipText("Exportacion de la Base de Datos");
			item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttExportar_Click(e);
				}
			});
			jMenu.add(item);
			jMenu.addSeparator();
			item = new JMenuItem("Configuraciones");
			item.setToolTipText("Configuraciones del Sistema");
			item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttConfigurar_Click(e);
				}
			});
			jMenu.add(item);
			jMenu.addSeparator();
			item = new JMenuItem("Salir");
			item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.exit(1);
				}
			});
			jMenu.add(item);
                        jMenu = new JMenu("Censos");
                        item = new JMenuItem("Plantacion");
                        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
                        item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttCensoPlantacion_Click(e);
				}
			});
                        jMenu.add(item);
                        item = new JMenuItem("Entrega");
                        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
                        item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttCensoEntrega_Click(e);
				}
			});
                        jMenu.add(item);
                        item = new JMenuItem("Ver Censados");
                        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,0));
                        item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttVerCensados_Click(e);
				}
			});
                        jMenu.add(item);
                        jMenuBar.add(jMenu);
                        jMenu = new JMenu("Datos");
                        item = new JMenuItem("Telefono");
                        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
                        item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttTelefono_Click(e);
				}
			});
                        jMenu.add(item);
                        item = new JMenuItem("Estadisticas");
                        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
                        item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttEstadistica_Click(e);
				}
			});
                        jMenu.add(item);
                        JMenu jMenuTo = new JMenu("Tolerancia");
                        item = new JMenuItem("Por Socio");
                        item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttTolerancia_Click(e);
				}
			});
                        jMenuTo.add(item);
                        item = new JMenuItem("Por Variedad");
                        item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttToleranciaIndividual_Click(e);
				}
			});
                        jMenuTo.add(item);
                        jMenu.add(jMenuTo);
                        jMenuBar.add(jMenu);
                        jMenu = new JMenu("Ayuda");
                        item = new JMenuItem("Manual de Usuario");
                        item.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                //Lanzar Manual de Usuario
                            }
                        });
                        jMenu.add(item);              
                        
                        item = new JMenuItem("Acerca de...");
                        item.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                bttAbout_Click(e);
                            }
                        });
                        jMenu.add(item);
                        jMenuBar.add(jMenu);
                        return jMenuBar;
		}
		
		//Separacion de La ventana en 2 Partes
		private JSplitPane getPnlSociosS(){
			pnlSociosS = new JSplitPane();
			pnlSociosS.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			pnlSociosS.setRightComponent(getPnlAD());
			pnlSociosS.setLeftComponent(getPnlSocios());
			pnlSociosS.setDividerLocation(430);
			ActionMap map = pnlSociosS.getActionMap();
			map.getParent().remove("startResize"); //Para desabilitar el F8 y F6 de JSplitPane 
			return pnlSociosS;
		}
		
		//Inicialisacion del Panel de Socios en donde esta la tabla
		private JPanel getPnlSocios(){
			pnlSocios = new JPanel();
			pnlSocios.setFont(empresa.getOptions().getFontLbl());
			pnlSocios.setBorder(BorderFactory.createTitledBorder("Socios"));
			pnlSocios.setPreferredSize(new Dimension(450,600));
			pnlSocios.setLayout(new GridBagLayout());
			getPrimeraSocio();
			getSegundaSocio();
                        ButtonGroup grupo = new ButtonGroup();
                        grupo.add(chkSocios);
                        grupo.add(chkTerceros);
			pnlSocios.setBackground(empresa.getOptions().getColor_form());
			return pnlSocios;
		}
		
		private void getPrimeraSocio(){
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.anchor = GridBagConstraints.EAST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.0;
			pnlSocios.add(getLblNumeroB(),c);
			c.gridx = 1;
			c.gridy = 0;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.6;
			c.weighty = 0.0;
			pnlSocios.add(getTxtNumeroB(), c);
			c.gridx = 2;
			c.gridy = 0;
			c.anchor = GridBagConstraints.EAST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.0;
			pnlSocios.add(getLblNombreB(),c);
			c.gridx = 3;
			c.gridy = 0;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1;
			c.weighty = 0.0;
			pnlSocios.add(getTxtNombreB(), c);
		}
		
		private void getSegundaSocio(){
                        GridBagConstraints c = new GridBagConstraints();
                        c.gridx = 1;
			c.gridy = 1;
			c.anchor = GridBagConstraints.EAST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.2;
			c.weighty = 0.0;
			pnlSocios.add(getChkSocios(), c);
                        c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.2;
			c.weighty = 0.0;
			pnlSocios.add(getChkTerceros(), c);
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = GridBagConstraints.RELATIVE;
			c.insets = new Insets(2,0,0,0);
			pnlSocios.add(getTblSocios(), c);
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 3;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 0.0;
			c.gridheight = GridBagConstraints.REMAINDER;
			c.gridwidth = GridBagConstraints.REMAINDER;
			pnlSocios.add(getPnlAcciones(), c);
		}
		
		//Inicialisacion del Panel Acciones
		
		private JPanel getPnlAcciones(){
			pnlAcciones = new JPanel();
			pnlAcciones.setLayout(new FlowLayout());
			pnlAcciones.setBackground(empresa.getOptions().getColor_form());
			pnlAcciones.add(getBttCensoPlantacion());
			pnlAcciones.add(getBttCensoEntrega());
                       // pnlAcciones.add(getBttPlanillaEntrega());
                        pnlAcciones.add(getBttTelefono());
			return pnlAcciones;
		}
		
		//Inicialisacion del Panel de la Derecha, lo Separa en 2 partes
		private JPanel getPnlAD(){
			pnlAD = new JPanel();
			pnlAD.setLayout(new BoxLayout(pnlAD, BoxLayout.Y_AXIS));
			pnlAD.setPreferredSize(new Dimension(300,600));
			pnlAD.setBackground(empresa.getOptions().getColor_form());
			pnlAD.add(getPnlSuperiorInformativo());
			pnlAD.add(getPnlInferior());
			return pnlAD;
		}
		
		//Inicialisacion del Panel superior de la 2 partes
		private JPanel getPnlSuperiorInformativo(){
			pnlSuperior = new JPanel();
			pnlSuperior.setBorder(BorderFactory.createTitledBorder(""));
			pnlSuperior.setPreferredSize(new Dimension(300,200));
			pnlSuperior.setLayout(new BoxLayout(pnlSuperior, BoxLayout.Y_AXIS));
			pnlSuperior.setBackground(empresa.getOptions().getColor_form());
			pnlSuperior.add(getJTMovInfo());
			return pnlSuperior;
		}
		
		//Inicialisacion del Panel Inferior de la 2 partes
		private JPanel getPnlInferior(){
			pnlDatos = new JPanel();
			pnlDatos.setBorder(BorderFactory.createTitledBorder(""));
			pnlDatos.setLayout(new BoxLayout(pnlDatos, BoxLayout.Y_AXIS));
			pnlDatos.setBackground(empresa.getOptions().getColor_form());
			pnlDatos.setPreferredSize(new Dimension(300,350));
			pnlDatos.add(getJTabbedPane());
			return pnlDatos;
		}
		
		//JTabbedPane del panel del Superior de la 2 partes
		private JTabbedPane getJTMovInfo(){
			JPanel tbl1 = new JPanel();
			pnlTbMovInfo = new JTabbedPane();
			pnlTbMovInfo.setBackground(empresa.getOptions().getColor_form());
			tbl1.setLayout(new BorderLayout());
			tbl1.add(getTblInformativo(), BorderLayout.CENTER);
			pnlTbMovInfo.add(tbl1);
			pnlTbMovInfo.setTitleAt(0, "Informacion");
			return pnlTbMovInfo;
		}
		
		//Inicialisacion de la tabla Informaciones
		private JScrollPane getTblInformativo(){
			Vector colNames = new Vector(2);
			colNames.addElement("Nro.");
			colNames.addElement("Fecha");
			colNames.addElement("Descripcion");
			Vector rows = new Vector();
			V_Tabla tabla = new V_Tabla(rows, colNames);
			tblInformacion = new JTable(tabla);
			TableRender render = new TableRender();
			tblInformacion.setDefaultRenderer(String.class, render);
			tblInformacion.setSelectionMode(0);
			scrTblInfo = new JScrollPane(tblInformacion);
			tblInformacion.setShowHorizontalLines(false);
			tblInformacion.setBackground(empresa.getOptions().getColorJMenu());
			tblInformacion.getColumnModel().getColumn(0).setPreferredWidth(30);
			tblInformacion.getColumnModel().getColumn(1).setPreferredWidth(70);
			tblInformacion.getColumnModel().getColumn(2).setPreferredWidth(240);
			tblInformacion.addMouseListener(new MouseAdapter(){
				public void mouseReleased(MouseEvent e){tblInformacion(null);}
			});
			tblInformacion.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){KeyPressed(e);}
				public void keyReleased(KeyEvent e){tblInformacion(e);}
			});
			return scrTblInfo;
		}
		
                private void KeyPressed(KeyEvent e){
                    if (e.getKeyCode() == KeyEvent.VK_F2){
                        bttCensoEntrega_Click(null);
                    }
                }
                
		//Trae un Movimiento Informativo al hacerle click en la ventana
		private void tblInformacion(KeyEvent e){
			int nroFila = tblInformacion.getSelectedRow();
			long nro = (Long)tblInformacion.getValueAt(nroFila,0);
			this.movInfor = unSocio.getInformacion(nro);
			
		}
		
		//Inicilisacion del TabbedPane del panel de abajo
		private JTabbedPane getJTabbedPane(){
			tpDatos = new JTabbedPane();
			tpDatos.setPreferredSize(new Dimension(300,400));
			tpDatos.add(getPnlInformacion());
			tpDatos.setTitleAt(0, "Datos");
                        tpDatos.add(getPnlEntrega());
                        tpDatos.setTitleAt(1, "Entregas");
			return tpDatos;
		}
		
		//Panel ubicado en la parte inferior con la informacion de los socios. Dentro del Panel de abajo
		private JPanel getPnlInformacion(){
			pnlInformacion = new JPanel();
			pnlInformacion.setBackground(empresa.getOptions().getColorJMenu());
			pnlInformacion.setLayout(new GridBagLayout());
			getPrimeraInfo();
			getSegundaInfo();
			getTerceraInfo();
			getCuartaInfo();
			return pnlInformacion;
		}
                
                 private JPanel getPnlEntrega(){
			pnlEntrega = new JPanel();
			pnlEntrega.setBackground(empresa.getOptions().getColor_form());
			pnlEntrega.setLayout(new BoxLayout(pnlEntrega, BoxLayout.Y_AXIS));
			pnlEntrega.add(getTblEntrega());
			pnlEntrega.add(getPnlListadoEntrega());
			return pnlEntrega;
			
		}
                 
                 private JScrollPane getTblEntrega(){
			Vector colNames = new Vector(6);
			colNames.addElement("Fecha");
			colNames.addElement("Temp.");
			colNames.addElement("Variedad");
			colNames.addElement("Kg");
                        colNames.addElement("Rinde");
                        colNames.addElement("Ciclo");
			Vector rows = new Vector();
			V_Tabla tabla = new V_Tabla(rows, colNames);
			tblEntrega = new JTable(tabla);
			TableRender render = new TableRender();
			tblEntrega.setDefaultRenderer(String.class, render);
			tblEntrega.setSelectionMode(0);
			scrTblEnt = new JScrollPane(tblEntrega);
			tblEntrega.setPreferredScrollableViewportSize(new Dimension(500, 70));
			tblEntrega.setShowHorizontalLines(false);
			tblEntrega.setBackground(empresa.getOptions().getColorJMenu());
			tblEntrega.getColumnModel().getColumn(0).setPreferredWidth(30);
			tblEntrega.getColumnModel().getColumn(1).setPreferredWidth(5);
			tblEntrega.getColumnModel().getColumn(2).setPreferredWidth(40);
			tblEntrega.getColumnModel().getColumn(3).setPreferredWidth(15);
                        tblEntrega.getColumnModel().getColumn(4).setPreferredWidth(5);
                        tblEntrega.getColumnModel().getColumn(5).setPreferredWidth(5);
			
			return scrTblEnt;
		}
		
		private void getPrimeraInfo(){
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = 1;
			c.gridheight = 1;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblNumeroI(), c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = 1;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblNumeroIR(), c);
			c.gridwidth = 1;
			c.gridheight = 2;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblNombreI(), c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = 2;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblNombreIR(), c);
			c.gridwidth = 1;
			c.gridheight = 3;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblTipoDocI(), c);
			c.gridwidth = 2;
			c.gridheight = 3;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getRdDniI(), c);
		}
		
		private void getSegundaInfo(){
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = 3;
			c.gridheight = 3;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getRdLeI(), c);
			c.gridwidth = 4;
			c.gridheight = 3;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getRdLcI(), c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = 3;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getRdCiI(), c);
			c.gridwidth = 1;
			c.gridheight = 4;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblNroDocumentoI(), c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = 4;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblNroDocumentoIR(), c);
			c.gridwidth = 1;
			c.gridheight = 5;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblIvaI(), c);
			c.gridwidth = 2;
			c.gridheight = 5;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblRiI(), c);
		}
		
		private void getTerceraInfo(){
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = 3;
			c.gridheight = 5;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblRniI(), c);
			c.gridwidth = 4;
			c.gridheight = 5;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblExeI(), c);
			c.gridwidth = GridBagConstraints.RELATIVE;
			c.gridheight = 5;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblMoI(), c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = 5;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblCfI(), c);
			c.gridwidth = 1;
			c.gridheight = 6;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblCuitI(), c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = 6;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblCuitIR(), c);
			c.gridwidth = 1;
			c.gridheight = 7;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblDomicilioI(), c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = 7;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.9;
			c.weighty = 0.1;
			pnlInformacion.add(getLblDomicilioIR(), c);
		}
		
		private void getCuartaInfo(){
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = 1;
			c.gridheight = 8;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblFechaIngresoI(), c);
			c.gridwidth = 2;
			c.gridheight = 8;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblFechaIngresoIR(), c);
			c.gridwidth = GridBagConstraints.RELATIVE;
			c.gridheight = 8;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblActaIngresoI(), c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridheight = 8;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.1;
			c.weighty = 0.1;
			pnlInformacion.add(getLblActaIngresoIR(), c);
		}
		
					
		private JLabel getLblNumeroB(){
			lblNumeroB = new JLabel("Numero  ");
			lblNumeroB.setFont(empresa.getOptions().getFontLbl());
			return lblNumeroB;
		}
		
		private JTextField getTxtNumeroB(){
			txtNumeroB = new JTextField();
			txtNumeroB.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){}
				public void keyReleased(KeyEvent e){txtNumeroB_Change(e);}
			});
			return txtNumeroB;
		}
		
		private JLabel getLblNombreB(){
			lblNombreB = new JLabel("Nombre  ");
			lblNombreB.setFont(empresa.getOptions().getFontLbl());
			return lblNombreB;
			
		}
		
		private JTextField getTxtNombreB(){
			txtNombreB = new JTextField();
			txtNombreB.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){}
				public void keyReleased(KeyEvent e){txtNombreB_Change(e);}
			});
			return txtNombreB;
		}
		
		//Inicialisacion Tabla socios
		@SuppressWarnings("unchecked")
		private JScrollPane getTblSocios(){
			Map sorted;
			Collection co; Socio socio;
			Vector vecCol = new Vector(5);
			Vector vecRows  = new Vector();
			vecCol.addElement("Numero");
			vecCol.addElement("Nombre y Apellido");
			vecCol.addElement("Domicilio");
			sorted = new TreeMap(empresa.getMapSocios());
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
			co = sorted.values();
			Iterator it = co.iterator();
			while (it.hasNext()){
				socio = (Socio)it.next();
				Vector row = new Vector();
				if(socio.getEstado().equals("activo")){
					row.addElement(new Long(socio.getNroSocio()));
					row.addElement(new String(socio.getNomApe()));
					row.addElement(new String(socio.getDomicilio()));
					vecRows.addElement(row);
				}
			}
			V_Tabla tabla = new V_Tabla(vecRows, vecCol);
			tblSocios = new JTable(tabla);
			TableRender render = new TableRender();
			tblSocios.setDefaultRenderer(String.class, render);
			tblSocios.setSelectionMode(0);
			tblSocios.setBackground(empresa.getOptions().getColorJMenu());
			JScrollPane scroll = new JScrollPane(tblSocios);
			tblSocios.getColumnModel().getColumn(0).setPreferredWidth(20);
			tblSocios.getColumnModel().getColumn(1).setPreferredWidth(120);
			tblSocios.getColumnModel().getColumn(2).setPreferredWidth(180);
			tblSocios.addMouseListener(new MouseAdapter(){
				public void mouseReleased(MouseEvent e){tblSocio(null);}
			
			});
			tblSocios.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){KeyPressed(e);}
				public void keyReleased(KeyEvent e){tblSocio(e);}
			});
			return scroll;
		}
                
              
		
		private JButton getBttCensoPlantacion(){
			ImageIcon img = new ImageIcon("img/pala.gif");
			bttCensoPlantacion = new JButton("Plantacion", img);
			bttCensoPlantacion.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttCensoPlantacion_Click(e);
				}
			});
			return bttCensoPlantacion;
		}
		
		private JButton getBttCensoEntrega(){
			ImageIcon img = new ImageIcon("img/tractor.gif");
			bttCensoEntrega = new JButton("Entrega", img);
			bttCensoEntrega.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttCensoEntrega_Click(e);
				}
			});
			return bttCensoEntrega;
		}
                
                private JButton getBttPlanillaEntrega(){
			//ImageIcon img = new ImageIcon("img/tractor.gif");
			bttPlanillaEntrega = new JButton("Plan. Entrega"/*,img*/);
			bttPlanillaEntrega.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttPlanillaEntrega_Click(e);
				}
			});
			return bttPlanillaEntrega;
		}
                
                private JButton getBttTelefono(){
                    ImageIcon img = new ImageIcon("img/telefono.png");
                    bttTelefono = new JButton("Telefono", img);
                    bttTelefono.setEnabled(true);
                    bttTelefono.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            bttTelefono_Click(e);
                        }
                    });
                    return bttTelefono;
                }
		
		
		//Evento de click en la tabla socio
		private void tblSocio(KeyEvent e){
			if (this.chkSocios.isSelected()){
                            try{
                                if (tblSocios.getSelectedRowCount() > 0){
                                    int nroFila; long nroSocio;
                                    nroFila = tblSocios.getSelectedRow();
                                    nroSocio = (Long)(tblSocios.getValueAt(nroFila,0));
                                    unSocio = empresa.getSocio(nroSocio);
                                    llenarInfo();
                                    llenarMovInformacion(unSocio);
                                    llenarEntregas(unSocio);
                                    this.movInfor = null;
                                    this.unTercero=null;
                                }
                            }catch(Exception ex){
                                    JOptionPane.showMessageDialog(this,"Error en tblSocios " + ex , "Error", JOptionPane.ERROR_MESSAGE);
                            }
                      }else{
                          if (tblSocios.getSelectedRowCount() > 0){
                                    int nroFila; long nroSocio;
                                    nroFila = tblSocios.getSelectedRow();
                                    nroSocio = (Long)(tblSocios.getValueAt(nroFila,0));
                                    this.unTercero = empresa.getTercero(new Long(nroSocio).intValue());
                                    this.lblNumeroIR.setText(new Integer(unTercero.getId()).toString());
                                    this.lblNombreIR.setText(unTercero.getNombre());
                                    this.lblDomicilioIR.setText(unTercero.getDireccion());
                                    this.lblCuitIR.setText("");
                                    this.lblNroDocumentoIR.setText("");
                                    this.lblFechaIngresoIR.setText("");
                                    this.lblActaIngresoIR.setText("");
                                    ((DefaultTableModel)this.tblInformacion.getModel()).setNumRows(0);
                                    ((DefaultTableModel)this.tblEntrega.getModel()).setNumRows(0);
                                    this.unSocio= null;
                                    
                                }  
                      }
                }
		
		private JLabel getLblNumeroI(){
			lblNumeroI = new JLabel("Numero:");
			lblNumeroI.setFont(empresa.getOptions().getFontLbl());
			return lblNumeroI;
		}
		
		private JLabel getLblNumeroIR(){
			lblNumeroIR = new JLabel("");
			lblNumeroIR.setFont(empresa.getOptions().getFontLblAlt1());
			return lblNumeroIR;
		}
		
		private JLabel getLblNombreI(){
			lblNombreI = new JLabel("Nombre:");
			lblNombreI.setFont(empresa.getOptions().getFontLbl());
			return lblNombreI;
		}
		
		private JLabel getLblNombreIR(){
			lblNombreIR = new JLabel("");
			lblNombreIR.setFont(empresa.getOptions().getFontLblAlt1());
			return lblNombreIR;
		}
		
		private JLabel getLblTipoDocI(){
			lblTipoDocI = new JLabel("Tipo Documento:");
			lblTipoDocI.setFont(empresa.getOptions().getFontLbl());
			return lblTipoDocI;
		}
		
		private JRadioButton getRdDniI(){
			rdDniI = new JRadioButton("DNI");
			rdDniI.setBackground(empresa.getOptions().getColorJMenu());
			rdDniI.setEnabled(false);
			rdDniI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdDniI;
		}
		
		private JRadioButton getRdLeI(){
			rdLeI = new JRadioButton("LE");
			rdLeI.setBackground(empresa.getOptions().getColorJMenu());
			rdLeI.setEnabled(false);
			rdLeI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdLeI;
		}
		
		private JRadioButton getRdLcI(){
			rdLcI = new JRadioButton("LC");
			rdLcI.setBackground(empresa.getOptions().getColorJMenu());
			rdLcI.setEnabled(false);
			rdLcI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdLcI;
		}
		
		private JRadioButton getRdCiI(){
			rdCiI = new JRadioButton("CI");
			rdCiI.setBackground(empresa.getOptions().getColorJMenu());
			rdCiI.setEnabled(false);
			rdCiI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdCiI;
		}
		
		private JLabel getLblNroDocumentoI(){
			lblNroDocumentoI = new JLabel("Numero Doc:");
			lblNroDocumentoI.setFont(empresa.getOptions().getFontLbl());
			return lblNroDocumentoI;
		}
		
		private JLabel getLblNroDocumentoIR(){
			lblNroDocumentoIR = new JLabel("");
			lblNroDocumentoIR.setFont(empresa.getOptions().getFontLblAlt1());
			return lblNroDocumentoIR;
		}
		
		private JLabel getLblIvaI(){
			lblIvaI = new JLabel("Iva:");
			lblIvaI.setFont(empresa.getOptions().getFontLbl());
			return lblIvaI;
		}
		
		private JRadioButton getLblRiI(){
			rdRiI = new JRadioButton("RI");
			rdRiI.setBackground(empresa.getOptions().getColorJMenu());
			rdRiI.setEnabled(false);
			rdRiI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdRiI;
		}
		
		private JRadioButton getLblRniI(){
			rdRniI = new JRadioButton("RNI");
			rdRniI.setBackground(empresa.getOptions().getColorJMenu());
			rdRniI.setEnabled(false);
			rdRniI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdRniI;
		}
		
		private JRadioButton getLblExeI(){
			rdExeI = new JRadioButton("EXE");
			rdExeI.setBackground(empresa.getOptions().getColorJMenu());
			rdExeI.setEnabled(false);
			rdExeI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdExeI;
		}
		
		private JRadioButton getLblMoI(){
			rdMoI = new JRadioButton("MO");
			rdMoI.setBackground(empresa.getOptions().getColorJMenu());
			rdMoI.setEnabled(false);
			rdMoI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdMoI;
		}
		
		private JRadioButton getLblCfI(){
			rdCfI = new JRadioButton("CF");
			rdCfI.setBackground(empresa.getOptions().getColorJMenu());
			rdCfI.setEnabled(false);
			rdCfI.setFont(empresa.getOptions().getFontLblAlt1());
			return rdCfI;
		}
		
		private JLabel getLblCuitI(){
			lblCuitI = new JLabel("Cuit:");
			lblCuitI.setFont(empresa.getOptions().getFontLbl());
			return lblCuitI;
		}
			
		private JLabel getLblCuitIR(){
			lblCuitIR = new JLabel("");
			lblCuitIR.setFont(empresa.getOptions().getFontLblAlt1());
			return lblCuitIR;
		}
		
		private JLabel getLblDomicilioI(){
			lblDomicilioI = new JLabel("Domicilio:");
			lblDomicilioI.setFont(empresa.getOptions().getFontLbl());
			return lblDomicilioI;
		}
			
		private JLabel getLblDomicilioIR(){
			lblDomicilioIR = new JLabel("");
			lblDomicilioIR.setFont(empresa.getOptions().getFontLblAlt1());
			return lblDomicilioIR;
		}
		
		private JLabel getLblFechaIngresoI(){
			lblFechaIngresoI = new JLabel("Fecha Ingreso:");
			lblFechaIngresoI.setFont(empresa.getOptions().getFontLbl());
			return lblFechaIngresoI;
		}
		
		private JLabel getLblFechaIngresoIR(){
			lblFechaIngresoIR = new JLabel("");
			lblFechaIngresoIR.setFont(empresa.getOptions().getFontLblAlt1());
			return lblFechaIngresoIR;
		}
			
		private JLabel getLblActaIngresoI(){
			lblActaIngresoI = new JLabel("Acta Ingreso:");
			lblActaIngresoI.setFont(empresa.getOptions().getFontLbl());
			return lblActaIngresoI;
		}
		
		private JLabel getLblActaIngresoIR(){
			lblActaIngresoIR = new JLabel("");
			lblActaIngresoIR.setFont(empresa.getOptions().getFontLblAlt1());
			return lblActaIngresoIR;
		}
		
		//llena los datos personales del socio
		public void llenarInfo(){
			lblNumeroIR.setText(new Long(unSocio.getNroSocio()).toString());
			lblNombreIR.setText(unSocio.getNomApe());
			lblDomicilioIR.setText(unSocio.getDomicilio());
			lblNroDocumentoIR.setText(unSocio.getNroIde());
			lblActaIngresoIR.setText(unSocio.getActaIngreso());
			lblFechaIngresoIR.setText(unSocio.getFechaIngreso().getDMA());
			lblCuitIR.setText(unSocio.getCuit());
			if(unSocio.getTipoDoc().equals("DNI")){ rdDniI.setSelected(true);}else{ rdDniI.setSelected(false);}
			if(unSocio.getTipoDoc().equals("LE")){ rdLeI.setSelected(true);}else{ rdLeI.setSelected(false);}
			if(unSocio.getTipoDoc().equals("LC")){ rdLcI.setSelected(true);}else{ rdLcI.setSelected(false);}
			if(unSocio.getTipoDoc().equals("CI")){ rdCiI.setSelected(true);}else{ rdCiI.setSelected(false);}
			if(unSocio.getTipoIva().equals("RI")){ rdRiI.setSelected(true);}else{ rdRiI.setSelected(false);}
			if(unSocio.getTipoIva().equals("RNI")){ rdRniI.setSelected(true);}else{ rdRniI.setSelected(false);}
			if(unSocio.getTipoIva().equals("EXE")){ rdExeI.setSelected(true);}else{ rdExeI.setSelected(false);}
			if(unSocio.getTipoIva().equals("MO")){ rdMoI.setSelected(true);}else{ rdMoI.setSelected(false);}
			if(unSocio.getTipoIva().equals("CF")){ rdCfI.setSelected(true);}else{ rdCfI.setSelected(false);}
		}
		
		//Evento producido de cambion en el JTextField del Buscar Numero		
		@SuppressWarnings("unchecked")
		private void txtNumeroB_Change(KeyEvent e){
			Map sorted; Collection co; Iterator it; Socio so; String nroSocio;
			txtNombreB.setText("");
			sorted = new TreeMap(empresa.getMapSocios());
			co = sorted.values();
			it = co.iterator();
			((DefaultTableModel)tblSocios.getModel()).setNumRows(0);
			while (it.hasNext()){
				so = (Socio)it.next();
				nroSocio = new Long(so.getNroSocio()).toString();
				if (nroSocio.contains(txtNumeroB.getText())){
					if (so.getEstado().equals("activo")){
						((DefaultTableModel)tblSocios.getModel()).addRow(getTblVectorSo(so));
					}
				}
			}
		}
		
		//Evento producido de cambion en el JTextField del Buscar Nombre
		@SuppressWarnings("unchecked")
		private void txtNombreB_Change(KeyEvent e){
			Map sorted; Collection co; Iterator it; Socio so; String nombreSocio;
			sorted = new TreeMap(empresa.getMapSocios()); String nombreSoAux;
			txtNumeroB.setText("");
			co = sorted.values();
			it = co.iterator();
			((DefaultTableModel)tblSocios.getModel()).setNumRows(0);
			while (it.hasNext()){
				so = (Socio)it.next();
				nombreSocio = so.getNomApe().toUpperCase();
                                nombreSoAux = txtNombreB.getText().toUpperCase();
				if (nombreSocio.contains(nombreSoAux)){
					if (so.getEstado().equals("activo")){
						((DefaultTableModel)tblSocios.getModel()).addRow(getTblVectorSo(so));
					}
				}
			}
		}
		
		/**
		 * Actualiza la tabla de Socios
		 * @param Socio
		 * @return Vector
		 */
		@SuppressWarnings("unchecked")
		private Vector getTblVectorSo(Socio so){
			Vector row = new Vector();
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
			DecimalFormat decimal = new DecimalFormat();
			row.addElement(new Long(so.getNroSocio()));
			row.addElement(new String(so.getNomApe()).toString());
			row.addElement(new String(so.getDomicilio()));
			return row;
		}
                
                /**
		 * Actualiza la tabla de Socios
		 * @param Tercero
		 * @return Vector
		 */
		@SuppressWarnings("unchecked")
		private Vector getTblVectorSo(Tercero ter){
			Vector row = new Vector();
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
			DecimalFormat decimal = new DecimalFormat();
			row.addElement(new Long(ter.getId()));
			row.addElement(new String(ter.getNombre()).toString());
			row.addElement(new String(ter.getDireccion()).toString());
			return row;
		}
                
                /**
                 * Metodo del Panel del Listado de las Entregas
                 * @return JPanel
                 */
                private JPanel getPnlListadoEntrega(){
			pnlListadoEntrega = new JPanel();
			pnlListadoEntrega.setLayout(new BoxLayout(pnlListadoEntrega, BoxLayout.X_AXIS));
			pnlListadoEntrega.add(Box.createHorizontalGlue());
			pnlListadoEntrega.add(Box.createHorizontalGlue());
			pnlListadoEntrega.add(getBttListadoEntrega());
			pnlListadoEntrega.setBackground(empresa.getOptions().getColorJMenu());
			return pnlListadoEntrega;
		}

		
		/**
		 * Actualiza la tabla de informaciones
		 * @param Socio
		 */
		public void llenarMovInformacion(Socio so){
			Map sorted; Collection co; Iterator it; MovInformativo mo;
			sorted = new TreeMap(so.getAllInformaciones()); Vector row;
			co = sorted.values();
			it = co.iterator();
			((DefaultTableModel)tblInformacion.getModel()).setNumRows(0);
			while (it.hasNext()){
				mo = (MovInformativo)it.next();
				row = new Vector();
				row.addElement(mo.getNumero());
				row.addElement(mo.getFecha().getDMA());
				row.addElement(mo.getDescripcion());
				((DefaultTableModel)tblInformacion.getModel()).addRow(row);
			}
		}
                
                /**
		 * Actualiza la tabla de Entrega
		 * @param unSocio Socio
		 */
		public void llenarEntregas(Socio unSocio){
                    Entrega entrega; Vector row;
                    Iterator it = new TreeMap(unSocio.getAllEntregas()).values().iterator();
                    DecimalFormat format = new DecimalFormat();
                    ((DefaultTableModel)tblEntrega.getModel()).setNumRows(0);
                    while (it.hasNext()){
                    	entrega = (Entrega)it.next();
                    	row = new Vector();
                    	row.addElement(entrega.getFecha().getDMA());
                    	row.addElement(entrega.getTemporada());
			row.addElement(entrega.getVariedad());
                        row.addElement(String.valueOf(entrega.getKg()));
                        row.addElement(format.format(entrega.getRinde()));
                        row.addElement(String.valueOf(entrega.getCiclo()));
			((DefaultTableModel)tblEntrega.getModel()).addRow(row);
                    }
		}
		
		
		
		private void bttImportar_Click(ActionEvent e){
			V_Importar vImpo = new V_Importar(empresa, this);
			vImpo.setVisible(true);
		}
		
		private void bttCensoPlantacion_Click(ActionEvent e){
                    if (unSocio != null){
			censoPlantacion = new V_CensoPlantacion(empresa, unSocio, this);
                        censoPlantacion.setVisible(true);
                   }else{
                            JOptionPane.showMessageDialog(this, "Por Favor Seleccione un Socio de la tabla", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
		}
		
		private void bttConfigurar_Click(ActionEvent e){
			V_Configuracion v_conf = new V_Configuracion(this.empresa);
			v_conf.setVisible(true);
		}
		
		private void bttExportar_Click(ActionEvent e){
			int resultado; File f; JFileChooser chooser;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("C:/"));
			chooser.setApproveButtonText("Guardar");
			chooser.setDialogTitle("Exportar");
			chooser.setSelectedFile(new File("Exportacion-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +
					                 "-" + Calendar.getInstance().get(Calendar.MONTH) + "-" + Calendar.getInstance().get(Calendar.YEAR) + ".mdb"));
			resultado = chooser.showOpenDialog(this);
			if (resultado == JFileChooser.APPROVE_OPTION){
				try{
                                    FileInputStream fis = new FileInputStream("db/db.mdb"); 
                                    FileOutputStream fos = new FileOutputStream(chooser.getSelectedFile());
                                    FileChannel canalFuente = fis.getChannel(); 
				    FileChannel canalDestino = fos.getChannel(); 
				    canalFuente.transferTo(0, canalFuente.size(), canalDestino); 
				    fis.close(); 
				    fos.close();
				 }catch (Exception ex) {
					 JOptionPane.showMessageDialog(this, "Error Exportando la Base de Datos: " + ex, "Mensaje", JOptionPane.ERROR_MESSAGE);
				 }
			}
		}
                
                private void bttEstadistica_Click(ActionEvent e){
                    V_Estadistica v_esta = new V_Estadistica(this.empresa);
                    v_esta.setVisible(true);
                }
                
                private void bttTolerancia_Click(ActionEvent e){
                    V_Tolerancia v_Tole = new V_Tolerancia(this.empresa, this);
                    v_Tole.setVisible(true);
                }
                
                private void bttToleranciaIndividual_Click(ActionEvent e){
                    V_ToleranciaIndividual v_ToleIndi = new V_ToleranciaIndividual(this.empresa, this);
                    v_ToleIndi.setVisible(true);
                }
                
                private void bttTelefono_Click(ActionEvent e){
                    if (this.unSocio != null){
                        V_Telefono v_tel = new V_Telefono(this.empresa, this.unSocio, this);
                        v_tel.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(this, "Por Favor Seleccione un Socio de la tabla", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		    }
                }
                
                private void bttCensoEntrega_Click(ActionEvent e){
                   if (unSocio != null){
			if (censoEntrega == null){
                            censoEntrega = new V_CensoEntrega(empresa, unSocio, this);
                            censoEntrega.setVisible(true);
			}else{
                            censoEntrega.setVisible(false);
                            censoEntrega = new V_CensoEntrega(empresa, unSocio, this);
                            censoEntrega.setVisible(true);
			}
                         
                    }else{
                            JOptionPane.showMessageDialog(this, "Por Favor Seleccione un Socio de la tabla", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
		}
                
                private void bttPlanillaEntrega_Click(ActionEvent e){
                   if (unSocio != null){
			if (planillaEntrega == null){
                            planillaEntrega = new V_PlanillaEntrega(empresa, unSocio, this);
                            planillaEntrega.setVisible(true);
			}else{
                            planillaEntrega.setVisible(false);
                            planillaEntrega = new V_PlanillaEntrega(empresa, unSocio, this);
                            planillaEntrega.setVisible(true);
			}
                    } else if (this.unTercero != null){
                        if (planillaEntregaTer == null){
                            planillaEntregaTer = new V_PlanillaEntregaTer(empresa, unTercero, this);
                            planillaEntregaTer.setVisible(true);
			}else{
                            planillaEntregaTer.setVisible(false);
                            planillaEntregaTer = new V_PlanillaEntregaTer(empresa, unTercero, this);
                            planillaEntregaTer.setVisible(true);
			}
                    }else{
                            JOptionPane.showMessageDialog(this, "Por Favor Seleccione un Socio/Tercero de la tabla", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
		}
                
                private void bttVerCensados_Click(ActionEvent e){
                    if (verCensado == null){
                        verCensado = new V_VerCensado(this.empresa, this);
                        verCensado.setVisible(true);
                    }else{
                        verCensado.setVisible(false);
                        verCensado = new V_VerCensado(this.empresa, this);
                        verCensado.setVisible(true);
                    }
                }
		
                private void bttAbout_Click(ActionEvent e){
                    V_About v_about = new V_About(this.empresa);
                    v_about.setVisible(true);
                }
                
                 private JButton getBttListadoEntrega(){
			bttListadoEntrega = new JButton(new ImageIcon("img/listado.gif"));
			bttListadoEntrega.setToolTipText("Listado");
			bttListadoEntrega.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttListadoEntrega_Click(e);
				}
			});
			return bttListadoEntrega;
		}
                 
                private void bttListadoEntrega_Click(ActionEvent e){
                    ReporteEntregas repEntregas = new ReporteEntregas(this.empresa, "Reporte Entregas", this.unSocio);
                    repEntregas.run();
                }
                
                private JRadioButton getChkSocios(){
			chkSocios = new JRadioButton("Socios");
			chkSocios.setSelected(true);
			chkSocios.setBackground(empresa.getOptions().getColor_form());
			chkSocios.setFont(empresa.getOptions().getFontLbl());
			chkSocios.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					chkSocios_Click(e);
				}
			});
			return chkSocios;
		}
		
		private JRadioButton getChkTerceros(){
			chkTerceros = new JRadioButton("Terceros");
			chkTerceros.setBackground(empresa.getOptions().getColor_form());
			chkTerceros.setFont(empresa.getOptions().getFontLbl());
			chkTerceros.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					chkTerceros_Click(e);
				}
			});
			
			return chkTerceros;
		}
                
                public void chkSocios_Click(ActionEvent e)
                {
                    this.bttCensoPlantacion.setEnabled(true);
                    this.bttCensoEntrega.setEnabled(true);
                    
                    Iterator it; Socio so;
                    it = new TreeMap(empresa.getMapSocios()).values().iterator();
                    ((DefaultTableModel)tblSocios.getModel()).setNumRows(0);
                    while (it.hasNext()){
                            so = (Socio)it.next();
                            if (txtNumeroB.getText().length() != 0){
                                if((so.getEstado().equals("activo")) &&
                                        (String.valueOf(so.getNroSocio()).contains(txtNumeroB.getText()))){
                                        ((DefaultTableModel)tblSocios.getModel()).addRow(getTblVectorSo(so));
                                }
                            }else if (txtNombreB.getText().length() != 0){
                                String nombre = so.getNomApe().toLowerCase();
                                if((so.getEstado().equals("activo")) &&
                                    (nombre.contains(txtNombreB.getText().toLowerCase()))){
                                    ((DefaultTableModel)tblSocios.getModel()).addRow(getTblVectorSo(so));
                                }
                            }else{
                                if(so.getEstado().equals("activo")){
                                    ((DefaultTableModel)tblSocios.getModel()).addRow(getTblVectorSo(so));
                                }
                            }
                    }
		
                }
                
                public void chkTerceros_Click(ActionEvent e)
                {
                    this.bttCensoPlantacion.setEnabled(false);
                    this.bttCensoEntrega.setEnabled(false);
                    
                    Iterator it; Tercero ter;
                    it = new TreeMap(empresa.getMapTerceros()).values().iterator();
                    ((DefaultTableModel)tblSocios.getModel()).setNumRows(0);
                    while (it.hasNext()){
				ter = (Tercero)it.next();
				 if (txtNumeroB.getText().length() != 0){
                                    if((String.valueOf(ter.getId()).contains(txtNumeroB.getText()))){
                                            ((DefaultTableModel)tblSocios.getModel()).addRow(getTblVectorSo(ter));
                                    }
                                }else if (txtNombreB.getText().length() != 0){
                                    String nombre = ter.getNombre().toLowerCase();
                                    if((nombre.contains(txtNombreB.getText().toLowerCase()))){
					((DefaultTableModel)tblSocios.getModel()).addRow(getTblVectorSo(ter));
                                    }
                                }else{
                                    ((DefaultTableModel)tblSocios.getModel()).addRow(getTblVectorSo(ter));
                                    
                                }
			}
			
                }
		
                
		
		
}
		
		
		
		
		

	