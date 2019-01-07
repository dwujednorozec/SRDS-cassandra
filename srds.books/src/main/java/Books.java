import CassBackend.Backend;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

//koment
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Books {
    private UUID requestID;
    private static final String TABLE_NAME = "allbooks";
    List<Integer> result = new ArrayList<>();
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

    public Boolean rentBook(List<String> titles,int idUser){
       // Boolean result = false;
        numberOfTitles = titles.size()/2;
        lastUserId = idUser;

        for (int i=0;i<titles.size();i++) {
            List<String> requestBooks;
            requestBooks = getBook(titles.get(i), 1+Integer.valueOf(titles.get(i+1)));
            i++;

            if (requestBooks.isEmpty()) {
                result.add(-1);
                numberOfBooks.add(1+Integer.valueOf(titles.get(i+1)));
            } else {
 // TODO need to be tested
                result.add(Integer.valueOf(requestBooks.get(0)));
                numberOfBooks.add(1+Integer.valueOf(titles.get(i+1)));
            }
        }

        if (result.contains(-1)) {
            return false;
        }


        //TODO test this and also add wait before this line ^
        requestID = UUID.randomUUID();

        for (int i=0;i<(numberOfTitles);i++){
            RequestBook requestBook = new RequestBook(session,requestID,Integer.valueOf(result.get(i)),idUser,numberOfBooks.get(i),false);
            requestBook.saveRequest();
        }

        CheckBook checkBook = new CheckBook(session,requestID,idUser);

        if (checkBook.CheckApproved(result)){
            return true;
            //dostal ksiazke i sie bawi
        }else{
            return false;
            //sad panda nie dostal
        }

    }

    public void returnBook(){
        //trzeba zrobic jakas globalna dla klasy (tej klasy) strukture (lista po klasie? set?) ktora bedzie przechowywac co kto wypozyczyl
        //do dopracowania troszke bo moze cos nie dzialac (na szybko dane wjebane do poprawki tez zeby bylo ladniej)

        //INACZEJ!!!: select * form request where UUID = zapisane_my_last_UUID i potem wpisac z tego albo zrobic strukture jakas gdzie to bedzie zapisane tu... nwm pomysli sie

        for (int i=0;i<(numberOfTitles);i++) {
            RequestBook requestBook = new RequestBook(session, requestID, Integer.valueOf(result.get(i)), lastUserId, numberOfBooks.get(i),true);
           // inaczej tego requesta trza dac, on musi edytowac stary wpis i zmienic tylko returned... i timestampa?
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
