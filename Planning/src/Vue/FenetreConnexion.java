package Vue;

/**
 *
 * @author Xavier Antoine
 */

import Controleur.Connexion;
import Modele.ConnexionDatabase;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class FenetreConnexion extends FenetreTemplate{
    
    private JTextField login;
    private JPasswordField password;
    private JLabel labelLogin, labelPassword, titre;
    private JButton connectButton;
    private Container container = null;
    private final Connexion connexion;
    private int logUser =0;
    
    public FenetreConnexion() throws SQLException, ClassNotFoundException {
        connexion = new Connexion();
       
        container = fenetre;
        
    }
    
    
    public void constructPanel(){
            
        Font police  = new Font(Font.SERIF, Font.PLAIN,  28);
        Font police2  = new Font(Font.SERIF, Font.PLAIN,  40);
        
        //creation des labels 
        labelLogin=new JLabel("Nom d'utilisateur:");
        labelLogin.setFont(police);
        labelPassword=new JLabel("Mot de passe:");
        labelPassword.setFont(police);
        titre = new JLabel("Bienvenue");
        titre.setFont(police2);
        // creation des champs de texte
        password=new JPasswordField();
        password.setEchoChar('*');
        login=new JTextField();
        
         // creation des boutons
        connectButton=new JButton("Se connecter");
        
        //ajout des items sur le buffer
        container.add(login);
        container.add(password);
        container.add(connectButton);
        container.add(labelLogin);
        container.add(labelPassword);
        container.add(titre);
        titre.setBounds(350,150,400,60);
        login.setBounds(400, 360, 300, 50);
        password.setBounds(400, 460, 300, 50);
        connectButton.setBounds(350, 550, 150, 60);
        labelLogin.setBounds(130, 350, 200, 60);
        labelPassword.setBounds(130, 450, 200, 60);
        connectButton.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String log = login.getText();
                String passw = password.getText();
               
                    try {
                        logUser = connexion.UserConnect(log, passw);
                    } catch (ClassNotFoundException | SQLException  ex) {
                        Logger.getLogger(FenetreConnexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                if(logUser == 1){
                    //FENETRE ADMIN
                    JOptionPane.showMessageDialog(null,"Connexion réussie");
                    fenetre.dispose();
                    try {
                        FenetreAdmin admin = new FenetreAdmin();
                        //admin.ajouterCours();
                        admin.supprimerCours();
                        //admin.modifierCours("5", "2020-06-06", "15:30", "Probabilité", "Cours Magistral", "coudray", "Amphi A", "B, 2022","null");
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(FenetreConnexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(logUser == 2){
                    //FENETRE REFERENT
                    JOptionPane.showMessageDialog(null,"Connexion réussie");
                    fenetre.dispose();
                    
                }
                else if(logUser == 3 || logUser == 4){
                    JOptionPane.showMessageDialog(null,"Connexion réussie");
                    fenetre.dispose();
                    try {
                        int nbSemaine = ConnexionDatabase.SQLNumSemaine();
                        FenetreEdt edt = new FenetreEdt(log);
                        edt.CreerEDT(log, nbSemaine);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(FenetreConnexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"La connexion a échoué","Etat connexion",JOptionPane.ERROR_MESSAGE);
                }
                
            }  
        });
    }
}


