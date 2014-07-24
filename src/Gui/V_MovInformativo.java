package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import db.DbMovInformativo;
import base.Empresa;
import base.Fecha_wi;
import base.MovInformativo;
import base.Socio;

/**
 * @author Adrian
 * <p> Clase Visual de ABM de los Movimientos Informativos, bloqueado en este modulo
 */
public class V_MovInformativo extends JFrame {
	//Atributos
		private JPanel jContentPane;
		private JPanel pnlDatos;
		private JPanel pnlAcciones;
		private JLabel lblNumero;
		private JTextField txtNumero;
		private JLabel lblFecha;
		private JFormattedTextField txtFecha;
		private JLabel lblDescripcion;
		private JTextField txtDescripcion;
		private JButton bttAgregar;
		private JButton bttModificar;
		private JButton bttBorrar;
		private JButton bttCancelar;
		private MovInformativo movInfo;
		private Empresa empresa;
		private Socio unSocio;
		private V_Principal principal;
	
	//Constructor
		public V_MovInformativo(Empresa empresa, Socio unSocio, V_Principal principal, MovInformativo movInfo){
			super();
			this.empresa = empresa;
			this.unSocio = unSocio;
			this.principal = principal;
			this.movInfo = movInfo;
			initialize();
		}
		
	//Metodos
		
		private void initialize(){
			if ((unSocio != null) || (movInfo != null)){
				this.setSize(310,400);
				this.setLocationRelativeTo(principal);
				this.setTitle("Movimientos Informativos");
				this.setContentPane(getJContentPane());
				this.setResizable(false);
				this.setIconImage(this.empresa.getOptions().getIcono().getImage());
			}
		}
		
		private JPanel getJContentPane(){
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(empresa.getOptions().getColor_form());
			jContentPane.add(getPnlDatos());
			jContentPane.add(getPnlAcciones());
			return jContentPane;
		}
		
		private JPanel getPnlDatos(){
			pnlDatos = new JPanel();
			pnlDatos.setLocation(0,0);
			pnlDatos.setSize(302,270); 
			pnlDatos.setBorder(BorderFactory.createTitledBorder("Datos"));
			pnlDatos.setBackground(empresa.getOptions().getColor_form());
			pnlDatos.setLayout(null);
			pnlDatos.add(getLblNumero());
			pnlDatos.add(getTxtNumero());
			pnlDatos.add(getLblFecha());
			pnlDatos.add(getTxtFecha());
			pnlDatos.add(getLblDescripcion());
			pnlDatos.add(getTxtDescripcion());
			return pnlDatos;
		}
		
		private JPanel getPnlAcciones(){
			pnlAcciones = new JPanel();
			pnlAcciones.setLocation(0,270);
			pnlAcciones.setSize(302, 95);
			pnlAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
			pnlAcciones.setBackground(empresa.getOptions().getColor_form());
			pnlAcciones.add(getBttAgregar());
			pnlAcciones.add(getBttModificar());
			pnlAcciones.add(getBttBorrar());
			pnlAcciones.add(getBttCancelar());
			return pnlAcciones;
		}
		
		private JLabel getLblNumero(){
			lblNumero = new JLabel("Numero");
			lblNumero.setLocation(20, 30);
			lblNumero.setSize(70, 30);
			lblNumero.setFont(empresa.getOptions().getFontLbl());
			return lblNumero;
		}
		
		private JTextField getTxtNumero(){
			txtNumero = new JTextField();
			txtNumero.setLocation(110, 35);
			txtNumero.setSize(empresa.getOptions().getX_TamTxt() - 50,empresa.getOptions().getY_TamTxt());
			if (movInfo != null){
				txtNumero.setText(new Long(movInfo.getNumero()).toString());
			}else{
				txtNumero.setText(new Long(unSocio.getLastIdMovInformativo()).toString());
			}
			txtNumero.setEnabled(false);
			return txtNumero;
		}
		
		private JLabel getLblFecha(){
			lblFecha = new JLabel("Fecha");
			lblFecha.setLocation(20,60);
			lblFecha.setSize(90,27);
			lblFecha.setFont(empresa.getOptions().getFontLbl());
			return lblFecha;
		}
		
		private JFormattedTextField getTxtFecha(){
			txtFecha = new JFormattedTextField(DateFormat.getDateInstance());
			txtFecha.setLocation(110, 65);
			txtFecha.setSize(empresa.getOptions().getX_TamTxt(), empresa.getOptions().getY_TamTxt());
			if (movInfo != null){
				txtFecha.setText(movInfo.getFecha().getDMA());
			}
			return txtFecha;
		}
		
