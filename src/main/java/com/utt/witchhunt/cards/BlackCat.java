package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.IHM;
import com.utt.witchhunt.engines.Player;

public class BlackCat extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		this.setReveal();
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster) {
		List<Cards> discardedcardslist = Game.getdiscardedcardlist();

		if(!discardedcardslist.isEmpty()) {
			Cards ca = IHM.newselectcard(discardedcardslist);
			caster.discardCard(this);
			caster.addCard(ca);
			Game.setnextPlayer(caster);
			this.setReveal();
			return true;
		}
		return false;
		
	}

}
