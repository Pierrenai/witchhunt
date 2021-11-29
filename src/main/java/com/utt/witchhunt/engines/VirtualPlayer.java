package com.utt.witchhunt.engines;

import com.utt.witchhunt.engines.IA.Context;
import com.utt.witchhunt.engines.IA.OperationAdd;
import com.utt.witchhunt.engines.IA.Strategy;

public class VirtualPlayer extends Player {
	
	
	public VirtualPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private Context context;
	
	public void setStrategy(Strategy strat) {
		this.context = new Context(strat);
	}
	
	public void play() {
		this.context.playCards(this.getCards());
	}
}
