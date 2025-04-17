package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}

	public int getKm() {
		return km;
	}

	@Override
	public String toString() {
		return km + "KM";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && ((Borne) obj).getKm() == km;
	}
	
	@Override
	public int hashCode() {
		return 23 * super.hashCode() * km;
	}
}
