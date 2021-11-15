package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;

import com.utt.witchhunt.engines.effects.ChooseNextPlayer;

public class Cards {
	private static List<Cards> cardslist = new ArrayList<Cards>();
	private boolean reveal = false;
	private boolean playerRequired = true;
	
	
	private Effect WitchSide, HuntSide;
	private String name;
	
	private Cards(String n, Effect WS, Effect HS) {
		this.name = n;
		this.WitchSide = WS;
		this.HuntSide = HS;
	}
	
	public static void createCards() {
		Effect w = new ChooseNextPlayer();
		Effect h = new ChooseNextPlayer();
		//Angry Mob
		cardslist.add(new Cards("Angry Mob", w, h));
		
		//The Inquisition
		cardslist.add(new Cards("The Inquisition", w, h));
		
		//Pointed Hat
		cardslist.add(new Cards("Pointed Hat", w, h));
		
		//Hooked Nose
		cardslist.add(new Cards("Hooked Nose", w, h));
		
		//Broomstick
		cardslist.add(new Cards("Broomstick", w, h));
		
		//Wart
		cardslist.add(new Cards("Wart", w, h));
		
		//Ducking Stool
		cardslist.add(new Cards("Ducking Stool", w, h));
		
		//Cauldron
		cardslist.add(new Cards("Cauldron", w, h));
		
		//Evil Eye
		cardslist.add(new Cards("Evil Eye", w, h));
		
		//Toad
		cardslist.add(new Cards("Toas", w, h));
		
		//Black Cat
		cardslist.add(new Cards("Black Cat", w, h));
		
		//Pet Newi
		cardslist.add(new Cards("Pet Newi", w, h));
	}
	
	public static List<Cards> getCards() {
		return cardslist;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public boolean isReveal() {
		return reveal;
	}
	
	public boolean isPlayerRequired() {
		return playerRequired;
	}
	
	public void executeHuntSide() {
		this.HuntSide.execute();
	}
	
	public void executeHuntSide(Player p) {
		this.HuntSide.execute(p);
	}

	public void executeWitchSide() {
		this.WitchSide.execute();
	}
	
	public void executeWitchSide(Player p) {
		this.WitchSide.execute(p);
	}
}
