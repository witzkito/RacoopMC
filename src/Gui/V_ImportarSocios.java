package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import tools.DbfFilter;
import base.Empresa;
import base.MovInformativo;
import base.Socio;
import db.DbImportSocios;
import db.DbMovInformativo;
import db.DbSocio;

/**
 * @author Adrian
 * <p>Clase Visual de la Ventana en dode se elije importar desde el sistema de Ricardo (el anterior)</p>
 */
public class V_ImportarSocios extends JFrame implements Runnable {
		//Atributos
		private Empresa empresa = null;
		private JPanel jContentPane = null;
		private V_Principal principal = null;
		private JPanel pnlIndice = null;
		private JPanel pnlSocios = null;
		private JPanel pnlMovimientos = null;
		private JPanel pnlAcciones = null;
		private JButton bttSocio = null;
		private JTextField txtSocio = null;
		private JLabel lblSocio = null;
		private JButton bttSocioA = null;
		private JButton bttMovimientos = null;
		private JTextField txtMovimientos = null;
		private JLabel lblMovimientos = null;
		private JButton bttMovimientosA = null;
		private JButton bttIndice = null;
		private JTextField txtIndice = null;
		private JLabel lblIndice = null;
		private JButton bttIndiceA = null;
		private JButton bttFinalizar = null;
		private JButton bttCancelar = null;
		private JFileChooser chooser = null;
		private JProgressBar progres = null;
		private String ubicacionS = null;
		private String ubicacionM = null;
		private String ubicacionI = null;
		private long contS = 0;
		private long contM = 0;
		private long contI = 0;
		private Thread th = null;
		private Map socios = new HashMap();
		public boolean bandera;
		
		
	//Constructores
		public V_ImportarSocios(Empresa empresa, V_Principal principal){
			super();
			this.empresa = empresa;
			this.principal = principal;
			initialize();
		}
		
	//Metodos
		public void initialize(){
			this.setSize(310,450);
			this.setLocation(250,100);
			this.setTitle("Importar");
			this.setContentPane(getJContentPane());
			this.setIconImage(this.empresa.getOptions().getIcono().getImage());
			this.setResizable(false);
			this.setLocationRelativeTo(principal);
			this.setVisible(true);
		}
		
		private JPanel getJContentPane(){
			jContentPane = new JPanel();
			jContentPane.setBackground(empresa.getOptions().getColor_form());
			jContentPane.setLayout(null);
			jContentPane.add(getPnlSocios());
			jContentPane.add(getPnlMovimientos());
			jContentPane.add(getPnlIndice());
			jContentPane.add(getLblSocio());
			jContentPane.add(getLblMovimiento());
			jContentPane.add(getLblIndice());
			jContentPane.add(getPnlAcciones());
			jContentPane.add(getJProgressBar());
			return jContentPane;
		}
		
		private JPanel getPnlSocios(){
			pnlSocios = new JPanel();
			pnlSocios.setBorder(BorderFactory.createTitledBorder("Socios"));
			pnlSocios.setLocation(0,0);
			pnlSocios.setSize(302, 87);
			pnlSocios.setBackground(empresa.getOptions().getColor_form());
			pnlSocios.setLayout(null);
			pnlSocios.add(getTxtSocio());
			pnlSocios.add(getBttSocio());
			pnlSocios.add(getBttSocioA());
			return pnlSocios;
		}
		
		private JPanel getPnlMovimientos(){
			pnlMovimientos = new JPanel();
			pnlMovimientos.setLocation(0, 87);
			pnlMovimientos.setSize(302,87);
			pnlMovimientos.setLayout(null);
			pnlMovimientos.setEnabled(false);
			pnlMovimientos.setBorder(BorderFactory.createTitledBorder("Movimientos"));
			pnlMovimientos.setBackground(empresa.getOptions().getColor_form());
			pnlMovimientos.add(getTxtMovimientos());
			pnlMovimientos.add(getBttMovimientos());
			pnlMovimientos.add(getBttMovimientosA());
			return pnlMovimientos;
		}
		
		private JPanel getPnlIndice(){
			pnlIndice = new JPanel();
			pnlIndice.setLocation(0,174);
			pnlIndice.setSize(302,87);
			pnlIndice.setLayout(null);
			pnlIndice.setEnabled(false);
			pnlIndice.setBorder(BorderFactory.createTitledBorder("Indices"));
			pnlIndice.setBackground(empresa.getOptions().getColor_form());
			pnlIndice.add(getTxtIndice());
			pnlIndice.add(getBttIndice());
			pnlIndice.add(getBttIndiceA());
			return pnlIndice;
		}
		
