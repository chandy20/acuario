/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acuario;

import java.util.ArrayList;
import DAO.*;
import java.sql.SQLException;

/**
 *
 * @author EnovaSoft
 */
public class Acuario {

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Tactil tactil = new Tactil();
        tactil.setVisible(true);
//        g.communicator.connect();
//        if (g.communicator.getConnected() == true) {
//            if (g.communicator.initIOStream() == true) {
//                g.communicator.initListener();
//                g.cboxPorts.setVisible(false);
//                g.txtCodigo.requestFocus();
//                g.communicator.getcounter();
//                g.communicator.temporizador();
//            }
//        }
    }

}
