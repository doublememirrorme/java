package hr.java.vjezbe.entitet;

import java.util.Scanner;

import hr.java.vjezbe.utils.Unos;

/**
 * Predstavlja jednog profesora
 * 
 * @author hrvoj
 *
 */
public class Profesor extends Osoba {

	private String sifra;
	private String titula;
	
	
	public Profesor(String sifra, String ime, String prezime, String titula) {
		super(ime, prezime);
		this.sifra = sifra;
		this.titula = titula;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}
	
	
	public static Profesor unosProfesora(Scanner scanner, int redniBroj) {
		redniBroj++;
		
		System.out.println("\nUnesite " + redniBroj + ". profesora ======");

		System.out.println("Unesite sifru profesora: ");
		String sifra = scanner.nextLine();

		System.out.println("Unesite ime profesora: ");
		String ime = scanner.nextLine();

		System.out.println("Unesite prezime profesora: ");
		String prezime = scanner.nextLine();

		System.out.println("Unesite titulu profesora: ");
		String titula = scanner.nextLine();

		return new Profesor(sifra, ime, prezime, titula);
	}
	
	/**
	 * Odabir profesora iz liste profesora
	 * 
	 * @param scanner korisnicki unos
	 * @param profesori lista profesora
	 * @return odabranog profesora
	 */
	public static Profesor odabirProfesora(Scanner scanner, Profesor[] profesori) {
		int odabir = 0;
		
		do {
			
			System.out.println("Odaberite profesora: ");
			
			for (int i = 0; i < profesori.length; i++) {
				System.out.println(
						(i + 1) + ". " +
						profesori[i].getPrezime() + " " + profesori[i].getPrezime()
				);
			}
			
			odabir = Unos.unos(scanner, "Odabir >> ");

			odabir =  odabir - 1;

		} while (odabir < 0 || odabir >= profesori.length);
		
		return profesori[odabir];
	}
}
