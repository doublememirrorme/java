package hr.java.vjezbe.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper klasa za razlicite validacije i provjere
 * 
 * @author hrvoj
 *
 */
public class Unos {
	
	public static final Logger log = LoggerFactory.getLogger(Unos.class);

	public Unos() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Onemogucava unos vrijednosti koje nisu tipa int
	 * 
	 * @param scanner korisnicki unos
	 * @param poruka poruka za ispis na ekranu
	 * @return unesenu vrijednost
	 */
	public static int unos(Scanner scanner, String poruka) {
		int broj = -1;
		
		do {
			System.out.println(poruka);
			
			try {
				broj = scanner.nextInt();
				log.info("Unesena vrijednost je: " + broj);
			
			} catch (InputMismatchException e) {
				log.error("Neispravan unos: " + e.getLocalizedMessage());
				broj = -1;
			
			} finally {
				scanner.nextLine();				
			}
		} while (broj < 0);
		
		return broj;
	}
	
	/**
	 * Onemogucava unos datuma koji ne ofgovara trazenom formatu
	 * 
	 * @param scanner korisnicki unos
	 * @param poruka poruka za isps na ekranu
	 * @param formatDatuma trazeni format datuma
	 * @return uneseni datum
	 */
	public static LocalDate unosDatuma(Scanner scanner, String poruka, String formatDatuma) {
		LocalDate datum = null;
		String datumString = null;
		
		do {
			try {
				System.out.println(poruka);
				datumString = scanner.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDatuma);
				datum = LocalDate.parse(datumString, formatter);
			
			} catch (Exception e) {
				log.error("Neispravan datum");
				datum = null;
			}
			
		} while (datum == null);
		
		return datum;
	}

	/**
	 * Sluzi za korsnicki unos vremena i datuma prema trazenom formatu
	 *  
	 * @param scanner korisnicki unos
	 * @param poruka poruka za ispis na ekranu
	 * @param formatDatuma trazeni format datuma i vremena
	 * @return uneseni datum
	 */
	public static LocalDateTime unosDatumaIVremena(Scanner scanner, String poruka, String formatDatuma) {
		LocalDateTime datumIVrijeme = null;
		String datumString = null;
		
		do {
			try {
				System.out.println(poruka);
				datumString = scanner.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDatuma);
				datumIVrijeme = LocalDateTime.parse(datumString, formatter);
			
			} catch (Exception e) {
				log.error("Neispravan datum");
				datumIVrijeme = null;
			}
			
		} while (datumIVrijeme == null);
		
		return datumIVrijeme;
	}
}