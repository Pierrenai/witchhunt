package com.utt.witchhunt.Vue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.utt.witchhunt.Controleur.Controleur;
import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.CharacterType;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;
import com.utt.witchhunt.Modele.VirtualPlayer;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import javax.swing.SwingConstants;

/**
 * 
 * L'interface graphique composée d'une JFrame et de JPanels
 * 
 * @author Pierre et Maud
 *
 */
public class InterfaceGraphique extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPaneStart;
	private static JPanel contentPaneCard;
	private static JPanel contentPanePlayStep;
	private static JPanel contentPaneAccuse;
	private static JPanel contentPaneEtreAccuse;	
	private static JPanel contentPaneChoixJoueur;	
	private static JPanel contentPaneAfficherCartes;
	private static JPanel contentPaneVolerCarte;
	private static JPanel contentPaneRegardeID;
	private static JPanel contentPaneEndGame;
	private static JPanel contentPaneReveler;
	private static JPanel contentPaneRevelerID;
	private static JPanel contentPaneMontrerID;
	private static JPanel contentPaneFinInquisition;
	private static JPanel contentPaneAfficherCartesWitch;
	private Controleur controleur;
	private JTextField txtNbRealPlayer;
	private JTextField txtNbRobotPlayer;
	private static JTextField txtname1;
	private static JTextField txtname2;
	private static JTextField txtname3;
	private static JTextField txtname4;
	private static JTextField txtname5;
	private static JTextField txtname6;
	private int j = 250;
	private int m=200;
	private JLabel lblNewLabel_6;
	
	
	/**
	 * Create the frame.
	 * @param controleur 
	 */

	
	public InterfaceGraphique(Controleur controleur) {
		this.controleur=controleur;
		initialize();
		contentPaneStart.setVisible(true);
	}


	/**
	 * Methode de fin de partie appelée si un joueur dépasse 5 points
	 * @param bigwinner
	 */
	
	public void end(Player bigwinner) {
		end_interface(bigwinner);
		contentPaneEndGame.setVisible(true);
	}
	
	/**
	 * Methode permettant à la classe Game de récupérer les noms des joueurs entrés par l'utilisateur
	 * @param i
	 * @return
	 */
	
	public static String getname(int i) {
		switch(i){
		   
	       case 0: 
	    	return txtname1.getText();
	   	
	       case 1:
	    	return txtname2.getText();
	       case 2:
	        return txtname3.getText();
	       case 3:
	    	   return txtname4.getText();
	       case 4:
	    	   return txtname5.getText();
	       case 5:
	    	   return txtname6.getText();
	       default:
	           break;
	   }
		return null;
			
	}
	/**
	 * Méthode permettant d'afficher les cartes du joueur et de le faire choisir entre le rôle de Witch ou de Villager
	 * @param player
	 * @param i
	 */
	
	public void opencardinterface(Player player,int i) {
		contentPaneStart.setVisible(false);
		cardinterface(player,i);
		contentPaneCard.setVisible(true);
	}
	/**
	 * Methode permettant d'afficher les cartes du joueur et de le faire choisir entre "Jouer" et "Accuser"
	 * @param player
	 */
	
	public void playstep1 (Player player) {
		if(player instanceof VirtualPlayer) {
			controleur.play(player, "accuse");
		} else {
			playstep(player);
			contentPanePlayStep.setVisible(true);
		}		
	}
	
	/**
	 * Methode permettant au joueur de choisir la personne qu'il souhaite accuser
	 * @param player
	 */
	public void clickaccuser (Player player) {
		accuser(player);
		contentPaneAccuse.setVisible(true);
	}
	
	/**
	 * Methode permettant au joueur accusé de choisir entre jouer une carte côté Witch et se révéler
	 * @param accuseur
	 * @param player
	 */
	
	public void etre_accuser (Player accuseur,Player player) {
		etre_accuse_pane(accuseur,player);
		contentPaneEtreAccuse.setVisible(true);
	}

	/**
	 * Methode permettant d'afficher les cartes du joueur en lui permettant de cliquer sur celle qu'il souhaite utiliser
	 * @param playablecards
	 */
	
	public void affichercartesHunt (List<Cards> playablecards) {
		afficher_cartes_interface(playablecards);
		contentPaneAfficherCartes.setVisible(true);
	}
	
	/**
	 * Methode permettant au joueur de choisir le joueur suivant
	 * @param player
	 * @param card
	 */
	
	public void choix_joueur (Player player, Cards card) {
		choix_joueur_interface (player,card);
		contentPaneChoixJoueur.setVisible(true);
	}
	
	/**
	 * Methode permettant au joueur de choisir le joueur dont il souhaite réveler l'identité
	 * @param player
	 * @param card
	 */
	public void choix_joueur_reveler_id (Player player, Cards card) {
		choix_joueur_reveler(player, card);
		contentPaneRevelerID.setVisible(true);
	}
	/**
	 * Methode permettant d'afficher l'identite d'un joueur
	 * @param player
	 * @param card
	 */
	public void reveleridentite (Player player, Cards card) {
		montrer_ID(player,card);
		contentPaneMontrerID.setVisible(true);
	}
	
	/**
	 * Methode permettant à un joueur de regarder secretement l'identite d'un autre joueur (fin carte "The Inquisition")
	 * @param player
	 * @param nextPlayer
	 * @param card
	 */
	
	public void regarder_identite(Player player, Player nextPlayer,Cards card) {
		regarder_identite_interface (player,nextPlayer,card);
		contentPaneRegardeID.setVisible(true);
	}
	
	/**
	 * Methode permettant à un jour de prendre une carte révélée de l'un des joueurs (carte Pet Newi)
	 * @param player
	 * @param cardlists
	 */
	
	public void voler_une_carte (Player player,List<Cards> cardlists, Cards card) {
		voler_une_carte_interface(player,cardlists,card);
		contentPaneVolerCarte.setVisible(true);
	}
	
	/**
	 * Methode permettant au joueur de choisir la carte qu'il souhaite jouer pour se défendre suite à une accusation (Witch)
	 * @param playablecards
	 * @param player
	 */
	
	
	public void affichercarteswitch(List<Cards> playablecards,Player player) {
		afficher_cartes_witch_interface(playablecards,player);
		contentPaneAfficherCartesWitch.setVisible(true);
	}

	
				
	private void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1016, 646);
		contentPaneStart = new JPanel();
		contentPaneStart.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneStart);
		contentPaneStart.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Real player (np) =");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(27, 105, 111, 16);
		contentPaneStart.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Robot player (nbot) =");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(27, 137, 142, 16);
		contentPaneStart.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome in Witchhunt");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_2.setBounds(27, 43, 523, 34);
		contentPaneStart.add(lblNewLabel_2);
		
		txtNbRealPlayer = new JTextField();
		txtNbRealPlayer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNbRealPlayer.setBounds(148, 103, 96, 19);
		contentPaneStart.add(txtNbRealPlayer);
		txtNbRealPlayer.setColumns(10);
		
		txtNbRobotPlayer = new JTextField();
		txtNbRobotPlayer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNbRobotPlayer.setBounds(158, 137, 96, 19);
		contentPaneStart.add(txtNbRobotPlayer);
		txtNbRobotPlayer.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.createGame(Integer.parseInt(txtNbRealPlayer.getText()),Integer.parseInt(txtNbRobotPlayer.getText()));
								
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(27, 178, 85, 21);
		contentPaneStart.add(btnNewButton_1);
		
		txtname1 = new JTextField();
		txtname1.setBounds(102, 215, 96, 19);
		contentPaneStart.add(txtname1);
		txtname1.setColumns(10);
		
		txtname2 = new JTextField();
		txtname2.setBounds(102, 245, 96, 19);
		contentPaneStart.add(txtname2);
		txtname2.setColumns(10);
		
		txtname3 = new JTextField();
		txtname3.setBounds(102, 275, 96, 19);
		contentPaneStart.add(txtname3);
		txtname3.setColumns(10);
		
		txtname4 = new JTextField();
		txtname4.setBounds(102, 305, 96, 19);
		contentPaneStart.add(txtname4);
		txtname4.setColumns(10);
		
		txtname5 = new JTextField();
		txtname5.setBounds(102, 335, 96, 19);
		contentPaneStart.add(txtname5);
		txtname5.setColumns(10);
		
		txtname6 = new JTextField();
		txtname6.setBounds(102, 365, 96, 19);
		contentPaneStart.add(txtname6);
		txtname6.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Player's name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 216, 82, 16);
		contentPaneStart.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("maximum 6 player");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(101, 396, 128, 16);
		contentPaneStart.add(lblNewLabel_4);
				
	}
	
	private void cardinterface (Player player, int k) {
		
		
		contentPaneCard = new JPanel();
		contentPaneCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCard);
		contentPaneCard.setLayout(null);
		
		selectiden(player,k);
		
	}
	
	
	private void selectiden(Player player,int k) {
		
		ArrayList <Cards>cards = player.getCards();
		for(int i=0; i < cards.size(); i++) {
			Cards card = cards.get(i);
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			lblNewLabel.setIcon(card.getImg());
			lblNewLabel.setBounds(35+i*j, 60, 218, 269);
			contentPaneCard.add(lblNewLabel);
			
			JLabel lblNewLabel_5 = new JLabel(player+" : Here is your cards, please choose between Witch or Villager");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_5.setForeground(Color.BLACK);
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
			lblNewLabel_5.setBounds(210, 25, 651, 34);
			contentPaneCard.add(lblNewLabel_5);
		}
		
		
		JButton btnNewButton = new JButton("WITCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.setIdentity(CharacterType.WITCH);
				contentPaneCard.setVisible(false);
				controleur.selectid(k);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(259, 400, 123, 45);
		contentPaneCard.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("VILLAGER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.setIdentity(CharacterType.VILLAGER);
				contentPaneCard.setVisible(false);
				controleur.selectid(k);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(546, 400, 123, 45);
		contentPaneCard.add(btnNewButton_1);		
			
	}
	
	private void playstep (Player player) {
		
		contentPanePlayStep = new JPanel();
		contentPanePlayStep.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePlayStep);
		contentPanePlayStep.setLayout(null);
		ArrayList <Cards>cards = player.getCards();
		for(int i=0; i < cards.size(); i++) {
			Cards card = cards.get(i);
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));			
			lblNewLabel.setIcon(card.getImg());
			lblNewLabel.setBounds(35+i*j, 90, 218, 269);
			contentPanePlayStep.add(lblNewLabel);

		}
		JLabel lblNewLabel_5 = new JLabel(player+" : Do you want to play a card or accuse another player ?");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPanePlayStep.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("PLAY");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanePlayStep.setVisible(false);
				controleur.play(player, "play");				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(259, 400, 123, 45);
		contentPanePlayStep.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ACCUSE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanePlayStep.setVisible(false);
				controleur.play(player, "accuse");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(546, 400, 123, 45);
		contentPanePlayStep.add(btnNewButton_1);
	}
	
	private void accuser(Player player) {
		
		contentPaneAccuse = new JPanel();
		contentPaneAccuse.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAccuse);
		contentPaneAccuse.setLayout(null);
		ArrayList <Player> playerNotReveal = (ArrayList<Player>) Game.playerlistnotreveal(player);
		for(int i=0;i<playerNotReveal.size();i++) {
			JButton btnNewButton_2 = new JButton(String.valueOf(playerNotReveal.get(i)));
			int placejoueur=i;
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneAccuse.setVisible(false);
					if(playerNotReveal.get(placejoueur) instanceof VirtualPlayer){
						reveler(player,playerNotReveal.get(placejoueur));
					}else {
						etre_accuser(player,playerNotReveal.get(placejoueur));
					}
				}
			});
			btnNewButton_2.setBounds(90+i*m, 150, 104, 34);
			contentPaneAccuse.add(btnNewButton_2);			
		}
		JLabel lblNewLabel_5 = new JLabel(player+" : Who do you want to accuse ?");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPaneAccuse.add(lblNewLabel_5);
		
	}
	
	private void etre_accuse_pane (Player accuseur,Player player) {
		contentPaneEtreAccuse = new JPanel();
		contentPaneEtreAccuse.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneEtreAccuse);
		contentPaneEtreAccuse.setLayout(null);
		ArrayList <Cards>cards = player.getCards();
		for(int i=0; i < cards.size(); i++) {
			Cards card = cards.get(i);
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));			
			lblNewLabel.setIcon(card.getImg());
			lblNewLabel.setBounds(35+i*j, 90, 218, 269);
			contentPaneEtreAccuse.add(lblNewLabel);

		}
		JLabel lblNewLabel_5 = new JLabel(player+" : "+accuseur+" accused you, do you want to reveal or to play a card in Witch ?");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPaneEtreAccuse.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("PLAY A CARD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPaneEtreAccuse.setVisible(false);
				controleur.jouer_cartes_witch(player);			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(259, 400, 200, 45);
		contentPaneEtreAccuse.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("REVEAL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPaneEtreAccuse.setVisible(false);
				reveler(accuseur,player);
				contentPaneReveler.setVisible(true);

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(600, 400, 123, 45);
		contentPaneEtreAccuse.add(btnNewButton_1);
		
		lblNewLabel_6 = new JLabel("WARNING : Witch effect side of the card");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(27, 518, 275, 26);
		contentPaneEtreAccuse.add(lblNewLabel_6);
		
	}
	
	private void reveler (Player accuseur, Player player) {
		
		contentPaneReveler = new JPanel();
		contentPaneReveler.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneReveler);
		contentPaneReveler.setLayout(null);		
		
		if(player.getIdentity()==CharacterType.VILLAGER) {
			JLabel lblNewLabel_5 = new JLabel(player+" is a VILLAGER");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_5.setForeground(Color.BLACK);
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
			lblNewLabel_5.setBounds(210, 25, 651, 34);
			contentPaneReveler.add(lblNewLabel_5);
			
			JButton btnNewButton1 = new JButton("Next");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneReveler.setVisible(false);
					controleur.fin_reveler(accuseur,player);																	
				}
			});
			btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton1.setBounds(800, 400,100 ,50);
			contentPaneReveler.add(btnNewButton1);
		} 
		else {
			JLabel lblNewLabel_5 = new JLabel(player+" is a WITCH");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_5.setForeground(Color.BLACK);
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
			lblNewLabel_5.setBounds(210, 25, 651, 34);
			contentPaneReveler.add(lblNewLabel_5);
			
			JButton btnNewButton1 = new JButton("Next");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneReveler.setVisible(false);
					controleur.fin_reveler(accuseur,player);																	
				}
			});
			btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton1.setBounds(800, 400,100 ,50);
			contentPaneReveler.add(btnNewButton1);
		}
	}
	
	private void afficher_cartes_interface (List<Cards> playablecards) {
		
		contentPaneAfficherCartes = new JPanel();
		contentPaneAfficherCartes.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAfficherCartes);
		contentPaneAfficherCartes.setLayout(null);
		//List <Cards>cards = playablecards;
		for(int i=0; i < playablecards.size(); i++) {
			Cards card = playablecards.get(i);
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));			
			lblNewLabel.setIcon(card.getImg());
			lblNewLabel.setBounds(35+i*j, 90, 218, 269);
			contentPaneAfficherCartes.add(lblNewLabel);
			
			JButton btnNewButton = new JButton();
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneAfficherCartes.setVisible(false);
					controleur.joue_carte_hunt(card);
							
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNewButton.setBounds(35+i*j, 400, 200, 45);
			contentPaneAfficherCartes.add(btnNewButton);

		}
		JLabel lblNewLabel_5 = new JLabel("Here is your cards : Click on the one you want to play ");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPaneAfficherCartes.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("WARNING : Hunt effect side of the card");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(27, 518, 275, 26);
		contentPaneAfficherCartes.add(lblNewLabel_6);
		
	}
	
	private void choix_joueur_interface (Player player, Cards card) {
		
		contentPaneChoixJoueur = new JPanel();
		contentPaneChoixJoueur.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneChoixJoueur);
		contentPaneChoixJoueur.setLayout(null);
		List<Player> playerlist = Game.playerlistreveal(player);
		for(int i=0;i<playerlist.size();i++) {
			JButton btnNewButton_2 = new JButton(String.valueOf(playerlist.get(i)));
			int placejoueur=i;
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneChoixJoueur.setVisible(false);
					controleur.effethuntavecchoixjoueur(playerlist.get(placejoueur),card);					
				}
			});
			btnNewButton_2.setBounds(90+i*m, 150, 104, 34);
			contentPaneChoixJoueur.add(btnNewButton_2);			
		}
		JLabel lblNewLabel_5 = new JLabel(player+" : Who do you want to choose as next player ?");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPaneChoixJoueur.add(lblNewLabel_5);
		
	}
	
	private void voler_une_carte_interface(Player player, List<Cards>cardlist, Cards card1) {
		
		contentPaneVolerCarte = new JPanel();
		contentPaneVolerCarte.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneVolerCarte);
		contentPaneVolerCarte.setLayout(null);
		for(int i=0; i < cardlist.size(); i++) {
			Cards card = cardlist.get(i);
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));			
			lblNewLabel.setIcon(card.getImg());
			lblNewLabel.setBounds(35+i*j, 90, 218, 269);
			contentPaneVolerCarte.add(lblNewLabel);
			
			JButton btnNewButton = new JButton();
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneVolerCarte.setVisible(false);
					controleur.ajouter_carte_joueur(player,card,card1);
															
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNewButton.setBounds(35+i*j, 400, 200, 45);
			contentPaneVolerCarte.add(btnNewButton);

		}
		JLabel lblNewLabel_5 = new JLabel("Here is the cards that you can steal : Click on the one you choose ");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPaneVolerCarte.add(lblNewLabel_5);
	}
	
	private void regarder_identite_interface (Player player, Player nextPlayer, Cards card) {
		
		contentPaneRegardeID = new JPanel();
		contentPaneRegardeID.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneRegardeID);
		contentPaneRegardeID.setLayout(null);		
		
		JLabel lblNewLabel_5 = new JLabel(nextPlayer+" : Look secretely at the identity of : "+player+" by clicking on Show");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPaneRegardeID.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Show");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPaneRegardeID.setVisible(false);
				fin_Inquisition(player,card);
				contentPaneFinInquisition.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(350, 400, 200, 100);
		contentPaneRegardeID.add(btnNewButton);
		
	}
	
	private void fin_Inquisition (Player player, Cards card) {
		
		contentPaneFinInquisition = new JPanel();
		contentPaneFinInquisition.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneFinInquisition);
		contentPaneFinInquisition.setLayout(null);	
		
		if(player.getIdentity()==CharacterType.VILLAGER) {
			JButton btnNewButton = new JButton(player+" is VILLAGER ");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNewButton.setBounds(350, 400, 200, 100);
			contentPaneFinInquisition.add(btnNewButton);	
			
			JButton btnNewButton1 = new JButton("Next");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneFinInquisition.setVisible(false);
					controleur.fin_cartes(player,card);																	
				}
			});
			btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton1.setBounds(800, 200,100 ,50);
			contentPaneFinInquisition.add(btnNewButton1);
			
		} 
		else {
			JButton btnNewButton = new JButton(player+" is WITCH ");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNewButton.setBounds(350, 400, 200, 100);
			contentPaneFinInquisition.add(btnNewButton);	
			
			JButton btnNewButton1 = new JButton("Next");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneFinInquisition.setVisible(false);
					controleur.fin_cartes(player,card);																
				}
			});
			btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton1.setBounds(800, 200,100 ,50);
			contentPaneFinInquisition.add(btnNewButton1);
			
		}
		
	}
	
	private void choix_joueur_reveler (Player player, Cards card) {
		
		contentPaneRevelerID = new JPanel();
		contentPaneRevelerID.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneRevelerID);
		contentPaneRevelerID.setLayout(null);	
		
		contentPaneRevelerID = new JPanel();
		contentPaneRevelerID.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneRevelerID);
		contentPaneRevelerID.setLayout(null);
		List<Player> playerlist = Game.playerlistreveal(player);
		for(int i=0;i<playerlist.size();i++) {
			JButton btnNewButton_2 = new JButton(String.valueOf(playerlist.get(i)));
			int placejoueur=i;
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneRevelerID.setVisible(false);
					controleur.effethuntavecchoixjoueur(playerlist.get(placejoueur),card);					
				}
			});
			btnNewButton_2.setBounds(90+i*m, 150, 104, 34);
			contentPaneRevelerID.add(btnNewButton_2);			
		}
		JLabel lblNewLabel_5 = new JLabel(player+" : Who do you want to reveal ?");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPaneRevelerID.add(lblNewLabel_5);
		
	}
	
	private void montrer_ID (Player player, Cards card) {
		
		contentPaneMontrerID = new JPanel();
		contentPaneMontrerID.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMontrerID);
		contentPaneMontrerID.setLayout(null);		
		
		if(player.getIdentity()==CharacterType.VILLAGER) {
			JLabel lblNewLabel_5 = new JLabel(player+" is a VILLAGER");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_5.setForeground(Color.BLACK);
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
			lblNewLabel_5.setBounds(210, 25, 651, 34);
			contentPaneMontrerID.add(lblNewLabel_5);
			
			JButton btnNewButton1 = new JButton("Next");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneMontrerID.setVisible(false);
					choix_joueur_interface(player, card);
																					
				}
			});
			btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton1.setBounds(800, 400,100 ,50);
			contentPaneMontrerID.add(btnNewButton1);
		} 
		else {
			JLabel lblNewLabel_5 = new JLabel(player+" is a WITCH");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_5.setForeground(Color.BLACK);
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
			lblNewLabel_5.setBounds(210, 25, 651, 34);
			contentPaneMontrerID.add(lblNewLabel_5);
			
			JButton btnNewButton1 = new JButton("Next");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneMontrerID.setVisible(false);
					controleur.fin_cartes(player, card);																					
				}
			});
			btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton1.setBounds(800, 400,100 ,50);
			contentPaneMontrerID.add(btnNewButton1);
		}
	}

	private void afficher_cartes_witch_interface (List<Cards> playablecards, Player player) {
		
		contentPaneAfficherCartesWitch = new JPanel();
		contentPaneAfficherCartesWitch.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAfficherCartesWitch);
		contentPaneAfficherCartesWitch.setLayout(null);
		for(int i=0; i < playablecards.size(); i++) {
			Cards card = playablecards.get(i);
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));			
			lblNewLabel.setIcon(card.getImg());
			lblNewLabel.setBounds(35+i*j, 90, 218, 269);
			contentPaneAfficherCartesWitch.add(lblNewLabel);
			
			JButton btnNewButton = new JButton();
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPaneAfficherCartesWitch.setVisible(false);
					controleur.jouercarteswitch(card,player);
							
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNewButton.setBounds(35+i*j, 400, 200, 45);
			contentPaneAfficherCartesWitch.add(btnNewButton);

		}
		JLabel lblNewLabel_5 = new JLabel("Here is your cards : Click on the one you want to play ");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_5.setBounds(210, 25, 651, 34);
		contentPaneAfficherCartesWitch.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("WARNING : Hunt effect side of the card");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(27, 518, 275, 26);
		contentPaneAfficherCartesWitch.add(lblNewLabel_6);
		
	}
	
	private void end_interface(Player bigwinner) {
		
		contentPaneEndGame = new JPanel();
		contentPaneEndGame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneEndGame);
		contentPaneEndGame.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel(bigwinner+ " WON THE GAME !!");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_2.setBounds(250, 300, 523, 34);
		contentPaneEndGame.add(lblNewLabel_2);	
		
	}
}


	
	

			
	


	

