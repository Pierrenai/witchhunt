package com.utt.witchhunt.utilitaires;

import com.utt.witchhunt.engines.Game;

public class Controleur {
	
	public void createPartie (int np, int nbot) {
		Game G = Game.getInstance();
		G.startGame(np, nbot);
	}

}
