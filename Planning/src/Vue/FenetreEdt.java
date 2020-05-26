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
    
    protected final JFrame fen = new JFrame();
    private final Object[][] data = {
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""}    
        };
    private final String titre[] = {"","lundi","mardi","mercredi","jeudi","vendredi","samedi","dimanche"};
    private final JTable tableau = new JTable(data,titre);
    

    public FenetreEdt(){
        /*this.setSize(1000,1000);
        this.setTitle("Planning");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

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
        this.setJMenuBar(menuBar);
 
        //this.getContentPane().add(new JScrollPane(tableau));
        
        this.setVisible(true);*/
    }
}
