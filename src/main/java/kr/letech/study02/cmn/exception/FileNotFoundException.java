package kr.letech.study02.cmn.exception;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 9021896052489948378L;

	public FileNotFoundException() {
	}

	public FileNotFoundException(String message) {
		super(message);
	}

	public FileNotFoundException(String message, Throwable e) {
		super(message, e);
	}
}
