package Gui;

import Reportes.ReporteCensoEntrega;
import Reportes.ReportePlanillaEntrega;
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

public class V_PlanillaEntrega extends JFrame{
	

	private JPanel jContentPane;
	private JPanel pnlSocio;
	private JPanel pnlCensoEntrega;
	private JPanel pnlTbl;
	private JPanel pnlAcciones;
	private JLabel lblSocio;
        private JButton bttCancelar;
        private JButton bttListado;
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
	
	public V_PlanillaEntrega(Empresa emp,Socio unSocio, V_Principal principal){
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
        
        public V_PlanillaEntrega(Empresa emp,Socio unSocio, String temporada, V_Principal principal){
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
                   //crearNuevoCenso(bol);
                }
            }else{
              this.censoEntrega = this.unSocio.getCensoEntrega(temporada);
              this.censoEntregaInfos.clear();
              if (this.censoEntrega != null){
                  this.censoEntregaInfos.putAll(this.censoEntrega.getAllCensoEntreInfo());
              }else{
                  //crearNuevoCenso(bol);
              }
            }
        }
        
        private void initialize(){
		this.setTitle("Planilla Entrega VPN");
		this.setSize(400,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
               
          
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
		pnlTbl.add(getTblCensoEntrega());
		return pnlTbl;
	}
	
	private JPanel getPnlEntrega(){
		pnlCensoEntrega = new JPanel();
		pnlCensoEntrega.setBorder(BorderFactory.createTitledBorder("Datos"));
		pnlCensoEntrega.setBackground(empresa.getOptions().getColor_form());
		pnlCensoEntrega.setLayout(null);
		pnlCensoEntrega.setSize(390, 450);
		pnlCensoEntrega.setLocation(2,50);
		pnlCensoEntrega.add(getTblCensoEntrega());
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
		lblSocio = new JLabel(unSocio.getNomApe() , JLabel.CENTER);
		lblSocio.setFont(empresa.getOptions().getFontLbl());
		lblSocio.setLocation(0, 20);
		lblSocio.setSize(304, 20);
		return lblSocio;
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
        
                
        private Map getMeses(){
            Map meses = new HashMap();
            meses.put(2, "Enero"); meses.put(3, "Febrero"); meses.put(4, "Marzo");
            meses.put(5, "Abril"); meses.put(6, "Mayo"); meses.put(7, "Junio");
            meses.put(8, "Julio"); meses.put(9, "Agosto"); meses.put(10, "Septiembre");
            meses.put(11, "Octubre"); meses.put(12, "Noviembre"); meses.put(13, "Diciembre");
            return meses;
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
		scrTblCenso.setSize(373, 420);
		scrTblCenso.setLocation(10, 20);
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
            int nroFila = this.tblCensoEntrega.getSelectedRow();
            System.out.println(nroFila);
            if (nroFila > -1){
                Variedad variedad = (Variedad)this.tblCensoEntrega.getValueAt(nroFila, 2);
		String año = new Integer((Integer)(this.tblCensoEntrega.getValueAt(nroFila, 1))).toString();
                ReportePlanillaEntrega rePla = new ReportePlanillaEntrega(new Long(unSocio.getNroSocio()).intValue(), 
                        unSocio.getNomApe(),variedad.getNombre(),año,empresa, "Reporte Control Materia Prima");
                rePla.start();
            }else{
                ReportePlanillaEntrega rePla = new ReportePlanillaEntrega(new Long(unSocio.getNroSocio()).intValue(),
                        unSocio.getNomApe(),"","",empresa, "Reporte Control Materia Prima");
                rePla.start();
            }
	}
}