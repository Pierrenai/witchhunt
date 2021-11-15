package com.utt.witchhunt.engines.effects;

import com.utt.witchhunt.engines.Effect;
import com.utt.witchhunt.engines.Player;
import com.utt.witchhunt.engines.Game;

public class ChooseNextPlayer extends Effect{


	@Override
	public void execute() {}

	@Override
	public void execute(Player p) {
		Game.setnextPlayer(p);
	}
}
