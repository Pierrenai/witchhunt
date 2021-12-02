package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class PetNewi extends Cards {

	@Override
	public boolean WitchSide(Player accuser, Player caster) {
		Game.setnextPlayer(caster);
		this.isReveal();
		return true;
		
	}

	@Override
	public boolean HuntSide(Player caster) {
		Scanner sc= new Scanner(System.in);
		
		List<Cards> stealablecards = new ArrayList<Cards>();
		Player stealedplayer = null;
		
		String nexti = sc.nextLine();
		for(int i=0; i < Game.getplayerlist().size(); i++) {
			for(int ii=0; ii < Game.getplayerlist().get(i).getCards().size(); ii++) {
				if(Game.getplayerlist().get(i).getCards().get(ii).isReveal()) {
					stealablecards.add(Game.getplayerlist().get(i).getCards().get(ii));
					stealedplayer = Game.getplayerlist().get(i);
				}
			}
		}
		if(!stealablecards.isEmpty()) {
			System.out.println("What card you want steal ?");
			Cards stealedcard = caster.selectcard(stealablecards);
			
			caster.addCard(stealedcard);
			stealedplayer.removeCard(stealedcard);
			
			Game.setnextPlayer(caster.selectplayer(Game.playerlistreveal(caster)));
			
			this.setReveal();
			return true;
		}
		
		return false;
		
	}

	@Override
	public boolean Witchplayable(Player caster) {
		return true;
	}

	@Override
	public boolean Huntplayable(Player caster) {
		List<Cards> stealablecards = new ArrayList<Cards>();
		for(int i=0; i < Game.getplayerlist().size(); i++) {
			for(int ii=0; ii < Game.getplayerlist().get(i).getCards().size(); ii++) {
				if(Game.getplayerlist().get(i).getCards().get(ii).isReveal()) {
					stealablecards.add(Game.getplayerlist().get(i).getCards().get(ii));
				}
			}
		}
		if(!stealablecards.isEmpty()) return true;
		else return false;
	}

}
