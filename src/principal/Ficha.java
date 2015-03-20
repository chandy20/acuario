/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import DAO.AcuarioDAO;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.media.Time;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import objetos.PezVO;

/**
 *
 * @author Alex
 */
public class Ficha extends javax.swing.JDialog {

    /**
     * Creates new form Ficha
     */
    public Player player;
    public Component video;
    public Component controles;
    public Player player1;
    public Component video1;
    public Component controles1;
    public Player player2;
    public Component video2;
    public Component controles2;
    public Time cero;
    public Time cero1;
    boolean verVideo = false;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final int velocidad = 5000;//en milisegundos
    AcuarioDAO aDAO = new AcuarioDAO();
    PezVO pVO = new PezVO();
    int x = 0;
    double t = 0, tiempo = 0, tiempogeneral = 0;
    Manager f1, f2, f3;
    private String aux08032015 = "";
//    int principal = 0;
    ArrayList<PezVO> lista = new ArrayList<PezVO>();

    public Ficha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        hiloVideoPrincipal.start();
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
            String datos = "<html><body><table><tr><td>"
                    + nombreComun
                    + "</td></tr><tr><td>&nbsp;</td></tr><tr><td>"
                    + nombreCientifico
                    + "</td></tr></table></body></html>";

            try {
                String text = "Hello World";
                AffineTransform affinetransform = new AffineTransform();
                FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
                Font font = new Font("Harabara", Font.PLAIN, 60);
                int textwidth = (int) (font.getStringBounds(nombreComun, frc).getWidth());
                int textheight = (int) (font.getStringBounds(nombreComun, frc).getHeight());
                VideoInfo(textwidth);

            } catch (Exception e) {
                System.out.println("widht: ");
            }
            this.titulito.setText("Nombre Común & Nombre Científico");
            this.info.setText(datos);
        }

    }

    public void getDistribucion(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 3);
    }

    public void getForma(int pez_id) throws SQLException {
        ArrayList<PezVO> lista1 = aDAO.getDatosGenerales(pez_id, 10);
        getDistribucion(pez_id);
        for (PezVO pezVO : lista1) {
            String general = pezVO.getPez_generalidades();
            String distribucion = pezVO.getPez_distribucion();
            if (pezVO.getPez_generalidades() == null || general.equals("")) {
                general = "No especificadas";
            }
            if (pezVO.getPez_distribucion() == null || distribucion.equals("")) {
                distribucion = "No especificado";
            }
            String datos = "<html><body><tr><td>"
                    + general
                    + "</td></tr><tr><td>&nbsp;</td></tr><tr><td>Yo vivo en...</td></tr><tr><td>"
                    + distribucion
                    + "</td></tr></table></body></html>";
            this.titulito.setText("Información General");
            this.info.setText(datos);
        }
    }

    public void getAlimentacion(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 9);
        for (PezVO pezVO : lista) {
            String alimentos = pezVO.getPez_alimentacion();
            if (pezVO.getPez_alimentacion() == null || alimentos.equals("")) {
                alimentos = "No especificada";
            }
            String datos = "<html><body><tr><td>"
                    + alimentos
                    + "</td></tr></table></body></html>";
            this.titulito.setText("Mi comida favorita es...");
            this.info.setText(datos);
        }
    }

    public void getComportamiento(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 10);
        for (PezVO pezVO : lista) {
            String curiosidades = pezVO.getPez_curiosidades();
            if (pezVO.getPez_curiosidades() == null || curiosidades.equals("")) {
                curiosidades = "No especificadas";
            }
            String datos = "<html><body><tr><td>"
                    + curiosidades
                    + "</td></tr></table></body></html>";
            this.titulito.setText("Mis Curiosidades");
            this.info.setText(datos);
        }
    }

    public void VideoPrincipal(String direccion) {
//        JPanel panel = new JPanel();
//        videoPane.setLayout(new BorderLayout());
        videoPane.setSize(1920, 1080);

//        videoPane.setLocation(0, 0);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        URL url = null;
        try {
            url = new URL(direccion);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
//            System.out.println("url: " + new MediaLocator(url));
            player = f1.createRealizedPlayer(new MediaLocator(url));
//            System.out.println("player: " + player);
            video = player.getVisualComponent();
//            System.out.println("video: " + video);
            video.setSize(1920, 1080);
//            video.setLocation(0, 540);
            video.setVisible(true);
            if (video != null) {
                videoPane.add("Center", video);
            }
//            video.repaint();
            controles = player.getControlPanelComponent();
            player.start();
            cero = player.getMediaTime();
            videoPane.updateUI();
            tiempo = 0;
            verVideo = true;
            System.out.println("time secunds "+player.getDuration().getSeconds());
        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VideoInfo(String direccion) {
        aux08032015 = direccion;
//        File directorio = new File(direccion);
//        System.out.println("archivo " + directorio.exists());
//        if (!directorio.exists()) {
//            direccion = "file:///c:/acuario/defoult.mpg";
//        } else {
        direccion = "file:///" + direccion;
//        }

        videoPeces.setSize(1920, 1080);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        URL url = null;
        try {
            url = new URL(direccion);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            player1 = f2.createRealizedPlayer(new MediaLocator(url));
            System.out.println("player: " + player1);
            video1 = player1.getVisualComponent();
            video1.setSize(1920, 540);
            video1.setLocation(0, 540);
            video1.setVisible(true);

            if (video1 != null) {
                videoPeces.add("Center", video1);
            }
            videoPeces.add(titulo);
            videoPeces.add(info);
            videoPeces.add(barra);
            videoPeces.add(titulito);
            videoPeces.add(fondo);
            tiempogeneral = player1.getDuration().getSeconds() + (0.8);
            controles1 = player1.getControlPanelComponent();
        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VideoDetalle(String direccion) {
        aux08032015 = direccion;
//        File directorio = new File(direccion);
//        System.out.println("archivo " + directorio.exists());
//        direccion = "file:///" + direccion;

        videoDetalle.setSize(1920, 1080);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        URL url = null;
        try {
            url = new URL(direccion);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            player2 = f3.createRealizedPlayer(new MediaLocator(url));
            System.out.println("player: " + player1);
            video2 = player2.getVisualComponent();
            video2.setSize(1920, 1080);
            video2.setLocation(0, 0);
            video2.setVisible(true);

            if (video2 != null) {
                videoDetalle.add("Center", video2);
            }
//            tiempogeneral = player2.getDuration().getSeconds() + (0.8);
            controles2 = player2.getControlPanelComponent();

            player2.start();
            videoDetalle.updateUI();
        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VideoInfo(int width) {
        String direccion = aux08032015;
        File directorio = new File(direccion);
        System.out.println("archivo " + directorio.exists());
//        if (!directorio.exists()) {
//            direccion = "file:///c:/acuario/defoult.mpg";
//        } else {
        direccion = "file:///" + direccion;
//        }

        videoPeces.setSize(1920, 1080);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        URL url = null;
        try {
            url = new URL(direccion);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            player1 = f2.createRealizedPlayer(new MediaLocator(url));
            System.out.println("player: " + player1);
            video1 = player1.getVisualComponent();
            video1.setSize(1920, 540);
            video1.setLocation(0, 540);
            video1.setVisible(true);

            if (video1 != null) {
                videoPeces.add("Center", video1);
            }
            videoPeces.add(titulo);
            videoPeces.add(info);
            JLabel lbl1 = new JLabel("");
            lbl1.setBounds(20, 70, width, 10);
            lbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/linea_titulo.jpg"))); // NOI18N
            videoPeces.add(lbl1);
            videoPeces.add(fondo);
            tiempogeneral = player1.getDuration().getSeconds() + (0.8);
            controles1 = player1.getControlPanelComponent();
        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reproducirPrincipal() {
//        player.setMediaTime(cero);
        player.start();
        videoPane.updateUI();
        tiempo = 0;
    }

    public void reproducir() {
//        player1.setMediaTime(cero);
        player1.start();
        videoPeces.updateUI();
        t = 0;
    }

    Inicio init = new Inicio();

//    Thread hiloVideoPrincipal = new Thread() {//declaramos el hilo
//
//        @Override
//        public void run() {
//            try {
//                
////                reproducir();
////                int sw = 0;
////                System.out.println("Time.TIME_UNKNOWN: ");
//                while (true) {//ciclo infinito
//                    
//                    hiloVideoPrincipal.sleep(1000);//que duerma un segundo
//                }
//            } catch (java.lang.InterruptedException ie) {
//                System.out.println(ie.getMessage());
//            }
//        }
//    };
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
        titulito = new javax.swing.JLabel();
        barra = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        videoPane = new javax.swing.JPanel();
        videoPeces = new javax.swing.JPanel();
        videoDetalle = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);
        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1920, 1080));

        datos.setAlignmentX(0.0F);
        datos.setAlignmentY(0.0F);
        datos.setLayout(null);

        titulo.setFont(new java.awt.Font("Harabara", 0, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo.setAlignmentY(0.0F);
        titulo.setMaximumSize(new java.awt.Dimension(720, 95));
        titulo.setMinimumSize(new java.awt.Dimension(720, 95));
        titulo.setPreferredSize(new java.awt.Dimension(720, 95));
        datos.add(titulo);
        titulo.setBounds(20, 20, 720, 50);

        titulito.setFont(new java.awt.Font("Bitter", 0, 40)); // NOI18N
        titulito.setForeground(new java.awt.Color(255, 247, 152));
        titulito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulito.setToolTipText("");
        titulito.setAlignmentY(0.0F);
        titulito.setMaximumSize(new java.awt.Dimension(1360, 95));
        titulito.setMinimumSize(new java.awt.Dimension(1360, 95));
        titulito.setPreferredSize(new java.awt.Dimension(1360, 95));
        datos.add(titulito);
        titulito.setBounds(20, 110, 1360, 40);

        barra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        barra.setAlignmentY(0.0F);
        datos.add(barra);
        barra.setBounds(20, 80, 720, 15);

        info.setFont(new java.awt.Font("Gandhi Sans", 0, 35)); // NOI18N
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        info.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        info.setAlignmentY(0.0F);
        info.setMaximumSize(new java.awt.Dimension(1536, 505));
        info.setMinimumSize(new java.awt.Dimension(1535, 505));
        info.setPreferredSize(new java.awt.Dimension(1535, 505));
        datos.add(info);
        info.setBounds(20, 170, 1360, 360);

        fondo.setBackground(new java.awt.Color(204, 204, 0));
        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoSegundaDatos.jpg")); // NOI18N
        fondo.setAlignmentY(0.0F);
        datos.add(fondo);
        fondo.setBounds(0, 0, 1920, 1080);
        datos.add(jScrollPane1);
        jScrollPane1.setBounds(1220, 440, 2, 2);

        jLayeredPane1.add(datos);
        datos.setBounds(0, 0, 1920, 540);

        videoPane.setAlignmentX(0.0F);
        videoPane.setAlignmentY(0.0F);
        videoPane.setMaximumSize(new java.awt.Dimension(1920, 1080));
        videoPane.setMinimumSize(new java.awt.Dimension(1920, 1080));
        videoPane.setLayout(null);
        jLayeredPane1.add(videoPane);
        videoPane.setBounds(0, 0, 1920, 1080);

        videoPeces.setAlignmentX(0.0F);
        videoPeces.setAlignmentY(0.0F);
        videoPeces.setMaximumSize(new java.awt.Dimension(1920, 1080));
        videoPeces.setMinimumSize(new java.awt.Dimension(1920, 1080));
        videoPeces.setLayout(null);
        jLayeredPane1.add(videoPeces);
        videoPeces.setBounds(0, 0, 1920, 540);

        javax.swing.GroupLayout videoDetalleLayout = new javax.swing.GroupLayout(videoDetalle);
        videoDetalle.setLayout(videoDetalleLayout);
        videoDetalleLayout.setHorizontalGroup(
            videoDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
        );
        videoDetalleLayout.setVerticalGroup(
            videoDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );

        jLayeredPane1.add(videoDetalle);
        videoDetalle.setBounds(0, 0, 1920, 1080);

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
    private javax.swing.JLabel barra;
    public javax.swing.JPanel datos;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel info;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel titulito;
    private javax.swing.JLabel titulo;
    public javax.swing.JPanel videoDetalle;
    public javax.swing.JPanel videoPane;
    public javax.swing.JPanel videoPeces;
    // End of variables declaration//GEN-END:variables

}
