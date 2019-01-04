import CassBackend.Backend;

public class Main {
    public static void main(String[] args) {
        final int clientCount = 150;
        final int simulationTime = 150;
        final int checkDelay = 100; // delay to make it slower a little

        Backend backend = new Backend("config.properties");


    }
}
