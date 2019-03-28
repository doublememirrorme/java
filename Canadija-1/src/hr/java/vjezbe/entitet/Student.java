package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Student {

	private String ime;
	private String prezime;
	private String jmbag;
	private LocalDate datumRodenja;
	
	
	public Student(String ime, String prezime, String jmbag, LocalDate datumRodenja) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbag = jmbag;
		this.datumRodenja = datumRodenja;
	}


	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
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

		System.out.println("Unesite prezime studenta: ");
		String prezime = scanner.nextLine();

		System.out.println(
				"Unesite datum rodenja studenta '" +
				prezime + " " + ime +
				"' u formatu(dd.MM.yyyy): ");
		String datumRodenjaString = scanner.nextLine();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate datumRodenja = LocalDate.parse(datumRodenjaString, formatter);

		System.out.println("Unesite jmbag studenta: ");
		String jmbag = scanner.nextLine();
		
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
}
