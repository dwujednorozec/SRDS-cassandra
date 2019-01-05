import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reader implements Runnable {
    private List<String> titles;
    private List<String> myTitles = new ArrayList<>();
    private List<String> requestRet = new ArrayList<>();
    private int id;
    private Books book;
    private int counter = 10000;
    private int randomizerBooks;

    public Reader(Books book, List<String> titles, int randomizerBooks) {
        this.id = counter++;
        this.book = book;
        this.titles = titles;
        this.randomizerBooks = randomizerBooks;
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
            int reqNumber;
            reqNumber = generator.nextInt(20);

            for (int i=0;i<=reqNumber;i++){
                myTitles.add(titles.get(generator.nextInt(randomizerBooks)));
                myTitles.add(Integer.toString(generator.nextInt(20)));
                //przerob to na liste w liscie czy cos zeby mogl miec kilka pozycji kazdej
                //albo zostaw tak na retarda
                //dobre miejsce na staty
            }



            requestRet = book.rentBook(myTitles);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //uzupelnic args
    public static void createReaderAndGo (int count, Books book, List<String> titles, int randomizerBooks) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new Reader(book, titles, randomizerBooks));
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