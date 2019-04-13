package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaZavrsnogRadaPismeniDio, int ocjenaZavrsnogRadaObrana);
	
	/**
	 * Odreduje prosjek ocjena na ispitima
	 * 
	 * @param ispiti lista ispita
	 * @return prosjek ocjena na ispitima
	 * @throws NemoguceOdreditiProsjekStudenta kada je neka od ocjena na ispitima negativna
	 */
	default public BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudenta {
		
//		Ispit[] polozeniIspiti = filtrirajPolozeneIspite(ispiti);
		BigDecimal ocjene = new BigDecimal(0);
		
		for (Ispit ispit : ispiti) {
			if (ispit.getOcjena().equals(Ocjena.NEDOVOLJAN.getOcjena())) {
				throw new NemoguceOdreditiProsjekStudenta(
						"Student: " + ispit.getStudent().getJmbag() + " " +
						ispit.getStudent().getPrezime() + " " + ispit.getStudent().getIme() +
						"\nIspit: " + ispit.getPredmet().getNaziv() + ": " + ispit.getOcjena());
			}
			ocjene.add(new BigDecimal(ispit.getOcjena()));
		}
		
		return ocjene.divide(new BigDecimal(ispiti.size() > 0 ? ispiti.size() : 1));
	}
	
	/**
	 * Filtrira ispite gdje ocjena ispite nije negativna
	 * 
	 * @param ispiti lista ispita
	 * @return sve ispite na kojima je ocjena veca od negativne
	 */
	private List<Ispit> filtrirajPolozeneIspite(List<Ispit> ispiti) {
		return ispiti
				.stream()
				.filter(ispit -> ispit.getOcjena() > 1)
				.collect(Collectors.toList());
	};
	
	/**
	 * Filtrira listu ispita prema studentu
	 * 
	 * @param list lista ispita
	 * @param student student za pretragu
	 * @return listu ispita za odredenog studenta
	 */
	default public List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti, Student student) {	
		return ispiti
				.stream()
				.filter(ispit -> ispit.getStudent().getJmbag().equals(student.getJmbag()))
				.collect(Collectors.toList());
	}
}
 