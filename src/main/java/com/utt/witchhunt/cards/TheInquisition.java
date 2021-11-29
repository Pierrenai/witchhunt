package com.utt.witchhunt.cards;

import java.util.List;
import java.util.Scanner;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.IHM;
import com.utt.witchhunt.engines.Player;

public class TheInquisition extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Cards card = IHM.newselectcard(caster.getplayableCards());
		caster.discardCard(card);
		Game.setnextPlayer(caster);		
		return true;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Player target = IHM.newselectplayer(Game.playerlistnotreveal(caster));
		
		if(caster.isReveal() && caster.getIdentity()==CharacterType.VILLAGER) {
			Game.setnextPlayer(target);
			System.out.println(caster + " see " + target + "identity");
			System.out.println(target + " is " + target.getIdentity());
			return true;
		}
		return false;
	}

}
