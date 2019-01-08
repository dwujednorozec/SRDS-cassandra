import CassBackend.Backend;

public class Main {
    public static void main(String[] args) {
        final int clientCount = 31;
        final int simulationTime = 150;
        final int checkDelay = 100; // delay to make it slower a little
        final int maxBookReqNumber = 1;

        Backend backend = new Backend("config.properties");

        Insertbooks insertbooks = new Insertbooks(backend.getSession());

        insertbooks.saveBooks(0,"Potop",150,150);
        insertbooks.saveBooks(1,"Dziady",100,100);
        insertbooks.saveBooks(2,"Java testing",150,150);
        insertbooks.saveBooks(3,"Try to catch",200,200);
        insertbooks.saveBooks(4,"The Shining",150,150);
        insertbooks.saveBooks(5,"Pet Sematary",200,200);

        final int randomizerBooks = insertbooks.getTitles().size();
        Reader.createReaderAndGo(clientCount, backend, insertbooks.getTitles(), randomizerBooks, maxBookReqNumber);

        Stats.getInstance().showStats();
    }
}