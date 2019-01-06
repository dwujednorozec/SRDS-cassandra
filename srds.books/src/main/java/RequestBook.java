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
    private boolean returned;

    public RequestBook(Session session, UUID requestId, int id_book, int id_user, int requestedBooks, boolean returned) {
        super(session);
        this.requestId = requestId;
        this.id_book = id_book;
        this.id_user = id_user;
        this.requestedBooks = requestedBooks;
        this.returned = returned;
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



}
