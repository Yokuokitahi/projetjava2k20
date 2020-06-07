/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Drakking
 */
public class Panneau extends JPanel{
    @Override
    public void paintComponent(Graphics g){
        int x,y,width,height;
        x=80;
        y=100;
        width=900;
        height=600;
        
        //carré
        g.setColor(Color.white);
        g.fillRect(x,y,width,height);
        
        // label(jours)
        Font font = new Font("courier",Font.ROMAN_BASELINE,15);
        g.setFont(font);
        g.setColor(Color.gray);
        g.drawString("Lundi",x+50,y-10);
        g.drawString("Mardi",x+200,y-10);
        g.drawString("Mercredi",x+350,y-10);
        g.drawString("Jeudi",x+500,y-10);
        g.drawString("Vendredi",x+650,y-10);
        g.drawString("Samedi",x+800,y-10);
        
        //label (heures)
        g.setColor(Color.BLUE);
        g.drawString("8h30",x-35,y);
        g.drawString("10h00",x-45,y+75);
        g.drawString("10h15",x-45,y+87);
        g.drawString("11h45",x-45,y+162);
        g.drawString("12h00",x-45,y+175);
        g.drawString("13h30",x-45,y+250);
        g.drawString("13h45",x-45,y+262);
        g.drawString("15h15",x-45,y+337);
        g.drawString("15h30",x-45,y+350);
        g.drawString("17h00",x-45,y+425);
        g.drawString("17h15",x-45,y+437);
        g.drawString("18h45",x-45,y+512);
        g.drawString("19h00",x-45,y+525);
        g.drawString("20h30",x-45,y+600);
        
     
        g.setColor(Color.black);
        
       //lignes
        
        g.drawLine(x,y,x+width,y);//8h30
        g.drawLine(x,y+75,x+width,y+75);//10h
        g.drawLine(x,y+87,x+width,y+87);//10h15
        g.drawLine(x,y+162,x+width,y+162);//11h45
        g.drawLine(x,y+175,x+width,y+175);//12h
        g.drawLine(x,y+250,x+width,y+250);//13h30
        g.drawLine(x,y+262,x+width,y+262);//13h45
        g.drawLine(x,y+337,x+width,y+337);//15h15
        g.drawLine(x,y+350,x+width,y+350);//15h30
        g.drawLine(x,y+425,x+width,y+425);//17h
        g.drawLine(x,y+437,x+width,y+437);//17h15
        g.drawLine(x,y+512,x+width,y+512);//18h45
        g.drawLine(x,y+525,x+width,y+525);//19h
        g.drawLine(x,y+600,x+width,y+600);//20h30
        
        
        
        //colonnes
        g.drawLine(x,y,x,height+y);
        g.drawLine(x,y+height,x+width,y+height);
        g.drawLine(x+150,y,x+150,height+y);
        g.drawLine(x+300,y,x+300,height+y);
        g.drawLine(x+450,y,x+450,height+y);
        g.drawLine(x+600,y,x+600,height+y);
        g.drawLine(x+750,y,x+750,height+y);
        g.drawLine(x+900,y,x+900,height+y);
        
          
    }
}
