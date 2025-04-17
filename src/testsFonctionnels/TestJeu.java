package testsFonctionnels;

import jeu.Jeu;
import jeu.Joueur;

public class TestJeu {
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Joueur joueur1 = new Joueur("Toto");
		Joueur joueur2 = new Joueur("Titi");
		Joueur joueur3 = new Joueur("Tutu");
		jeu.inscrire(joueur1, joueur2, joueur3);
		jeu.distributerCartes();
		System.out.println(jeu.lancer());
	}
}
