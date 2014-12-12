/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import DAO.AcuarioDAO;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Alex
 */
public class Ficha extends javax.swing.JDialog {

    /**
     * Creates new form Ficha
     */
    public Ficha(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        AcuarioDAO aDAO = new AcuarioDAO();
        ArrayList<String> peces = aDAO.getImagesFromFish(1);
        aDAO.temporizador(peces);
        System.out.println("sadasd");
        initComponents();
        long start = System.currentTimeMillis();
        long aux = start;
        int x = 0;
        while (true) {
            long end = System.currentTimeMillis();
            long res = end - start;
            if (aux != end) {
                aux = end;
                if (res % 5000 == 0) {
                    Image foto = getToolkit().getImage(peces.get(x));
                    slider.setIcon(new ImageIcon(foto));
                    x++;
                    if (x == peces.size() - 1) {
                        x = 0;
                    }
                }
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        slider = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);
        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1920, 1080));
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        slider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slider.setMaximumSize(new java.awt.Dimension(800, 600));
        slider.setMinimumSize(new java.awt.Dimension(800, 600));
        slider.setPreferredSize(new java.awt.Dimension(800, 600));
        jLayeredPane1.add(slider);
        slider.setBounds(560, 40, 800, 600);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acuario2.jpg"))); // NOI18N
        fondo.setText("jLabel1");
        fondo.setMaximumSize(new java.awt.Dimension(1920, 1080));
        fondo.setMinimumSize(new java.awt.Dimension(1920, 1080));
        fondo.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jLayeredPane1.add(fondo);
        fondo.setBounds(0, 0, 1920, 1080);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ficha.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ficha.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ficha.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ficha.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ficha dialog = null;
                try {
                    dialog = new Ficha(new javax.swing.JFrame(), true);

                } catch (SQLException ex) {
                    Logger.getLogger(Ficha.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JLabel slider;
    // End of variables declaration//GEN-END:variables
}