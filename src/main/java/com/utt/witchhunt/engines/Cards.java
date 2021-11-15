package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;

import com.utt.witchhunt.engines.effects.ChooseNextPlayer;
import com.utt.witchhunt.engines.effects.RevealIdentity;
import com.utt.witchhunt.engines.effects.TakeNextTurn;

public class Cards {
	private static List<Cards> cardslist = new ArrayList<Cards>();
	private boolean reveal = false;
	private boolean playerRequired = true;
	private String desc;
	
	
	private Effect WitchSide, HuntSide;
	private String name;
	
	private Cards(String n, Effect WS, Effect HS) {
		this.name = n;
		this.WitchSide = WS;
		this.HuntSide = HS;
	}
	
	public void setdesc(String d) {
		this.desc = d;
	}
	
	public String getdesc() {
		return this.desc;
	}
	
	public static void createCards() {
		//Angry Mob
		Effect AngryW = new TakeNextTurn();
		Effect AngryH = new RevealIdentity();
		String AngryDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		cardslist.add(new Cards("Angry Mob", AngryW, AngryH));
		
		//The Inquisition
		Effect InquisitionW = new TakeNextTurn(); // + Discard
		Effect InquisitionH = new ChooseNextPlayer(); // + LookPlayerIdentity
		String InquisitionDesc = "W: Discard a card from your hand + Take next turn | H(Only playable if you have been revealed as a Villager): Choose next player + Before their turn, secretly look at their identity";
		cardslist.add(new Cards("The Inquisition", InquisitionW, InquisitionH));
		
		//Pointed Hat
		Effect PointedW = new TakeNextTurn();
		Effect PointedH = new ChooseNextPlayer();
		String PointedDesc = "W(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Take next turn | H(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Choose next player";
		cardslist.add(new Cards("Pointed Hat", PointedW, PointedH));
		
		//Hooked Nose
		Effect HookedW = new ChooseNextPlayer();
		Effect HookedH = new ChooseNextPlayer();
		String HookednDesc = "W: Take one card from the hand of player who accused you + Take next turn | H: Choose next player + Before their turn, take a random card from thier hand and add it to your hand";
		cardslist.add(new Cards("Hooked Nose", HookedW, HookedH));
		
		//Broomstick while reveal, you cannot be chosen by the Angry Mob
		Effect BrommstickW = new TakeNextTurn();
		Effect BrommstickH = new ChooseNextPlayer();
		String BroomsticknDesc = "(While reveal, you cannot be chosen by the Angry Mob) | W: Take next turn | H: Choose next player";
		cardslist.add(new Cards("Broomstick", BrommstickW, BrommstickH));
		
		//Wart while reveal, you cannot be chosen by the Ducking Stool
		Effect WartW = new TakeNextTurn();
		Effect WartH = new ChooseNextPlayer();
		String WartDesc = "(While reveal, you cannot be chosen by the Ducking Stool) | W: Take next turn | H: Choose next player";
		cardslist.add(new Cards("Wart", WartW, WartH));
		
		//Ducking Stool
		Effect DuckingW = new ChooseNextPlayer();
		Effect DuckingH = new ChooseNextPlayer(); // Something
		String DuckingDesc = "W: Choose next player | H: Choose a player. They must reveal their identity or discrad a card from thier hand (W: +1pts + You take next turn | H: -1pts + They take next turn | If they discard: They take next turn)";
		cardslist.add(new Cards("Ducking Stool", DuckingW, DuckingH));
		
		//Cauldron
		Effect CauldronW = new TakeNextTurn(); // + Something
		Effect CauldronH = new RevealIdentity(); // + Something
		String CauldronDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		cardslist.add(new Cards("Cauldron", CauldronW, CauldronH));
		
		//Evil Eye
		Effect EvilW = new ChooseNextPlayer(); // + Something
		Effect EvilH = new ChooseNextPlayer(); // + Something
		String EvilDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		cardslist.add(new Cards("Evil Eye", EvilW, EvilH));
		
		//Toad
		Effect ToadW = new TakeNextTurn();
		Effect ToadH = new RevealIdentity(); // + Something
		String ToadnDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		cardslist.add(new Cards("Toas", ToadW, ToadH));
		
		//Black Cat
		Effect BlackW = new TakeNextTurn();
		Effect BlackH = new TakeNextTurn(); // + Something
		String BlackDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		cardslist.add(new Cards("Black Cat", BlackW, BlackH));
		
		//Pet Newi
		Effect NewiW = new TakeNextTurn();
		Effect NewiH = new ChooseNextPlayer(); // + Something
		String NewiDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		cardslist.add(new Cards("Pet Newi", NewiW, NewiH));
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
