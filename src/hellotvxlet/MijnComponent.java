/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class MijnComponent extends HComponent implements UserEventListener {
    Image schip;
    Image sterren;
    int x = 350;
    int y = 450;
    int xe = 350;
    int re = 10;
    int ya = 0;
    int enemy[][] = {
        { 0,0,0,0,0,0,0,0 },
        { 0,0,1,0,0,1,0,0 },
        { 0,0,0,0,0,0,0,0 },
        { 1,0,0,0,1,0,0,1 },
        { 0,0,1,0,0,0,0,0 },
        { 0,0,0,0,1,0,1,0 },
        { 1,0,1,0,0,0,0,0 },
        { 0,0,0,0,0,1,0,1 }
    };
    boolean laser = false;
    public MijnComponent(){
        this.setBounds(0, 0, 720, 576);
        // IMG zetten in C:\Program Files\TechnoTrend\TT-MHP-Browser\fileio\DSMCC\0.0.3
        
        schip = this.getToolkit().getImage("spaceship.png");
        sterren = this.getToolkit().getImage("sterren.png");
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(schip, 1);
        mt.addImage(sterren, 1);
        try{
            mt.waitForAll();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        
        UserEventRepository repo = new UserEventRepository("repo");
        repo.addAllArrowKeys();
        
        EventManager manager = EventManager.getInstance();
        manager.addUserEventListener(this, repo);
        
        Timer t = new Timer();
        MijnTimerTask mtt = new MijnTimerTask();
        mtt.setCallback(this);
        t.scheduleAtFixedRate(mtt,0,100);
    }
    
    void callback() {
        xe+=re;
        if(xe>660) re=-10;
        if(xe<0) re=10;
        ++ya;
        if(ya>570) ya=0;
        this.repaint();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(new DVBColor(0,0,255,179));
        //g.fillRoundRect(0, 0, 305, 205, 15, 15);
        //g.setColor(new DVBColor(0,0,255,179));
        //g.fillRoundRect(0, 0, 300, 200, 15, 15);
        
        //g.setColor(new DVBColor(255,255,0,255));
        //g.drawString("Dit is de beste tekst ooit", 20, 40);
        //g.drawLine(0, 0, 100, 100);
        
        g.drawImage(sterren, 0, ya, null);
        g.drawImage(sterren, 0, ya-570, null);
        g.drawImage(schip, x, y, null);
        if(laser){
            g.setColor(Color.RED);
            g.drawLine(x + 25, 450, x + 25, 0);
        }
        g.setColor(Color.GREEN);
        for(int i=0;i<8;++i){
            for(int j=0;j<8;++j){
                if(laser){
                    if(x+25>xe+10*j && x+25<xe+10*j+10){
                        for(int k=0;k<8;++k)enemy[k][j]=0;
                    }
                }
                if(enemy[i][j]==1) g.fillRect(xe+10*j, 0+10*i, 10, 10);
            }
        }
        boolean won  = true;
        for(int l=0;l<8;++l){
            for(int m=0;m<8;++m){
                if(enemy[l][m] == 1)won=false;
            }
        }
        if(won){
            g.setColor(Color.YELLOW);
            g.drawString("Gewonnen :D", 300, 280);

        }
    }

    public void userEventReceived(UserEvent e) {
        if(e.getType()==HRcEvent.KEY_PRESSED){
            if(e.getCode()==HRcEvent.VK_LEFT){
                x=x-10;
                this.repaint();
            }
            if(e.getCode()==HRcEvent.VK_RIGHT){
                x=x+10;
                this.repaint();
            }
            if(e.getCode()==HRcEvent.VK_UP){
                laser=true;
                this.repaint();
            }
            /*if(e.getCode()==HRcEvent.VK_UP){
                y=y-10;
                this.repaint();
            }
            if(e.getCode()==HRcEvent.VK_DOWN){
                y=y+10;
                this.repaint();
            }*/
        }
        if(e.getType()==HRcEvent.KEY_RELEASED){
                if(e.getCode()==HRcEvent.VK_UP){
                laser=false;
                this.repaint();
            }
        }
    }
}
