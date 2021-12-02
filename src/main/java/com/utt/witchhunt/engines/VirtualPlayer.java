package com.utt.witchhunt.engines;

import java.util.List;
import java.util.Random;

import com.utt.witchhunt.engines.IA.Strategy;

public class VirtualPlayer extends Player {
	private Strategy strategy;
	
	public VirtualPlayer(String name, Strategy strategy) {
		super(name);
		this.strategy = strategy;
	}

	
	public void play() {
		boolean command = false;
		do{
			boolean A = this.strategy.play(this.getCards());
			if(A) {
				if(this.playHuntCard()) command = true;
			}
			if(!A) {
				command = true;
				this.accuser();
			}
			
		}while(!command);
	}

	@Override
	public boolean playHuntCard() {
		if(this.strategy.playHuntCard(this.getCards())) return true;
		else return false;
	}

	@Override
	public void etreAccuse(Player p) {
		boolean command = false;
		do {
			if(this.strategy.etreAccuse(p, this.getCards())) command = true;
		}while(!command);
		
	}

	@Override
	public void selectIdentity() {
		this.setIdentity(this.strategy.selectIdentity());
	}

	@Override
	public void accuser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accuser(Player ppasaccusable) {
		// TODO Auto-generated method stub
		
	}
	
	public void setStrategy(Strategy strat) {
		this.strategy = strat;
	}


	@Override
	public Player selectplayer(List<Player> players) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Cards selectcard(List<Cards> cards) {
		// TODO Auto-generated method stub
		return null;
	}
}
