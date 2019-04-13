package hr.java.vjezbe.entitet;

public enum Ocjena {
	NEDOVOLJAN(1),
	DOVOLJAN(2),
	DOBAR(3),
	VRLO_DOBAR(4),
	IZVRSTAN(5);
	
	private int ocjena;

	private Ocjena(int ocjena) {
		this.ocjena = ocjena;
	}

	public int getOcjena() {
		return ocjena;
	}
}
