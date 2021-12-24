package com.utt.witchhunt.cards;

import com.utt.witchhunt.player.Player;

public abstract class Effect {

	public Effect() {
	}
	
	public abstract void execute();
	public abstract void execute(Player p);
}
