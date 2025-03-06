package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	
	public Jeu() {
		JeuDeCartes jdc = new JeuDeCartes();
		Carte[] cartes = jdc.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		cartes = (Carte[]) listeCartes.toArray();
		sabot = new Sabot(cartes);
	}
	
	public Sabot getSabot() {
		return sabot;
	}

}
