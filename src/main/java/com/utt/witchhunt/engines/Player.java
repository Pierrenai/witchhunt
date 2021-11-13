package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private List<Cards> cards = new ArrayList<Cards>();

	public Player(String n) {
		this.name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void addCard(Cards c) {
		this.cards.add(c);
	}
	
	public List<Cards> getCards() {
		return cards;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void etreAccuse(Player p) {
		System.out.println(this + " est accusé par " + p);
	}

}
