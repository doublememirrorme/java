package hr.java.vjezbe.entitet;

/**
 * Predstavlja sucelje diplomskih obrazovnih ustanova
 * 
 * @author hrvoj
 * 
 */
public interface Diplomski extends Visokoskolska {
	
	/**
	 * @return Student za rektorovu nagradu
	 */
	public Student odrediStudentaZaRektorovuNagradu();

}
