import CassBackend.Backend;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

public class Insertbooks extends CassandraTableModel{
 //   private Session session;
    private static final String TABLE_NAME = "allbooks";
    private List<String> titles = new ArrayList<String>();


    public Insertbooks(Session session) {
        super(session);

    }

    public void saveBooks(int id_book, String book_name, int nr_of_free_books, int total_books) {
        createBookTable(TABLE_NAME);

        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME).append("(id_book, book_name, nr_of_free_books, total_books)")
                .append("VALUES (").append(id_book)
                .append(", ").append(book_name)
                .append(", ").append(nr_of_free_books)
                .append(", ").append(total_books)
                .append(");");

        String query = sb.toString();
        execute(query);
        titles.add("book_name");
    }

    public List<String> getTitles() {
        return titles;
    }
}
