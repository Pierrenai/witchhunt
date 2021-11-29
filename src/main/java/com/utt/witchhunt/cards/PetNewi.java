package com.utt.witchhunt.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.IHM;
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
			Cards stealedcard = IHM.newselectcard(stealablecards);
			
			caster.addCard(stealedcard);
			stealedplayer.removeCard(stealedcard);
			
			Game.setnextPlayer(IHM.newselectplayer(Game.playerlistreveal(caster)));
			
			this.setReveal();
			return true;
		}
		
		return false;
		
	}

}
