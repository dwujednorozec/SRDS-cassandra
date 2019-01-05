import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reader implements Runnable {
    List<String> titles = new ArrayList<String>();
    private String book_name;
    private int id;
    private Books book;
    private int counter = 10000;

    public Reader(Books book, List<String> titles) {
        this.id = counter++;
        this.book = book;
        this.titles = titles;
    }

    public void run(){
        Random generator = new Random();
        try {
            //check if dostepne

            //String title = titles.get(generator.nextInt(4));
            //int numberOf = generator.nextInt(20);

            //WTF?!
           // List<RequestBook> requestBooks = new ArrayList<>();
           // requestBooks = book.getBook(title, numberOf);


           // if(requestBooks.isEmpty()) {// odrzucamy wyporzycenie
                //break;
          //  } else { ///wyporzyczamy jeżeli są dostępne WSZYSTKIE
                //do staff
           // }

            String requestRet[] = new String[10];

            requestRet = book.

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //uzupelnic args
    public static void createReaderAndGo (int count, Books book, List<String> titles) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new Reader(book, titles));
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