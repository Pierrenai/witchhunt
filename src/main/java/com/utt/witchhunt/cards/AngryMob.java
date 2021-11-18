package com.utt.witchhunt.cards;

import java.util.List;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class AngryMob extends Cards {

	@Override
	public boolean WitchSide(Player caster, Player target) {
		Game.setnextPlayer(caster);
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster, Player target) {
		List<Cards> targetcards = target.getCards();
		Broomstick broomstickcard = new Broomstick(); //Ici il faut venir récuperer la bonne carte !!!!
		
		if(caster.isReveal() && caster.getIdentity()==CharacterType.VILLAGER && !targetcards.contains(broomstickcard) && !broomstickcard.isReveal()) {
		target.revealIdentity();
		if(caster.getIdentity()==CharacterType.WITCH) {
			caster.addPoints(2);
			Game.setnextPlayer(caster);
		}
		if(caster.getIdentity()==CharacterType.VILLAGER) {
			caster.removePoints(2);
			Game.setnextPlayer(target);
		}
		return true;
		}
		return false;
	}

}
