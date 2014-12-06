/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acuario;

/**
 *
 * @author Enovasoft
 */
//import java.io.*;
//import java.net.*;
import DAO.AcuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JList;
import objetos.PezVO;

public class Tactil extends javax.swing.JFrame {

    /**
     * Creates new form Tactil
     */
    AcuarioDAO aDAO = new AcuarioDAO();
    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    Ficha ficha = new Ficha();
    ImageIcon Imagenes[] = new ImageIcon[3];
    String nombres[] = new String[3];
    int contador = 0;

    public Tactil() throws SQLException {
        initComponents();
        createObjects();
        id = id();
        name = name();
        int i = 0;
        for (String s : id) {
            Imagenes[i] = new ImageIcon(getClass().getResource("/images/peces/fish" + Integer.parseInt(s) + ".png"));
            i++;
        }
        int j = 0;
        for (String s : name) {
            nombres[j] = s;
            j++;
        }
        visor.setIcon(Imagenes[0]);
        nombre.setText(nombres[0]);
        Connection connection = Conexion.getConnection();
        if (connection != null) {
            System.out.print(connection);
        }
    }

    public ArrayList id() throws SQLException {
        id = aDAO.listarPeces();
        return id;
    }

    public ArrayList name() throws SQLException {
        name = aDAO.listarNombrePeces();
        return name;
    }

    private void createObjects() {
        ficha.setVisible(true);
    }

