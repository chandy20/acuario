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
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.media.Time;
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
    public Player player;
    public Component video;
    public Component controles;
    public Player player1;
    public Component video1;
    public Component controles1;
    public Time cero;
    public Time cero1;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final int velocidad = 5000;//en milisegundos
    AcuarioDAO aDAO = new AcuarioDAO();
    PezVO pVO = new PezVO();
    int x = 0;
    double t = 0, tiempo = 0, tiempogeneral=0;
    Manager f1, f2;
//    int principal = 0;
    ArrayList<PezVO> lista = new ArrayList<PezVO>();

    public Ficha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        hiloVideoPrincipal.start();
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

//    public void getClasificacion(int pez_id) throws SQLException {
//        lista = aDAO.getDatosGenerales(pez_id, 1);
//        for (PezVO pezVO : lista) {
//            String orden = pezVO.getPez_coloracion();
//            String familia = pezVO.getPez_alimentacion();
//            String subfamilia = pezVO.getPez_biotopo();
//            String datos = "<html><body><table><tr><td colspan=3 align='center' style='font-size:70px'><b>CLASICACIÓN</b></td></tr>"
//                    + "<tr><td width='545px' align='center' style='font-size:50px'><b>ORDEN</b></td>"
//                    + "<td width='545px' align='center' style='font-size:50px'><b>FAMILIA</b></td>"
//                    + "<td width='545px' align='center' style='font-size:50px'><b>SUBFAMILIA  </b></td></tr>"
//                    + "<tr><td align='center' style='font-size:50px'>" + orden + "</td>"
//                    + "<td align='center' style='font-size:50px'>" + familia + "</td>"
//                    + "<td align='center' style='font-size:50px'>" + subfamilia + "</td></tr></table></body></html>";
//            this.info.setText(datos);
//        }
//    }

