import CassBackend.Backend;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Books {
    private UUID requestID;
    private static final String TABLE_NAME = "allbooks";
    private List<Integer> result = new ArrayList<>();
    private List<Integer> numberOfBooks = new ArrayList<>();
    private Session session;
    private int numberOfTitles;
    private int lastUserId;

    public Books(Backend backend) {
        this.session = backend.getSession();
        requestID = UUID.randomUUID();
    }

    public Boolean rentBook(List<String> titles,int idUser){
        numberOfTitles = titles.size()/2;
        List<String> new_titles = new ArrayList<>();
        lastUserId = idUser;


        for (int i=0;i<titles.size();i+=2) {
            List<Integer> requestBooks;
            requestBooks = getBook(titles.get(i), 1+Integer.valueOf(titles.get(i+1)));


            if (requestBooks.isEmpty()) {
                result.add(-1);
                numberOfBooks.add(1+Integer.valueOf(titles.get(i+1)));
            } else {
                result.add(requestBooks.get(0));
                new_titles.add(titles.get(i));
                numberOfBooks.add(1+Integer.valueOf(titles.get(i+1)));
            }
        }
        Stats.getInstance().request(idUser,new_titles);

        if (result.contains(-1)) {
            return false;
        }

        for (int i=0;i<(numberOfTitles);i++){
            RequestBook requestBook = new RequestBook(session,requestID,result.get(i),idUser,numberOfBooks.get(i),false);
            requestBook.saveRequest();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CheckBook checkBook = new CheckBook(session,requestID,idUser);

        if (checkBook.CheckApproved(result,new_titles)){
            Stats.getInstance().rent(idUser,new_titles);
            return true;
        }else{
            for (int i=0;i<(numberOfTitles);i++) {
                RequestBook requestBook = new RequestBook(session, requestID, result.get(i), lastUserId, 0,true);
                requestBook.saveRequest();

            }
            Stats.getInstance().dontrent(idUser,new_titles);
            return false;
        }

    }

    public void returnBook(){

        for (int i=0;i<(numberOfTitles);i++) {
            RequestBook requestBook = new RequestBook(session, requestID, result.get(i), lastUserId, numberOfBooks.get(i),true);
            requestBook.saveRequest();
        }
        Stats.getInstance().unrent(lastUserId,numberOfTitles);

        result.clear();
        numberOfBooks.clear();
    }

    public List<Integer> getBook(String bookName, int requestedBooks) {
        StringBuilder sb =
                new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE book_name='").append(bookName).append("'");

        String query = sb.toString();
        session.execute(query);
        ResultSet rs = session.execute(query);

        List<Integer> requestBooks = new ArrayList<>();

        rs.forEach(r -> {
            if (r.getInt("nr_of_free_books") > requestedBooks) {
                requestBooks.add(r.getInt("id_book"));
            }
        });

        return requestBooks;
    }
}
