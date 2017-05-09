/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;

/**
 *
 * @author Thomas Verhelst
 */
public class MijnComponent extends HComponent implements UserEventListener {
    
    String[][] Begin = {
        {"B","A1H2","F1V3","A2V3","D2V3","A5V2","E5H2","C6H3"},
        {"A","A1V2","D1H3","D2V2","F2V3","E3V2","A4H3","E5H2","C5V2","A6H2","D6H2"},
        {"B","D3V3","B4H2","B5V2","C6H2","F4V3"},
        {"B","A1V3","D1V3","C4V2","D4H3","C6H3","F5V2"},
        {"B","A1H2","D1V3","F1V2","A2V3","E2V3","B4H3","F3V2","A5V2","E5H2","E6H2"},
        {"B","A1H2","A2H2","D1V2","E2V3","F2V3","D3V3","A4H2","C4V2","A5V2","D6H3"},
        {"B","B1V2","C1H2","E1V2","F1V2","D2V2","C4H2","F3V2","D6V2"},
        {"A","D1H2","F1V3","C2H2","E2V2","C3V2","D3V2","E4H2","A4H2","A5H2","A6H2","C5V2","D5H3","D6H3"},
        {"A","B1V2","C1H2","E1H2","E2H2","D2V2","E3V3","F3V2","A4V3","B4H3","C5V2","F5V2"},
        {"B","A1H2","A2H2","C1V2","E1H2","F2V3","A3V3","B4H3","A6H2","D5V2","E5H2","E6H2"}
    };
    
    String[][] Inter = {
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {}
    };
    
    String[][] Adv = {
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {}
    };
    
    String[][] Expert = {
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {}
    };
    
    String[] selectdLevel = Begin[2];
    
    Image kaart,rood;
    
    Image top,bottom,left,right;
    
    Image cH1,cH2,cH3,cH4,cH5,cH6,cH7,cH8,cH9,cH10,cH11;
    
    Image cV1,cV2,cV3,cV4,cV5,cV6,cV7,cV8,cV9,cV10,cV11;
    
    Image vH1,vH2,vH3,vH4;
    
    Image vV1,vV2,vV3,vV4;
    
    Image focus;
    
    int knopWidth = 130;
    int knopHeight = 40;
    
    int width,height;
    int krtWidth = 527;
    int krtHeight = 559;
    int randL,randT;
    int rasterSize = 72;
    int A,B,C,D,E,F;
    int een,twee,drie,vier,vijf,zes;
    int maxCar = 11;
    int maxVracht = 4;
    int boardSize = 6;
    int[][] placementMap;
    String[] carList;
    
    public MijnComponent(int width, int height, String level){
        this.setBounds(0, 0, width, height);
        
        this.width = width;
        this.height = height;
        
        randL = ((width - krtWidth) / 2) + 45;
        randT = ((height - krtHeight) / 2) +66;
        A = randL;
        B = randL + rasterSize;
        C = randL + (rasterSize * 2);
        D = randL + (rasterSize * 3);
        E = randL + (rasterSize * 4);
        F = randL + (rasterSize * 5);
        een = randT;
        twee = randT + rasterSize;
        drie = randT + (rasterSize * 2);
        vier = randT + (rasterSize * 3);
        vijf = randT + (rasterSize * 4);
        zes = randT + (rasterSize * 5);
        
        // IMG zetten in C:\Program Files\TechnoTrend\TT-MHP-Browser\fileio\DSMCC\0.0.3
        kaart = this.getToolkit().getImage("kaart.gif");rood = this.getToolkit().getImage("redcar.gif");
        
        top = this.getToolkit().getImage("top.gif");bottom = this.getToolkit().getImage("bottom.gif");left = this.getToolkit().getImage("left.gif");right = this.getToolkit().getImage("right.gif");
        
        // cars
        cH1 = this.getToolkit().getImage("car/H/1.gif");cH2 = this.getToolkit().getImage("car/H/2.gif");cH3 = this.getToolkit().getImage("car/H/3.gif");cH4 = this.getToolkit().getImage("car/H/4.gif");cH5 = this.getToolkit().getImage("car/H/5.gif");cH6 = this.getToolkit().getImage("car/H/6.gif");cH7 = this.getToolkit().getImage("car/H/7.gif");cH8 = this.getToolkit().getImage("car/H/8.gif");cH9 = this.getToolkit().getImage("car/H/9.gif");cH10 = this.getToolkit().getImage("car/H/10.gif");cH11 = this.getToolkit().getImage("car/H/11.gif");

        cV1 = this.getToolkit().getImage("car/V/1.gif");cV2 = this.getToolkit().getImage("car/V/2.gif");cV3 = this.getToolkit().getImage("car/V/3.gif");cV4 = this.getToolkit().getImage("car/V/4.gif");cV5 = this.getToolkit().getImage("car/V/5.gif");cV6 = this.getToolkit().getImage("car/V/6.gif");cV7 = this.getToolkit().getImage("car/V/7.gif");cV8 = this.getToolkit().getImage("car/V/8.gif");cV9 = this.getToolkit().getImage("car/V/9.gif");cV10 = this.getToolkit().getImage("car/V/10.gif");cV11 = this.getToolkit().getImage("car/V/11.gif");
        
        // vracht
        vH1 = this.getToolkit().getImage("vracht/H/1.gif");vH2 = this.getToolkit().getImage("vracht/H/2.gif");vH3 = this.getToolkit().getImage("vracht/H/3.gif");vH4 = this.getToolkit().getImage("vracht/H/4.gif");
    
        vV1 = this.getToolkit().getImage("vracht/V/1.gif");vV2 = this.getToolkit().getImage("vracht/V/2.gif");vV3 = this.getToolkit().getImage("vracht/V/3.gif");vV4 = this.getToolkit().getImage("vracht/V/4.gif");
        
        focus = this.getToolkit().getImage("focus.png");
        
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(kaart, 1);mt.addImage(rood, 1);
        
        mt.addImage(top, 1);mt.addImage(bottom, 1);mt.addImage(left, 1);mt.addImage(right, 1);
        
        //cars
        mt.addImage(cH1, 1);mt.addImage(cH2, 1);mt.addImage(cH3, 1);mt.addImage(cH4, 1);mt.addImage(cH5, 1);mt.addImage(cH6, 1);mt.addImage(cH7, 1);mt.addImage(cH8, 1);mt.addImage(cH9, 1);mt.addImage(cH10, 1);mt.addImage(cH11, 1);
        
        mt.addImage(cV1, 1);mt.addImage(cV2, 1);mt.addImage(cV3, 1);mt.addImage(cV4, 1);mt.addImage(cV5, 1);mt.addImage(cV6, 1);mt.addImage(cV7, 1);mt.addImage(cV8, 1);mt.addImage(cV9, 1);mt.addImage(cV10, 1);mt.addImage(cV11, 1);
        
        //vrachtwagens
        mt.addImage(vH1, 1);mt.addImage(vH2, 1);mt.addImage(vH3, 1);mt.addImage(vH4, 1);
        
        mt.addImage(vV1, 1);mt.addImage(vV2, 1);mt.addImage(vV3, 1);mt.addImage(vV4, 1);
        
        mt.addImage(focus, 1);
        
        try{mt.waitForAll();}catch(InterruptedException ex){ex.printStackTrace();}
        
        UserEventRepository repo = new UserEventRepository("repo");repo.addAllArrowKeys();
        EventManager manager = EventManager.getInstance();manager.addUserEventListener(this, repo);
    }
    
