package com.utt.witchhunt.utilitaires;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

import com.utt.witchhunt.cards.Cards;
import com.utt.witchhunt.engines.Game;
import com.utt.witchhunt.player.Player;

public class Main {
	
	public static void main(String[] args) {
		int np = 4;
		int nbot = 2;
		
		Controleur c = new Controleur();
		c.createPartie(np, nbot);
	}
}
