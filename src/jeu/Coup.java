package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

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
		if (joueurCible == null) {
			return true;
		}
		
		if (!joueurCible.estDepotAutorise(carteJouee)) {
			return false;
		}
		
		boolean coupZoneAdverse = carteJouee instanceof Attaque || carteJouee instanceof DebutLimite;
		
		if (joueurCible != joueurCourant) {
			return coupZoneAdverse;
		}
		return !coupZoneAdverse;
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
	
	@Override
	public String toString() {
		if (joueurCible != null) {
			String str = joueurCourant.toString() + " dépose la carte " + carteJouee.toString();
			if (joueurCible == joueurCourant) {
				str += " dans sa zone de jeu";
			} else {
				str += " dans la zone de jeu de " + joueurCible.toString();
			}
			return str;
		}
		return joueurCourant.toString() + " défausse la carte " + carteJouee.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup coup) {
			if (!joueurCourant.equals(coup.joueurCourant)) {
				return false;
			}
			
			if (!carteJouee.equals(coup.carteJouee)) {
				return false;
			}
			
			
			if (joueurCible == null) {
				return coup.joueurCible == null;
			}
			
			return joueurCible.equals(coup.joueurCible);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		int code = 31 * getClass().hashCode() * joueurCourant.hashCode() * carteJouee.hashCode();
		if (joueurCible != null) {
			code *= joueurCible.hashCode();
		}
		return code;
	}
}
