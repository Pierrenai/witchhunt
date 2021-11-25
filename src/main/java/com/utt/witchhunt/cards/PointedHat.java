package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.IHM;
import com.utt.witchhunt.engines.Player;

public class PointedHat extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		List<Cards> castercards = caster.getCards();
		List<Cards> castercardsreveal = new ArrayList<Cards>();
		for(int i=0; i < castercards.size(); i++) {
			if(castercards.get(i).isReveal()) {
				castercardsreveal.add(castercards.get(i));
			}
		}
		
		
		if(!castercardsreveal.isEmpty()) {
			Cards choicedcard = IHM.newselectcard(castercardsreveal);
			choicedcard.setNotReveal();
			Game.setnextPlayer(caster);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean HuntSide(Player caster) {
		List<Cards> castercards = caster.getCards();
		List<Cards> castercardsreveal = new ArrayList<Cards>();
		for(int i=0; i < castercards.size(); i++) {
			if(castercards.get(i).isReveal()) {
				castercardsreveal.add(castercards.get(i));
			}
		}
		
		
		if(!castercardsreveal.isEmpty()) {
			Cards choicedcard = IHM.newselectcard(castercardsreveal);
			choicedcard.setNotReveal();
			Game.setnextPlayer(IHM.selectplayer(caster, false));
			return true;
		}
		
		return false;
		
	}

}
