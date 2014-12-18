/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author EnovaSoft
 */

import DAO.AcuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import objetos.PezVO;

public class Principal {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static void main( String[] args ) throws SQLException, IOException {
        // TODO code application logic here
        AcuarioDAO aDAO = new AcuarioDAO();
        ArrayList<PezVO> peces = aDAO.getImagePrincipalFromFish();
        Inicio inicio = new Inicio( peces );
        inicio.setLocationRelativeTo( null );
        inicio.setContentPane( inicio.menu );
        inicio.setVisible( true );
    }

}