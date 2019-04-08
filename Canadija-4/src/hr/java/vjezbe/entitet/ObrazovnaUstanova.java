package hr.java.vjezbe.entitet;

import java.util.Scanner;

import hr.java.vjezbe.utils.Unos;

/**
 * Predstavlja jednu obrazovnu ustanovi
 * 
 * @author hrvoj
 *
 */
public abstract class ObrazovnaUstanova {
	
	private static final int BROJ_PROFESORA = 2;
	private static final int BROJ_PREDMETA = 2;
	private static final int BROJ_STUDENATA = 2;
	private static final int BROJ_ISPITNIH_ROKOVA = 2;

	private String naziv;
	Profesor[] profesori;
	Predmet[] predmeti;
	Student[] studenti;
	Ispit[] ispiti;

	
	public ObrazovnaUstanova(Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
		super();
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}

	
	/**
	 * Trazi najuspjesnijeg studenta na godini
	 * 
	 * @param godina predstavlja godinu
	 * @return najuspjesnijeg studenta na godini
	 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godina);
	
	/**
	 * Unos obrazovne ustanove 
	 * 
	 * @param scanner predstavlja unos
	 * @return novu obrazovnu ustanovu
	 */
	public static ObrazovnaUstanova unosObrazovneUstanove(Scanner scanner) {
		Profesor[] profesori = new Profesor[BROJ_PROFESORA];
		Predmet[] predmeti = new Predmet[BROJ_PREDMETA];
		Student[] studenti = new Student[BROJ_STUDENATA];
		Ispit[] ispiti = new Ispit[BROJ_ISPITNIH_ROKOVA];
		
		for (int i = 0; i < BROJ_PROFESORA; i++) {
			 profesori[i] = Profesor.unosProfesora(scanner, i);
		}
		
		for (int i = 0; i < BROJ_PREDMETA; i++) {
			predmeti[i] = Predmet.unosPredmeta(scanner, i, profesori);
		}
		
		for (int i = 0; i < BROJ_STUDENATA; i++) {
			studenti[i] = Student.unosStudenta(scanner, i);
		}
		
		for (int i = 0; i < BROJ_ISPITNIH_ROKOVA; i++) {
			ispiti[i] = Ispit.unosIspitnogRoka(scanner, i, predmeti, profesori, studenti);
		}

		
		int tipUstanove = Unos.unos(scanner,
				"Odaberite obrazovnu ustanovu za navedene podatke koju zelite unijeti:" +
				"\n1) Veleuciliate Jave" + 
				"\n2) Fakultet racunarstva ");
		
		System.out.println("Unesite naziv obrazovne ustanove: ");
		String naziv = scanner.nextLine();
		
		ObrazovnaUstanova ustanova = tipUstanove == 1
				? new VeleucilisteJave(predmeti, profesori, studenti, ispiti)
				: tipUstanove == 2
					? new FakultetRacunarstva(predmeti, profesori, studenti, ispiti)
					: null;
					
		ustanova.setNaziv(naziv);
		
		return ustanova;
	}
	
	
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Predmet[] getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Predmet[] predmeti) {
		this.predmeti = predmeti;
	}

	public Profesor[] getProfesori() {
		return profesori;
	}

	public void setProfesori(Profesor[] profesori) {
		this.profesori = profesori;
	}

	public Student[] getStudenti() {
		return studenti;
	}

	public void setStudenti(Student[] studenti) {
		this.studenti = studenti;
	}

	public Ispit[] getIspiti() {
		return ispiti;
	}

	public void setIspiti(Ispit[] ispiti) {
		this.ispiti = ispiti;
	}

}
