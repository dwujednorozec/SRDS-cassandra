import CassBackend.Backend;
import com.datastax.driver.core.Session;

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


        session.execute(query1);

        session.execute(query2);

        session.execute(query3);

        session.execute(query4);
    }

    public String[] getTitles() {
        return new String[] { "Potop, Dziady, Hari Pota, Hari Pota 2" };
    }
}
