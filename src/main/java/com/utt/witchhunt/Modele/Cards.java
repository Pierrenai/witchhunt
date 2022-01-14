package com.utt.witchhunt.Modele;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.utt.witchhunt.Vue.InterfaceGraphique;
import com.utt.witchhunt.cards.rumourcards.AngryMob;
import com.utt.witchhunt.cards.rumourcards.BlackCat;
import com.utt.witchhunt.cards.rumourcards.Broomstick;
import com.utt.witchhunt.cards.rumourcards.Cauldron;
import com.utt.witchhunt.cards.rumourcards.DuckingStool;
import com.utt.witchhunt.cards.rumourcards.EvilEye;
import com.utt.witchhunt.cards.rumourcards.HookedNoise;
import com.utt.witchhunt.cards.rumourcards.PetNewi;
import com.utt.witchhunt.cards.rumourcards.PointedHat;
import com.utt.witchhunt.cards.rumourcards.TheInquisition;
import com.utt.witchhunt.cards.rumourcards.Toad;
import com.utt.witchhunt.cards.rumourcards.Wart;

public abstract class Cards {
	private boolean reveal = false;
	private String desc;
	private String name;
	private ImageIcon img;

	
	public static Cards[] cardsid = new Cards[12];
	
	public Cards() {
	}
	
	public void setCard(String n, String d, ImageIcon img) {
		this.name = n;
		this.desc = d;
		this.img = img;
		
		}
	
