package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.IHM;
import com.utt.witchhunt.engines.Player;

public class Wart extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		return true;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Player target = IHM.newselectplayer(Game.playerlistreveal(caster));
		
		Game.setnextPlayer(target);
		return true;
	}
}
