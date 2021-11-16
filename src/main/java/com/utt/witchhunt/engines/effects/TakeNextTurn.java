package com.utt.witchhunt.engines.effects;

import com.utt.witchhunt.engines.Effect;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.engines.Player;

public class TakeNextTurn extends Effect {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Player p) {
		Game.setnextTurn(p);
	}

}
