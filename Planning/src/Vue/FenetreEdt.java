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
        
        Font font = new Font("courier",Font.ROMAN_BASELINE,10);
        
        RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
        ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,nbSemaine);
        System.out.println(result.get(0).get(0));
        if(!result.get(0).get(0).equals("Erreur : pas de cours disponibles actuellement")){
            for(ArrayList<String> iterator : result){
                infox = iterator.get(4)+"\n"+iterator.get(6).toUpperCase()+"\n"+iterator.get(5)+"\n"+iterator.get(7)+"\n";
                
                for(int k=9;k<iterator.size();k++){
                    infox=infox + "Gr."+iterator.get(k)+" ";
                }
                
                switch (iterator.get(2)) {
                    case "0":
                        if(null!=iterator.get(3))switch (iterator.get(3)) {
                    case "0":
                        JTextPane coursA = new JTextPane();
                        coursA.setBackground(Color.orange);
                        coursA.setBounds(81,101,149,74);
                        coursA.setText(infox);
                        coursA.setFont(font);
                        coursA.setEditable(false);
                        fenetre.add(coursA);
                        break;
                    case "1":
                        JTextPane coursB = new JTextPane();
                        coursB.setBackground(Color.orange);
                        coursB.setBounds(81,188,149,74);
                        coursB.setText(infox);
                        coursB.setFont(font);
                        coursB.setEditable(false);
                        fenetre.add(coursB);

                        break;
                    case "2":
                        JTextPane coursC = new JTextPane();
                        coursC.setBackground(Color.orange);
                        coursC.setBounds(81,276,149,74);
                        coursC.setText(infox);
                        coursC.setFont(font);
                        coursC.setEditable(false);
                        fenetre.add(coursC);

                        break;
                    case "3":
                        JTextPane coursD = new JTextPane();
                        coursD.setBackground(Color.orange);
                        coursD.setBounds(81,363,149,74);
                        coursD.setText(infox);
                        coursD.setFont(font);
                        coursD.setEditable(false);
                        fenetre.add(coursD);
                        break;
                    case "4":
                        JTextPane coursE = new JTextPane();
                        coursE.setBackground(Color.orange);
                        coursE.setBounds(81,451,149,74);
                        coursE.setText(infox);
                        coursE.setFont(font);
                        coursE.setEditable(false);
                        fenetre.add(coursE);
                        break;
                    case "5":
                        JTextPane coursF = new JTextPane();
                        coursF.setBackground(Color.orange);
                        coursF.setBounds(81,538,149,74);
                        coursF.setText(infox);
                        coursF.setFont(font);
                        coursF.setEditable(false);
                        fenetre.add(coursF);
                        break;
                    case "6":
                        JTextPane coursG = new JTextPane();
                        coursG.setBackground(Color.orange);
                        coursG.setBounds(81,626,149,74);
                        coursG.setText(infox);
                        coursG.setFont(font);
                        coursG.setEditable(false);
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
                        coursH.setText(infox);
                        coursH.setFont(font);
                        coursH.setEditable(false);
                        fenetre.add(coursH);
                        break;
                    case "1":
                        JTextPane coursI = new JTextPane();
                        coursI.setBackground(Color.orange);
                        coursI.setBounds(231,188,149,74);
                        coursI.setText(infox);
                        coursI.setFont(font);
                        coursI.setEditable(false);
                        fenetre.add(coursI);
                        break;
                    case "2":
                        JTextPane coursJ = new JTextPane();
                        coursJ.setBackground(Color.orange);
                        coursJ.setBounds(231,276,149,74);
                        coursJ.setText(infox);
                        coursJ.setFont(font);
                        coursJ.setEditable(false);
                        fenetre.add(coursJ);
                        break;
                    case "3":
                        JTextPane coursK = new JTextPane();
                        coursK.setBackground(Color.orange);
                        coursK.setBounds(231,363,149,74);
                        coursK.setText(infox);
                        coursK.setFont(font);
                        coursK.setEditable(false);
                        fenetre.add(coursK);
                        break;
                    case "4":
                        JTextPane coursL = new JTextPane();
                        coursL.setBackground(Color.orange);
                        coursL.setBounds(231,451,149,74);
                        coursL.setText(infox);
                        coursL.setFont(font);
                        coursL.setEditable(false);
                        fenetre.add(coursL);
                        break;
                    case "5":
                        JTextPane coursM = new JTextPane();
                        coursM.setBackground(Color.orange);
                        coursM.setBounds(231,538,149,74);
                        coursM.setText(infox);
                        coursM.setFont(font);
                        coursM.setEditable(false);
                        fenetre.add(coursM);
                        break;
                    case "6":
                        JTextPane coursN = new JTextPane();
                        coursN.setBackground(Color.orange);
                        coursN.setBounds(231,626,149,74);
                        coursN.setText(infox);
                        coursN.setFont(font);
                        coursN.setEditable(false);
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
                        coursO.setText(infox);
                        coursO.setFont(font);
                        coursO.setEditable(false);
                        fenetre.add(coursO);
                        break;
                    case "1":
                        JTextPane coursP = new JTextPane();
                        coursP.setBackground(Color.orange);
                        coursP.setBounds(381,188,149,74);
                        coursP.setText(infox);
                        coursP.setFont(font);
                        coursP.setEditable(false);
                        fenetre.add(coursP);
                        break;
                    case "2":
                        JTextPane coursQ = new JTextPane();
                        coursQ.setBackground(Color.orange);
                        coursQ.setBounds(381,276,149,74);
                        coursQ.setText(infox);
                        coursQ.setFont(font);
                        coursQ.setEditable(false);
                        fenetre.add(coursQ);
                        break;
                    case "3":
                        JTextPane coursR = new JTextPane();
                        coursR.setBackground(Color.orange);
                        coursR.setBounds(381,363,149,74);
                        coursR.setText(infox);
                        coursR.setFont(font);
                        coursR.setEditable(false);
                        fenetre.add(coursR);
                        break;
                    case "4":
                        JTextPane coursS = new JTextPane();
                        coursS.setBackground(Color.orange);
                        coursS.setBounds(381,451,149,74);
                        coursS.setText(infox);
                        coursS.setFont(font);
                        coursS.setEditable(false);
                        fenetre.add(coursS);
                        break;
                    case "5":
                        JTextPane coursT = new JTextPane();
                        coursT.setBackground(Color.orange);
                        coursT.setBounds(381,538,149,74);
                        coursT.setText(infox);
                        coursT.setFont(font);
                        coursT.setEditable(false);
                        fenetre.add(coursT);
                        break;
                    case "6":
                        JTextPane coursU = new JTextPane();
                        coursU.setBackground(Color.orange);
                        coursU.setBounds(381,626,149,74);
                        coursU.setText(infox);
                        coursU.setFont(font);
                        coursU.setEditable(false);
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
                                coursV.setText(infox);
                                coursV.setFont(font);
                                coursV.setEditable(false);
                                fenetre.add(coursV);
                            break;
                            case "1":
                                JTextPane coursW = new JTextPane();
                                coursW.setBackground(Color.orange);
                                coursW.setBounds(531,188,149,74);
                                coursW.setText(infox);
                                coursW.setFont(font);
                                coursW.setEditable(false);
                                fenetre.add(coursW);

                            break;
                            case "2":
                                JTextPane coursX = new JTextPane();
                                coursX.setBackground(Color.orange);
                                coursX.setBounds(531,276,149,74);
                                coursX.setText(infox);
                                coursX.setFont(font);
                                coursX.setEditable(false);
                                fenetre.add(coursX);
                            break;
                            case "3":
                                JTextPane coursX1 = new JTextPane();
                                coursX1.setBackground(Color.orange);
                                coursX1.setBounds(531,363,149,74);
                                coursX1.setText(infox);
                                coursX1.setFont(font);
                                coursX1.setEditable(false);
                                fenetre.add(coursX1);
                            break;
                            case "4":
                                JTextPane coursY = new JTextPane();
                                coursY.setBackground(Color.orange);
                                coursY.setBounds(531,451,149,74);
                                coursY.setText(infox);
                                coursY.setFont(font);
                                coursY.setEditable(false);
                                fenetre.add(coursY);
                            break;
                            case "5":
                                JTextPane coursZ = new JTextPane();
                                coursZ.setBackground(Color.orange);
                                coursZ.setBounds(531,538,149,74);
                                coursZ.setText(infox);
                                coursZ.setFont(font);
                                coursZ.setEditable(false);
                                fenetre.add(coursZ);
                            break;
                            case "6":
                                JTextPane cours1A = new JTextPane();
                                cours1A.setBackground(Color.orange);
                                cours1A.setBounds(531,626,149,74);
                                cours1A.setText(infox);
                                cours1A.setFont(font);
                                cours1A.setEditable(false);
                                fenetre.add(cours1A);
                            break;
                            default:
                            break;
                        } break;
                        
                    case "4":
                         if(null != iterator.get(3))switch (iterator.get(3)) {
                    case "0":
                        JTextPane cours1B = new JTextPane();
                        cours1B.setBackground(Color.orange);
                        cours1B.setBounds(681,101,149,74);
                        cours1B.setText(infox);
                        cours1B.setFont(font);
                        cours1B.setEditable(false);
                        fenetre.add(cours1B);
                        break;
                    case "1":
                        JTextPane cours1C = new JTextPane();
                        cours1C.setBackground(Color.orange);
                        cours1C.setBounds(681,188,149,74);
                        cours1C.setText(infox);
                        cours1C.setFont(font);
                        cours1C.setEditable(false);
                        fenetre.add(cours1C);
                        break;
                    case "2":
                        JTextPane cours1D = new JTextPane();
                        cours1D.setBackground(Color.orange);
                        cours1D.setBounds(681,276,149,74);
                        cours1D.setText(infox);
                        cours1D.setFont(font);
                        cours1D.setEditable(false);
                        fenetre.add(cours1D);
                        break;
                    case "3":
                        JTextPane cours1E = new JTextPane();
                        cours1E.setBackground(Color.orange);
                        cours1E.setBounds(681,363,149,74);
                        cours1E.setText(infox);
                        cours1E.setFont(font);
                        cours1E.setEditable(false);
                        fenetre.add(cours1E);
                        break;
                    case "4":
                        JTextPane cours1F = new JTextPane();
                        cours1F.setBackground(Color.orange);
                        cours1F.setBounds(681,451,149,74);
                        cours1F.setText(infox);
                        cours1F.setFont(font);
                        cours1F.setEditable(false);
                        fenetre.add(cours1F);
                        break;
                    case "5":
                        JTextPane cours1G = new JTextPane();
                        cours1G.setBackground(Color.orange);
                        cours1G.setBounds(681,538,149,74);
                        cours1G.setText(infox);
                        cours1G.setFont(font);
                        cours1G.setEditable(false);
                        fenetre.add(cours1G);
                        break;
                    case "6":
                        JTextPane cours1H = new JTextPane();
                        cours1H.setBackground(Color.orange);
                        cours1H.setBounds(681,626,149,74);
                        cours1H.setText(infox);
                        cours1H.setFont(font);
                        cours1H.setEditable(false);
                        fenetre.add(cours1H);
                        break;
                }  break;
                    default:
                        break;
                }
                if("5".equals(iterator.get(2))){
                    if(null != iterator.get(3))switch (iterator.get(3)) {
                        case "0":
                            JTextPane cours1I = new JTextPane();
                            cours1I.setBackground(Color.orange);
                            cours1I.setBounds(831,101,149,74);
                            cours1I.setText(infox);
                            cours1I.setFont(font);
                            cours1I.setEditable(false);
                            fenetre.add(cours1I);
                            break;
                        case "1":{
                            JTextPane cours1J = new JTextPane();
                            cours1J.setBackground(Color.orange);
                            cours1J.setBounds(831,188,149,74);
                            cours1J.setText(infox);
                            cours1J.setFont(font);
                            cours1J.setEditable(false);
                            fenetre.add(cours1J);
                                break;
                            }
                        case "2":{
                            JTextPane cours1K = new JTextPane();
                            cours1K.setBackground(Color.orange);
                            cours1K.setBounds(831,276,149,74);
                            cours1K.setText(infox);
                            cours1K.setFont(font);
                            cours1K.setEditable(false);
                            fenetre.add(cours1K);
                                break;
                            }
                        case "3":
                            JTextPane cours1L = new JTextPane();
                            cours1L.setBackground(Color.orange);
                            cours1L.setBounds(831,363,149,74);
                            cours1L.setText(infox);
                            cours1L.setFont(font);
                            cours1L.setEditable(false);
                            fenetre.add(cours1L);
                            break;
                        case "4":
                            JTextPane cours1M = new JTextPane();
                            cours1M.setBackground(Color.orange);
                            cours1M.setBounds(831,451,149,74);
                            cours1M.setText(infox);
                            cours1M.setFont(font);
                            cours1M.setEditable(false);
                            fenetre.add(cours1M);
                            break;
                        case "5":
                            JTextPane cours1N = new JTextPane();
                            cours1N.setBackground(Color.orange);
                            cours1N.setBounds(831,538,149,74);
                            cours1N.setText(infox);
                            cours1N.setFont(font);
                            cours1N.setEditable(false);
                            fenetre.add(cours1N);
                            break;
                        case "6":
                            JTextPane cours1O = new JTextPane();
                            cours1O.setBackground(Color.orange);
                            cours1O.setBounds(831,626,149,74);
                            cours1O.setText(infox);
                            cours1O.setFont(font);
                            cours1O.setEditable(false);
                            fenetre.add(cours1O);
                        
                            break;
                        default:
                            break;
                    }
            }
        }
  
        }else{
            JOptionPane.showMessageDialog(null,result.get(0).get(0));
        }
        
        fenetre.add(grille);
    }
}
