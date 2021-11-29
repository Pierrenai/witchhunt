package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * 
 * Là où tout se passe
 * 
 * @author Pierre et Maud
 *
 */
public class Game {
	private static int nplayer = 0;
	private static List<Player> playerlist = new ArrayList<Player>();
	private static List<Cards> cardslist = new ArrayList<Cards>();
	private static Player nextPlayer = null;
	
	private final static Game instance = new Game();
	
	public final static Game getInstance() { 
		if(instance==null) new Game();
		return instance; }
	
	private Game() {
	}
	
	/**
	 * Permet de lancer la partie
	 * 
	 * @param np
	 * 
	 * Nombre de joueur
	 */
	public void startGame(int np) {
		nplayer = np;
	    
	    //Ici on créer les joueurs
	    createPlayers();
	    
	    //Ici on commence un round
	    nextRound();
	}
	
	/**
	 * Méthode permettant de créer les joueurs de manière automatique
	 * 
	 */
	public static void createPlayers() {
		if(playerlist.isEmpty()) {
			for(int i=0; i < nplayer; i++) {
				
		        //Ici on créer le joueur
				Player player = new Player("Player n" + i); 
				playerlist.add(player);
				
				System.out.println(player.getName() + " successfully added.");
				
			
			}
		}
	}
	
	/**
	 * Méthode permettant de récuperer les cartes de la défausse
	 * 
	 * @return
	 * La list des cartes défaussés
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
		if(nplayer==3) nc=4;
		if(nplayer==4) nc=3;
		if(nplayer==5) nc=2;
		if(nplayer==6) nc=2;
		
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
	
	/**
	 * Execute toutes les actions de fin de tour
	 */
	public static void endTurn() {
		Player bigwinner = null;
		Player roundwinner = null;
		
		//Vérifie si il reste un seul joueur non révélé
		List<Player> playernotreveal = new ArrayList<Player>();
		for(int i=0; i < playerlist.size(); i++) {
			if(!playerlist.get(i).isReveal()) playernotreveal.add(playerlist.get(i));
		}
		
		if(playernotreveal.size()==1) roundwinner = playernotreveal.get(0);
		if(roundwinner!=null && roundwinner.getIdentity()==CharacterType.WITCH) roundwinner.addPoints(2); 
		if(roundwinner!=null && roundwinner.getIdentity()==CharacterType.VILLAGER) roundwinner.addPoints(1); 
		
		checkPts();
		
		for(int i=0; i < playerlist.size(); i++) {
			if(playerlist.get(i).getPoints()>=5) {
				bigwinner = playerlist.get(i);
				break;
			}
		}
		
		if(bigwinner!=null) {
			System.out.println(bigwinner + " a gagné(e)");
		}
		else {
			if(roundwinner!=null) {
				nextRound();
			} else {
				nextTurn();
			}
		}
	}
	
	/**
	 * Enclenche un nouveau tour
	 * 
	 */
	public static void nextTurn() {
		clearScreen();
		
		//Si le premier joueur n'est pas défini c'est le premier joueur créer qui commence
		if(nextPlayer == null) {
			nextPlayer = playerlist.get(0);
		}
		
		//Ici c'est autour de next player joueur de jouer
		System.out.println("C'est le tour de " + nextPlayer);
		System.out.println("Command : accuse | play");
		
		Scanner sc= new Scanner(System.in);
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			//Ici si on met accuse dans la command le joueur accuse
			if(nexti.matches("accuse")) {
				command = true;
				Player accusedplayer = IHM.newselectplayer(playerlistnotreveal(nextPlayer));
				
				accusedplayer.etreAccuse(nextPlayer);
			} 
			//Ici command pour jouer une carte
			if(nexti.matches("play")) {
				if(nextPlayer.playHuntCard()) {
					command = true;
					endTurn();
				} else System.out.println("ERROR : Can't do this");
			} 
			else {
				System.out.println("ERROR : not recognize");
			}
		}while(!command);
	}
	
	public static void nextRound() {
		//On supprimer toutes les cartes
		removeAllCards();
		//On distribu les cartes au début du round
		distributeCards();
		//Selection des rôles
		selectidentities();	
		//Ici on met les pts
		checkPts();
		//Debut tour
		nextTurn();
	}
	
	/**
	 * Selectionner les identités de chacun en début de tour
	 */
	private static void selectidentities() {
		Scanner sc= new Scanner(System.in);
		
		for(int i=0; i < playerlist.size(); i++) {
			Player player = playerlist.get(i);
			System.out.println(player + " a toi de choisir");
			System.out.println("Tes cartes sont : " +  player.getCards());
			System.out.println("Select between Witch : W or Villager : V");
			
			boolean command = false;
			do{
				String nexti = sc.nextLine();
				if(nexti.matches("W")) {
					command = true;
					player.setIdentity(CharacterType.WITCH);
				} 
				if(nexti.matches("V")) {
					command = true;
					player.setIdentity(CharacterType.VILLAGER);
				}
				else {
					System.out.println("ERROR : select an existing identity");
				}
			}while(!command);
			
			clearScreen();
		}
	}
	
	/**
	 * Saute 50 ligne dans la console pour ne plus voir les joueurs d'avant
	 */
	public static void clearScreen() {  
		for (int i = 0; i < 50; ++i) System.out.println();
	}
	
	public static void removeAllCards() {  
		for (int i = 0; i < playerlist.size(); i++) {
			playerlist.get(i).clearCards();
		}
		cardslist.clear();
	}
	
	/**
	 * Permet de montrer les points de touts les joueurs
	 */
	public static void checkPts() {
		for(int i=0; i < playerlist.size(); i++) {
			System.out.println(playerlist.get(i) + " a " + playerlist.get(i).getPoints() + " pts");
		}
	}
	
	public static List<Player> getplayerlist(){
		return playerlist;
	}
	
	public static List<Player> playerlistnotreveal(Player playertoexclude){
		List<Player> playerlistnotreveal = new ArrayList<Player>();
		for(int i=0; i < playerlist.size(); i++) {
			if(!playerlist.get(i).isReveal() && playerlist.get(i)!=playertoexclude) playerlistnotreveal.add(playerlist.get(i));
		}
		return playerlistnotreveal;
	}
	
	public static List<Player> playerlistreveal(Player playertoexclude){
		List<Player> playerlistreveal = new ArrayList<Player>();
		for(int i=0; i < playerlist.size(); i++) {
			if(playerlist.get(i)!=playertoexclude) playerlistreveal.add(playerlist.get(i));
		}
		return playerlistreveal;
	}
}
