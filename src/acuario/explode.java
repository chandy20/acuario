/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acuario;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class explode {

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();    
    private Dimension d;
    
    private JFrame container;
    
    //coordenadas del JPanel contenido
    private int px=0;
    private int py=0;
    
    private int velocidad = 60;//en milisegundos

    /**
    * Constructor de clase
    */
    public explode( JFrame container ) {
        this.container = container;
        this.container.setVisible(true);
    }

    /**
    * Metodo para ejecutar el efecto EXPLODE
    */
    public void play() {       
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( 
            new Runnable() {
                @Override
                public void run() {
                    container.setOpacity((float)(container.getOpacity() + 0.1));
                    if( container.getOpacity() > 0.9 ) {
                        container.setOpacity(1);
                        close();                       
                    }
                }
            }, 100, velocidad , TimeUnit.MILLISECONDS );
    }
    
    public void stop() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( 
            new Runnable() {
                @Override
                public void run() {
                    container.setOpacity((float)(container.getOpacity() - 0.1));
                    if( container.getOpacity() < 0.1 ) {
                        container.dispose();
                        close();                       
                    }
                }
            }, 100, velocidad , TimeUnit.MILLISECONDS );
    }

    /**
    * Metodo para terminar el efecto explode
    */
    public void close() {
        scheduler.shutdownNow();
    }

}