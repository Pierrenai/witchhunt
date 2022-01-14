package com.utt.witchhunt.cards.rumourcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;

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
			Cards ca = caster.selectcard(discardedcardslist);
			caster.discardCard(this);
			caster.addCard(ca);
			Game.setnextPlayer(caster);
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
		if(!Game.getdiscardedcardlist().isEmpty()) return true;
		else return false;
	}

}
