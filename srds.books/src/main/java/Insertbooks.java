import CassBackend.Backend;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

public class Insertbooks {
    private Session session;
    //  private int requestedBooks;

    public Insertbooks(Backend backend) {
        this.session = backend.getSession();
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


        //wpisanie do bazy tytułów
        session.execute(query1);

        session.execute(query2);

        session.execute(query3);

        session.execute(query4);
    }

    public List<String> getTitles() {
        List<String> titles = new ArrayList<String>();
        titles.add("Potop");
        titles.add("Dziady");
        titles.add("Hari Pota");
        titles.add("Hari Pota 2");
        return titles;
        //return new String[] { "Potop, Dziady, Hari Pota, Hari Pota 2" };
    }
}
