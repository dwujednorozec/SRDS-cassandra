import CassBackend.Backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reader implements Runnable {
    private List<String> titles;
    private List<String> myTitles = new ArrayList<>();
    private int maxBookReqNumber;
    private int id;
    private Books book;
    private static int counter = 10000;
    private int randomizerBooks;

    public Reader(Backend backend, List<String> titles, int randomizerBooks, int maxBookReqNumber) {
        this.id = counter;
        counter++;
        this.maxBookReqNumber = maxBookReqNumber;
        this.titles = titles;
        this.randomizerBooks = randomizerBooks;
        this.book = new Books(backend);
    }

    public void run(){
        Random generator = new Random();
        boolean requestRet;

        try {
            System.out.println(this.id);
            //check if dostepne
            int reqNumber;
            reqNumber = generator.nextInt(maxBookReqNumber);
            int temp;
            List<Integer> lastrandoms = new ArrayList<>();
            for (int i=0;i<=reqNumber;i++) {
                do {
                    temp = generator.nextInt(randomizerBooks);
                }while (lastrandoms.contains(temp));
                lastrandoms.add(temp);
                myTitles.add(titles.get(temp));
                myTitles.add(Integer.toString(generator.nextInt(20)));
            }

            Thread.sleep(1000+generator.nextInt(2000));

            requestRet = book.rentBook(myTitles,id);

            Thread.sleep(1000+generator.nextInt(3000));

            if (requestRet) {
                book.returnBook();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void createReaderAndGo (int count, Backend backend, List<String> titles, int randomizerBooks, int maxBookReqNumber) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new Reader(backend, titles, randomizerBooks, maxBookReqNumber));
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