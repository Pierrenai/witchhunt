package com.utt.witchhunt.cards.rumourcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.utt.witchhunt.Modele.Cards;
import com.utt.witchhunt.Modele.CharacterType;
import com.utt.witchhunt.Modele.Game;
import com.utt.witchhunt.Modele.Player;

public class DuckingStool extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Player target = caster.selectplayer(Game.playerlistreveal(caster));
		Game.setnextPlayer(target);
		this.setReveal();
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster) {
		
		Player target = caster.selectplayer(Game.playerlistreveal(caster));
		List<Cards> targetcards = target.getCards();
		Cards wart = super.getcardwithid(5);
		
		boolean check = true;
		if(targetcards.contains(wart) && wart.isReveal()) check = false;
		
		if(!target.isReveal()&& check) {
			Scanner sc= new Scanner(System.in);
			System.out.println(target+ "Press Y to reveal your identity | N to discard a card from your hand");
			boolean command = false;
			do{
				String nexti = sc.nextLine();
				if(nexti.matches("Y")) {
					target.revealIdentity();	
					if(target.getIdentity()==CharacterType.WITCH) { //Tu confond le caster et la target
						caster.addPoints(1);
						Game.setnextPlayer(caster);
					}
					if(target.getIdentity()==CharacterType.VILLAGER) { //La même ici
						caster.removePoints(1);
						Game.setnextPlayer(target);
					}
					command = true;
					this.setReveal();
					return true;
					
				}
	
				if(nexti.matches("N")) {
					List<Cards> dcards = caster.getCards();
					for(int i = 0; i< dcards.size(); i++) {
						if(dcards.get(i).isReveal()) dcards.remove(i);
					}
					if(!dcards.isEmpty()) {
						Cards card = caster.selectcard(dcards);
						target.discardCard(card);
						Game.setnextPlayer(target);		
						command = true;
						this.setReveal();
						return true;
					}
				
				}
				else {
					System.out.println("ERROR : Press Y to reveal | N to not reveal");
				}
			}while(!command);
		}
		
		return false;
		
	}

	@Override
	public boolean Witchplayable(Player accuser, Player caster) {
		return true;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		Cards wart = super.getcardwithid(5);
		List <Player> listep = Game.playerlistnotreveal(caster);
		for (int i=0; i<listep.size(); i++) {
			if (listep.get(i).getCards().contains(wart)&& wart.isReveal()) {
				listep.remove(i);
			}
		}
		if (!listep.isEmpty()) return true;
		else return false;
	}

}