    private int letterify(char string){
        int amount=A;
        switch(string){
            case 'A':
                amount =  A;
                break;
            case 'B':
                amount =  B;
                break;
            case 'C':
                amount =  C;
                break;
            case 'D':
                amount =  D;
                break;
            case 'E':
                amount =  E;
                break;
            case 'F':
                amount =  F;
                break;
        }
        return amount;
    }
    
    private int textify(char number){
        int amount=een;
        switch(number){
            case '1':
                amount =  een;
                break;
            case '2':
                amount =  twee;
                break;
            case '3':
                amount =  drie;
                break;
            case '4':
                amount =  vier;
                break;
            case '5':
                amount =  vijf;
                break;
            case '6':
                amount =  zes;
                break;
        }
        return amount;
    }
    
    private int arrayLetterify(char string){
        int amount=A;
        switch(string){
            case 'A':
                amount =  0;
                break;
            case 'B':
                amount =  1;
                break;
            case 'C':
                amount =  2;
                break;
            case 'D':
                amount =  3;
                break;
            case 'E':
                amount =  4;
                break;
            case 'F':
                amount =  5;
                break;
        }
        return amount;
    }
    
    private int arrayTextify(char number){
        int amount=een;
        switch(number){
            case '1':
                amount =  0;
                break;
            case '2':
                amount =  1;
                break;
            case '3':
                amount =  2;
                break;
            case '4':
                amount =  3;
                break;
            case '5':
                amount =  4;
                break;
            case '6':
                amount =  5;
                break;
        }
        return amount;
    }
    
    private Image pickCar(char direction){
        Image car;
        int randomNumber = (int)Math.floor(Math.random()* maxCar)+1;
        if(direction == 'V'){
            car = cV1;
            switch(randomNumber){
                case 1:
                    car = cV1;
                    break;
                case 2:
                    car = cV2;
                    break;
                case 3:
                    car = cV3;
                    break;
                case 4:
                    car = cV4;
                    break;
                case 5:
                    car = cV5;
                    break;
                case 6:
                    car = cV6;
                    break;
                case 7:
                    car = cV7;
                    break;
                case 8:
                    car = cV8;
                    break;
                case 9:
                    car = cV9;
                    break;
                case 10:
                    car = cV10;
                    break;
                case 11:
                    car = cV11;
                    break;
            }
        }else{
            car = cH1;
            switch(randomNumber){
                case 1:
                    car = cH1;
                    break;
                case 2:
                    car = cH2;
                    break;
                case 3:
                    car = cH3;
                    break;
                case 4:
                    car = cH4;
                    break;
                case 5:
                    car = cH5;
                    break;
                case 6:
                    car = cH6;
                    break;
                case 7:
                    car = cH7;
                    break;
                case 8:
                    car = cH8;
                    break;
                case 9:
                    car = cH9;
                    break;
                case 10:
                    car = cH10;
                    break;
                case 11:
                    car = cH11;
                    break;
            }
        }
        return car;
    }
    
