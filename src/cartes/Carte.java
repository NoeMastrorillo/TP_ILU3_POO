package cartes;

public abstract class Carte {
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj.getClass() == getClass();
	}
	
	// TODO: faire les hashcode

}
