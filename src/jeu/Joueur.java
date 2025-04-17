package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Bataille;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.Limite;
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
	
	public String afficherEtatJoueur() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Bottes :\n");
		for (Botte botte : zoneDeJeu.getBottes()) {
			sb.append("- " + botte.toString());
		}
		
		sb.append("Limitation ? ");
		List<Limite> pileLimites = zoneDeJeu.getPileLimites();
		boolean contient = !pileLimites.isEmpty() && pileLimites.get(0) instanceof DebutLimite;
		sb.append(contient);
		sb.append("\n");
		
		sb.append("Sommet bataille : ");
		Bataille sommetBataille = null;
		List<Bataille> pileBatailles = zoneDeJeu.getPileBatailles();
		if (!pileBatailles.isEmpty()) {
			sommetBataille = pileBatailles.get(0);
		}
		sb.append(sommetBataille);
		sb.append("\n");
		
		sb.append("Contenu de la main :\n");
		sb.append(main.toString());
		
		return sb.toString();
	}

	public String getNom() {
		return nom;
	}

	public MainJoueur getMain() {
		return main;
	}
	
	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}

	@Override
	public String toString() {
		return nom;
	}
}
