package com.utt.witchhunt.cards;

import java.util.Scanner;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class PetNewi extends Cards {

	@Override
	public boolean WitchSide(Player caster, Player target) {
		Game.setnextPlayer(caster);
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster, Player target) {
		Scanner sc= new Scanner(System.in);
		System.out.println("From which player do you want to take a revealed card?");
		String nexti = sc.nextLine();
		//Ici ta pas piqué mon code mdr
		
		Game.setnextPlayer(target);
		
		return false;
		
	}

}
