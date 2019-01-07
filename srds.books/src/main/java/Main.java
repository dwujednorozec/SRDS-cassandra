import CassBackend.Backend;


public class Main {
    public static void main(String[] args) {
        final int clientCount = 2;
        final int simulationTime = 150;
        final int checkDelay = 100; // delay to make it slower a little
        final int randomizerBooks = 3;



        Backend backend = new Backend("config.properties");
        //srds.books/src/main/resources/config.properties  ------śćieżka

        Insertbooks insertbooks = new Insertbooks(backend.getSession());

        //insertbooks.insertbookByTitle();
        insertbooks.saveBooks(0,"Potop",50,50);
        insertbooks.saveBooks(1,"Dziady",100,100);
        insertbooks.saveBooks(2,"Java testing",50,50);
        insertbooks.saveBooks(3,"Try to catch",100,100);
        insertbooks.saveBooks(4,"The Shining",50,50);

        Books books = new Books(backend);

        Reader.createReaderAndGo(clientCount, books, insertbooks.getTitles(), randomizerBooks);


    }
}