package Gui;

import base.Ciudad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import base.Empresa;
import base.Variedad;
import db.DbConfiguraciones;
import db.DbEmpresa;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import tools.MdbFilterBascula;


/**
 * @author Fernando A. Witzke
 *  23/11/2007
 *  Clase JFrame encargada de la Ventana para dar las configuraciones
 */
public class V_Configuracion extends JFrame {
	
	private JPanel jContentPane;
	private JPanel pnlGeneral;
	private JPanel pnlBotones;
	private JButton bttAgregar;
	private JLabel lblVariedad;
	private JComboBox cmbVariedad;
	private JButton bttConfVariedad;
        private JLabel lblCiudad;
        private JComboBox cmbCiudad;
	private JButton bttConfCiudad;
        private JLabel lblBascula;
        private JTextField txtBascula;
        private JButton bttBascula;
        private JLabel lblEmpresa;
        private JTextField txtEmpresa;
        private JButton bttModEmpresa;
        private JLabel lblTelefono;
        private JTextField txtTelefono;
        private JButton bttModTelefono;
        private JLabel lblLocalidad;
        private JTextField txtLocalidad;
        private JButton bttModLocalidad;
        private JLabel lblDireccion;
        private JTextField txtDireccion;
        private JButton bttModDireccion;
        private V_Variedad v_var;
        private V_Ciudad v_ci;
	private Empresa empresa;
        private DbEmpresa dbEmp = new DbEmpresa(Empresa.con);
	
	
	
		public V_Configuracion(Empresa emp){
			super();
			this.empresa = emp;
			initialize();
			
		}
		
		private void initialize(){
			this.setTitle("Configuraciones");
			this.setSize(380,500);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setIconImage(this.empresa.getOptions().getIcono().getImage());
			this.setContentPane(getJContentPane());
		}
		
		private JPanel getJContentPane(){
			jContentPane = new JPanel();
			jContentPane.setBackground(empresa.getOptions().getColor_form());
			jContentPane.setLayout(null);
			jContentPane.add(getPnlGeneral());
			jContentPane.add(getPnlBotones());
                        return jContentPane;
		}
		
		private JPanel getPnlGeneral(){
			pnlGeneral = new JPanel();
			pnlGeneral.setBorder(BorderFactory.createTitledBorder("Opciones Generales"));
			pnlGeneral.setLocation(0, 0);
			pnlGeneral.setSize(373,400);
			pnlGeneral.setBackground(empresa.getOptions().getColor_form());
			pnlGeneral.setLayout(null);
			pnlGeneral.add(getLblVariedad());
			pnlGeneral.add(getCmbVariedad());
			pnlGeneral.add(getBttConfVariedad());
			pnlGeneral.add(getLblCiudad());
			pnlGeneral.add(getCmbCiudad());
			pnlGeneral.add(getBttConfCiudad());
                        pnlGeneral.add(getLblBascula());
                        pnlGeneral.add(getTxtBascula());
                        pnlGeneral.add(getBttBascula());
                        pnlGeneral.add(getLblEmpresa());
                        pnlGeneral.add(getTxtEmpresa());
                        pnlGeneral.add(getBttModEmpresa());
                        pnlGeneral.add(getLblTelefono());
                        pnlGeneral.add(getTxtTelefono());
                        pnlGeneral.add(getBttModTelefono());
                        pnlGeneral.add(getLblLocalidad());
                        pnlGeneral.add(getTxtLocalidad());
                        pnlGeneral.add(getBttModLocalidad());
                        pnlGeneral.add(getLblDireccion());
                        pnlGeneral.add(getTxtDireccion());
                        pnlGeneral.add(getBttModDireccion());
			return pnlGeneral;
		}
		
		private JPanel getPnlBotones(){
			pnlBotones = new JPanel();
			pnlBotones.setBorder(BorderFactory.createTitledBorder(""));
			pnlBotones.setLocation(0,403);
			pnlBotones.setSize(373,67);
			pnlBotones.setBackground(empresa.getOptions().getColor_form());
			pnlBotones.add(getBttAgregar());
			return pnlBotones;
		}
		
			
		private JLabel getLblVariedad(){
			lblVariedad = new JLabel("Variedades");
			lblVariedad.setFont(empresa.getOptions().getFontLbl());
			lblVariedad.setLocation(20, 30);
			lblVariedad.setSize(100, 20);
			return lblVariedad;
		}
		
		private JComboBox getCmbVariedad(){
			cmbVariedad = new JComboBox(empresa.getConf().getAllVariedades().values().toArray());
			cmbVariedad.setLocation(115,30);
			cmbVariedad.setSize(130, 20);
			cmbVariedad.setFont(empresa.getOptions().getFontLbl());
			return cmbVariedad;
		}
		
