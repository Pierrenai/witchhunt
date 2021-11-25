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
	
	public void discardCard(Cards c) {
		this.cards.remove(c);
		Game.adddiscardedCard(c);
	}
	
	public List<Cards> getCards() {
		return cards;
	}
	
	public void clearCards() {
		cards.clear();
	}
	
	public List<Cards> getplayableCards() {
		List<Cards> notrevealcards = new ArrayList<Cards>();
		for(int i=0; i < cards.size(); i++) {
			if(!cards.get(i).isReveal()) {
				notrevealcards.add(cards.get(i));
			}
		}
		return notrevealcards;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Méthode permettant à un joueur de jouer une carte
	 * 
	 * @return
	 * Si le joueur a pu jouer la carte
	 */
	public boolean playHuntCard() {
		List<Cards> playablecards = this.getplayableCards();
		
		Cards card = IHM.newselectcard(playablecards);
		if(card==null) return false;

		if(card.HuntSide(this)) return true;
		else return false;
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
				
				command = true;
			}
			//Ici le joueur ne revele pas son identité et décide de jouer une carte Witch
			if(nexti.matches("N")) {
				Cards card = IHM.newselectcard(getplayableCards());
				
				if(card!=null && card.WitchSide(p, this)) command = true;
				else System.out.println("ERROR : You can't do this");
				System.out.println("Press Y to reveal | N to not reveal");
			}
			else {
				System.out.println("ERROR : Press Y to reveal | N to not reveal");
			}
		}while(!command);
		
		Game.endTurn();
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
