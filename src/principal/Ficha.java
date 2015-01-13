/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import DAO.AcuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final int velocidad = 5000;//en milisegundos
    AcuarioDAO aDAO = new AcuarioDAO();
    PezVO pVO = new PezVO();
    int x = 0;
    ArrayList<PezVO> lista = new ArrayList<PezVO>();

    public Ficha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void cargaNombre(int pez_id) throws SQLException {
        String nombre = aDAO.getPezName(pez_id);
        titulo.setText(nombre);
    }

    public void getNombres(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 1);
        for (PezVO pezVO : lista) {
            String nombreComun = pezVO.getPez_nombComun();
            String nombreCientifico = pezVO.getPez_nombCientifico();           
            String datos = "<html><body><table><tr><td width='800px' align='center' style='font-size:50px'><b>NOMBRE COMÚN</b></td>"
                    + "<td width='835px' align='center' style='font-size:50px'><b>NOMBRE CIENTÍFICO</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + nombreComun + "</td>"
                    + "<td align='center' style='font-size:40px'>" + nombreCientifico + "</td></tr></table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getClasificacion(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 1);
        for (PezVO pezVO : lista) {
            String orden = pezVO.getPez_coloracion();
            String familia = pezVO.getPez_alimentacion();
            String subfamilia = pezVO.getPez_biotopo();
            String datos = "<html><body><table><tr><td colspan=3 align='center' style='font-size:70px'><b>CLASICACIÓN</b></td></tr>"
                    + "<tr><td width='545px' align='center' style='font-size:50px'><b>ORDEN</b></td>"
                    + "<td width='545px' align='center' style='font-size:50px'><b>FAMILIA</b></td>"
                    + "<td width='545px' align='center' style='font-size:50px'><b>SUBFAMILIA  </b></td></tr>"
                    + "<tr><td align='center' style='font-size:50px'>" + orden + "</td>"
                    + "<td align='center' style='font-size:50px'>" + familia + "</td>"
                    + "<td align='center' style='font-size:50px'>" + subfamilia + "</td></tr></table></body></html>";
            this.info.setText(datos);
        }

    }

    public void getBiotopo(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 2);
        for (PezVO pezVO : lista) {
            String biotopo = pezVO.getPez_biotopo();
            if (pezVO.getPez_biotopo() == null || biotopo.equals("")) {
                biotopo = "NO ESPECIFICADO";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>BIÓTOPO</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + biotopo + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getDistribucion(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 3);
        for (PezVO pezVO : lista) {
            String distribucion = pezVO.getPez_distribucion();
            if (pezVO.getPez_distribucion() == null || pezVO.getPez_distribucion().equals("")) {
                distribucion = "NO ESPECIFICADA";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>DISTRIBUCIÓN</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + distribucion + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getForma(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 11);
        for (PezVO pezVO : lista) {
            String forma = pezVO.getPez_forma();
            if (pezVO.getPez_forma() == null || forma.equals("")) {
                forma = "NO ESPECIFICADA";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>FORMA</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + forma + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getColoracion(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 4);
        for (PezVO pezVO : lista) {
            String coloracion = pezVO.getPez_coloracion();
            if (pezVO.getPez_coloracion() == null || coloracion.equals("")) {
                coloracion = "NO ESPECIFICADA";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>COLORACIÓN</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + coloracion + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getTamano(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 5);
        for (PezVO pezVO : lista) {
            String tamano = pezVO.getPez_tamano();
            if (pezVO.getPez_tamano() == null || tamano.equals("")) {
                tamano = "NO ESPECIFICADO";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>TAMAÑO</b></td></tr>"
                    + "<tr><td align='center' style='font-size:50px'>" + tamano + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getTempreratura(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 6);
        for (PezVO pezVO : lista) {
            String temperatura = pezVO.getPez_tempreatura();
            if (pezVO.getPez_tempreatura() == null || temperatura.equals("")) {
                temperatura = "NO ESPECIFICADA";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>TEMPERATURA</b></td></tr>"
                    + "<tr><td align='center' style='font-size:50px'>" + temperatura + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getAgua(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 7);
        for (PezVO pezVO : lista) {
            String agua = pezVO.getPez_agua();
            if (pezVO.getPez_agua() == null || agua.equals("")) {
                agua = "NO ESPECIFICADA";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>AGUA</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + agua + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getAcuario(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 8);
        for (PezVO pezVO : lista) {
            String acuario = pezVO.getPez_acuario();
            if (pezVO.getPez_acuario() == null || acuario.equals("")) {
                acuario = "NO ESPECIFICADO";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>ACUARIO</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + acuario + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getAlimentacion(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 9);
        for (PezVO pezVO : lista) {
            String alimentacion = pezVO.getPez_alimentacion();
            if (pezVO.getPez_alimentacion() == null || alimentacion.equals("")) {
                alimentacion = "NO ESPECIFICADA";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>ALIMENTACION</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + alimentacion + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getComportamiento(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 10);
        for (PezVO pezVO : lista) {
            String comportamiento = pezVO.getPez_comportamiento();
            if (pezVO.getPez_comportamiento() == null || comportamiento.equals("")) {
                comportamiento = "NO ESPECIFICADO";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>COMPORTAMIENTO</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + comportamiento + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void cargaImagenes(int pez_id) throws SQLException {
        ArrayList<String> peces = aDAO.getImagesFromFish(pez_id);
        for (String ruta : peces) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(
                    new Runnable() {
                        public void run() {
                            System.out.println("equis: " + x);
                            x++;
                            slider.setIcon(new ImageIcon(ruta));
                        }
                    }, 0, velocidad, TimeUnit.MILLISECONDS);
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
        inicial = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        finicial = new javax.swing.JLabel();
        visor = new javax.swing.JPanel();
        tittle = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        fondo1 = new javax.swing.JLabel();
        datos = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        slider = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);
        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1920, 1080));

        inicial.setAlignmentX(0.0F);
        inicial.setAlignmentY(0.0F);
        inicial.setMaximumSize(new java.awt.Dimension(1920, 1080));
        inicial.setMinimumSize(new java.awt.Dimension(1920, 1080));
        inicial.setPreferredSize(new java.awt.Dimension(1920, 1080));
        inicial.setLayout(null);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mundoIV.png"))); // NOI18N
        jLabel2.setAlignmentY(0.0F);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        inicial.add(jLabel2);
        jLabel2.setBounds(560, 200, 800, 560);

        finicial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        finicial.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoSegunda.jpg")); // NOI18N
        finicial.setAlignmentY(0.0F);
        inicial.add(finicial);
        finicial.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(inicial);
        inicial.setBounds(0, 0, 1920, 1080);

        visor.setLayout(null);

        tittle.setFont(new java.awt.Font("BoyzRGross", 0, 80)); // NOI18N
        tittle.setForeground(new java.awt.Color(255, 255, 255));
        tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittle.setToolTipText("");
        tittle.setAlignmentY(0.0F);
        tittle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tittle.setMaximumSize(new java.awt.Dimension(720, 95));
        tittle.setMinimumSize(new java.awt.Dimension(720, 95));
        tittle.setPreferredSize(new java.awt.Dimension(720, 95));
        visor.add(tittle);
        tittle.setBounds(600, 130, 720, 95);

        foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto.setAlignmentY(0.0F);
        foto.setMaximumSize(new java.awt.Dimension(1500, 700));
        foto.setMinimumSize(new java.awt.Dimension(1500, 700));
        foto.setPreferredSize(new java.awt.Dimension(1500, 700));
        visor.add(foto);
        foto.setBounds(210, 258, 1500, 700);

        fondo1.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoSegunda.jpg")); // NOI18N
        fondo1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        fondo1.setMinimumSize(new java.awt.Dimension(1920, 1080));
        fondo1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        visor.add(fondo1);
        fondo1.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(visor);
        visor.setBounds(0, 0, 1920, 1080);

        datos.setLayout(null);

        titulo.setFont(new java.awt.Font("BoyzRGross", 0, 80)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setAlignmentY(0.0F);
        titulo.setMaximumSize(new java.awt.Dimension(720, 95));
        titulo.setMinimumSize(new java.awt.Dimension(720, 95));
        titulo.setPreferredSize(new java.awt.Dimension(720, 95));
        datos.add(titulo);
        titulo.setBounds(600, 25, 720, 95);

        slider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slider.setAlignmentY(0.0F);
        slider.setMaximumSize(new java.awt.Dimension(720, 400));
        slider.setMinimumSize(new java.awt.Dimension(720, 400));
        slider.setPreferredSize(new java.awt.Dimension(720, 400));
        datos.add(slider);
        slider.setBounds(600, 135, 720, 400);

        info.setFont(new java.awt.Font("BoyzRGross", 0, 70)); // NOI18N
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info.setAlignmentY(0.0F);
        info.setMaximumSize(new java.awt.Dimension(1536, 505));
        info.setMinimumSize(new java.awt.Dimension(1535, 505));
        info.setPreferredSize(new java.awt.Dimension(1535, 505));
        datos.add(info);
        info.setBounds(140, 550, 1635, 505);

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoSegunda.jpg")); // NOI18N
        fondo.setMaximumSize(new java.awt.Dimension(1920, 1080));
        fondo.setMinimumSize(new java.awt.Dimension(1920, 1080));
        fondo.setPreferredSize(new java.awt.Dimension(1920, 1080));
        datos.add(fondo);
        fondo.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(datos);
        datos.setBounds(0, 0, 1920, 1080);

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
    public javax.swing.JPanel datos;
    private javax.swing.JLabel finicial;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel fondo1;
    public javax.swing.JLabel foto;
    private javax.swing.JLabel info;
    public javax.swing.JPanel inicial;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JLabel slider;
    public javax.swing.JLabel tittle;
    private javax.swing.JLabel titulo;
    public javax.swing.JPanel visor;
    // End of variables declaration//GEN-END:variables
}
