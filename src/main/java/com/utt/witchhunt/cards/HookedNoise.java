package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.IHM;
import com.utt.witchhunt.engines.Player;

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
			return true;
		}
		
		return false;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Random rand = new Random();
		
		Player target = IHM.selectplayer(caster, false);
		
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
			return true;
		}
		return false;		
	}

}