    private Image pickVracht(char direction){
        Image vracht;
        int randomNumber = (int)Math.floor(Math.random()* maxVracht)+1;
        if(direction == 'V'){
            vracht = cV1;
            switch(randomNumber){
                case 1:
                    vracht = vV1;
                    break;
                case 2:
                    vracht = vV2;
                    break;
                case 3:
                    vracht = vV3;
                    break;
                case 4:
                    vracht = vV4;
                    break;
            }
        }else{
            vracht = vH1;
            switch(randomNumber){
                case 1:
                    vracht = vH1;
                    break;
                case 2:
                    vracht = vH2;
                    break;
                case 3:
                    vracht = vH3;
                    break;
                case 4:
                    vracht = vH4;
                    break;
            }
        }
        return vracht;
    }
    
    private void emptyArrays(String[] level) {
        carList = new String[level.length];
        placementMap = new int[boardSize][boardSize];
    }
    
    private void fillArray(int x, int y, char direction, boolean car) {
        int amount;
        if(car){
            amount = 2;
        }else{
            amount = 3;
        }
        if(direction == 'V'){
            for(int i = 0;i<amount;++i){
                placementMap[y+i][x] = 1;
            }
        }else{
            for(int i = 0;i<amount;++i){
                placementMap[y][x+i] = 1;
            }
        }
    }
    
    private void delPlace(int x, int y){
        placementMap[x][y] = 0;
    }
    
    private void addPlace(int x, int y){
        placementMap[x][y] = 1;
    }
    
    private void BoardSetup(Graphics g, String[] level){
        emptyArrays(level);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.drawImage(kaart, (width - krtWidth) / 2, (height - krtHeight) / 2, null);
        for(int i =0,ilen=level.length;i<ilen;++i){
            if(i == 0){
                g.drawImage(rood, letterify(level[i].charAt(0)), drie, null);
                fillArray(arrayLetterify(level[i].charAt(0)),arrayTextify('3'),'H',true);
            }else{
                Image toUse;
                if(level[i].charAt(3) == '2'){
                    toUse = pickCar(level[i].charAt(2));
                    fillArray(arrayLetterify(level[i].charAt(0)),arrayTextify(level[i].charAt(1)),level[i].charAt(2),true);
                }else{
                    toUse = pickVracht(level[i].charAt(2));
                    fillArray(arrayLetterify(level[i].charAt(0)),arrayTextify(level[i].charAt(1)),level[i].charAt(2),false);
                }
                g.drawImage(toUse, letterify(level[i].charAt(0)), textify(level[i].charAt(1)), null);
            }
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        BoardSetup(g,selectdLevel);
        
        //g.setColor(new DVBColor(0,0,255,179));
        //g.fillRoundRect(0, 0, 305, 205, 15, 15);
        //g.setColor(new DVBColor(0,0,255,179));
        //g.fillRoundRect(0, 0, 300, 200, 15, 15);
        
        //g.setColor(new DVBColor(255,255,0,255));
        //g.drawString("Dit is de beste tekst ooit", 20, 40);
        //g.drawLine(0, 0, 100, 100);
        
//        if(laser){
//            g.setColor(Color.RED);
//            g.drawLine(x + 25, 450, x + 25, 0);
//        }
//        g.setColor(Color.GREEN);
//        for(int i=0;i<8;++i){
//            for(int j=0;j<8;++j){
//                if(laser){
//                    if(x+25>xe+10*j && x+25<xe+10*j+10){
//                        for(int k=0;k<8;++k)enemy[k][j]=0;
//                    }
//                }
//                if(enemy[i][j]==1) g.fillRect(xe+10*j, 0+10*i, 10, 10);
//            }
//        }
//        boolean won  = true;
//        for(int l=0;l<8;++l){
//            for(int m=0;m<8;++m){
//                if(enemy[l][m] == 1)won=false;
//            }
//        }
//        if(won){
//            g.setColor(Color.YELLOW);
//            g.drawString("Gewonnen :D", 300, 280);
//
//        }
    }

    public void userEventReceived(UserEvent e) {
        if(e.getType()==HRcEvent.KEY_PRESSED){
            if(e.getCode()==HRcEvent.VK_LEFT){
//                x=x-10;
                this.repaint();
            }
            if(e.getCode()==HRcEvent.VK_RIGHT){
//                x=x+10;
                this.repaint();
            }
            if(e.getCode()==HRcEvent.VK_UP){
//                laser=true;
                this.repaint();
            }
            if(e.getCode()==HRcEvent.VK_DOWN){
//                y=y+10;
                this.repaint();
            }
        }
        if(e.getType()==HRcEvent.KEY_RELEASED){
                if(e.getCode()==HRcEvent.VK_ENTER){
//                laser=false;
//                this.repaint();
            }
        }
    }
}
