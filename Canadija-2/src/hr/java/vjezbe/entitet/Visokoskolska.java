package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Arrays;

public interface Visokoskolska {

	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaZavrsnogRadaPismeniDio, int ocjenaZavrsnogRadaObrana);
	
	default public BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti) {
		
		Ispit[] polozeniIspiti = filtrirajPolozeneIspite(ispiti);
		BigDecimal ocjene = new BigDecimal(0);
		
		for (Ispit ispit : polozeniIspiti) {
			ocjene.add(new BigDecimal(ispit.getOcjena()));
		}
		
		return ocjene.divide(new BigDecimal(polozeniIspiti.length > 0 ? polozeniIspiti.length : 1));
	}
	
	private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti) {
		return Arrays
				.stream(ispiti)
				.filter(ispit -> ispit.getOcjena() > 1)
				.toArray(Ispit[]::new);
	};
	
	default public Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {
		return Arrays
				.stream(ispiti)
				.filter(ispit -> ispit.getStudent().getJmbag().equals(student.getJmbag()))
				.toArray(Ispit[]::new);
	}
}
