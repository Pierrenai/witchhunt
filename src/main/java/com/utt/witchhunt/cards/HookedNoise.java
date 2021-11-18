package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Player;

public class HookedNoise extends Cards {

	@Override
	public boolean WitchSide(Player caster, Player target) {
		return false;
	}

	@Override
	public boolean HuntSide(Player caster, Player target) {
		return false;		
	}

}
