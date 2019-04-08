package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Student extends Osoba {

	private String jmbag;
	private LocalDate datumRodenja;
	
	
	public Student(String ime, String prezime, String jmbag, LocalDate datumRodenja) {
		super(ime, prezime);
		this.jmbag = jmbag;
		this.datumRodenja = datumRodenja;
	}
	

	public String getJmbag() {
		return jmbag;
	}

	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}

	public LocalDate getDatumRodenja() {
		return datumRodenja;
	}

	public void setDatumRodenja(LocalDate datumRodenja) {
		this.datumRodenja = datumRodenja;
	}


	public static Student unosStudenta(Scanner scanner, int redniBroj) {
		redniBroj++;
		
		System.out.println("\nUnesite " + redniBroj + ". studenta ======");

		System.out.println("Unesite ime studenta: ");
		String ime = scanner.nextLine();
		if (ime == null || ime.equals("")) return null;

		System.out.println("Unesite prezime studenta: ");
		String prezime = scanner.nextLine();
		if (prezime == null || prezime.equals("")) return null;

		System.out.println(
				"Unesite datum rodenja studenta '" +
				prezime + " " + ime +
				"' u formatu(dd.MM.yyyy): ");
		String datumRodenjaString = scanner.nextLine();
		if (datumRodenjaString == null || datumRodenjaString.equals("")) return null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate datumRodenja = LocalDate.parse(datumRodenjaString, formatter);

		System.out.println("Unesite jmbag studenta: ");
		String jmbag = scanner.nextLine();
		if (jmbag == null || datumRodenjaString.equals("")) return null;
		
		return new Student(ime, prezime, jmbag, datumRodenja);
	}


	public static Student odabirStudenta(Scanner scanner, Student[] studenti) {
		int odabir = 0;
		
		do {
			
			System.out.println("Odaberite studenta: ");
			
			for (int i = 0; i < studenti.length; i++) {
				System.out.println(
						(i + 1) + ". " +
						studenti[i].getPrezime() + " " + studenti[i].getIme()
				);
			}
			
			System.out.println("Odabir >> ");
			odabir = scanner.nextInt();
			scanner.nextLine();

			odabir -= 1;

		} while (odabir < 0 || odabir >= studenti.length);
		
		return studenti[odabir];
	}


	public static void sortiraj(Student[] studenti) {
		Arrays.sort(studenti, (s1, s2) -> s1.getIme().compareTo(s2.getIme()));
		Arrays.sort(studenti, (s1, s2) -> s1.getPrezime().compareTo(s2.getPrezime()));
	}
}
