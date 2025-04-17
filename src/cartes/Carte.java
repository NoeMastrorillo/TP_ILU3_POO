package cartes;

public abstract class Carte {
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj.getClass() == getClass();
	}
	
	@Override
	public int hashCode() {
		return 31 * getClass().hashCode();
	}

}
