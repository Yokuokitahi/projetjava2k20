/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
    private String j;
    private String m;
    private String a;
    private String date;
    
    
    public FenetreAdmin(String login)throws SQLException, ClassNotFoundException{
        fenetre.setTitle("Administration");
        fenetre.setSize(1200,1000);
        
        pan.setLayout(null);
        
        JButton ajouter = new JButton("Ajouter un cours");
        ajouter.setBackground(Color.GREEN);
        ajouter.setBounds(50,50,160,50);
        
        JButton suppr = new JButton("Supprimer un cours");
        suppr.setBackground(Color.RED);
        suppr.setBounds(250,50,160,50);
        
        JButton valide = new JButton("Valider");
        valide.setBounds(350,150,80,30);
        
        ajouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });
       
        suppr.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        jours.setBounds(50, 150, 80, 30);
        //jours.addItem("Jour");
        
        mois.setBounds(150,150,80,30);
        //mois.addItem("Mois");
        
        annee.setBounds(250,150,80,30);
        //annee.addItem("Année");
        
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
        
        pan.add(valide);
        
        valide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                j = jours.getSelectedItem().toString();
                m = mois.getSelectedItem().toString();
                a = annee.getSelectedItem().toString();
                date = a +"-"+m+"-"+j;
                System.out.print(date);
            }
        });
        
        pan.add(ajouter);
        pan.add(suppr);
        pan.add(jours);
        pan.add(mois);
        pan.add(annee);
        
       
        menuBar.add(test1);
        fenetre.setContentPane(pan);
        fenetre.setJMenuBar(menuBar);
        
    }
}
