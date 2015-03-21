/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.File;
import principal.Conexion;
import java.sql.*;
import java.util.ArrayList;
import objetos.*;

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
            pstm = cn.getConnection().prepareStatement("SELECT pez_id FROM pez WHERE pez_estado = true order by id ASC");
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
            pstm = cn.getConnection().prepareStatement("SELECT pez_nombComun FROM pez WHERE pez_estado = true order by id ASC");
            res = pstm.executeQuery();
            while (res.next()) {
                p = res.getString("pez_nombComun");
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

    public ArrayList<PezVO> getDatosGenerales(int pez_id, int x) throws SQLException {
        ArrayList general = new ArrayList();
        PezVO pVO = new PezVO();
        try {
            String sql = "";
            switch (x) {
                case 1:
                    sql = "SELECT p.pez_nombComun, p.pez_nombCientifico FROM pez p  WHERE pez_id = " + pez_id;
                    break;
//                case 2:
//                    sql = "SELECT pez_biotopo FROM pez WHERE pez_id = " + pez_id;
//                    break;
                case 3:
                    sql = "SELECT pez_distribucion FROM pez WHERE pez_id = " + pez_id;
                    break;
//                case 4:
//                    sql = "SELECT pez_coloracion FROM pez WHERE pez_id = " + pez_id;
//                    break;
//                case 5:
//                    sql = "SELECT pez_tamano FROM pez WHERE pez_id = " + pez_id;
//                    break;
//                case 6:
//                    sql = "SELECT pez_temperatura FROM pez WHERE pez_id = " + pez_id;
//                    break;
//                case 7:
//                    sql = "SELECT pez_agua FROM pez WHERE pez_id = " + pez_id;
//                    break;
//                case 8:
//                    sql = "SELECT pez_acuario FROM pez WHERE pez_id = " + pez_id;
//                    break;
                case 9:
                    sql = "SELECT pez_alimentacion FROM pez WHERE pez_id = " + pez_id;
                    break;
                case 10:
                    sql = "SELECT pez_generalidades FROM pez WHERE pez_id = " + pez_id;
                    break;
                case 11:
                    sql = "SELECT pez_curiosidades FROM pez WHERE pez_id = " + pez_id;
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
                        general.add(pVO);
                        break;
//                    case 2:
//                        pVO.setPez_biotopo(res.getString("pez_biotopo"));
//                        general.add(pVO);
//                        break;
                    case 3:
                        pVO.setPez_distribucion(res.getString("pez_distribucion"));
                        general.add(pVO);
                        break;
//                    case 4:
//                        pVO.setPez_coloracion(res.getString("pez_coloracion"));
//                        general.add(pVO);
//                        break;
//                    case 5:
//                        pVO.setPez_tamano(res.getString("pez_tamano"));
//                        general.add(pVO);
//                        break;
//                    case 6:
//                        pVO.setPez_tempreatura(res.getString("pez_temperatura"));
//                        general.add(pVO);
//                        break;
//                    case 7:
//                        pVO.setPez_agua(res.getString("pez_agua"));
//                        general.add(pVO);
//                        break;
//                    case 8:
//                        pVO.setPez_acuario(res.getString("pez_acuario"));
//                        general.add(pVO);
//                        break;
                    case 9:
                        pVO.setPez_alimentacion(res.getString("pez_alimentacion"));
                        general.add(pVO);
                        break;
                    case 10:
                        pVO.setPez_generalidades(res.getString("pez_generalidades"));
                        general.add(pVO);
                        break;
                    case 11:
                        pVO.setPez_curiosidades(res.getString("pez_curiosidades"));
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

    public String getPezName(int pez_id) throws SQLException {
        String name = null;
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT pez_nombComun FROM pez WHERE pez_estado = true AND pez_id = " + pez_id);
            res = pstm.executeQuery();
            while (res.next()) {
                name = res.getString("pez_nombComun");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            stm.close();
            pstm.close();
            res.close();
        }
        cn.desconectar();
        return name;
    }

    public ArrayList<PezVO> getDatosPez(String nombre) throws SQLException {
        ArrayList general = new ArrayList();
        PezVO pVO = new PezVO();
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT pez_biotopo FROM pez WHERE pez_nombComun = '" + nombre + "'");
            res = pstm.executeQuery();
            while (res.next()) {
                pVO.setPez_nombComun(res.getString("pez_nombComun"));
                pVO.setPez_nombCientifico(res.getString("pez_nombCientifico"));
                //los siguientes no son los campos reales solo los uso para efectos de mandar la descripcion de las foraneas en un solo objeto
               
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

    public ArrayList<String> getImagesFromFish(int pez_id) throws SQLException {
        ArrayList lista = new ArrayList();
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT foto_ruta FROM foto WHERE pez_id =" + pez_id + " AND tipo = false");
            res = pstm.executeQuery();
            while (res.next()) {
                lista.add(res.getString("foto_ruta"));
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        } finally {
            stm.close();
            pstm.close();
            res.close();
        }
        cn.desconectar();
        return lista;
    }

    public ArrayList<PezVO> getInfoFromFish() throws SQLException {
        PezVO pezVO = null;
        ArrayList<PezVO> lista = new ArrayList<PezVO>();
        try {
            stm = cn.getConnection().createStatement();
            pstm = cn.getConnection().prepareStatement("SELECT pez_id, pez_nombComun, pez_nombCientifico FROM pez WHERE pez_estado = true");
            res = pstm.executeQuery();
            while (res.next()) {
                pezVO = new PezVO();
                pezVO.setPez_id(res.getInt("pez_id"));
                pezVO.setPez_nombComun(res.getString("pez_nombComun"));
                pezVO.setPez_nombCientifico(res.getString("pez_nombCientifico"));
                lista.add(pezVO);
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
}
