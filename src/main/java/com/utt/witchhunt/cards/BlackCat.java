package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class BlackCat extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster) {
		List<Cards> cardslist = new ArrayList<Cards>();
		cardslist= Game.getdiscardedcardlist();
		cardslist.get(0);
		cardslist.remove(0);
		//cardslist.add(??); ajouter cette carte au discarded
		Game.setnextPlayer(target);
		return true;
		
		/*
		List<Cards> cardslist = Game.getdiscardedcardlist();
		if(cardslist.isEmpty()) return false;
		//Ici faut rajouter le code pour choisir une carte
		
		caster.removeCard(this);
		Game.adddiscardedCard(this);
		
		Game.setnextPlayer(caster);
		
		//Déso je n'ai pas m'en empecher
		 */
		
	}

}
