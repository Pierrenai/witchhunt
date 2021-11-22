package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * 
 * Là où tout se passe
 * 
 * @author piotr
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
	    
	    //On récupère les cartes
	    cardslist = Cards.getcardslist();
	    
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
	public void adddiscardedCard(Cards c) {
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
	
	public static void setnextPlayer(Player p) {
		nextPlayer = p;
	}
	
	public static void endTurn() {
		//Verifier si quelqu'un a gagné
		Boolean winner = false;
		
		if(winner) {
			nextRound();
		} else {
			nextTurn();
		}
	}
	
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
				accuserpar(nextPlayer);
				
				command = true;
			} 
			//Ici command pour jouer une carte
			if(nexti.matches("play")) {
				if(nextPlayer.canplayCards()) {
					Cards c = selectcard(nextPlayer);
					if(c.isPlayerRequiredHunt()) {
						if(c.HuntSide(nextPlayer, selectplayer(false))) {
							c.setReveal();
							endTurn();
							command = true;
						}
						else System.out.println("ERROR : Can't play this card");
					} else {
						if(c.HuntSide(nextPlayer, null)) {
							c.setReveal();
							endTurn();
							command = true;
						}
						else System.out.println("ERROR : Can't play this card");
					}
				} else {
					System.out.println("ERROR : you don't have any more cards");
				}
			} 
			else {
				System.out.println("ERROR : not recognize");
			}
		}while(!command);
	}
	
	public static void nextRound() {
		//On distribu les cartes au début du round
		distributeCards();
		//Selection des rôles
		selectidentities();	
		//Ici on met les pts
		checkPts();
		//Debut tour
		nextTurn();
	}
	
	private static void accuserpar(Player p) {
		Player player = selectplayer(true);
				
		player.etreAccuse(p);
	}
	
	//Si notreveal = true il ne faut pas que le joueur soit reveler pour que ça marche
	public static Player selectplayer(boolean notreveal) {
		Scanner sc= new Scanner(System.in);
		
		for(int i=0; i < playerlist.size(); i++) {
			Player player = playerlist.get(i);
			if(player!=nextPlayer) {
				System.out.println(i + " : " + player + " reveal : " + player.isReveal());
			}
		}
		
		boolean command = false;
		do{
			int nexti = sc.nextInt();
			if(nexti <= playerlist.size()) {
				Player player = playerlist.get(nexti);
				
				//On vérifie si on a besoin que le joueur ne soit pas révélé et si il est reveal
				if(notreveal && player.isReveal()) {
					System.out.println(player + "is already reveal");
					System.out.println("Choose an other player");
				} else {
					command = true;
					
					return player;
				}
			} 
			else {
				System.out.println("ERROR : select an existing player");
			}
		}while(!command);
		
		return null;
	}
	
	public static Cards selectcard (Player p) {
		Scanner sc= new Scanner(System.in);
		
		List<Cards> playercards = p.getCards();
		
		for(int i=0; i < playercards.size(); i++) {
			Cards card = playercards.get(i);
			System.out.println(i + " : " + card);
		}
		
				
		boolean command = false;
		do{
			int nexti = sc.nextInt();
			if(nexti <= playercards.size()) {
				Cards card = playercards.get(nexti);
				//On vérifie si la carte n'est pas déjà révélé
				if(card.isReveal()) {
					System.out.println(card + "is already reveal");
					System.out.println("Choose an other card");
				} else {
					System.out.println(p + " played " + card);
					
					command = true;
					
					return card;
				}
			} 
			else {
				System.out.println("ERROR : select an existing cards");
			}
		}while(!command);
		
		return null;
	}
	
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
					player.setIdentity(CharacterType.WITCH);
					
					command = true;
				} 
				if(nexti.matches("V")) {
					player.setIdentity(CharacterType.VILLAGER);
					
					command = true;
				}
				else {
					System.out.println("ERROR : select an existing identity");
				}
			}while(!command);
			
			clearScreen();
		}
	}
	
	public static void clearScreen() {  
		for (int i = 0; i < 50; ++i) System.out.println();
	}
	
	public static void checkPts() {
		for(int i=0; i < playerlist.size(); i++) {
			System.out.println(playerlist.get(i) + " a " + playerlist.get(i).getPoints() + " pts");
		}
	}
}
