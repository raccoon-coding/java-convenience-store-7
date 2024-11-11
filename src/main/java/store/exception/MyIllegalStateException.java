package store.exception;

public class MyIllegalStateException extends IllegalStateException implements MyException {
    public MyIllegalStateException(String message) {
        super(message);
    }
}
