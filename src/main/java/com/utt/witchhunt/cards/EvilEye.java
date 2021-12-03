package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class EvilEye extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Player target = caster.selectplayer(Game.playerlistreveal(caster));
		this.setReveal();
		target.accuser(caster);
		return true;
			
	}

	@Override
	public boolean HuntSide(Player caster) {
		Player target = caster.selectplayer(Game.playerlistreveal(caster));
		this.setReveal();
		target.accuser(caster);
		return true;
		
	}

	@Override
	public boolean Witchplayable(Player accuser, Player caster) {
		return true;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		return true;
	}

}
