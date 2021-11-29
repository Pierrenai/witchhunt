package com.utt.witchhunt.engines.IA;

import java.util.List;

import com.utt.witchhunt.engines.Cards;

public class Context {
	private Strategy strategy;
	
	   public Context(Strategy strategy){
	      this.strategy = strategy;
	   }
	
	   public int executeStrategy(int num1, int num2){
	      return strategy.doOperation(num1, num2);
	   }

	public Cards playCards(List<Cards> cards) {
		return strategy.playCard(cards);
	}
}
