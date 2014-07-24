package Gui;

import Reportes.ReportePlanillaEntrega;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import db.DbCensoEntrega;
import db.DbCensoEntregaInfo;
import db.DbMovInformativo;
import base.CensoEntrega;
import base.Empresa;
import base.Tercero;

public class V_PlanillaEntregaTer extends JFrame{
	

	private JPanel jContentPane;
	private JPanel pnlSocio;
	private JPanel pnlCensoEntrega;
	private JPanel pnlTbl;
	private JPanel pnlAcciones;
	private JLabel lblSocio;
        private JLabel lblVariedad;
        private JComboBox cmbVariedad;
        private JRadioButton rd1Año;
        private JRadioButton rd2Año;
        private JButton bttCancelar;
        private JButton bttListado;
	private JTable tblCensoEntrega;
	private JScrollPane scrTblCenso;
	private Empresa empresa;
        private String temporada;
	private V_Principal principal;
	private CensoEntrega censoEntrega = null;
	private Map censoEntregaInfos;
	private Tercero unTercero;
	private DbCensoEntrega dbCensoEntrega;
	private DbMovInformativo dbMovInfor;
	private DbCensoEntregaInfo dbCensoEntregaInfo;
        private Calendar cal = Calendar.getInstance();
	
	public V_PlanillaEntregaTer(Empresa emp,Tercero unTercero, V_Principal principal){
		super();
		this.empresa = emp;
		this.principal = principal;
		this.unTercero = unTercero;
		this.setIconImage(this.empresa.getOptions().getIcono().getImage());
		initialize();
		
        }
        
       
        private void initialize(){
		this.setTitle("Planilla Entrega VPN");
		this.setSize(400,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
                ButtonGroup grupo = new ButtonGroup();
                grupo.add(rd1Año);
                grupo.add(rd2Año);
               
          
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
	
	
	
	private JPanel getPnlEntrega(){
		pnlCensoEntrega = new JPanel();
		pnlCensoEntrega.setBorder(BorderFactory.createTitledBorder("Datos"));
		pnlCensoEntrega.setBackground(empresa.getOptions().getColor_form());
		pnlCensoEntrega.setLayout(null);
		pnlCensoEntrega.setSize(390, 450);
		pnlCensoEntrega.setLocation(2,50);
                pnlCensoEntrega.add(getLblVariedad());
                pnlCensoEntrega.add(getCmbVariedad());
                pnlCensoEntrega.add(getRd1Año());
                pnlCensoEntrega.add(getRd2Año());
		return pnlCensoEntrega;
	}
	
	private JPanel getPnlAcciones(){
		pnlAcciones = new JPanel();
		pnlAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
		pnlAcciones.setBackground(empresa.getOptions().getColor_form());
		pnlAcciones.setSize(390,65);
		pnlAcciones.setLocation(2,500);
                pnlAcciones.add(getBttListado());
		pnlAcciones.add(getBttCancelar());
                return pnlAcciones;
	}
	
	
	
	private JLabel getLblSocio(){
		lblSocio = new JLabel(unTercero.getNombre() , JLabel.CENTER);
		lblSocio.setFont(empresa.getOptions().getFontLbl());
		lblSocio.setLocation(0, 20);
		lblSocio.setSize(304, 20);
		return lblSocio;
	}
        
        private JComboBox getCmbVariedad(){
		cmbVariedad = new JComboBox(empresa.getConf().getAllVariedades().values().toArray());
		cmbVariedad.setLocation(175,37);
		cmbVariedad.setSize(120, 20);
		cmbVariedad.setFont(empresa.getOptions().getFontLbl());
		return cmbVariedad;
	}
	
        private JLabel getLblVariedad(){
		lblVariedad = new JLabel("Variedad");
		lblVariedad.setFont(empresa.getOptions().getFontLbl());
		lblVariedad.setLocation(80, 37);
		lblVariedad.setSize(304, 20);
		return lblVariedad;
	}
        
        private JRadioButton getRd1Año(){
		rd1Año = new JRadioButton("1 Año");
		rd1Año.setLocation(100,87);
		rd1Año.setSize(70,15);
		rd1Año.setBackground(empresa.getOptions().getColor_form());
		rd1Año.setFont(empresa.getOptions().getFontLbl());
		rd1Año.setSelected(true);
		return rd1Año;
	}
	
	private JRadioButton getRd2Año(){
		rd2Año = new JRadioButton("2 Año");
		rd2Año.setLocation(218,87);
		rd2Año.setSize(70,15);
		rd2Año.setBackground(empresa.getOptions().getColor_form());
		rd2Año.setFont(empresa.getOptions().getFontLbl());
		return rd2Año;
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
	
	
	public void bttAceptar_Click(ActionEvent e){
		
		
	}
	
        
	
	public void bttCancelar_Click(ActionEvent e){
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
	
	
	
	       
        //Accion del Boton bttListado
	private void bttListado_Click(ActionEvent e){
            String id = new Integer(this.unTercero.getId()).toString(); String año;
            if (this.rd1Año.isSelected()){
                año = "1";
            }else{
                año = "2";
            }
            ReportePlanillaEntrega rePla = new ReportePlanillaEntrega(this.unTercero.getId(), this.unTercero.getNombre(),
                    this.cmbVariedad.getSelectedItem().toString(),año,empresa, "Censo Entrega");
            rePla.start();
	}
}