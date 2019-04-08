package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
	
	public VeleucilisteJave(Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
		super(predmeti, profesori, studenti, ispiti);
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
			Ispit[] ispiti, int ocjenaZavrsnogRadaPismeniDio, int ocjenaZavrsnogRadaObrana)
	{
		return odrediProsjekOcjenaNaIspitima(ispiti)
				.add(new BigDecimal(ocjenaZavrsnogRadaPismeniDio))
				.add(new BigDecimal(ocjenaZavrsnogRadaObrana))
				.divide(new BigDecimal(4));
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		Student najuspjesnijiStudent = null;
		BigDecimal maxProsjek = new BigDecimal(0);
		
		for (Student student : this.getStudenti()) {
			BigDecimal prosjek = odrediProsjekOcjenaNaIspitima(
					filtrirajIspitePoStudentu(this.getIspiti(), student));
			
			if (prosjek.compareTo(maxProsjek) >= 0) {
				maxProsjek = prosjek;
				najuspjesnijiStudent = student;
			}
		}
		
		return najuspjesnijiStudent;
	}

	
}
