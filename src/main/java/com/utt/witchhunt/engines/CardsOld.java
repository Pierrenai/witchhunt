package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;

import com.utt.witchhunt.engines.effects.RevealIdentity;
import com.utt.witchhunt.engines.effects.TakeNextTurn;


//Je laisse cette classe à titre d'information
public class CardsOld {
	private static List<CardsOld> cardslist = new ArrayList<CardsOld>();
	private boolean reveal = false;
	private boolean playerRequired = true;
	private String desc;
	
	
	private List<Effect> WitchSide, HuntSide;
	private String name;
	
	private CardsOld(String n, List<Effect> WS, List<Effect> HS, String Desc) {
		this.name = n;
		this.WitchSide = WS;
		this.HuntSide = HS;
		this.desc = Desc;
	}
	
	public String getdesc() {
		return this.desc;
	}
	
	public static void createCards() {
		//Angry Mob
		List<Effect> AngryW = new ArrayList<Effect>();
		AngryW.add(new TakeNextTurn());
		
		List<Effect> AngryH = new ArrayList<Effect>();
		AngryW.add(new RevealIdentity());
		
		String AngryDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		
		cardslist.add(new CardsOld("Angry Mob", AngryW, AngryH, AngryDesc));
		
		//The Inquisition
		List<Effect> InquisitionW = new ArrayList<Effect>();
		InquisitionW.add(new TakeNextTurn());
		
		List<Effect> InquisitionH = new ArrayList<Effect>();
		InquisitionH.add(new RevealIdentity());
		
		String InquisitionDesc = "W: Discard a card from your hand + Take next turn | H(Only playable if you have been revealed as a Villager): Choose next player + Before their turn, secretly look at their identity";
		
		cardslist.add(new CardsOld("The Inquisition", InquisitionW, InquisitionH, InquisitionDesc));
		
		//Pointed Hat
		List<Effect> PointedW = new ArrayList<Effect>();
		PointedW.add(new TakeNextTurn());
		
		List<Effect> PointedH = new ArrayList<Effect>();
		PointedH.add(new RevealIdentity());
		
		String PointedDesc = "W(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Take next turn | H(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Choose next player";
		
		cardslist.add(new CardsOld("Pointed Hat", PointedW, PointedH, PointedDesc));
		
		//Hooked Nose
		List<Effect> HookedW = new ArrayList<Effect>();
		HookedW.add(new TakeNextTurn());
		
		List<Effect> HookedH = new ArrayList<Effect>();
		HookedH.add(new RevealIdentity());

		String HookednDesc = "W: Take one card from the hand of player who accused you + Take next turn | H: Choose next player + Before their turn, take a random card from thier hand and add it to your hand";
		
		cardslist.add(new CardsOld("Hooked Nose", HookedW, HookedH, HookednDesc));
		
		//Broomstick while reveal, you cannot be chosen by the Angry Mob
		List<Effect> BrommstickW = new ArrayList<Effect>();
		BrommstickW.add(new TakeNextTurn());
		
		List<Effect> BrommstickH = new ArrayList<Effect>();
		BrommstickH.add(new RevealIdentity());
		
		String BroomsticknDesc = "(While reveal, you cannot be chosen by the Angry Mob) | W: Take next turn | H: Choose next player";
		
		cardslist.add(new CardsOld("Broomstick", BrommstickW, BrommstickH, BroomsticknDesc));
		
		//Wart while reveal, you cannot be chosen by the Ducking Stool
		List<Effect> WartW = new ArrayList<Effect>();
		WartW.add(new TakeNextTurn());
		
		List<Effect> WartH = new ArrayList<Effect>();
		WartH.add(new RevealIdentity());
		
		String WartDesc = "(While reveal, you cannot be chosen by the Ducking Stool) | W: Take next turn | H: Choose next player";
		
		cardslist.add(new CardsOld("Wart", WartW, WartH, WartDesc));
		
		//Ducking Stool
		List<Effect> DuckingW = new ArrayList<Effect>();
		DuckingW.add(new TakeNextTurn());
		
		List<Effect> DuckingH = new ArrayList<Effect>();
		DuckingH.add(new RevealIdentity());

		String DuckingDesc = "W: Choose next player | H: Choose a player. They must reveal their identity or discrad a card from thier hand (W: +1pts + You take next turn | H: -1pts + They take next turn | If they discard: They take next turn)";
		
		cardslist.add(new CardsOld("Ducking Stool", DuckingW, DuckingH, DuckingDesc));
		
		//Cauldron
		List<Effect> CauldronW = new ArrayList<Effect>();
		CauldronW.add(new TakeNextTurn());
		
		List<Effect> CauldronH = new ArrayList<Effect>();
		CauldronH.add(new RevealIdentity());
		
		String CauldronDesc = "W: The player who accused you discards a random card from their hand + Take next turn | H: Reveal your identity (W: Player to your left takes next turn | H: Choose next player)";
		
		cardslist.add(new CardsOld("Cauldron", CauldronW, CauldronH, CauldronDesc));
		
		//Evil Eye
		List<Effect> EvilW = new ArrayList<Effect>();
		EvilW.add(new TakeNextTurn());
		
		List<Effect> EvilH = new ArrayList<Effect>();
		EvilH.add(new RevealIdentity());

		String EvilDesc = "W: Choose next player + On their turn they must accuse a player other than you, if possible | H: Choose next player + On their turn they must accuse a player other than you, if possible";
		
		cardslist.add(new CardsOld("Evil Eye", EvilW, EvilH, EvilDesc));
		
		//Toad
		List<Effect> ToadW = new ArrayList<Effect>();
		ToadW.add(new TakeNextTurn());
		
		List<Effect> ToadH = new ArrayList<Effect>();
		ToadH.add(new RevealIdentity());
		
		String ToadnDesc = "W: Take next turn | H: Reveal your identity (W: Player to your left takes next turn | H: Choose next player)";
		
		cardslist.add(new CardsOld("Toas", ToadW, ToadH, ToadnDesc));
		
		//Black Cat
		List<Effect> BlackW = new ArrayList<Effect>();
		BlackW.add(new TakeNextTurn());
		
		List<Effect> BlackH = new ArrayList<Effect>();
		BlackH.add(new RevealIdentity());
		
		String BlackDesc = "W: Take next turn | H: Add one discarded card to your hand, and then discard this card + Take next turn";
		
		cardslist.add(new CardsOld("Black Cat", BlackW, BlackH, BlackDesc));
		
		//Pet Newi
		List<Effect> NewiW = new ArrayList<Effect>();
		NewiW.add(new TakeNextTurn());
		
		List<Effect> NewiH = new ArrayList<Effect>();
		NewiH.add(new RevealIdentity());

		String NewiDesc = "W: Take next turn | H: Take a revealed Rumour card from any other player into your hand + Choose next player";
		
		cardslist.add(new CardsOld("Pet Newi", NewiW, NewiH, NewiDesc));
	}
	
	public static List<CardsOld> getCards() {
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
	
	/*
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
	*/
}
