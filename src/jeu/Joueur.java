package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cartes.Carte;
import utils.GestionCartes;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu = new ZoneDeJeu();;
	private MainJoueur main = new MainJoueur();
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		
		Carte carte = sabot.piocher();
		donner(carte);
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void deposer(Carte carte) {
		zoneDeJeu.deposer(carte);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> coups = new HashSet<>();
		for (Joueur autre : participants) {
			coups.addAll(genererCoups(autre));
		}
		return coups;
	}
	
	public Set<Coup> coupsDefausse() {
		return genererCoups(null);
	}
	
	private Set<Coup> genererCoups(Joueur autre) {
		Set<Coup> coups = new HashSet<>();
		for (Carte carte : main) {
			Coup coup = new Coup(this, carte, autre);
			if (coup.estValide()) {
				coups.add(coup);
			}
		}
		return coups;
	}
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		ArrayList<Coup> coups = new ArrayList<>(coupsPossibles(participants));
		if (coups.isEmpty()) {
			coups.addAll(coupsDefausse());
		}
		return GestionCartes.extraire(new ArrayList<>(coups));
	}

	public String getNom() {
		return nom;
	}

	public MainJoueur getMain() {
		return main;
	}

	@Override
	public String toString() {
		return nom;
	}
}
