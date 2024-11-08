package store.exception;

public class OutOfStockException extends MyIllegalStateException {
    public OutOfStockException(String message) {
        super(message);
    }
}
