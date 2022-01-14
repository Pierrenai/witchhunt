
package com.utt.witchhunt.Controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.Modele.CardType;
import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.CharacterType;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;
import com.utt.witchhunt.Modele.VirtualPlayer;
import com.utt.witchhunt.Vue.InterfaceGraphique;
/**
 * 
 * Le controleur faisant le lien entre l'interface graphique et le modele 
 * 
 * @author Pierre et Maud
 *
 */
public class Controleur {
	
	private InterfaceGraphique interfaceGraphique;
	private static Player nextPlayer = null;
	private List <Player> playerlist = Game.getplayerlist();
	private int nb;

	
	/**
	 * Constructeur de la classe controleur
	 */
	public Controleur () {
		
		interfaceGraphique = new InterfaceGraphique(this);
		interfaceGraphique.setVisible(true);		
		
	}
	
	/**
	 * Lancement de l'interface graphique 
	 * @param args
	 */
	public static void main(String[] args) {
		new Controleur();
	}
	
	
	/**
	 * Demande de la Vue pour lancer la partie
	 * @param np (nombre de joueur reels)
	 * @param nbot (nombre de joueurs virtuels)
	 */
	
	public void createGame (int np, int nbot) {
		this.nb = nbot;
		Game G = Game.getInstance();
		G.startGame(np, nbot);
		Player player = playerlist.get(0);
		interfaceGraphique.opencardinterface(player,0);
	}
		
	/**
	 * Methode permettant au joueur de selectionner son identite en debut de tour (appel à l'interface graphique pour l'affichage des cartes)
	 * @param k
	 */
	public void selectid (int k) {
		k=k+1;
		if(k==(playerlist.size())-nb) {
			nextTurn(playerlist.get(0));
		}
		else {
			Player player2 = playerlist.get(k);
			interfaceGraphique.opencardinterface(player2,k);
		}
	}
	/**
	 * Methode qui decompte les points à chaque fin de tour
	 * @param player
	 */	
	public void endTurn (Player player) {
		Player bigwinner = null;
		Player roundwinner = null;
		
		//Verifie si il reste un seul joueur non revele
		List<Player> playernotreveal = new ArrayList<Player>();
		for(int i=0; i < playerlist.size(); i++) {
			if(!playerlist.get(i).isReveal()) playernotreveal.add(playerlist.get(i));
		}
		if(playernotreveal.size()==1) roundwinner = playernotreveal.get(0);
		if(roundwinner!=null && roundwinner.getIdentity()==CharacterType.WITCH) roundwinner.addPoints(2); 
		if(roundwinner!=null && roundwinner.getIdentity()==CharacterType.VILLAGER) roundwinner.addPoints(1); 
		
		for(int i=0; i < playerlist.size(); i++) {
			if(playerlist.get(i).getPoints()>=5) {
				bigwinner = playerlist.get(i);
				break;
			}
		}
		
		if(bigwinner!=null) {
			interfaceGraphique.end(bigwinner);
		}
		else {
			if(roundwinner!=null) {
				Game.nextRound();	
				for(int i=0; i<playerlist.size();i++) {
					playerlist.get(i).setReveal(false);
				}
				interfaceGraphique.opencardinterface(player,0);				
			} else {
				nextTurn(player);
			}
		}
	}
	
	/**
	 * Enclenche un nouveau tour
	 */
	public void nextTurn(Player player) {
		nextPlayer=player;
		interfaceGraphique.playstep1(nextPlayer);
	}
	
