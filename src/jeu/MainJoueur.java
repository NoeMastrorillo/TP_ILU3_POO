package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> main = new ArrayList<>();
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		boolean presente = main.remove(carte);
		assert presente;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Carte carte : main) {
			sb.append("\n- ");
			sb.append(carte.toString());
		}
		return sb.toString();
	}

	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();
	}
}
