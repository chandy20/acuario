/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import java.io.Serializable;

/**
 *
 * @author Enovasoft
 */
public class PezVO implements Serializable{

    public PezVO() {
    }
    
    private int pez_id;
    private String pez_nombre;
    private String pez_nombComun;
    private String pez_nombCientifico;
    private String pez_biotopo;
    private String pez_distribucion;
    private String pez_rutaDistribucion;
    private String pez_forma;
    private String pez_coloracion;
    private String pez_tamano;
    private String pez_tempreatura;
    private String pez_agua;
    private String pez_alimentacion;
    private String pez_comportamiento;
    private boolean pez_estado;
    private int subf_id;

    public int getPez_id() {
        return pez_id;
    }

    public void setPez_id(int pez_id) {
        this.pez_id = pez_id;
    }

    public String getPez_nombre() {
        return pez_nombre;
    }

    public void setPez_nombre(String pez_nombre) {
        this.pez_nombre = pez_nombre;
    }

    public String getPez_nombComun() {
        return pez_nombComun;
    }

    public void setPez_nombComun(String pez_nombComun) {
        this.pez_nombComun = pez_nombComun;
    }

    public String getPez_nombCientifico() {
        return pez_nombCientifico;
    }

    public void setPez_nombCientifico(String pez_nombCientifico) {
        this.pez_nombCientifico = pez_nombCientifico;
    }

    public String getPez_biotopo() {
        return pez_biotopo;
    }

    public void setPez_biotopo(String pez_biotopo) {
        this.pez_biotopo = pez_biotopo;
    }

    public String getPez_distribucion() {
        return pez_distribucion;
    }

    public void setPez_distribucion(String pez_distribucion) {
        this.pez_distribucion = pez_distribucion;
    }

    public String getPez_rutaDistribucion() {
        return pez_rutaDistribucion;
    }

    public void setPez_rutaDistribucion(String pez_rutaDistribucion) {
        this.pez_rutaDistribucion = pez_rutaDistribucion;
    }

    public String getPez_forma() {
        return pez_forma;
    }

    public void setPez_forma(String pez_forma) {
        this.pez_forma = pez_forma;
    }

    public String getPez_coloracion() {
        return pez_coloracion;
    }

    public void setPez_coloracion(String pez_coloracion) {
        this.pez_coloracion = pez_coloracion;
    }

    public String getPez_tamano() {
        return pez_tamano;
    }

    public void setPez_tamano(String pez_tamano) {
        this.pez_tamano = pez_tamano;
    }

    public String getPez_tempreatura() {
        return pez_tempreatura;
    }

    public void setPez_tempreatura(String pez_tempreatura) {
        this.pez_tempreatura = pez_tempreatura;
    }

    public String getPez_agua() {
        return pez_agua;
    }

    public void setPez_agua(String pez_agua) {
        this.pez_agua = pez_agua;
    }

    public String getPez_alimentacion() {
        return pez_alimentacion;
    }

    public void setPez_alimentacion(String pez_alimentacion) {
        this.pez_alimentacion = pez_alimentacion;
    }

    public String getPez_comportamiento() {
        return pez_comportamiento;
    }

    public void setPez_comportamiento(String pez_comportamiento) {
        this.pez_comportamiento = pez_comportamiento;
    }

    public boolean isPez_estado() {
        return pez_estado;
    }

    public void setPez_estado(boolean pez_estado) {
        this.pez_estado = pez_estado;
    }

    public int getSubf_id() {
        return subf_id;
    }

    public void setSubf_id(int subf_id) {
        this.subf_id = subf_id;
    }    
    
}
