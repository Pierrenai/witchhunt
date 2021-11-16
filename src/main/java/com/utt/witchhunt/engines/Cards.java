package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;

import com.utt.witchhunt.cards.AngryMob;

public abstract class Cards {
	private boolean reveal = false;
	private boolean playerRequired = true; //Necessaire ?
	private String desc;
	private String name;
	
	public Cards() {
	}
	
	public void setCard(String n, String d) {
		this.name = n;
		this.desc = d;
	}
	
	public List<Cards> getcardslist() {
		List<Cards> cardslist = new ArrayList<Cards>();
		
		//Angry Mob Card
		AngryMob angrymob = new AngryMob();
		String AngryDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		angrymob.setCard("Angry Mob", AngryDesc);
		
		cardslist.add(angrymob);

		String InquisitionDesc = "W: Discard a card from your hand + Take next turn | H(Only playable if you have been revealed as a Villager): Choose next player + Before their turn, secretly look at their identity";

		String PointedDesc = "W(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Take next turn | H(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Choose next player";

		String HookednDesc = "W: Take one card from the hand of player who accused you + Take next turn | H: Choose next player + Before their turn, take a random card from thier hand and add it to your hand";

		String BroomsticknDesc = "(While reveal, you cannot be chosen by the Angry Mob) | W: Take next turn | H: Choose next player";

		String WartDesc = "(While reveal, you cannot be chosen by the Ducking Stool) | W: Take next turn | H: Choose next player";

		String DuckingDesc = "W: Choose next player | H: Choose a player. They must reveal their identity or discrad a card from thier hand (W: +1pts + You take next turn | H: -1pts + They take next turn | If they discard: They take next turn)";

		String CauldronDesc = "W: The player who accused you discards a random card from their hand + Take next turn | H: Reveal your identity (W: Player to your left takes next turn | H: Choose next player)";

		String EvilDesc = "W: Choose next player + On their turn they must accuse a player other than you, if possible | H: Choose next player + On their turn they must accuse a player other than you, if possible";

		String ToadnDesc = "W: Take next turn | H: Reveal your identity (W: Player to your left takes next turn | H: Choose next player)";

		String BlackDesc = "W: Take next turn | H: Add one discarded card to your hand, and then discard this card + Take next turn";

		String NewiDesc = "W: Take next turn | H: Take a revealed Rumour card from any other player into your hand + Choose next player";

		
		return cardslist;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getdesc() {
		return this.desc;
	}
	
	public boolean isReveal() {
		return reveal;
	}
	
	public boolean isPlayerRequired() {
		return playerRequired;
	}
	
	public abstract boolean WitchSide(Player caster, Player target);
	public abstract boolean HuntSide(Player caster, Player target);
}
