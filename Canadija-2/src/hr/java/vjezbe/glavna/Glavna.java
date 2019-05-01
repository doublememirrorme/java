package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.Scanner;

import hr.java.vjezbe.entitet.FakultetRacunarstva;
import hr.java.vjezbe.entitet.ObrazovnaUstanova;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.entitet.VeleucilisteJave;
import hr.java.vjezbe.entitet.Visokoskolska;

public class Glavna {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Unesite broj obrazovnih ustanova: ");
		ObrazovnaUstanova[] obrazovneUstanove = new ObrazovnaUstanova[scanner.nextInt()];
		scanner.nextLine();

		for (int i = 0; i < obrazovneUstanove.length; i++) {
			obrazovneUstanove[i] = ObrazovnaUstanova.unosObrazovneUstanove(scanner);
			
			for (Student student : obrazovneUstanove[i].getStudenti()) {
				String fullName = student.getIme() + " " + student.getPrezime();
				String tipStudija = obrazovneUstanove[i] instanceof VeleucilisteJave
						? "zavrsnog"
						: obrazovneUstanove[i] instanceof FakultetRacunarstva
							? "diplomskog"
							: null;
				
				System.out.println("Unesite ocjenu " + tipStudija + " rada za studenta: " + fullName);
				int ocjenaPismenog =  scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Unesite ocjenu obrane " + tipStudija + " rada za studenta: " + fullName);
				int ocjenaObrane =  scanner.nextInt();
				scanner.nextLine();
				
				BigDecimal konacnaOcjenaStudija = new BigDecimal(1);
				
				if (obrazovneUstanove[i] instanceof VeleucilisteJave) {
					konacnaOcjenaStudija =
						((VeleucilisteJave) obrazovneUstanove[i])
						.izracunajKonacnuOcjenuStudijaZaStudenta(
							((Visokoskolska) obrazovneUstanove[i])
								.filtrirajIspitePoStudentu(obrazovneUstanove[i].getIspiti(), student),
							ocjenaPismenog,
							ocjenaObrane);
				}
				
				if (obrazovneUstanove[i] instanceof FakultetRacunarstva) {
					konacnaOcjenaStudija =
						((FakultetRacunarstva) obrazovneUstanove[i])
						.izracunajKonacnuOcjenuStudijaZaStudenta(
							((Visokoskolska) obrazovneUstanove[i])
								.filtrirajIspitePoStudentu(obrazovneUstanove[i].getIspiti(), student),
							ocjenaPismenog,
							ocjenaObrane);
				}
			
				System.out.println("Koncana ocjena studija za studenta " + fullName + " je " + konacnaOcjenaStudija);
			}
			
			
			Student najuspjesnijiStudent = null;
			if (obrazovneUstanove[i] instanceof VeleucilisteJave) {
				najuspjesnijiStudent = ((VeleucilisteJave) obrazovneUstanove[i]).odrediNajuspjesnijegStudentaNaGodini(2018);
			}
			
			else if (obrazovneUstanove[i] instanceof FakultetRacunarstva) {
				najuspjesnijiStudent = ((FakultetRacunarstva) obrazovneUstanove[i]).odrediNajuspjesnijegStudentaNaGodini(2018);				
			}
			
			
			if (najuspjesnijiStudent != null) {
				System.out.println(
						"Najbolji student 2018. godine je " +
								najuspjesnijiStudent.getIme() + " " + najuspjesnijiStudent.getPrezime() + " " +
								"JMBAG: " + najuspjesnijiStudent.getJmbag());				
			}
			
			if (obrazovneUstanove[i] instanceof FakultetRacunarstva) {
				Student studentZaRektorskuNagradu = ((FakultetRacunarstva) obrazovneUstanove[i]).odrediStudentaZaRektorovuNagradu();

				System.out.println(
						"Student koji je osvojio rektorovu nagradu je: " +
						studentZaRektorskuNagradu.getIme() + " " + studentZaRektorskuNagradu.getPrezime() +
						"JMBAG: " + studentZaRektorskuNagradu.getJmbag());
			}
		}

	}
}
