package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new LinkedList<>();
	private List<Bataille> pileBatailles = new LinkedList<>();
	private Collection<Borne> collectionBornes = new ArrayList<>();
	private Set<Botte> bottes = new HashSet<>();

	public int donnerLimitationVitesse() {
		if (estPrioritaire() || pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite) {
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
		} else if (carte instanceof Botte botte) {
			bottes.add(botte);
		} else if (carte instanceof Bataille bataille) {
			pileBatailles.add(0, bataille);
		}
	}

//	public void deposer(Borne borne) {
//		collectionBornes.add(borne);
//	}
//	
//	public void deposer(Limite limite) {
//		pileLimites.add(0, limite);
//	}
//	
//	public void deposer(Botte botte) {
//		bottes.add(botte);
//	}
//	
//	public void deposer(Bataille bataille) {
//		pileBatailles.add(0, bataille);
//	}

	public boolean peutAvancer() {
		if (pileBatailles.isEmpty()) {
			return estPrioritaire();
		}

		Bataille sommet = pileBatailles.get(0);
		return sommet.equals(Cartes.FEU_VERT)
				|| ((sommet instanceof Parade || sommet.equals(Cartes.FEU_ROUGE)) && estPrioritaire())
				|| (sommet instanceof Attaque && contientBotteDeType(sommet.getType()) && estPrioritaire());
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		} else if (carte instanceof Bataille bataille){
			return estDepotBatailleAutorise(bataille);
		} else {
			// Botte
			return true;
		}
	}
//	
//	public boolean estDepotAutorise(Carte carte) {
//		return true;
//	}
//	
//	public boolean estDepotAutorise(Borne borne) {
//		return estDepotBorneAutorise(borne);
//	}
//	
//	public boolean estDepotAutorise(Limite limite) {
//		return estDepotLimiteAutorise(limite);
//	}
//	
//	public boolean estDepotAutorise(Bataille bataille) {
//		return estDepotBatailleAutorise(bataille);
//	}

	private boolean contientBotteDeType(Type type) {
		for (Botte botte : bottes) {
			if (botte.getType() == type) {
				return true;
			}
		}

		return false;
	}

	private boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) {
			return false;
		}

		if (pileBatailles.isEmpty()) {
			return true;
		}

		Bataille sommet = pileBatailles.get(0);
		return sommet.equals(Cartes.FEU_ROUGE) || !sommet.equals(Cartes.FEU_VERT);
	}

	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer()
				&& (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite || borne.getKm() <= 50)
				&& donnerKmParcourus() + borne.getKm() <= 1000;
	}

	private boolean estDepotLimiteAutorise(Limite limite) {
		return !estPrioritaire()
				&& (limite instanceof DebutLimite && (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite))
				|| (!pileLimites.isEmpty() && pileLimites.get(0) instanceof DebutLimite);
	}

	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (contientBotteDeType(bataille.getType())) {
			return false;
		}
		
		if (bataille instanceof Attaque) {
			return peutAvancer();
		}

		if (bataille.equals(Cartes.FEU_VERT)) {
			return estDepotFeuVertAutorise();
		}

		return !pileBatailles.isEmpty() && pileBatailles.get(0).getType() == bataille.getType();
	}

	private boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}

	public List<Limite> getPileLimites() {
		return pileLimites;
	}

	public List<Bataille> getPileBatailles() {
		return pileBatailles;
	}

	public Collection<Borne> getCollectionBornes() {
		return collectionBornes;
	}

	public Set<Botte> getBottes() {
		return bottes;
	}

}
