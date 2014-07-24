/*
 * V_VerCensado.java
 *
 * Created on 5 de marzo de 2008, 09:21
 */

package Gui;

import Reportes.ReporteTolerancia;
import base.Empresa;
import base.Socio;
import base.Variedad;
import db.DbRepTolerancia;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  Usuario
 */
public class V_Tolerancia extends javax.swing.JFrame {
    
    private Empresa emp;
    private V_Principal principal;
    DecimalFormat format = new DecimalFormat();
    Variedad variedadSeteada;
    private boolean año1 = false;
    private boolean año2 = false;
        
        
    
    public V_Tolerancia(Empresa emp, V_Principal principal){
        this.emp = emp;
        this.principal = principal;
        initComponents();
        this.setLocationRelativeTo(this.getParent());
        llenarVariedades();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
     
    }
    /** Creates new form V_VerCensado */
    public V_Tolerancia() {
        initComponents();
        llenarVariedades();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBusqueda = new javax.swing.JPanel();
        bttBuscar = new javax.swing.JButton();
        rd1Año = new javax.swing.JRadioButton();
        rd2Años = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtTemporada = new javax.swing.JTextField();
        Calendar cal = Calendar.getInstance();
        txtTemporada.setText(String.valueOf("Temporada " + (cal.get(Calendar.YEAR))));
        bttArriba = new javax.swing.JButton();
        bttAbajo = new javax.swing.JButton();
        cmbVariedad = new javax.swing.JComboBox();
        pnlAcciones = new javax.swing.JPanel();
        bttAceptar = new javax.swing.JButton();
        bttListar = new javax.swing.JButton();
        pnlDatos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Vector colNames = new Vector(6);
        colNames.add("Numero");
        colNames.add("Nombre");
        colNames.add("Censo Plantacion");
        colNames.add("Censo Entrega");
        colNames.add("Entrega");
        colNames.add("Tolerancia");
        Vector rows = new Vector();
        V_Tabla tabla = new V_Tabla(rows,colNames);
        tblListadoCub = new JTable(tabla);
        lblInformacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Censo Plantacion - Censo Entrega - Entrega - Tolerancia");
        setIconImage(this.emp.getOptions().getIcono().getImage());
        setResizable(false);

        pnlBusqueda.setBackground(this.emp.getOptions().getColor_form());
        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)))); // NOI18N

        bttBuscar.setIcon(new ImageIcon("img/lupa.png"));
        bttBuscar.setText("Buscar");
        bttBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttBuscar_Click(evt);
            }
        });

        rd1Año.setBackground(this.emp.getOptions().getColor_form());
        rd1Año.setFont(this.emp.getOptions().getFontLbl());
        rd1Año.setText("1 Año");
        rd1Año.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd1Año_Click(evt);
            }
        });

        rd2Años.setBackground(this.emp.getOptions().getColor_form());
        rd2Años.setFont(this.emp.getOptions().getFontLbl());
        rd2Años.setText("2 Año");
        rd2Años.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd2Años_Click(evt);
            }
        });

        jLabel1.setFont(this.emp.getOptions().getFontLbl());
        jLabel1.setText("Temporada");

        txtTemporada.setEditable(false);
        txtTemporada.setFont(this.emp.getOptions().getFontTxt());

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

        cmbVariedad.setFont(this.emp.getOptions().getFontLbl());
        cmbVariedad.setMinimumSize(new java.awt.Dimension(23, 16));
        cmbVariedad.setPreferredSize(new java.awt.Dimension(28, 18));
        cmbVariedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVariedad_Click(evt);
            }
        });

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addComponent(rd1Año, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rd2Años, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cmbVariedad, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(bttBuscar))
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bttAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bttArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(105, Short.MAX_VALUE))
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
                .addGap(9, 9, 9)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd1Año)
                    .addComponent(rd2Años)
                    .addComponent(cmbVariedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(bttBuscar)
                .addContainerGap())
        );

        pnlAcciones.setBackground(emp.getOptions().getColor_form());
        pnlAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0))); // NOI18N

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
                .addGap(160, 160, 160)
                .addComponent(bttAceptar)
                .addGap(41, 41, 41)
                .addComponent(bttListar)
                .addContainerGap(180, Short.MAX_VALUE))
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
        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        TableRender render = new TableRender();
        tblListadoCub.setDefaultRenderer(String.class, render);
        tblListadoCub.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblListadoCub.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblListadoCub.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblListadoCub.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblListadoCub.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblListadoCub.getColumnModel().getColumn(5).setPreferredWidth(40);
        tblListadoCub.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblListado_keyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblListadoCub);

        lblInformacion.setFont(this.emp.getOptions().getFontLbl());

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInformacion))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttBuscar_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttBuscar_Click
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        llenarTabla(this.emp.getMapSocios());
            
        
        
}//GEN-LAST:event_bttBuscar_Click

    private void bttAceptar_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttAceptar_Click
        this.dispose();
    }//GEN-LAST:event_bttAceptar_Click

    private void bttListar_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttListar_Click
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        double totalCensoPlantacion = 0; double totalCensoEntrega =0;
        double totalEntrega = 0;
        DbRepTolerancia db = new DbRepTolerancia(Empresa.con);
        db.borrarAll();
        int cont = this.tblListadoCub.getRowCount();
        while (cont != 0){
            db.Insert((Long)((tblListadoCub.getModel()).getValueAt(cont - 1, 0)),
                    String.valueOf((tblListadoCub.getModel()).getValueAt(cont - 1, 1)),
                    String.valueOf((tblListadoCub.getModel()).getValueAt(cont - 1, 2)),
                    String.valueOf((tblListadoCub.getModel()).getValueAt(cont - 1, 3)),
                    String.valueOf((tblListadoCub.getModel()).getValueAt(cont - 1, 4)),
                    String.valueOf((tblListadoCub.getModel()).getValueAt(cont - 1, 5)));
            cont = cont - 1;
        }
        System.out.println(totalCensoPlantacion);
        System.out.println(totalCensoEntrega);
        System.out.println(totalEntrega);
        ReporteTolerancia repTo = new ReporteTolerancia(this.emp, titulo(),
                temporada);
        repTo.run();
}//GEN-LAST:event_bttListar_Click

    private String titulo(){
        String ciclo = ""; String var = "";
        if (año1 && !año2){
            ciclo = ", Mandioca de 1 Ciclo ";
        }else if (!año1 && año2){
            ciclo = ", Mandioca de 2 Ciclos ";
        }
        if (variedadSeteada != null){
            if (!variedadSeteada.getNombre().equals("Sin Variedad")){
                var = ", " + variedadSeteada.getNombre();
            }
        }
        return "Reporte de Tolerancias" + ciclo + var;
    }
    
    private void tblListado_keyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListado_keyPressed
       /* Socio unSocio;
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
        }*/
    }//GEN-LAST:event_tblListado_keyPressed

    private void rd1Año_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd1Año_Click
        if (rd1Año.isSelected() && rd2Años.isSelected()){
            rd1Año.setSelected(false);
            rd2Años.setSelected(false);
        }
}//GEN-LAST:event_rd1Año_Click

    private void rd2Años_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd2Años_Click
        if (rd1Año.isSelected() && rd2Años.isSelected()){
            rd1Año.setSelected(false);
            rd2Años.setSelected(false);
        }
}//GEN-LAST:event_rd2Años_Click

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

    private void cmbVariedad_Click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVariedad_Click
        
        
}//GEN-LAST:event_cmbVariedad_Click
 
    private void llenarTabla(Map mapa){
        String temporada = txtTemporada.getText().replace("Temporada ", "");
        setearVariables();
        if (rd1Año.isSelected() && !rd2Años.isSelected()){
            if (((Variedad)cmbVariedad.getSelectedItem()).getNombre().equals("Sin Variedad")){
                llenar1Año(temporada);
            }else{
                llenar1Año(temporada, (Variedad)cmbVariedad.getSelectedItem());
            }
        }else if (!rd1Año.isSelected() && rd2Años.isSelected()){
            if (((Variedad)cmbVariedad.getSelectedItem()).getNombre().equals("Sin Variedad")){
                llenar2Años(temporada);
            }else{
                llenar2Años(temporada, (Variedad)cmbVariedad.getSelectedItem());
            }
        }else if (!rd1Año.isSelected() && !rd2Años.isSelected()){
            if (((Variedad)cmbVariedad.getSelectedItem()).getNombre().equals("Sin Variedad")){
                llenarTodo(temporada);
            }else{
                llenarTodo(temporada, (Variedad)cmbVariedad.getSelectedItem());
            }
        }
        
    }
    
    private void setearVariables(){
        if (rd1Año.isSelected() && !rd2Años.isSelected()){
            año1 = true;
            año2 = false;
        }else if (!rd1Año.isSelected() && rd2Años.isSelected()){
            año1 = false;
            año2 = true;
        }
        if (((Variedad)cmbVariedad.getSelectedItem()).getNombre().equals("Sin Variedad")){
            this.variedadSeteada = null;
        }else{
            this.variedadSeteada = (Variedad)cmbVariedad.getSelectedItem();
        }
    }
    
       
    private void llenar1Año(String temporada){
        Map sorted = new TreeMap(this.emp.getMapSocios()); Socio unSocio;
        Iterator it = sorted.values().iterator(); Vector row;
        ((DefaultTableModel)this.tblListadoCub.getModel()).setNumRows(0);
        while (it.hasNext()){
            unSocio = (Socio)it.next();
            if (unSocio.getEstado().equals("activo") && this.agregarFila1Año(temporada, unSocio)){
                row = new Vector();
                row.addElement(unSocio.getNroSocio());
                row.addElement(unSocio.getNomApe());
                if(unSocio.getCensoPlantacion(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoPlantacion(temporada).getTotal1AñoHec()));
                }else{
                    row.addElement(format.format(0));
                }
                if (unSocio.getCensoEntrega(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoEntrega(temporada).getTotalKg1Año()));
                }else{
                    row.addElement(format.format(0));
                }
                row.addElement(format.format(unSocio.getTotalEntrega(temporada, "1 Año")));
                row.addElement(String.valueOf(unSocio.getTolerancia1Año(temporada)) + "%") ;
                ((DefaultTableModel)this.tblListadoCub.getModel()).addRow(row);
            }

        }
    }
    
    private void llenar1Año(String temporada, Variedad var){
        Map sorted = new TreeMap(this.emp.getMapSocios()); Socio unSocio;
        Iterator it = sorted.values().iterator(); Vector row;
        ((DefaultTableModel)this.tblListadoCub.getModel()).setNumRows(0);
        while (it.hasNext()){
            unSocio = (Socio)it.next();
            if (unSocio.getEstado().equals("activo") && this.agregarFila1Año(temporada, unSocio, var)){
                row = new Vector();
                row.addElement(unSocio.getNroSocio());
                row.addElement(unSocio.getNomApe());
                if(unSocio.getCensoPlantacion(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoPlantacion(temporada).getTotal1AñoHec(var)));
                }else{
                    row.addElement(format.format(0));
                }
                if (unSocio.getCensoEntrega(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoEntrega(temporada).getTotalKg1Año(var)));
                }else{
                    row.addElement(format.format(0));
                }
                row.addElement(format.format(unSocio.getTotalEntrega(temporada, "1 Año", var)));
                row.addElement(String.valueOf(unSocio.getTolerancia1Año(temporada, var)) + "%") ;
                ((DefaultTableModel)this.tblListadoCub.getModel()).addRow(row);
            }

        }
    }
    
    private void llenar2Años(String temporada){
        Map sorted = new TreeMap(this.emp.getMapSocios()); Socio unSocio;
        Iterator it = sorted.values().iterator(); Vector row;
        ((DefaultTableModel)this.tblListadoCub.getModel()).setNumRows(0);
        while (it.hasNext()){
            unSocio = (Socio)it.next();
            if (unSocio.getEstado().equals("activo") && this.agregarFila2Año(temporada, unSocio)){
                row = new Vector();
                row.addElement(unSocio.getNroSocio());
                row.addElement(unSocio.getNomApe());
                if(unSocio.getCensoPlantacion(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoPlantacion(temporada).getTotal2AñoHec()));
                }else{
                    row.addElement(format.format(0));
                }
                if (unSocio.getCensoEntrega(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoEntrega(temporada).getTotalKg2Año()));
                }else{
                    row.addElement(format.format(0));
                }
                row.addElement(format.format(unSocio.getTotalEntrega(temporada, "2 Años")));
                row.addElement(String.valueOf(unSocio.getTolerancia2Años(temporada)) + "%") ;
                ((DefaultTableModel)this.tblListadoCub.getModel()).addRow(row);
            }

        }
    }
    
    private void llenar2Años(String temporada, Variedad var){
        Map sorted = new TreeMap(this.emp.getMapSocios()); Socio unSocio;
        Iterator it = sorted.values().iterator(); Vector row;
        ((DefaultTableModel)this.tblListadoCub.getModel()).setNumRows(0);
        while (it.hasNext()){
            unSocio = (Socio)it.next();
            if (unSocio.getEstado().equals("activo") && this.agregarFila2Año(temporada, unSocio, var)){
                row = new Vector();
                row.addElement(unSocio.getNroSocio());
                row.addElement(unSocio.getNomApe());
                if(unSocio.getCensoPlantacion(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoPlantacion(temporada).getTotal2AñoHec(var)));
                }else{
                    row.addElement(format.format(0));
                }
                if (unSocio.getCensoEntrega(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoEntrega(temporada).getTotalKg2Año(var)));
                }else{
                    row.addElement(format.format(0));
                }
                row.addElement(format.format(unSocio.getTotalEntrega(temporada, "2 Años", var)));
                row.addElement(String.valueOf(unSocio.getTolerancia2Años(temporada, var)) + "%") ;
                ((DefaultTableModel)this.tblListadoCub.getModel()).addRow(row);
            }

        }
    }
    
     private void llenarTodo(String temporada){
        Map sorted = new TreeMap(this.emp.getMapSocios()); Socio unSocio;
        Iterator it = sorted.values().iterator(); Vector row;
        ((DefaultTableModel)this.tblListadoCub.getModel()).setNumRows(0);
        while (it.hasNext()){
            unSocio = (Socio)it.next();
            if (unSocio.getEstado().equals("activo") && this.agregarFilaTodo(temporada, unSocio)){
                row = new Vector();
                row.addElement(unSocio.getNroSocio());
                row.addElement(unSocio.getNomApe());
                if(unSocio.getCensoPlantacion(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoPlantacion(temporada).getTotal1AñoHec() +
                            unSocio.getCensoPlantacion(temporada).getTotal2AñoHec()));
                }else{
                    row.addElement(format.format(0));
                }
                if (unSocio.getCensoEntrega(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoEntrega(temporada).getTotalKg1Año() +
                            unSocio.getCensoEntrega(temporada).getTotalKg2Año()));
                }else{
                    row.addElement(format.format(0));
                }
                row.addElement(format.format(unSocio.getTotalEntrega(temporada, "Todos")));
                row.addElement(String.valueOf(unSocio.getTolerancia(temporada)) + "%") ;
                ((DefaultTableModel)this.tblListadoCub.getModel()).addRow(row);
            }

        }
    }
    
    private void llenarTodo(String temporada, Variedad var){
        Map sorted = new TreeMap(this.emp.getMapSocios()); Socio unSocio;
        Iterator it = sorted.values().iterator(); Vector row;
        ((DefaultTableModel)this.tblListadoCub.getModel()).setNumRows(0);
        while (it.hasNext()){
            unSocio = (Socio)it.next();
            if (unSocio.getEstado().equals("activo") && this.agregarFilaTodo(temporada, unSocio, var)){
                row = new Vector();
                row.addElement(unSocio.getNroSocio());
                row.addElement(unSocio.getNomApe());
                if(unSocio.getCensoPlantacion(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoPlantacion(temporada).getTotal1AñoHec(var) +
                            unSocio.getCensoPlantacion(temporada).getTotal2AñoHec(var)));
                }else{
                    row.addElement(format.format(0));
                }
                if (unSocio.getCensoEntrega(temporada) != null){
                    row.addElement(format.format(unSocio.getCensoEntrega(temporada).getTotalKg1Año(var) + 
                            unSocio.getCensoEntrega(temporada).getTotalKg2Año(var)));
                }else{
                    row.addElement(format.format(0));
                }
                row.addElement(format.format(unSocio.getTotalEntrega(temporada, "Todos", var)));
                row.addElement(String.valueOf(unSocio.getTolerancia(temporada, var)) + "%") ;
                ((DefaultTableModel)this.tblListadoCub.getModel()).addRow(row);
            }

        }
    }
    
    /**
     * Funcion que retorna un booleano en caso que se tenga que agregar la fila
     * a la columna, o el socio a la tabla
     * @param temporada String
     * @param so Socio
     * @return boolean si se debe agregar
     */
    private boolean agregarFila1Año(String temporada, Socio so){
        boolean retornar = false;
        if (so.getCensoPlantacion(temporada)!= null && so.getCensoPlantacion(temporada).getTotal1AñoHec() != 0){
            retornar = true;
        }else if (so.getCensoEntrega(temporada) != null && (so.getCensoEntrega(temporada).getKg1año()) != 0){
            retornar = true;
        }else if (so.getTotalEntrega(temporada, "1 Año") != 0){
            retornar = true;
        }
        return retornar;
    }
    
    /**
     * Funcion que retorna un booleano en caso que se tenga que agregar la fila
     * a la columna, o el socio a la tabla
     * @param temporada String
     * @param so Socio
     * @param va Variedad
     * @return boolean si se debe agregar
     */
    private boolean agregarFila1Año(String temporada, Socio so, Variedad va){
        boolean retornar = false;
        if (so.getCensoPlantacion(temporada)!= null && so.getCensoPlantacion(temporada).getTotal1AñoHec(va) != 0){
            retornar = true;
        }else if (so.getCensoEntrega(temporada) != null && (so.getCensoEntrega(temporada).getKg1año(va)) != 0){
            retornar = true;
        }else if (so.getTotalEntrega(temporada, "1 Año", va) != 0){
            retornar = true;
        }
        return retornar;
    }
    
    /**
     * Funcion que retorna un booleano en caso que se tenga que agregar la fila
     * a la columna, o el socio a la tabla
     * @param temporada String
     * @param so Socio
     * @param va Variedad
     * @return boolean si se debe agregar
     */
    private boolean agregarFila2Año(String temporada, Socio so){
        boolean retornar = false;
        if (so.getCensoPlantacion(temporada)!= null && so.getCensoPlantacion(temporada).getTotal2AñoHec() != 0){
            retornar = true;
        }else if (so.getCensoEntrega(temporada) != null && (so.getCensoEntrega(temporada).getKg2año()) != 0){
            retornar = true;
        }else if (so.getTotalEntrega(temporada, "2 Años") != 0){
            retornar = true;
        }
        return retornar;
    }
    
    /**
     * Funcion que retorna un booleano en caso que se tenga que agregar la fila
     * a la columna, o el socio a la tabla
     * @param temporada String
     * @param so Socio
     * @param va Variedad
     * @return boolean si se debe agregar
     */
    private boolean agregarFila2Año(String temporada, Socio so, Variedad va){
        boolean retornar = false;
        if (so.getCensoPlantacion(temporada)!= null && so.getCensoPlantacion(temporada).getTotal2AñoHec(va) != 0){
            retornar = true;
        }else if (so.getCensoEntrega(temporada) != null && (so.getCensoEntrega(temporada).getKg2año(va)) != 0){
            retornar = true;
        }else if (so.getTotalEntrega(temporada, "2 Años",va) != 0){
            retornar = true;
        }
        return retornar;
    }
    
    /**
     * Funcion que retorna un booleano en caso que se tenga que agregar la fila
     * a la columna, o el socio a la tabla
     * @param temporada String
     * @param so Socio
     * @param va Variedad
     * @return boolean si se debe agregar
     */
    private boolean agregarFilaTodo(String temporada, Socio so){
        boolean retornar = false;
        if (so.getCensoPlantacion(temporada)!= null && so.getCensoPlantacion(temporada).getTotalHec() != 0){
            retornar = true;
        }else if (so.getCensoEntrega(temporada) != null && ((so.getCensoEntrega(temporada).getTotalKg1Año()) +
                (so.getCensoEntrega(temporada).getTotalKg2Año()))!= 0){
            retornar = true;
        }else if (so.getTotalEntrega(temporada, "Todos") != 0){
            retornar = true;
        }
        return retornar;
    }
    
     /**
     * Funcion que retorna un booleano en caso que se tenga que agregar la fila
     * a la columna, o el socio a la tabla
     * @param temporada String
     * @param so Socio
     * @param va Variedad
     * @return boolean si se debe agregar
     */
    private boolean agregarFilaTodo(String temporada, Socio so, Variedad va){
        boolean retornar = false;
        if (so.getCensoPlantacion(temporada)!= null && (so.getCensoPlantacion(temporada).getTotal1AñoHec(va) + 
                so.getCensoPlantacion(temporada).getTotal2AñoHec(va))!= 0){
            retornar = true;
        }else if (so.getCensoEntrega(temporada) != null && ((so.getCensoEntrega(temporada).getTotalKg1Año(va)) +
                (so.getCensoEntrega(temporada).getTotalKg2Año(va)))!= 0){
            retornar = true;
        }else if (so.getTotalEntrega(temporada, "Todos", va) != 0){
            retornar = true;
        }
        return retornar;
    }
    
    
    private void llenarVariedades(){
        Iterator it = this.emp.getConf().getAllVariedades().values().iterator();
        Variedad var;
        this.cmbVariedad.addItem(new Variedad(0,"Sin Variedad",0,0));
        while (it.hasNext()){
            var = (Variedad)it.next();
            this.cmbVariedad.addItem(var);
        }
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new V_Tolerancia().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttAbajo;
    private javax.swing.JButton bttAceptar;
    private javax.swing.JButton bttArriba;
    private javax.swing.JButton bttBuscar;
    private javax.swing.JButton bttListar;
    private javax.swing.JComboBox cmbVariedad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JRadioButton rd1Año;
    private javax.swing.JRadioButton rd2Años;
    private javax.swing.JTable tblListadoCub;
    private javax.swing.JTextField txtTemporada;
    // End of variables declaration//GEN-END:variables
    
}
