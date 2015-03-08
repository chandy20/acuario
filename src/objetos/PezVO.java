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
    private String pez_nombComun;
    private String pez_nombCientifico;
    private String pez_distribucion;
    private String pez_alimentacion;
    private String pez_cuerpo;
    private String pez_generalidades;
    private String pez_curiosidades;
    private boolean pez_estado;

    public int getPez_id() {
        return pez_id;
    }

    public void setPez_id(int pez_id) {
        this.pez_id = pez_id;
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

    public String getPez_distribucion() {
        return pez_distribucion;
    }

    public void setPez_distribucion(String pez_distribucion) {
        this.pez_distribucion = pez_distribucion;
    }

    public String getPez_alimentacion() {
        return pez_alimentacion;
    }

    public void setPez_alimentacion(String pez_alimentacion) {
        this.pez_alimentacion = pez_alimentacion;
    }

    public String getPez_cuerpo() {
        return pez_cuerpo;
    }

    public void setPez_cuerpo(String pez_cuerpo) {
        this.pez_cuerpo = pez_cuerpo;
    }

    public String getPez_generalidades() {
        return pez_generalidades;
    }

    public void setPez_generalidades(String pez_generalidades) {
        this.pez_generalidades = pez_generalidades;
    }

    public String getPez_curiosidades() {
        return pez_curiosidades;
    }

    public void setPez_curiosidades(String pez_curiosidades) {
        this.pez_curiosidades = pez_curiosidades;
    }

    public boolean isPez_estado() {
        return pez_estado;
    }

    public void setPez_estado(boolean pez_estado) {
        this.pez_estado = pez_estado;
    }  
}
