package com.utt.witchhunt.cards.rumourcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;

public class HookedNoise extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Random rand = new Random();
		
		List<Cards> accusercards = accuser.getCards();
		List<Cards> castercardsnotreveal = new ArrayList<Cards>();
		for(int i=0; i < accusercards.size(); i++) {
			if(!accusercards.get(i).isReveal()) {
				castercardsnotreveal.add(accusercards.get(i));
			}
		}
		
		
		if(!castercardsnotreveal.isEmpty()) {
			int randomIndex = rand.nextInt(castercardsnotreveal.size());
	        Cards randomElement = castercardsnotreveal.get(randomIndex);

	        accuser.removeCard(randomElement);
	        caster.addCard(randomElement);
	        
			Game.setnextPlayer(caster);
			this.setReveal();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Random rand = new Random();
		
		Player target = caster.selectplayer(Game.playerlistreveal(caster));
		
		List<Cards> accusercards = target.getCards();
		List<Cards> castercardsnotreveal = new ArrayList<Cards>();
		for(int i=0; i < accusercards.size(); i++) {
			if(!accusercards.get(i).isReveal()) {
				castercardsnotreveal.add(accusercards.get(i));
			}
		}
		
		
		if(!castercardsnotreveal.isEmpty()) {
			int randomIndex = rand.nextInt(castercardsnotreveal.size());
	        Cards randomElement = castercardsnotreveal.get(randomIndex);

	        target.removeCard(randomElement);
	        caster.addCard(randomElement);
	        
			Game.setnextPlayer(target);
			this.setReveal();
			return true;
		}
		return false;		
	}

	@Override
	public boolean Witchplayable(Player accuser, Player caster) {
		return true;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		if(!caster.isReveal()) return true;
		else return false;
	}

}
