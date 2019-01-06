import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import static com.datastax.driver.core.schemabuilder.SchemaBuilder.createTable;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


public class RequestBook extends CassandraTableModel {
    private static final String TABLE_NAME = "BookRequest";
    private UUID requestId;
    private int id_book;
    private int id_user;
    private int requestedBooks;
    private long timestamp;
    private boolean returned;

    public RequestBook(Session session, UUID requestId, int id_book, int id_user, int requestedBooks, boolean returned) {
        super(session);
        this.requestId = requestId;
        this.id_book = id_book;
        this.id_user = id_user;
        this.requestedBooks = requestedBooks;
        this.returned = returned;
    }


    public RequestBook(Session session, UUID requestId, int id_book, int id_user, int requestedBooks, boolean returned, long timestamp) {
        super(session);
        this.requestId = requestId;
        this.id_book = id_book;
        this.id_user = id_user;
        this.requestedBooks = requestedBooks;
        this.returned = returned;
        this.timestamp = timestamp;
    }

// przerobienie na koszyk requestow: dodanie fora i powielanie requestu + dodanie UUID pojedynczego requestu

    public void saveRequest() {
        createRequestTable(TABLE_NAME);

        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME).append("(id, id_book, id_user, req_books, timestamp)")
                .append("VALUES (").append(requestId)
                .append(", ").append(id_book)
                .append(", ").append(id_user)
                .append(", ").append(requestedBooks)
                .append(". ").append(returned)
                .append(", ").append(System.currentTimeMillis())
                .append(");");

        String query = sb.toString();
        execute(query);
    }

    public void CheckApproved(){
        //jakas lista na wejscie i w for each dac wywolanie singla

        singleCheckApproved()
    }

    public boolean singleCheckApproved(int id_book, int id_user){
        //todo

        StringBuilder sb = new StringBuilder("SELECT total_books FROM BookRequest WHERE id_book = ").append(idBook);

        String query = sb.toString();
        ResultSet rs = execute(query);
        //StringBuilder builder = new StringBuilder();
        int totalBooks = 0;

        for (Row row : rs) {
            totalBooks = row.getInt("total_books");
        }

        List<RequestBook> requestBooks;
        requestBooks = getRelevant(id_book);
        int suma = 0;

        for (RequestBook requestBook : requestBooks){
            suma += requestBook.requestedBooks;
        }

        if (totalBooks >= suma){
            return true;
        }

        return false;

    }

    private List<RequestBook> getRelevant (int idBook){

        //nie wiem czy nie wyjebac tego dostepne booki i liczyc na bierzaco

        List<RequestBook> requestBooks = new ArrayList<>();

        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE id = ").append(idBook);

        String query = sb.toString();
        ResultSet rs = execute(query);

        rs.forEach(r -> {
            if (!r.getBool("returned")){
                requestBooks.add(new RequestBook(
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
        requestBooks.sort(new Comparator<RequestBook>() {
            @Override
            public int compare(RequestBook m1, RequestBook m2) {
                if (m1.timestamp == m2.timestamp) {
                    return 0;
                }
                return m1.timestamp < m2.timestamp ? -1 : 1;
            }
        });

        return requestBooks;
    }

    private List<RequestBook> ODLdetermine (int reqID, int idBook){

        //nie wiem czy nie wyjebac tego dostepne booki i liczyc na bierzaco

        List<RequestBook> requestBooks = new ArrayList<>();
        StringBuilder sb = new StringBuilder("SELECT total_books FROM BookRequest WHERE id_book = ").append(idBook);

        String query = sb.toString();
        ResultSet rs = execute(query);
        //StringBuilder builder = new StringBuilder();
        int totalBooks;

        for (Row row : rs) {
            totalBooks = row.getInt("total_books");
        }

        StringBuilder sb2 = new StringBuilder("SELECT req_books FROM ").append(TABLE_NAME).append(" WHERE id = ").append(reqID);

        query = sb2.toString();
        rs = execute(query);
        List<Integer> usersBooks = new ArrayList<>();

        for (Row row : rs) {
            usersBooks.add(row.getInt("req_books"));
        }

        int suma = 0;

        for (int i=0;i<usersBooks.size();i++){
            suma += usersBooks.get(i);
        }

        //if (suma<)
        //rs.one();

        // Sort by Timestamp
        requestBooks.sort(new Comparator<RequestBook>() {
            @Override
            public int compare(RequestBook m1, RequestBook m2) {
                if (m1.timestamp == m2.timestamp) {
                    return 0;
                }
                return m1.timestamp < m2.timestamp ? -1 : 1;
            }
        });

        return requestBooks;
    }

}
