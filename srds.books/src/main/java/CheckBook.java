import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


public class CheckBook extends CassandraTableModel{
    private static final String TABLE_NAME = "BookRequest";
    private UUID requestId;
//    private List<Integer> id_book;
    private int id_user;
//    private List<Integer> requestedBooks;
    private long timestamp;
    private boolean returned;
    private int single_id_book;
    private int single_requestedBooks;


//    public CheckBook(Session session, UUID requestId, List<Integer> id_book, int id_user, List<Integer> requestedBooks, boolean returned) {
//        super(session);
//        this.requestId = requestId;
//        this.id_book = id_book;
//        this.id_user = id_user;
//        this.requestedBooks = requestedBooks;
//        this.returned = returned;
//    }

    public CheckBook(Session session, UUID requestId, int id_book, int id_user, int requestedBooks, boolean returned) {
        super(session);
        this.requestId = requestId;
        this.single_id_book = id_book;
        this.id_user = id_user;
        this.single_requestedBooks = requestedBooks;
        this.returned = returned;
    }

    public CheckBook(Session session, UUID requestId, int id_book, int id_user, int requestedBooks, boolean returned, long timestamp) {
        super(session);
        this.requestId = requestId;
        this.single_id_book = id_book;
        this.id_user = id_user;
        this.single_requestedBooks = requestedBooks;
        this.returned = returned;
        this.timestamp = timestamp;
    }

    public boolean CheckApproved(List<Integer> id_book){
        //jakas lista na wejscie i w for each dac wywolanie singla

        for (int i=0;i<=id_book.size();i++){

            if (!singleCheckApproved(id_book.get(i))){
                return false;
            }
        }
        return true;
    }

    public boolean singleCheckApproved(int id_book){
        //todo

        StringBuilder sb = new StringBuilder("SELECT total_books FROM BookRequest WHERE id_book = ").append(id_book);

        String query = sb.toString();
        ResultSet rs = execute(query);
        //StringBuilder builder = new StringBuilder();
        int totalBooks = 0;

        for (Row row : rs) {
            totalBooks = row.getInt("total_books");
        }

        List<CheckBook> requestBooks;
        requestBooks = getRelevant(id_book);

        boolean partialDecision = false;
        for (CheckBook requestBook : requestBooks){
            if (totalBooks >= requestBook.single_requestedBooks) {
                totalBooks -= requestBook.single_requestedBooks;
                if (requestId == requestBook.requestId){
                        partialDecision = true;
                        break;
                }
            }
        }

        return partialDecision;

    }

    private List<CheckBook> getRelevant (int idBook){

        //nie wiem czy nie wyjebac tego dostepne booki i liczyc na bierzaco

        List<CheckBook> requestBooks = new ArrayList<>();

        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE id = ").append(idBook);

        String query = sb.toString();
        ResultSet rs = execute(query);

        rs.forEach(r -> {
            if (!r.getBool("returned")){
                requestBooks.add(new CheckBook(
                        session,
                        r.getUUID("id"),
                        r.getInt("id_book"),
                        r.getInt("id_user"),
                        r.getInt("req_books"),
                        r.getBool("returned"),
                        r.getLong("timestamp")));
            }
        });


        // Sort by Timestamp
        requestBooks.sort(new Comparator<CheckBook>() {
            @Override
            public int compare(CheckBook m1, CheckBook m2) {
                if (m1.timestamp == m2.timestamp) {
                    return 0;
                }
                return m1.timestamp < m2.timestamp ? -1 : 1;
            }
        });

        return requestBooks;
    }

}
