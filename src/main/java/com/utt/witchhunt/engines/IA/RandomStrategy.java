package com.utt.witchhunt.engines.IA;

import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Player;

public class RandomStrategy implements Strategy {

	@Override
	public void etreAccuse(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectIdentity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean playHuntCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean play(List<Cards> cards) {
		Random rand = new Random();
		int i = rand.nextInt(1);
		
		if(i==0) return true;
		else return false;
	}

}
