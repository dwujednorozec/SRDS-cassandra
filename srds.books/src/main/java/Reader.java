import java.util.ArrayList;
import java.util.Random;

public class Reader implements Runnable {
    public void run(){
        Random generator = new Random();
        try {
            //check if dostepne

            if() {// wyporzyczamy jeżeli są dostępne WSZYSTKIE

            } else { ///odrzucamy wyporzycenie

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //uzupelnic args
    public static void createReaderAndGo (int count) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new Reader());
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}