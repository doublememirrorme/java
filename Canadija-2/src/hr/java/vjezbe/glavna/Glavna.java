package hr.java.vjezbe.glavna;

import java.util.Scanner;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

public class Glavna {

	private static final int BROJ_PROFESORA = 2;
	private static final int BROJ_PREDMETA = 3;
	private static final int BROJ_STUDENATA = 2;
	private static final int BROJ_ISPITNIH_ROKOVA = 1;
	
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Profesor[] profesori = new Profesor[BROJ_PROFESORA];
		Predmet[] predmeti = new Predmet[BROJ_PREDMETA];
		Student[] studenti = new Student[BROJ_STUDENATA];
		Ispit[] ispitniRokovi = new Ispit[BROJ_ISPITNIH_ROKOVA];

		for (int i = 0; i < BROJ_PROFESORA; i++) {
			profesori[i] = Profesor.unosProfesora(scanner, i);
		}
		
		for (int i = 0; i < BROJ_PREDMETA; i++) {
			predmeti[i] = Predmet.unosPredmeta(scanner, i, profesori);
		}
		
		for (int i = 0; i < BROJ_STUDENATA; i++) {
			studenti[i] = Student.unosStudenta(scanner, i);
		}
		
		for (int i = 0; i < BROJ_ISPITNIH_ROKOVA; i++) {
			ispitniRokovi[i] = Ispit.unosIspitnogRoka(scanner, i, predmeti, profesori, studenti);
		}
		
		Ispit.pronadiIzvrse(ispitniRokovi);
	}

}
