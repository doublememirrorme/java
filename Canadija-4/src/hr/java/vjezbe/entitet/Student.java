package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.util.List;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumRodenja == null) ? 0 : datumRodenja.hashCode());
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (datumRodenja == null) {
			if (other.datumRodenja != null)
				return false;
		} else if (!datumRodenja.equals(other.datumRodenja))
			return false;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
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
	public static Student odabirStudenta(Scanner scanner, List<Student> studenti) {
		int odabir = 0;
		
		do {
			
			System.out.println("Odaberite studenta: ");
			
			for (int i = 0; i < studenti.size(); i++) {
				System.out.println(
						(i + 1) + ". " +
						studenti.get(i).getPrezime() + " " + studenti.get(i).getIme()
				);
			}
			
			odabir = Unos.unos(scanner, "Odabir >> ");

			odabir -= 1;

		} while (odabir < 0 || odabir >= studenti.size());
		
		return studenti.get(odabir);
	}
}
