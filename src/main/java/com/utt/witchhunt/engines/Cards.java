package com.utt.witchhunt.engines;

import java.util.ArrayList;
import java.util.List;

import com.utt.witchhunt.cards.AngryMob;
import com.utt.witchhunt.cards.BlackCat;
import com.utt.witchhunt.cards.Broomstick;
import com.utt.witchhunt.cards.Cauldron;
import com.utt.witchhunt.cards.DuckingStool;
import com.utt.witchhunt.cards.EvilEye;
import com.utt.witchhunt.cards.HookedNoise;
import com.utt.witchhunt.cards.PetNewi;
import com.utt.witchhunt.cards.PointedHat;
import com.utt.witchhunt.cards.TheInquisition;
import com.utt.witchhunt.cards.Toad;
import com.utt.witchhunt.cards.Wart;

public abstract class Cards {
	private boolean reveal = false;
	private boolean playerRequiredWitch = true;
	private boolean playerRequiredHunt = true;
	private String desc;
	private String name;
	
	private static Cards[] cardsid = new Cards[12];
	
	public Cards() {
	}
	
	public void setCard(String n, String d) {
		this.name = n;
		this.desc = d;
	}
	
	public static List<Cards> getcardslist() {
		List<Cards> cardslist = new ArrayList<Cards>();
		
		//Angry Mob Card
		AngryMob angrymob = new AngryMob();
		String AngryDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		angrymob.setCard("Angry Mob", AngryDesc);
		
		cardsid[0] = angrymob;
		cardslist.add(angrymob);

		//The Inquisition Card
		TheInquisition inquisition = new TheInquisition();
		String InquisitionDesc = "W: Discard a card from your hand + Take next turn | H(Only playable if you have been revealed as a Villager): Choose next player + Before their turn, secretly look at their identity";
		inquisition.setCard("The Inquisition", InquisitionDesc);
		
		cardsid[1] = inquisition;
		cardslist.add(inquisition);
		
		//Pointed Hat Card
		PointedHat pointedhat = new PointedHat();
		String PointedDesc = "W(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Take next turn | H(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Choose next player";
		pointedhat.setCard("Pointed Hat", PointedDesc);
		
		cardsid[2] = pointedhat;
		cardslist.add(pointedhat);
		
		//Hooked Noise Card
		HookedNoise hookednoise = new HookedNoise();
		String HookedDesc = "W: Take one card from the hand of player who accused you + Take next turn | H: Choose next player + Before their turn, take a random card from thier hand and add it to your hand";
		hookednoise.setCard("Hooked Noise", HookedDesc);
		
		cardsid[3] = hookednoise;
		cardslist.add(hookednoise);
		
		//Broomstick Card
		Broomstick broomstick = new Broomstick();
		String BroomsticknDesc = "(While reveal, you cannot be chosen by the Angry Mob) | W: Take next turn | H: Choose next player";
		broomstick.setCard("Broomstick", BroomsticknDesc);
		
		cardsid[4] = broomstick;
		cardslist.add(broomstick);
		
		//Wart Card
		Wart wart = new Wart();
		String WartDesc = "(While reveal, you cannot be chosen by the Ducking Stool) | W: Take next turn | H: Choose next player";
		wart.setCard("Wart", WartDesc);
		
		cardsid[5] = wart;
		cardslist.add(wart);
		
		//Ducking Stool Card
		DuckingStool duckingstool = new DuckingStool();
		String DuckingDesc = "W: Choose next player | H: Choose a player. They must reveal their identity or discrad a card from thier hand (W: +1pts + You take next turn | H: -1pts + They take next turn | If they discard: They take next turn)";
		duckingstool.setCard("Ducking Stool", DuckingDesc);
		
		cardsid[6] = duckingstool;
		cardslist.add(duckingstool);
		
		//Cauldron Card
		Cauldron cauldron = new Cauldron();
		String CauldronDesc = "W: The player who accused you discards a random card from their hand + Take next turn | H: Reveal your identity (W: Player to your left takes next turn | H: Choose next player)";
		cauldron.setCard("Cauldron", CauldronDesc);
		
		cardsid[7] = cauldron;
		cardslist.add(cauldron);
		
		//Evil Eye Card
		EvilEye evileye = new EvilEye();
		String EvilDesc = "W: Choose next player + On their turn they must accuse a player other than you, if possible | H: Choose next player + On their turn they must accuse a player other than you, if possible";
		evileye.setCard("Evil Eye", EvilDesc);
		
		cardsid[8] = evileye;
		cardslist.add(evileye);
		
		//Toad Card
		Toad toad = new Toad();
		String ToadDesc = "W: Take next turn | H: Reveal your identity (W: Player to your left takes next turn | H: Choose next player)";
		toad.setCard("Toad", ToadDesc);
		
		cardsid[9] = toad;
		cardslist.add(toad);
		
		//Black Cat Card
		BlackCat blackcat = new BlackCat();
		String BlackDesc = "W: Take next turn | H: Add one discarded card to your hand, and then discard this card + Take next turn";
		blackcat.setCard("Black Cat", BlackDesc);
		
		cardsid[10] = blackcat;
		cardslist.add(blackcat);
		
		//Pet Newi Card
		PetNewi petnewi = new PetNewi();
		String NewiDesc = "W: Take next turn | H: Take a revealed Rumour card from any other player into your hand + Choose next player";
		petnewi.setCard("Pet Newi", NewiDesc);
		
		cardsid[11] = petnewi;
		cardslist.add(petnewi);
		
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
	
	public void setNotReveal() {
		this.reveal = false;
	}
	
	public void setReveal() {
		this.reveal = true;
	}
	
	public boolean isPlayerRequiredHunt() {
		return playerRequiredHunt;
	}
	
	public boolean isPlayerRequiredWitch() {
		return playerRequiredWitch;
	}
	
	public abstract boolean WitchSide(Player caster, Player target); //Caster = celui qui joue la carte | target : celui qui est ciblé par la carte
	public abstract boolean HuntSide(Player caster, Player target); //Idem
}
