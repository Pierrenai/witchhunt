package com.utt.witchhunt.engines;

import java.util.List;
import java.util.Scanner;

public interface IHM {
	/**
	 * Méthode permettant de selectionner un joueur via la console
	 * @param p
	 * Joueur qui séléctionne
	 * @param notreveal
	 * Si notreveal = true il ne faut pas que le joueur soit reveler pour que ça marche
	 * @return
	 * Le joueur séléctionné
	 */
	public static Player selectplayer(Player p, boolean notreveal) {
		Scanner sc= new Scanner(System.in);
		List<Player> playerlist = Game.getplayerlist();
		
		for(int i=0; i < playerlist.size(); i++) {
			Player player = playerlist.get(i);
			if(player!=p) {
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
	
	/**
	 * Méhode permettant de selectionner une carte d'un joueur via la console 
	 * @param p
	 * Le joueur qui selectionne une carte
	 * @return
	 * La carte choisie
	 */
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
}
