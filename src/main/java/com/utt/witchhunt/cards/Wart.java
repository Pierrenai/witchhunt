package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class Wart extends Cards {

	@Override
	public boolean WitchSide(Player caster, Player target) {
		Game.setnextPlayer(caster);
		Game.endTurn();
		return true;
	}

	@Override
	public boolean HuntSide(Player caster, Player target) {
		Game.setnextPlayer(target);
		Game.endTurn();
		return true;
	}
}
