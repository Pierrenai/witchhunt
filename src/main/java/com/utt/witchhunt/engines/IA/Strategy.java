
package com.utt.witchhunt.engines.IA;

import java.util.List;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Player;

public interface Strategy {
	public boolean playHuntCard(List<Cards> cards);
	public boolean etreAccuse(Player accuser, List<Cards> cards);
	public boolean accuser(List<Cards> playerlist);
	public CharacterType selectIdentity();
	
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