//    public void getBiotopo(int pez_id) throws SQLException {
//        lista = aDAO.getDatosGenerales(pez_id, 2);
//        for (PezVO pezVO : lista) {
//            String biotopo = pezVO.getPez_biotopo();
//            if (pezVO.getPez_biotopo() == null || biotopo.equals("")) {
//                biotopo = "NO ESPECIFICADO";
//            }
//            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>BIÓTOPO</b></td></tr>"
//                    + "<tr><td align='center' style='font-size:40px'>" + biotopo + "</td></tr><table></body></html>";
//            this.info.setText(datos);
//        }
//    }

    public void getDistribucion(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 3);
        for (PezVO pezVO : lista) {
            String distribucion = pezVO.getPez_distribucion();
            if (pezVO.getPez_distribucion() == null || pezVO.getPez_distribucion().equals("")) {
                distribucion = "NO ESPECIFICADA";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>ME ENCUENTRO EN</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + distribucion + "</td></tr><table></body></html>";
            this.info.setText(datos);
        }
    }

    public void getForma(int pez_id) throws SQLException {
        ArrayList<PezVO> lista1 = aDAO.getDatosGenerales(pez_id, 11);
        getAlimentacion(pez_id);
        for (PezVO pezVO : lista1) {
            String forma = pezVO.getPez_curiosidades();
            String alimentacion = pezVO.getPez_alimentacion();
            if (pezVO.getPez_curiosidades()== null || forma.equals("")) {
                forma = "NO ESPECIFICADA";
            }
            if (pezVO.getPez_alimentacion()== null || alimentacion.equals("")) {
                alimentacion = "NO ESPECIFICADA";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>MIS CURIOSIDADES</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + forma + "</td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>YO COMO </td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + alimentacion + "</td></tr></table></body></html>";       
            this.info.setText(datos);
        }
    }

//    public void getColoracion(int pez_id) throws SQLException {
//        lista = aDAO.getDatosGenerales(pez_id, 4);
//        for (PezVO pezVO : lista) {
//            String coloracion = pezVO.getPez_coloracion();
//            if (pezVO.getPez_coloracion() == null || coloracion.equals("")) {
//                coloracion = "NO ESPECIFICADA";
//            }
//            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>COLORACIÓN</b></td></tr>"
//                    + "<tr><td align='center' style='font-size:40px'>" + coloracion + "</td></tr><table></body></html>";
//            this.info.setText(datos);
//        }
//    }

//    public void getTamano(int pez_id) throws SQLException {
//        lista = aDAO.getDatosGenerales(pez_id, 5);
//        for (PezVO pezVO : lista) {
//            String tamano = pezVO.getPez_tamano();
//            if (pezVO.getPez_tamano() == null || tamano.equals("")) {
//                tamano = "NO ESPECIFICADO";
//            }
//            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>TAMAÑO</b></td></tr>"
//                    + "<tr><td align='center' style='font-size:50px'>" + tamano + "</td></tr><table></body></html>";
//            this.info.setText(datos);
//        }
//    }

//    public void getTempreratura(int pez_id) throws SQLException {
//        lista = aDAO.getDatosGenerales(pez_id, 6);
//        for (PezVO pezVO : lista) {
//            String temperatura = pezVO.getPez_tempreatura();
//            if (pezVO.getPez_tempreatura() == null || temperatura.equals("")) {
//                temperatura = "NO ESPECIFICADA";
//            }
//            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>TEMPERATURA</b></td></tr>"
//                    + "<tr><td align='center' style='font-size:50px'>" + temperatura + "</td></tr><table></body></html>";
//            this.info.setText(datos);
//        }
//    }

//    public void getAgua(int pez_id) throws SQLException {
//        lista = aDAO.getDatosGenerales(pez_id, 7);
//        for (PezVO pezVO : lista) {
//            String agua = pezVO.getPez_agua();
//            if (pezVO.getPez_agua() == null || agua.equals("")) {
//                agua = "NO ESPECIFICADA";
//            }
//            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>AGUA</b></td></tr>"
//                    + "<tr><td align='center' style='font-size:40px'>" + agua + "</td></tr><table></body></html>";
//            this.info.setText(datos);
//        }
//    }

//    public void getAcuario(int pez_id) throws SQLException {
//        lista = aDAO.getDatosGenerales(pez_id, 8);
//        for (PezVO pezVO : lista) {
//            String acuario = pezVO.getPez_acuario();
//            if (pezVO.getPez_acuario() == null || acuario.equals("")) {
//                acuario = "NO ESPECIFICADO";
//            }
//            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>ACUARIO</b></td></tr>"
//                    + "<tr><td align='center' style='font-size:40px'>" + acuario + "</td></tr><table></body></html>";
//            this.info.setText(datos);
//        }
//    }

    public void getAlimentacion(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 9);
    }

    public void getComportamiento(int pez_id) throws SQLException {
        lista = aDAO.getDatosGenerales(pez_id, 10);
        for (PezVO pezVO : lista) {
            String comportamiento = pezVO.getPez_generalidades();
            if (pezVO.getPez_generalidades()== null || comportamiento.equals("")) {
                comportamiento = "NO ESPECIFICADO";
            }
            String datos = "<html><body><table><tr><td align='center' style='font-size:60px'><b>MIS GENERALIDADES</b></td></tr>"
                    + "<tr><td align='center' style='font-size:40px'>" + comportamiento + "</td></tr><table></body></html>";
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

//            controles.setSize(1920, 100);
//            controles.setVisible(true);
//            if (controles != null) {
//                videoPane.add("South", controles);
//            }
//            this.setContentPane(videoPane);
//            player.getDuration().getSeconds() con este llamado se sabe la duracion del video
            //player.setMediaTime(new Time(0));
            //player.deallocate();
        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VideoInfo(String direccion) {
        File directorio = new File(direccion);
        System.out.println("archivo "+directorio.exists());
        if (!directorio.exists()) {
            direccion = "file:///c:/acuario/defoult.mpg";
        }else{
            direccion = "file:///"+direccion;
        }

//        System.out.println(direccion);
//        JPanel panel = new JPanel();
//        videoPane.setLayout(new BorderLayout());
            videoPeces.setSize(1920, 1080);
//        datos.setLocation(0, 0);

//        videoPeces.setOpaque();
//        setLocation(0, 540);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            URL url = null;
            try {
                url = new URL(direccion);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
//            System.out.println("url: " + new MediaLocator(url));
//            player1
                player1 = f2.createRealizedPlayer(new MediaLocator(url));

                System.out.println("player: " + player1);
                video1 = player1.getVisualComponent();
//            System.out.println("video: " + video);
                video1.setSize(1920, 540);
                video1.setLocation(0, 540);
                video1.setVisible(true);

                if (video1 != null) {
                    videoPeces.add("Center", video1);
                }
                videoPeces.add(titulo);
                videoPeces.add(info);
                videoPeces.add(fondo);
//            video.repaint();
                tiempogeneral = player1.getDuration().getSeconds() + (0.8);
                controles1 = player1.getControlPanelComponent();
//            controles.setSize(1920, 100);
//            controles.setVisible(true);
//            if (controles != null) {
//                videoPane.add("South", controles);
//            }
//            this.setContentPane(videoPane);
//            player.getDuration().getSeconds() con este llamado se sabe la duracion del video
                //player.setMediaTime(new Time(0));
                //player.deallocate();
            } catch (IOException | NoPlayerException | CannotRealizeException ex) {
                Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void reproducirPrincipal() {
        player.setMediaTime(cero);
        player.start();
        videoPane.updateUI();
        tiempo = 0;
    }

    public void reproducir() {
        player1.setMediaTime(cero);
        player1.start();
        videoPeces.updateUI();
        t = 0;
    }

    Thread hiloVideoPrincipal = new Thread() {//declaramos el hilo

        @Override
        public void run() {
            try {
                VideoPrincipal("file:///c:/acuario/video/agua_converted.mpg");
//                VideoInfo("file:///c:/acuario/video/agua_converted.mpg");
                cero = player.getMediaTime();
                reproducirPrincipal();
//                reproducir();
//                int sw = 0;
//                System.out.println("Time.TIME_UNKNOWN: ");
                while (true) {//ciclo infinito
                    if (tiempo >= player.getDuration().getSeconds()) {
                        reproducirPrincipal();
                    }
//                    System.out.println("activo "+titulo.getText().equals(""));
                    if (!info.getText().equals("")) {
                        if (t >= tiempogeneral) {
                            reproducir();
                        }
                        t++;
                    } else {
                        t = 0;
                    }
                    tiempo++;
                    hiloVideoPrincipal.sleep(1000);//que duerma un segundo
                }
            } catch (java.lang.InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
    };

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
        finicial = new javax.swing.JLabel();
        visor = new javax.swing.JPanel();
        subtittle = new javax.swing.JLabel();
        tittle = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        fondo1 = new javax.swing.JLabel();
        datos = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        videoPane = new javax.swing.JPanel();
        videoPeces = new javax.swing.JPanel();

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

        finicial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        finicial.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoSegunda.jpg")); // NOI18N
        finicial.setAlignmentY(0.0F);
        inicial.add(finicial);
        finicial.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(inicial);
        inicial.setBounds(0, 0, 1920, 1080);

        visor.setLayout(null);

        subtittle.setFont(new java.awt.Font("Harabara", 0, 100)); // NOI18N
        subtittle.setForeground(new java.awt.Color(255, 255, 255));
        subtittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtittle.setToolTipText("");
        subtittle.setAlignmentY(0.0F);
        subtittle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        subtittle.setMaximumSize(new java.awt.Dimension(1850, 100));
        subtittle.setMinimumSize(new java.awt.Dimension(1850, 100));
        subtittle.setPreferredSize(new java.awt.Dimension(1850, 100));
        visor.add(subtittle);
        subtittle.setBounds(35, 935, 1850, 100);

        tittle.setFont(new java.awt.Font("Harabara", 0, 100)); // NOI18N
        tittle.setForeground(new java.awt.Color(255, 255, 255));
        tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittle.setToolTipText("");
        tittle.setAlignmentY(0.0F);
        tittle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tittle.setMaximumSize(new java.awt.Dimension(720, 100));
        tittle.setMinimumSize(new java.awt.Dimension(720, 100));
        tittle.setPreferredSize(new java.awt.Dimension(720, 100));
        visor.add(tittle);
        tittle.setBounds(510, 45, 900, 100);

        foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto.setAlignmentY(0.0F);
        foto.setMaximumSize(new java.awt.Dimension(1050, 700));
        foto.setMinimumSize(new java.awt.Dimension(1050, 700));
        foto.setPreferredSize(new java.awt.Dimension(1050, 700));
        visor.add(foto);
        foto.setBounds(435, 190, 1050, 700);

        fondo1.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoSegunda.jpg")); // NOI18N
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
        titulo.setBounds(20, 20, 720, 95);

        info.setFont(new java.awt.Font("BoyzRGross", 0, 70)); // NOI18N
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info.setAlignmentY(0.0F);
        info.setMaximumSize(new java.awt.Dimension(1536, 505));
        info.setMinimumSize(new java.awt.Dimension(1535, 505));
        info.setPreferredSize(new java.awt.Dimension(1535, 505));
        datos.add(info);
        info.setBounds(20, 120, 1280, 380);

        fondo.setBackground(new java.awt.Color(204, 204, 0));
        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoSegundaDatos.jpg")); // NOI18N
        datos.add(fondo);
        fondo.setBounds(0, 0, 1920, 520);

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
    private javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JLabel subtittle;
    public javax.swing.JLabel tittle;
    private javax.swing.JLabel titulo;
    public javax.swing.JPanel videoPane;
    public javax.swing.JPanel videoPeces;
    public javax.swing.JPanel visor;
    // End of variables declaration//GEN-END:variables
}
