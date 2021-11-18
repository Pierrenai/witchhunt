package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	private String name;
	private int pts = 0;
	private boolean reveal = false;
	private CharacterType identity;
	private List<Cards> cards = new ArrayList<Cards>();

	public Player(String n) {
		this.name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void addCard(Cards c) {
		this.cards.add(c);
	}
	
	public void removeCard(Cards c) {
		this.cards.remove(c);
	}
	
	public List<Cards> getCards() {
		return cards;
	}
	
	public boolean canplayCards() {
		List<Cards> notrevealcards = new ArrayList<Cards>();
		for(int i=0; i < cards.size(); i++) {
			if(!cards.get(i).isReveal()) {
				notrevealcards.add(cards.get(i));
			}
		}
		if(notrevealcards.isEmpty()) {
			return false;
		} else return true;
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
				if(this.identity == CharacterType.WITCH) {
					p.addPoints(1);
				}
				
				command = true;
			}
			//Ici le joueur ne revele pas son identité
			if(nexti.matches("N")) {
				System.out.println(this + " est le prochain joueur");
				if(this.canplayCards()) {
					/*
					 * Bug à cause que dans le selectcard il cherche le nextPlayer de Game !!! à réfléchir
					Cards c = Game.selectcard(this);
					if(c.isPlayerRequiredWitch()) {
						if(c.WitchSide(this, Game.selectplayer(false))) {
							c.setReveal();
							Game.endTurn();
							command = true;
						}
						else System.out.println("ERROR : Can't play this card");
					} else {
						if(c.WitchSide(this, null)) {
							c.setReveal();
							Game.endTurn();
							command = true;
						}
						else System.out.println("ERROR : Can't play this card");
					}*/
				} else {
					System.out.println("ERROR : you don't have any more cards");
				}
				
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
	
	public void setIdentity(CharacterType i) {
		this.identity = i;
	}
	
	public CharacterType getIdentity() {
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
