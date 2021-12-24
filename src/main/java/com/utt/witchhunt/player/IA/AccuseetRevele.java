package com.utt.witchhunt.player.IA;

import java.util.List;
import java.util.Random;

import com.utt.witchhunt.cards.Cards;
import com.utt.witchhunt.player.CharacterType;
import com.utt.witchhunt.player.Player;

public class AccuseetRevele implements Strategy {

	@Override
	public Cards playHuntCard(List<Cards> cards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cards playHuntWitch(List<Cards> cards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean etreAccuse(Player accuser, List<Cards> cards) {
		return false;
	}

	@Override
	public Player accuser(List<Player> list) {
		Random rand = new Random();
		int i = rand.nextInt(list.size());
		return list.get(i);
	}

	@Override
	public CharacterType selectIdentity() {
		Random rand = new Random();
		int i = rand.nextInt(1);
		
		if(i==0) return CharacterType.VILLAGER;
		else return CharacterType.WITCH;
	}

	@Override
	public boolean play(List<Cards> cards) {
		return false;
	}

	@Override
	public Cards selectCards(List<Cards> cards) {
		Random rand = new Random();
		int i = rand.nextInt(cards.size());
		return cards.get(i);
	}

	@Override
	public Player selectPlayer(List<Player> list) {
		Random rand = new Random();
		int i = rand.nextInt(list.size());
		return list.get(i);
	}

}
