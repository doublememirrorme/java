package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

	public FakultetRacunarstva(Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
		super(predmeti, profesori, studenti, ispiti);
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaDiplomskogRadaPismeniDio,
			int ocjenaDiplomskogRadaObrana) {
		
		return odrediProsjekOcjenaNaIspitima(ispiti)
				.multiply(new BigDecimal(3))
				.add(new BigDecimal(ocjenaDiplomskogRadaPismeniDio))
				.add(new BigDecimal(ocjenaDiplomskogRadaObrana))
				.divide(new BigDecimal(5));
	}

	@Override
	public Student odrediStudentaZaRektorovuNagradu() {
		Student najuspjesnijiStudent = null;
		BigDecimal maxProsjek = new BigDecimal(0);
		
		for (Student student : this.getStudenti()) {
			BigDecimal prosjek = odrediProsjekOcjenaNaIspitima(
					filtrirajIspitePoStudentu(this.getIspiti(), student));
			
			if (prosjek.compareTo(maxProsjek) >= 0) {
				if (najuspjesnijiStudent == null ||
					student.getDatumRodenja().isAfter(najuspjesnijiStudent.getDatumRodenja()))
				{
					maxProsjek = prosjek;
					najuspjesnijiStudent = student;
					
				}
			}
		}
		
		return najuspjesnijiStudent;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		Student najuspjesnijiStudent = null;
		int brojIzvrsnihOcjenaNajuspjesnijegStudenta = 0;
		
		for (Student student : this.getStudenti()) {
			int brojacIzvrsnihOcjena = 0;
			
			for (Ispit ispit : filtrirajIspitePoStudentu(this.getIspiti(), student)) {
				if (ispit.getOcjena() == 5) {
					brojacIzvrsnihOcjena++;
				}
			}
			
			if (brojacIzvrsnihOcjena > brojIzvrsnihOcjenaNajuspjesnijegStudenta) {
				najuspjesnijiStudent = student;
			}
		}
		
		return najuspjesnijiStudent;
	}

}
