package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
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
		if(caster.isReveal() && caster.getIdentity().matches("Villager"))
		target.revealIdentity();
		if(caster.getIdentity().matches("Witch")) {
			caster.addPoints(2);
			Game.setnextPlayer(caster);
			return true;
		}
		if(caster.getIdentity().matches("Villager")) {
			caster.removePoints(2);
			Game.setnextPlayer(target);
			return true;
		}
		return false;
	}

}
