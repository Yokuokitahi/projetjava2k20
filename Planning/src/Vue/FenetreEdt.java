/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Drakking
 */
public class FenetreEdt extends FenetreTemplate{
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu test1 = new JMenu("Emploi du temps");
    private final JMenu test2 = new JMenu("Récapitulatif des cours");
    
    private final JMenuItem item1 = new JMenuItem("Ouvrir");
    private final JMenuItem item2 = new JMenuItem("Fermer");
    
    private final Panneau grille = new Panneau();
    
    /*private final Object[][] data = {
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""}    
        };
    private final String titre[] = {"","lundi","mardi","mercredi","jeudi","vendredi","samedi","dimanche"};
    private final JTable tableau = new JTable(data,titre);*/
    

    public FenetreEdt(){
        
        fenetre.setSize(new Dimension(1200,1000));
        
        this.test1.add(item1);
        this.test1.addSeparator();
        item2.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent arg0) {
        System.exit(0);
        }
        });
        this.test1.add(item2);
        this.menuBar.add(test1);
        this.menuBar.add(test2);
        
        JTextPane cours = new JTextPane();
        cours.setBackground(Color.orange);
        cours.setBounds(81,276,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours);
        fenetre.add(grille);
       //fenetre.add(new JScrollPane(tableau));
    
    }
}
