package com.utt.witchhunt.cards.rumourcards;

import java.util.List;
import java.util.Scanner;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.CharacterType;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;

public class TheInquisition extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		List<Cards> clist = caster.getCards();
		for(int i = 0; i < clist.size(); i++) {
			if(clist.get(i).isReveal()) clist.remove(i);
		}
		Cards card = caster.selectcard(clist);
		caster.discardCard(card);
		Game.setnextPlayer(caster);	
		this.setReveal();
		return true;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Player target = caster.selectplayer(Game.playerlistnotreveal(caster));
		
		if(caster.isReveal() && caster.getIdentity()==CharacterType.VILLAGER) {
			Game.setnextPlayer(target);
			System.out.println(caster + " see " + target + "identity");
			System.out.println(target + " is " + target.getIdentity());
			this.setReveal();
			return true;
		}
		return false;
	}

	@Override
	public boolean Witchplayable(Player accuser, Player caster) {
		List<Cards> clist = caster.getCards();
		for(int i = 0; i < clist.size(); i++) {
			if(clist.get(i).isReveal()) clist.remove(i);
		}
		if(!clist.isEmpty()) return true;
		else return false;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		if(caster.isReveal() && caster.getIdentity()==CharacterType.VILLAGER) return true;
		else return false;
	}

}
