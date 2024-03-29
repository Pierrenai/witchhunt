package com.utt.witchhunt.Modele;

import java.util.List;
import java.util.Scanner;

public class RealPlayer extends Player {
	

	public RealPlayer(String n) {
		super(n);
	}
	
	
	@Override
	public void play() {
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		System.out.println("Command : accuse | play");
		
		boolean command = false;
		do{
			String nexti = sc.nextLine();
			//Ici si on met accuse dans la command le joueur accuse
			if(nexti.matches("accuse")) {
				command = true;
				this.accuser();
			} 
			//Ici command pour jouer une carte
			if(nexti.matches("play")) {
																	
				if(this.playHuntCard()) {
					command = true;
					Game.endTurn();
				} else System.out.println("ERROR : Can't do this");
			} 
			else {
				System.out.println("ERROR : not recognize");
			}
		}while(!command);
		
	}
	
	
	@Override
	public void accuser() {
		Player accusee = selectplayer(Game.playerlistnotreveal(this));
																	
		if(accusee!=null) accusee.etreAccuse(this);
		else System.out.println(this + " ne pas accuser");
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
	

	
	public boolean playHuntCard() {
		List<Cards> playablecards = this.getplayableCards(CardType.HUNT);
		if(!playablecards.isEmpty()) {
			Cards card = selectcard(playablecards);
			card.HuntSide(this);
			return true;
		}
		else return false;

	}
	
	
	public void etreAccuse(Player p) {
		System.out.println(this + " est accusé par " + p);
		System.out.println("Press Y to reveal | N to not reveal");

		
		@SuppressWarnings("resource")
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
				Cards card = selectcard(getplayableCards(CardType.WITCH));
				
				if(card!=null && card.WitchSide(p, this)) command = true;
				else System.out.println("ERROR : You can't do this");
			}
			else {
				System.out.println("ERROR : Press Y to reveal | N to not reveal");
			}
		}while(!command);
		
		Game.endTurn();
	}


	@Override
	public void selectIdentity() {
		@SuppressWarnings("resource")
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
	
	@Override
	public Player selectplayer(List<Player> players) {
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		if(players.size()==0) {
			return null;
		}
		
		//Listage des joueurs
		for(int i=0; i < players.size(); i++) {
			Player player = players.get(i);
			System.out.println(i + " : " + player);
		}
		
		//Boucle pour choisir la joueurs
		boolean command = false;
		do{
			int nexti = sc.nextInt();
			if(nexti <= players.size()) {
				command = true;
				Player player = players.get(nexti);
					
				return player;
			} 
			else {
				System.out.println("ERROR : select an existing player");
			}
		}while(!command);
		
		return null;
	}
	
	@Override
	public Cards selectcard (List<Cards> cards) {
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		if(cards.size()==0) {
			return null;
		}
		
		//Listage des cartes
		for(int i=0; i < cards.size(); i++) {
			Cards card = cards.get(i);
			System.out.println(i + " : " + card);
		}
		
		//Boucle pour choisir la cartes
		boolean command = false;
		do{
			int nexti = sc.nextInt();
			if(nexti <= cards.size()) {
				command = true;
				Cards card = cards.get(nexti);
					
				return card;
			} 
			else {
				System.out.println("ERROR : select an existing cards");
			}
		}while(!command);
		
		return null;
	}
	
	public static String namegetter() {
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		System.out.println("Nom du joueur :");
		String name = sc.nextLine();
		return name;
	}
}