		private JPanel getPnlAcciones(){
			pnlAcciones = new JPanel();
			pnlAcciones.setLocation(0, 330);
			pnlAcciones.setSize(302, 75);
			pnlAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
			pnlAcciones.setBackground(empresa.getOptions().getColor_form());
			pnlAcciones.add(getBttFinalizar());
			pnlAcciones.add(getBttCancelar());
			return pnlAcciones;
		}
		
		private JLabel getLblSocio(){
			lblSocio = new JLabel("0 Socios encontrados");
			lblSocio.setFont(empresa.getOptions().getFontLbl());
			lblSocio.setLocation(75, 270);
			lblSocio.setSize(210, 20);
			return lblSocio;
		}
		
		private JLabel getLblMovimiento(){
			lblMovimientos = new JLabel("0 Movimientos encontrados");
			lblMovimientos.setFont(empresa.getOptions().getFontLbl());
			lblMovimientos.setLocation(75, 290);
			lblMovimientos.setEnabled(false);
			lblMovimientos.setSize(210, 20);
			return lblMovimientos;
		}
		
		private JLabel getLblIndice(){
			lblIndice = new JLabel("0 Indices encontrados");
			lblIndice.setFont(empresa.getOptions().getFontLbl());
			lblIndice.setLocation(75, 310);
			lblIndice.setEnabled(false);
			lblIndice.setSize(210, 20);
			return lblIndice;
		}
		
		private JTextField getTxtSocio(){
			txtSocio = new JTextField();
			txtSocio.setSize(empresa.getOptions().getX_TamTxt() + 120, empresa.getOptions().getY_TamTxt());
			txtSocio.setLocation(40, 30);
			return txtSocio;
		}
		
