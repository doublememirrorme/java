package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import hr.java.vjezbe.utils.Unos;

/**
 * Predstavlja studenta
 * 
 * @author hrvoj
 *
 */
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


	/**
	 * Unos studenta
	 * 
	 * @param scanner korisnicki unos
	 * @param redniBroj redni broj studenta
	 * @return unesenog studenta
	 */
	public static Student unosStudenta(Scanner scanner, int redniBroj) {
		redniBroj++;
		
		System.out.println("\nUnesite " + redniBroj + ". studenta ======");

		System.out.println("Unesite ime studenta: ");
		String ime = scanner.nextLine();

		System.out.println("Unesite prezime studenta: ");
		String prezime = scanner.nextLine();

		LocalDate datumRodenja = Unos.unosDatuma(
				scanner,
				"Unesite datum rodenja studenta '" +
				prezime + " " + ime +
				"' u formatu(dd.MM.yyyy): ",
				"dd.MM.yyyy");
		
		System.out.println("Unesite jmbag studenta: ");
		String jmbag = scanner.nextLine();
		
		return new Student(ime, prezime, jmbag, datumRodenja);
	}


	/**
	 * Odabir studenata iz liste studenata
	 * 
	 * @param scanner korisnicki unos
	 * @param studenti lista studenata
	 * @return odabrani student
	 */
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
			
			odabir = Unos.unos(scanner, "Odabir >> ");

			odabir -= 1;

		} while (odabir < 0 || odabir >= studenti.length);
		
		return studenti[odabir];
	}
}
