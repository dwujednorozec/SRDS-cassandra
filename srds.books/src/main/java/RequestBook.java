import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import static com.datastax.driver.core.schemabuilder.SchemaBuilder.createTable;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class RequestBook extends CassandraTableModel {
    private static final String TABLE_NAME = "BookRequest";
    private UUID requestId;
    private int id_book;
    private int id_user;
    private int requestedBooks;
    private long timestamp;

    public RequestBook(Session session, UUID requestId, int id_book, int id_user, int requestedBooks) {
        super(session);
        this.requestId = requestId;
        this.id_book = id_book;
        this.id_user = id_user;
        this.requestedBooks = requestedBooks;
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
                .append(", ").append(System.currentTimeMillis())
                .append(");");

        String query = sb.toString();
        execute(query);
    }

    public void checkApproved(int id_book, int id_user){
        //todo
    }

    private List<RequestBook> determine (int reqID, int idBook){

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




        rs.one();

        return requestBooks;
    }

}
