package com.utt.witchhunt.engines.IA;

import java.util.List;

import com.utt.witchhunt.engines.Cards;

public interface Strategy {
	public int doOperation(int num1, int num2);
	
	public Cards playCard(List<Cards> cards);
}
