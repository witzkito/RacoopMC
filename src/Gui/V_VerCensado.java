/*
 * V_VerCensado.java
 *
 * Created on 5 de marzo de 2008, 09:21
 */

package Gui;

import Reportes.ReporteSociosCensos;
import base.Empresa;
import base.Socio;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  Usuario
 */
public class V_VerCensado extends javax.swing.JFrame {
    
    private Empresa emp;
    private V_Principal principal;
    
    public V_VerCensado(Empresa emp, V_Principal principal){
        this.emp = emp;
        this.principal = principal;
        initComponents();
        this.setLocationRelativeTo(this.getParent());
     
    }
    /** Creates new form V_VerCensado */
    public V_VerCensado() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdGroupCensos = new javax.swing.ButtonGroup();
        rdGroupConSin = new javax.swing.ButtonGroup();
        pnlBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTemporada = new javax.swing.JTextField();
        Calendar cal = Calendar.getInstance();
        txtTemporada.setText(String.valueOf("Temporada " + (cal.get(Calendar.YEAR))));
        bttArriba = new javax.swing.JButton();
        bttAbajo = new javax.swing.JButton();
        rdCensoPlantacion = new javax.swing.JRadioButton();
        rdCensoEntrega = new javax.swing.JRadioButton();
        rdConCenso = new javax.swing.JRadioButton();
        rdSinCenso = new javax.swing.JRadioButton();
        bttBuscar = new javax.swing.JButton();
        pnlAcciones = new javax.swing.JPanel();
        bttAceptar = new javax.swing.JButton();
        bttListar = new javax.swing.JButton();
        pnlDatos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Vector colNames = new Vector(5);
        colNames.add("Numero");
        colNames.add("Nombre");
        colNames.add("Fecha");
        colNames.add("Orden");
        colNames.add("Total");
        Vector rows = new Vector();
        V_Tabla tabla = new V_Tabla(rows,colNames);
        tblListadoCub = new JTable(tabla);
        lblInformacion = new javax.swing.JLabel();

        rdGroupCensos.add(rdCensoPlantacion);
        rdGroupCensos.add(rdCensoEntrega);

        rdGroupConSin.add(rdConCenso);
        rdGroupConSin.add(rdSinCenso);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Censados");
        setIconImage(this.emp.getOptions().getIcono().getImage());
        setResizable(false);

