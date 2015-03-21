/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import DAO.AcuarioDAO;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
    //boolean control = true;
    boolean controlSegunda = true;
    boolean controlInactividad = true;
    long timep1 = System.currentTimeMillis();
    //variables de ficha
    public Player player;
    public Component videop;
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
    long timep2 = 0;
    long timea = 0;
//    int principal = 0;
    ArrayList<PezVO> lista = new ArrayList<PezVO>();

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
//        setPreferredSize(null);
//        java.awt.GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
//        devices[ 1].setFullScreenWindow(ficha);
//        setContentPane(inicial);
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
        controlSegunda = false;
        RemovePanel();
        try {
            VideoPrincipal("file:///c:/acuario/video/promo.mpg");

        } catch (OutOfMemoryError e) {
            System.out.println("iniciarInicio");
            System.gc();
            VideoPrincipal("file:///c:/acuario/video/promo.mpg");
        }
        this.setContentPane(this.videoPane);
    }

    public void iniciarFicha() {
//        setContentPane(datos);
        try {
//            cargaImagenes(ids[ contador]);
            cargaNombre(ids[ contador]);
            RemovePanel();
            try {
                VideoInfo("c:/acuario/" + String.valueOf(ids[contador]) + "/videos/general.mpg");
            } catch (OutOfMemoryError e) {
                System.gc();
                VideoInfo("c:/acuario/" + String.valueOf(ids[contador]) + "/videos/general.mpg");
            }

            reproducir();

        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarVideo() {
        verVideo = false;
        RemovePanel();
//        VideoPrincipal("file:///c:/acuario/video/promo.mpg");
//        reproducirPrincipal();
        this.setContentPane(menu);
    }

    Thread hilo = new Thread() {//declaramos el hilo

        @Override
        public void run() {
            timep1 = System.currentTimeMillis();
            while (true) {
                try {
                    //ciclo infinito
                    timep2 = System.currentTimeMillis();
                    timea = ((timep2 - timep1) / 1000);

                    System.out.println("y: " + timea);
                    if (timea >= 1000) {
                        System.out.println("inicia inactividad control: " + controlInactividad + " control segunda:" + controlSegunda);
                        controlInactividad = false;
                        if (controlSegunda) {
                            System.out.println("INICIO DE INACTIVIDAD");
                            iniciarInicio();
                        }
                        System.out.println("tiempo actual" + player.getMediaTime().getSeconds());
                        if (player.getMediaTime().getSeconds() >= 29) {
                            System.out.println("tiempo total de la inactividad " + player.getDuration().getSeconds() + " timepo actual " + player.getMediaTime().getSeconds());
                            System.out.println("reincia video");
                            iniciarInicio();
                        }
                    }
                    hilo.sleep(1000);//que duerma un segundo

                } catch (InterruptedException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }

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
        videoPane = new javax.swing.JPanel();
        videoPeces = new javax.swing.JPanel();
        videoDetalle = new javax.swing.JPanel();
        datos = new javax.swing.JPanel();
        atracito = new javax.swing.JButton();
        homecito = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        titulito = new javax.swing.JLabel();
        barra = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        fondo1 = new javax.swing.JLabel();
        fondo2 = new javax.swing.JLabel();

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
        close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atrasP.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        seleccion.add(close);
        close.setBounds(183, 33, 140, 140);

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicio.png"))); // NOI18N
        home.setAlignmentY(0.0F);
        home.setBorderPainted(false);
        home.setContentAreaFilled(false);
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        home.setFocusPainted(false);
        home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        home.setMaximumSize(new java.awt.Dimension(140, 140));
        home.setMinimumSize(new java.awt.Dimension(140, 140));
        home.setPreferredSize(new java.awt.Dimension(140, 140));
        home.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicioP.png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        seleccion.add(home);
        home.setBounds(33, 33, 140, 140);

        seleccionado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seleccionado.setAlignmentY(0.0F);
        seleccionado.setMaximumSize(new java.awt.Dimension(700, 490));
        seleccionado.setMinimumSize(new java.awt.Dimension(700, 490));
        seleccionado.setPreferredSize(new java.awt.Dimension(700, 490));
        seleccion.add(seleccionado);
        seleccionado.setBounds(610, 227, 700, 490);

        tittle.setFont(new java.awt.Font("Harabara", 1, 170)); // NOI18N
        tittle.setForeground(new java.awt.Color(255, 247, 152));
        tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittle.setToolTipText("");
        tittle.setAlignmentY(0.0F);
        tittle.setMaximumSize(new java.awt.Dimension(1200, 150));
        tittle.setMinimumSize(new java.awt.Dimension(1200, 150));
        tittle.setPreferredSize(new java.awt.Dimension(1200, 150));
        seleccion.add(tittle);
        tittle.setBounds(360, 25, 1300, 150);

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
        nombres.setBounds(33, 474, 270, 270);

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
        biotopo.setBounds(1301, 777, 270, 270);

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
        distribucion.setBounds(33, 777, 270, 270);

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
        forma.setBounds(350, 777, 270, 270);

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
        tamano.setBounds(667, 777, 270, 270);

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
        temperatura.setBounds(1618, 474, 270, 270);

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
        alimentacion.setBounds(1618, 777, 270, 270);

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
        comportamiento.setBounds(984, 777, 270, 270);

        fseleccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fseleccion.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoPeces.jpg")); // NOI18N
        fseleccion.setAlignmentY(0.0F);
        seleccion.add(fseleccion);
        fseleccion.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(seleccion);
        seleccion.setBounds(0, 0, 1920, 1080);

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

        datos.setAlignmentX(0.0F);
        datos.setAlignmentY(0.0F);
        datos.setLayout(null);

        atracito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atrasVideo.png"))); // NOI18N
        atracito.setAlignmentY(0.0F);
        atracito.setBorder(null);
        atracito.setBorderPainted(false);
        atracito.setContentAreaFilled(false);
        atracito.setFocusPainted(false);
        atracito.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atrasPideoP.png"))); // NOI18N
        atracito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atracitoMouseClicked(evt);
            }
        });
        datos.add(atracito);
        atracito.setBounds(190, -20, 140, 140);

        homecito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicioVideos.png"))); // NOI18N
        homecito.setAlignmentY(0.0F);
        homecito.setBorderPainted(false);
        homecito.setContentAreaFilled(false);
        homecito.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        homecito.setFocusPainted(false);
        homecito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homecito.setMaximumSize(new java.awt.Dimension(140, 140));
        homecito.setMinimumSize(new java.awt.Dimension(140, 140));
        homecito.setPreferredSize(new java.awt.Dimension(140, 140));
        homecito.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicioVideosP.png"))); // NOI18N
        homecito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homecitoMouseClicked(evt);
            }
        });
        homecito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homecitoActionPerformed(evt);
            }
        });
        datos.add(homecito);
        homecito.setBounds(40, -20, 140, 140);

        titulo.setFont(new java.awt.Font("Harabara", 0, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo.setAlignmentY(0.0F);
        titulo.setMaximumSize(new java.awt.Dimension(720, 95));
        titulo.setMinimumSize(new java.awt.Dimension(720, 95));
        titulo.setPreferredSize(new java.awt.Dimension(720, 95));
        datos.add(titulo);
        titulo.setBounds(600, 20, 720, 50);

        titulito.setFont(new java.awt.Font("Bitter", 0, 40)); // NOI18N
        titulito.setForeground(new java.awt.Color(255, 247, 152));
        titulito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulito.setToolTipText("");
        titulito.setAlignmentY(0.0F);
        titulito.setMaximumSize(new java.awt.Dimension(1360, 95));
        titulito.setMinimumSize(new java.awt.Dimension(1360, 95));
        titulito.setPreferredSize(new java.awt.Dimension(1360, 95));
        datos.add(titulito);
        titulito.setBounds(20, 210, 1360, 40);

        barra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        barra.setAlignmentY(0.0F);
        datos.add(barra);
        barra.setBounds(20, 80, 720, 15);

        info.setFont(new java.awt.Font("Gandhi Sans", 0, 35)); // NOI18N
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        info.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        info.setAlignmentY(0.0F);
        info.setMaximumSize(new java.awt.Dimension(1360, 250));
        info.setMinimumSize(new java.awt.Dimension(1360, 250));
        info.setName(""); // NOI18N
        info.setPreferredSize(new java.awt.Dimension(1360, 250));
        datos.add(info);
        info.setBounds(20, 260, 1360, 270);

        fondo1.setBackground(new java.awt.Color(204, 204, 0));
        fondo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo1.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoSegundaDatos.jpg")); // NOI18N
        fondo1.setAlignmentY(0.0F);
        datos.add(fondo1);
        fondo1.setBounds(0, 0, 1920, 1080);

        fondo2.setBackground(new java.awt.Color(204, 204, 0));
        fondo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo2.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoArriba.jpg")); // NOI18N
        fondo2.setAlignmentY(0.0F);
        datos.add(fondo2);
        fondo2.setBounds(0, 0, 1920, 1080);

        jLayeredPane1.add(datos);
        datos.setBounds(0, 0, 1920, 540);

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
        timep1 = System.currentTimeMillis();
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
//        videoPane
        timep1 = System.currentTimeMillis();
        controlInactividad = true;
        if (controlSegunda) {
            RemovePanel();
            try {
                VideoDetalle("file:///c:/acuario/video/promo.mpg");///url video promociones
            } catch (OutOfMemoryError e) {
                System.gc();
                VideoDetalle("file:///c:/acuario/video/promo.mpg");///url video promociones
            }
//             reproducirPrincipal();
            setContentPane(videoDetalle);
            controlSegunda = false;
        }
    }//GEN-LAST:event_videoMouseClicked

    private void pecesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pecesMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        controlInactividad = true;
        verVideo = true;
        this.setContentPane(this.tactil);
        cargarComponentes();
        if (!controlSegunda) {
            cerrarVideo();
            controlSegunda = true;
        }
//        setContentPane(visor);
    }//GEN-LAST:event_pecesMouseClicked

    private void prevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
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
        timep1 = System.currentTimeMillis();
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
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
            this.setContentPane(menu);
        }
    }//GEN-LAST:event_cerrarMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {
            controlInactividad = true;
            controlSegunda = true;

        } else {
//            setContentPane(visor);
            this.setContentPane(tactil);
//            control = true;
        }

    }//GEN-LAST:event_closeMouseClicked

    private void nombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombresMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {
            controlInactividad = true;
            controlSegunda = true;
        } else {
//            if (control) {
            iniciarFicha();
//                control = false;
//            }
            try {
//            setContentPane(datos);
                setContentPane(videoPeces);
                getNombres(ids[contador]);

            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_nombresMouseClicked

    private void biotopoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_biotopoMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
//            if (control) {
            iniciarFicha();
//                control = false;
//            }
            try {
//            setContentPane(datos);
                setContentPane(videoPeces);
                getForma(ids[contador]);

            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_biotopoMouseClicked

    private void distribucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distribucionMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;

        } else {
            RemovePanel();
            try {
                VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/vivo.mpg"); //vivo.mpg
            } catch (OutOfMemoryError e) {
                System.gc();
                VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/vivo.mpg"); //vivo.mpg
            }
            
//            control = true;
            setContentPane(videoDetalle);
        }
    }//GEN-LAST:event_distribucionMouseClicked

    private void formaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formaMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {
            controlInactividad = true;
            controlSegunda = true;

        } else {
            RemovePanel();
            try {
                VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/cuerpo.mpg");//cuerpo.mpg
            } catch (OutOfMemoryError e) {
                System.gc();
                VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/cuerpo.mpg");//cuerpo.mpg
            }
            
//            control = true;
            setContentPane(videoDetalle);
        }
    }//GEN-LAST:event_formaMouseClicked

    private void tamanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tamanoMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;

        } else {
            RemovePanel();
            try {
                VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/medidas.mpg");//medidas.mpg
            } catch (OutOfMemoryError e) {
                System.gc();
                VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/medidas.mpg");//medidas.mpg
            }
            
//            control = true;
            setContentPane(videoDetalle);
        }
    }//GEN-LAST:event_tamanoMouseClicked

    private void temperaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_temperaturaMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;

        } else {
            RemovePanel();
            try {
                VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/temperatura.mpg");//temperatura.mpg
            } catch (OutOfMemoryError e) {
                System.gc();
                VideoDetalle("file:///c:/acuario/" + String.valueOf(ids[contador]) + "/videos/temperatura.mpg");//temperatura.mpg
            }
            
//            control = true;
            setContentPane(videoDetalle);
        }
    }//GEN-LAST:event_temperaturaMouseClicked

    private void alimentacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alimentacionMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
