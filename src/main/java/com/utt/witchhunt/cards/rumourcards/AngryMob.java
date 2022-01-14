package com.utt.witchhunt.cards.rumourcards;

import java.util.List;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.CharacterType;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;

public class AngryMob extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		this.setReveal();
		return true;
	}

	@Override
	public boolean HuntSide(Player caster) {
		Player target = caster.selectplayer(Game.playerlistnotreveal(caster));
		if(target==null) return false;
		
		List<Cards> targetcards = target.getCards();
		//List<Cards> targetcards = caster.getCards();
		Cards broomstickcard = super.getcardwithid(4);
		
		boolean check = true;
		if(targetcards.contains(broomstickcard) && broomstickcard.isReveal()) check = false;
			if(caster.isReveal() && caster.getIdentity()==CharacterType.VILLAGER && !target.isReveal() && check) {
			target.revealIdentity();
			if(caster.getIdentity()==CharacterType.WITCH) {
				caster.addPoints(2);
				Game.setnextPlayer(caster);
			}
			if(caster.getIdentity()==CharacterType.VILLAGER) {
				caster.removePoints(2);
				Game.setnextPlayer(target);
			}
			this.setReveal();
			return true;
		} else return false;
	}

	@Override
	public boolean Witchplayable(Player accuser, Player caster) {
		return true;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		if(caster.isReveal() && caster.getIdentity()==CharacterType.VILLAGER) {
			Cards broomstickcard = super.getcardwithid(4);
			List<Player> plist = Game.playerlistnotreveal(caster);
			for(int i=0; i < plist.size(); i++) {
				if(plist.get(i).getCards().contains(broomstickcard) && broomstickcard.isReveal()) plist.remove(i);
			}
			if(!plist.isEmpty()) return true;
			else return false;
		}
		else return false;
	}

}
