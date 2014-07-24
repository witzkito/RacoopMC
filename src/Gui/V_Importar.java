package Gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tools.MdbFilter;
import base.Empresa;



/**
 * @author Adrian
 * <p>Clase Visual de la Ventana de Importar en donde se elije los sistemas desde donde importar</p>
 */
@SuppressWarnings("serial")
public class V_Importar extends JFrame {
	
		private JPanel jContentPane = null;
		private JPanel pnlDesde = null;
		private JPanel pnlAcciones = null;
		private JButton bttSistema1 = null;
		private JButton bttSistema2 = null;
		private JButton bttSistema3 = null;
                private V_Principal principal;
		private Empresa empresa;
		
		public V_Importar(Empresa empresa, V_Principal principal){
			super();
			this.empresa = empresa;
			this.principal = principal;
			initialize();
			
		}
		
		private void initialize(){
			this.setSize(350,450);
			this.setLocation(250,100);
			this.setTitle("Importar Datos");
			this.setContentPane(getJContentPane());
			this.setIconImage(this.empresa.getOptions().getIcono().getImage());
			this.setResizable(false);
			this.setLocationRelativeTo(principal);
		}
		
		private JPanel getJContentPane(){
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(empresa.getOptions().getColor_form());
			jContentPane.add(getPnlDesde());
			return jContentPane;
		}
		
		private JPanel getPnlDesde(){
			pnlDesde = new JPanel();
			pnlDesde.setBorder(BorderFactory.createTitledBorder("Importar desde"));
			pnlDesde.setLocation(0, 0);
			pnlDesde.setSize(347,417);
			pnlDesde.setBackground(empresa.getOptions().getColor_form());
			pnlDesde.setLayout(null);
			pnlDesde.add(getPnlAcciones());
			return pnlDesde;
		}
		
		private JPanel getPnlAcciones(){
			pnlAcciones = new JPanel();
			pnlAcciones.setLocation(25, 30);
			pnlAcciones.setSize(300,340);
			pnlAcciones.setBackground(empresa.getOptions().getColor_form());
			pnlAcciones.setLayout(new GridLayout(6,0));
			//pnlAcciones.add(getBttSistema1());
			pnlAcciones.add(getBttSistema2());
			pnlAcciones.add(getBttSistema3());
                        return pnlAcciones;
		}
		
		private JButton getBttSistema1(){
			bttSistema1 = new JButton("Socios - Ricardo Strieder - 1998");
			bttSistema1.setIcon(new ImageIcon("img/cla.PNG"));
			bttSistema1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttSistema1_Click(e);
				}
			});
			return bttSistema1;
		}
		
		private JButton getBttSistema2(){
			bttSistema2 = new JButton("Racoop (Oficina) - 2007");
			bttSistema2.setIcon(new ImageIcon("img/icono.gif"));
			bttSistema2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttSistema2_Click(e);
				}
			});
			return bttSistema2;
		}
		
		private JButton getBttSistema3(){
			bttSistema3 = new JButton("Racoop - Modulo Censo - BackUp - 2007");
			bttSistema3.setIcon(new ImageIcon("img/icono.gif"));
			bttSistema3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttSistema3_Click(e);
				}
			});
			return bttSistema3;
		}
                
               	private void bttSistema1_Click(ActionEvent e){
			V_ImportarSocios vImporSo = new V_ImportarSocios(empresa, principal);
			this.dispose();
		}
		
		private void bttSistema2_Click(ActionEvent e){
			V_ImportarRacoop vImporRa = new V_ImportarRacoop(empresa, principal);
			this.dispose();
		}
		
		private void bttSistema3_Click(ActionEvent e){
			int resultado; File f; JFileChooser chooser;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("C:/"));
			chooser.setFileFilter(new MdbFilter());
			chooser.setApproveButtonText("Restaurar");
			chooser.setDialogTitle("Importar BackUp");
			resultado = chooser.showOpenDialog(this);
			if (resultado == JFileChooser.APPROVE_OPTION){
				try{
					FileInputStream fis = new FileInputStream(chooser.getSelectedFile()); 
					f = new File("db/db.mdb");
					f.delete();
					FileOutputStream fos = new FileOutputStream("db/db.mdb");
					FileChannel canalFuente = fis.getChannel(); 
				    FileChannel canalDestino = fos.getChannel(); 
				    canalFuente.transferTo(0, canalFuente.size(), canalDestino); 
				    fis.close(); 
				    fos.close();
				    System.exit(1);
				}catch (Exception ex) {
					 JOptionPane.showMessageDialog(this, "Error Importando BackUp de la Base de Datos: " + ex, "Mensaje", JOptionPane.ERROR_MESSAGE);
				 }
			}
		}
               
		
}

