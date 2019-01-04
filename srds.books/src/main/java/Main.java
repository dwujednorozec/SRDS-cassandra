import CassBackend.Backend;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster;

public class Main {
    public static void main(String[] args) {
        final int clientCount = 150;
        final int simulationTime = 150;
        final int checkDelay = 100; // delay to make it slower a little

        Backend backend = new Backend("config.properties");

        Books books = new Books(backend);
    }

    public void insertbookByTitle() {

        String query1 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"

                + " VALUES(1,'Potop', 10, 10);" ;

        String query2 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"


                + " VALUES(1,'Dziady', 100, 120);" ;

        String query3 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"

                + " VALUES(1,'Hari Pota', 20, 80);" ;

        String query4 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"

                + " VALUES(1,'Hari Pota 2', 10, 180);" ;
        //Creating Cluster object
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();

        //Creating Session object
        Session session = cluster.connect("tp");

        session.execute(query1);

        session.execute(query2);

        session.execute(query3);

        session.execute(query4);


    }
}