	public static List<Cards> getcardslist() {
		List<Cards> cardslist = new ArrayList<Cards>();
		
		//Angry Mob Card
		AngryMob angrymob = new AngryMob();
		String AngryDesc = "W: Take next turn | H(Only playable if you have been revealed as a Villager): Reveal another player's identity (W: +2pts + Take next Turn | H: -2pts + They take next turn)";
		ImageIcon AngryIcon = new ImageIcon(InterfaceGraphique.class.getResource("/media/angryMob.PNG"));
		angrymob.setCard("Angry Mob", AngryDesc, AngryIcon);
		
		cardsid[0] = angrymob;
		cardslist.add(angrymob);

		
		//The Inquisition Card
		TheInquisition inquisition = new TheInquisition();
		String InquisitionDesc = "W: Discard a card from your hand + Take next turn | H(Only playable if you have been revealed as a Villager): Choose next player + Before their turn, secretly look at their identity";
		ImageIcon InquisitionIcon = new ImageIcon(InterfaceGraphique.class.getResource("/media/theInquisition.PNG"));
		inquisition.setCard("The Inquisition", InquisitionDesc, InquisitionIcon);
		
		cardsid[1] = inquisition;
		cardslist.add(inquisition);
		
		//Pointed Hat Card
		PointedHat pointedhat = new PointedHat();
		String PointedDesc = "W(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Take next turn | H(Only playable if you have a revealed Rumour card): Take one of your own revealed Rumour cards into your hand + Choose next player";
		ImageIcon PointedHatIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/pointedHat.PNG"));
		pointedhat.setCard("Pointed Hat", PointedDesc, PointedHatIcon);
		
		cardsid[2] = pointedhat;
		cardslist.add(pointedhat);
		
		//Hooked Noise Card
		HookedNoise hookednoise = new HookedNoise();
		String HookedDesc = "W: Take one card from the hand of player who accused you + Take next turn | H: Choose next player + Before their turn, take a random card from thier hand and add it to your hand";
		ImageIcon HookedNoseIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/hookedNose.PNG"));
		hookednoise.setCard("Hooked Noise", HookedDesc, HookedNoseIcon);
		
		cardsid[3] = hookednoise;
		cardslist.add(hookednoise);
		
		//Broomstick Card
		Broomstick broomstick = new Broomstick();
		String BroomsticknDesc = "(While reveal, you cannot be chosen by the Angry Mob) | W: Take next turn | H: Choose next player";
		ImageIcon BroomstickIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/broomstick.PNG"));
		broomstick.setCard("Broomstick", BroomsticknDesc, BroomstickIcon);
		
		cardsid[4] = broomstick;
		cardslist.add(broomstick);
		
		//Wart Card
		Wart wart = new Wart();
		String WartDesc = "(While reveal, you cannot be chosen by the Ducking Stool) | W: Take next turn | H: Choose next player";
		ImageIcon WartIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/wart.PNG"));
		wart.setCard("Wart", WartDesc, WartIcon);
		
		cardsid[5] = wart;
		cardslist.add(wart);
		
		//Ducking Stool Card
		DuckingStool duckingstool = new DuckingStool();
		String DuckingDesc = "W: Choose next player | H: Choose a player. They must reveal their identity or discrad a card from thier hand (W: +1pts + You take next turn | H: -1pts + They take next turn | If they discard: They take next turn)";
		ImageIcon DuckingStoolIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/duckingStool.PNG"));
		duckingstool.setCard("Ducking Stool", DuckingDesc, DuckingStoolIcon);
		
		cardsid[6] = duckingstool;
		cardslist.add(duckingstool);
		
		//Cauldron Card
		Cauldron cauldron = new Cauldron();
		String CauldronDesc = "W: The player who accused you discards a random card from their hand + Take next turn | H: Reveal your identity (W: Player to your left takes next turn | H: Choose next player)";
		ImageIcon CauldronIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/cauldron.PNG"));
		cauldron.setCard("Cauldron", CauldronDesc, CauldronIcon);
		
		cardsid[7] = cauldron;
		cardslist.add(cauldron);
		
		//Evil Eye Card
		EvilEye evileye = new EvilEye();
		String EvilDesc = "W: Choose next player + On their turn they must accuse a player other than you, if possible | H: Choose next player + On their turn they must accuse a player other than you, if possible";
		ImageIcon EvilEyeIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/evilEye.PNG"));
		evileye.setCard("Evil Eye", EvilDesc, EvilEyeIcon);
		
		cardsid[8] = evileye;
		cardslist.add(evileye);
		
		//Toad Card
		Toad toad = new Toad();
		String ToadDesc = "W: Take next turn | H: Reveal your identity (W: Player to your left takes next turn | H: Choose next player)";
		ImageIcon ToadIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/toad.PNG"));
		toad.setCard("Toad", ToadDesc, ToadIcon);
		
		cardsid[9] = toad;
		cardslist.add(toad);
		
		//Black Cat Card
		BlackCat blackcat = new BlackCat();
		String BlackDesc = "W: Take next turn | H: Add one discarded card to your hand, and then discard this card + Take next turn";
		ImageIcon BlackCatIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/blackCat.PNG"));
		blackcat.setCard("Black Cat", BlackDesc, BlackCatIcon);
		
		cardsid[10] = blackcat;
		cardslist.add(blackcat);
		
		//Pet Newi Card
		PetNewi petnewi = new PetNewi();
		String NewiDesc = "W: Take next turn | H: Take a revealed Rumour card from any other player into your hand + Choose next player";
		ImageIcon PetNewtIcon = new ImageIcon (InterfaceGraphique.class.getResource("/media/petNewt.PNG"));
		petnewi.setCard("Pet Newi", NewiDesc, PetNewtIcon);
		
		cardsid[11] = petnewi;
		cardslist.add(petnewi);
		
		return cardslist;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public String getdesc() {
		return this.desc;
	}
	/**
	 * Methode permettant de savoir si une carte est revelee
	 * @return
	 */
	
	public boolean isReveal() {
		return reveal;
	}
	
	public void setNotReveal() {
		this.reveal = false;
	}
	/**
	 * Methode permettant de reveler une carte
	 */
	public void setReveal() {
		this.reveal = true;
	}
	
	public Cards getcardwithid(int id) {
		return cardsid[id];
	}
	
	public abstract boolean WitchSide(Player accuser, Player caster); //Accuser = celui qui accusé | Caster = celui qui se défend avec la carte witch
	public abstract boolean HuntSide(Player caster); //Caster celui qui joue la carte
	public abstract boolean Witchplayable(Player accuser, Player caster); //Vérifie si la carte est jouable
	public abstract boolean Huntplayable(Player caster); //Vérifie si la carte est jouable

	/**
	 * Methode permettant de recuperer l'image d'une carte
	 * @return
	 */
	public ImageIcon getImg() {
		return img;
	}
	/**
	 * Methode permettant de recuperer le nom d'une carte
	 * @return
	 */
	public String getName() {
		return name;
	}

	
}
