package store.view;

public class Output {
    private static final String EXCEPTION_PREFIX = "[ERROR] ";

    public Output() {
    }

    public void viewReceipt(String receipt) {
        System.out.println(receipt);
    }

    public void viewExceptionMessage(String e) {
        System.out.println(EXCEPTION_PREFIX + e);
    }
}
