package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Arrays;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenta;

/**
 * Predstavlja sve visokoskolske ustanove
 * 
 * @author hrvoj
 *
 */
public interface Visokoskolska {

	/**
	 * Racuna konacnu ocjenu studija za studenta
	 * 
	 * @param ispiti lista ispita
	 * @param ocjenaZavrsnogRadaPismeniDio ocjena pismenog djela zavrsnog rada 
	 * @param ocjenaZavrsnogRadaObrana ocjena obrane zavrsnog rada
	 * @return konacnu ocjenu za studenta
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaZavrsnogRadaPismeniDio, int ocjenaZavrsnogRadaObrana);
	
	/**
	 * Odreduje prosjek ocjena na ispitima
	 * 
	 * @param ispiti lista ispita
	 * @return prosjek ocjena na ispitima
	 * @throws NemoguceOdreditiProsjekStudenta kada je neka od ocjena na ispitima negativna
	 */
	default public BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti) throws NemoguceOdreditiProsjekStudenta {
		
//		Ispit[] polozeniIspiti = filtrirajPolozeneIspite(ispiti);
		BigDecimal ocjene = new BigDecimal(0);
		
		for (Ispit ispit : ispiti) {
			if (ispit.getOcjena().equals(1)) {
				throw new NemoguceOdreditiProsjekStudenta(
						"Student: " + ispit.getStudent().getJmbag() + " " +
						ispit.getStudent().getPrezime() + " " + ispit.getStudent().getIme() +
						"\nIspit: " + ispit.getPredmet().getNaziv() + ": " + ispit.getOcjena());
			}
			ocjene.add(new BigDecimal(ispit.getOcjena()));
		}
		
		return ocjene.divide(new BigDecimal(ispiti.length > 0 ? ispiti.length : 1));
	}
	
	/**
	 * Filtrira ispite gdje ocjena ispite nije negativna
	 * 
	 * @param ispiti lista ispita
	 * @return sve ispite na kojima je ocjena veca od negativne
	 */
	private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti) {
		return Arrays
				.stream(ispiti)
				.filter(ispit -> ispit.getOcjena() > 1)
				.toArray(Ispit[]::new);
	};
	
	/**
	 * Filtrira listu ispita prema studentu
	 * 
	 * @param ispiti lista ispita
	 * @param student student za pretragu
	 * @return listu ispita za odredenog studenta
	 */
	default public Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {
		return Arrays
				.stream(ispiti)
				.filter(ispit -> ispit.getStudent().getJmbag().equals(student.getJmbag()))
				.toArray(Ispit[]::new);
	}
}
 