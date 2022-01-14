package com.utt.witchhunt.cards.rumourcards;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;

public class Wart extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		this.setReveal();
		return true;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Player target = caster.selectplayer(Game.playerlistreveal(caster));
		
		Game.setnextPlayer(target);
		this.setReveal();
		return true;
	}
	
	@Override
	public boolean Witchplayable(Player accuser, Player caster) {
		return true;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		return true;
	}
}
