package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.IHM;
import com.utt.witchhunt.engines.Player;

public class Cauldron extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		
		Random rand = new Random();		
		List<Cards> accusercards = accuser.getCards();
		if(!accusercards.isEmpty()) {
			int randomIndex = rand.nextInt(accusercards.size());
	        Cards randomElement = accusercards.get(randomIndex);
	        accuser.discardCard(randomElement);
		}
		Game.setnextPlayer(caster);
		this.setReveal();
		return true;
	}

	@Override
	public boolean HuntSide(Player caster) {
		
		caster.revealIdentity();
		if(caster.getIdentity()==CharacterType.WITCH) {
			Game.setnextPlayer(Game.getleftplayer(caster)); 
			this.setReveal();
			return true;
		}
		if(caster.getIdentity()==CharacterType.VILLAGER) {
			Player target = IHM.newselectplayer(Game.playerlistreveal(caster));
			Game.setnextPlayer(target);
			this.setReveal();
			return true;
		}
		return false;
		
	}

}
