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
		Random rand = new Random();
		int i = rand.nextInt(1);
		
		if(i==0) return true;
		else return false;
	}

	@Override
	public boolean playHuntCard(List<Cards> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean etreAccuse(Player accuser, List<Cards> cards) {
		return false;
	}

	@Override
	public boolean accuser(List<Cards> playerlist) {
		// TODO Auto-generated method stub
		return false;
	}

}
