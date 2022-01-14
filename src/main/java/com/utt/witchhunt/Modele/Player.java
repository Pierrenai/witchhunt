package com.utt.witchhunt.Modele;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * Classe permettant d'instancier les joueurs
 * 
 * @author Pierre et Maud
 *
 */
public abstract class Player{
	private String name;
	private int pts = 0;
	private boolean reveal = false;
	private CharacterType identity;
	private ArrayList<Cards> cards;

	public Player(String n) {
		this.name = n;
		cards = new ArrayList<Cards>();
		
	}
	
	/**
	 * Methode permettant de recuperer le nom d'un joueur
	 * @return
	 * name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Methode permettant d'ajouter une carte au joueur
	 * @param c
	 * Carte à ajouter
	 */
	public void addCard(Cards c) {
		this.cards.add(c);
	}
	
	/**
	 * Methode permettant de supprimer une carte au joueur
	 * @param c
	 * Carte à supprimer
	 */
	public void removeCard(Cards c) {
		this.cards.remove(c);
	}
	
	/**
	 * Methode permettant de défausser une carte du joueur
	 * @param c
	 * Carte à défausser
	 */
	public void discardCard(Cards c) {
		this.cards.remove(c);
		Game.adddiscardedCard(c);
	}
	

	public ArrayList<Cards> getCards() {
		return cards;
	}
	
	/**
	 * Methode permettant de supprimer toutes les cartes d'un joueur
	 */
	public void clearCards() {
		cards.clear();
	}
	
	/**
	 * Methode permettant de récuperer la liste des cartes non-reveles du joueur
	 * @return
	 * La liste des cartes jouables
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
	
	
	public abstract void play();
	
	
	public abstract boolean playHuntCard();
	
	
	public abstract void etreAccuse(Player p);
	

	public abstract void accuser();
	
	
	public abstract void accuser(Player ppasaccusable);
	
	
	public abstract void selectIdentity();
	
	
	public abstract Player selectplayer(List<Player> players);
	
	
	public abstract Cards selectcard(List<Cards> cards);
	
	/**
	 * Methode pour savoir si un joueur est revele
	 * 
	 * @return
	 */
	public boolean isReveal() {
		return reveal;
	}
	
	/**
	 * Methode pour fixer l'identite du joueur
	 * @param i
	 * CharacterType
	 */
	public void setIdentity(CharacterType i) {
		this.identity = i;
		this.setReveal(false);
	}
	/**
	 * Methode pour recuperer l'identite d'un joueur
	 * 
	 * @return
	 * Identité
	 */
	public CharacterType getIdentity() {
		return this.identity;
	}
	/**
	 * Methode permettant d'ajouter des points
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
	 * Nombre de point(s) supprime(s)
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
	

	public void revealIdentity() {
		this.setReveal(true);
		System.out.println(this + " est " + identity);
	}

	/**
	 * Methode pour reveler un joueur
	 */
	public void setReveal(boolean reveal) {
		this.reveal = reveal;
	}

	

}
