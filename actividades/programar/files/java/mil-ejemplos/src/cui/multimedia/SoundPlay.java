/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cui.multimedia;

import java.applet.*;
import java.net.*;

/**
 *
 * @author david
 */
public class SoundPlay {
    static String defSounds[]= {
        "file:////home/david/ies/material.profesores/anaValle/dir/1Evaluacion/Examen1EvaDAI2004/muybien.wav",
        "file:////home/david/repositorio/ogg/INXS/Definitive/INXS-Tight.ogg",
        "file:////home/david/ies/material.profesores/anaValle/dir/1Evaluacion/Examen1EvaDAI2004/nonono.wav"
    };
    
    public static void main(String[] av) {
        if (av.length==0) main(defSounds);
        else for (int i=0;i<av.length;i++) {
            System.out.println("Starting "+av[i]);
            try {
                URL snd=new URL(av[i]);
                Applet.newAudioClip(snd).play();
                Thread.sleep(3000);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}