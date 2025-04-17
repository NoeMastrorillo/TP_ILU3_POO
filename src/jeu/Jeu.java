package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private static final int NBCARTES = 6;
	
	private Sabot sabot;
	private Set<Joueur> joueurs = new LinkedHashSet<>();
	private Iterator<Joueur> tourJoueur;
	
	public Jeu() {
		JeuDeCartes jdc = new JeuDeCartes();
		Carte[] cartes = jdc.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		cartes = listeCartes.toArray(cartes);
		sabot = new Sabot(cartes);
		tourJoueur = joueurs.iterator();
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
	
	public String jouerTour(Joueur joueur) {
		StringBuilder str = new StringBuilder();
		
		Carte cartePiochee = joueur.prendreCarte(sabot);
		str.append("Le joueur " + joueur.toString() + " pioche la carte " + cartePiochee.toString() + "\nIl a dans sa main : " + joueur.getMain().toString() + "\n");
		
		Coup coup = joueur.choisirCoup(joueurs);
		str.append(coup.toString());
		
		Carte carteJouee = coup.getCarteJouee();
		Joueur joueurCible = coup.getJoueurCible();
		joueur.retirerDeLaMain(carteJouee);
		
		if (joueurCible != null) {
			joueurCible.deposer(carteJouee);
		} else {
			joueur.deposer(carteJouee);
		}
		
		return str.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		if (!tourJoueur.hasNext()) {
			tourJoueur = joueurs.iterator();
		}
		
		return tourJoueur.next();
	}
	
	public String lancer() {
		Joueur joueurCourant = donnerJoueurSuivant();
		StringBuilder deroulement = new StringBuilder();
		
		boolean joueurGagne = false;
		boolean sabotVide = false;
		while (!joueurGagne && !sabotVide) {
			deroulement.append(jouerTour(joueurCourant) + "\n");
			if (joueurCourant.donnerKmParcourus() >= 1000) {
				joueurGagne = true;
			} else if (sabot.estVide()) {
				sabotVide = true;
			} else {
				joueurCourant = donnerJoueurSuivant();
			}
		}
		
		if (joueurGagne) {
			deroulement.append("Le joueur " + joueurCourant.toString() + " a gagné.");
		} else {
			deroulement.append("Le sabot est vide.");
		}
		
		return deroulement.toString();
	}
	
	public Sabot getSabot() {
		return sabot;
	}

}