	/**
	 * Methode permettant le debut du tour de jeu d'un joueur 
	 * @param player
	 * @param nexti
	 */
	public void play (Player player, String nexti) {
		if(nexti.matches("accuse")) {
			if (player instanceof VirtualPlayer) {
				List <Player> playerlist = Game.playerlistnotreveal(player);
				Random rand = new Random();
				int randomIndex = rand.nextInt(playerlist.size());
		        Player randomElement = playerlist.get(randomIndex);
		        interfaceGraphique.etre_accuser(player, randomElement);

				
			}else {
				interfaceGraphique.clickaccuser(player);

			}
		}
		if(nexti.matches("play")) {
			List<Cards> playablecards = player.getplayableCards(CardType.HUNT);
			if(!playablecards.isEmpty()) {
				interfaceGraphique.affichercartesHunt(playablecards);
			}
		}
		
	}

	/**
	 * Methode informant le modèle qu'un joueur est revele et attribuant le points à l'accusateur si necessaire
	 * @param accuseur
	 * @param player
	 */	
	public void fin_reveler (Player accuseur, Player player) {
		player.setReveal(true);
		if(player.getIdentity() == CharacterType.WITCH) {
			accuseur.addPoints(1);
			endTurn(accuseur);
		}
		else {
			endTurn(player);
		}
	}
	
	/**
	 * Methode appelee après le choix de la carte jouee en Hunt
	 * @param card
	 */	
	public void joue_carte_hunt (Cards card) {

		if (card.getName()== "Broomstick"|| card.getName()=="Hooked Noise"||card.getName()=="Wart"||card.getName()=="Evil Eye") {
			interfaceGraphique.choix_joueur(nextPlayer, card);
		}
		else if (card.getName()=="The Inquisition") {
			if((nextPlayer.isReveal())&& (nextPlayer.getIdentity()==CharacterType.VILLAGER)) {
				interfaceGraphique.choix_joueur(nextPlayer, card);
			}
			else {
				play (nextPlayer, "play");
			}
		}
			
		else if(card.getName()=="Angry Mob") {
			if((nextPlayer.isReveal())&& (nextPlayer.getIdentity()==CharacterType.VILLAGER)) {
				interfaceGraphique.choix_joueur_reveler_id(nextPlayer, card);
			}
			else {
				play (nextPlayer, "play");
				}
		}
		else if(card.getName()=="Cauldron"||card.getName()=="Toad") {
			interfaceGraphique.reveleridentite (nextPlayer,card);
			
		}else if(card.getName()=="Pet Newi") {
			List<Cards> stealablecards = new ArrayList<Cards>();			
			for(int i=0; i <playerlist.size(); i++) {
				for(int ii=0; ii < playerlist.get(i).getCards().size(); ii++) {
					if(playerlist.get(i).getCards().get(ii).isReveal()) {
						stealablecards.add(playerlist.get(i).getCards().get(ii));
					}
				}
			}
			if(!stealablecards.isEmpty()) {
				interfaceGraphique.voler_une_carte(nextPlayer,stealablecards,card);											
			}
			else {
				play (nextPlayer, "play");
			}
		} else if (card.getName()=="Black Cat") {
			
			List <Cards> cardlist = Game.getdiscardedcardlist();
			if(cardlist.size()==0) {
				play(nextPlayer,"play");
			}
			Random rand = new Random();
			int randomIndex = rand.nextInt(cardlist.size());
	        Cards randomElement = cardlist.get(randomIndex);
	        nextPlayer.addCard(randomElement);
	        nextPlayer.removeCard(card);
			Game.adddiscardedCard(card);
			endTurn(nextPlayer);
			
		}

	}
	
