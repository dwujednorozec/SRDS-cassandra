import CassBackend.Backend;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Books {
    private UUID requestID;
    private static final String TABLE_NAME = "Books";
    List<String> result = new ArrayList<>();
    List<Integer> numberOfBooks = new ArrayList<>(); // ilosc ksiazek jakie zarzadal reader
 //   private String bookName;
    private Session session;
    private int numberOfTitles;
    private int lastUserId;
  //  private int requestedBooks;

    public Books(Backend backend) {
        //this.bookName = bookName;
        //this.session = session;
        this.session = backend.getSession();
     //   this.requestedBooks = requestedBooks;
    }

    public List<String> rentBook(List<String> titles,int idUser){
       // Boolean result = false;
        numberOfTitles = titles.size()/2;
        lastUserId = idUser;

        for (int i=0;i<titles.size();i++) {
            List<String> requestBooks;
            requestBooks = getBook(titles.get(i), 1+Integer.valueOf(titles.get(i+1)));
            i++;

            if (requestBooks.isEmpty()) {
                result.add("NOT_AVAILABLE");
                numberOfBooks.add(1+Integer.valueOf(titles.get(i+1)));
            } else {
 // TODO need to be tested
                result.add(requestBooks.get(0));
                numberOfBooks.add(1+Integer.valueOf(titles.get(i+1)));
            }
        }

        if (result.contains("NOT_AVAILABLE")) {
            return result;
        }

        //TODO try to reserve and check after few sec
        requestID = UUID.randomUUID();

        for (int i=0;i<(numberOfTitles);i++){
            RequestBook requestBook = new RequestBook(session,requestID,Integer.valueOf(result.get(i)),idUser,numberOfBooks.get(i));
            requestBook.saveRequest();
        }

        //wait nad check dopisac

        return result;
    }

    public void returnBook(){
        //trzeba zrobic jakas globalna dla klasy (tej klasy) strukture (lista po klasie? set?) ktora bedzie przechowywac co kto wypozyczyl
        //do dopracowania troszke bo moze cos nie dzialac (na szybko dane wjebane do poprawki tez zeby bylo ladniej)

        for (int i=0;i<(numberOfTitles);i++) {
            RequestBook requestBook = new RequestBook(session, requestID, Integer.valueOf(result.get(i)), lastUserId, numberOfBooks.get(i));
            requestBook.saveRequest();
        }

        result.clear();
        numberOfBooks.clear();
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
