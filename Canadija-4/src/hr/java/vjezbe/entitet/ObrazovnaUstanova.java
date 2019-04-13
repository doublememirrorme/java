package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.java.vjezbe.sortiranje.StudentSorter;
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
	List<Profesor> profesori;
	List<Predmet> predmeti;
	List<Student> studenti;
	List<Ispit> ispiti;

	
	public ObrazovnaUstanova(
			List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
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
		List<Profesor> profesori = new ArrayList<>();
		List<Predmet> predmeti = new ArrayList<>();
		List<Student> studenti = new ArrayList<>();
		List<Ispit> ispiti = new ArrayList<>();
		
		for (int i = 0; i < BROJ_PROFESORA; i++) {
			 profesori.add(Profesor.unosProfesora(scanner, i));
		}
		
		for (int i = 0; i < BROJ_PREDMETA; i++) {
			predmeti.add(Predmet.unosPredmeta(scanner, i, profesori));
		}
		
		for (int i = 0; i < BROJ_STUDENATA; i++) {
			studenti.add(Student.unosStudenta(scanner, i));
		}
		
		for (int i = 0; i < BROJ_ISPITNIH_ROKOVA; i++) {
			ispiti.add(Ispit.unosIspitnogRoka(scanner, i, predmeti, profesori, studenti));
		}
		
		predmeti.forEach(predmet -> {
			System.out.println(predmet.getStudent().isEmpty()
					? "Nema studenata upisanih na predmet '" + predmet.getNaziv()
					: "Studenti upisani na predmet '" + predmet.getNaziv() + "' su:");
			
			if (!predmet.getStudent().isEmpty())
				predmet
					.getSortedStudent()
					.forEach(student -> System.out.println(student.getIme() + " " + student.getPrezime()));
		});
		

		
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

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public List<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	public List<Ispit> getIspiti() {
		return ispiti;
	}

	public void setIspiti(List<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

}
