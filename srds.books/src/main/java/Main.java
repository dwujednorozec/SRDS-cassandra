import CassBackend.Backend;


public class Main {
    public static void main(String[] args) {
        final int clientCount = 150;
        final int simulationTime = 150;
        final int checkDelay = 100; // delay to make it slower a little
        final int randomizerBooks = 3;

        Backend backend = new Backend("config.properties");

        Insertbooks insertbooks = new Insertbooks(backend.getSession());

        //insertbooks.insertbookByTitle();
        insertbooks.saveBooks(0,"Potop",50,50);

        Books books = new Books(backend);

        Reader.createReaderAndGo(clientCount, books, insertbooks.getTitles(), randomizerBooks);


    }
}