		private JButton getBttSocio(){
			bttSocio = new JButton("...");
			bttSocio.setLocation(260, 30);
			bttSocio.setSize(20, 20);
			bttSocio.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttSocio_Click(e);
				}
			});
			return bttSocio;
		}
		
		private JButton getBttSocioA(){
			bttSocioA = new JButton("Importar");
			bttSocioA.setLocation(110, 55);
			bttSocioA.setSize(85, 25);
			bttSocioA.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttSocioA_Click(e);
				}
			});
			return bttSocioA;
		}
		
		private JTextField getTxtMovimientos(){
			txtMovimientos = new JTextField();
			txtMovimientos.setSize(empresa.getOptions().getX_TamTxt() + 120, empresa.getOptions().getY_TamTxt());
			txtMovimientos.setLocation(40, 30);
			txtMovimientos.setEnabled(false);
			return txtMovimientos;
		}
		
		private JButton getBttMovimientos(){
			bttMovimientos = new JButton("...");
			bttMovimientos.setLocation(260, 30);
			bttMovimientos.setSize(20, 20);
			bttMovimientos.setEnabled(false);
			bttMovimientos.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttMovimientos_Click(e);
				}
			});
			return bttMovimientos;
		}
		
		private JButton getBttMovimientosA(){
			bttMovimientosA = new JButton("Importar");
			bttMovimientosA.setLocation(110, 55);
			bttMovimientosA.setSize(85, 25);
			bttMovimientosA.setEnabled(false);
			bttMovimientosA.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttMovimientosA_Click(e);
				}
			});
			return bttMovimientosA;
		}
		
		private JTextField getTxtIndice(){
			txtIndice = new JTextField();
			txtIndice.setSize(empresa.getOptions().getX_TamTxt() + 120, empresa.getOptions().getY_TamTxt());
			txtIndice.setLocation(40, 30);
			txtIndice.setEnabled(false);
			return txtIndice;
		}
		
		private JButton getBttIndice(){
			bttIndice = new JButton("...");
			bttIndice.setLocation(260, 30);
			bttIndice.setSize(20, 20);
			bttIndice.setEnabled(false);
			bttIndice.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttIndice_Click(e);
				}
			});
			return bttIndice;
		}
		
		private JButton getBttIndiceA(){
			bttIndiceA = new JButton("Importar");
			bttIndiceA.setLocation(110, 55);
			bttIndiceA.setSize(85, 25);
			bttIndiceA.setEnabled(false);
			bttIndiceA.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttIndiceA_Click(e);
				}
			});
			return bttIndiceA;
		}
		
		private JButton getBttFinalizar(){
			ImageIcon img = new ImageIcon("img/Ok.png");
			bttFinalizar = new JButton("Finalizar", img);
			bttFinalizar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttFinalizar_Click(e);
				}
			});
			return bttFinalizar;
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
		
		private JProgressBar getJProgressBar(){
			progres = new JProgressBar();
			progres.setSize(300, 15);
			progres.setLocation(1,405);
			progres.setOpaque(false);
			progres.setStringPainted(true);
			progres.setBorderPainted(true);
			progres.setValue(0);
			progres.setVisible(false);
			return progres;
			
		}
		
		private void bttSocio_Click(ActionEvent e){
			int resultado; File f;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("C:\\"));
			chooser.setFileFilter(new DbfFilter());
			resultado = chooser.showOpenDialog(this);
			if (resultado == JFileChooser.APPROVE_OPTION){
				f = chooser.getSelectedFile();
				txtSocio.setText(f.getPath());
			}
		}
		
		private void bttSocioA_Click(ActionEvent e){
			DbImportSocios imp = new DbImportSocios(txtSocio.getText(), this);
			lblSocio.setText(new Long(imp.getCount()).toString() + " Socios encontrados");
			if (imp.getError()){
				ubicacionS = txtSocio.getText();
				contS = imp.getCount();
			}
		}
		
		private void bttFinalizar_Click(ActionEvent e){
			if (ubicacionS != null){
				th = new Thread(this);
				txtSocio.setEnabled(false);
				txtMovimientos.setEnabled(false);
				txtIndice.setEnabled(false);
				bttFinalizar.setEnabled(false);
				bttCancelar.setEnabled(false);
				bttSocioA.setEnabled(false);
				bttMovimientosA.setEnabled(false);
				bttIndiceA.setEnabled(false);
				bttSocio.setEnabled(false);
				bttMovimientos.setEnabled(false);
				bttIndice.setEnabled(false);
				progres.setVisible(true);
				progres.setMaximum(new Long(contS + contM + contI).intValue());
				th.start();
			}
		}
		
		private void bttMovimientos_Click(ActionEvent e){
			int resultado; File f;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("C:\\"));
			chooser.setFileFilter(new DbfFilter());
			resultado = chooser.showOpenDialog(this);
			if (resultado == JFileChooser.APPROVE_OPTION){
				f = chooser.getSelectedFile();
				txtMovimientos.setText(f.getPath());
			}
		}
		
		private void bttMovimientosA_Click(ActionEvent e){
			DbImportSocios imp = new DbImportSocios(txtMovimientos.getText(), this);
			lblMovimientos.setText(imp.getCount() + " Movimientos encontrados" );
			if (imp.getError()){
				ubicacionM = txtMovimientos.getText();
				contM = imp.getCount();
			}
		}
		
		private void bttIndice_Click(ActionEvent e){
			int resultado; File f;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("*.*"));
			chooser.setFileFilter(new DbfFilter());
			resultado = chooser.showOpenDialog(this);
			if (resultado == JFileChooser.APPROVE_OPTION){
				f = chooser.getSelectedFile();
				txtMovimientos.setText(f.getPath());
			}
		}
		
		private void bttIndiceA_Click(ActionEvent e){
			DbImportSocios imp = new DbImportSocios(txtIndice.getText(), this);
			lblIndice.setText(new Long(imp.getCount()).toString() + " Indices encontrados");
			if(imp.getError()){
				ubicacionI = txtIndice.getText();
				contI = imp.getCount();
			}
		}
		
		public void incrementarProgress(int incremento){
			progres.setValue(progres.getValue() + incremento);
		}
		
		private void bttCancelar_Click(ActionEvent e){
			this.dispose();
		}
		
	public void run(){
		if (ubicacionS != null){
			try{
				DbSocio dbSo = new DbSocio(Empresa.con);
				progres.setString("Guardando Socios...");
				DbImportSocios impS = new DbImportSocios(ubicacionS, this);
				socios.putAll(impS.getAllSocios(this));
				empresa.addMapSocios(socios);
				progres.setString("Creando Movimientos Informativos...");
				cargarInformacion();
				dbSo.InsertAll(socios);
				progres.setString("Transformando caracteres invalidos");
				dbSo.correjir();
				th.sleep(1000);
				System.exit(1);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		try{
			th.sleep(1000);
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Carga la informacion de los socios 
	 */
	private void cargarInformacion(){
		Collection informaciones = empresa.getMapSocios().values();
		Iterator it = informaciones.iterator(); Socio so; MovInformativo mov; 
		DbMovInformativo dbMov = new DbMovInformativo(Empresa.con);
		while (it.hasNext()){
			so = (Socio)it.next();
			if (so.getAllInformaciones().size() == 0){
				mov = new MovInformativo(so.getLastIdMovInformativo(),so.getFechaIngreso(), "Se ha dado de Alta al Socio");
				so.addInformacion(mov);
				dbMov.Insert(mov, so);
				if (so.getEstado().equals("baja")){
					mov = new MovInformativo(so.getLastIdMovInformativo(), so.getFechaEgreso(), "Se ha dado de Baja al Socio");
					so.addInformacion(mov);
					dbMov.Insert(mov, so);
				}
			}
		}
	}
		
}
