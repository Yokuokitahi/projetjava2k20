/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.RechercherSeanceSemaine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Drakking
 */
public class FenetreEdt extends FenetreTemplate{
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu test1 = new JMenu("Emploi du temps");
    private final JMenu test2 = new JMenu("Récapitulatif des cours");
    
    private final JMenuItem item1 = new JMenuItem("Etudiant");
    private final JMenuItem item2 = new JMenuItem("Fermer");
    
    Panneau grille = new Panneau();
    CardLayout c1 = new CardLayout();
    
    @SuppressWarnings("empty-statement")
    public FenetreEdt(final String login, final int nbSemaine) throws SQLException, ClassNotFoundException{
        
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
        JButton suivant = new JButton("Semaine suivante");
        suivant.setBounds(550,20,155,20);
        
        JButton prec = new JButton("Semaine précédente");
        prec.setBounds(350,20,155,20);
        
        suivant.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    fenetre.dispose();
                    FenetreEdt suiv = new FenetreEdt(login,nbSemaine+1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        prec.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    fenetre.dispose();
                    FenetreEdt suiv = new FenetreEdt(login,nbSemaine-1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        fenetre.setJMenuBar(menuBar);
        fenetre.add(suivant);
        fenetre.add(prec);
        fenetre.add(grille);
        
        
        String infox;
        int posX=0, posY=0;
        Font font = new Font("courier",Font.ROMAN_BASELINE,10);
        
        RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
        ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,nbSemaine);
        if(!result.get(0).get(0).equals("Erreur : pas de cours disponibles actuellement")){
            for(ArrayList<String> iterator : result){
                infox = iterator.get(4)+"\n"+iterator.get(6).toUpperCase()+"\n"+iterator.get(5)+"\n"+iterator.get(7)+"\n";
                for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                    infox+= "Gr."+iterator.get(k)+" ";
                }
                
                switch (iterator.get(2)) {
                    case "0":
                        posX = 81;
                        break;
                    case "1":
                        posX=231;
                        break;
                    case "2":
                        posX=381;
                        break;
                    case "3":
                        posX=531;
                        break;
                    case "4":
                        posX=681;
                        break;
                    case "5":
                        posX=831;
                        break;
                    default: 
                        break;
                }
                switch(iterator.get(3)){
                    case "0":
                        posY=101;
                        break;
                    case "1":
                        posY=188;
                        break;
                    case "2":
                        posY=276;
                        break;
                    case "3":
                        posY=363;
                        break;
                    case "4":
                        posY=451;
                        break;
                    case "5":
                        posY=538;
                        break;
                    case "6":
                        posY=626;
                        break;
                }
                JTextPane cours = new JTextPane();
                cours.setBackground(Color.orange);
                cours.setFont(font);
                cours.setEditable(false);
                cours.setBounds(posX,posY,149,74);
                cours.setText(infox);
                fenetre.add(cours);
            }
        }else{
            JOptionPane.showMessageDialog(null,result.get(0).get(0));
        }
        
        fenetre.add(grille);
    }
}