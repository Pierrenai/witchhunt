package com.utt.witchhunt.engines;

public abstract class Effect {

	public Effect() {
	}
	
	public abstract void execute();
	public abstract void execute(Player p);
}
