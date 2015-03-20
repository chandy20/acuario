/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import objetos.PezVO;

/**
 *
 * @author JAK LIZCANO
 */
public class Inicio extends javax.swing.JFrame {

    
//    Ficha ficha = null;
    Image Imagenes[] = null;
    String names[] = null;
    String scientistNames[] = null;
    BufferedImage buffer[] = null;
    int ids[] = null;
    int contador = 0;
    boolean control = true;
    boolean controlSegunda = true;
    boolean controlInactividad = true;
    long y = 0;

    public Inicio() {
    }

    public Inicio(ArrayList<PezVO> peces) throws IOException {
        initComponents();
//        comenzarFicha();
        llenarVectores(peces);
        cargarComponentes();
        hilo.start();
    }

    public void comenzarFicha() {
//        ficha = new Ficha(this, false);
//        ficha.setPreferredSize(null);
//        java.awt.GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
//        devices[ 1].setFullScreenWindow(ficha);
//        ficha.setContentPane(ficha.inicial);
    }

    public void llenarVectores(ArrayList<PezVO> peces) throws IOException {
        Imagenes = new Image[peces.size()];
        names = new String[peces.size()];
        ids = new int[peces.size()];
        scientistNames = new String[peces.size()];
        buffer = new BufferedImage[peces.size()];
        int i = 0;
        for (PezVO pezVO : peces) {
            Imagenes[ i] = getToolkit().getImage("c:/acuario/" + pezVO.getPez_id() + "/descripcion.png");
            names[ i] = pezVO.getPez_nombComun();
            scientistNames[ i] = pezVO.getPez_nombCientifico();
            ids[ i] = pezVO.getPez_id();
            buffer[ i] = ImageIO.read(new File("c:/acuario/" + pezVO.getPez_id() + "/descripcion.png"));
            i++;
        }
    }

    public void cargarComponentes() {
        Image foto = Imagenes[ contador].getScaledInstance(700, (int) ((buffer[contador].getHeight() * 700) / buffer[contador].getWidth()), Image.SCALE_DEFAULT);
        slider.setIcon(new ImageIcon(foto));
        nombre.setText(names[ contador]);
    }

    public void iniciarInicio() {//inicio inactividad
        this.setContentPane(this.menu);
    }

    public void iniciarFicha() {
//        ficha.setContentPane(ficha.datos);
//        try {
////            ficha.cargaImagenes(ids[ contador]);
////            ficha.cargaNombre(ids[ contador]);
////            ficha.videoPeces.removeAll();
////            ficha.VideoInfo("c:/acuario/" + String.valueOf(ids[contador]) + "/videos/general.mpg");
////            ficha.reproducir();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void cerrarVideo() {
//        ficha.verVideo = false;
//        ficha.videoPane.removeAll();
//        ficha.VideoPrincipal("file:///c:/acuario/video/promo.mpg");
//        ficha.reproducirPrincipal();
//        this.setContentPane();
    }

