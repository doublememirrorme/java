package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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


	public static Ispit unosIspitnogRoka(
			Scanner scanner, int redniBroj, Predmet[] predmeti,
			Profesor[] profesori, Student[] studenti)
	{
		redniBroj++;
		
		System.out.println("\nUnesite " + redniBroj + ". ispitni rok ======");

		Predmet predmet = Predmet.odabirPredmeta(scanner, predmeti);
		Student student = Student.odabirStudenta(scanner, studenti);
		
		Integer ocjena = 1;
		do {
			System.out.println("Unesite ocjenu na ispitu(1 - 5): ");
			ocjena = scanner.nextInt();
			scanner.nextLine();
			
		} while (ocjena < 1 || ocjena > 5);

		System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyyTHH:mm): ");
		String datumIVrijemeString = scanner.nextLine();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm");
		LocalDateTime datumIVrijeme = LocalDateTime.parse(datumIVrijemeString, formatter);
		
		return new Ispit(predmet, student, ocjena, datumIVrijeme);
	}

	public static void pronadiIzvrse(Ispit[] ispitniRokovi) {

		for (Ispit ispit : ispitniRokovi) {
			if (ispit.ocjena == 5)
				System.out.println(
					"Student " + ispit.student.getPrezime() + " " + ispit.student.getIme() +
					"je ostvario ocjenu 'izvrstan' na predmetu '" + ispit.predmet.getNaziv() + "'");
		}

	}
}