//            if (control) {
            iniciarFicha();
//                control = false;
//            }
            try {
//            setContentPane(datos);
                setContentPane(videoPeces);
                getAlimentacion(ids[contador]);

            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_alimentacionMouseClicked

    private void comportamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comportamientoMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {

            controlInactividad = true;
            controlSegunda = true;
        } else {
//            if (control) {
            iniciarFicha();
//                control = false;
//            }
            try {
//            setContentPane(datos);
                setContentPane(videoPeces);
                getComportamiento(ids[contador]);

            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_comportamientoMouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
        RemovePanel();
        setContentPane(menu);
        this.setContentPane(menu);
//        control = true;
    }//GEN-LAST:event_homeMouseClicked

    private void mfondoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mfondoMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {
            cerrarVideo();
            controlInactividad = true;
            controlSegunda = true;
        }
        this.setContentPane(menu);
    }//GEN-LAST:event_mfondoMouseClicked

    private void bannerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bannerMouseClicked
        // TODO add your handling code here:
        timep1 = System.currentTimeMillis();
        if (!controlInactividad) {
            cerrarVideo();
            controlInactividad = true;
            controlSegunda = true;
        }
        this.setContentPane(menu);
    }//GEN-LAST:event_bannerMouseClicked

    private void atracitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atracitoMouseClicked
        // TODO add your handling code here:
        RemovePanel();
        this.setContentPane(seleccion);
    }//GEN-LAST:event_atracitoMouseClicked

    private void homecitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homecitoMouseClicked
        // TODO add your handling code here:
        RemovePanel();
        this.setContentPane(menu);
    }//GEN-LAST:event_homecitoMouseClicked

    private void homecitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homecitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homecitoActionPerformed

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
            Logger.getLogger(Ficha.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
//            System.out.println("url: " + new MediaLocator(url));
            player = f1.createRealizedPlayer(new MediaLocator(url));
//            System.out.println("player: " + player);
            videop = player.getVisualComponent();
//            System.out.println("video: " + video);
            videop.setSize(1920, 980);
            videop.setLocation(0, 100);
            videop.setVisible(true);
            if (videop != null) {
                videoPane.add("Center", videop);
            }
//            video.repaint();
            controles = player.getControlPanelComponent();
            System.gc();
            player.start();
            cero = player.getMediaTime();
            videoPane.updateUI();
            tiempo = 0;
            verVideo = true;
            videoPane.add(home);
            videoPane.add(fondo1);
            System.out.println("time secunds " + player.getDuration().getSeconds());

        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Ficha.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            player = f2.createRealizedPlayer(new MediaLocator(url));
            video1 = player.getVisualComponent();
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
            videoPeces.add(homecito);
            videoPeces.add(atracito);
            videoPeces.add(fondo1);
//            tiempogeneral = player1.getDuration().getSeconds() + (0.8);
//            controles1 = player.getControlPanelComponent();

        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RemovePanel() {
        videoPane.removeAll();
        videoDetalle.removeAll();
        videoPeces.removeAll();
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
            Logger.getLogger(Ficha.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            player = f3.createRealizedPlayer(new MediaLocator(url));
//            System.out.println("player: " + player1);
            video2 = player.getVisualComponent();
            video2.setSize(1920, 980);
            video2.setLocation(0, 100);
            video2.setVisible(true);

            if (video2 != null) {
                videoDetalle.add("Center", video2);
            }
//            tiempogeneral = player2.getDuration().getSeconds() + (0.8);
            controles2 = player.getControlPanelComponent();
            videoDetalle.add(homecito);
            videoDetalle.add(atracito);
            videoDetalle.add(fondo2);
            System.gc();
            player.start();
            videoDetalle.updateUI();

        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VideoInfo(int width) {
        String direccion = aux08032015;
        File directorio = new File(direccion);
        System.out.println("direccion " + direccion);
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
            Logger.getLogger(Ficha.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            player = f2.createRealizedPlayer(new MediaLocator(url));
            System.out.println("player: " + player);
            video1 = player.getVisualComponent();
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
            //videoPeces.add(lbl1);
            videoPeces.add(fondo1);
            tiempogeneral = player.getDuration().getSeconds() + (0.8);
//            controles1 = player1.getControlPanelComponent();

        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(Ficha.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    public void reproducirPrincipal() {
////        player.setMediaTime(cero);
//        player.start();
//        videoPane.updateUI();
//        tiempo = 0;
//    }

    public void reproducir() {
//        player1.setMediaTime(cero);
        System.gc();
        player.start();
        videoPeces.updateUI();
        t = 0;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alimentacion;
    private javax.swing.JButton atracito;
    private javax.swing.JLabel backslider;
    private javax.swing.JLabel banner;
    private javax.swing.JLabel barra;
    private javax.swing.JButton biotopo;
    private javax.swing.JButton cerrar;
    private javax.swing.JButton close;
    private javax.swing.JButton comportamiento;
    public javax.swing.JPanel datos;
    private javax.swing.JButton distribucion;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondo2;
    private javax.swing.JButton forma;
    private javax.swing.JLabel fseleccion;
    private javax.swing.JButton home;
    private javax.swing.JButton homecito;
    private javax.swing.JLabel info;
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
    private javax.swing.JLabel titulito;
    private javax.swing.JLabel titulo;
    private javax.swing.JButton video;
    public javax.swing.JPanel videoDetalle;
    public javax.swing.JPanel videoPane;
    public javax.swing.JPanel videoPeces;
    // End of variables declaration//GEN-END:variables
}
