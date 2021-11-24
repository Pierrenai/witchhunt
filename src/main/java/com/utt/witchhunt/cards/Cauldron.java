package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class Cauldron extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		
		//List<Cards> cards = new ArrayList<Cards>();
		//cards = ?ACCUSATEUR?.getCards();
		//cards.remove(0); //aleatoire????
		
		return false;
	}

	@Override
	public boolean HuntSide(Player caster) {
		caster.revealIdentity();
		if(caster.getIdentity()==CharacterType.WITCH) {
			Game.setnextPlayer(caster); //attention pas le caster, random ? @P:Je pense qu'on va prendre le joueur n-1
			return true;
		}
		if(caster.getIdentity()==CharacterType.VILLAGER) {
			Game.setnextPlayer(target);
			return true;
		}
		return false;
		
	}

}
