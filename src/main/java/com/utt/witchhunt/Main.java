package com.utt.witchhunt;

import com.utt.witchhunt.engines.Game;

public class Main {
	public static void main(String[] args) {
		Game G = Game.getInstance();
		int nbplayer = G.NPlayer();
		
	    System.out.println(Integer.toString(nbplayer));
	  }
}
