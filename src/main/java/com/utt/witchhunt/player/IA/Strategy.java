
package com.utt.witchhunt.player.IA;

import java.util.List;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.CharacterType;
import com.utt.witchhunt.Modele.Player;

public interface Strategy {
	public Cards playHuntCard(List<Cards> cards);
	public Cards playHuntWitch(List<Cards> cards);
	
	
	/**
	 * Méthode pour savoir si le bot veut joueur une carte ou reveler son identité
	 * @param accuser
	 * @param cards
	 * @return
	 * True = joue une carte
	 * False = revele son identité
	 */
	public boolean etreAccuse(Player accuser, List<Cards> cards);
	public Player accuser(List<Player> list);
	public CharacterType selectIdentity();
	public Cards selectCards(List<Cards> cards);
	public Player selectPlayer(List<Player> list);
	
	/**
	 * Méthode pour savoir si le bot veut jouer une carte ou accuser
	 * @param cards
	 * La list des cartes du joueur
	 * @return
	 * True = joue une carte
	 * False = accuse un joueur
	 */
	public boolean play(List<Cards> cards);
}
