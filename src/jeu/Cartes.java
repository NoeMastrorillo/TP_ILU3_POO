package jeu;

import cartes.Attaque;
import cartes.Botte;
import cartes.Parade;
import cartes.Type;

public interface Cartes {
	public static Botte PRIORITAIRE = new Botte(Type.FEU);
	public static Attaque FEU_ROUGE = new Attaque(Type.FEU);
	public static Parade FEU_VERT = new Parade(Type.FEU);
}
