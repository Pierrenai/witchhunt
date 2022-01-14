package com.utt.witchhunt.cards.rumourcards;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.CharacterType;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;

public class Toad extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		this.setReveal();
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster) {
		caster.revealIdentity();
		if(caster.getIdentity()==CharacterType.WITCH) {
			Game.setnextPlayer(Game.getleftplayer(caster));
			return true;
		}
		if(caster.getIdentity()==CharacterType.VILLAGER) {
			Player target = caster.selectplayer(Game.playerlistreveal(caster));
			Game.setnextPlayer(target);
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
		if(!caster.isReveal())return true;
		else return false;
	}

}
