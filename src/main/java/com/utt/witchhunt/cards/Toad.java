package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class Toad extends Cards {

	@Override
	public boolean WitchSide(Player caster, Player target) {
		Game.setnextPlayer(caster);
		return true;
		
	}

	@Override
	public void HuntSide(Player caster, Player target) {
		caster.revealIdentity();
		if(caster.getIdentity().matches("Witch")) {
			Game.setnextPlayer(caster); //pas le caster, random ?
			return true;
		}
		if(caster.getIdentity().matches("Villager")) {
			Game.setnextPlayer(target);
			return true;
		}
		return false;
		
	}

}
