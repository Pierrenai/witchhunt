package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player{
	private String name;
	private int pts = 0;
	private boolean reveal = false;
	private CharacterType identity;
	private List<Cards> cards = new ArrayList<Cards>();

	public Player(String n) {
		this.name = n;
	}
	
	/**
	 * name getter
	 * @return
	 * name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Permet d'ajouter une carte au joueur
	 * @param c
	 * Carte à ajouter
	 */
	public void addCard(Cards c) {
		this.cards.add(c);
	}
	
	/**
	 * Permet de supprimer une carte au joueur
	 * @param c
	 * Carte à supprimer
	 */
	public void removeCard(Cards c) {
		this.cards.remove(c);
	}
	
	/**
	 * Permet de défausser une carte du joueur
	 * @param c
	 * Carte à défausser
	 */
	public void discardCard(Cards c) {
		this.cards.remove(c);
		Game.adddiscardedCard(c);
	}
	
	/**
	 * getter de la list des cartes du joueur
	 * @return
	 * List des cartes du joueur
	 */
	public List<Cards> getCards() {
		return cards;
	}
	
	/**
	 * Permet de supprimer toutes les cartes d'un joueur
	 */
	public void clearCards() {
		cards.clear();
	}
	
	/**
	 * Permet de récuperer la list des cartes non-révélés du joueur
	 * @return
	 * La list des cartes jouables
	 */
	public List<Cards> getplayableCards(CardType cardtype) {
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
	 * Méthode permettant à un joueur de jouer
	 */
	public abstract void play();
	
	/**
	 * Méthode permettant à un joueur de jouer une carte
	 * 
	 * @return
	 * Si le joueur a pu jouer la carte
	 */
	public abstract boolean playHuntCard();
	
	/**
	 * Méthode permettant au joueur d'être accusé
	 * 
	 * @param p
	 * Le joueur qui accuse
	 */
	public abstract void etreAccuse(Player p);
	
	/**
	 * Méthode permettant au joueur d'accuser
	 * 
	 */
	public abstract void accuser();
	
	/**
	 * Méthode permettant au joueur d'accuser
	 * 
	 * @param ppasaccusable
	 * Le joueur qui est pas accusable
	 */
	public abstract void accuser(Player ppasaccusable);
	
	/**
	 * Méthode permettant au joueur de choisir son identité
	 */
	public abstract void selectIdentity();
	
	/**
	 * Méthode pour choisir un joueur
	 * @param players
	 * List des joueurs choisissables
	 * @return
	 * Le joueur choisie
	 */
	public abstract Player selectplayer(List<Player> players);
	
	/**
	 * Méthode pour choisir une carte
	 * @param cards
	 * List des cartes choisissables
	 * @return
	 * La carte choisie
	 */
	public abstract Cards selectcard(List<Cards> cards);
	
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
		this.setReveal(false);
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
		this.setReveal(true);
		System.out.println(this + " est " + identity);
	}

	/**
	 * reveal setter
	 */
	public void setReveal(boolean reveal) {
		this.reveal = reveal;
	}


}
