package kr.letech.study02.cmn.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5325891750191730113L;

	public UserNotFoundException(String id){
        super(id + " Not Found Exception");
    }

}