        pnlBusqueda.setBackground(this.emp.getOptions().getColor_form());
        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0))));

        jLabel1.setFont(this.emp.getOptions().getFontLbl());
        jLabel1.setText("Temporada");

        txtTemporada.setFont(this.emp.getOptions().getFontTxt());
        txtTemporada.setEnabled(false);

        bttArriba.setIcon(new ImageIcon("img/fleArriba.gif"));
        bttArriba.setMinimumSize(new java.awt.Dimension(15, 10));
        bttArriba.setPreferredSize(new java.awt.Dimension(15, 10));
        bttArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttArriba_Click(evt);
            }
        });

        bttAbajo.setIcon(new ImageIcon("img/fleAbajo.gif"));
        bttAbajo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bttAbajo.setMinimumSize(new java.awt.Dimension(15, 10));
        bttAbajo.setPreferredSize(new java.awt.Dimension(15, 10));
        bttAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttAbajo_Click(evt);
            }
        });

        rdCensoPlantacion.setBackground(this.emp.getOptions().getColor_form());
        rdCensoPlantacion.setFont(this.emp.getOptions().getFontLbl());
        rdCensoPlantacion.setText("Censo Plantacion");

        rdCensoEntrega.setBackground(this.emp.getOptions().getColor_form());
        rdCensoEntrega.setFont(this.emp.getOptions().getFontLbl());
        rdCensoEntrega.setText("Censo Entrega");

        rdConCenso.setBackground(this.emp.getOptions().getColor_form());
        rdConCenso.setFont(this.emp.getOptions().getFontLbl());
        rdConCenso.setText("Con Censo");

        rdSinCenso.setBackground(this.emp.getOptions().getColor_form());
        rdSinCenso.setFont(this.emp.getOptions().getFontLbl());
        rdSinCenso.setText("Sin Censo");

        bttBuscar.setIcon(new ImageIcon("img/lupa.png"));
        bttBuscar.setText("Buscar");
        bttBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttBuscar_Click(evt);
            }
        });

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBusquedaLayout.createSequentialGroup()
                        .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdCensoPlantacion)
                                    .addComponent(rdConCenso))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rdSinCenso)
                                    .addComponent(rdCensoEntrega)))
                            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bttAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bttArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBusquedaLayout.createSequentialGroup()
                        .addComponent(bttBuscar)
                        .addGap(157, 157, 157))))
        );
        pnlBusquedaLayout.setVerticalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBusquedaLayout.createSequentialGroup()
                        .addComponent(bttArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(bttAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdCensoPlantacion)
                    .addComponent(rdCensoEntrega))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdConCenso)
                    .addComponent(rdSinCenso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bttBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAcciones.setBackground(emp.getOptions().getColor_form());
        pnlAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));

        bttAceptar.setIcon(new ImageIcon("img/Ok.png"));
        bttAceptar.setText("Aceptar");
        bttAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttAceptar_Click(evt);
            }
        });

        bttListar.setIcon(new ImageIcon("img/listado.png"));
        bttListar.setText("Listar");
        bttListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttListar_Click(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesLayout = new javax.swing.GroupLayout(pnlAcciones);
        pnlAcciones.setLayout(pnlAccionesLayout);
        pnlAccionesLayout.setHorizontalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(bttAceptar)
                .addGap(41, 41, 41)
                .addComponent(bttListar)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        pnlAccionesLayout.setVerticalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addGroup(pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttAceptar)
                    .addComponent(bttListar))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pnlDatos.setBackground(emp.getOptions().getColor_form());
        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));

        TableRender render = new TableRender();
        tblListadoCub.setDefaultRenderer(String.class, render);
        tblListadoCub.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblListadoCub.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblListadoCub.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblListadoCub.getColumnModel().getColumn(3).setPreferredWidth(15);
        tblListadoCub.getColumnModel().getColumn(4).setPreferredWidth(30);
        tblListadoCub.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblListado_keyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblListadoCub);

        lblInformacion.setFont(this.emp.getOptions().getFontLbl());
        lblInformacion.setText("Presione F1 para acceder a un Censo");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addComponent(lblInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInformacion)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttArriba_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttArriba_Click
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        txtTemporada.setText(String.valueOf("Temporada " + (new Integer(temporada) + 1)));
        temporada = txtTemporada.getText().replace("Temporada ", "");
        
    }//GEN-LAST:event_bttArriba_Click

    private void bttAbajo_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttAbajo_Click
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        txtTemporada.setText(String.valueOf("Temporada " + (new Integer(temporada) - 1)));
        temporada = txtTemporada.getText().replace("Temporada ", "");
        
    }//GEN-LAST:event_bttAbajo_Click

    private void bttBuscar_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttBuscar_Click
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        if(this.rdGroupCensos.isSelected(null)){
            JOptionPane.showMessageDialog(this, "Por favor seleccione un Censo", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }else{
            if(this.rdGroupConSin.isSelected(null)){
                JOptionPane.showMessageDialog(this, "Por favor seleccione una Opcion de Listado", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }else{
                if(rdCensoPlantacion.isSelected()){
                    if (rdConCenso.isSelected()){
                        llenarTablaPlantacion(this.emp.getSociosConCensoPlantacion(temporada));
                    }else{
                        llenarTablaPlantacion(this.emp.getSociosSinCensoPlantacion(temporada));
                    }
                }else{
                    if (rdConCenso.isSelected()){
                        llenarTablaCensoEntrega(this.emp.getSociosConCensoEntrega(temporada));
                    }else{
                        llenarTablaCensoEntrega(this.emp.getSociosSinCensoEntrega(temporada));
                    }
                }
                
            }
                
        }
        
}//GEN-LAST:event_bttBuscar_Click

    private void bttAceptar_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttAceptar_Click
        this.dispose();
    }//GEN-LAST:event_bttAceptar_Click

    private void bttListar_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttListar_Click
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        if(this.rdGroupCensos.isSelected(null)){
            JOptionPane.showMessageDialog(this, "Por favor seleccione un Censo, para luego poder listarlo", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }else{
            if(this.rdGroupConSin.isSelected(null)){
                JOptionPane.showMessageDialog(this, "Por favor seleccione una Opcion de Listado, para luego ser listada", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }else{
                if(rdCensoPlantacion.isSelected()){
                    if (rdConCenso.isSelected()){
                        ReporteSociosCensos rep = new ReporteSociosCensos(this.emp,temporada,"Socios con Censo Plantacion", 1);
                        rep.run();
                    }else{
                        ReporteSociosCensos rep = new ReporteSociosCensos(this.emp, temporada, "Socios sin Censo Plantacion", 2);
                        rep.run();
                    }
                }else{
                    if (rdConCenso.isSelected()){
                       ReporteSociosCensos rep = new ReporteSociosCensos(this.emp, temporada, "Socios con Censo Entrega", 3);
                        rep.run();
                    }else{
                       ReporteSociosCensos rep = new ReporteSociosCensos(this.emp, temporada, "Socios sin Censo Entrega", 4);
                        rep.run();
                    }
                }
                
            }
                
        }
}//GEN-LAST:event_bttListar_Click

    private void tblListado_keyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListado_keyPressed
        Socio unSocio;
        if (evt.getKeyCode() == KeyEvent.VK_F1){
            unSocio = this.emp.getSocio((Long)tblListadoCub.getValueAt(
                                        tblListadoCub.getSelectedRow(), 0));
            if(rdCensoPlantacion.isSelected()){
                V_CensoPlantacion v_censo = new V_CensoPlantacion(this.emp, unSocio,txtTemporada.getText(), principal);
                v_censo.setVisible(true);
            }else{
                V_CensoEntrega v_censo = new V_CensoEntrega(this.emp, unSocio,txtTemporada.getText(), principal);
                v_censo.setVisible(true);
          }
        }
    }//GEN-LAST:event_tblListado_keyPressed
 
    private void llenarTablaPlantacion(Map mapa){
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        Map sorted = new TreeMap(mapa); Socio unSocio;
        Iterator it = sorted.values().iterator(); Vector row;
        ((DefaultTableModel)this.tblListadoCub.getModel()).setNumRows(0);
        while (it.hasNext()){
            unSocio = (Socio)it.next();
            row = new Vector();
            row.addElement(unSocio.getNroSocio());
            row.addElement(unSocio.getNomApe());
            if(unSocio.getCensoPlantacion(temporada) != null){
                row.addElement(unSocio.getCensoPlantacion(temporada).getFecha_wi().getDMA());
            }
            row.addElement(" ");
            row.addElement(new String(new Double(unSocio.getTotal1Hec(temporada) + unSocio.getTotal2Hec(temporada)) + " hec"));
            ((DefaultTableModel)this.tblListadoCub.getModel()).addRow(row);
            
        }
    }
    
    private void llenarTablaCensoEntrega(Map mapa){
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        Map sorted = new TreeMap(mapa); Socio unSocio;
        Iterator it = sorted.values().iterator(); Vector row;
        ((DefaultTableModel)this.tblListadoCub.getModel()).setNumRows(0);
        while (it.hasNext()){
            unSocio = (Socio)it.next();
            row = new Vector();
            row.addElement(unSocio.getNroSocio());
            row.addElement(unSocio.getNomApe());
            if (unSocio.getCensoEntrega(temporada) != null){
                row.addElement(unSocio.getCensoEntrega(temporada).getFecha().getDMA());
                row.addElement(unSocio.getCensoEntrega(temporada).getNroCenso());
                row.addElement(new String(new Double(unSocio.getCensoEntrega(temporada).getTotalKg1Año() + 
                        unSocio.getCensoEntrega(temporada).getTotalKg2Año()) + " kg"));
            }else{
                row.addElement(" ");
                row.addElement(" ");
                row.addElement(" ");
            }
            ((DefaultTableModel)this.tblListadoCub.getModel()).addRow(row);
            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new V_VerCensado().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttAbajo;
    private javax.swing.JButton bttAceptar;
    private javax.swing.JButton bttArriba;
    private javax.swing.JButton bttBuscar;
    private javax.swing.JButton bttListar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JRadioButton rdCensoEntrega;
    private javax.swing.JRadioButton rdCensoPlantacion;
    private javax.swing.JRadioButton rdConCenso;
    private javax.swing.ButtonGroup rdGroupCensos;
    private javax.swing.ButtonGroup rdGroupConSin;
    private javax.swing.JRadioButton rdSinCenso;
    private javax.swing.JTable tblListadoCub;
    private javax.swing.JTextField txtTemporada;
    // End of variables declaration//GEN-END:variables
    
}