package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenta;

/**
 * Veleuciliste Jave
 * 
 * @author hrvoj
 *
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
	
	private static final Logger log = LoggerFactory.getLogger(ObrazovnaUstanova.class);
	
	public VeleucilisteJave(List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
		super(predmeti, profesori, studenti, ispiti);
	}

	/**
	 * Racuna konacnu ocjenu za studenta
	 * 
	 * @param ispiti lista ispita
	 * @param ocjenaZavrsnogRadaPismeniDio ocjena pismenog djela zavrsnog rada
	 * @param ocjenaZavrsnogRadaObrana ocjena obrane zavrsnog rada
	 * @return konacnu ocjenu za studenta
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
			List<Ispit> ispiti, int ocjenaZavrsnogRadaPismeniDio, int ocjenaZavrsnogRadaObrana)
	{
		BigDecimal vrijednost = new BigDecimal(1);
		
		try {
			vrijednost = odrediProsjekOcjenaNaIspitima(ispiti)
					.add(new BigDecimal(ocjenaZavrsnogRadaPismeniDio))
					.add(new BigDecimal(ocjenaZavrsnogRadaObrana))
					.divide(new BigDecimal(4));
		
		} catch (NemoguceOdreditiProsjekStudenta e) {
			log.error("Nemoguce odrediti prosjek studenta: " + e.getLocalizedMessage());
			return new BigDecimal(1);
		}			
			
		return vrijednost;
	}

	/**
	 * Odreduje najuspjesnijeg studenta na godini
	 * 
	 * @param godina odabrana godina
	 * @return najuspjesnijeg studenta
	 *
	 */
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		Student najuspjesnijiStudent = null;
		BigDecimal maxProsjek = new BigDecimal(0);
		
		for (Student student : this.getStudenti()) {
			
			BigDecimal prosjek = new BigDecimal(0);
			try {
				prosjek = odrediProsjekOcjenaNaIspitima(
						filtrirajIspitePoStudentu(this.getIspiti(), student));
				
				if (prosjek.compareTo(maxProsjek) >= 0) {
					maxProsjek = prosjek;
					najuspjesnijiStudent = student;
				}
			} catch (NemoguceOdreditiProsjekStudenta e) {
				log.error(
						"Nemoguce odrediti prosjek studenta: " +
						student.getIme() + " " + student.getPrezime(), e);
				
				System.out.println(
						"Nemoguce odrediti prosjek studenta: " +
						student.getIme() + " " + student.getPrezime());
			
			} catch (Exception e) {	
				e.printStackTrace();
			}
			
		}
		
		return najuspjesnijiStudent;
	}

	
}
