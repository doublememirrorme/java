package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import hr.java.vjezbe.iznimke.NeispravnaOcjena;
import hr.java.vjezbe.utils.Unos;

/**
 * Predstavlja jedan ispit
 * 
 * @author hrvoj
 *
 */
public class Ispit {

	private Predmet predmet;
	private Student student;
	private Integer ocjena;
	private LocalDateTime datumIVrijeme;
	
	
	public Ispit(Predmet predmet, Student student, Integer ocjena, LocalDateTime datumIVrijeme) {
		super();
		this.predmet = predmet;
		this.student = student;
		this.ocjena = ocjena;
		this.datumIVrijeme = datumIVrijeme;
	}


	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getOcjena() {
		return ocjena;
	}

	public void setOcjena(Integer ocjena) {
		this.ocjena = ocjena;
	}

	public LocalDateTime getDatumIVrijeme() {
		return datumIVrijeme;
	}

	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		this.datumIVrijeme = datumIVrijeme;
	}


	/**
	 * Unos ispitnog roka
	 * 
	 * @param scanner predstavlja korisnicki unos
	 * @param redniBroj redni broj ispitnog roka
	 * @param predmeti lista predmeta
	 * @param profesori lista profesora
	 * @param studenti lista studenata
	 * @return novi ispit inicijaliziran prema unesenim podacima
	 */
	public static Ispit unosIspitnogRoka(
			Scanner scanner, int redniBroj, Predmet[] predmeti,
			Profesor[] profesori, Student[] studenti)
	throws NeispravnaOcjena
	{
		redniBroj++;
		
		System.out.println("\nUnesite " + redniBroj + ". ispitni rok ======");

		Predmet predmet = Predmet.odabirPredmeta(scanner, predmeti);
		Student student = Student.odabirStudenta(scanner, studenti);
		
		Integer ocjena = 1;
//		do {
//			ocjena = Unos.unos(scanner, "Unesite ocjenu na ispitu(1 - 5): ");			
//		
//		} while (ocjena < 1 || ocjena > 5);

		ocjena = Unos.unos(scanner, "Unesite ocjenu na ispitu(1 - 5): ");			
		if (ocjena < 1 || ocjena > 5) throw new NeispravnaOcjena("Neispravna ocjena");
		
		LocalDateTime datumIVrijeme = Unos.unosDatumaIVremena(
				scanner,
				"Unesite datum i vrijeme ispita u formatu (dd.MM.yyyyTHH:mm): ",
				"dd.MM.yyyy'T'HH:mm");
		
		return new Ispit(predmet, student, ocjena, datumIVrijeme);
	}

	/**
	 * Trazi izvrsne ispite i ispisuje ih u konzolu
	 * 
	 * @param ispitniRokovi lista ispita
	 * 
	 */
	public static void pronadiIzvrse(Ispit[] ispitniRokovi) {

		for (Ispit ispit : ispitniRokovi) {
			if (ispit.ocjena.equals(5))
				System.out.println(
					"Student " + ispit.student.getPrezime() + " " + ispit.student.getIme() +
					"je ostvario ocjenu 'izvrstan' na predmetu '" + ispit.predmet.getNaziv() + "'");
		}

	}
}
