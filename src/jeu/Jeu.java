package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private static final int NBCARTES = 6;
	
	private Sabot sabot;
	private Set<Joueur> joueurs = new TreeSet<>();
	
	public Jeu() {
		JeuDeCartes jdc = new JeuDeCartes();
		Carte[] cartes = jdc.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		cartes = (Carte[]) listeCartes.toArray();
		sabot = new Sabot(cartes);
	}
	
	public void inscrire(Joueur... aInscrire) {
		Collections.addAll(joueurs, aInscrire);
	}
	
	public void distributerCartes() {
		for (int i = 0; i < NBCARTES; i++) {
			for (Joueur joueur : joueurs) {
				joueur.donner(sabot.piocher());
			}
		}
	}
	
	public Sabot getSabot() {
		return sabot;
	}

}
