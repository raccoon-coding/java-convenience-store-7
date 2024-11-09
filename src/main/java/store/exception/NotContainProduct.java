package store.exception;

public class NotContainProduct extends MyIllegalStateException {
    public NotContainProduct(String message) {
        super(message);
    }
}
