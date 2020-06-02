package Vue;

import Controleur.RechercherSeanceSemaine;
import Modele.ConnexionDatabase;
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
    private final JMenu test1 = new JMenu("Etudiant");
    
    private final JMenuItem item1 = new JMenuItem("Emploi du temps");
    private final JMenuItem item2 = new JMenuItem("Récap cours");
    private final JMenuItem item3 = new JMenuItem("Fermer");
    
    private final Panneau grille = new Panneau();
    
    private final JTextPane semaine = new JTextPane();
    
    public FenetreEdt() throws SQLException, ClassNotFoundException{
        fenetre.setSize(new Dimension(1200,1000));
        fenetre.setContentPane(grille);
        grille.setLayout(null);
        
        test1.add(item1);
        test1.add(item2);
        test1.addSeparator();
        test1.add(item3);
        
        item3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        menuBar.add(test1);
     
    }
    
    public void CreerEDT(final String login, final int nbSemaine) throws SQLException, ClassNotFoundException{
        
        Font font = new Font("courier",Font.ROMAN_BASELINE,10);
        Font font2 = new Font("courier",Font.ROMAN_BASELINE,15);
        
        semaine.setText("Semaine n°"+ nbSemaine);
        semaine.setFont(font2);
        semaine.setEditable(false);
        semaine.setBackground(grille.getBackground());
        semaine.setBounds(475, 10, 155, 20);
        grille.add(semaine);
        
        JButton suivant = new JButton("Semaine suivante");
        suivant.setBounds(550,30,155,20);
        
        JButton prec = new JButton("Semaine précédente");
        prec.setBounds(350,30,155,20);
        
        
        item1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                grille.removeAll();
                grille.repaint();
                int nbSemaineact = ConnexionDatabase.SQLNumSemaine();
                CreerEDT(login,nbSemaineact);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        prec.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    grille.removeAll();
                    grille.repaint();
                    CreerEDT(login, nbSemaine-1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        suivant.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    grille.removeAll();
                    grille.repaint();
                    CreerEDT(login, nbSemaine+1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        
        
        fenetre.setJMenuBar(menuBar);
        grille.add(suivant);
        grille.add(prec);        
        
        String infox;
        int posX=0, posY=0;
        
        
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
                grille.add(cours);
            }
        }else{
            JOptionPane.showMessageDialog(null,result.get(0).get(0));
        }
    }
}