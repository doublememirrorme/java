package hr.java.vjezbe.entitet;

import java.util.Scanner;

public class Profesor {

	private static final String[] TITULE = {
			"dipl. ing",
			"dr."
	};

	private String sifra;
	private String ime;
	private String prezime;
	private String titula;
	
	
	public Profesor(String sifra, String ime, String prezime, String titula) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.titula = titula;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
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

		String titula = Profesor.unostTitule(scanner);

		return new Profesor(sifra, ime, prezime, titula);
	}

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
			
			System.out.println("Odabir >> ");
			odabir = scanner.nextInt();
			scanner.nextLine();

			odabir =  odabir - 1;

		} while (odabir < 0 || odabir >= profesori.length);
		
		return profesori[odabir];
	}
	
	private static String unostTitule(Scanner scanner) {
		String titulaZaProvjeru = "";
		
		do {
			System.out.println("Unesite titulu profesora: ");
			titulaZaProvjeru = scanner.nextLine();

			for (String titula : TITULE) {
				if (titulaZaProvjeru.equals(titula))
					return titulaZaProvjeru;
			}
		} while (true);
	}
}
