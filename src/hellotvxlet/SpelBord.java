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
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;

/**
 *
 * @author Thomas Verhelst
 */
public class SpelBord extends HComponent implements UserEventListener {
    
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
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"}
    };
    
    String[][] Adv = {
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"}
    };
    
    String[][] Expert = {
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"},
        {"B"}
    };
    
    String[] selectedLevel = Begin[0];
    String niveau;
    int levelNumber;
    String[][] huidigNiveau;
    
    Image kaart,rood;
    Image nextLevel,prevLevel,nextFLevel,prevFLevel;
    
    Image top,bottom,left,right;
    
    Image cH1,cH2,cH3,cH4,cH5,cH6,cH7,cH8,cH9,cH10,cH11;
    
    Image cV1,cV2,cV3,cV4,cV5,cV6,cV7,cV8,cV9,cV10,cV11;
    
    Image vH1,vH2,vH3,vH4;
    
    Image vV1,vV2,vV3,vV4;
    
    int width,height;
    int krtWidth = 527;
    int krtHeight = 559;
    int randL,randT;
    int rasterSize = 72;
    int A,B,C,D,E,F;
    int een,twee,drie,vier,vijf,zes;
    int maxCar = 11, maxVracht = 4;
    int boardSize = 6;
    int bottomMapHeight, topMapHeight;
    int[][] placementMap, colorMap;
    String[] carList;
    int carInFocus = 0;
    
    // paint calls
    boolean firstEnter = true;
    boolean MoveButtons = false;
    boolean allowMoveUp = false;
    boolean allowMoveDown = false;
    boolean allowMoveRight = false;
    boolean allowMoveLeft = false;
    boolean carFocus = true;
    
    // after game
    boolean won = false;
    int goToLevel;
    boolean volgendLevel = true;
    
    public SpelBord(int width, int height, String level,String niveau){
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
        
        bottomMapHeight = (((height - krtHeight) / 2) + krtHeight) - 25;
        topMapHeight = ((height - krtHeight) / 2) + 40;
        
        levelNumber = stringToInt(level.charAt(2));
        switch(level.charAt(0)){
            case 'B':
                selectedLevel = Begin[levelNumber];
                huidigNiveau = Begin;
                break;
            case 'I':
                selectedLevel = Inter[levelNumber];
                huidigNiveau = Inter;
                break;
            case 'A':
                selectedLevel = Adv[levelNumber];
                huidigNiveau = Adv;
                break;
            case 'E':
                selectedLevel = Expert[levelNumber];
                huidigNiveau = Expert;
                break;
        }
        
        this.niveau = niveau;
        
        // IMG zetten in C:\Program Files\TechnoTrend\TT-MHP-Browser\fileio\DSMCC\0.0.3
        kaart = this.getToolkit().getImage("kaart.gif");rood = this.getToolkit().getImage("redcar.gif");
        
        top = this.getToolkit().getImage("top.png");bottom = this.getToolkit().getImage("bottom.png");left = this.getToolkit().getImage("left.png");right = this.getToolkit().getImage("right.png");
        
        nextLevel = this.getToolkit().getImage("knoppen/next.png");prevLevel = this.getToolkit().getImage("knoppen/back.png");nextFLevel = this.getToolkit().getImage("knoppen/focus/nextf.png");prevFLevel = this.getToolkit().getImage("knoppen/focus/backf.png");
        
        // cars
        cH1 = this.getToolkit().getImage("car/H/1.gif");cH2 = this.getToolkit().getImage("car/H/2.gif");cH3 = this.getToolkit().getImage("car/H/3.gif");cH4 = this.getToolkit().getImage("car/H/4.gif");cH5 = this.getToolkit().getImage("car/H/5.gif");cH6 = this.getToolkit().getImage("car/H/6.gif");cH7 = this.getToolkit().getImage("car/H/7.gif");cH8 = this.getToolkit().getImage("car/H/8.gif");cH9 = this.getToolkit().getImage("car/H/9.gif");cH10 = this.getToolkit().getImage("car/H/10.gif");cH11 = this.getToolkit().getImage("car/H/11.gif");

        cV1 = this.getToolkit().getImage("car/V/1.gif");cV2 = this.getToolkit().getImage("car/V/2.gif");cV3 = this.getToolkit().getImage("car/V/3.gif");cV4 = this.getToolkit().getImage("car/V/4.gif");cV5 = this.getToolkit().getImage("car/V/5.gif");cV6 = this.getToolkit().getImage("car/V/6.gif");cV7 = this.getToolkit().getImage("car/V/7.gif");cV8 = this.getToolkit().getImage("car/V/8.gif");cV9 = this.getToolkit().getImage("car/V/9.gif");cV10 = this.getToolkit().getImage("car/V/10.gif");cV11 = this.getToolkit().getImage("car/V/11.gif");
        
        // vracht
        vH1 = this.getToolkit().getImage("vracht/H/1.gif");vH2 = this.getToolkit().getImage("vracht/H/2.gif");vH3 = this.getToolkit().getImage("vracht/H/3.gif");vH4 = this.getToolkit().getImage("vracht/H/4.gif");
    
        vV1 = this.getToolkit().getImage("vracht/V/1.gif");vV2 = this.getToolkit().getImage("vracht/V/2.gif");vV3 = this.getToolkit().getImage("vracht/V/3.gif");vV4 = this.getToolkit().getImage("vracht/V/4.gif");
        
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(kaart, 1);mt.addImage(rood, 1);
        
        mt.addImage(top, 1);mt.addImage(bottom, 1);mt.addImage(left, 1);mt.addImage(right, 1);
        
        mt.addImage(nextLevel, 1);mt.addImage(prevLevel, 1);mt.addImage(nextFLevel, 1);mt.addImage(prevFLevel, 1);
        
        //cars
        mt.addImage(cH1, 1);mt.addImage(cH2, 1);mt.addImage(cH3, 1);mt.addImage(cH4, 1);mt.addImage(cH5, 1);mt.addImage(cH6, 1);mt.addImage(cH7, 1);mt.addImage(cH8, 1);mt.addImage(cH9, 1);mt.addImage(cH10, 1);mt.addImage(cH11, 1);
        
        mt.addImage(cV1, 1);mt.addImage(cV2, 1);mt.addImage(cV3, 1);mt.addImage(cV4, 1);mt.addImage(cV5, 1);mt.addImage(cV6, 1);mt.addImage(cV7, 1);mt.addImage(cV8, 1);mt.addImage(cV9, 1);mt.addImage(cV10, 1);mt.addImage(cV11, 1);
        
        //vrachtwagens
        mt.addImage(vH1, 1);mt.addImage(vH2, 1);mt.addImage(vH3, 1);mt.addImage(vH4, 1);
        
        mt.addImage(vV1, 1);mt.addImage(vV2, 1);mt.addImage(vV3, 1);mt.addImage(vV4, 1);
        
        try{mt.waitForAll();}catch(InterruptedException ex){ex.printStackTrace();}
        
        UserEventRepository repo = new UserEventRepository("repo");repo.addAllArrowKeys();repo.addKey(HRcEvent.VK_ENTER);
        
        EventManager manager = EventManager.getInstance();manager.addUserEventListener(this, repo);
        
        ArraySetup();
    }
    
    private int stringToInt(char Nummer){
        int Number = 0;
        switch(Nummer){
            case '0':
                Number = 0;
                break;
            case '1':
                Number = 1;
                break;
            case '2':
                Number = 2;
                break;
            case '3':
                Number = 3;
                break;
            case '4':
                Number = 4;
                break;
            case '5':
                Number = 5;
                break;
            case '6':
                Number = 6;
                break;
            case '7':
                Number = 7;
                break;
            case '8':
                Number = 8;
                break;
            case '9':
                Number = 9;
                break;
        }
        return Number;
    }
    
    private String arrayToString(int place){
        String amount="A";
        switch(place){
            case 0:
                amount =  "A";
                break;
            case 1:
                amount =  "B";
                break;
            case 2:
                amount =  "C";
                break;
            case 3:
                amount =  "D";
                break;
            case 4:
                amount =  "E";
                break;
            case 5:
                amount =  "F";
                break;
        }
        return amount;
    }
    
    private int arrayToLetterify(int string){
        int amount=A;
        switch(string){
            case 0:
                amount =  A;
                break;
            case 1:
                amount =  B;
                break;
            case 2:
                amount =  C;
                break;
            case 3:
                amount =  D;
                break;
            case 4:
                amount =  E;
                break;
            case 5:
                amount =  F;
                break;
        }
        return amount;
    }
    
    private int arrayToTextify(int number){
        int amount=een;
        switch(number){
            case 0:
                amount =  een;
                break;
            case 1:
                amount =  twee;
                break;
            case 2:
                amount =  drie;
                break;
            case 3:
                amount =  vier;
                break;
            case 4:
                amount =  vijf;
                break;
            case 5:
                amount =  zes;
                break;
        }
        return amount;
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
    
    private int pickColor(int maxColorNumber){
        return (int)Math.floor(Math.random()* maxColorNumber)+1;
    }
    
    private Image pickCar(char direction,int color){
        Image car;
        if(direction == 'V'){
            car = cV1;
            switch(color){
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
            switch(color){
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
    
    private Image pickVracht(char direction,int color){
        Image vracht;
        if(direction == 'V'){
            vracht = cV1;
            switch(color){
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
            switch(color){
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
    
    private void emptyArrays() {
        carList = new String[selectedLevel.length];
        placementMap = new int[boardSize][boardSize];
        colorMap = new int[boardSize][boardSize];
        carList = selectedLevel;
        if(carList[0].length() < 2){
            carList[0] = carList[0]+"3H2";
        }
    }
    
    private void fillArray(int x, int y, char direction, boolean car, int color) {
        int amount;
        if(car){
            amount = 2;
        }else{
            amount = 3;
        }
        if(direction == 'V'){
            for(int i = 0;i<amount;++i){
                placementMap[x][y+i] = 1;
                colorMap[x][y+i] = color;
            }
        }else{
            for(int i = 0;i<amount;++i){
                placementMap[x+i][y] = 1;
                colorMap[x+i][y] = color;
            }
        }
    }
    
    private void delPlace(int x, int y){
        placementMap[x][y] = 0;
        colorMap[x][y] = 0;
    }
    
    private void addPlace(int x, int y, int color){
        placementMap[x][y] = 1;
        colorMap[x][y] = color;
    }
    
    private void focusOn(Graphics g, int carNumber){
        if(carNumber >= carList.length) carNumber = 0;
        g.setColor(new DVBColor(255,255,255,102));
        int x = letterify(carList[carNumber].charAt(0));
        int y = textify(carList[carNumber].charAt(1));
        if(carList[carNumber].charAt(2) == 'H'){
            if(carList[carNumber].charAt(3) == '2'){
                g.fillRect(x,y, rasterSize*2, rasterSize);
            }else{
                g.fillRect(x,y, rasterSize*3, rasterSize);
            }
        }else{
            if(carList[carNumber].charAt(3) == '2'){
                g.fillRect(x,y, rasterSize, rasterSize*2);
            }else{
                g.fillRect(x,y, rasterSize, rasterSize*3);
            }
        }
        carInFocus = carNumber;
    }
    
    private void ShowMoveButtons(Graphics g){
        boolean H = (carList[carInFocus].charAt(2)=='H')?true:false;
        boolean car = (carList[carInFocus].charAt(3)=='2')?true:false;
        int x = arrayLetterify(carList[carInFocus].charAt(0));
        int y = arrayTextify(carList[carInFocus].charAt(1));
        if(H){
            if(freePlace(x-1,y)){
                g.drawImage(left, arrayToLetterify(x-1),arrayToTextify(y), null);
                allowMoveLeft = true;
            }
            if(car){
                if(freePlace(x+2,y)){
                    g.drawImage(right, arrayToLetterify(x+2),arrayToTextify(y), null);
                    allowMoveRight = true;
                }
            }else{
                if(freePlace(x+3,y)){
                    g.drawImage(right, arrayToLetterify(x+3),arrayToTextify(y), null);
                    allowMoveRight = true;
                }
            }
            if(carInFocus == 0 && x == 4){
                g.drawImage(right, (F + rasterSize) - 20,arrayToTextify(y), null);
                allowMoveRight = true;
            }
        }else{
            if(freePlace(x,y-1)){
                g.drawImage(top, arrayToLetterify(x),arrayToTextify(y-1), null);
                allowMoveUp = true;
            }
            if(car){
                if(freePlace(x,y+2)){
                    g.drawImage(bottom, arrayToLetterify(x),arrayToTextify(y+2), null);
                    allowMoveDown = true;
                }
            }else{
                if(freePlace(x,y+3)){
                    g.drawImage(bottom, arrayToLetterify(x),arrayToTextify(y+3), null);
                    allowMoveDown = true;
                }
            }
        }
    }
    
    private boolean freePlace(int x, int y){
        return (x >=0 && x< boardSize)&&(y>=0&&y<boardSize)? placementMap[x][y]==0:false;
    }
    
    private void move(String direction){
        int x = arrayLetterify(carList[carInFocus].charAt(0));
        int y = arrayTextify(carList[carInFocus].charAt(1));
        boolean car = carList[carInFocus].charAt(3) == '2';
        
        if(allowMoveUp && direction.equals("up")){
            carList[carInFocus] = carList[carInFocus].substring(0, 1) + y + carList[carInFocus].substring(2,4);
            if(car){
                delPlace(x,y+1);
            }else{
                delPlace(x,y+2);
            }
            addPlace(x,y-1,colorMap[x][y]);
        }
        if(allowMoveDown && direction.equals("down")){
            carList[carInFocus] = carList[carInFocus].substring(0, 1) + (y+2) + carList[carInFocus].substring(2,4);
            delPlace(x,y);
            if(car){
                addPlace(x,y+2,colorMap[x][y+1]);
            }else{
                addPlace(x,y+3,colorMap[x][y+1]);
            }
        }
        if(allowMoveLeft && direction.equals("left")){
            carList[carInFocus] = arrayToString(x-1) + carList[carInFocus].substring(1,4);
            if(car){
                delPlace(x+1,y);
            }else{
                delPlace(x+2,y);
            }
            addPlace(x-1,y,colorMap[x][y]);
        }
        if(allowMoveRight && direction.equals("right")){
            if(carInFocus != 0 || x!=4){
                carList[carInFocus] = arrayToString(x+1) + carList[carInFocus].substring(1,4);
                delPlace(x,y);
                if(car){
                    addPlace(x+2,y,colorMap[x+1][y]);
                }else{
                    addPlace(x+3,y,colorMap[x+1][y]);
                }
            }else{
                carList[carInFocus] = arrayToString(x+1) + carList[carInFocus].substring(1,4);
                delPlace(x,y);
                won = true;
            }
        }
        allowMoveUp = false;
        allowMoveDown = false;
        allowMoveLeft = false;
        allowMoveRight = false;
    }
    
    private void ArraySetup(){
        emptyArrays();
        for(int i =0,ilen=carList.length;i<ilen;++i){
            int colorToUse = (i!=0)?(carList[i].charAt(3) == '2')?pickColor(maxCar):pickColor(maxVracht):maxCar+1;
            boolean car = (carList[i].charAt(3) == '2')?true:false;
            fillArray(arrayLetterify(carList[i].charAt(0)),arrayTextify(carList[i].charAt(1)),carList[i].charAt(2),car,colorToUse);
        }
    }
    
    private void BoardFill(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.drawImage(kaart, (width - krtWidth) / 2, (height - krtHeight) / 2, null);
        g.setColor(new DVBColor(194,55,0,255));
        g.drawString(niveau, ((width - krtWidth) / 2) + 140, topMapHeight);
        int numberLeftPosition = ((levelNumber+1)>9)?360:370;
        g.drawString(""+(levelNumber + 1), ((width - krtWidth) / 2) + numberLeftPosition, topMapHeight + 2);
        for(int i = 0,ilen = carList.length;i<ilen;++i){
            if(i == 0){
                g.drawImage(rood, letterify(carList[i].charAt(0)), drie, null);
            }else{
                Image ImageToUse;
                if(carList[i].charAt(3) == '2'){
                    ImageToUse = pickCar(carList[i].charAt(2), colorMap[arrayLetterify(carList[i].charAt(0))][arrayTextify(carList[i].charAt(1))]);
                }else{
                    ImageToUse = pickVracht(carList[i].charAt(2), colorMap[arrayLetterify(carList[i].charAt(0))][arrayTextify(carList[i].charAt(1))]);
                }
                g.drawImage(ImageToUse, letterify(carList[i].charAt(0)), textify(carList[i].charAt(1)), null);
            }
        }
    }
    
    private void Win(Graphics g){
        g.setColor(new DVBColor(194,55,0,255));
        g.drawString("Gewonnen :D", ((width - krtWidth) / 2) + 200, bottomMapHeight);
        MoveButtons = false;
        allowMoveUp = false;
        allowMoveDown = false;
        allowMoveLeft = false;
        allowMoveRight = false;
        showLevelSelection(g);
        this.repaint();
    }
    
    private void focusOnLevelSelection(Graphics g){
        carFocus = false;
        goToLevel = levelNumber;
        if(volgendLevel && levelNumber != 0){
            ++goToLevel;
            g.drawImage(prevFLevel, ((width - krtWidth) / 2) + 15, bottomMapHeight - 28, this);
        }else if(!volgendLevel && levelNumber < huidigNiveau.length-1){
            --goToLevel;
            g.drawImage(nextFLevel, (((width - krtWidth) / 2) + krtWidth) - 70, bottomMapHeight - 28, this);
        }
    }
    
    private void showLevelSelection(Graphics g){
        if(levelNumber != 0){
            g.drawImage(prevLevel, ((width - krtWidth) / 2) + 15, bottomMapHeight - 28, this);
        }
        if(levelNumber < huidigNiveau.length-1){
            g.drawImage(nextLevel, (((width - krtWidth) / 2) + krtWidth) - 70, bottomMapHeight - 28, this);
        }
        focusOnLevelSelection(g);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        BoardFill(g);
        
        if(MoveButtons){
            ShowMoveButtons(g);
            if(!allowMoveUp && !allowMoveDown && !allowMoveRight && !allowMoveLeft){
                MoveButtons = false;
            }
        }
        if(carFocus){
            focusOn(g,carInFocus);
        }
        if(won){
            Win(g);
        }
    }

    public void userEventReceived(UserEvent e) {
        if(e.getType()==HRcEvent.KEY_PRESSED){
            if(!MoveButtons && !won){
                if(e.getCode()==HRcEvent.VK_LEFT || e.getCode()==HRcEvent.VK_DOWN){
                    if(carInFocus > 0){
                        --carInFocus;
                    }else{
                        carInFocus = carList.length-1;
                    }
                    this.repaint();
                }
                if(e.getCode()==HRcEvent.VK_RIGHT || e.getCode()==HRcEvent.VK_UP){
                    if(carInFocus < carList.length-1){
                        ++carInFocus;
                    }else{
                        carInFocus = 0;
                    }
                    this.repaint();
                }
            }else{
                if(allowMoveUp){
                    if(e.getCode()==HRcEvent.VK_UP){
                        move("up");
                        this.repaint();
                    }
                }
                if(allowMoveDown){
                    if(e.getCode()==HRcEvent.VK_DOWN){
                        move("down");
                        this.repaint();
                    }
                }
                if(allowMoveLeft){
                    if(e.getCode()==HRcEvent.VK_LEFT){
                        move("left");
                        this.repaint();
                    }
                }
                if(allowMoveRight){
                    if(e.getCode()==HRcEvent.VK_RIGHT){
                        move("right");
                        this.repaint();
                    }
                }
                if(won){
                    if(e.getCode()==HRcEvent.VK_RIGHT){
                        volgendLevel = true;
                    }
                    if(e.getCode()==HRcEvent.VK_LEFT){
                        volgendLevel = false;
                    }
                }
            }
        }
        if(e.getType()==HRcEvent.KEY_RELEASED && e.getCode()==HRcEvent.VK_ENTER){
            if(!firstEnter){
                if(MoveButtons){
                    MoveButtons = false;
                    allowMoveUp = false;
                    allowMoveDown = false;
                    allowMoveLeft = false;
                    allowMoveRight = false;
                    this.repaint();
                }else{

                    MoveButtons = true;
                    this.repaint();
                }
            }else{
                firstEnter = false;
            }
            if(won){
                if(volgendLevel){
                    ++levelNumber;
                    
                }else{
                    --levelNumber;
                }
                selectedLevel = huidigNiveau[levelNumber];
                ArraySetup();
                carFocus = true;
                won = false;
                this.repaint();
            }
        }
    }
}
