package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.FakultetRacunarstva;
import hr.java.vjezbe.entitet.ObrazovnaUstanova;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.entitet.VeleucilisteJave;
import hr.java.vjezbe.entitet.Visokoskolska;
import hr.java.vjezbe.utils.Unos;

public class Glavna {
	
	public static final Logger log = LoggerFactory.getLogger(Glavna.class);
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
				
		final int brojObrazovnihUstanova = Unos.unos(scanner, "Unesite broj obrazovnih ustanova: ");
		List<ObrazovnaUstanova> obrazovneUstanove = new ArrayList<>();
		

		for (int i = 0; i < brojObrazovnihUstanova; i++) {
			obrazovneUstanove.add(ObrazovnaUstanova.unosObrazovneUstanove(scanner));
			
			for (Student student : obrazovneUstanove.get(i).getStudenti()) {
				String fullName = student.getIme() + " " + student.getPrezime();
				String tipStudija = obrazovneUstanove.get(i) instanceof VeleucilisteJave
						? "zavrsnog"
						: obrazovneUstanove.get(i) instanceof FakultetRacunarstva
							? "diplomskog"
							: null;
				
				int ocjenaPismenog =  Unos.unos(
						scanner, "Unesite ocjenu " + tipStudija + " rada za studenta: " + fullName);
				
				int ocjenaObrane = Unos.unos(
						scanner, "Unesite ocjenu obrane " + tipStudija + " rada za studenta: " + fullName);
				
				BigDecimal konacnaOcjenaStudija = new BigDecimal(1);
				
				if (obrazovneUstanove.get(i) instanceof VeleucilisteJave) {
					konacnaOcjenaStudija =
						((VeleucilisteJave) obrazovneUstanove.get(i))
						.izracunajKonacnuOcjenuStudijaZaStudenta(
							((Visokoskolska) obrazovneUstanove.get(i))
								.filtrirajIspitePoStudentu(obrazovneUstanove.get(i).getIspiti(), student),
							ocjenaPismenog,
							ocjenaObrane);
				}
				
				if (obrazovneUstanove.get(i) instanceof FakultetRacunarstva) {
					konacnaOcjenaStudija =
						((FakultetRacunarstva) obrazovneUstanove.get(i))
						.izracunajKonacnuOcjenuStudijaZaStudenta(
							((Visokoskolska) obrazovneUstanove.get(i))
								.filtrirajIspitePoStudentu(obrazovneUstanove.get(i).getIspiti(), student),
							ocjenaPismenog,
							ocjenaObrane);
				}
			
				System.out.println(
						"Koncana ocjena studija za studenta " + fullName +
						" je " + konacnaOcjenaStudija);
			}
			
			
			Student najuspjesnijiStudent = null;
			if (obrazovneUstanove.get(i) instanceof VeleucilisteJave) {
				najuspjesnijiStudent = ((VeleucilisteJave) obrazovneUstanove.get(i)).odrediNajuspjesnijegStudentaNaGodini(2018);
			}
			
			else if (obrazovneUstanove.get(i) instanceof FakultetRacunarstva) {
				najuspjesnijiStudent = ((FakultetRacunarstva) obrazovneUstanove.get(i)).odrediNajuspjesnijegStudentaNaGodini(2018);				
			}
			
			
			if (najuspjesnijiStudent != null) {
				System.out.println(
						"Najbolji student 2018. godine je " +
								najuspjesnijiStudent.getIme() + " " + najuspjesnijiStudent.getPrezime() + " " +
								"JMBAG: " + najuspjesnijiStudent.getJmbag());				
			}
			
			if (obrazovneUstanove.get(i) instanceof FakultetRacunarstva) {
				Student studentZaRektorskuNagradu =
						((FakultetRacunarstva) obrazovneUstanove.get(i)).odrediStudentaZaRektorovuNagradu();

				if (studentZaRektorskuNagradu != null) {
					System.out.println(
							"Student koji je osvojio rektorovu nagradu je: " +
							studentZaRektorskuNagradu.getIme() + " " + studentZaRektorskuNagradu.getPrezime() +
							"JMBAG: " + studentZaRektorskuNagradu.getJmbag());					
				}
			}
		}

	}
}
