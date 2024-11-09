package store.exception;

public class NotOrderProduct extends MyIllegalStateException {
    public NotOrderProduct(String message) {
        super(message);
    }
}
