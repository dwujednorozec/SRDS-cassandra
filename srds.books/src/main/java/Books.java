import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;


import java.util.ArrayList;
import java.util.List;

public class Books {
    private int id;
    private static final String TABLE_NAME = "Books";
    private String bookName;
    private Session session;
    private int requestedBooks;

    public Books(String bookName, Session session, int requestedBooks) {
        this.bookName = bookName;
        this.session = session;
        this.requestedBooks = requestedBooks;
    }

    public List<RequestBook> getBook(int bookName) {
        StringBuilder sb =
                new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE book_name=").append(bookName);

        String query = sb.toString();
      //  ResultSet rs = execute(query); why not work?
        ResultSet rs = session.execute(query);

        List<RequestBook> requestBooks = new ArrayList<>();

        rs.forEach(r -> {
            if (r.getInt("nr_of_free_books") > requestedBooks) {
                requestBooks.add(new RequestBook(
                        session,
                        r.getInt("id_book"),
                        r.getInt("book_name"),
                        r.getInt("nr_of_free_books"),
                        requestedBooks));
            } else {
                //jakies info ze nie znalazl
            }
        });

        return requestBooks;
    }
}
