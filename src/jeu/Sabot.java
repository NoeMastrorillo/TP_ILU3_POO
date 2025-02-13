package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private int nbCartes;
	private Carte[] cartes;
	private int nbOperations = 0;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		cartes[nbCartes] = carte;
		nbCartes++;
		nbOperations++;
	}
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();
		Carte carte = it.next();
		it.remove();
		return carte;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private int nbOperationsReference = nbOperations;
		private boolean nextEffectue = false;
		
		private void verifierConcurrence() {
			if (nbOperations != nbOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
		
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		public Carte next() {
			if (hasNext()) {
				nextEffectue = true;
				return cartes[indiceIterateur++];
			} else {
				throw new NoSuchElementException();
			}
		}
		
		public void remove() {
			if (!nextEffectue) {
				throw new IllegalStateException();
			}
			
			for (int i = indiceIterateur-1; i < nbCartes-1; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nbOperations++;
			nbOperationsReference++;
		}
	}
}
