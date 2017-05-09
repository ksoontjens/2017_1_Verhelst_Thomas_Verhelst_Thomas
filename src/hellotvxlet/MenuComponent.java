/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;

/**
 *
 * @author Thomas Verhelst
 */
class MenuComponent extends HComponent {
    Image intro;
    Image titel;
    int width;
    int height;
    int introSize = 335;
    public MenuComponent(int width, int height){
        this.setBounds(0, 0, width, height);
        this.width = width;
        this.height = height;
        // IMG zetten in C:\Program Files\TechnoTrend\TT-MHP-Browser\fileio\DSMCC\0.0.3
        intro = this.getToolkit().getImage("intro.png");
        titel = this.getToolkit().getImage("title.png");
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(intro, 1);
        mt.addImage(titel, 1);
        try{
            mt.waitForAll();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        
        //background
        g.setColor(new DVBColor(191,227,87,255));
        g.fillRect(0, 0, width, height);
        
        //bottom Image
        g.drawImage(intro, 0, height - introSize, width, introSize, null);
        
        //name
        g.drawImage(titel, (width - 400)/2, 20,400,135, null);
    }
}
