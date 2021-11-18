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
	public boolean WitchSide(Player caster, Player target) {
		Game.setnextPlayer(target);
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster, Player target) {
		//Je vois que tu copie allègrement mon code
		Scanner sc= new Scanner(System.in);
		System.out.println(target+ "Press Y to reveal your identity | N to discard a card from your hand");
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			if(nexti.matches("Y")) {
				target.revealIdentity();				
				command = true;
			}

			if(nexti.matches("N")) {
				List<Cards> cards = new ArrayList<Cards>();
				cards = target.getCards();
				Scanner sca= new Scanner(System.in);
				System.out.println(cards+"Which card do you want to discard ?");
				//String nexti2 = sca.nextLine();//comment reconnaitre carte?
				//............				
				
				command = true;
			}
			else {
				System.out.println("ERROR : Press Y to reveal | N to not reveal");
			}
		}while(!command);
		
		if(caster.getIdentity()==CharacterType.WITCH) { //Tu confond le caster et la target
			caster.addPoints(1);
			Game.setnextPlayer(caster);
			return true;
		}
		if(caster.getIdentity()==CharacterType.VILLAGER) { //La même ici
			caster.removePoints(1);
			Game.setnextPlayer(target);
			return true;
		}
		return false;
		
	}

}
