package com.utt.witchhunt.Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.Vue.InterfaceGraphique;
import com.utt.witchhunt.player.IA.AccuseetRevele;
import com.utt.witchhunt.player.IA.Strategy;


/**
 * 
 * L'instanciation du jeu
 * 
 * @author Pierre et Maud
 *
 */
public class Game {
	private static int nplayer = 0;
	private static int nbot = 0;
	private static final List<Player> playerlist = new ArrayList<Player>();
	private static List<Cards> cardslist = new ArrayList<Cards>();
	private static Player nextPlayer = null;
	

	private final static Game instance = new Game();
	
	public final static Game getInstance() { 
		if(instance==null) new Game();
		return instance; }
	
	private Game() {}
	
	/**
	 * Permet de lancer la partie
	 * 
	 * @param np
	 * 
	 * Nombre de joueur
	 */
	public void startGame(int np, int nb) {
		nplayer = np;
		nbot = nb;
	    
	    //Ici on créer les joueurs
	    createPlayers();
	    
	    //Ici on commence un round
	    nextRound();
	}
	
	public static void endTurn () {
		//
	}
	
	/**
	 * Méthode permettant de créer les joueurs de manière automatique
	 * 
	 */
	public static void createPlayers(){
		if(playerlist.isEmpty()) {
			for(int i=0; i < nplayer; i++) {
				
		        //Ici on créer le joueur réel
				Player player = new RealPlayer(InterfaceGraphique.getname(i));
				playerlist.add(player);
				
			
			}
			
			for(int i=0; i < nbot; i++) {
		        //Ici on créer le joueur virtuel
				Strategy nul = new AccuseetRevele();
				Player player = new VirtualPlayer("Bot n°" + i, nul);
				playerlist.add(player);
				player.selectIdentity();
				
			
			}
		}
	}
	
	/**
	 * Méthode permettant de récuperer les cartes de la défausse
	 * 
	 * @return
	 * La liste des cartes défaussés
	 */
	public static List<Cards> getdiscardedcardlist(){
		return cardslist;
	}
	/**
	 * Méthode permettant d'ajouter une cartes à la défausse
	 * 
	 * @param c
	 * La carte à défausser
	 */
	public static void adddiscardedCard(Cards c) {
		cardslist.add(c);
	}
	
	/**
	 * Distribue les cartes aux joueurs
	 * 
	 * <pre>
	 * Attention à bien créer les joueurs avant de distribuer les cartes
	 * </pre> 
	 */
	public static void distributeCards() {
		cardslist = Cards.getcardslist();
		
		Random rand = new Random();
		int nc = 0;
		
		//Ici je set le nombre de cartes par joueurs
		if(nplayer+nbot==3) nc=4;
		if(nplayer+nbot==4) nc=3;
		if(nplayer+nbot==5) nc=2;
		if(nplayer+nbot==6) nc=2;
		
		//Ici on créer le nombre de joueur défini avant
		for(int i=0; i < nplayer; i++) {
			Player player = playerlist.get(i);
			
			//Ici distibuer les cartes de maniere aléatoire
			for(int ii=0; ii < nc; ii++) {
				int randomIndex = rand.nextInt(cardslist.size());
		        Cards randomElement = cardslist.get(randomIndex);
		        cardslist.remove(randomIndex);
		        
		        player.addCard(randomElement);
			}
		}
		
		
		//Ici je list les cartes de la défausse
		System.out.println("La défausse possèdent " +  cardslist);
		
	}
	
	/**
	 * Permet de définir le prochain joueur
	 * 
	 * @param p
	 * Prochain joueur
	 */
	public static void setnextPlayer(Player p) {
		nextPlayer = p;
		System.out.println(nextPlayer + " is nextPlayer");
	}
	
	
	public static void nextRound() {
		//On supprimer toutes les cartes
		removeAllCards();
		//On distribu les cartes au début du round
		distributeCards();
		
	}
	
		
	public static void removeAllCards() {  
		for (int i = 0; i < playerlist.size(); i++) {
			playerlist.get(i).clearCards();
		}
		cardslist.clear();
	}
	
	public static List<Player> getplayerlist(){
		return playerlist;
	}
	
	/**
	 * Méthode permettant de récuperer le joueur n-1 dans la list des joueurs. Si le joueur est le premier de la list on récuperer le dernier joueur de la list
	 * @param p
	 * Le joueur à droite du joueur à gauche
	 * @return
	 * Le joueur à gauche de p
	 */
	public static Player getleftplayer(Player p){
		Player leftplayer = null;
		for(int i=0; i < playerlist.size(); i++) {
			if(playerlist.get(i)==p) break;
			leftplayer = playerlist.get(i);
		}
		
		if(leftplayer==null) leftplayer = playerlist.get(playerlist.size()-1);
		
		return leftplayer;
	}
	/**
	 * Methode permettant de récupérer la liste des joueurs qui ne sont pas révélés
	 * @param playertoexclude
	 * @return
	 */
	
	public static List<Player> playerlistnotreveal(Player playertoexclude){
		List<Player> playerlistnotreveal = new ArrayList<Player>();
		for(int i=0; i < playerlist.size(); i++) {
			if(!playerlist.get(i).isReveal() && playerlist.get(i)!=playertoexclude) playerlistnotreveal.add(playerlist.get(i));
		}
		return playerlistnotreveal;
	}
	/**
	 * Methode permettant de récupérer une liste de joueur en excluant l'un des joueurs
	 * @param playertoexclude
	 * @return
	 */
	
	public static List<Player> playerlistreveal(Player playertoexclude){
		List<Player> playerlistreveal = new ArrayList<Player>();
		for(int i=0; i < playerlist.size(); i++) {
			if(playerlist.get(i)!=playertoexclude) playerlistreveal.add(playerlist.get(i));
		}
		return playerlistreveal;
	}
}
