package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private static Random random = new Random();
	
	private GestionCartes() {
	}
	
	public static <T> T extraire(List<T> liste) {
		int index = random.nextInt(liste.size());
		
		// Version ListIterator
		ListIterator<T> it = liste.listIterator();
		T elem = null;
		for (int i = 0; i <= index; i++) {
			elem = it.next();
		}
		it.remove();
		return elem;
		
		// Version directe
//		T elem = liste.get(index);
//		liste.remove(index);
//		return elem;
	}
	
	public static <T> List<T> melanger(List<T> liste) {
		List<T> newListe = new ArrayList<>();
		int taille = liste.size();
		for (int i = 0; i < taille; i++) {
			newListe.add(extraire(liste));
		}
		return newListe;
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		for (T elem : l1) {
			if (Collections.frequency(l1, elem) != Collections.frequency(l2, elem)) {
				return false;
			}
		}
		
		for (T elem : l2) {
			if (Collections.frequency(l2, elem) != Collections.frequency(l1, elem)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste) {
		List<T> newListe = new ArrayList<>();
		for (T elem : liste) {
			if (!newListe.contains(elem)) {
				for (int i = 0; i < Collections.frequency(liste, elem); i++) {
					newListe.add(elem);
				}
			}
		}
		return newListe;
	}
	
	public static <T> boolean verifierRassemblement(List<T> liste) {
		int i = 0;
		T prev = null;
		
		for (ListIterator<T> it = liste.listIterator(); it.hasNext(); i++) {
			T elem = it.next();
			if (prev != null && prev != elem && trouverElementFinListe(liste, i, prev)) {
				return false;
			}
			prev = elem;
		}
		
		return true;
	}

	private static <T> boolean trouverElementFinListe(List<T> liste, int i, T elem) {
		for (ListIterator<T> it = liste.listIterator(i); it.hasNext();) {
			T elem2 = it.next();
			if (elem2 == elem) {
				return true;
			}
		}
		return false;
	}
}
