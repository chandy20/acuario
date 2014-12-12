/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Image;
import principal.Conexion;
import java.sql.*;
import java.util.ArrayList;
import objetos.*;
import java.io.*;
import javax.swing.ImageIcon;
import principal.Ficha;

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

    public ArrayList<PezVO> getDatosGenerales(String nombre, int x) throws SQLException {
        ArrayList general = new ArrayList();
        PezVO pVO = new PezVO();
        try {
            String sql = "";
            switch (x) {
                case 1:
                    sql = "SELECT p.pez_nombComun, p.pez_nombCientifico, s.subf_descripcion, f.fami_descripcion, o.orde_descripcion FROM pez p INNER JOIN subfamilia s ON s.subf_id = p.subf_id INNER JOIN familia f ON f.fami_id = s.fami_id INNER JOIN orden o ON o.orde_id = f.orde_id WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 2:
                    sql = "SELECT pez_biotopo FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 3:
                    sql = "SELECT pez_distribucion FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 4:
                    sql = "SELECT pez_coloracion FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 5:
                    sql = "SELECT pez_tamano FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 6:
                    sql = "SELECT pez_temperatura FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 7:
                    sql = "SELECT pez_agua FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 8:
                    sql = "SELECT pez_acuario FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 9:
                    sql = "SELECT pez_alimentacion FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                case 10:
                    sql = "SELECT pez_comportamiento FROM pez WHERE pez_nombre = '" + nombre + "'";
                    break;
                    
                default:
                    throw new AssertionError();
            }
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement(sql);
            res = pstm.executeQuery();
            while (res.next()) {
                switch (x) {
                    case 1:
                        pVO.setPez_nombComun(res.getString("pez_nombComun"));
                        pVO.setPez_nombCientifico(res.getString("pez_nombCientifico"));
                        //los siguientes no son los campos reales solo los uso para efectos de mandar la descripcion de las foraneas en un solo objeto
                        pVO.setPez_biotopo(res.getString("subf_descripcion"));
                        pVO.setPez_alimentacion(res.getString("fami_descripcion"));
                        pVO.setPez_coloracion(res.getString("orde_descripcion"));
                        general.add(pVO);
                        break;
                    case 2:
                        pVO.setPez_biotopo(res.getString("pez_biotopo"));
                        general.add(pVO);
                        break;
                    case 3:
                        pVO.setPez_distribucion(res.getString("pez_distribucion"));
                        general.add(pVO);
                        break;
                    case 4:
                        pVO.setPez_coloracion(res.getString("pez_coloracion"));
                        general.add(pVO);
                        break;
                    case 5:
                        pVO.setPez_tamano(res.getString("pez_tamano"));
                        general.add(pVO);
                        break;
                    case 6:
                        pVO.setPez_tempreatura(res.getString("pez_temperatura"));
                        general.add(pVO);
                        break;
                    case 7:
                        pVO.setPez_agua(res.getString("pez_agua"));
                        general.add(pVO);
                        break;
                    case 8:
                        pVO.setPez_acuario(res.getString("pez_acuario"));
                        general.add(pVO);
                        break;
                    case 9:
                        pVO.setPez_alimentacion(res.getString("pez_alimentacion"));
                        general.add(pVO);
                        break;
                    case 10:
                        pVO.setPez_comportamiento(res.getString("pez_comportamiento"));
                        general.add(pVO);
                        break;
                    default:
                        throw new AssertionError();
                }
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

    public ArrayList<PezVO> getDatosPez(String nombre) throws SQLException {
        ArrayList general = new ArrayList();
        PezVO pVO = new PezVO();
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT pez_biotopo FROM pez WHERE pez_nombre = '" + nombre + "'");
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
    
    public ArrayList<String> getImagesFromFish(int pez_id)throws SQLException{
        ArrayList lista = new ArrayList();
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT foto_ruta FROM foto WHERE pez_id =" + pez_id + " AND tipo = false");
            res = pstm.executeQuery();
            while (res.next()) {
                lista.add(res.getString("foto_ruta"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            stm.close();
            pstm.close();
            res.close();
        }
        cn.desconectar();
        return lista;
    }
    public String getImagePrincipalFromFish(int pez_id)throws SQLException{
        String ruta = null;
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT foto_ruta FROM foto WHERE pez_id =" + pez_id + " AND tipo = true");
            res = pstm.executeQuery();
            while (res.next()) {
                ruta = res.getString("foto_ruta");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            stm.close();
            pstm.close();
            res.close();
        }
        cn.desconectar();
        return ruta;
    }
    
    public void temporizador(ArrayList<String> peces){
    }

}
