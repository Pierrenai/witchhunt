package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class BlackCat extends Cards {

	@Override
	public boolean WitchSide(Player caster, Player target) {
		Game.setnextPlayer(caster);
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster, Player target) {
		List<Cards> cardslist = new ArrayList<Cards>();
		cardslist= Game.getdiscardedcardlist();
		cardslist.get(0);
		cardslist.remove(0);
		//cardslist.add(??); ajouter cette carte au discarded
		Game.setnextPlayer(target);
		return true;
		
		
		
	}

}
