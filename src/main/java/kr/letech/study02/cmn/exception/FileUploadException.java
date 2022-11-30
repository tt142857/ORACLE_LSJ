package kr.letech.study02.cmn.exception;

public class FileUploadException extends RuntimeException {

	private static final long serialVersionUID = 9021896052489948378L;

	public FileUploadException() {
	}

	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(String message, Throwable e) {
		super(message, e);
	}
}
