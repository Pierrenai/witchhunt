package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	private static int nplayer = 0;
	private static List<Player> playerlist = new ArrayList<Player>();
	private static List<Cards> cardslist = new ArrayList<Cards>();
	
	private final static Game instance = new Game();
	
	public final static Game getInstance() { 
		if(instance==null) new Game();
		return instance; }
	
	private Game() {
		Cards.createdCards();
		cardslist = Cards.getCards();
	}
	
	public int getNP() {
		return nplayer;
	}
	
	public boolean editNP(int np) {
		nplayer = np;
		if(nplayer==np)return true; 
		else return false;
	}
	
	public void createPlayers() {
		for(int i=0; i < nplayer; i++) {
			
	        //Ici on créer le joueur
			Player player = new Player("Player n" + i); 
			playerlist.add(player);
			
			System.out.println(player.getName() + " successfully added.");
			
		
		}
	}
	
	public void distributeCards() {
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
}