    //    public void Escuchar() {
//        try {
//            serverAddr = new ServerSocket(2500);
//        } catch (Exception e) {
//            System.err.println("Error creando socket");
//        }
//        while (true) {
//            try {
//                sc = serverAddr.accept(); // esperando conexión
//                InputStream istream = sc.getInputStream();
//                ObjectInput in = new ObjectInputStream(istream);
//                int Estado = (int) in.readObject();
//                Thread.sleep(2000);
//                DataOutputStream ostream = new DataOutputStream(sc.getOutputStream());
//                if (Estado != -1) {
//                    communicator.bloqueaDesbloquea(Estado);
//                    if (estado == 0) {
//                        estado = 1;
//                        jLabel1.setText("Torniquete desbloqueado");
//                    } else {
//                        estado = 0;
//                        jLabel1.setText("Torniquete bloqueado");
//                    }
//                }
//                ostream.writeInt(estado);
//                ostream.flush();
//                sc.close();
//            } catch (Exception e) {
//                System.err.println("excepcion " + e.toString());
//                e.printStackTrace();
//            } // try
//        } // while
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        caracteristicas = new javax.swing.JLabel();
        general = new javax.swing.JLabel();
        habitat = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        anterior = new javax.swing.JLabel();
        next = new javax.swing.JLabel();
        visor = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acuario Mundo Aventura");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);
        jLayeredPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(1366, 768));

        caracteristicas.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        caracteristicas.setForeground(new java.awt.Color(255, 255, 255));
        caracteristicas.setText("CARACTERÍSTICAS");
        jLayeredPane1.add(caracteristicas);
        caracteristicas.setBounds(500, 690, 230, 29);

        general.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        general.setForeground(new java.awt.Color(255, 255, 255));
        general.setText("GENERAL");
        general.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generalMouseClicked(evt);
            }
        });
        jLayeredPane1.add(general);
        general.setBounds(70, 690, 130, 29);

        habitat.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        habitat.setForeground(new java.awt.Color(255, 255, 255));
        habitat.setText("HABITAT Y ALIMENTACIÓN");
        jLayeredPane1.add(habitat);
        habitat.setBounds(940, 690, 340, 29);

        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mundoII.png"))); // NOI18N
        titulo.setAlignmentY(0.0F);
        jLayeredPane1.add(titulo);
        titulo.setBounds(518, 25, 330, 170);

        anterior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prev.png"))); // NOI18N
        anterior.setAlignmentY(0.0F);
        anterior.setRequestFocusEnabled(false);
        anterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                anteriorMouseClicked(evt);
            }
        });
        jLayeredPane1.add(anterior);
        anterior.setBounds(100, 356, 130, 130);

        next.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        next.setAlignmentY(0.0F);
        next.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                nextMouseMoved(evt);
            }
        });
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        jLayeredPane1.add(next);
        next.setBounds(1150, 356, 130, 130);

        visor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        visor.setAlignmentY(0.0F);
        visor.setMaximumSize(new java.awt.Dimension(720, 300));
        visor.setMinimumSize(new java.awt.Dimension(720, 300));
        visor.setPreferredSize(new java.awt.Dimension(720, 300));
        visor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visorMouseClicked(evt);
            }
        });
        jLayeredPane1.add(visor);
        visor.setBounds(330, 245, 720, 300);

        nombre.setBackground(new java.awt.Color(255, 255, 255));
        nombre.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        nombre.setAlignmentY(0.0F);
        nombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(nombre);
        nombre.setBounds(330, 550, 720, 50);

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acuario.jpg"))); // NOI18N
        fondo.setText("general");
        jLayeredPane1.add(fondo);
        fondo.setBounds(0, 0, 1406, 768);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        try {
            // TODO add your handling code here:
            id = aDAO.listarPeces();
        } catch (SQLException ex) {
            Logger.getLogger(Tactil.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (contador == id.size() - 1) {
            contador = -1;
        }
        contador++;
        visor.setIcon(Imagenes[contador]);
        nombre.setText(nombres[contador]);
    }//GEN-LAST:event_nextMouseClicked

    private void anteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anteriorMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            id = aDAO.listarPeces();
        } catch (SQLException ex) {
            Logger.getLogger(Tactil.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (contador == 0) {
            contador = id.size();
        }
        contador--;
        visor.setIcon(Imagenes[contador]);
        nombre.setText(nombres[contador]);
    }//GEN-LAST:event_anteriorMouseClicked

    private void nextMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_nextMouseMoved

    private void visorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_visorMouseClicked

    private void generalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generalMouseClicked
        // TODO add your handling code here:
        ArrayList<PezVO> general = new ArrayList<PezVO>();
        try {
            general = aDAO.getDatosGenerales(nombre.getText());
            String nComun = "", nCient = "", orden = "", familia = "", subfam = "";
            for (PezVO pezVO : general) {
                nComun = pezVO.getPez_nombComun();
                nCient = pezVO.getPez_nombCientifico();
                // los siguientes no son las variables que deberian ser ya que son foraneas cada una de ellas y no estan concebidas dentro del objeto
                // por tal razon uso convenietemente tres variables del objeto con tipo el mismo tipo de dato que las descripciones en las tablas 
                // foraneas.
                orden = pezVO.getPez_coloracion();
                familia = pezVO.getPez_alimentacion();
                subfam = pezVO.getPez_biotopo();
            }
            String texto = "<html><body><div align = 'center'><table><tr><td align='center' style ='font-size:30 px;'><b>NOMBRE</b></td></tr><tr><td align='center' style ='font-size:20 px;'>" + nombre.getText() + "</td></tr>"
                    + "<tr><td align='center' style ='font-size:30 px;'><b>NOMBRE COMÚN</b></td></tr><tr><td align='center' style ='font-size:20 px;'>" + nComun + "</td></tr>"
                    + "<tr><td align='center' style ='font-size:30 px;'><b>NOMBRE CIENTIFICO</b></td></tr><tr><td align='center' style ='font-size:20 px;'>" + nCient + "</td></tr>"
                    + "<tr><td align='center' style ='font-size:30 px;'><b>ORDEN</b></td></tr><tr><td align='center' style ='font-size:20 px;'>Este pez pertenece al orden de los" + orden + "</td></tr>"
                    + "<tr><td align='center' style ='font-size:30 px;'><b>FAMILIA</b></td></tr><tr><td align='center' style ='font-size:20 px;'> es de la familia de los " + familia + "</td></tr>"
                    + "<tr><td align='center' style ='font-size:30 px;'><b>SUBFAMILIA</b></td></tr><tr><td align='center' style ='font-size:20 px;'>cuya subfamilia son los " + subfam + "</td></tr>"
                    + "</table></div></body></html>";
            ficha.informacion.setText(texto);
        } catch (SQLException ex) {
            Logger.getLogger(Tactil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_generalMouseClicked

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Tactil().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Tactil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anterior;
    private javax.swing.JLabel caracteristicas;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel general;
    private javax.swing.JLabel habitat;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel next;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel visor;
    // End of variables declaration//GEN-END:variables

}
