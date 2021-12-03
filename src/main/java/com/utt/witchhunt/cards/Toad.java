package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class Toad extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		this.setReveal();
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster) {
		caster.revealIdentity();
		if(caster.getIdentity()==CharacterType.WITCH) {
			Game.setnextPlayer(Game.getleftplayer(caster));
			return true;
		}
		if(caster.getIdentity()==CharacterType.VILLAGER) {
			Player target = caster.selectplayer(Game.playerlistreveal(caster));
			Game.setnextPlayer(target);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean Witchplayable(Player accuser, Player caster) {
		return true;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		if(!caster.isReveal())return true;
		else return false;
	}

}
