package store;

public class Application {
    public static void main(String[] args) {
        Config config = new Config();
        UserRequest userRequest = config.getUserRequest();
        userRequest.request();
    }
}
