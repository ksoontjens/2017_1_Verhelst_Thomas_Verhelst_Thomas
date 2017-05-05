package hellotvxlet;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.*;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {
  
    public HelloTVXlet() {
        
    }
    public HScene scene;
    public void initXlet(XletContext context) {
      scene = HSceneFactory.getInstance().getDefaultHScene();
      
      MijnComponent mc = new MijnComponent();
      scene.add(mc);
      scene.validate();
      scene.setVisible(true);
      
      //720 x 576 standard definition
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
       
    }
}