    Thread hilo = new Thread() {//declaramos el hilo

        @Override
        public void run() {
            try {
//                ficha.VideoPrincipal("file:///c:/acuario/video/peces.mpg");
////                VideoInfo("file:///c:/acuario/video/agua_converted.mpg");
//                
//                ficha.reproducirPrincipal();
                while (true) {//ciclo infinito
//                     if (ficha.verVideo == true) {
//                        if (ficha.player.getMediaTime().getSeconds() != 0) {
//
//                            System.out.println("tiempo del video " + ficha.tiempo);
//                            if (ficha.tiempo >= 90) {
//                                ficha.videoPane.removeAll();
//                                ficha.VideoPrincipal("file:///c:/acuario/video/peces.mpg");
//                                ficha.setContentPane(ficha.videoPane);
//                            }
//                            ficha.tiempo++;
//                        }
//                    } else {
//                        if (y <= 2) {
//                            System.out.println("die");
//                            ficha.tiempo = 0;
//                            ficha.videoPane.removeAll();
////                            ficha.player.stop();
//                        }
//                    }
                    if (y == 360) {
                        controlInactividad = false;
                        if (controlSegunda) {
                            iniciarInicio();
                        }
                    }
                    y++;
                    hilo.sleep(1000);//que duerma un segundo
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
        menu = new javax.swing.JPanel();
        video = new javax.swing.JButton();
        peces = new javax.swing.JButton();
        banner = new javax.swing.JLabel();
        mfondo = new javax.swing.JLabel();
        tactil = new javax.swing.JPanel();
        cerrar = new javax.swing.JButton();
        slider = new javax.swing.JLabel();
        backslider = new javax.swing.JLabel();
        next = new javax.swing.JButton();
        prev = new javax.swing.JButton();
        nombre = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        seleccion = new javax.swing.JPanel();
        close = new javax.swing.JButton();
        home = new javax.swing.JButton();
        seleccionado = new javax.swing.JLabel();
        tittle = new javax.swing.JLabel();
        nombres = new javax.swing.JButton();
        biotopo = new javax.swing.JButton();
        distribucion = new javax.swing.JButton();
        forma = new javax.swing.JButton();
        tamano = new javax.swing.JButton();
        temperatura = new javax.swing.JButton();
        alimentacion = new javax.swing.JButton();
        comportamiento = new javax.swing.JButton();
        fseleccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);
        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1920, 1080));
        jLayeredPane1.setName(""); // NOI18N

        menu.setAlignmentX(0.0F);
        menu.setAlignmentY(0.0F);
        menu.setMaximumSize(new java.awt.Dimension(1920, 1080));
        menu.setMinimumSize(new java.awt.Dimension(1920, 1080));
        menu.setOpaque(false);
        menu.setLayout(null);

        video.setForeground(new java.awt.Color(255, 255, 255));
        video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/video.png"))); // NOI18N
        video.setAlignmentY(0.0F);
        video.setBorder(null);
        video.setBorderPainted(false);
        video.setContentAreaFilled(false);
        video.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        video.setFocusPainted(false);
        video.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        video.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/videoP.png"))); // NOI18N
        video.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                videoMouseClicked(evt);
            }
        });
        menu.add(video);
        video.setBounds(330, 194, 480, 480);

        peces.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/peces.png"))); // NOI18N
        peces.setAlignmentY(0.0F);
        peces.setBorder(null);
        peces.setBorderPainted(false);
        peces.setContentAreaFilled(false);
        peces.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        peces.setFocusPainted(false);
        peces.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pecesP.png"))); // NOI18N
        peces.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pecesMouseClicked(evt);
            }
        });
        menu.add(peces);
        peces.setBounds(1110, 194, 480, 480);

        banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        banner.setIcon(new javax.swing.ImageIcon("C:\\acuario\\social.jpg")); // NOI18N
        banner.setAlignmentY(0.0F);
        banner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bannerMouseClicked(evt);
            }
        });
        menu.add(banner);
        banner.setBounds(0, 868, 1920, 212);

        mfondo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mfondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoMenu.jpg")); // NOI18N
        mfondo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mfondo.setAlignmentY(0.0F);
        mfondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mfondoMouseClicked(evt);
            }
        });
        menu.add(mfondo);
        mfondo.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(menu);
        menu.setBounds(0, 0, 1920, 1080);

        tactil.setAlignmentX(0.0F);
        tactil.setAlignmentY(0.0F);
        tactil.setMaximumSize(new java.awt.Dimension(1920, 1080));
        tactil.setMinimumSize(new java.awt.Dimension(1920, 1080));
        tactil.setRequestFocusEnabled(false);
        tactil.setLayout(null);

        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atras.png"))); // NOI18N
        cerrar.setAlignmentY(0.0F);
        cerrar.setBorder(null);
        cerrar.setBorderPainted(false);
        cerrar.setContentAreaFilled(false);
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cerrar.setFocusPainted(false);
        cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cerrar.setMaximumSize(new java.awt.Dimension(140, 140));
        cerrar.setMinimumSize(new java.awt.Dimension(140, 140));
        cerrar.setPreferredSize(new java.awt.Dimension(140, 140));
        cerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atrasP.png"))); // NOI18N
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
        });
        tactil.add(cerrar);
        cerrar.setBounds(20, 20, 140, 140);

        slider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slider.setAlignmentY(0.0F);
        slider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        slider.setMaximumSize(new java.awt.Dimension(700, 490));
        slider.setMinimumSize(new java.awt.Dimension(700, 490));
        slider.setPreferredSize(new java.awt.Dimension(700, 490));
        slider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sliderMouseClicked(evt);
            }
        });
        tactil.add(slider);
        slider.setBounds(610, 350, 700, 490);

        backslider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backslider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bkpeces.png"))); // NOI18N
        backslider.setAlignmentY(0.0F);
        backslider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        backslider.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backslider.setMaximumSize(new java.awt.Dimension(768, 768));
        backslider.setMinimumSize(new java.awt.Dimension(768, 768));
        backslider.setPreferredSize(new java.awt.Dimension(768, 768));
        tactil.add(backslider);
        backslider.setBounds(576, 206, 768, 768);

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        next.setAlignmentY(0.0F);
        next.setBorder(null);
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        next.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        next.setFocusPainted(false);
        next.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        next.setMaximumSize(new java.awt.Dimension(440, 530));
        next.setMinimumSize(new java.awt.Dimension(440, 530));
        next.setPreferredSize(new java.awt.Dimension(440, 530));
        next.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nextP.png"))); // NOI18N
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        tactil.add(next);
        next.setBounds(1280, 330, 440, 530);

        prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prev.png"))); // NOI18N
        prev.setAlignmentY(0.0F);
        prev.setBorder(null);
        prev.setBorderPainted(false);
        prev.setContentAreaFilled(false);
        prev.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        prev.setFocusPainted(false);
        prev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        prev.setMaximumSize(new java.awt.Dimension(440, 530));
        prev.setMinimumSize(new java.awt.Dimension(440, 530));
        prev.setPreferredSize(new java.awt.Dimension(440, 530));
        prev.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prevP.png"))); // NOI18N
        prev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevMouseClicked(evt);
            }
        });
        tactil.add(prev);
        prev.setBounds(210, 330, 440, 530);

        nombre.setFont(new java.awt.Font("Harabara", 0, 170)); // NOI18N
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setAlignmentY(0.0F);
        nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nombre.setMaximumSize(new java.awt.Dimension(700, 70));
        nombre.setMinimumSize(new java.awt.Dimension(700, 70));
        nombre.setPreferredSize(new java.awt.Dimension(700, 70));
        nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombreMouseClicked(evt);
            }
        });
        tactil.add(nombre);
        nombre.setBounds(310, 25, 1300, 150);

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoPeces.jpg")); // NOI18N
        fondo.setAlignmentY(0.0F);
        tactil.add(fondo);
        fondo.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(tactil);
        tactil.setBounds(0, 0, 1920, 1080);

        seleccion.setAlignmentX(0.0F);
        seleccion.setAlignmentY(0.0F);
        seleccion.setMaximumSize(new java.awt.Dimension(1920, 1080));
        seleccion.setMinimumSize(new java.awt.Dimension(1920, 1080));
        seleccion.setName(""); // NOI18N
        seleccion.setPreferredSize(new java.awt.Dimension(1920, 1080));
        seleccion.setLayout(null);

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atras.png"))); // NOI18N
        close.setAlignmentY(0.0F);
        close.setBorder(null);
        close.setBorderPainted(false);
        close.setContentAreaFilled(false);
        close.setFocusPainted(false);
        close.setMaximumSize(new java.awt.Dimension(64, 64));
        close.setMinimumSize(new java.awt.Dimension(64, 64));
        close.setPreferredSize(new java.awt.Dimension(64, 64));
        close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atrasP.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        seleccion.add(close);
        close.setBounds(143, 33, 100, 100);

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicio.png"))); // NOI18N
        home.setAlignmentY(0.0F);
        home.setBorderPainted(false);
        home.setContentAreaFilled(false);
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        home.setFocusPainted(false);
        home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        home.setMaximumSize(new java.awt.Dimension(100, 100));
        home.setMinimumSize(new java.awt.Dimension(100, 100));
        home.setPreferredSize(new java.awt.Dimension(100, 100));
        home.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicioP.png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        seleccion.add(home);
        home.setBounds(33, 33, 100, 100);

        seleccionado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seleccionado.setAlignmentY(0.0F);
        seleccionado.setMaximumSize(new java.awt.Dimension(500, 350));
        seleccionado.setMinimumSize(new java.awt.Dimension(500, 350));
        seleccionado.setPreferredSize(new java.awt.Dimension(500, 350));
        seleccion.add(seleccionado);
        seleccionado.setBounds(433, 160, 500, 350);

        tittle.setFont(new java.awt.Font("Harabara", 1, 70)); // NOI18N
        tittle.setForeground(new java.awt.Color(255, 247, 152));
        tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittle.setToolTipText("");
        tittle.setAlignmentY(0.0F);
        tittle.setMaximumSize(new java.awt.Dimension(700, 45));
        tittle.setMinimumSize(new java.awt.Dimension(700, 45));
        tittle.setPreferredSize(new java.awt.Dimension(700, 45));
        seleccion.add(tittle);
        tittle.setBounds(333, 48, 700, 70);

        nombres.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        nombres.setForeground(new java.awt.Color(255, 255, 255));
        nombres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nombre.png"))); // NOI18N
        nombres.setAlignmentY(0.0F);
        nombres.setBorder(null);
        nombres.setBorderPainted(false);
        nombres.setContentAreaFilled(false);
        nombres.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombres.setFocusPainted(false);
        nombres.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nombres.setIconTextGap(0);
        nombres.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nombreP.png"))); // NOI18N
        nombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombresMouseClicked(evt);
            }
        });
        seleccion.add(nombres);
        nombres.setBounds(33, 323, 190, 190);

        biotopo.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        biotopo.setForeground(new java.awt.Color(255, 255, 255));
        biotopo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        biotopo.setAlignmentY(0.0F);
        biotopo.setBorder(null);
        biotopo.setBorderPainted(false);
        biotopo.setContentAreaFilled(false);
        biotopo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        biotopo.setFocusPainted(false);
        biotopo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        biotopo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacionP.png"))); // NOI18N
        biotopo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                biotopoMouseClicked(evt);
            }
        });
        seleccion.add(biotopo);
        biotopo.setBounds(921, 545, 190, 190);

        distribucion.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        distribucion.setForeground(new java.awt.Color(255, 255, 255));
        distribucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/casa.png"))); // NOI18N
        distribucion.setAlignmentY(0.0F);
        distribucion.setBorder(null);
        distribucion.setBorderPainted(false);
        distribucion.setContentAreaFilled(false);
        distribucion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        distribucion.setFocusPainted(false);
        distribucion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        distribucion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/casaP.png"))); // NOI18N
        distribucion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distribucionMouseClicked(evt);
            }
        });
        seleccion.add(distribucion);
        distribucion.setBounds(33, 545, 190, 190);

        forma.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        forma.setForeground(new java.awt.Color(255, 255, 255));
        forma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cuerpo.png"))); // NOI18N
        forma.setAlignmentY(0.0F);
        forma.setBorder(null);
        forma.setBorderPainted(false);
        forma.setContentAreaFilled(false);
        forma.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        forma.setFocusPainted(false);
        forma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        forma.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cuerpoP.png"))); // NOI18N
        forma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formaMouseClicked(evt);
            }
        });
        seleccion.add(forma);
        forma.setBounds(255, 545, 190, 190);

        tamano.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        tamano.setForeground(new java.awt.Color(255, 255, 255));
        tamano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medidas.png"))); // NOI18N
        tamano.setAlignmentY(0.0F);
        tamano.setBorder(null);
        tamano.setBorderPainted(false);
        tamano.setContentAreaFilled(false);
        tamano.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tamano.setFocusPainted(false);
        tamano.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tamano.setIconTextGap(0);
        tamano.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medidasP.png"))); // NOI18N
        tamano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tamanoMouseClicked(evt);
            }
        });
        seleccion.add(tamano);
        tamano.setBounds(477, 545, 190, 190);

        temperatura.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        temperatura.setForeground(new java.awt.Color(255, 255, 255));
        temperatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temperatura.png"))); // NOI18N
        temperatura.setAlignmentY(0.0F);
        temperatura.setBorder(null);
        temperatura.setBorderPainted(false);
        temperatura.setContentAreaFilled(false);
        temperatura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        temperatura.setFocusPainted(false);
        temperatura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        temperatura.setIconTextGap(0);
        temperatura.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temperaturaP.png"))); // NOI18N
        temperatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                temperaturaMouseClicked(evt);
            }
        });
        seleccion.add(temperatura);
        temperatura.setBounds(1143, 323, 190, 190);

        alimentacion.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        alimentacion.setForeground(new java.awt.Color(255, 255, 255));
        alimentacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/comida.png"))); // NOI18N
        alimentacion.setAlignmentY(0.0F);
        alimentacion.setBorder(null);
        alimentacion.setBorderPainted(false);
        alimentacion.setContentAreaFilled(false);
        alimentacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        alimentacion.setFocusPainted(false);
        alimentacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alimentacion.setIconTextGap(0);
        alimentacion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/comidaP.png"))); // NOI18N
        alimentacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alimentacionMouseClicked(evt);
            }
        });
        seleccion.add(alimentacion);
        alimentacion.setBounds(1143, 545, 190, 190);

        comportamiento.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        comportamiento.setForeground(new java.awt.Color(255, 255, 255));
        comportamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/curiosidades.png"))); // NOI18N
        comportamiento.setAlignmentY(0.0F);
        comportamiento.setBorder(null);
        comportamiento.setBorderPainted(false);
        comportamiento.setContentAreaFilled(false);
        comportamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        comportamiento.setFocusPainted(false);
        comportamiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        comportamiento.setIconTextGap(0);
        comportamiento.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/curiosidadesP.png"))); // NOI18N
        comportamiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comportamientoMouseClicked(evt);
            }
        });
        seleccion.add(comportamiento);
        comportamiento.setBounds(699, 545, 190, 190);

        fseleccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fseleccion.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoPeces.jpg")); // NOI18N
        fseleccion.setAlignmentY(0.0F);
        seleccion.add(fseleccion);
        fseleccion.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(seleccion);
        seleccion.setBounds(0, 0, 1920, 1080);

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

    private void sliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
            tittle.setText(names[ contador]);
            Image foto = Imagenes[ contador].getScaledInstance(500, (int) ((buffer[ contador].getHeight() * 500) / buffer[ contador].getWidth()), Image.SCALE_DEFAULT);
            seleccionado.setIcon(new ImageIcon(foto));
            this.setContentPane(seleccion);
        }

    }//GEN-LAST:event_sliderMouseClicked

    private void nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreMouseClicked
        // TODO add your handling code here:
        sliderMouseClicked(evt);
    }//GEN-LAST:event_nombreMouseClicked

    private void videoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_videoMouseClicked
        // TODO add your handling code here:
//        ficha.videoPane
        y = 0;
        controlInactividad = true;
        if (controlSegunda) {
//            ficha.videoPane.removeAll();
//            ficha.VideoPrincipal("file:///c:/acuario/video/promo.mpg");///url video promociones
            // ficha.reproducirPrincipal();
//            ficha.setContentPane(ficha.videoPane);
            controlSegunda = false;
        }
    }//GEN-LAST:event_videoMouseClicked

    private void pecesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pecesMouseClicked
        // TODO add your handling code here:
        y = 0;
        controlInactividad = true;
//        ficha.verVideo = true;
        this.setContentPane(this.tactil);
        cargarComponentes();
        if (!controlSegunda) {
            cerrarVideo();
            controlSegunda = true;
        }
//        ficha.setContentPane(ficha.visor);
    }//GEN-LAST:event_pecesMouseClicked

    private void prevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
            contador--;
            if (contador == -1) {
                contador = ids.length - 1;
            }
            cargarComponentes();
        }
    }//GEN-LAST:event_prevMouseClicked

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
            contador++;
            if (contador == ids.length) {
                contador = 0;
            }
            cargarComponentes();
        }
    }//GEN-LAST:event_nextMouseClicked

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
            this.setContentPane(menu);
//            ficha.setContentPane(ficha.inicial);
        }
    }//GEN-LAST:event_cerrarMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {
            controlInactividad = true;
            controlSegunda = true;

        } else {
//            ficha.setContentPane(ficha.visor);
            this.setContentPane(tactil);
            control = true;
        }

    }//GEN-LAST:event_closeMouseClicked

    private void nombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombresMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {
            controlInactividad = true;
            controlSegunda = true;
        } else {
            if (control) {
                iniciarFicha();
                control = false;
            }
//            try {
////            ficha.setContentPane(ficha.datos);
//                ficha.setContentPane(ficha.videoPeces);
//                ficha.getNombres(ids[contador]);
//            } catch (SQLException ex) {
//                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

    }//GEN-LAST:event_nombresMouseClicked

    private void biotopoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_biotopoMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
            if (control) {
                iniciarFicha();
                control = false;
            }
