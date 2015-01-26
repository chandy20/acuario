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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    private Connection connection = Conexion.getConnection();
    
    public Inicio( ArrayList<PezVO> peces ) throws IOException {
        initComponents();
        comenzarFicha();
        llenarVectores( peces );
        cargarComponentes();
        hilo.start();
    }
    
    public void comenzarFicha() {
        ficha = new Ficha( this, false );
        ficha.setPreferredSize( null );
        java.awt.GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        devices[ 1 ].setFullScreenWindow( ficha );
        ficha.setContentPane( ficha.inicial );
    }
    
    public void llenarVectores( ArrayList<PezVO> peces ) throws IOException {
        Imagenes = new Image[ peces.size() ];
        names = new String[ peces.size() ];
        ids = new int[ peces.size() ];
        buffer = new BufferedImage[ peces.size() ];
        int i = 0;
        for ( PezVO pezVO : peces ) {
            Imagenes[ i ] = getToolkit().getImage( pezVO.getPez_nombComun() );
            names[ i ] = pezVO.getPez_nombre();
            ids[ i ] = pezVO.getPez_id();
            buffer[ i ] = ImageIO.read( new File( pezVO.getPez_nombComun() ));
            i++;
        }
    }
    
    public void cargarComponentes() {
        Image foto = Imagenes[ contador ].getScaledInstance(( int )(( buffer[contador].getWidth()*300 )/buffer[contador].getHeight() ), 300, Image.SCALE_DEFAULT );
        slider.setIcon( new ImageIcon( foto ));
        nombre.setText( names[ contador ] );
        ficha.foto.setIcon( new ImageIcon( Imagenes[ contador ]));
        ficha.tittle.setText( names[ contador ]);
    }
    
    public void iniciarInicio() {
        this.setContentPane( this.menu );
    }
    
    public void iniciarFicha() {
        ficha.setContentPane( ficha.datos );
        try {
            ficha.cargaImagenes( ids[ contador ]);
            ficha.cargaNombre( ids[ contador ]);
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
    
    public void cerrarVideo() {
        String cmd = "tskill chrome";
        Process hijo;
        try {
            hijo = Runtime.getRuntime().exec(cmd);
            hijo.waitFor();
            if (hijo.exitValue() == 0) {
                System.out.println("Video cerrado con exito");
            } else {
                System.out.println("Incapaz de cerrar Video. Exit code: " + hijo.exitValue() + "n");
            }
        } catch (IOException e) {
            System.out.println("Incapaz de cerrar Video.");
        } catch (InterruptedException e) {
            System.out.println("Incapaz de cerrar Video.");
        }
    }
    
    Thread hilo = new Thread() {//declaramos el hilo

        public long tiempo(String Fecha, String Fecha2) throws ParseException {
            Calendar calFechaInicial = Calendar.getInstance();
            Calendar calFechaFinal = Calendar.getInstance();
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            calFechaInicial.setTime(df.parse(Fecha));
            calFechaFinal.setTime(df.parse(Fecha2));
            long segundos = ((calFechaFinal.getTimeInMillis() - calFechaInicial.getTimeInMillis()) / 1000);
            return segundos;
        }

        @Override
        public void run() {
            boolean x = true;
            Calendar fecha = new GregorianCalendar();
            String año = Integer.toString(fecha.get(Calendar.YEAR));
            String mes = Integer.toString(fecha.get(Calendar.MONTH));
            String dia = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
            String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
            String minuto = Integer.toString(fecha.get(Calendar.MINUTE));
            String segundo = Integer.toString(fecha.get(Calendar.SECOND));
            String Fecha = año + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo;
            while (x) {
                try {
                    String sql = "select CURRENT_TIMESTAMP as fechaActual";
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    String Fecha2 = "";
                    if (rs.next()) {
                        Fecha2 = rs.getString("fechaActual");
                    }
                    long y = 0;
                    try {
                        y = tiempo(Fecha, Fecha2);
                    } catch (ParseException ex) {
                        System.out.println(ex);
                    }
                    if (y == 5) {
                        x = false;
                        iniciarInicio();
                        run();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
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
        logo = new javax.swing.JLabel();
        video = new javax.swing.JButton();
        vvideo = new javax.swing.JLabel();
        peces = new javax.swing.JButton();
        vpeces = new javax.swing.JLabel();
        banner = new javax.swing.JLabel();
        mfondo = new javax.swing.JLabel();
        tactil = new javax.swing.JPanel();
        cerrar = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        prev = new javax.swing.JButton();
        anterior = new javax.swing.JLabel();
        next = new javax.swing.JButton();
        siguiente = new javax.swing.JLabel();
        slider = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        seleccion = new javax.swing.JPanel();
        close = new javax.swing.JButton();
        seleccionado = new javax.swing.JLabel();
        tittle = new javax.swing.JLabel();
        nombres = new javax.swing.JButton();
        clasificacion = new javax.swing.JButton();
        biotopo = new javax.swing.JButton();
        distribucion = new javax.swing.JButton();
        forma = new javax.swing.JButton();
        coloracion = new javax.swing.JButton();
        tamano = new javax.swing.JButton();
        temperatura = new javax.swing.JButton();
        agua = new javax.swing.JButton();
        acuario = new javax.swing.JButton();
        alimentacion = new javax.swing.JButton();
        comportamiento = new javax.swing.JButton();
        fseleccion = new javax.swing.JLabel();

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

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mundoIII.png"))); // NOI18N
        logo.setAlignmentY(0.0F);
        menu.add(logo);
        logo.setBounds(383, 50, 600, 400);

        video.setForeground(new java.awt.Color(255, 255, 255));
        video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/video.png"))); // NOI18N
        video.setAlignmentY(0.0F);
        video.setBorder(null);
        video.setBorderPainted(false);
        video.setContentAreaFilled(false);
        video.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        video.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        video.setMaximumSize(new java.awt.Dimension(128, 128));
        video.setMinimumSize(new java.awt.Dimension(128, 128));
        video.setPreferredSize(new java.awt.Dimension(128, 128));
        video.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/videoP.png"))); // NOI18N
        video.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                videoMouseClicked(evt);
            }
        });
        menu.add(video);
        video.setBounds(262, 470, 128, 128);

        vvideo.setFont(new java.awt.Font("BoyzRGross", 0, 55)); // NOI18N
        vvideo.setForeground(new java.awt.Color(255, 255, 255));
        vvideo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vvideo.setText("Ver Video");
        vvideo.setAlignmentY(0.0F);
        vvideo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vvideo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vvideoMouseClicked(evt);
            }
        });
        menu.add(vvideo);
        vvideo.setBounds(390, 504, 170, 60);

        peces.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fishI.png"))); // NOI18N
        peces.setAlignmentY(0.0F);
        peces.setBorder(null);
        peces.setBorderPainted(false);
        peces.setContentAreaFilled(false);
        peces.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        peces.setMaximumSize(new java.awt.Dimension(128, 128));
        peces.setMinimumSize(new java.awt.Dimension(128, 128));
        peces.setPreferredSize(new java.awt.Dimension(128, 128));
        peces.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fishIP.png"))); // NOI18N
        peces.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pecesMouseClicked(evt);
            }
        });
        menu.add(peces);
        peces.setBounds(822, 470, 128, 128);

        vpeces.setFont(new java.awt.Font("BoyzRGross", 0, 55)); // NOI18N
        vpeces.setForeground(new java.awt.Color(255, 255, 255));
        vpeces.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vpeces.setText("Ver Peces");
        vpeces.setAlignmentY(0.0F);
        vpeces.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vpeces.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vpecesMouseClicked(evt);
            }
        });
        menu.add(vpeces);
        vpeces.setBounds(950, 504, 160, 60);

        banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        banner.setIcon(new javax.swing.ImageIcon("C:\\acuario\\social.jpg")); // NOI18N
        banner.setAlignmentY(0.0F);
        menu.add(banner);
        banner.setBounds(0, 648, 1366, 120);

        mfondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mfondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoMenu.jpg")); // NOI18N
        mfondo.setAlignmentY(0.0F);
        mfondo.setMaximumSize(new java.awt.Dimension(1366, 768));
        mfondo.setMinimumSize(new java.awt.Dimension(1366, 768));
        mfondo.setPreferredSize(new java.awt.Dimension(1366, 768));
        menu.add(mfondo);
        mfondo.setBounds(0, 0, 1366, 768);

        jLayeredPane1.add(menu);
        menu.setBounds(0, 0, 1366, 768);

        tactil.setAlignmentX(0.0F);
        tactil.setAlignmentY(0.0F);
        tactil.setMaximumSize(new java.awt.Dimension(1366, 768));
        tactil.setMinimumSize(new java.awt.Dimension(1366, 768));
        tactil.setLayout(null);

        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        cerrar.setAlignmentY(0.0F);
        cerrar.setBorder(null);
        cerrar.setBorderPainted(false);
        cerrar.setContentAreaFilled(false);
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrar.setMaximumSize(new java.awt.Dimension(64, 64));
        cerrar.setMinimumSize(new java.awt.Dimension(64, 64));
        cerrar.setPreferredSize(new java.awt.Dimension(64, 64));
        cerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/closeP.png"))); // NOI18N
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
        });
        tactil.add(cerrar);
        cerrar.setBounds(1290, 10, 64, 64);

        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mundoII.png"))); // NOI18N
        titulo.setAlignmentY(0.0F);
        titulo.setMaximumSize(new java.awt.Dimension(330, 200));
        titulo.setMinimumSize(new java.awt.Dimension(330, 200));
        titulo.setPreferredSize(new java.awt.Dimension(330, 200));
        tactil.add(titulo);
        titulo.setBounds(518, 25, 300, 200);

        prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prev.png"))); // NOI18N
        prev.setAlignmentY(0.0F);
        prev.setBorder(null);
        prev.setBorderPainted(false);
        prev.setContentAreaFilled(false);
        prev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prev.setMaximumSize(new java.awt.Dimension(128, 128));
        prev.setMinimumSize(new java.awt.Dimension(128, 128));
        prev.setPreferredSize(new java.awt.Dimension(128, 128));
        prev.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prevP.png"))); // NOI18N
        prev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevMouseClicked(evt);
            }
        });
        tactil.add(prev);
        prev.setBounds(101, 383, 128, 128);

        anterior.setFont(new java.awt.Font("BoyzRGross", 0, 36)); // NOI18N
        anterior.setForeground(new java.awt.Color(255, 255, 255));
        anterior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anterior.setText("Anterior");
        anterior.setAlignmentY(0.0F);
        anterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                anteriorMouseClicked(evt);
            }
        });
        tactil.add(anterior);
        anterior.setBounds(100, 512, 130, 45);

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        next.setAlignmentY(0.0F);
        next.setBorder(null);
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        next.setMaximumSize(new java.awt.Dimension(128, 128));
        next.setMinimumSize(new java.awt.Dimension(128, 128));
        next.setPreferredSize(new java.awt.Dimension(128, 128));
        next.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nextP.png"))); // NOI18N
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        tactil.add(next);
        next.setBounds(1137, 383, 128, 128);

        siguiente.setFont(new java.awt.Font("BoyzRGross", 0, 36)); // NOI18N
        siguiente.setForeground(new java.awt.Color(255, 255, 255));
        siguiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        siguiente.setText("Siguiente");
        siguiente.setAlignmentY(0.0F);
        siguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        siguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                siguienteMouseClicked(evt);
            }
        });
        tactil.add(siguiente);
        siguiente.setBounds(1136, 512, 130, 45);

        slider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slider.setAlignmentY(0.0F);
        slider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slider.setMaximumSize(new java.awt.Dimension(700, 300));
        slider.setMinimumSize(new java.awt.Dimension(700, 300));
        slider.setPreferredSize(new java.awt.Dimension(700, 300));
        slider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sliderMouseClicked(evt);
            }
        });
        tactil.add(slider);
        slider.setBounds(333, 297, 700, 300);

        nombre.setFont(new java.awt.Font("BoyzRGross", 0, 60)); // NOI18N
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
        nombre.setBounds(333, 625, 700, 70);

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon("C:\\acuario\\fondoPeces.jpg")); // NOI18N
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

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        close.setAlignmentY(0.0F);
        close.setBorder(null);
        close.setBorderPainted(false);
        close.setContentAreaFilled(false);
        close.setMaximumSize(new java.awt.Dimension(64, 64));
        close.setMinimumSize(new java.awt.Dimension(64, 64));
        close.setPreferredSize(new java.awt.Dimension(64, 64));
        close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/closeP.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        seleccion.add(close);
        close.setBounds(1290, 10, 64, 64);

        seleccionado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seleccionado.setAlignmentY(0.0F);
        seleccionado.setMaximumSize(new java.awt.Dimension(700, 300));
        seleccionado.setMinimumSize(new java.awt.Dimension(700, 300));
        seleccionado.setPreferredSize(new java.awt.Dimension(700, 300));
        seleccion.add(seleccionado);
        seleccionado.setBounds(333, 150, 700, 300);

        tittle.setFont(new java.awt.Font("BoyzRGross", 2, 70)); // NOI18N
        tittle.setForeground(new java.awt.Color(255, 255, 255));
        tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittle.setToolTipText("");
        tittle.setAlignmentY(0.0F);
        tittle.setMaximumSize(new java.awt.Dimension(700, 45));
        tittle.setMinimumSize(new java.awt.Dimension(700, 45));
        tittle.setPreferredSize(new java.awt.Dimension(700, 45));
        seleccion.add(tittle);
        tittle.setBounds(333, 50, 700, 85);

        nombres.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        nombres.setForeground(new java.awt.Color(255, 255, 255));
        nombres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nombres.png"))); // NOI18N
        nombres.setText("Nombres");
        nombres.setAlignmentY(0.0F);
        nombres.setBorder(null);
        nombres.setBorderPainted(false);
        nombres.setContentAreaFilled(false);
        nombres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nombres.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nombres.setIconTextGap(0);
        nombres.setMaximumSize(new java.awt.Dimension(160, 128));
        nombres.setMinimumSize(new java.awt.Dimension(160, 128));
        nombres.setPreferredSize(new java.awt.Dimension(160, 128));
        nombres.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nombresP.png"))); // NOI18N
        nombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombresMouseClicked(evt);
            }
        });
        seleccion.add(nombres);
        nombres.setBounds(110, 460, 160, 128);

        clasificacion.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        clasificacion.setForeground(new java.awt.Color(255, 255, 255));
        clasificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clasificacion.png"))); // NOI18N
        clasificacion.setText("Clasificación");
        clasificacion.setAlignmentY(0.0F);
        clasificacion.setBorder(null);
        clasificacion.setBorderPainted(false);
        clasificacion.setContentAreaFilled(false);
        clasificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clasificacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clasificacion.setIconTextGap(0);
        clasificacion.setMaximumSize(new java.awt.Dimension(256, 128));
        clasificacion.setMinimumSize(new java.awt.Dimension(256, 128));
        clasificacion.setPreferredSize(new java.awt.Dimension(256, 128));
        clasificacion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clasificacionP.png"))); // NOI18N
        clasificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clasificacionMouseClicked(evt);
            }
        });
        seleccion.add(clasificacion);
        clasificacion.setBounds(330, 470, 256, 128);

        biotopo.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        biotopo.setForeground(new java.awt.Color(255, 255, 255));
        biotopo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/biotopo.png"))); // NOI18N
        biotopo.setText("Biótopo");
        biotopo.setAlignmentY(0.0F);
        biotopo.setBorder(null);
        biotopo.setBorderPainted(false);
        biotopo.setContentAreaFilled(false);
        biotopo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        biotopo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        biotopo.setMaximumSize(new java.awt.Dimension(128, 128));
        biotopo.setMinimumSize(new java.awt.Dimension(128, 128));
        biotopo.setPreferredSize(new java.awt.Dimension(128, 128));
        biotopo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/biotopoP.png"))); // NOI18N
        biotopo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                biotopoMouseClicked(evt);
            }
        });
        seleccion.add(biotopo);
        biotopo.setBounds(680, 600, 128, 128);

        distribucion.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        distribucion.setForeground(new java.awt.Color(255, 255, 255));
        distribucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/distribucion.png"))); // NOI18N
        distribucion.setText("Distribución");
        distribucion.setAlignmentY(0.0F);
        distribucion.setBorder(null);
        distribucion.setBorderPainted(false);
        distribucion.setContentAreaFilled(false);
        distribucion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        distribucion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        distribucion.setMaximumSize(new java.awt.Dimension(192, 128));
        distribucion.setMinimumSize(new java.awt.Dimension(192, 128));
        distribucion.setPreferredSize(new java.awt.Dimension(192, 128));
        distribucion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/distribucionP.png"))); // NOI18N
        distribucion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distribucionMouseClicked(evt);
            }
        });
        seleccion.add(distribucion);
        distribucion.setBounds(1140, 460, 192, 128);

        forma.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        forma.setForeground(new java.awt.Color(255, 255, 255));
        forma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forma.png"))); // NOI18N
        forma.setText("Forma");
        forma.setAlignmentY(0.0F);
        forma.setBorder(null);
        forma.setBorderPainted(false);
        forma.setContentAreaFilled(false);
        forma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        forma.setMaximumSize(new java.awt.Dimension(128, 128));
        forma.setMinimumSize(new java.awt.Dimension(128, 128));
        forma.setPreferredSize(new java.awt.Dimension(128, 128));
        forma.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/formaP.png"))); // NOI18N
        forma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formaMouseClicked(evt);
            }
        });
        seleccion.add(forma);
        forma.setBounds(70, 590, 128, 128);

        coloracion.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        coloracion.setForeground(new java.awt.Color(255, 255, 255));
        coloracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coloracion.png"))); // NOI18N
        coloracion.setText("Coloración");
        coloracion.setAlignmentY(0.0F);
        coloracion.setBorder(null);
        coloracion.setBorderPainted(false);
        coloracion.setContentAreaFilled(false);
        coloracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        coloracion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        coloracion.setIconTextGap(0);
        coloracion.setMaximumSize(new java.awt.Dimension(162, 128));
        coloracion.setMinimumSize(new java.awt.Dimension(162, 128));
        coloracion.setPreferredSize(new java.awt.Dimension(162, 128));
        coloracion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coloracionP.png"))); // NOI18N
        coloracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                coloracionMouseClicked(evt);
            }
        });
        seleccion.add(coloracion);
        coloracion.setBounds(670, 480, 162, 128);

        tamano.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        tamano.setForeground(new java.awt.Color(255, 255, 255));
        tamano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tamano.png"))); // NOI18N
        tamano.setText("Tamaño");
        tamano.setAlignmentY(0.0F);
        tamano.setBorder(null);
        tamano.setBorderPainted(false);
        tamano.setContentAreaFilled(false);
        tamano.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tamano.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tamano.setIconTextGap(0);
        tamano.setMaximumSize(new java.awt.Dimension(128, 128));
        tamano.setMinimumSize(new java.awt.Dimension(128, 128));
        tamano.setPreferredSize(new java.awt.Dimension(128, 128));
        tamano.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tamanoP.png"))); // NOI18N
        tamano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tamanoMouseClicked(evt);
            }
        });
        seleccion.add(tamano);
        tamano.setBounds(360, 600, 128, 128);

        temperatura.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        temperatura.setForeground(new java.awt.Color(255, 255, 255));
        temperatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temperatura.png"))); // NOI18N
        temperatura.setText("Temperatura");
        temperatura.setAlignmentY(0.0F);
        temperatura.setBorder(null);
        temperatura.setBorderPainted(false);
        temperatura.setContentAreaFilled(false);
        temperatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        temperatura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        temperatura.setIconTextGap(0);
        temperatura.setMaximumSize(new java.awt.Dimension(216, 128));
        temperatura.setMinimumSize(new java.awt.Dimension(216, 128));
        temperatura.setPreferredSize(new java.awt.Dimension(216, 128));
        temperatura.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temperaturaP.png"))); // NOI18N
        temperatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                temperaturaMouseClicked(evt);
            }
        });
        seleccion.add(temperatura);
        temperatura.setBounds(900, 510, 216, 128);

        agua.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        agua.setForeground(new java.awt.Color(255, 255, 255));
        agua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agua.png"))); // NOI18N
        agua.setText("Agua");
        agua.setAlignmentY(0.0F);
        agua.setBorder(null);
        agua.setBorderPainted(false);
        agua.setContentAreaFilled(false);
        agua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agua.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agua.setIconTextGap(0);
        agua.setMaximumSize(new java.awt.Dimension(128, 128));
        agua.setMinimumSize(new java.awt.Dimension(128, 128));
        agua.setPreferredSize(new java.awt.Dimension(128, 128));
        agua.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aguaP.png"))); // NOI18N
        agua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aguaMouseClicked(evt);
            }
        });
        seleccion.add(agua);
        agua.setBounds(210, 630, 128, 128);

        acuario.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        acuario.setForeground(new java.awt.Color(255, 255, 255));
        acuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acuario.png"))); // NOI18N
        acuario.setText("Acuario");
        acuario.setAlignmentY(0.0F);
        acuario.setBorder(null);
        acuario.setBorderPainted(false);
        acuario.setContentAreaFilled(false);
        acuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        acuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        acuario.setIconTextGap(0);
        acuario.setMaximumSize(new java.awt.Dimension(128, 128));
        acuario.setMinimumSize(new java.awt.Dimension(128, 128));
        acuario.setPreferredSize(new java.awt.Dimension(128, 128));
        acuario.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acuarioP.png"))); // NOI18N
        acuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acuarioMouseClicked(evt);
            }
        });
        seleccion.add(acuario);
        acuario.setBounds(520, 630, 128, 128);

        alimentacion.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        alimentacion.setForeground(new java.awt.Color(255, 255, 255));
        alimentacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alimentacion.png"))); // NOI18N
        alimentacion.setText("Alimentación");
        alimentacion.setAlignmentY(0.0F);
        alimentacion.setBorder(null);
        alimentacion.setBorderPainted(false);
        alimentacion.setContentAreaFilled(false);
        alimentacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        alimentacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alimentacion.setIconTextGap(0);
        alimentacion.setMaximumSize(new java.awt.Dimension(200, 128));
        alimentacion.setMinimumSize(new java.awt.Dimension(200, 128));
        alimentacion.setPreferredSize(new java.awt.Dimension(200, 128));
        alimentacion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alimentacionP.png"))); // NOI18N
        alimentacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alimentacionMouseClicked(evt);
            }
        });
        seleccion.add(alimentacion);
        alimentacion.setBounds(840, 630, 200, 128);

        comportamiento.setFont(new java.awt.Font("BoyzRGross", 0, 50)); // NOI18N
        comportamiento.setForeground(new java.awt.Color(255, 255, 255));
        comportamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/comportamiento.png"))); // NOI18N
        comportamiento.setText("Comportamiento");
        comportamiento.setAlignmentY(0.0F);
        comportamiento.setBorder(null);
        comportamiento.setBorderPainted(false);
        comportamiento.setContentAreaFilled(false);
        comportamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comportamiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        comportamiento.setIconTextGap(0);
        comportamiento.setMaximumSize(new java.awt.Dimension(256, 128));
        comportamiento.setMinimumSize(new java.awt.Dimension(256, 128));
        comportamiento.setPreferredSize(new java.awt.Dimension(256, 128));
        comportamiento.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/comportamientoP.png"))); // NOI18N
        comportamiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comportamientoMouseClicked(evt);
            }
        });
        seleccion.add(comportamiento);
        comportamiento.setBounds(1080, 590, 256, 128);

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
        tittle.setText( names[ contador ]);
        Image foto = Imagenes[ contador ].getScaledInstance(( int )(( buffer[ contador ].getWidth()*300 )/buffer[ contador ].getHeight() ), 300, Image.SCALE_DEFAULT );
        seleccionado.setIcon( new ImageIcon( foto ));
        this.setContentPane( seleccion );
    }//GEN-LAST:event_sliderMouseClicked

    private void nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreMouseClicked
        // TODO add your handling code here:
        sliderMouseClicked( evt );
    }//GEN-LAST:event_nombreMouseClicked

    private void vpecesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vpecesMouseClicked
        // TODO add your handling code here:
        pecesMouseClicked( evt );
    }//GEN-LAST:event_vpecesMouseClicked

    private void siguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_siguienteMouseClicked
        // TODO add your handling code here:
        nextMouseClicked( evt );
    }//GEN-LAST:event_siguienteMouseClicked

    private void anteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anteriorMouseClicked
        // TODO add your handling code here:
        prevMouseClicked( evt );
    }//GEN-LAST:event_anteriorMouseClicked

    private void vvideoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vvideoMouseClicked
        // TODO add your handling code here:
        videoMouseClicked( evt );
    }//GEN-LAST:event_vvideoMouseClicked

    private void videoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_videoMouseClicked
        // TODO add your handling code here:
        try {
            File file = new File( "c:\\acuario/video/demo.html" );
            Desktop.getDesktop().open(file);
            ficha.dispose();
            controlSegunda = false;
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_videoMouseClicked

    private void pecesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pecesMouseClicked
        // TODO add your handling code here:
        this.setContentPane( tactil );
        if ( !controlSegunda ) {
            comenzarFicha();
            cargarComponentes();
            cerrarVideo();
            controlSegunda = true;
        }
        ficha.setContentPane( ficha.visor );
    }//GEN-LAST:event_pecesMouseClicked

    private void prevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevMouseClicked
        // TODO add your handling code here:
        contador--;
        if ( contador == -1 ) {
            contador = ids.length-1;
        }
        cargarComponentes();
    }//GEN-LAST:event_prevMouseClicked

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        // TODO add your handling code here:
        contador++;
        if ( contador == ids.length ) {
            contador = 0;
        }
        cargarComponentes();
    }//GEN-LAST:event_nextMouseClicked

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        // TODO add your handling code here:
        this.setContentPane( menu );
        ficha.setContentPane( ficha.inicial );
    }//GEN-LAST:event_cerrarMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        ficha.setContentPane( ficha.visor );
        this.setContentPane( tactil );
        control = true;
    }//GEN-LAST:event_closeMouseClicked

    private void nombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombresMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getNombres( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_nombresMouseClicked

    private void biotopoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_biotopoMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getBiotopo( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_biotopoMouseClicked

    private void distribucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distribucionMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getDistribucion( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_distribucionMouseClicked

    private void formaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formaMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getForma( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_formaMouseClicked

    private void coloracionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coloracionMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getColoracion( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_coloracionMouseClicked

    private void tamanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tamanoMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getTamano( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_tamanoMouseClicked

    private void temperaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_temperaturaMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getTempreratura( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_temperaturaMouseClicked

    private void aguaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aguaMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getAgua( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_aguaMouseClicked

    private void acuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acuarioMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getAcuario( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_acuarioMouseClicked

    private void alimentacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alimentacionMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getAlimentacion( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_alimentacionMouseClicked

    private void comportamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comportamientoMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getComportamiento( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_comportamientoMouseClicked

    private void clasificacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clasificacionMouseClicked
        // TODO add your handling code here:
        if ( control ) {
            iniciarFicha();
            control = false;
        }
        try {
            ficha.getClasificacion( ids[contador] );
        } catch ( SQLException ex ) {
            Logger.getLogger( Inicio.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//GEN-LAST:event_clasificacionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acuario;
    private javax.swing.JButton agua;
    private javax.swing.JButton alimentacion;
    private javax.swing.JLabel anterior;
    private javax.swing.JLabel banner;
    private javax.swing.JButton biotopo;
    private javax.swing.JButton cerrar;
    private javax.swing.JButton clasificacion;
    private javax.swing.JButton close;
    private javax.swing.JButton coloracion;
    private javax.swing.JButton comportamiento;
    private javax.swing.JButton distribucion;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton forma;
    private javax.swing.JLabel fseleccion;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel logo;
    public javax.swing.JPanel menu;
    public javax.swing.JLabel mfondo;
    private javax.swing.JButton next;
    private javax.swing.JLabel nombre;
    private javax.swing.JButton nombres;
    private javax.swing.JButton peces;
    private javax.swing.JButton prev;
    private javax.swing.JPanel seleccion;
    private javax.swing.JLabel seleccionado;
    private javax.swing.JLabel siguiente;
    private javax.swing.JLabel slider;
    private javax.swing.JPanel tactil;
    private javax.swing.JButton tamano;
    private javax.swing.JButton temperatura;
    private javax.swing.JLabel tittle;
    private javax.swing.JLabel titulo;
    private javax.swing.JButton video;
    private javax.swing.JLabel vpeces;
    private javax.swing.JLabel vvideo;
    // End of variables declaration//GEN-END:variables
}
