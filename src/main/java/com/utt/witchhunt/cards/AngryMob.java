package com.utt.witchhunt.cards;

import java.util.List;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.IHM;
import com.utt.witchhunt.engines.Player;

public class AngryMob extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		this.setReveal();
		return true;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Player target = IHM.newselectplayer(Game.playerlistnotreveal(caster));
		List<Cards> targetcards = target.getCards();
		Cards broomstickcard = super.getcardwithid(4);
		
		boolean check = true;
		if(targetcards.contains(broomstickcard) && broomstickcard.isReveal()) check = false;
		
		if(caster.isReveal() && caster.getIdentity()==CharacterType.VILLAGER && !target.isReveal() && check) {
		target.revealIdentity();
		if(caster.getIdentity()==CharacterType.WITCH) {
			caster.addPoints(2);
			Game.setnextPlayer(caster);
		}
		if(caster.getIdentity()==CharacterType.VILLAGER) {
			caster.removePoints(2);
			Game.setnextPlayer(target);
		}
		this.setReveal();
		return true;
		}
		return false;
	}

}
