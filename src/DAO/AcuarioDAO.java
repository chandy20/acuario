/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import acuario.Conexion;
import java.sql.*;
import java.util.ArrayList;
import objetos.*;
import java.io.*;

/**
 *
 * @author Enovasoft
 */
public class AcuarioDAO {

    Conexion cn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet res;

    public AcuarioDAO() {
        cn = new Conexion();
    }

    //funcion que devuelve el listado de id peces que se encuentran activos en la base de datos
    public ArrayList listarPeces() throws SQLException {
        ArrayList id = new ArrayList();
        String p;
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT pez_id FROM pez WHERE pez_estado = true");
            res = pstm.executeQuery();
            while (res.next()) {
                p = res.getString("pez_id");
                id.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            stm.close();
            pstm.close();
            res.close();
        }
        cn.desconectar();
        return id;
    }

    //funcion que devuelve el listado de nombres peces que se encuentran activos en la base de datos
    public ArrayList listarNombrePeces() throws SQLException {
        ArrayList nombre = new ArrayList();
        String p;
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT pez_nombre FROM pez WHERE pez_estado = true");
            res = pstm.executeQuery();
            while (res.next()) {
                p = res.getString("pez_nombre");
                nombre.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            stm.close();
            pstm.close();
            res.close();
        }
        cn.desconectar();
        return nombre;
    }

}
