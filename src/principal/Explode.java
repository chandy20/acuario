/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class Explode {

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private JFrame container;
    private final int velocidad = 60;//en milisegundos

    /**
    * Constructor de clase
     * @param container
    */
    public Explode( JFrame container) {
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
                        container.setOpacity(0);
                        container.dispose();
                        close();                       
                    }
                }
            }, 100, velocidad , TimeUnit.MILLISECONDS );
    }

    /**
    * Metodo para terminar el efecto Explode
    */
    public void close() {
        scheduler.shutdownNow();
    }

}