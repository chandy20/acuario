/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Desktop;
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

    /**
     * Creates new form Inicio
     */
    Ficha ficha = null;
    Image Imagenes[] = null;
    String names[] = null;
    BufferedImage buffer[] = null;
    int ids[] = null;
    int contador = 0;
    boolean control = true;
    boolean controlSegunda = true;
    boolean controlInactividad = true;
    long y = 0;

    public Inicio(ArrayList<PezVO> peces) throws IOException {
        initComponents();
        comenzarFicha();
        llenarVectores(peces);
        cargarComponentes();
//        hilo.start();
    }

    public void comenzarFicha() {
        ficha = new Ficha(this, false);
        ficha.setPreferredSize(null);
        java.awt.GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        devices[ 1].setFullScreenWindow(ficha);
        ficha.setContentPane(ficha.inicial);
    }

    public void llenarVectores(ArrayList<PezVO> peces) throws IOException {
        Imagenes = new Image[peces.size()];
        names = new String[peces.size()];
        ids = new int[peces.size()];
        buffer = new BufferedImage[peces.size()];
        int i = 0;
        for (PezVO pezVO : peces) {
            Imagenes[ i] = getToolkit().getImage(pezVO.getPez_nombComun());
            names[ i] = pezVO.getPez_nombre();
            ids[ i] = pezVO.getPez_id();
            buffer[ i] = ImageIO.read(new File(pezVO.getPez_nombComun()));
            i++;
        }
    }

    public void cargarComponentes() {
        Image foto = Imagenes[ contador].getScaledInstance(500, (int) ((buffer[contador].getHeight() * 500) / buffer[contador].getWidth()), Image.SCALE_DEFAULT);
        slider.setIcon(new ImageIcon(foto));
        nombre.setText(names[ contador]);
        Image fotoTv = Imagenes[ contador].getScaledInstance(1050, (int) ((buffer[contador].getHeight() * 1050) / buffer[contador].getWidth()), Image.SCALE_DEFAULT);
        ficha.foto.setIcon(new ImageIcon(fotoTv));
        ficha.tittle.setText(names[ contador]);
    }

    public void iniciarInicio() {
        this.setContentPane(this.menu);
    }

    public void iniciarFicha() {
        ficha.setContentPane(ficha.datos);
        try {
//            ficha.cargaImagenes(ids[ contador]);
            ficha.cargaNombre(ids[ contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarVideo() {
        Process hijo;
        try {
            hijo = Runtime.getRuntime().exec("tskill chrome");
            hijo.waitFor();
            if (hijo.exitValue() == 0) {
                System.out.println("Video cerrado con exito");
            } else {
                System.out.println("Incapaz de cerrar Video. Exit code: " + hijo.exitValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Incapaz de cerrar Video.");
        } catch (InterruptedException e) {
            System.out.println("Incapaz de cerrar Video.");
        }
    }

    Thread hilo = new Thread() {//declaramos el hilo

        @Override
        public void run() {
            try {
                while (true) {//ciclo infinito
                    if (y == 10) {
                       controlInactividad = false;
                        if (controlSegunda) {
                            iniciarInicio();
//                            try {
////                                File file = new File("c:\\acuario/video/demo.html");
////                                Desktop.getDesktop().open(file);
////                                ficha.dispose();
//                                controlSegunda = false;
//                            } catch (IOException ex) {
//                                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//                            }
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
        bgrilla = new javax.swing.JButton();
        bcarrusel = new javax.swing.JButton();
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
        grilla = new javax.swing.JPanel();
        gcerrar = new javax.swing.JButton();
        gtitulo = new javax.swing.JLabel();
        P1 = new javax.swing.JLabel();
        P2 = new javax.swing.JLabel();
        P3 = new javax.swing.JLabel();
        P4 = new javax.swing.JLabel();
        P5 = new javax.swing.JLabel();
        P6 = new javax.swing.JLabel();
        P7 = new javax.swing.JLabel();
        P8 = new javax.swing.JLabel();
        P9 = new javax.swing.JLabel();
        P10 = new javax.swing.JLabel();
        P11 = new javax.swing.JLabel();
        P12 = new javax.swing.JLabel();
        up = new javax.swing.JButton();
        down = new javax.swing.JButton();
        gfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);
        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1366, 768));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1366, 768));

        menu.setAlignmentX(0.0F);
        menu.setAlignmentY(0.0F);
        menu.setMaximumSize(new java.awt.Dimension(1366, 768));
        menu.setMinimumSize(new java.awt.Dimension(1366, 768));
        menu.setOpaque(false);
        menu.setLayout(null);

        video.setForeground(new java.awt.Color(255, 255, 255));
        video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/video.png"))); // NOI18N
        video.setAlignmentY(0.0F);
        video.setBorder(null);
        video.setBorderPainted(false);
        video.setContentAreaFilled(false);
        video.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        video.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        video.setMaximumSize(new java.awt.Dimension(340, 340));
        video.setMinimumSize(new java.awt.Dimension(340, 340));
        video.setPreferredSize(new java.awt.Dimension(340, 340));
        video.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/videoP.png"))); // NOI18N
        video.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                videoMouseClicked(evt);
            }
        });
        menu.add(video);
        video.setBounds(250, 139, 340, 340);

        peces.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/peces.png"))); // NOI18N
        peces.setAlignmentY(0.0F);
        peces.setBorder(null);
        peces.setBorderPainted(false);
        peces.setContentAreaFilled(false);
        peces.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        peces.setMaximumSize(new java.awt.Dimension(340, 340));
        peces.setMinimumSize(new java.awt.Dimension(340, 340));
        peces.setPreferredSize(new java.awt.Dimension(340, 340));
        peces.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pecesP.png"))); // NOI18N
        peces.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pecesMouseClicked(evt);
            }
        });
        menu.add(peces);
        peces.setBounds(806, 139, 340, 340);

        banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        banner.setIcon(new javax.swing.ImageIcon("C:\\acuario\\social.jpg")); // NOI18N
        banner.setAlignmentY(0.0F);
        banner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bannerMouseClicked(evt);
            }
        });
        menu.add(banner);
        banner.setBounds(0, 618, 1366, 150);

        mfondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mfondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoMenu.jpg")); // NOI18N
        mfondo.setAlignmentY(0.0F);
        mfondo.setMaximumSize(new java.awt.Dimension(1366, 768));
        mfondo.setMinimumSize(new java.awt.Dimension(1366, 768));
        mfondo.setPreferredSize(new java.awt.Dimension(1366, 768));
        mfondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mfondoMouseClicked(evt);
            }
        });
        menu.add(mfondo);
        mfondo.setBounds(0, 0, 1366, 768);

        jLayeredPane1.add(menu);
        menu.setBounds(0, 0, 1366, 768);

        tactil.setAlignmentX(0.0F);
        tactil.setAlignmentY(0.0F);
        tactil.setMaximumSize(new java.awt.Dimension(1366, 768));
        tactil.setMinimumSize(new java.awt.Dimension(1366, 768));
        tactil.setLayout(null);

        bgrilla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grilla.png"))); // NOI18N
        bgrilla.setAlignmentY(0.0F);
        bgrilla.setBorder(null);
        bgrilla.setBorderPainted(false);
        bgrilla.setContentAreaFilled(false);
        bgrilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bgrilla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bgrilla.setMaximumSize(new java.awt.Dimension(100, 100));
        bgrilla.setMinimumSize(new java.awt.Dimension(100, 100));
        bgrilla.setPreferredSize(new java.awt.Dimension(100, 100));
        bgrilla.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grillaP.png"))); // NOI18N
        tactil.add(bgrilla);
        bgrilla.setBounds(1246, 20, 100, 100);

        bcarrusel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carrusel.png"))); // NOI18N
        bcarrusel.setAlignmentY(0.0F);
        bcarrusel.setBorder(null);
        bcarrusel.setBorderPainted(false);
        bcarrusel.setContentAreaFilled(false);
        bcarrusel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcarrusel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bcarrusel.setMaximumSize(new java.awt.Dimension(100, 100));
        bcarrusel.setMinimumSize(new java.awt.Dimension(100, 100));
        bcarrusel.setPreferredSize(new java.awt.Dimension(100, 100));
        bcarrusel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carruselP.png"))); // NOI18N
        tactil.add(bcarrusel);
        bcarrusel.setBounds(1136, 20, 100, 100);

        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atras.png"))); // NOI18N
        cerrar.setAlignmentY(0.0F);
        cerrar.setBorder(null);
        cerrar.setBorderPainted(false);
        cerrar.setContentAreaFilled(false);
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cerrar.setMaximumSize(new java.awt.Dimension(100, 100));
        cerrar.setMinimumSize(new java.awt.Dimension(100, 100));
        cerrar.setPreferredSize(new java.awt.Dimension(100, 100));
        cerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atrasP.png"))); // NOI18N
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
        });
        tactil.add(cerrar);
        cerrar.setBounds(20, 20, 100, 100);

        slider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slider.setAlignmentY(0.0F);
        slider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slider.setMaximumSize(new java.awt.Dimension(500, 350));
        slider.setMinimumSize(new java.awt.Dimension(500, 350));
        slider.setPreferredSize(new java.awt.Dimension(500, 350));
        slider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sliderMouseClicked(evt);
            }
        });
        tactil.add(slider);
        slider.setBounds(433, 274, 500, 350);

        backslider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backslider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bkpeces.png"))); // NOI18N
        backslider.setAlignmentY(0.0F);
        backslider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backslider.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tactil.add(backslider);
        backslider.setBounds(408, 174, 550, 550);

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        next.setAlignmentY(0.0F);
        next.setBorder(null);
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        next.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        next.setMaximumSize(new java.awt.Dimension(315, 380));
        next.setMinimumSize(new java.awt.Dimension(315, 380));
        next.setPreferredSize(new java.awt.Dimension(315, 380));
        next.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nextP.png"))); // NOI18N
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        tactil.add(next);
        next.setBounds(905, 260, 315, 380);

        prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prev.png"))); // NOI18N
        prev.setAlignmentY(0.0F);
        prev.setBorder(null);
        prev.setBorderPainted(false);
        prev.setContentAreaFilled(false);
        prev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        prev.setMaximumSize(new java.awt.Dimension(315, 380));
        prev.setMinimumSize(new java.awt.Dimension(315, 380));
        prev.setPreferredSize(new java.awt.Dimension(315, 380));
        prev.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prevP.png"))); // NOI18N
        prev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevMouseClicked(evt);
            }
        });
        tactil.add(prev);
        prev.setBounds(150, 260, 315, 380);

        nombre.setFont(new java.awt.Font("Harabara", 0, 100)); // NOI18N
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setAlignmentY(0.0F);
        nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        nombre.setBounds(233, 20, 900, 100);

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoPeces.jpg")); // NOI18N
        fondo.setAlignmentY(0.0F);
        fondo.setMaximumSize(new java.awt.Dimension(1366, 768));
        fondo.setMinimumSize(new java.awt.Dimension(1366, 768));
        fondo.setPreferredSize(new java.awt.Dimension(1366, 768));
        tactil.add(fondo);
        fondo.setBounds(0, 0, 1366, 768);

        jLayeredPane1.add(tactil);
        tactil.setBounds(0, 0, 1366, 768);

        seleccion.setAlignmentX(0.0F);
        seleccion.setAlignmentY(0.0F);
        seleccion.setMaximumSize(new java.awt.Dimension(1366, 768));
        seleccion.setMinimumSize(new java.awt.Dimension(1366, 768));
        seleccion.setPreferredSize(new java.awt.Dimension(1366, 768));
        seleccion.setLayout(null);

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atras.png"))); // NOI18N
        close.setAlignmentY(0.0F);
        close.setBorder(null);
        close.setBorderPainted(false);
        close.setContentAreaFilled(false);
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
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        nombres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nombres.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nombres.setIconTextGap(0);
        nombres.setMaximumSize(new java.awt.Dimension(190, 190));
        nombres.setMinimumSize(new java.awt.Dimension(190, 190));
        nombres.setPreferredSize(new java.awt.Dimension(190, 190));
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
        biotopo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        biotopo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        biotopo.setMaximumSize(new java.awt.Dimension(190, 190));
        biotopo.setMinimumSize(new java.awt.Dimension(190, 190));
        biotopo.setPreferredSize(new java.awt.Dimension(190, 190));
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
        distribucion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        distribucion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        distribucion.setMaximumSize(new java.awt.Dimension(190, 190));
        distribucion.setMinimumSize(new java.awt.Dimension(190, 190));
        distribucion.setPreferredSize(new java.awt.Dimension(190, 190));
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
        forma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        forma.setMaximumSize(new java.awt.Dimension(190, 190));
        forma.setMinimumSize(new java.awt.Dimension(190, 190));
        forma.setPreferredSize(new java.awt.Dimension(190, 190));
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
        tamano.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tamano.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tamano.setIconTextGap(0);
        tamano.setMaximumSize(new java.awt.Dimension(190, 190));
        tamano.setMinimumSize(new java.awt.Dimension(190, 190));
        tamano.setPreferredSize(new java.awt.Dimension(190, 190));
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
        temperatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        temperatura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        temperatura.setIconTextGap(0);
        temperatura.setMaximumSize(new java.awt.Dimension(190, 190));
        temperatura.setMinimumSize(new java.awt.Dimension(190, 190));
        temperatura.setPreferredSize(new java.awt.Dimension(190, 190));
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
        alimentacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        alimentacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alimentacion.setIconTextGap(0);
        alimentacion.setMaximumSize(new java.awt.Dimension(190, 190));
        alimentacion.setMinimumSize(new java.awt.Dimension(190, 190));
        alimentacion.setPreferredSize(new java.awt.Dimension(190, 190));
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
        comportamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comportamiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        comportamiento.setIconTextGap(0);
        comportamiento.setMaximumSize(new java.awt.Dimension(190, 190));
        comportamiento.setMinimumSize(new java.awt.Dimension(190, 190));
        comportamiento.setPreferredSize(new java.awt.Dimension(190, 190));
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
        fseleccion.setMaximumSize(new java.awt.Dimension(1366, 768));
        fseleccion.setMinimumSize(new java.awt.Dimension(1366, 768));
        fseleccion.setPreferredSize(new java.awt.Dimension(1366, 768));
        seleccion.add(fseleccion);
        fseleccion.setBounds(0, 0, 1366, 768);

        jLayeredPane1.add(seleccion);
        seleccion.setBounds(0, 0, 1366, 768);

        grilla.setAlignmentX(0.0F);
        grilla.setAlignmentY(0.0F);
        grilla.setMaximumSize(new java.awt.Dimension(1366, 768));
        grilla.setMinimumSize(new java.awt.Dimension(1366, 768));
        grilla.setLayout(null);

        gcerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/review.png"))); // NOI18N
        gcerrar.setAlignmentY(0.0F);
        gcerrar.setBorder(null);
        gcerrar.setBorderPainted(false);
        gcerrar.setContentAreaFilled(false);
        gcerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gcerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gcerrar.setMaximumSize(new java.awt.Dimension(64, 64));
        gcerrar.setMinimumSize(new java.awt.Dimension(64, 64));
        gcerrar.setPreferredSize(new java.awt.Dimension(64, 64));
        gcerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reviewP.png"))); // NOI18N
        gcerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gcerrarMouseClicked(evt);
            }
        });
        grilla.add(gcerrar);
        gcerrar.setBounds(1290, 10, 64, 64);

        gtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gtitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mundoII.png"))); // NOI18N
        gtitulo.setAlignmentY(0.0F);
        gtitulo.setMaximumSize(new java.awt.Dimension(330, 200));
        gtitulo.setMinimumSize(new java.awt.Dimension(330, 200));
        gtitulo.setPreferredSize(new java.awt.Dimension(330, 200));
        grilla.add(gtitulo);
        gtitulo.setBounds(518, 25, 300, 200);

        P1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P1.setAlignmentY(0.0F);
        P1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P1.setMaximumSize(new java.awt.Dimension(284, 150));
        P1.setMinimumSize(new java.awt.Dimension(284, 150));
        P1.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P1);
        P1.setBounds(34, 267, 284, 150);

        P2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P2.setAlignmentY(0.0F);
        P2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P2.setMaximumSize(new java.awt.Dimension(284, 150));
        P2.setMinimumSize(new java.awt.Dimension(284, 150));
        P2.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P2);
        P2.setBounds(351, 267, 284, 150);

        P3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P3.setAlignmentY(0.0F);
        P3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P3.setMaximumSize(new java.awt.Dimension(284, 150));
        P3.setMinimumSize(new java.awt.Dimension(284, 150));
        P3.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P3);
        P3.setBounds(668, 267, 284, 150);

        P4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P4.setAlignmentY(0.0F);
        P4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P4.setMaximumSize(new java.awt.Dimension(284, 150));
        P4.setMinimumSize(new java.awt.Dimension(284, 150));
        P4.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P4);
        P4.setBounds(985, 267, 284, 150);

        P5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P5.setAlignmentY(0.0F);
        P5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P5.setMaximumSize(new java.awt.Dimension(284, 150));
        P5.setMinimumSize(new java.awt.Dimension(284, 150));
        P5.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P5);
        P5.setBounds(34, 434, 284, 150);

        P6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P6.setAlignmentY(0.0F);
        P6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P6.setMaximumSize(new java.awt.Dimension(284, 150));
        P6.setMinimumSize(new java.awt.Dimension(284, 150));
        P6.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P6);
        P6.setBounds(351, 434, 284, 150);

        P7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P7.setAlignmentY(0.0F);
        P7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P7.setMaximumSize(new java.awt.Dimension(284, 150));
        P7.setMinimumSize(new java.awt.Dimension(284, 150));
        P7.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P7);
        P7.setBounds(668, 434, 284, 150);

        P8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P8.setAlignmentY(0.0F);
        P8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P8.setMaximumSize(new java.awt.Dimension(284, 150));
        P8.setMinimumSize(new java.awt.Dimension(284, 150));
        P8.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P8);
        P8.setBounds(985, 434, 284, 150);

        P9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P9.setAlignmentY(0.0F);
        P9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P9.setMaximumSize(new java.awt.Dimension(284, 150));
        P9.setMinimumSize(new java.awt.Dimension(284, 150));
        P9.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P9);
        P9.setBounds(34, 601, 284, 150);

        P10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P10.setAlignmentY(0.0F);
        P10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P10.setMaximumSize(new java.awt.Dimension(284, 150));
        P10.setMinimumSize(new java.awt.Dimension(284, 150));
        P10.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P10);
        P10.setBounds(351, 601, 284, 150);

        P11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P11.setAlignmentY(0.0F);
        P11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P11.setMaximumSize(new java.awt.Dimension(284, 150));
        P11.setMinimumSize(new java.awt.Dimension(284, 150));
        P11.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P11);
        P11.setBounds(668, 601, 284, 150);

        P12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P12.setAlignmentY(0.0F);
        P12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        P12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        P12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        P12.setMaximumSize(new java.awt.Dimension(284, 150));
        P12.setMinimumSize(new java.awt.Dimension(284, 150));
        P12.setPreferredSize(new java.awt.Dimension(284, 150));
        grilla.add(P12);
        P12.setBounds(985, 601, 284, 150);

        up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up.png"))); // NOI18N
        up.setAlignmentY(0.0F);
        up.setBorder(null);
        up.setBorderPainted(false);
        up.setContentAreaFilled(false);
        up.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        up.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        up.setMaximumSize(new java.awt.Dimension(64, 64));
        up.setMinimumSize(new java.awt.Dimension(64, 64));
        up.setPreferredSize(new java.awt.Dimension(64, 64));
        up.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upP.png"))); // NOI18N
        grilla.add(up);
        up.setBounds(1285, 267, 64, 64);

        down.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down.png"))); // NOI18N
        down.setAlignmentY(0.0F);
        down.setAutoscrolls(true);
        down.setBorder(null);
        down.setBorderPainted(false);
        down.setContentAreaFilled(false);
        down.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        down.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        down.setMaximumSize(new java.awt.Dimension(64, 64));
        down.setMinimumSize(new java.awt.Dimension(64, 64));
        down.setPreferredSize(new java.awt.Dimension(64, 64));
        down.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/downP.png"))); // NOI18N
        grilla.add(down);
        down.setBounds(1285, 687, 64, 64);

        gfondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gfondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoPeces.jpg")); // NOI18N
        gfondo.setMaximumSize(new java.awt.Dimension(1366, 768));
        gfondo.setMinimumSize(new java.awt.Dimension(1366, 768));
        gfondo.setPreferredSize(new java.awt.Dimension(1366, 768));
        grilla.add(gfondo);
        gfondo.setBounds(0, 0, 1366, 768);

        jLayeredPane1.add(grilla);
        grilla.setBounds(0, 0, 1366, 768);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMouseClicked
        // TODO add your handling code here:
        tittle.setText(names[ contador]);
        Image foto = Imagenes[ contador].getScaledInstance(500, (int) ((buffer[ contador].getHeight() * 500) / buffer[ contador].getWidth()), Image.SCALE_DEFAULT);
        seleccionado.setIcon(new ImageIcon(foto));
        this.setContentPane(seleccion);
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
//                File file = new File("c:\\acuario/video/demo.html");
//                Desktop.getDesktop().open(file);
//            ficha.VideoPrincipal("file:///c:/acuario/video/acuario.mpg", 1920, 1080);
//            ficha.videoPane.setLocation(0,0);
//            ficha.video.setLocation(0, 0);
            ficha.reproducirPrincipal();
            ficha.setContentPane(ficha.videoPane);
            controlSegunda = false;
        }
    }//GEN-LAST:event_videoMouseClicked

    private void pecesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pecesMouseClicked
        // TODO add your handling code here:
        y = 0;
        controlInactividad = true;
        this.setContentPane(tactil);
        ficha.videoPane.setVisible(false);
        if (!controlSegunda) {
            comenzarFicha();
            cargarComponentes();
            //cerrarVideo();
            controlSegunda = true;
        }
        ficha.setContentPane(ficha.visor);
    }//GEN-LAST:event_pecesMouseClicked

    private void prevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevMouseClicked
        // TODO add your handling code here:
        contador--;
        if (contador == -1) {
            contador = ids.length - 1;
        }
        cargarComponentes();
    }//GEN-LAST:event_prevMouseClicked

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        // TODO add your handling code here:
        contador++;
        if (contador == ids.length) {
            contador = 0;
        }
        cargarComponentes();
    }//GEN-LAST:event_nextMouseClicked

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        // TODO add your handling code here:
        this.setContentPane(menu);
        ficha.setContentPane(ficha.inicial);
    }//GEN-LAST:event_cerrarMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        ficha.setContentPane(ficha.visor);
        this.setContentPane(tactil);
        control = true;
    }//GEN-LAST:event_closeMouseClicked

    private void nombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombresMouseClicked
        // TODO add your handling code here:
        if (control) {
            iniciarFicha();
            control = false;
        }
        try {
            
            ficha.setContentPane(ficha.datos);
            ficha.getNombres(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_nombresMouseClicked

    private void biotopoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_biotopoMouseClicked
        // TODO add your handling code here:
        if (control) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getBiotopo(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_biotopoMouseClicked

    private void distribucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distribucionMouseClicked
        // TODO add your handling code here:
        if (control) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getDistribucion(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_distribucionMouseClicked

    private void formaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formaMouseClicked
        // TODO add your handling code here:
        if (control) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getForma(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formaMouseClicked

    private void tamanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tamanoMouseClicked
        // TODO add your handling code here:
        if (control) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getTamano(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tamanoMouseClicked

    private void temperaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_temperaturaMouseClicked
        // TODO add your handling code here:
        if (control) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getTempreratura(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_temperaturaMouseClicked

    private void alimentacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alimentacionMouseClicked
        // TODO add your handling code here:
        if (control) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getAlimentacion(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_alimentacionMouseClicked

    private void comportamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comportamientoMouseClicked
        // TODO add your handling code here:
        if (control) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getComportamiento(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comportamientoMouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
        ficha.setContentPane(ficha.inicial);
        this.setContentPane(menu);
        control = true;
    }//GEN-LAST:event_homeMouseClicked

    private void mfondoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mfondoMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {
            comenzarFicha();
            cargarComponentes();
//            cerrarVideo();
            controlInactividad = true;
            controlSegunda = true;
        }
    }//GEN-LAST:event_mfondoMouseClicked

    private void bannerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bannerMouseClicked
        // TODO add your handling code here:
        y = 0;
        if (!controlInactividad) {
            comenzarFicha();
            cargarComponentes();
//            cerrarVideo();
            controlInactividad = true;
            controlSegunda = true;
        }
    }//GEN-LAST:event_bannerMouseClicked

    private void gcerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gcerrarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_gcerrarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel P1;
    private javax.swing.JLabel P10;
    private javax.swing.JLabel P11;
    private javax.swing.JLabel P12;
    private javax.swing.JLabel P2;
    private javax.swing.JLabel P3;
    private javax.swing.JLabel P4;
    private javax.swing.JLabel P5;
    private javax.swing.JLabel P6;
    private javax.swing.JLabel P7;
    private javax.swing.JLabel P8;
    private javax.swing.JLabel P9;
    private javax.swing.JButton alimentacion;
    private javax.swing.JLabel backslider;
    private javax.swing.JLabel banner;
    private javax.swing.JButton bcarrusel;
    private javax.swing.JButton bgrilla;
    private javax.swing.JButton biotopo;
    private javax.swing.JButton cerrar;
    private javax.swing.JButton close;
    private javax.swing.JButton comportamiento;
    private javax.swing.JButton distribucion;
    private javax.swing.JButton down;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton forma;
    private javax.swing.JLabel fseleccion;
    private javax.swing.JButton gcerrar;
    private javax.swing.JLabel gfondo;
    private javax.swing.JPanel grilla;
    private javax.swing.JLabel gtitulo;
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
    private javax.swing.JButton up;
    private javax.swing.JButton video;
    // End of variables declaration//GEN-END:variables
}
