package hr.java.vjezbe.entitet;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Predmet {

	private String sifra;
	private String naziv;
	private Integer brojEctsBodova;
	private Profesor nositelj;
	private Student[] student;
	
	
	public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj, Student[] student) {
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

	public Student[] getStudent() {
		return student;
	}

	public void setStudent(Student[] student) {
		this.student = student;
	}


	public static Predmet unosPredmeta(Scanner scanner, int redniBroj, Profesor[] profesori) {
		redniBroj++;
		
		System.out.println("\nUnesite " + redniBroj + ". predmet ======");

		String sifra = Predmet.unosSifrePredmeta(scanner);

		System.out.println("Unesite naziv predmeta: ");
		String naziv = scanner.nextLine();

		System.out.println("Unesite broj ECTS bodova za predmet '" + naziv + "': ");
		Integer brojEctsBodova = scanner.nextInt();
		scanner.nextLine();

		Profesor nositelj = Profesor.odabirProfesora(scanner, profesori);
		
		System.out.println("Unesite broj studenata za predmet '" + naziv + "': ");
		int brojStudenata = scanner.nextInt();
		scanner.nextLine();

		return new Predmet(sifra, naziv, brojEctsBodova, nositelj, new Student[brojStudenata]);
	}


	private static String unosSifrePredmeta(Scanner scanner) {
		
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);

		String sifra = "";
		
		do {
			System.out.println("Unesite sifru predmeta: ");
			sifra = scanner.nextLine();
					
			Matcher matcher = pattern.matcher(sifra);
			
			if (matcher.matches()) return sifra;
			
		} while (true);
	}


	public static Predmet odabirPredmeta(Scanner scanner, Predmet[] predmeti) {
		int odabir = 0;
		
		do {
			
			System.out.println("Odaberite predmet: ");
			
			for (int i = 0; i < predmeti.length; i++) {
				System.out.println((i + 1) + ". " + predmeti[i].getNaziv());
			}
			
			System.out.println("Odabir >> ");
			odabir = scanner.nextInt();
			scanner.nextLine();

			odabir -= 1;

		} while (odabir < 0 || odabir >= predmeti.length);
		
		return predmeti[odabir];
	}
	

	public static void ukupanBrojECTSaZa(Predmet[] predmeti) {
		int sum = 0;
		
		for (Predmet predmet : predmeti) {
			sum += predmet.getBrojEctsBodova();
		}

		System.out.println("\nUkupan broj ECTS bodova je: " + sum);
	}
}
