package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
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
import tools.MdbFilter;
import base.Empresa;
import base.MovInformativo;
import base.Socio;
import db.Coneccion;
import db.DbCensoPlanInfo;
import db.DbMovInformativo;
import db.DbSocio;

/**
 * @author Adrian - 11 de Diciembre del 2007
 * <p>Clase Visual de la Ventana en dode se elije importar desde el Sistema Racoop</p>
 */
public class V_ImportarRacoop extends JFrame implements Runnable {
		//Atributos
		private Empresa empresa = null;
		private JPanel jContentPane = null;
		private V_Principal principal = null;
		private JPanel pnlBase = null;
		private JPanel pnlInfo = null;
		private JPanel pnlAcciones = null;
		private JButton bttBase = null;
		private JTextField txtBase = null;
		private JLabel lblBase = null;
		private JButton bttBaseA = null;
		private JButton bttFinalizar = null;
		private JButton bttCancelar = null;
		private JLabel lblBaseOK = null;
		private JLabel lblSocios = null;
		private ImageIcon iconoPregunta;
		private ImageIcon iconoOk;
		private ImageIcon iconoNoOk;
		private JLabel imgBase;
		private JLabel imgSocios;
		private JFileChooser chooser = null;
		private JProgressBar progres = null;
		private Thread th = null;
		private Coneccion conn = null;
		private DbSocio dbSo = null;
		private DbCensoPlanInfo dbCensoPlaInfo = null;
		//Variables para saber si esta listo para importar
		private boolean compBase = false;
		private boolean compSocio = false;
			
		
	//Constructores
		public V_ImportarRacoop(Empresa empresa, V_Principal principal){
			super();
			this.empresa = empresa;
			this.principal = principal;
			this.iconoOk = new ImageIcon("img/settings.png");
			this.iconoNoOk = new ImageIcon("img/stop.gif");
			this.iconoPregunta = new ImageIcon("img/inter.gif");
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
			jContentPane.add(getPnlBase());
			jContentPane.add(getPnlInfo());
			jContentPane.add(getPnlAcciones());
			jContentPane.add(getJProgressBar());
			return jContentPane;
		}
				
		private JPanel getPnlBase(){
			pnlBase = new JPanel();
			pnlBase.setLocation(0,0);
			pnlBase.setSize(302,87);
			pnlBase.setLayout(null);
			pnlBase.setBorder(BorderFactory.createTitledBorder("Base de Datos"));
			pnlBase.setBackground(empresa.getOptions().getColor_form());
			pnlBase.add(getTxtBase());
			pnlBase.add(getBttBase());
			pnlBase.add(getBttBaseA());
			return pnlBase;
		}
		
