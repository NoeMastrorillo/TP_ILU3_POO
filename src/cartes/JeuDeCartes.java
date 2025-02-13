package cartes;

public class JeuDeCartes {
	private Configuration[] typesDeCartes = {
			new Configuration(new Borne(25), 10),
			new Configuration(new Borne(50), 10),
			new Configuration(new Borne(75), 10),
			new Configuration(new Borne(100), 12),
			new Configuration(new Borne(200), 4),
			new Configuration(new Parade(Type.FEU), 14),
			new Configuration(new FinLimite(), 6),
			new Configuration(new Parade(Type.ESSENCE), 6),
			new Configuration(new Parade(Type.CREVAISON), 6),
			new Configuration(new Parade(Type.ACCIDENT), 6),
			new Configuration(new Attaque(Type.FEU), 5),
			new Configuration(new DebutLimite(), 4),
			new Configuration(new Attaque(Type.ESSENCE), 3),
			new Configuration(new Attaque(Type.CREVAISON), 3),
			new Configuration(new Attaque(Type.ACCIDENT), 3),
			new Configuration(new Botte(Type.FEU), 1),
			new Configuration(new Botte(Type.ESSENCE), 1),
			new Configuration(new Botte(Type.CREVAISON), 1),
			new Configuration(new Botte(Type.ACCIDENT), 1),
	};
	
	public Carte[] donnerCartes() {
		int nbCartes = 0;
		for (Configuration config: typesDeCartes) {
			nbCartes += config.getNbExemplaires();
		}
		
		Carte[] cartes = new Carte[nbCartes];
		int i = 0;
		for (Configuration config: typesDeCartes) {
			for (int j = 0; j < config.getNbExemplaires(); j++) {
				cartes[i+j] = config.getCarte();
			}
			i += config.getNbExemplaires();
		}
		
		return cartes;
	}

	public String affichageJeuDeCartes() {
		StringBuilder sb = new StringBuilder("JEU :\n\n");
		for (Configuration config: typesDeCartes) {
			sb.append(config.getNbExemplaires())
				.append(' ')
				.append(config.getCarte())
				.append('\n');
		}
		return sb.toString();
	}
	
	private static class Configuration {
		private Carte carte;
		private int nbExemplaires;
		
		public Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}
}
