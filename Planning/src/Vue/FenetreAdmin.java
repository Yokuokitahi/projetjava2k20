/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.InfosDB;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Drakking
 */
public class FenetreAdmin extends FenetreTemplate{
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu test1 = new JMenu("Admin");
    
    private final JPanel pan = new JPanel();
    private final JComboBox jours = new JComboBox();
    private final JComboBox mois = new JComboBox();
    private final JComboBox annee = new JComboBox();
    private final JComboBox heure = new JComboBox();
    private final JComboBox min = new JComboBox();
    private final JComboBox matiere = new JComboBox();
    private final JComboBox typeCours = new JComboBox();
    private final JComboBox enseignants = new JComboBox();
    
    private String j;
    private String m;
    private String a;
    private String h;
    private String mn;
    private String date;
    private String horaire;
    private String typeC;
    private String mat;
    private String prof;
    
    
    public FenetreAdmin(String login)throws SQLException, ClassNotFoundException{
        fenetre.setTitle("Administration");
        fenetre.setSize(1200,1000);
        pan.setLayout(null);
        
        //fonction pour avoir matieres + typecours depuis BDD
        ArrayList<String> matieres = InfosDB.getMatiere();
        ArrayList<String> type = InfosDB.getTypeDeCours();
        ArrayList<String> enseignant = InfosDB.getEnseignant();
        
        //Creations de boutons
        JButton ajouter = new JButton("Ajouter un cours");
        ajouter.setBackground(Color.GREEN);
        ajouter.setBounds(50,50,160,50);
        
        JButton suppr = new JButton("Supprimer un cours");
        suppr.setBackground(Color.RED);
        suppr.setBounds(250,50,160,50);
        
        JButton valide = new JButton("Valider");
        valide.setBounds(250,250,80,30);
        
        //Bouton ajouter cours
        ajouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });
       
        //bouton supprimer cours
        suppr.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        //on créer des menus deroulants
        jours.setBounds(50, 150, 80, 30);
        jours.addItem("Jour");
        
        mois.setBounds(150,150,80,30);
        mois.addItem("Mois");
        
        annee.setBounds(250,150,80,30);
        annee.addItem("Année");
        
        heure.setBounds(350,150,80,30);
        heure.addItem("Heure");
        
        min.setBounds(450,150,80,30);
        min.addItem("Minutes");
        
        //Recupere les matieres ds un menu deroulant
        matiere.setBounds(50,200,120,30);
        matiere.addItem("Matière");
        for (int i = 0; i < matieres.size();i++){
            matiere.addItem(matieres.get(i));
        }
        
        //Recupere type de cours ds un menu deroulant
        typeCours.setBounds(200,200,120,30);
        typeCours.addItem("Type de cours");
        for (int i = 0; i < type.size();i++){
            typeCours.addItem(type.get(i));
        }
        
        //Recupere le prof associé a une matiere via menu deroulant
        enseignants.setBounds(350,200,120,30);
        enseignants.addItem("Enseignants");
        for (int i = 0; i < enseignant.size();i++){
            enseignants.addItem(enseignant.get(i));
        }
        
        //setup des menus deroulants
        for (int i=1;i<=31;i++)
        {
            jours.addItem(i);
        }
        
        for (int i=1;i<=12;i++)
        {
            mois.addItem(i);
        }
        
        for (int i=2019;i<=2023;i++)
        {
            annee.addItem(i);
        }
        
        for (int i=8;i<=20;i++)
        {
            heure.addItem(i);
        }
        
        for (int i=0;i<=45;i++)
        {
            min.addItem(i);
            i=i-1+15;
        }
        
        
        //on ajoute les menus a notre pannel
        pan.add(valide);
        pan.add(jours);
        pan.add(mois);
        pan.add(annee);
        pan.add(heure);
        pan.add(min);
        pan.add(typeCours);
        pan.add(matiere);
        pan.add(enseignants);  
        
        
        //Boutons de validation avec action (on recupere les infos saisies)
        valide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                j = jours.getSelectedItem().toString();
                m = mois.getSelectedItem().toString();
                a = annee.getSelectedItem().toString();
                h = heure.getSelectedItem().toString();
                mn= min.getSelectedItem().toString();
                date = a +"-"+m+"-"+j;
                horaire = h + ":" + mn;
                prof = enseignants.getSelectedItem().toString();
                typeC = typeCours.getSelectedItem().toString();
                mat = matiere.getSelectedItem().toString();
                System.out.println(date);
                System.out.println(horaire);
                System.out.println(typeC);
                System.out.println(mat);
                System.out.println(prof);
            }
        });
 
        pan.add(ajouter);
        pan.add(suppr);

        menuBar.add(test1);
        fenetre.setContentPane(pan);
        fenetre.setJMenuBar(menuBar);
        
    }
}
