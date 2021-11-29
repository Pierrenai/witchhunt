package com.utt.witchhunt.engines.IA;

import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.Cards;

public class RandomStrategy implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cards playCard(List<Cards> cards) {
		Random rand = new Random();
		int i = rand.nextInt(cards.size());
		return cards.get(i);
	}

}
