package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {
	private Joueur joueurCourant;
	private Joueur joueurCible;
	private Carte carteJouee;
	
	public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		this.joueurCible = joueurCible;
		this.carteJouee = carteJouee;
	}
	
	public boolean estValide() {
		return ((carteJouee instanceof Attaque || carteJouee instanceof Limite) && !joueurCible.equals(joueurCourant))
				|| joueurCible.equals(joueurCourant);
	}
	
	public Joueur getJoueurCourant() {
		return joueurCourant;
	}
	
	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public Carte getCarteJouee() {
		return carteJouee;
	}
}
