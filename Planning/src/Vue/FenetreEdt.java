package Vue;

import Controleur.InfosDB;
import Controleur.RechercherSeance;
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
    private final JMenu menuEtudiant = new JMenu("Etudiant");
    
    private final JMenuItem item1 = new JMenuItem("Emploi du temps");
    private final JMenuItem item2 = new JMenuItem("Recap");
    private final JMenuItem item3 = new JMenuItem("Fermer");
    private final JMenuItem deco = new JMenuItem("Déconnexion");
    
    private final Font font = new Font("courier",Font.ROMAN_BASELINE,10);
    private final Font font2 = new Font("courier",Font.ROMAN_BASELINE,15);
    
    private final JPanel buffer = new JPanel();
    private final Panneau grille = new Panneau();
    
    private final JTextPane semaine = new JTextPane();
    
    public FenetreEdt(final String login) throws SQLException, ClassNotFoundException{
        fenetre.setSize(new Dimension(1200,1000)); 
        buffer.setLayout(null);
        grille.setLayout(null);
        menuEtudiant.add(item1);
        menuEtudiant.add(item2);
        menuEtudiant.addSeparator();
        menuEtudiant.add(item3);
        menuBar.add(menuEtudiant);
        menuBar.add(deco);
        
        deco.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.dispose();
                FenetreConnexion test = null;
                try {
                    test = new FenetreConnexion();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
                test.constructPanel();
            }
        });
        
        item1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    buffer.removeAll();
                    buffer.repaint();
                    int nbSemaineact= ConnexionDatabase.SQLNumSemaine();
                    CreerEDT(login, nbSemaineact);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        item2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    buffer.removeAll();
                    buffer.repaint();
                    Recap(login);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        item3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

           
    }
    
    public void CreerEDT(final String login, final int nbSemaine) throws SQLException, ClassNotFoundException{
        fenetre.setContentPane(grille);
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
        
        JButton affichage = new JButton("Afficher en liste");
        affichage.setBounds(1000,200,155,20);
        
        affichage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                grille.removeAll();
                grille.repaint();
                try {
                    ListeEdt(login, nbSemaine);
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
        grille.add(affichage);
        
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
    
    public void Recap(final String login) throws SQLException, ClassNotFoundException{

        RechercherSeance heures = new RechercherSeance();
        ArrayList<String> matieres = InfosDB.getMatiere();
        fenetre.setJMenuBar(menuBar); 
        int posX = 81,posY=101, pas = 25, i=0;

  
        fenetre.setContentPane(buffer);

        
        ArrayList<String> infosHeures;
        ArrayList<String> informations = new ArrayList<>();
        for(String iterator:matieres){
            infosHeures = heures.RecapSeance(login, iterator);
            double nbHeures = (infosHeures.size())*1.5;
            informations.add(iterator + "  " + nbHeures + " heures");
        }
       
        for(String iterator:informations){
            JTextPane cours = new JTextPane();
                cours.setBackground(Color.LIGHT_GRAY);
                cours.setFont(font);
                cours.setEditable(false);
                cours.setBounds(posX,posY+(i*pas),149,20);
                cours.setText(iterator);
                buffer.add(cours);
                i++;
        }  
    }
    
    public void ListeEdt(final String login, final int nbSemaine) throws SQLException, ClassNotFoundException{
        //JScrollPane test = new JScrollPane(buffer);
        
        JTextPane jours = new JTextPane();
        JTextPane jours2 = new JTextPane();
        JTextPane jours3 = new JTextPane();
        JTextPane jours4 = new JTextPane();
        JTextPane jours5 = new JTextPane();
        JTextPane jours6 = new JTextPane();
        fenetre.setContentPane(buffer);
        semaine.setText("Semaine n°"+ nbSemaine);
        semaine.setFont(font2);
        semaine.setEditable(false);
        semaine.setBackground(grille.getBackground());
        semaine.setBounds(475, 10, 155, 20);
        buffer.add(semaine);
        
        JButton suivant = new JButton("Semaine suivante");
        suivant.setBounds(550,30,155,20);
        suivant.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    buffer.removeAll();
                    buffer.repaint();
                    ListeEdt( login, nbSemaine+1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        JButton prec = new JButton("Semaine précédente");
        prec.setBounds(350,30,155,20);
        prec.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    buffer.removeAll();
                    buffer.repaint();
                    ListeEdt( login, nbSemaine-1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        JButton affichage = new JButton("Afficher en tableau");
        affichage.setBounds(1000,200,155,20);   
        affichage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                grille.removeAll();
                grille.repaint();
                try {
                    CreerEDT(login, nbSemaine);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        });
        
        fenetre.setJMenuBar(menuBar);
        buffer.add(suivant);
        buffer.add(prec); 
        buffer.add(affichage);
        int posX=200, posY=150, var =0;
        String infox; 
        
        RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
        ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,nbSemaine);
        
        if(!result.get(0).get(0).equals("Erreur : pas de cours disponibles actuellement")){
            for(ArrayList<String> iterator : result){     
                infox = "heure du cours"+ " "+ iterator.get(4)+"  "+iterator.get(6).toUpperCase()+"  "+iterator.get(7)+"  "+iterator.get(5)+"\n";    
                for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                    infox+= "Gr."+iterator.get(k)+" ";
                    
                }
            }
            
            jours.setText("Lundi"); 
            jours.setBounds(posX, posY, 150, 20);
            buffer.add(jours);
            posY+=20;
            
            
            for(ArrayList<String> iterator : result){
               if(iterator.get(3).equals("0")){
                    iterator.set(3, "08:30 - 10:00");
                }
                if(iterator.get(3).equals("1")){
                    iterator.set(3, "10:15 - 11:45");
                 
                }
                if(iterator.get(3).equals("2")){
                    iterator.set(3, "12:00 - 13:30");
                    
                }
                if(iterator.get(3).equals("3")){
                    iterator.set(3, "13:45 - 15:15");
                }
                if(iterator.get(3).equals("4")){
                    iterator.set(3, "15:30 - 17:00");
                }
                if(iterator.get(3).equals("5")){
                    iterator.set(3, "17:15 - 18:45");
                }
                if(iterator.get(3).equals("6")){
                    iterator.set(3, "19:30 - 20:30");
                }
                
                
                if("0".equals(iterator.get(2))){
                    var=1;
                    infox = iterator.get(3)+ " "+ iterator.get(4)+"  "+iterator.get(6).toUpperCase()+"  "+iterator.get(7)+"  "+iterator.get(5)+"\n";    
                    for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                        infox+= "Gr."+iterator.get(k)+" ";      
                    }
                    JTextPane cours = new JTextPane();
                    cours.setBackground(Color.orange);
                    cours.setFont(font);
                    cours.setEditable(false);
                    cours.setBounds(posX,posY,300,74);
                    cours.setText(infox);
                    buffer.add(cours);
                    posY+=75;
                }
            } if(var==0){
                    JTextPane pasDeCours = new JTextPane();
                    pasDeCours.setBackground(Color.orange);
                    pasDeCours.setFont(font);
                    pasDeCours.setEditable(false);
                    pasDeCours.setBounds(posX,posY,300,74);
                    pasDeCours.setText("Pas de cours de jour-là");
                    buffer.add(pasDeCours);
                    posY+=75;
                }  
            var =0;
            jours2.setText("Mardi"); 
            jours2.setBounds(posX, posY, 150, 20);
            buffer.add(jours2);
            posY+=20;  
            
            for(ArrayList<String> iterator : result){
              if(iterator.get(3).equals("0")){
                    iterator.set(3, "08:30 - 10:00");
                }
                if(iterator.get(3).equals("1")){
                    iterator.set(3, "10:15 - 11:45");
                    
                }
                if(iterator.get(3).equals("2")){
                    iterator.set(3, "12:00 - 13:30");
                   
                }
                if(iterator.get(3).equals("3")){
                    iterator.set(3, "13:45 - 15:15");
                }
                if(iterator.get(3).equals("4")){
                    iterator.set(3, "15:30 - 17:00");
                }
                if(iterator.get(3).equals("5")){
                    iterator.set(3, "17:15 - 18:45");
                }
                if(iterator.get(3).equals("6")){
                    iterator.set(3, "19:30 - 20:30");
                }
                
                 
                if("1".equals(iterator.get(2))){
                    var = 1;
                    infox = iterator.get(3) + " "+ iterator.get(4)+"  "+iterator.get(6).toUpperCase()+"  "+iterator.get(7)+"  "+iterator.get(5)+"\n";    
                    for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                        infox+= "Gr."+iterator.get(k)+" "; 
                    }
                    JTextPane cours = new JTextPane();
                    cours.setBackground(Color.orange);
                    cours.setFont(font);
                    cours.setEditable(false);
                    cours.setBounds(posX,posY,300,74);
                    cours.setText(infox);
                    buffer.add(cours);
                    posY+=75;
                }
            }if(var==0){
                    JTextPane pasDeCours = new JTextPane();
                    pasDeCours.setBackground(Color.orange);
                    pasDeCours.setFont(font);
                    pasDeCours.setEditable(false);
                    pasDeCours.setBounds(posX,posY,300,74);
                    pasDeCours.setText("Pas de cours de jour-là");
                    buffer.add(pasDeCours);
                    posY+=75;
                } 
            var =0;
            jours3.setText("Mercredi"); 
            jours3.setBounds(posX, posY, 150, 20);
            buffer.add(jours3);
            posY+=20;

            for(ArrayList<String> iterator : result){
              if(iterator.get(3).equals("0")){
                    iterator.set(3, "08:30 - 10:00");
                }
                if(iterator.get(3).equals("1")){
                    iterator.set(3, "10:15 - 11:45");

                }
                if(iterator.get(3).equals("2")){
                    iterator.set(3, "12:00 - 13:30");

                }
                if(iterator.get(3).equals("3")){
                    iterator.set(3, "13:45 - 15:15");
                }
                if(iterator.get(3).equals("4")){
                    iterator.set(3, "15:30 - 17:00");
                }
                if(iterator.get(3).equals("5")){
                    iterator.set(3, "17:15 - 18:45");
                }
                if(iterator.get(3).equals("6")){
                    iterator.set(3, "19:30 - 20:30");
                }

                 
                if("2".equals(iterator.get(2))){
                    var =1;
                    infox = iterator.get(3) + " "+ iterator.get(4)+"  "+iterator.get(6).toUpperCase()+"  "+iterator.get(7)+"  "+iterator.get(5)+"\n";
                    for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                        infox+= "Gr."+iterator.get(k)+" ";
                    }
                    JTextPane cours = new JTextPane();
                    cours.setBackground(Color.orange);
                    cours.setFont(font);
                    cours.setEditable(false);
                    cours.setBounds(posX,posY,300,74);
                    cours.setText(infox);
                    buffer.add(cours);
                    posY+=75;
                }
            }if(var==0){
                    JTextPane pasDeCours = new JTextPane();
                    pasDeCours.setBackground(Color.orange);
                    pasDeCours.setFont(font);
                    pasDeCours.setEditable(false);
                    pasDeCours.setBounds(posX,posY,300,74);
                    pasDeCours.setText("Pas de cours de jour-là");
                    buffer.add(pasDeCours);
                    posY+=75;
                } 
            var = 0;
            jours4.setText("Jeudi"); 
            jours4.setBounds(posX, posY, 150, 20);
            buffer.add(jours4);
            posY+=20;  
            
            for(ArrayList<String> iterator : result){
                
              if(iterator.get(3).equals("0")){
                    iterator.set(3, "08:30 - 10:00");
                }
                if(iterator.get(3).equals("1")){
                    iterator.set(3, "10:15 - 11:45");
                    
                }
                if(iterator.get(3).equals("2")){
                    iterator.set(3, "12:00 - 13:30");
                 
                }
                if(iterator.get(3).equals("3")){
                    iterator.set(3, "13:45 - 15:15");
                }
                if(iterator.get(3).equals("4")){
                    iterator.set(3, "15:30 - 17:00");
                }
                if(iterator.get(3).equals("5")){
                    iterator.set(3, "17:15 - 18:45");
                }
                if(iterator.get(3).equals("6")){
                    iterator.set(3, "19:30 - 20:30");
                }
                
                if("3".equals(iterator.get(2))){
                    var=1;
                    infox = iterator.get(3) + " "+ iterator.get(4)+"  "+iterator.get(6).toUpperCase()+"  "+iterator.get(7)+"  "+iterator.get(5)+"\n";    
                    for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                        infox+= "Gr."+iterator.get(k)+" ";   
                    }
                    JTextPane cours = new JTextPane();
                    cours.setBackground(Color.orange);
                    cours.setFont(font);
                    cours.setEditable(false);
                    cours.setBounds(posX,posY,300,74);
                    cours.setText(infox);
                    buffer.add(cours);
                    posY+=75;
                }
            }if(var==0){
                    JTextPane pasDeCours = new JTextPane();
                    pasDeCours.setBackground(Color.orange);
                    pasDeCours.setFont(font);
                    pasDeCours.setEditable(false);
                    pasDeCours.setBounds(posX,posY,300,74);
                    pasDeCours.setText("Pas de cours de jour-là");
                    buffer.add(pasDeCours);
                    posY+=75;
                } 
            var = 0;
            jours5.setText("Vendredi"); 
            jours5.setBounds(posX, posY, 150, 20);
            buffer.add(jours5);
            posY+=20;  
            
            for(ArrayList<String> iterator : result){
                if(iterator.get(3).equals("0")){
                    iterator.set(3, "08:30 - 10:00");
                }
                if(iterator.get(3).equals("1")){
                    iterator.set(3, "10:15 - 11:45");
                  
                }
                if(iterator.get(3).equals("2")){
                    iterator.set(3, "12:00 - 13:30");
                   
                }
                if(iterator.get(3).equals("3")){
                    iterator.set(3, "13:45 - 15:15");
                }
                if(iterator.get(3).equals("4")){
                    iterator.set(3, "15:30 - 17:00");
                }
                if(iterator.get(3).equals("5")){
                    iterator.set(3, "17:15 - 18:45");
                }
                if(iterator.get(3).equals("6")){
                    iterator.set(3, "19:30 - 20:30");
                }
                 
                if("4".equals(iterator.get(2))){
                    var=1;
                    infox = iterator.get(3) + " "+ iterator.get(4)+"  "+iterator.get(6).toUpperCase()+"  "+iterator.get(7)+"  "+iterator.get(5)+"\n";    
                    for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                        infox+= "Gr."+iterator.get(k)+" ";
                    }
                    JTextPane cours = new JTextPane();
                    cours.setBackground(Color.orange);
                    cours.setFont(font);
                    cours.setEditable(false);
                    cours.setBounds(posX,posY,300,74);
                    cours.setText(infox);
                    buffer.add(cours);
                    posY+=75;
                }
            }if(var==0){
                    JTextPane pasDeCours = new JTextPane();
                    pasDeCours.setBackground(Color.orange);
                    pasDeCours.setFont(font);
                    pasDeCours.setEditable(false);
                    pasDeCours.setBounds(posX,posY,300,74);
                    pasDeCours.setText("Pas de cours de jour-là");
                    buffer.add(pasDeCours);
                    posY+=75;
                } 
            var = 0;
            jours6.setText("Samedi"); 
            jours6.setBounds(posX, posY, 150, 20);
            buffer.add(jours6);
            posY+=20;  
            
            for(ArrayList<String> iterator : result){
               if(iterator.get(3).equals("0")){
                    iterator.set(3, "08:30 - 10:00");
                }
                if(iterator.get(3).equals("1")){
                    iterator.set(3, "10:15 - 11:45");
       
                }
                if(iterator.get(3).equals("2")){
                    iterator.set(3, "12:00 - 13:30");
                   
                }
                if(iterator.get(3).equals("3")){
                    iterator.set(3, "13:45 - 15:15");
                }
                if(iterator.get(3).equals("4")){
                    iterator.set(3, "15:30 - 17:00");
                }
                if(iterator.get(3).equals("5")){
                    iterator.set(3, "17:15 - 18:45");
                }
                if(iterator.get(3).equals("6")){
                    iterator.set(3, "19:30 - 20:30");
                }
                
                if("5".equals(iterator.get(2))){
                    var =1;
                    infox = iterator.get(3) + " "+ iterator.get(4)+"  "+iterator.get(6).toUpperCase()+"  "+iterator.get(7)+"  "+iterator.get(5)+"\n";    
                    for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                        infox+= "Gr."+iterator.get(k)+" ";
                    }
                    JTextPane cours = new JTextPane();
                    cours.setBackground(Color.orange);
                    cours.setFont(font);
                    cours.setEditable(false);
                    cours.setBounds(posX,posY,300,74);
                    cours.setText(infox);
                    buffer.add(cours);
                    posY+=75;
                }
            } if(var==0){
                    JTextPane pasDeCours = new JTextPane();
                    pasDeCours.setBackground(Color.orange);
                    pasDeCours.setFont(font);
                    pasDeCours.setEditable(false);
                    pasDeCours.setBounds(posX,posY,300,74);
                    pasDeCours.setText("Pas de cours de jour-là");
                    buffer.add(pasDeCours);
                    posY+=75;
                }            
        }else{
            JOptionPane.showMessageDialog(null,result.get(0).get(0));
        }
    }
}