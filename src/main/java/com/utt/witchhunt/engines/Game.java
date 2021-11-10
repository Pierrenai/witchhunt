package com.utt.witchhunt.engines;

public class Game {
	private static int nplayer = 0;
	
	private final static Game instance = new Game();
	
	public final static Game getInstance() { 
		if(instance==null) new Game();
		return instance; }
	
	private Game() {
	}
	
	public int getNP() {
		return nplayer;
	}
	
	public boolean editNP(int np) {
		nplayer = np;
		if(nplayer==np)return true; 
		else return false;
	}
}
