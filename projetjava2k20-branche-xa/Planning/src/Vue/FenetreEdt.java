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
import java.util.ArrayList;

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
    

    @SuppressWarnings("empty-statement")
    public FenetreEdt(String login) throws SQLException, ClassNotFoundException{
        
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
        
       RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
       ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,22);
       int i=0;
        if(!result.get(0).get(0).equals("Erreur : pas de cours disponibles actuellement")){
            for(ArrayList<String> iterator : result){
                i+=1;
                System.out.println("Cours n°"+i);
                System.out.println("ID de la séance : " + iterator.get(0));
                System.out.println("Semaine n°" + iterator.get(1));
                System.out.println("Jour de la séance : " + iterator.get(2));
                System.out.println("Heure de début : " + iterator.get(3));
                System.out.println("Matière : " + iterator.get(4));
                System.out.println("Type de cours : " + iterator.get(5));
                System.out.println("Professeur : " + iterator.get(6).toUpperCase());
                System.out.println("Salle : " + iterator.get(7));
                System.out.println("Site : " + iterator.get(8));
                for(int k=9;k<iterator.size();k++){
                    System.out.println("Groupe : " + iterator.get(k));
                }
                System.out.println("\n-----------------\n");
                
                switch (iterator.get(2)) {
                    case "0":
                        if(null!=iterator.get(3))switch (iterator.get(3)) {
                    case "0":
                        JTextPane coursA = new JTextPane();
                        coursA.setBackground(Color.orange);
                        coursA.setBounds(81,101,149,74);
                        fenetre.add(coursA);
                        break;
                    case "1":
                        JTextPane coursB = new JTextPane();
                        coursB.setBackground(Color.orange);
                        coursB.setBounds(81,188,149,74);
                        fenetre.add(coursB);

                        break;
                    case "2":
                        JTextPane coursC = new JTextPane();
                        coursC.setBackground(Color.orange);
                        coursC.setBounds(81,276,149,74);
                        fenetre.add(coursC);

                        break;
                    case "3":
                        JTextPane coursD = new JTextPane();
                        coursD.setBackground(Color.orange);
                        coursD.setBounds(81,363,149,74);
                        fenetre.add(coursD);
                        break;
                    case "4":
                        JTextPane coursE = new JTextPane();
                        coursE.setBackground(Color.orange);
                        coursE.setBounds(81,451,149,74);
                        fenetre.add(coursE);
                        break;
                    case "5":
                        JTextPane coursF = new JTextPane();
                        coursF.setBackground(Color.orange);
                        coursF.setBounds(81,538,149,74);
                        fenetre.add(coursF);
                        break;
                    case "6":
                        JTextPane coursG = new JTextPane();
                        coursG.setBackground(Color.orange);
                        coursG.setBounds(81,626,149,74);
                        fenetre.add(coursG);

                        break;
                    default:
                        break;
                }
break;
                    case "1":
                         if(null!=iterator.get(3))switch (iterator.get(3)) {
                    case "0":
                        JTextPane coursH = new JTextPane();
                        coursH.setBackground(Color.orange);
                        coursH.setBounds(231,101,149,74);
                        fenetre.add(coursH);
                        break;
                    case "1":
                        JTextPane coursI = new JTextPane();
                        coursI.setBackground(Color.orange);
                        coursI.setBounds(231,188,149,74);
                        fenetre.add(coursI);
                        break;
                    case "2":
                        JTextPane coursJ = new JTextPane();
                        coursJ.setBackground(Color.orange);
                        coursJ.setBounds(231,276,149,74);
                        fenetre.add(coursJ);
                        break;
                    case "3":
                        JTextPane coursK = new JTextPane();
                        coursK.setBackground(Color.orange);
                        coursK.setBounds(231,363,149,74);
                        fenetre.add(coursK);
                        break;
                    case "4":
                        JTextPane coursL = new JTextPane();
                        coursL.setBackground(Color.orange);
                        coursL.setBounds(231,451,149,74);
                        fenetre.add(coursL);
                        break;
                    case "5":
                        JTextPane coursM = new JTextPane();
                        coursM.setBackground(Color.orange);
                        coursM.setBounds(231,538,149,74);
                        fenetre.add(coursM);
                        break;
                    case "6":
                        JTextPane coursN = new JTextPane();
                        coursN.setBackground(Color.orange);
                        coursN.setBounds(231,626,149,74);
                        fenetre.add(coursN);
                        break;
                    default:
                        break;
                }
break;
                    case "2":
                         if(null!=iterator.get(3))switch (iterator.get(3)) {
                    case "0":
                        JTextPane coursO = new JTextPane();
                        coursO.setBackground(Color.orange);
                        coursO.setBounds(381,101,149,74);
                        fenetre.add(coursO);
                        break;
                    case "1":
                        JTextPane coursP = new JTextPane();
                        coursP.setBackground(Color.orange);
                        coursP.setBounds(381,188,149,74);
                        fenetre.add(coursP);
                        break;
                    case "2":
                        JTextPane coursQ = new JTextPane();
                        coursQ.setBackground(Color.orange);
                        coursQ.setBounds(381,276,149,74);
                        fenetre.add(coursQ);
                        break;
                    case "3":
                        JTextPane coursR = new JTextPane();
                        coursR.setBackground(Color.orange);
                        coursR.setBounds(381,363,149,74);
                        fenetre.add(coursR);
                        break;
                    case "4":
                        JTextPane coursS = new JTextPane();
                        coursS.setBackground(Color.orange);
                        coursS.setBounds(381,451,149,74);
                        fenetre.add(coursS);
                        break;
                    case "5":
                        JTextPane coursT = new JTextPane();
                        coursT.setBackground(Color.orange);
                        coursT.setBounds(381,538,149,74);
                        fenetre.add(coursT);
                        break;
                    case "6":
                        JTextPane coursU = new JTextPane();
                        coursU.setBackground(Color.orange);
                        coursU.setBounds(381,626,149,74);
                        fenetre.add(coursU);
                        break;
                    default:
                        break;
                }
break;
                    case "3":
                       if(null!=iterator.get(3))switch (iterator.get(3)) {
                           case "0":
                                JTextPane coursV = new JTextPane();
                                coursV.setBackground(Color.orange);
                                coursV.setBounds(531,101,149,74);
                                fenetre.add(coursV);
                            break;
                            case "1":
                                JTextPane coursW = new JTextPane();
                                coursW.setBackground(Color.orange);
                                coursW.setBounds(531,188,149,74);
                                fenetre.add(coursW);

                            break;
                            case "2":
                                JTextPane coursX = new JTextPane();
                                coursX.setBackground(Color.orange);
                                coursX.setBounds(531,276,149,74);
                                fenetre.add(coursX);
                            break;
                            case "3":
                                JTextPane coursX1 = new JTextPane();
                                coursX1.setBackground(Color.orange);
                                coursX1.setBounds(531,363,149,74);
                                fenetre.add(coursX1);
                            break;
                            case "4":
                                JTextPane coursY = new JTextPane();
                                coursY.setBackground(Color.orange);
                                coursY.setBounds(531,451,149,74);
                                fenetre.add(coursY);
                            break;
                            case "5":
                                JTextPane coursZ = new JTextPane();
                                coursZ.setBackground(Color.orange);
                                coursZ.setBounds(531,538,149,74);
                                fenetre.add(coursZ);
                            break;
                            case "6":
                                JTextPane cours1A = new JTextPane();
                                cours1A.setBackground(Color.orange);
                                cours1A.setBounds(531,626,149,74);
                                fenetre.add(cours1A);
                            break;
                            default:
                            break;
                        } break;
                        
                    case "4":
                        if("0".equals(iterator.get(3))){
                            JTextPane cours1B = new JTextPane();
                            cours1B.setBackground(Color.orange);
                            cours1B.setBounds(681,101,149,74);
                            fenetre.add(cours1B);
                        }else if("1".equals(iterator.get(3))){
                            JTextPane cours1C = new JTextPane();
                            cours1C.setBackground(Color.orange);
                            cours1C.setBounds(681,188,149,74);
                            fenetre.add(cours1C);
                        }else if("2".equals(iterator.get(3))){
                            JTextPane cours1D = new JTextPane();
                            cours1D.setBackground(Color.orange);
                            cours1D.setBounds(681,276,149,74);
                            fenetre.add(cours1D);
                        }else if("3".equals(iterator.get(3))){
                            JTextPane cours1E = new JTextPane();
                            cours1E.setBackground(Color.orange);
                            cours1E.setBounds(681,363,149,74);
                            fenetre.add(cours1E);
                        }else if("4".equals(iterator.get(3))){
                            JTextPane cours1F = new JTextPane();
                            cours1F.setBackground(Color.orange);
                            cours1F.setBounds(681,451,149,74);
                            fenetre.add(cours1F);
                        }else if("5".equals(iterator.get(3))){
                            JTextPane cours1G = new JTextPane();
                            cours1G.setBackground(Color.orange);
                            cours1G.setBounds(681,538,149,74);
                            fenetre.add(cours1G);
                        }else if("6".equals(iterator.get(3))){
                            JTextPane cours1H = new JTextPane();
                            cours1H.setBackground(Color.orange);
                            cours1H.setBounds(681,626,149,74);
                            fenetre.add(cours1H);
                        }   break;
                    default:
                        break;
                }
                if("5".equals(iterator.get(2))){
                    if(null != iterator.get(3))switch (iterator.get(3)) {
                        case "0":
                            JTextPane cours1I = new JTextPane();
                            cours1I.setBackground(Color.orange);
                            cours1I.setBounds(831,101,149,74);
                            fenetre.add(cours1I);
                            break;
                        case "1":{
                            JTextPane cours1J = new JTextPane();
                            cours1J.setBackground(Color.orange);
                            cours1J.setBounds(831,188,149,74);
                            fenetre.add(cours1J);
                                break;
                            }
                        case "2":{
                            JTextPane cours1J = new JTextPane();
                            cours1J.setBackground(Color.orange);
                            cours1J.setBounds(831,276,149,74);
                            fenetre.add(cours1J);
                                break;
                            }
                        case "3":
                            JTextPane cours1K = new JTextPane();
                            cours1K.setBackground(Color.orange);
                            cours1K.setBounds(831,363,149,74);
                            fenetre.add(cours1K);
                            break;
                        case "4":
                            JTextPane cours1L = new JTextPane();
                            cours1L.setBackground(Color.orange);
                            cours1L.setBounds(831,451,149,74);
                            fenetre.add(cours1L);
                            break;
                        case "5":
                            JTextPane cours1M = new JTextPane();
                            cours1M.setBackground(Color.orange);
                            cours1M.setBounds(831,538,149,74);
                            fenetre.add(cours1M);
                            break;
                        case "6":
                            JTextPane cours1N = new JTextPane();
                            cours1N.setBackground(Color.orange);
                            cours1N.setBounds(831,626,149,74);
                            fenetre.add(cours1N);
                        
                            break;
                        default:
                            break;
                    }
            }
        }
            fenetre.setJMenuBar(menuBar);
            fenetre.add(grille);
        }else{
            JOptionPane.showMessageDialog(null,result.get(0).get(0),"Etat connexion",JOptionPane.ERROR_MESSAGE);
        }
        
       /* 
        JTextPane cours1 = new JTextPane();
        cours1.setBackground(Color.orange);
        cours1.setBounds(681,101,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours1);
        fenetre.add(grille);
       //fenetre.add(new JScrollPane(tableau));
       JTextPane cours2 = new JTextPane();
        cours2.setBackground(Color.yellow);
        cours2.setBounds(681,188,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours2);
        fenetre.add(grille);
        JTextPane cours3 = new JTextPane();
        cours3.setBackground(Color.red);
        cours3.setBounds(681,276,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours3);
        fenetre.add(grille);
        JTextPane cours4 = new JTextPane();
        cours4.setBackground(Color.blue);
        cours4.setBounds(681,363,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours4);
        fenetre.add(grille);
        JTextPane cours5 = new JTextPane();
        cours5.setBackground(Color.yellow);
        cours5.setBounds(681,451,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours5);
        fenetre.add(grille);
        JTextPane cours6 = new JTextPane();
        cours6.setBackground(Color.orange);
        cours6.setBounds(681,538,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours6);
        fenetre.add(grille);
        JTextPane cours7 = new JTextPane();
        cours7.setBackground(Color.red);
        cours7.setBounds(681,626,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours7);
        fenetre.add(grille);*/
    }
}
