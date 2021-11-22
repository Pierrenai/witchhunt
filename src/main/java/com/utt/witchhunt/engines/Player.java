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
	
	public void deleteCards() {
		for(int i=0; i < cards.size(); i++) {
			this.removeCard(cards.get(i));
		}
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
	
	/**
	 * Méthode permettant à un joueur de jouer une carte
	 */
	public void playCard() {
		
	}
	
	
	/**
	 * Méthode permettant au joueur d'être accusé
	 * 
	 * @param p
	 * Le joueur qui accuse
	 */
	public void etreAccuse(Player p) {
		System.out.println(this + " est accusé par " + p);
		System.out.println("Press Y to reveal | N to not reveal");

		
		Scanner sc= new Scanner(System.in);
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			//Ici le joueur revele son identité
			if(nexti.matches("Y")) {
				this.reveal = true;
				System.out.println(this + " est " + identity);
				if(this.identity == CharacterType.WITCH) {
					p.addPoints(1);
				}
				
				Game.setnextPlayer(this);
				Game.endTurn();
				
				command = true;
			}
			//Ici le joueur ne revele pas son identité
			if(nexti.matches("N")) {
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
	}
	
	/**
	 * Getter de reveal
	 * 
	 * @return
	 * Si il est reveal
	 */
	public boolean isReveal() {
		return reveal;
	}
	
	/**
	 * Setter de identity
	 * Cache l'identité du joueur
	 * 
	 * @param i
	 * Identité
	 */
	public void setIdentity(CharacterType i) {
		this.identity = i;
		this.reveal = false;
	}
	/**
	 * Getter de identity
	 * 
	 * @return
	 * Identité
	 */
	public CharacterType getIdentity() {
		return this.identity;
	}
	/**
	 * Permet d'ajouter des points
	 * 
	 * @param p
	 * Nombre de point(s) ajouté(s)
	 */
	public void addPoints(int p) {
		this.pts += p;
	}
	
	/**
	 * Permet d'ajouter des points
	 * 
	 * @param p
	 * Nombre de point(s) supprimé(s)
	 */
	public void removePoints(int p) {
		this.pts -= p;
	}
	
	/**
	 * Getter de point
	 * @return
	 * Nombre de point
	 */
	public int getPoints() {
		return this.pts;
	}
	
	/**
	 * Permet de revelé l'identité
	 */
	public void revealIdentity() {
		this.reveal = true;
		System.out.println(this + " est " + identity);
	}


}
