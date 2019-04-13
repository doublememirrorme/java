package hr.java.vjezbe.entitet;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import hr.java.vjezbe.utils.Unos;

/**
 * Predstavlja jedan predmet
 * 
 * @author hrvoj
 *
 */
public class Predmet {

	private String sifra;
	private String naziv;
	private Integer brojEctsBodova;
	private Profesor nositelj;
	private Set<Student> student;
	
	
	public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj, Set<Student> student) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
		this.student = student;
	}


	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}

	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}

	public Profesor getNositelj() {
		return nositelj;
	}

	public void setNositelj(Profesor nositelj) {
		this.nositelj = nositelj;
	}

	public Set<Student> getStudent() {
		return student;
	}

	public SortedSet<Student> getSortedStudent() {
		return new TreeSet<>(
				Comparator
					.comparing(Student::getPrezime)
					.thenComparing(Student::getIme));
	}
	
	public void setStudent(Set<Student> student) {
		this.student = student;
	}


	/**
	 * Unos predmeta
	 * 
	 * @param scanner korisnicki unos
	 * @param redniBroj redni broj predmeta
	 * @param profesori lista profesora
	 * @return kreirani predmet
	 */
	public static Predmet unosPredmeta(Scanner scanner, int redniBroj, List<Profesor> profesori) {
		redniBroj++;
		
		System.out.println("\nUnesite " + redniBroj + ". predmet ======");

		System.out.println("Unesite sifru predmeta: ");
		String sifra = scanner.nextLine();

		System.out.println("Unesite naziv predmeta: ");
		String naziv = scanner.nextLine();

		Integer brojEctsBodova = Unos.unos(
				scanner, "Unesite broj ECTS bodova za predmet '" + naziv + "': ");

		Profesor nositelj = Profesor.odabirProfesora(scanner, profesori);
		

		return new Predmet(sifra, naziv, brojEctsBodova, nositelj, new HashSet<Student>());
	}


	/**
	 * Odabir predmeta iz liste
	 * 
	 * @param scanner korisnicki unos
	 * @param predmeti lista predmeta
	 * @return odabrani predmet
	 */
	public static Predmet odabirPredmeta(Scanner scanner, List<Predmet> predmeti) {
		int odabir = 0;
		
		do {
			
			System.out.println("Odaberite predmet: ");
			
			for (int i = 0; i < predmeti.size(); i++) {
				System.out.println((i + 1) + ". " + predmeti.get(i).getNaziv());
			}
			
			odabir = Unos.unos(scanner, "Odabir >> ");

			odabir -= 1;

		} while (odabir < 0 || odabir >= predmeti.size());
		
		return predmeti.get(odabir);
	}


}
