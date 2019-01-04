import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reader implements Runnable {
    List<String> supplierNames1 = new ArrayList<String>();
    private String book_name;
    private int id;
    private Books book;
    private int counter = 10000;

    public Reader(Books book, listOfBooks listBooks) {
        this.id = counter++;
        this.book = book;
        this.
    }

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
    public static void createReaderAndGo (int count, Books book) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new Reader(book));
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