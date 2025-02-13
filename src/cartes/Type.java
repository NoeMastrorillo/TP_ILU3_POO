package cartes;

public enum Type {
	FEU("Feu rouge", "Feu vert", "Véhicule prioritaire"),
	ESSENCE("Panne d'essence", "Essence", "Citerne d'essence"),
	CREVAISON("Crevaison", "Roue de secours", "Increvable"),
	ACCIDENT("Accident", "Réparations", "As du volant");
	
	String attaque;
	String parade;
	String botte;
	
	private Type(String attaque, String parade, String botte) {
		this.attaque = attaque;
		this.parade = parade;
		this.botte = botte;
	}

	public String getAttaque() {
		return attaque;
	}

	public String getParade() {
		return parade;
	}

	public String getBotte() {
		return botte;
	}
	
}
