package com.utt.witchhunt.engines.IA;

import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Player;

public class RandomStrategy implements Strategy {

	@Override
	public CharacterType selectIdentity() {
		Random rand = new Random();
		int i = rand.nextInt(1);
		
		if(i==0) return CharacterType.VILLAGER;
		else return CharacterType.WITCH;
	}

	@Override
	public boolean play(List<Cards> cards) {
		int i = 1;
		
		if(!cards.isEmpty()) {
			Random rand = new Random();
			i = rand.nextInt(1);
		}
		
		if(i==0) return true;
		else return false;
	}

	@Override
	public Cards playHuntCard(List<Cards> cards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean etreAccuse(Player accuser, List<Cards> cards) {
		return false;
	}

	@Override
	public Player accuser(List<Player> playerlist) {
		Random rand = new Random();
		int i = rand.nextInt(playerlist.size());
		return playerlist.get(i);
	}

	@Override
	public Cards playHuntWitch(List<Cards> cards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cards selectCards(List<Cards> cards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player selectPlayer(List<Player> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
