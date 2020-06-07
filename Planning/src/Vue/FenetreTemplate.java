package Vue;

import java.awt.Color;
import javax.swing.*;


/**
 *
 * @author Xavier Antoine
 */
public class FenetreTemplate extends JFrame {
   
    protected final JFrame fenetre = new JFrame();
    ///MODELE DE LA FENETRE DE BASE
    public FenetreTemplate(){
        fenetre.setTitle("Planning");
        fenetre.setSize(900,900);
        fenetre.setBackground(Color.PINK);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fenetre.setVisible(true);
    }       
        
}