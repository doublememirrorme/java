package hr.java.vjezbe.iznimke;

/**
 * Baciti kada se pronade vise najmladih studenata
 * 
 * @author hrvoj
 *
 */
public class PostojiViseNajmladjihStudenataException extends RuntimeException {

	private static final long serialVersionUID = 1142088483730368726L;

	public PostojiViseNajmladjihStudenataException() {
		
	}

	public PostojiViseNajmladjihStudenataException(String message) {
		super(message);
	}

	public PostojiViseNajmladjihStudenataException(Throwable cause) {
		super(cause);
	}

	public PostojiViseNajmladjihStudenataException(String message, Throwable cause) {
		super(message, cause);
	}

	public PostojiViseNajmladjihStudenataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
