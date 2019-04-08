package hr.java.vjezbe.iznimke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bacati kada je nemoguce odrediti prosjek iz nekog razloga
 * 
 * @author hrvoj
 *
 */
public class NemoguceOdreditiProsjekStudenta extends Exception {

	private static final long serialVersionUID = 5303821292654979559L;
	private static final Logger log = LoggerFactory.getLogger(NemoguceOdreditiProsjekStudenta.class);

	public NemoguceOdreditiProsjekStudenta() {
		log.error("Nemoguce odrediti prosjek studenta");
	}

	public NemoguceOdreditiProsjekStudenta(String message) {
		super(message);
	}

	public NemoguceOdreditiProsjekStudenta(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NemoguceOdreditiProsjekStudenta(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NemoguceOdreditiProsjekStudenta(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
