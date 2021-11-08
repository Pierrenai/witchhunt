package com.utt.witchhunt.engines;

public class Game {
	private static int nplayer = 0;
	
	private final static Game instance = new Game();
	
	public final static Game getInstance() { 
		if(instance==null) new Game();
		return instance; }
	
	private Game() {
		nplayer = 1;
	}
	
	public int NPlayer() {
		return nplayer;
	}
}
