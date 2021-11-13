package com.utt.witchhunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class Main {
	
	public static void main(String[] args) {
		//Ici je créer la frame
		Frame();
		
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
	    
	    //Ici c'est pour les commandes executables
	    List<Player> playerlist = Game.getplayerlist();
	    boolean command = false;
		do{
			String nexti = sc.nextLine();
			if(nexti.matches("accuse")) {
				command = true;
				accuser(playerlist.get(1));
			} 
			else {
				System.out.println("ERROR : not recognize");
			}
		}while(!command);
		
	  }
	
	private static void accuser(Player p) {
		Scanner sc= new Scanner(System.in);
		List<Player> playerlist = Game.getplayerlist();
		
		for(int i=0; i < playerlist.size(); i++) {
			Player player = playerlist.get(i);
			System.out.println(i + " : " + player);
		}
		
		boolean command = false;
		do{
			int nexti = sc.nextInt();
			if(nexti <= playerlist.size()) {
				playerlist.get(nexti).etreAccuse(p);
				command = true;
			} 
			else {
				System.out.println("ERROR : select an existing player");
			}
		}while(!command);
	}
	
	private static void Frame() {
		JFrame frame = new JFrame("Hello World");
	    
        JLabel label = new JLabel("Je suis un JLabel", JLabel.CENTER);
        frame.add(label);
    
        // Définissez le panel
        JPanel panel = new JPanel();
        // Définir les boutons
        JButton btn1 = new JButton("Bouton 1");
        JButton btn2 = new JButton("Bouton 2");      
        // Ajouter les boutons au frame
        panel.add(btn1); 
        panel.add(btn2);
         
        // Ajouter label et panel au frame
        frame.setLayout(new GridLayout(2, 1));
        frame.add(label);
        frame.add(panel);
         
        frame.pack();
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
