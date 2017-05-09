package hellotvxlet;

import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.*;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {
    //720 x 576 standard definition
    int width = 720;
    int height = 576;
    String niveau;
    public HelloTVXlet() {
        
    }
    public HScene scene;
    MijnComponent mc;
    public void initXlet(XletContext context) {
      scene = HSceneFactory.getInstance().getDefaultHScene();
      //showMenu();
      mc = new MijnComponent(width,height,"B 2");
      scene.add(mc);
      scene.validate();scene.setVisible(true);
      
//      Button Images:
//      HTextButton knop = new HTextButton("1",250,200,100,100);
//      knop.setGraphicContent(image, HVisible.FIRST_STATE);
//      knop.setGraphicContent(image, HVisible.FOCUSED_STATE);
//      knop.setGraphicContent(image, HVisible.ACTIONED_FOCUSED_STATE);
//      knop.setGraphicContent(image, HVisible.ACTIONED_STATE);
    }
    
    private void showMenu(){
        scene.removeAll();
        int topDown = 220;
        int levelButtonSize = 130;
        int smallButtonSize = 40;
        int padding = 10;
        if(niveau == null){
            HTextButton knop1 = new HTextButton("simpel",(((width-padding)-(4*(levelButtonSize+padding)))/2)+((levelButtonSize*0)+(padding*0)),topDown,levelButtonSize,smallButtonSize);
              knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop1.setBackground(new DVBColor(194,55,0,255));
              knop1.setActionCommand("simpel");
              knop1.addHActionListener(this);
              scene.add(knop1);
              
              HTextButton knop2 = new HTextButton("standaard",(((width-padding)-(4*(levelButtonSize+padding)))/2)+((levelButtonSize*1)+(padding*1)),topDown,levelButtonSize,smallButtonSize);
              knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop2.setBackground(new DVBColor(194,55,0,255));
              knop2.setActionCommand("standaard");
              knop2.addHActionListener(this);
              scene.add(knop2);
              
              HTextButton knop3 = new HTextButton("spannend",(((width-padding)-(4*(levelButtonSize+padding)))/2)+((levelButtonSize*2)+(padding*2)),topDown,levelButtonSize,smallButtonSize);
              knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop3.setBackground(new DVBColor(194,55,0,255));
              knop3.setActionCommand("spannend");
              knop3.addHActionListener(this);
              scene.add(knop3);
              
              HTextButton knop4 = new HTextButton("super",(((width-padding)-(4*(levelButtonSize+padding)))/2)+((levelButtonSize*3)+(padding*3)),topDown,levelButtonSize,smallButtonSize);
              knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop4.setBackground(new DVBColor(194,55,0,255));
              knop4.setActionCommand("super");
              knop4.addHActionListener(this);
              scene.add(knop4);
              //up, down, left, right
              knop1.setFocusTraversal(null,null,knop4,knop2);
              knop2.setFocusTraversal(null,null,knop1,knop3);
              knop3.setFocusTraversal(null,null,knop2,knop4);
              knop4.setFocusTraversal(null,null,knop3,knop1);
              
              knop1.requestFocus();
              
        }else{
            String level = "";
            if(niveau.equals("simpel")){
                level = "B";
            }else if(niveau.equals("standaard")){
                level = "I";
            }
            else if(niveau.equals("spannend")){
                level = "A";
            }
            else if(niveau.equals("super")){
                level = "E";
            }
              HStaticText tekst = new HStaticText(niveau,0,170,width,30);
              tekst.setForeground(new DVBColor(194,55,0,255));
              tekst.setHorizontalAlignment(HVisible.HALIGN_CENTER);
              scene.add(tekst);
              
              HTextButton back = new HTextButton("terug",(((width-(10*smallButtonSize))/2)+(smallButtonSize*0))-20,topDown+70,100,smallButtonSize);
              back.setBackgroundMode(HVisible.BACKGROUND_FILL);
              back.setBackground(new DVBColor(194,55,0,255));
              back.setActionCommand("back");
              back.addHActionListener(this);
              scene.add(back);
              
              HTextButton knop1 = new HTextButton("1",((width-(10*smallButtonSize))/2)+(smallButtonSize*0),topDown,smallButtonSize,smallButtonSize);
              knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop1.setBackground(new DVBColor(194,55,0,255));
              knop1.setActionCommand(level+" 1");
              knop1.addHActionListener(this);
              scene.add(knop1);
              
              HTextButton knop2 = new HTextButton("2",((width-(10*smallButtonSize))/2)+(smallButtonSize*1),topDown,smallButtonSize,smallButtonSize);
              knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop2.setBackground(new DVBColor(194,55,0,255));
              knop2.setActionCommand(level+" 2");
              knop2.addHActionListener(this);
              scene.add(knop2);
              
              HTextButton knop3 = new HTextButton("3",((width-(10*smallButtonSize))/2)+(smallButtonSize*2),topDown,smallButtonSize,smallButtonSize);
              knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop3.setBackground(new DVBColor(194,55,0,255));
              knop3.setActionCommand(level+" 3");
              knop3.addHActionListener(this);
              scene.add(knop3);
              
              HTextButton knop4 = new HTextButton("4",((width-(10*smallButtonSize))/2)+(smallButtonSize*3),topDown,smallButtonSize,smallButtonSize);
              knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop4.setBackground(new DVBColor(194,55,0,255));
              knop4.setActionCommand(level+" 4");
              knop4.addHActionListener(this);
              scene.add(knop4);
              
              HTextButton knop5 = new HTextButton("5",((width-(10*smallButtonSize))/2)+(smallButtonSize*4),topDown,smallButtonSize,smallButtonSize);
              knop5.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop5.setBackground(new DVBColor(194,55,0,255));
              knop5.setActionCommand(level+" 5");
              knop5.addHActionListener(this);
              scene.add(knop5);
              
              HTextButton knop6 = new HTextButton("6",((width-(10*smallButtonSize))/2)+(smallButtonSize*5),topDown,smallButtonSize,smallButtonSize);
              knop6.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop6.setBackground(new DVBColor(194,55,0,255));
              knop6.setActionCommand(level+" 6");
              knop6.addHActionListener(this);
              scene.add(knop6);
              
              HTextButton knop7 = new HTextButton("7",((width-(10*smallButtonSize))/2)+(smallButtonSize*6),topDown,smallButtonSize,smallButtonSize);
              knop7.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop7.setBackground(new DVBColor(194,55,0,255));
              knop7.setActionCommand(level+" 7");
              knop7.addHActionListener(this);
              scene.add(knop7);
              
              HTextButton knop8 = new HTextButton("8",((width-(10*smallButtonSize))/2)+(smallButtonSize*7),topDown,smallButtonSize,smallButtonSize);
              knop8.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop8.setBackground(new DVBColor(194,55,0,255));
              knop8.setActionCommand(level+" 8");
              knop8.addHActionListener(this);
              scene.add(knop8);
              
              HTextButton knop9 = new HTextButton("9",((width-(10*smallButtonSize))/2)+(smallButtonSize*8),topDown,smallButtonSize,smallButtonSize);
              knop9.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop9.setBackground(new DVBColor(194,55,0,255));
              knop9.setActionCommand(level+" 9");
              knop9.addHActionListener(this);
              scene.add(knop9);
              
              HTextButton knop10 = new HTextButton("10",((width-(10*smallButtonSize))/2)+(smallButtonSize*9),topDown,smallButtonSize,smallButtonSize);
              knop10.setBackgroundMode(HVisible.BACKGROUND_FILL);
              knop10.setBackground(new DVBColor(194,55,0,255));
              knop10.setActionCommand(level+" 10");
              knop10.addHActionListener(this);
              scene.add(knop10);
              //up, down, left, right
              back.setFocusTraversal(knop1, null, null, null);
              knop1.setFocusTraversal(null,back,knop10,knop2);
              knop2.setFocusTraversal(null,back,knop1,knop3);
              knop3.setFocusTraversal(null,back,knop2,knop4);
              knop4.setFocusTraversal(null,back,knop3,knop5);
              knop5.setFocusTraversal(null,back,knop4,knop6);
              knop6.setFocusTraversal(null,back,knop5,knop7);
              knop7.setFocusTraversal(null,back,knop6,knop8);
              knop8.setFocusTraversal(null,back,knop7,knop9);
              knop9.setFocusTraversal(null,back,knop8,knop10);
              knop10.setFocusTraversal(null,back,knop9,knop1);
              
              knop1.requestFocus();
        }
        MenuComponent menuBackground = new MenuComponent(width,height);
        scene.add(menuBackground);
        scene.validate(); scene.setVisible(true);
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
       
       if(niveau == null){
           niveau = arg0.getActionCommand();
           showMenu();
           scene.repaint();
       }
       else{
           if(!arg0.getActionCommand().equals("back")){
               mc = new MijnComponent(width,height,arg0.getActionCommand());
               scene.add(mc);
               scene.popToFront(mc);
               scene.repaint();
           }else{
               niveau = null;
               showMenu();
               scene.repaint();
           }
           
       }
    }
}
