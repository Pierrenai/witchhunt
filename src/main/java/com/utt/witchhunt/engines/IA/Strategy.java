package com.utt.witchhunt.engines.IA;

import java.util.List;

import com.utt.witchhunt.engines.Cards;
import com.utt.witchhunt.engines.Player;

public interface Strategy {
	public boolean playHuntCard();
	public void etreAccuse(Player p);
	public void selectIdentity();
	public boolean play(List<Cards> cards);
}
