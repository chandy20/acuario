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

    public ArrayList<PezVO> getDatosGenerales(String nombre) throws SQLException {
        ArrayList general = new ArrayList();
        PezVO pVO = new PezVO();
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT p.pez_nombComun, p.pez_nombCientifico, s.subf_descripcion, f.fami_descripcion, o.orde_descripcion FROM pez p INNER JOIN subfamilia s ON s.subf_id = p.subf_id INNER JOIN familia f ON f.fami_id = s.fami_id INNER JOIN orden o ON o.orde_id = f.orde_id WHERE pez_nombre = '" + nombre + "'");
            res = pstm.executeQuery();
            while (res.next()) {
                pVO.setPez_nombComun(res.getString("pez_nombComun"));
                pVO.setPez_nombCientifico(res.getString("pez_nombCientifico"));
                //los siguientes no son los campos reales solo los uso para efectos de mandar la descripcion de las foraneas en un solo objeto
                pVO.setPez_biotopo(res.getString("subf_descripcion")); 
                pVO.setPez_alimentacion(res.getString("fami_descripcion"));
                pVO.setPez_coloracion(res.getString("orde_descripcion"));                
                general.add(pVO);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            stm.close();
            pstm.close();
            res.close();
        }
        cn.desconectar();
        return general;
    }

}