		private JLabel getLblDescripcion(){
			lblDescripcion = new JLabel("Descripcion");
			lblDescripcion.setLocation(20, 90);
			lblDescripcion.setSize(90, 27);
			lblDescripcion.setFont(empresa.getOptions().getFontLbl());
			return lblDescripcion;
		}
		
		private JTextField getTxtDescripcion(){
			txtDescripcion = new JTextField();
			txtDescripcion.setLocation(110, 95);
			txtDescripcion.setSize(empresa.getOptions().getX_TamTxt() + 80 , empresa.getOptions().getY_TamTxt());
			if (movInfo != null){
				txtDescripcion.setText(movInfo.getDescripcion());
			}
			return txtDescripcion;
		}
		
		private JButton getBttAgregar(){
			bttAgregar = new JButton("Agregar");
			if (movInfo == null){
				bttAgregar.setEnabled(true);
			}else{
				bttAgregar.setEnabled(false);
			}
			bttAgregar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttAgregar_Click(e);
				}
			});
			
			return bttAgregar;
		}
		
		private JButton getBttModificar(){
			bttModificar = new JButton("Modificar");
			if (movInfo == null){
				bttModificar.setEnabled(false);
			}else{
				bttModificar.setEnabled(true);
			}
				bttModificar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttModificar_Click(e);
				}
			});
			
			return bttModificar;
		}
		
		private JButton getBttBorrar(){
			bttBorrar = new JButton("Borrar");
			if (movInfo == null){
				bttBorrar.setEnabled(false);
			}else{
				bttBorrar.setEnabled(true);
			}
			bttBorrar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttBorrar_Click(e);
				}
			});
			
			return bttBorrar;
		}
		
		private JButton getBttCancelar(){
			bttCancelar = new JButton("Cancelar");
			bttCancelar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					bttCancelar_Click(e);
				}
			});
			return bttCancelar;
		}
		
		private void bttAgregar_Click(ActionEvent e){
			MovInformativo movInfo; Fecha_wi fecha = new Fecha_wi();
			DbMovInformativo dbMovInfo = new DbMovInformativo(Empresa.con);
			if (txtDescripcion.getText().length() != 0){
				try{
					if (txtFecha.getText().length() != 0){
						fecha.asignar_fecha_hora(txtFecha.getText(0, 2), txtFecha.getText(3, 2), txtFecha.getText(6,4), "00", "00","00");
					}else{
						fecha.asignar_fecha_hora("00", "00", "00", "00", "00", "00");
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(this,"Error en la Fecha: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
				}
				movInfo = new MovInformativo(new Long(txtNumero.getText()), fecha, txtDescripcion.getText());
				unSocio.addInformacion(movInfo);
				dbMovInfo.Insert(movInfo, unSocio);
				txtDescripcion.setText("");
				principal.llenarMovInformacion(unSocio);
			}else{
				JOptionPane.showMessageDialog(this, "No se puede agregar un Movimiento Informativo sin una descripcion", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		private void bttCancelar_Click(ActionEvent e){
			txtNumero.setText(new Long(unSocio.getLastIdMovInformativo()).toString());
			this.dispose();
		}
		
		private void bttModificar_Click(ActionEvent e){
			MovInformativo movInfo; Fecha_wi fecha = new Fecha_wi();
			DbMovInformativo dbMovInfo = new DbMovInformativo(Empresa.con);
			if (txtDescripcion.getText().length() != 0){
				try{
					if (txtFecha.getText().length() != 0){
						fecha.asignar_fecha_hora(txtFecha.getText(0, 2), txtFecha.getText(3, 2), txtFecha.getText(6,4), "00", "00","00");
					}else{
						fecha.asignar_fecha_hora("00", "00", "00", "00", "00", "00");
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(this,"Error en la Fecha: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
				}
				movInfo = new MovInformativo(new Long(txtNumero.getText()), fecha, txtDescripcion.getText());
				unSocio.remInformacion(movInfo.getNumero());
				unSocio.addInformacion(movInfo);
				dbMovInfo.actualizar(movInfo, unSocio);
				bttCancelar_Click(e);
				principal.llenarMovInformacion(unSocio);
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "No se puede modificar un Movimiento Informativo sin una descripcion", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		private void bttBorrar_Click(ActionEvent e){
			DbMovInformativo dbMovInfo = new DbMovInformativo(Empresa.con);
			unSocio.remInformacion(new Long(txtNumero.getText()));
			dbMovInfo.borrar(movInfo, unSocio);
			txtDescripcion.setText("");
			principal.llenarMovInformacion(unSocio);
			principal.movInfor = null;
			this.dispose();
		}
		
}
