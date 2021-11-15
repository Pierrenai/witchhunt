package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
		Cards.createdCards();
		cardslist = Cards.getCards();
	}
	
	public static int getNP() {
		return nplayer;
	}
	
	public static boolean editNP(int np) {
		nplayer = np;
		if(nplayer==np)return true; 
		else return false;
	}
	
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
	
	/*
	public static List<Player> getplayerlist(){
		return playerlist;
	}
	*/
	
	public static List<Cards> getdiscardedcardlist(){
		return cardslist;
	}
	
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
			
			System.out.println(player + " possèdent " +  player.getCards());
		
		}
		
		//Ici je list les cartes de la défausse
		System.out.println("La défausse possèdent " +  cardslist);
		
	}
	
	static void setnextPlayer(Player p) {
		nextPlayer = p;
	}
	
	public static void nextTurn() {
		//Si le premier joueur n'est pas défini c'est le premier joueur créer qui commence
		if(nextPlayer == null) {
			nextPlayer = playerlist.get(0);
		}
		
		//Ici c'est autour de next player joueur de jouer
		System.out.println("C'est le tour de " + nextPlayer);
		System.out.println("Possible command : accuse | ");
		
		Scanner sc= new Scanner(System.in);
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			//Ici si on met accuse dans la command le joueur accuse
			if(nexti.matches("accuse")) {
				command = true;
				accuserpar(nextPlayer);
			} 
			else {
				System.out.println("ERROR : not recognize");
			}
		}while(!command);
	}
	
	public static void nextRound() {
		//On relance un round ici
	}
	
	private static void accuserpar(Player p) {
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
				//On vérifie si le joueur n'est pas déjà révélé
				if(player.isReveal()) {
					System.out.println(player + "is already reveal");
					System.out.println("Choose an other player");
				} else {
					player.etreAccuse(p);
					command = true;
				}
			} 
			else {
				System.out.println("ERROR : select an existing player");
			}
		}while(!command);
	}
	
	
}
