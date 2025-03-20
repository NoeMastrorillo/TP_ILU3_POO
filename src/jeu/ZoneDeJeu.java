package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new ArrayList<>();
	private List<Bataille> pileBatailles = new ArrayList<>();
	private List<Borne> collectionBornes = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int km = 0;
		for (Borne borne : collectionBornes) {
			km += borne.getKm();
		}
		return km;
	}
	
	public void deposer(Carte carte) {
		if (carte instanceof Borne borne) {
			collectionBornes.add(borne);
		} else if (carte instanceof Limite limite) {
			pileLimites.add(0, limite);
		} else {
			pileBatailles.add(0, (Bataille) carte);
		}
	}
	
	public boolean peutAvancer() {
		return !pileBatailles.isEmpty() && pileBatailles.get(0).equals(Cartes.FEU_VERT);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte == Cartes.FEU_VERT) return estDepotFeuVertAutorise();
		if (carte instanceof Borne borne) return estDepotBorneAutorise(borne);
		if (carte instanceof Limite limite) return estDepotLimiteAutorise(limite);
		if (carte instanceof Bataille bataille) return estDepotBatailleAutorise(bataille);
		return true;
	}
	
	private boolean estDepotFeuVertAutorise() {
		if (pileBatailles.isEmpty()) {
			return true;
		}
		
		Bataille bataille = pileBatailles.get(0);
		return bataille.equals(Cartes.FEU_ROUGE)
				|| ((bataille instanceof Parade) && !bataille.equals(Cartes.FEU_VERT));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer()
				&& (pileLimites.isEmpty()
					|| pileLimites.get(0) instanceof FinLimite
					|| borne.getKm() <= 50)
				&& donnerKmParcourus() + borne.getKm() <= 1000;	
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		return (limite instanceof DebutLimite
					&& (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite))
				|| pileLimites.get(0) instanceof DebutLimite;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			return peutAvancer();
		}
		
		if (bataille.equals(Cartes.FEU_VERT)) {
			if (pileBatailles.isEmpty()) return true;
			
			Bataille batailleSurPile = pileBatailles.get(0);
			return batailleSurPile.equals(Cartes.FEU_ROUGE) || !batailleSurPile.equals(Cartes.FEU_VERT);
		}
		
		return !pileBatailles.isEmpty() && pileBatailles.get(0).getType() == bataille.getType();
	}
}
