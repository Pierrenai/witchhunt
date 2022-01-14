package com.utt.witchhunt.cards.rumourcards;

import java.util.ArrayList;
import java.util.List;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;

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
			Cards choicedcard = caster.selectcard(castercardsreveal);
			choicedcard.setNotReveal();
			Game.setnextPlayer(caster);
			this.setReveal();
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
			Cards choicedcard = caster.selectcard(castercardsreveal);
			choicedcard.setNotReveal();
			Game.setnextPlayer(caster.selectplayer(Game.playerlistreveal(caster)));
			this.setReveal();
			return true;
		}
		
		return false;
		
	}

	@Override
	public boolean Witchplayable(Player accuser, Player caster) {
		List<Cards> castercards = caster.getCards();
		for(int i=0; i < castercards.size(); i++) {
			if(!castercards.get(i).isReveal()) castercards.remove(i);
		}
		if(!castercards.isEmpty()) return true;
		else return false;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		List<Cards> castercards = caster.getCards();
		for(int i=0; i < castercards.size(); i++) {
			if(!castercards.get(i).isReveal()) castercards.remove(i);
		}
		if(!castercards.isEmpty()) return true;
		else return false;
	}

}
