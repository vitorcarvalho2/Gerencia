package java.user.domain.shared.exceptions;

//ryoiki tenkai muryo kusho
public class DomainException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DomainException(String message) {
        super(message);
    }

}