	public void effethuntavecchoixjoueur(Player player, Cards card) {
		
		if(card.getName()== "Broomstick"||card.getName()=="Wart"||card.getName()=="Pet Newi"||card.getName()=="Evil Eye") {
			nextPlayer.removeCard(card);
			card.setReveal();
			endTurn(player);
		}
		if(card.getName()=="Cauldron"||card.getName()=="Toad") {
			nextPlayer.setReveal(true);
			nextPlayer.removeCard(card);
			card.setReveal();
			if(nextPlayer.getIdentity()==CharacterType.VILLAGER) {
				endTurn(player);
			}else {
				Player player1= Game.getleftplayer(nextPlayer);
				endTurn(player1);
			}
		}
		
		if(card.getName()=="Hooked Noise") {
			List<Cards> accusecards = player.getCards();
			List<Cards> accusecardsnotreveal = new ArrayList<Cards>();
			for(int i=0; i < accusecards.size(); i++) {
				if(!accusecards.get(i).isReveal()) {
					accusecardsnotreveal.add(accusecards.get(i));
				}
			}
			if(!accusecardsnotreveal.isEmpty()) {
				Random rand = new Random();
				int randomIndex = rand.nextInt(accusecardsnotreveal.size());
		        Cards randomElement = accusecardsnotreveal.get(randomIndex);
		        
		        player.removeCard(card);
		        player.removeCard(randomElement);
		        nextPlayer.addCard(randomElement);
		        card.setReveal();
		        endTurn(player);
			}
			else {
				play (nextPlayer, "play");
			}
			
		}
		if(card.getName()=="The Inquisition") {
			interfaceGraphique.regarder_identite(player,nextPlayer,card);
		}
		if((card.getName()=="Angry Mob")){
			interfaceGraphique.reveleridentite (player,card);			
		}
				
		
	}
	
	/**
	 * Methode permettant d'ajouter une carte revelee dans le jeu d'un joueur 
	 * @param player
	 * @param stealedcard
	 */
	public void ajouter_carte_joueur (Player player, Cards stealedcard, Cards card) {
		nextPlayer.addCard(stealedcard);
		for(int i=0; i <playerlist.size(); i++) {
			for(int ii=0; ii < playerlist.get(i).getCards().size(); ii++) {
				if(playerlist.get(i).getCards().get(ii).isReveal()) {
					if(stealedcard== playerlist.get(i).getCards().get(ii)) {
						playerlist.get(i).removeCard(stealedcard);						
					}					
				}
			}
		}
		interfaceGraphique.choix_joueur(nextPlayer, card);		
	}
	
	/**
	 * Methode de fin de cartes
	 * @param player
	 * @param card
	 */
	public void fin_cartes (Player player, Cards card) {
		if(card.getName()=="Angry Mob") {
			nextPlayer.removeCard(card);
			card.setReveal();
			if (player.getIdentity()==CharacterType.VILLAGER) {
				nextPlayer.removePoints(2);
				endTurn(player);
			}
			else {
				nextPlayer.addPoints(2);
				endTurn(nextPlayer);
			}			
		}
		if(card.getName()=="Cauldron"||card.getName()=="Toad") {
			player.removeCard(card);
			card.setReveal();
			if (player.getIdentity()==CharacterType.WITCH) {
				endTurn(Game.getleftplayer(player));
			}
			else {
				
				interfaceGraphique.choix_joueur(player, card);
			}
		}
		if (card.getName()=="The Inquisition") {
			nextPlayer.removeCard(card);
			card.setReveal();
			endTurn(player);
		}
	}

	
	/**
	 * Methode appelee lorsque le joueur decide de jouer une carte en Witch au lieu de se reveler
	 * @param player
	 */	
	public void jouer_cartes_witch (Player player) {
		List<Cards> playablecards = player.getplayableCards(CardType.WITCH);
		if(!playablecards.isEmpty()) {
			interfaceGraphique.affichercarteswitch(playablecards,player);
		}//ELSE ??
		
	}
	
	/**
	 * Methode appelee lorsque le joueur a choisi la carte qu'il souhaite jouer en Witch
	 * @param card
	 * @param player
	 */	
	public void jouercarteswitch(Cards card,Player player) {
		if(card.getName()== "Pet Newi"||card.getName()=="Toad"||card.getName()=="Black Cat"||card.getName()=="Angry Mob"||card.getName()=="Wart"||card.getName()== "Broomstick") {
			player.removeCard(card);
			card.setReveal();
			endTurn(player);
		}
	}
	
	

	

}