		private JButton getBttConfVariedad(){
			bttConfVariedad = new JButton("Configurar");
			bttConfVariedad.setLocation(255, 30);
			bttConfVariedad.setSize(100, 20);
			bttConfVariedad.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttConfVariedad_click(e);
				}
			});
			return bttConfVariedad;
		}
                
                private JLabel getLblCiudad(){
			lblCiudad = new JLabel("Ciudades");
			lblCiudad.setFont(empresa.getOptions().getFontLbl());
			lblCiudad.setLocation(20, 60);
			lblCiudad.setSize(100, 20);
			return lblCiudad;
		}
		
		private JComboBox getCmbCiudad(){
			cmbCiudad = new JComboBox(empresa.getConf().getAllCiudades().values().toArray());
			cmbCiudad.setLocation(115,60);
			cmbCiudad.setSize(130, 20);
			cmbCiudad.setFont(empresa.getOptions().getFontLbl());
			return cmbCiudad;
		}
		
		private JButton getBttConfCiudad(){
			bttConfCiudad = new JButton("Configurar");
			bttConfCiudad.setLocation(255, 60);
			bttConfCiudad.setSize(100, 20);
			bttConfCiudad.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttConfCiudad_click(e);
				}
			});
			return bttConfCiudad;
		}
		
                 private JLabel getLblBascula(){
			lblBascula = new JLabel("Base Bascula");
			lblBascula.setFont(empresa.getOptions().getFontLbl());
			lblBascula.setLocation(20, 90);
			lblBascula.setSize(100, 20);
			return lblBascula;
		}
                 
                private JTextField getTxtBascula(){
			txtBascula = new JTextField(this.empresa.getConf().getBascula());
			txtBascula.setLocation(115,90);
			txtBascula.setSize(130, 20);
			return txtBascula;
		}
                
                private JButton getBttBascula(){
			bttBascula = new JButton("Buscar");
			bttBascula.setLocation(255, 90);
			bttBascula.setSize(100, 20);
			bttBascula.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttBascula_Click(e);
				}
			});
			return bttBascula;
		}
                
                 private JLabel getLblEmpresa(){
			lblEmpresa = new JLabel("Empresa");
			lblEmpresa.setFont(empresa.getOptions().getFontLbl());
			lblEmpresa.setLocation(20, 120);
			lblEmpresa.setSize(120, 20);
			return lblEmpresa;
		}
		
		private JTextField getTxtEmpresa(){
			txtEmpresa = new JTextField();
			txtEmpresa.setText(String.valueOf(this.empresa.getNombre()));
			txtEmpresa.setHorizontalAlignment(JTextField.RIGHT);
			txtEmpresa.setLocation(150,120);
			txtEmpresa.setSize(95, 20);
			txtEmpresa.setFont(empresa.getOptions().getFontLbl());
			return txtEmpresa;
		}
		
		private JButton getBttModEmpresa(){
			bttModEmpresa = new JButton("Modificar");
			bttModEmpresa.setLocation(255, 120);
			bttModEmpresa.setSize(100, 20);
                        bttModEmpresa.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                bttModEmpresa_Click(e);
                            }
                        });
			return bttModEmpresa;
		}
                
                private JLabel getLblTelefono(){
			lblTelefono = new JLabel("Telefono");
			lblTelefono.setFont(empresa.getOptions().getFontLbl());
			lblTelefono.setLocation(20, 150);
			lblTelefono.setSize(120, 20);
			return lblTelefono;
		}
		
		private JTextField getTxtTelefono(){
			txtTelefono = new JTextField();
			txtTelefono.setText(String.valueOf(this.empresa.getTelefono()));
			txtTelefono.setHorizontalAlignment(JTextField.RIGHT);
			txtTelefono.setLocation(150,150);
			txtTelefono.setSize(95, 20);
			txtTelefono.setFont(empresa.getOptions().getFontLbl());
			return txtTelefono;
		}
		
		private JButton getBttModTelefono(){
			bttModTelefono = new JButton("Modificar");
			bttModTelefono.setLocation(255, 150);
			bttModTelefono.setSize(100, 20);
                        bttModTelefono.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                bttModTelefono_Click(e);
                            }
                        });
			return bttModTelefono;
		}
                
                 private JLabel getLblLocalidad(){
			lblLocalidad = new JLabel("Localidad");
			lblLocalidad.setFont(empresa.getOptions().getFontLbl());
			lblLocalidad.setLocation(20, 180);
			lblLocalidad.setSize(120, 20);
			return lblLocalidad;
		}
		
		private JTextField getTxtLocalidad(){
			txtLocalidad = new JTextField();
			txtLocalidad.setText(String.valueOf(this.empresa.getLocalidad()));
			txtLocalidad.setHorizontalAlignment(JTextField.RIGHT);
			txtLocalidad.setLocation(150,180);
			txtLocalidad.setSize(95, 20);
			txtLocalidad.setFont(empresa.getOptions().getFontLbl());
			return txtLocalidad;
		}
		
		private JButton getBttModLocalidad(){
			bttModLocalidad = new JButton("Modificar");
			bttModLocalidad.setLocation(255, 180);
			bttModLocalidad.setSize(100, 20);
                        bttModLocalidad.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                bttModLocalidad_Click(e);
                            }
                        });
			return bttModLocalidad;
		}
                
                private JLabel getLblDireccion(){
			lblDireccion = new JLabel("Direccion");
			lblDireccion.setFont(empresa.getOptions().getFontLbl());
			lblDireccion.setLocation(20, 210);
			lblDireccion.setSize(120, 20);
			return lblDireccion;
		}
		
		private JTextField getTxtDireccion(){
			txtDireccion = new JTextField();
			txtDireccion.setText(String.valueOf(this.empresa.getDireccion()));
			txtDireccion.setHorizontalAlignment(JTextField.RIGHT);
			txtDireccion.setLocation(150,210);
			txtDireccion.setSize(95, 20);
			txtDireccion.setFont(empresa.getOptions().getFontLbl());
			return txtDireccion;
		}
		
		private JButton getBttModDireccion(){
			bttModDireccion = new JButton("Modificar");
			bttModDireccion.setLocation(255, 210);
			bttModDireccion.setSize(100, 20);
                        bttModDireccion.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                bttModDireccion_Click(e);
                            }
                        });
			return bttModDireccion;
		}
                
		private JButton getBttAgregar(){
			ImageIcon img = new ImageIcon("img/Ok.png");
			bttAgregar = new JButton("Aceptar", img);
			bttAgregar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttAgregar_Click(e);
				}
			});
			
			return bttAgregar;
		}
		
				
		/**
		 * Actualiza el ComboBox de Variedad
		 */
		public void actualizarVariedad(){
			cmbVariedad.removeAllItems();
			Variedad unaVariedad;
			Iterator it = this.empresa.configruaciones.getAllVariedades().values().iterator();
			while (it.hasNext()){
				unaVariedad = (Variedad)it.next();
				cmbVariedad.addItem(unaVariedad);
			}
		}
		
		/**
		 * LLama a la Ventana V_Variedad desde el boton de Configuracion de Variedad
		 * @param e ActionEvent
		 */
		private void bttConfVariedad_click(ActionEvent e){
			if (v_var != null){
				if (v_var.isVisible()){
					v_var.setVisible(true);
				}else{
					v_var = new V_Variedad(this.empresa,this);
					v_var.setVisible(true);
				}
			}else{
				v_var = new V_Variedad(this.empresa,this);
				v_var.setVisible(true);
			}
		}
                
                private void bttConfCiudad_click(ActionEvent e){
			if (v_ci != null){
				if (v_ci.isVisible()){
					v_ci.setVisible(true);
				}else{
					v_ci = new V_Ciudad(this.empresa,this);
					v_ci.setVisible(true);
				}
			}else{
				v_ci = new V_Ciudad(this.empresa,this);
				v_ci.setVisible(true);
			}
		}
                
                /**
		 * Actualiza el ComboBox de Ciudad
		 */
		public void actualizarCiudad(){
			cmbCiudad.removeAllItems();
			Ciudad unaCiudad;
			Iterator it = this.empresa.configruaciones.getAllCiudades().values().iterator();
			while (it.hasNext()){
				unaCiudad = (Ciudad)it.next();
				cmbCiudad.addItem(unaCiudad);
			}
		}
		
		private void bttAgregar_Click(ActionEvent e){
			this.dispose();
		}
                
                private void bttBascula_Click(ActionEvent e){
                    int resultado; JFileChooser chooser;
                    DbConfiguraciones dbConf = new DbConfiguraciones(Empresa.con);
                    chooser = new JFileChooser();
                    chooser.setFileFilter(new MdbFilterBascula());
                    chooser.setCurrentDirectory(new File("C:/"));
                    chooser.setDialogTitle("Buscar Base de Datos de Bascula");
                    resultado = chooser.showOpenDialog(this);
                    if (resultado == JFileChooser.APPROVE_OPTION){
			txtBascula.setText(chooser.getSelectedFile().getAbsolutePath());
                        this.empresa.getConf().setBascula(chooser.getSelectedFile().getAbsolutePath());
                        dbConf.actualizarBascula(chooser.getSelectedFile().getAbsolutePath());
                    }
                }
                
                private void bttModEmpresa_Click(ActionEvent e){
                    empresa.setNombre(txtEmpresa.getText());
                    dbEmp.actualizar(empresa);
                }
                
                private void bttModTelefono_Click(ActionEvent e){
                    empresa.setTelefono(txtTelefono.getText());
                    dbEmp.actualizar(empresa);
                }
                
                private void bttModLocalidad_Click(ActionEvent e){
                    empresa.setLocalidad(txtLocalidad.getText());
                    dbEmp.actualizar(empresa);
                }
                
                private void bttModDireccion_Click(ActionEvent e){
                    empresa.setDireccion(txtDireccion.getText());
                    dbEmp.actualizar(empresa);
                }
}
