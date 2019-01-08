import com.datastax.driver.core.Session;

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

    public void saveRequest() {
        createRequestTable(TABLE_NAME);

        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME).append("(id, id_book, id_user, req_books, returned, timestamp)")
                .append("VALUES (").append(requestId)
                .append(", ").append(id_book)
                .append(", ").append(id_user)
                .append(", ").append(requestedBooks)
                .append(", ").append(returned)
                .append(", ").append(System.currentTimeMillis())
                .append(");");

        String query = sb.toString();
        System.out.println(query);
        execute(query);
    }

    public void saveRetrunRequest() {
        createRequestTable(TABLE_NAME);

        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME).append("(id, id_book, id_user, req_books, returned, timestamp)")
                .append("VALUES (").append(requestId)
                .append(", ").append(id_book)
                .append(", ").append(id_user)
                .append(", ").append(requestedBooks)
                .append(", ").append(returned)
                .append(", ").append(System.currentTimeMillis())
                .append(");");

        String query = sb.toString();
        execute(query);
    }
}
