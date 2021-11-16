package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	private String name;
	private int pts = 0;
	private boolean reveal = false;
	private String identity = "un bug";
	private List<Cards> cards = new ArrayList<Cards>();

	public Player(String n) {
		this.name = n;
	}
	
	public String getName() {
		return name;
	}
	
	void addCard(Cards c) {
		this.cards.add(c);
	}
	
	public List<Cards> getCards() {
		return cards;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void etreAccuse(Player p) {
		System.out.println(this + " est accusé par " + p);
		System.out.println("Press Y to reveal | N to not reveal");

		
		Scanner sc= new Scanner(System.in);
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			//Ici le joueur revele son identité
			if(nexti.matches("Y")) {
				reveal = true;
				System.out.println(this + " est " + identity);
				if(this.identity.matches("Witch")) {
					p.addPoints(1);
				}
				
				command = true;
			}
			//Ici le joueur ne revele pas son identité
			if(nexti.matches("N")) {
				System.out.println(this + " est le prochain joueur");
				
				command = true;
			}
			else {
				System.out.println("ERROR : Press Y to reveal | N to not reveal");
			}
		}while(!command);
		
		Game.setnextPlayer(this);
	}
	
	public boolean isReveal() {
		return reveal;
	}
	
	public void setIdentity(String i) {
		this.identity = i;
	}
	
	public String getIdentity() {
		return this.identity;
	}
	
	public void addPoints(int p) {
		this.pts += p;
	}
	
	public void removePoints(int p) {
		this.pts -= p;
	}
	
	public int getPoints() {
		return this.pts;
	}
	
	public void revealIdentity() {
		this.reveal = true;
		System.out.println(this + " est " + identity);
	}


}
