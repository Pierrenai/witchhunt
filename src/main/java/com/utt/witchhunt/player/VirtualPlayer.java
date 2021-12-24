package com.utt.witchhunt.player;

import java.util.List;
import java.util.Random;

import com.utt.witchhunt.cards.CardType;
import com.utt.witchhunt.cards.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.player.IA.Strategy;

public class VirtualPlayer extends Player {
	private Strategy strategy;
	
	public VirtualPlayer(String name, Strategy strategy) {
		super(name);
		this.strategy = strategy;
	}

	
	public void play() {
		if(this.strategy.play(this.getCards())) this.playHuntCard();
		else this.accuser();
	}

	@Override
	public boolean playHuntCard() {
		return false;
	}

	@Override
	public void etreAccuse(Player p) {
		if(this.strategy.etreAccuse(p, this.getplayableCards(CardType.WITCH))) {
			//Joue une carte witch on peut le faire plus tard
		} else {
			this.setReveal(true);
			System.out.println(this + " est " + getIdentity());
			if(this.getIdentity() == CharacterType.WITCH) {
				p.addPoints(1);
			}
			
			Game.setnextPlayer(this);
		}
		
		Game.endTurn();
		
	}

	@Override
	public void selectIdentity() {
		this.setIdentity(this.strategy.selectIdentity());
	}

	@Override
	public void accuser() {
		this.strategy.accuser(Game.playerlistnotreveal(this)).etreAccuse(this);
	}

	@Override
	public void accuser(Player ppasaccusable) {
		List<Player> playerlist = Game.playerlistnotreveal(this);
		if(playerlist.contains(ppasaccusable)) playerlist.remove(ppasaccusable);
		Player accusee = selectplayer(playerlist);
			
		if(accusee!=null) accusee.etreAccuse(this);
		if(accusee==null && !ppasaccusable.isReveal()) ppasaccusable.etreAccuse(ppasaccusable);
		else {
			System.out.println(this + " ne pas accuser personne");
			Game.endTurn();
		}
		
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
