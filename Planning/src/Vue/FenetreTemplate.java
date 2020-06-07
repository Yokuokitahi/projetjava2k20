package Vue;

import javax.swing.*;


/**
 *
 * @author Xavier Antoine
 */
public class FenetreTemplate extends JFrame {
   
    protected final JFrame fenetre = new JFrame();
    
    public FenetreTemplate(){
        fenetre.setTitle("Planning");
        fenetre.setSize(900,900);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fenetre.setVisible(true);
    }       
        
}