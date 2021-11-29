package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class DuckingStool extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(target);
		return true;
		
	}

	@Override
	public boolean HuntSide(Player accuser, Player caster) {
		
		Scanner sc= new Scanner(System.in);
		System.out.println(target+ "Press Y to reveal your identity | N to discard a card from your hand");
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			if(nexti.matches("Y")) {
				accuser.revealIdentity();	
				if(accuser.getIdentity()==CharacterType.WITCH) { //Tu confond le caster et la target
					caster.addPoints(1);
					Game.setnextPlayer(caster);
				}
				if(accuser.getIdentity()==CharacterType.VILLAGER) { //La même ici
					caster.removePoints(1);
					Game.setnextPlayer(accuser);
				}
				command = true;
				
			}

			if(nexti.matches("N")) {
				Cards card = IHM.newselectcard(accuser.getplayableCards());
				accuser.discardCard(card);
				Game.setnextPlayer(accuser);		
				command = true;
			}
			else {
				System.out.println("ERROR : Press Y to reveal | N to not reveal");
			}
		}while(!command);
		
		
		return false;
		
	}

}
