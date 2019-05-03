
package spacegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{
private int x=0;
private int y=0;

         public Ates(int x,int y) {
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

   


}
public class Oyun extends JPanel implements KeyListener,ActionListener{
    Timer timer=new Timer(5, this);
  private int gecenSure=0;
    private int harcananAtes=0;
    private BufferedImage image;
    ArrayList<Ates> ates=new ArrayList<Ates>();
    private int atesdirY=1;
    private int topX=0;
    private int topdirX=2;
    private int uzayGemisiX=0;
    private int dirUzayX=20;
    public boolean kontrolEt(){
    
        for(Ates atesler : ates){
        
        if(new Rectangle(atesler.getX(),atesler.getY(),10,20).intersects(new Rectangle(topX,0,20,20))){
        return true;
        
        }
        
        }
        return false;
    
    }
    public Oyun() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        }  catch (IOException ex) {
          Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        setBackground(Color.BLACK);
        timer.start();
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        gecenSure+=5;
        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);
        g.drawImage(image,uzayGemisiX, 490,image.getWidth()/10,image.getHeight()/10,this);
        for(Ates atesler: ates){
        if(atesler.getY()<0){
        ates.remove(ates);
        
        }
        }
        g.setColor(Color.blue);
        for(Ates atesler : ates){
        g.fillRect(atesler.getX(), atesler.getY(), 10,20);
        
        }
        
        if(kontrolEt()){
        timer.stop();
        String mesaj="Kazandınız..\n"+"Harcanan Ateş : "+harcananAtes+"\nGeçen süre : "+gecenSure/1000.0;
            JOptionPane.showMessageDialog(this, mesaj);
            System.exit(0);
        }
    }
    
    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        
               if(uzayGemisiX==720){
            
            }else{
             uzayGemisiX=uzayGemisiX+20;
            repaint();
            }
               
        }
          if(e.getKeyCode()==KeyEvent.VK_LEFT){
        
            if(uzayGemisiX==0){
            
            }else{
             uzayGemisiX=uzayGemisiX-20;
            repaint();
            }
             
            
        }
          if(e.getKeyCode()==KeyEvent.VK_SPACE){
             ates.add(new Ates(uzayGemisiX+15,490));
            harcananAtes++;
          }
     
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Ates atesler : ates){
        
        atesler.setY(atesler.getY()-atesdirY);
    
        }
       
        
        
        topX+=topdirX;
        if(topX>=750){
        topdirX=-topdirX;
        
        }
        if(topX<=0){
        topdirX=-topdirX;
        
        }
        repaint();
    }
    public void topHareket(){
    
    
    
    
    }

    
}
