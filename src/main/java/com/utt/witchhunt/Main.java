package com.utt.witchhunt;

import java.util.Scanner;

import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in); 
		int np = 0;
		
		System.out.println("Nombre de joueurs (3-6) : ");
		boolean p36 = false;
		while(p36) {
			int nexti = sc.nextInt();
			if(nexti >= 3 && nexti <= 6) {
				np = nexti;
				p36 = true;
			} else {
				System.out.println("ERROR : player must be between 3 and 6");
			}
		}
		
		Player[] players = new Player[np];
		for(int i=0; i < np; i++) {
			Player player = new Player("Player n" + i); 
			players[i] = player;
			
			System.out.println(player.getName() + " successfully added.");
		}
	
		
		
		Game G = Game.getInstance();
		if(G.editNP(np)) System.out.println("NP successfully changed");
		int nbplayer = G.getNP();
		
	    System.out.println(Integer.toString(nbplayer));
	  }
}
