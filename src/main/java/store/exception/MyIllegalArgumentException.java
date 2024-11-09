package store.exception;

public class MyIllegalArgumentException extends IllegalArgumentException  implements MyException {
    public MyIllegalArgumentException(String message) {
        super(message);
    }
}
