package com.utt.witchhunt.cards;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class EvilEye extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(target);
		//DOIT ACCUSER QQ AUTRE QUE LE CASTER @P:mdr
		
		return false;

		
	}

	@Override
	public boolean HuntSide(Player caster) {
		Game.setnextPlayer(target);
		//Doit accuser qq autre au tour suivant
		
		return false;
	}

}
