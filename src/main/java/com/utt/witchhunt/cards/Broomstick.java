package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class Broomstick extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		return true;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Game.setnextPlayer(target);
		return true;
	}

}
