package az.savey.ms.savey.exception;

public class AccountException extends RuntimeException{
    public AccountException() {}
    public AccountException(String message) {
        super(message);
    }
}
