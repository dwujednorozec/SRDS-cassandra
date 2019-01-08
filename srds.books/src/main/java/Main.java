import CassBackend.Backend;

//koment
public class Main {
    public static void main(String[] args) {
        final int clientCount = 150;
        final int simulationTime = 150;
        final int checkDelay = 100; // delay to make it slower a little
        final int maxBookReqNumber = 4;




        Backend backend = new Backend("config.properties");
        //srds.books/src/main/resources/config.properties  ------śćieżka

        Insertbooks insertbooks = new Insertbooks(backend.getSession());

        //insertbooks.insertbookByTitle();

        insertbooks.saveBooks(0,"Potop",150,150);
        insertbooks.saveBooks(1,"Dziady",100,100);
        insertbooks.saveBooks(2,"Java testing",150,150);
        insertbooks.saveBooks(3,"Try to catch",200,200);
        insertbooks.saveBooks(4,"The Shining",150,150);
        insertbooks.saveBooks(4,"Pet Sementary",200,200);

       // Books books = new Books(backend);

        final int randomizerBooks = insertbooks.getTitles().size();
        Reader.createReaderAndGo(clientCount, backend, insertbooks.getTitles(), randomizerBooks, maxBookReqNumber);

        Stats.getInstance().showStats();
    }
}