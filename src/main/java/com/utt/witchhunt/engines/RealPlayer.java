package com.utt.witchhunt.engines;

import java.util.List;
import java.util.Scanner;

public class RealPlayer extends Player {

	public RealPlayer(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	/**
	 * Méthode permettant à un joueur de jouer une carte
	 * 
	 * @return
	 * Si le joueur a pu jouer la carte
	 */
	public boolean playHuntCard() {
		List<Cards> playablecards = this.getplayableCards();
		
		Cards card = IHM.newselectcard(playablecards);
		if(card==null) return false;

		if(card.HuntSide(this)) return true;
		else return false;
	}
	
	
	@Override
	/**
	 * Méthode permettant au joueur d'être accusé
	 * 
	 * @param p
	 * Le joueur qui accuse
	 */
	public void etreAccuse(Player p) {
		System.out.println(this + " est accusé par " + p);
		System.out.println("Press Y to reveal | N to not reveal");

		
		Scanner sc= new Scanner(System.in);
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			//Ici le joueur revele son identité
			if(nexti.matches("Y")) {
				this.setReveal(true);
				System.out.println(this + " est " + getIdentity());
				if(this.getIdentity() == CharacterType.WITCH) {
					p.addPoints(1);
				}
				
				Game.setnextPlayer(this);
				
				command = true;
			}
			//Ici le joueur ne revele pas son identité et décide de jouer une carte Witch
			if(nexti.matches("N")) {
				Cards card = IHM.newselectcard(getplayableCards());
				
				if(card!=null && card.WitchSide(p, this)) command = true;
				else System.out.println("ERROR : You can't do this");
				System.out.println("Press Y to reveal | N to not reveal");
			}
			else {
				System.out.println("ERROR : Press Y to reveal | N to not reveal");
			}
		}while(!command);
		
		Game.endTurn();
	}


	@Override
	public void selectIdentity() {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Tes cartes sont : " +  this.getCards());
		System.out.println("Select between Witch : W or Villager : V");
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			if(nexti.matches("W")) {
				command = true;
				this.setIdentity(CharacterType.WITCH);
			} 
			if(nexti.matches("V")) {
				command = true;
				this.setIdentity(CharacterType.VILLAGER);
			}
			else {
				System.out.println("ERROR : select an existing identity");
			}
		}while(!command);
		
	}
}
