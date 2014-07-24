/*
 * V_About.java
 *
 * Created on 8 de mayo de 2008, 20:17
 */

package Gui;

import base.Empresa;
import javax.swing.ImageIcon;

/**
 *
 * @author  Witzkito
 */
public class V_About extends javax.swing.JFrame {
    
    private Empresa empresa;
    
    
    public V_About(Empresa emp){
           this.empresa = emp;
           initComponents();
           this.setLocationRelativeTo(this.getParent());
    }
    
    /** Creates new form V_About */
    public V_About() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTodo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca de...");
        setBackground(this.empresa.getOptions().getColor_form());
        setIconImage(this.empresa.getOptions().getIcono().getImage());
        setResizable(false);

        lblNombre.setFont(new java.awt.Font("Gill Sans MT", 1, 16));
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText(this.empresa.getOptions().getVersion());
        lblNombre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new ImageIcon("img/Wit.png"));

        txtTodo.setBackground(this.empresa.getOptions().getColor_form());
        txtTodo.setColumns(20);
        txtTodo.setEditable(false);
        txtTodo.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtTodo.setRows(5);
        txtTodo.setText("Contacto:\n\tTel: 03752-15375700\n                   Mail: Witzkito@gmail.com\n\nAnalizado y Desarrollado por:\n\t  - Witzke Fernando Adrian\n\nAgradecimietos:\n           - Witzke Fernando A. (Soporte, Confianza y paciencia)\n           - Strieder Ricardo (Ideas y soporte)\n           - Gallero Ignacio (imagen de Inicio)\n           - JasperSoft Corp. (Reportes)\n           - Kai Toedter (JCalendar)\n\t                    Muchas Gracias\nProyecto Iniciado en: Junio 2007");
        jScrollPane1.setViewportView(txtTodo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new V_About().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextArea txtTodo;
    // End of variables declaration//GEN-END:variables
    
}
