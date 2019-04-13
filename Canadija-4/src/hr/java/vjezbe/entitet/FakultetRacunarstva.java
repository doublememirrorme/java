package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenta;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

/**
 * Predstavlja fakultet racunarstva
 * 
 * @author hrvoj
 *
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

	private static final Logger log = LoggerFactory.getLogger(FakultetRacunarstva.class);

	/**
	 * Inicijalizira novi objekt
	 * 
	 * @param predmeti predstavlja sve predmete 
	 * @param profesori predstavlja sve profesore
	 * @param studenti predstavlja sve studente
	 * @param ispiti predstavlja sve ispite
	 */
	public FakultetRacunarstva(List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
		super(predmeti, profesori, studenti, ispiti);
	}

	/**
	 * Racuna konacnu ocjenu studija za studenta
	 * 
	 * @param ispiti lista ispita
	 * @param ocjenaDiplomskogRadaPismeniDio ocjena pismenog djela zavrsnog rada
	 * @param ocjenaDiplomskogRadaObrana ocjena obrane diplomskog rada
	 * 
	 * @return konacnu ocjenu studija za studenta
	 *
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaDiplomskogRadaPismeniDio,
			int ocjenaDiplomskogRadaObrana) {

		BigDecimal vrijednost = new BigDecimal(1);

		try {
			vrijednost = odrediProsjekOcjenaNaIspitima(ispiti)
					.multiply(new BigDecimal(3))
					.add(new BigDecimal(ocjenaDiplomskogRadaPismeniDio))
					.add(new BigDecimal(ocjenaDiplomskogRadaObrana))
					.divide(new BigDecimal(5));

		} catch (NemoguceOdreditiProsjekStudenta e) {
			log.error("Nemoguce odrediti prosjek studenta: " + e.getLocalizedMessage());
			return new BigDecimal(1);
		}

		return vrijednost;
	}

	/**
	 * Odreduje studenta koji je osvojio rektorovu nagradu
	 * 
	 * @return studenta koji je osvojio rektorovu nagradu
	 * @throws PostojiViseNajmladjihStudenataException ukoliko postoji vise najmladih studenata
	 *
	 */
	@Override
	public Student odrediStudentaZaRektorovuNagradu() throws PostojiViseNajmladjihStudenataException {
		Student najuspjesnijiStudent = null;
		BigDecimal maxProsjek = new BigDecimal(0);
//		List<Student> najuspjesnijiStudenti = new Student[1];
		List<Student> najuspjesnijiStudenti = new ArrayList<>();

		for (Student student : this.getStudenti()) {
			BigDecimal prosjek = new BigDecimal(0);

			try {
				prosjek = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(this.getIspiti(), student));

			} catch (NemoguceOdreditiProsjekStudenta e) {
				log.error("Nemoguce odrediti prosjek studenta: " + student.getIme() + " " + student.getPrezime(), e);

				System.out.println(
						"Nemoguce odrediti prosjek studenta: " + student.getIme() + " " + student.getPrezime());
			}

			if (prosjek.compareTo(maxProsjek) >= 0) {
				if (najuspjesnijiStudent != null) {
					if (student.getDatumRodenja().isAfter(najuspjesnijiStudent.getDatumRodenja())) {
						maxProsjek = prosjek;
						najuspjesnijiStudent = student;
						najuspjesnijiStudenti.clear();
						najuspjesnijiStudenti.add(najuspjesnijiStudent);
					}

					if (student.getDatumRodenja().equals(najuspjesnijiStudent.getDatumRodenja())) {
						najuspjesnijiStudenti.add(student);
					}

				}
			}
		}
		
		if (najuspjesnijiStudenti.size() > 1) {
			String listaStudenata = "";
			
			for (Student student : najuspjesnijiStudenti) {
				if (listaStudenata.equals(""))
					listaStudenata += student.getIme() + " " + student.getPrezime();
					
				else 
					listaStudenata += ", " + student.getIme() + " " + student.getPrezime();
			}
			
			log.error("Postoji Vise Najmladjih Studenata: " + listaStudenata);
			System.out.println("Postoji Vise Najmladjih Studenata: " + listaStudenata);
			throw new PostojiViseNajmladjihStudenataException(listaStudenata);
		}

		return najuspjesnijiStudent;
	}

	/**
	 * Odreduje najuspjesnijeg studenta na godini
	 * 
	 * @param godina koje godine
	 * @return najuspjesnijeg studenta
	 *
	 */
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		Student najuspjesnijiStudent = null;
		int brojIzvrsnihOcjenaNajuspjesnijegStudenta = 0;

		for (Student student : this.getStudenti()) {
			int brojacIzvrsnihOcjena = 0;

			for (Ispit ispit : filtrirajIspitePoStudentu(this.getIspiti(), student)) {
				if (ispit.getOcjena().equals(Ocjena.IZVRSTAN.getOcjena())) {
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
