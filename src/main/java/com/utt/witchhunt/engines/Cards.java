package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;

public class Cards {
	//private static Cards[] cardslist = new Cards[12];
	private static List<Cards> cardslist = new ArrayList<Cards>();
	
	
	private Effect WitchSide, HuntSide;
	private String name;
	
	private Cards(String n, Effect WS, Effect HS) {
		this.name = n;
		this.WitchSide = WS;
		this.HuntSide = HS;
	}
	
	public static void createdCards() {
		Effect w = new Effect();
		Effect h = new Effect();
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

}
