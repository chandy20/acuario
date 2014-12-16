/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

/**
 *
 * @author Alex
 */
public class PanelVideo extends javax.swing.JPanel {
    
    private Player mediaPlayer;
    
    public PanelVideo(URL url, Dimension d){
        try {
            Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);            
            mediaPlayer = Manager.createRealizedPlayer(url);            
            Component video = mediaPlayer.getVisualComponent();            
            video.setSize(d);            
            video.setVisible(true);
            add(video);
            mediaPlayer.start();
        } catch (IOException ex) {
            Logger.getLogger(PanelVideo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(PanelVideo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotRealizeException ex) {
            Logger.getLogger(PanelVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
