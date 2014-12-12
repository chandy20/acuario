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
import objetos.PezVO;

/**
 *
 * @author Alex
 */
public class Ficha extends javax.swing.JDialog {

    /**
     * Creates new form Ficha
     */
    AcuarioDAO aDAO = new AcuarioDAO();
    PezVO pVO = new PezVO();

    public Ficha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void cargaNombre(int pez_id) throws SQLException {
        String nombre = aDAO.getPezName(pez_id);
        titulo.setText(nombre);
    }

    public void getBiotopo(int pez_id) throws SQLException{
      ArrayList<PezVO> lista =  aDAO.getDatosGenerales(pez_id, 2);
        for (PezVO pezVO : lista) {
            String biotopo = pezVO.getPez_biotopo();
//            String datos = "<html><body><table><tr><h1><b><td align= 'center'>BIÓTOPO</td></b></h1></tr><tr><h2><td align= 'center'>"+biotopo+"</td></h2></tr></table></body></html>";
            String datos = "<html><body><div align = 'center'><h1><b><p><font size = '40'>BIÓTOPO</font></p></b></h1><br><h2><font size = '30'>"+biotopo+"</font></h2></div></body></html>";
            this.info.setText(datos);
        }
    }
    public void cargaImagenes(int pez_id) throws SQLException {

        ArrayList<String> peces = aDAO.getImagesFromFish(pez_id);
//        long start = System.currentTimeMillis();
//        long aux = start;
//        int x = 0;
//        while (true) {
//            long end = System.currentTimeMillis();
//            long res = end - start;
//            if (aux != end) {
//                aux = end;
//                if (res % 5000 == 0) {
//                    Image foto = getToolkit().getImage(peces.get(x));
        slider.setIcon(new ImageIcon(peces.get(0)));
//                    x++;
//                    if (x == peces.size()) {
//                        x = 0;
//                    }
//                }
//            }
//        }
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
        datos = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        slider = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        visor = new javax.swing.JPanel();
        tittle = new javax.swing.JLabel();
        back1 = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        fondo1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);
        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1920, 1080));

        datos.setLayout(null);

        titulo.setFont(new java.awt.Font("Tahoma", 2, 48)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setAlignmentY(0.0F);
        titulo.setMaximumSize(new java.awt.Dimension(720, 70));
        titulo.setMinimumSize(new java.awt.Dimension(720, 70));
        titulo.setPreferredSize(new java.awt.Dimension(720, 70));
        datos.add(titulo);
        titulo.setBounds(600, 50, 720, 50);

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        back.setAlignmentY(0.0F);
        datos.add(back);
        back.setBounds(600, 50, 720, 50);

        slider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slider.setAlignmentY(0.0F);
        slider.setMaximumSize(new java.awt.Dimension(720, 400));
        slider.setMinimumSize(new java.awt.Dimension(720, 400));
        slider.setPreferredSize(new java.awt.Dimension(720, 400));
        datos.add(slider);
        slider.setBounds(600, 125, 720, 400);

        info.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info.setAlignmentY(0.0F);
        info.setMaximumSize(new java.awt.Dimension(1536, 505));
        info.setMinimumSize(new java.awt.Dimension(1535, 505));
        info.setPreferredSize(new java.awt.Dimension(1535, 505));
        datos.add(info);
        info.setBounds(192, 550, 1535, 505);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acuario2.jpg"))); // NOI18N
        fondo.setText("jLabel1");
        fondo.setMaximumSize(new java.awt.Dimension(1920, 1080));
        fondo.setMinimumSize(new java.awt.Dimension(1920, 1080));
        fondo.setPreferredSize(new java.awt.Dimension(1920, 1080));
        datos.add(fondo);
        fondo.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(datos);
        datos.setBounds(0, 0, 1920, 1080);

        visor.setLayout(null);

        tittle.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        tittle.setForeground(new java.awt.Color(255, 255, 255));
        tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittle.setAlignmentY(0.0F);
        tittle.setMaximumSize(new java.awt.Dimension(720, 70));
        tittle.setMinimumSize(new java.awt.Dimension(720, 70));
        tittle.setPreferredSize(new java.awt.Dimension(720, 70));
        visor.add(tittle);
        tittle.setBounds(600, 50, 720, 70);

        back1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        back1.setAlignmentY(0.0F);
        back1.setMaximumSize(new java.awt.Dimension(720, 70));
        back1.setMinimumSize(new java.awt.Dimension(720, 70));
        back1.setPreferredSize(new java.awt.Dimension(720, 70));
        visor.add(back1);
        back1.setBounds(600, 50, 720, 70);

        foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto.setAlignmentY(0.0F);
        foto.setMaximumSize(new java.awt.Dimension(1500, 700));
        foto.setMinimumSize(new java.awt.Dimension(1500, 700));
        foto.setPreferredSize(new java.awt.Dimension(1500, 700));
        visor.add(foto);
        foto.setBounds(210, 170, 1500, 700);

        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acuario2.jpg"))); // NOI18N
        fondo1.setText("jLabel1");
        fondo1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        fondo1.setMinimumSize(new java.awt.Dimension(1920, 1080));
        fondo1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        visor.add(fondo1);
        fondo1.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(visor);
        visor.setBounds(0, 0, 1920, 1080);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                dialog = new Ficha(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel back;
    private javax.swing.JLabel back1;
    public javax.swing.JPanel datos;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel fondo1;
    public javax.swing.JLabel foto;
    private javax.swing.JLabel info;
    private javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JLabel slider;
    public javax.swing.JLabel tittle;
    private javax.swing.JLabel titulo;
    public javax.swing.JPanel visor;
    // End of variables declaration//GEN-END:variables
}
