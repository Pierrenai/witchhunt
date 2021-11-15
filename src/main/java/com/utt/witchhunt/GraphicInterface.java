package com.utt.witchhunt;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicInterface {

	
	static void Frame() {
		JFrame frame = new JFrame("Hello World");
	    
        JLabel label = new JLabel("Je suis un JLabel", JLabel.CENTER);
        frame.add(label);
    
        // Définissez le panel
        JPanel panel = new JPanel();
        // Définir les boutons
        JButton btn1 = new JButton("Bouton 1");
        JButton btn2 = new JButton("Bouton 2");      
        // Ajouter les boutons au frame
        panel.add(btn1); 
        panel.add(btn2);
         
        // Ajouter label et panel au frame
        frame.setLayout(new GridLayout(2, 1));
        frame.add(label);
        frame.add(panel);
         
        frame.pack();
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
