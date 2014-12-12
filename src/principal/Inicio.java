/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.awt.GraphicsEnvironment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ImageIcon Imagenes[] = null;
    String names[] = null;
    int ids[] = null;
    int contador = 0;
    
    public Inicio(ArrayList<PezVO> peces) {
        initComponents();
        Imagenes = new ImageIcon[peces.size()];
        names = new String[peces.size()];
        ids = new int[peces.size()];
        int i = 0;
        for (PezVO pezVO : peces) {
            Imagenes[i] = new ImageIcon(pezVO.getPez_nombComun());
            names[i] = pezVO.getPez_nombre();
            ids[i] = pezVO.getPez_id();
            i++;
        }
        nombre.setText(names[0]);
        slider.setIcon(Imagenes[0]);
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
        tactil = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        prev = new javax.swing.JLabel();
        next = new javax.swing.JLabel();
        slider = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        trasero = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        seleccion = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        seleccionado = new javax.swing.JLabel();
        tittle = new javax.swing.JLabel();
        espaldar = new javax.swing.JLabel();
        nombres = new javax.swing.JLabel();
        biotopo = new javax.swing.JLabel();
        coloracion = new javax.swing.JLabel();
        distribucion = new javax.swing.JLabel();
        forma = new javax.swing.JLabel();
        tamano = new javax.swing.JLabel();
        temperatura = new javax.swing.JLabel();
        agua = new javax.swing.JLabel();
        acuario = new javax.swing.JLabel();
        alimentacion = new javax.swing.JLabel();
        comportamiento = new javax.swing.JLabel();
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

        tactil.setAlignmentX(0.0F);
        tactil.setAlignmentY(0.0F);
        tactil.setMaximumSize(new java.awt.Dimension(1366, 768));
        tactil.setMinimumSize(new java.awt.Dimension(1366, 768));
        tactil.setLayout(null);

        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mundoII.png"))); // NOI18N
        titulo.setAlignmentY(0.0F);
        titulo.setMaximumSize(new java.awt.Dimension(330, 200));
        titulo.setMinimumSize(new java.awt.Dimension(330, 200));
        titulo.setPreferredSize(new java.awt.Dimension(330, 200));
        tactil.add(titulo);
        titulo.setBounds(518, 25, 300, 200);

        prev.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prev.png"))); // NOI18N
        prev.setAlignmentY(0.0F);
        prev.setMaximumSize(new java.awt.Dimension(130, 130));
        prev.setMinimumSize(new java.awt.Dimension(130, 130));
        prev.setPreferredSize(new java.awt.Dimension(130, 130));
        prev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevMouseClicked(evt);
            }
        });
        tactil.add(prev);
        prev.setBounds(100, 382, 130, 130);

        next.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        next.setAlignmentY(0.0F);
        next.setMaximumSize(new java.awt.Dimension(130, 130));
        next.setMinimumSize(new java.awt.Dimension(130, 130));
        next.setPreferredSize(new java.awt.Dimension(130, 130));
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        tactil.add(next);
        next.setBounds(1136, 382, 130, 130);

        slider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slider.setAlignmentY(0.0F);
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

        nombre.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setMaximumSize(new java.awt.Dimension(700, 50));
        nombre.setMinimumSize(new java.awt.Dimension(700, 50));
        nombre.setPreferredSize(new java.awt.Dimension(700, 50));
        tactil.add(nombre);
        nombre.setBounds(333, 625, 700, 50);

        trasero.setBackground(new java.awt.Color(0, 0, 0));
        trasero.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        trasero.setForeground(new java.awt.Color(255, 255, 255));
        trasero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trasero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        trasero.setAlignmentY(0.0F);
        trasero.setMaximumSize(new java.awt.Dimension(700, 50));
        trasero.setMinimumSize(new java.awt.Dimension(700, 50));
        trasero.setPreferredSize(new java.awt.Dimension(700, 50));
        trasero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                traseroMouseClicked(evt);
            }
        });
        tactil.add(trasero);
        trasero.setBounds(333, 625, 700, 50);

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acuario.jpg"))); // NOI18N
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

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        close.setAlignmentY(0.0F);
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

        tittle.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        tittle.setForeground(new java.awt.Color(255, 255, 255));
        tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittle.setAlignmentY(0.0F);
        tittle.setMaximumSize(new java.awt.Dimension(700, 45));
        tittle.setMinimumSize(new java.awt.Dimension(700, 45));
        tittle.setPreferredSize(new java.awt.Dimension(700, 45));
        seleccion.add(tittle);
        tittle.setBounds(333, 75, 700, 45);

        espaldar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        espaldar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        espaldar.setAlignmentY(0.0F);
        espaldar.setMaximumSize(new java.awt.Dimension(700, 45));
        espaldar.setMinimumSize(new java.awt.Dimension(700, 45));
        espaldar.setPreferredSize(new java.awt.Dimension(700, 45));
        seleccion.add(espaldar);
        espaldar.setBounds(333, 75, 700, 45);

        nombres.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        nombres.setForeground(new java.awt.Color(255, 255, 255));
        nombres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombres.setText("NOMBRES Y CLASIFICACIÓN");
        nombres.setAlignmentY(0.0F);
        nombres.setMaximumSize(new java.awt.Dimension(490, 45));
        nombres.setMinimumSize(new java.awt.Dimension(490, 45));
        nombres.setPreferredSize(new java.awt.Dimension(490, 45));
        nombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombresMouseClicked(evt);
            }
        });
        seleccion.add(nombres);
        nombres.setBounds(107, 545, 490, 45);

        biotopo.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        biotopo.setForeground(new java.awt.Color(255, 255, 255));
        biotopo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        biotopo.setText("BIÓTOPO");
        biotopo.setAlignmentY(0.0F);
        biotopo.setMaximumSize(new java.awt.Dimension(180, 45));
        biotopo.setMinimumSize(new java.awt.Dimension(180, 45));
        biotopo.setPreferredSize(new java.awt.Dimension(180, 45));
        seleccion.add(biotopo);
        biotopo.setBounds(703, 545, 180, 45);

        coloracion.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        coloracion.setForeground(new java.awt.Color(255, 255, 255));
        coloracion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coloracion.setText("COLORACIÓN");
        coloracion.setAlignmentY(0.0F);
        coloracion.setMaximumSize(new java.awt.Dimension(250, 45));
        coloracion.setMinimumSize(new java.awt.Dimension(250, 45));
        coloracion.setPreferredSize(new java.awt.Dimension(250, 45));
        seleccion.add(coloracion);
        coloracion.setBounds(354, 610, 250, 45);

        distribucion.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        distribucion.setForeground(new java.awt.Color(255, 255, 255));
        distribucion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        distribucion.setText("DISTRIBUCIÓN");
        distribucion.setAlignmentY(0.0F);
        distribucion.setMaximumSize(new java.awt.Dimension(270, 45));
        distribucion.setMinimumSize(new java.awt.Dimension(270, 45));
        distribucion.setPreferredSize(new java.awt.Dimension(270, 45));
        seleccion.add(distribucion);
        distribucion.setBounds(989, 545, 270, 45);

        forma.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        forma.setForeground(new java.awt.Color(255, 255, 255));
        forma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        forma.setText("FORMA");
        forma.setAlignmentY(0.0F);
        forma.setMaximumSize(new java.awt.Dimension(140, 45));
        forma.setMinimumSize(new java.awt.Dimension(140, 45));
        forma.setPreferredSize(new java.awt.Dimension(140, 45));
        seleccion.add(forma);
        forma.setBounds(107, 610, 140, 45);

        tamano.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        tamano.setForeground(new java.awt.Color(255, 255, 255));
        tamano.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tamano.setText("TAMAÑO");
        tamano.setAlignmentY(0.0F);
        tamano.setMaximumSize(new java.awt.Dimension(170, 45));
        tamano.setMinimumSize(new java.awt.Dimension(170, 45));
        tamano.setPreferredSize(new java.awt.Dimension(170, 45));
        seleccion.add(tamano);
        tamano.setBounds(711, 610, 170, 45);

        temperatura.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        temperatura.setForeground(new java.awt.Color(255, 255, 255));
        temperatura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        temperatura.setText("TEMPERATURA");
        temperatura.setAlignmentY(0.0F);
        temperatura.setMaximumSize(new java.awt.Dimension(270, 45));
        temperatura.setMinimumSize(new java.awt.Dimension(270, 45));
        temperatura.setPreferredSize(new java.awt.Dimension(270, 45));
        seleccion.add(temperatura);
        temperatura.setBounds(988, 610, 270, 45);

        agua.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        agua.setForeground(new java.awt.Color(255, 255, 255));
        agua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        agua.setText("AGUA");
        agua.setAlignmentY(0.0F);
        agua.setMaximumSize(new java.awt.Dimension(120, 45));
        agua.setMinimumSize(new java.awt.Dimension(120, 45));
        agua.setPreferredSize(new java.awt.Dimension(120, 45));
        seleccion.add(agua);
        agua.setBounds(50, 675, 120, 45);

        acuario.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        acuario.setForeground(new java.awt.Color(255, 255, 255));
        acuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acuario.setText("ACUARIO");
        acuario.setAlignmentY(0.0F);
        acuario.setMaximumSize(new java.awt.Dimension(180, 45));
        acuario.setMinimumSize(new java.awt.Dimension(180, 45));
        acuario.setPreferredSize(new java.awt.Dimension(180, 45));
        seleccion.add(acuario);
        acuario.setBounds(285, 675, 180, 45);

        alimentacion.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        alimentacion.setForeground(new java.awt.Color(255, 255, 255));
        alimentacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alimentacion.setText("ALIMENTACIÓN");
        alimentacion.setAlignmentY(0.0F);
        alimentacion.setMaximumSize(new java.awt.Dimension(280, 45));
        alimentacion.setMinimumSize(new java.awt.Dimension(280, 45));
        alimentacion.setPreferredSize(new java.awt.Dimension(280, 45));
        seleccion.add(alimentacion);
        alimentacion.setBounds(585, 675, 280, 45);

        comportamiento.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        comportamiento.setForeground(new java.awt.Color(255, 255, 255));
        comportamiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        comportamiento.setText("COMPORTAMIENTO");
        comportamiento.setAlignmentY(0.0F);
        comportamiento.setMaximumSize(new java.awt.Dimension(340, 45));
        comportamiento.setMinimumSize(new java.awt.Dimension(340, 45));
        comportamiento.setPreferredSize(new java.awt.Dimension(340, 45));
        seleccion.add(comportamiento);
        comportamiento.setBounds(976, 675, 340, 45);

        fseleccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fseleccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fseleccion.jpg"))); // NOI18N
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
        tittle.setText(names[contador]);
        seleccionado.setIcon(Imagenes[contador]);
        this.setContentPane( seleccion );
    }//GEN-LAST:event_sliderMouseClicked

    private void traseroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_traseroMouseClicked
        // TODO add your handling code here:
        sliderMouseClicked( evt );
    }//GEN-LAST:event_traseroMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        this.setContentPane(tactil);
    }//GEN-LAST:event_closeMouseClicked

    private void nombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombresMouseClicked
        // TODO add your handling code here:
        ficha = new Ficha(this, false);
        ficha.setPreferredSize(null);
        try {
            ficha.cargaImagenes(ids[contador]);
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        devices[ 1 ].setFullScreenWindow(ficha);
    }//GEN-LAST:event_nombresMouseClicked

    private void prevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevMouseClicked
        // TODO add your handling code here:
        contador--;
        if (contador == -1) {
            contador = ids.length-1;
        }
        slider.setIcon(Imagenes[contador]);
        nombre.setText(names[contador]);
    }//GEN-LAST:event_prevMouseClicked

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        // TODO add your handling code here:
        contador++;
        if (contador == ids.length) {
            contador = 0;
        }
        System.out.println("conteo " + contador);
        slider.setIcon(Imagenes[contador]);
        nombre.setText(names[contador]);
    }//GEN-LAST:event_nextMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acuario;
    private javax.swing.JLabel agua;
    private javax.swing.JLabel alimentacion;
    private javax.swing.JLabel biotopo;
    private javax.swing.JLabel close;
    private javax.swing.JLabel coloracion;
    private javax.swing.JLabel comportamiento;
    private javax.swing.JLabel distribucion;
    private javax.swing.JLabel espaldar;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel forma;
    private javax.swing.JLabel fseleccion;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel next;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel nombres;
    private javax.swing.JLabel prev;
    private javax.swing.JPanel seleccion;
    private javax.swing.JLabel seleccionado;
    private javax.swing.JLabel slider;
    private javax.swing.JPanel tactil;
    private javax.swing.JLabel tamano;
    private javax.swing.JLabel temperatura;
    private javax.swing.JLabel tittle;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel trasero;
    // End of variables declaration//GEN-END:variables
}
