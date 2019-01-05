import CassBackend.Backend;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;


import java.util.ArrayList;
import java.util.List;

public class Books {
    private int id;
    private static final String TABLE_NAME = "Books";
 //   private String bookName;
    private Session session;
  //  private int requestedBooks;

    public Books(Backend backend) {
        //this.bookName = bookName;
        //this.session = session;
        this.session = backend.getSession();
     //   this.requestedBooks = requestedBooks;
    }

    public List<String> rentBook(List<String> titles, int reqNumber){
       // Boolean result = false;
        List<String> result = new ArrayList<>();

        for (String title : titles) {
            List<String> requestBooks = new ArrayList<>();
            requestBooks = getBook(title,reqNumber);

            if (requestBooks.isEmpty()) {
                result.add("NOT_AVAILABLE");
            } else {
 // TODO need to be tested
                result.add(requestBooks.get(0));
            }
            // fruit is an element of the `fruits` array.
        }

        return result;
    }

    public void returnBook(){

    }

    public List<String> getBook(String bookName, int requestedBooks) {
        StringBuilder sb =
                new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE book_name=").append(bookName);

        String query = sb.toString();
        //  ResultSet rs = execute(query); why not work?
        ResultSet rs = session.execute(query);

        List<String> requestBooks = new ArrayList<>();

        rs.forEach(r -> {
            if (r.getInt("nr_of_free_books") > requestedBooks) {
                requestBooks.add(r.getString("id_book"));
            } else {
                //jakies info ze nie znalazl
            }
        });

        return requestBooks;
    }
}