		private JPanel getPnlInfo(){
			pnlInfo = new JPanel();
			pnlInfo.setLocation(0,90);
			pnlInfo.setSize(302,237);
			pnlInfo.setLayout(null);
			pnlInfo.setBackground(empresa.getOptions().getColor_form());
			pnlInfo.add(getLblBaseOk());
			pnlInfo.add(getLblImgBase());
			pnlInfo.add(getLblSocios());
			pnlInfo.add(getLblImgSocios());
			return pnlInfo;
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
				
		private JTextField getTxtBase(){
			txtBase = new JTextField();
			txtBase.setSize(empresa.getOptions().getX_TamTxt() + 120, empresa.getOptions().getY_TamTxt());
			txtBase.setLocation(40, 30);
			return txtBase;
		}
		
		private JButton getBttBase(){
			bttBase = new JButton("...");
			bttBase.setLocation(260, 30);
			bttBase.setSize(20, 20);
			bttBase.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttBase_Click(e);
				}
			});
			return bttBase;
		}
		
		private JButton getBttBaseA(){
			bttBaseA = new JButton("Importar");
			bttBaseA.setLocation(110, 55);
			bttBaseA.setSize(85, 25);
			bttBaseA.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttBaseA_Click(e);
				}
			});
			return bttBaseA;
		}
		
		private JLabel getLblBaseOk(){
			lblBaseOK = new JLabel("Base de Datos");
			lblBaseOK.setLocation(10,10);
			lblBaseOK.setSize(220, 15);
			lblBaseOK.setFont(empresa.getOptions().getFontLbl());
			return lblBaseOK;
		}
		
		private JLabel getLblImgBase(){
			imgBase = new JLabel(this.iconoPregunta);
			imgBase.setLocation(230, 7);
			imgBase.setSize(30, 20);
			return imgBase;
		}
		
		private JLabel getLblImgSocios(){
			imgSocios = new JLabel(this.iconoPregunta);
			imgSocios.setLocation(230, 32);
			imgSocios.setSize(30, 20);
			return imgSocios;
		}
		
		private JLabel getLblSocios(){
			lblSocios = new JLabel("Socios Nuevos encontrados");
			lblSocios.setLocation(10,35);
			lblSocios.setSize(220, 15);
			lblSocios.setFont(empresa.getOptions().getFontLbl());
			return lblSocios;
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
		
		private void bttFinalizar_Click(ActionEvent e){
			boolean entrar = false;
			if (this.compBase){
				if (!(this.compSocio)){
					if (JOptionPane.showConfirmDialog(this, "Existen errores en la Base de Datos, estos seran omitidos. Desea continuar la" +
							" importacion", "Mensaje",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						entrar = true;
					}
				}else{
					entrar = true;
				}
				if (entrar){
					th = new Thread(this);
					txtBase.setEnabled(false);
					bttFinalizar.setEnabled(false);
					bttCancelar.setEnabled(false);
					bttBaseA.setEnabled(false);
					bttBase.setEnabled(false);
					progres.setVisible(true);
					progres.setMaximum(new Integer(3));
					th.start();
				}
			}else{
				JOptionPane.showMessageDialog(this, "No se puede importar, existe un error en la base de datos, compruebe si la" +
						                      " ubicacion es correcta", "Mensaje", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
						
		private void bttBase_Click(ActionEvent e){
			int resultado; File f;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("*.*"));
			chooser.setFileFilter(new MdbFilter());
			resultado = chooser.showOpenDialog(this);
			if (resultado == JFileChooser.APPROVE_OPTION){
				f = chooser.getSelectedFile();
				txtBase.setText(f.getPath());
			}
		}
		
		private void bttBaseA_Click(ActionEvent e){
			try{
				this.conn = new Coneccion(txtBase.getText());
				this.imgBase.setIcon(this.iconoOk);
				this.compBase = true;
			}catch(Exception ex){
				this.imgBase.setIcon(this.iconoNoOk);
				this.compBase = false;
				
			}
                        this.lblSocios.setText(countSocios(conn) + " Socios Nuevos Encontrados");
			if(dbSo.estado()){
                            this.imgSocios.setIcon(this.iconoOk);
                            this.compSocio = true;
			}else{
                            this.imgSocios.setIcon(this.iconoNoOk);
                            this.compSocio = true;
                        }
				
		}
		
		private int countSocios(Coneccion conn){
			Map sociosImp; this.dbSo = new DbSocio(conn);
			Socio so; Socio soAux; int cont = 0;
			sociosImp = dbSo.getAll();
			Iterator it = sociosImp.values().iterator();
			while (it.hasNext()){
				so = (Socio)it.next();
				soAux = empresa.getSocio(so.getNroSocio());
				if (soAux == null){
					cont = cont + 1;
				}
			}
			return cont;
		}
		
		public void incrementarProgress(int incremento){
			progres.setValue(progres.getValue() + incremento);
		}
		
		private void bttCancelar_Click(ActionEvent e){
			this.dispose();
		}
		
	public void run(){
		try{
			progres.setString("Guardando Socios...");
			trabajarSocios();
			this.incrementarProgress(1);
			th.sleep(1000);
			System.exit(1);
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
		try{
			th.sleep(1000);
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Importa a la tabla Socios
	private void trabajarSocios(){
		this.dbSo = new DbSocio(this.conn);
		DbSocio dbSoAux = new DbSocio(Empresa.con);
		Socio so; Iterator it = dbSo.getAll().values().iterator();
		Map socios = dbSoAux.getAll();
		while (it.hasNext()){
			so = (Socio)it.next();
			if (socios.get(so.getNroSocio()) == null){
				dbSoAux.Insert(so);
			}else{
				dbSoAux.actualizar(so);
			}
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