//            try {
////            ficha.setContentPane(ficha.datos);
//                ficha.setContentPane(ficha.videoPeces);
//                ficha.getForma(ids[contador]);
//            } catch (SQLException ex) {
//                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

    }//GEN-LAST:event_biotopoMouseClicked

    private void distribucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distribucionMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;

        } else {
//            ficha.videoDetalle.removeAll();
//            ficha.VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/vivo.mpg"); //vivo.mpg
//            control = true;
//            ficha.setContentPane(ficha.videoDetalle);
        }
    }//GEN-LAST:event_distribucionMouseClicked

    private void formaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formaMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {
            controlInactividad = true;
            controlSegunda = true;

        } else {
//            ficha.videoDetalle.removeAll();
//            ficha.VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/cuerpo.mpg");//cuerpo.mpg
//            control = true;
//            ficha.setContentPane(ficha.videoDetalle);
        }
    }//GEN-LAST:event_formaMouseClicked

    private void tamanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tamanoMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;

        } else {
//            ficha.videoDetalle.removeAll();
//            ficha.VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/medidas.mpg");//medidas.mpg
//            control = true;
//            ficha.setContentPane(ficha.videoDetalle);
        }
    }//GEN-LAST:event_tamanoMouseClicked

    private void temperaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_temperaturaMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;

        } else {
//            ficha.videoDetalle.removeAll();
//            ficha.VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/temperatura.mpg");//temperatura.mpg
//            control = true;
//            ficha.setContentPane(ficha.videoDetalle);
        }
    }//GEN-LAST:event_temperaturaMouseClicked

    private void alimentacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alimentacionMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
            if (control) {
                iniciarFicha();
                control = false;
            }
