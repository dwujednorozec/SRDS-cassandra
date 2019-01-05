import CassBackend.Backend;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import jnr.ffi.annotations.In;


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

    public List<String> rentBook(List<String> titles){
       // Boolean result = false;
        List<String> result = new ArrayList<>();

        for (int i=0;i<=titles.size();i++) {
            List<String> requestBooks;
            requestBooks = getBook(titles.get(i), 1+Integer.valueOf(titles.get(i+1)));
            i++;

            if (requestBooks.isEmpty()) {
                result.add("NOT_AVAILABLE");
            } else {
 // TODO need to be tested
                result.add(requestBooks.get(0));
            }
        }

        if (result.contains("NOT_AVAILABLE")) {
            return result;
        }

        //TODO try to reserve and check after few sec

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
