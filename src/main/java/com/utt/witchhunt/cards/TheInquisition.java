package com.utt.witchhunt.cards;

import java.util.List;
import java.util.Scanner;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.CharacterType;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class TheInquisition extends Cards {

	@Override
	public boolean WitchSide(Player caster, Player target) {
		//Choisir une carte
		Scanner sc= new Scanner(System.in);
		
		List<Cards> castercards = caster.getCards();
		
		for(int i=0; i < castercards.size(); i++) {
			Cards card = castercards.get(i);
			System.out.println(i + " : " + card);
		}
		
				
		boolean command = false;
		do{
			int nexti = sc.nextInt();
			if(nexti <= castercards.size()) {
				Cards card = castercards.get(nexti);
				//On vérifie si la carte n'est pas déjà révélé
					
					command = true;
				}
			else {
				System.out.println("ERROR : select a card in your hand");
			}
		}while(!command);
		
		Game.setnextPlayer(caster);		
		return true;
	}

	@Override
	public boolean HuntSide(Player caster, Player target) {
		if(caster.isReveal() && caster.getIdentity()==CharacterType.VILLAGER) {
			Game.setnextPlayer(target);
			System.out.println(caster + " see " + target + "identity");
			System.out.println(target + " is " + target.getIdentity());
			return true;
		}
		return false;
	}

}