//            try {
////            ficha.setContentPane(ficha.datos);
//                ficha.setContentPane(ficha.videoPeces);
//                ficha.getAlimentacion(ids[contador]);
//            } catch (SQLException ex) {
//                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }//GEN-LAST:event_alimentacionMouseClicked

    private void comportamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comportamientoMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
            if (control) {
                iniciarFicha();
                control = false;
            }
//            try {
////            ficha.setContentPane(ficha.datos);
//                ficha.setContentPane(ficha.videoPeces);
//                ficha.getComportamiento(ids[contador]);
//            } catch (SQLException ex) {
//                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }//GEN-LAST:event_comportamientoMouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
//        ficha.setContentPane(ficha.inicial);
        this.setContentPane(menu);
        control = true;
    }//GEN-LAST:event_homeMouseClicked

    private void mfondoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mfondoMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {
            cerrarVideo();
            controlInactividad = true;
            controlSegunda = true;
        }
        this.setContentPane(menu);
    }//GEN-LAST:event_mfondoMouseClicked

    private void bannerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bannerMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {
            cerrarVideo();
            controlInactividad = true;
            controlSegunda = true;
        }
        this.setContentPane(menu);
    }//GEN-LAST:event_bannerMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alimentacion;
    private javax.swing.JLabel backslider;
    private javax.swing.JLabel banner;
    private javax.swing.JButton biotopo;
    private javax.swing.JButton cerrar;
    private javax.swing.JButton close;
    private javax.swing.JButton comportamiento;
    private javax.swing.JButton distribucion;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton forma;
    private javax.swing.JLabel fseleccion;
    private javax.swing.JButton home;
    private javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JPanel menu;
    public javax.swing.JLabel mfondo;
    private javax.swing.JButton next;
    private javax.swing.JLabel nombre;
    private javax.swing.JButton nombres;
    private javax.swing.JButton peces;
    private javax.swing.JButton prev;
    private javax.swing.JPanel seleccion;
    private javax.swing.JLabel seleccionado;
    private javax.swing.JLabel slider;
    private javax.swing.JPanel tactil;
    private javax.swing.JButton tamano;
    private javax.swing.JButton temperatura;
    private javax.swing.JLabel tittle;
    private javax.swing.JButton video;
    // End of variables declaration//GEN-END:variables
}
