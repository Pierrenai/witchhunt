package com.utt.witchhunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class Main {
	
	public static void main(String[] args) {
		//Ici sont les variables initialisées
		Scanner sc= new Scanner(System.in);
		int np = 0;
	
		//Ici on recup le nombre de joueur
		System.out.println("Entrez un nombre de joueurs entre 3 et 6 : ");
		boolean p36 = false;
		do{
			int nexti = sc.nextInt();
			if(nexti >= 3 && nexti <= 6) {
				np = nexti;
				p36 = true;
			} else {
				System.out.println("ERROR : player must be between 3 and 6");
				System.out.println("Entrez un nombre de joueurs entre 3 et 6 : ");
			}
		}while(!p36);
		
		//Ici on créer une partie
		Game G = Game.getInstance();
		if(G.editNP(np)) System.out.println("NP successfully changed");
	    
	    //Ici on créer les joueurs
	    G.createPlayers();
	    
	    //Ici on distribu les cartes
	    G.distributeCards();
	    
	  }
}
