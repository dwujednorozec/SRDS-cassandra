import com.datastax.driver.core.Session;
import static com.datastax.driver.core.schemabuilder.SchemaBuilder.createTable;



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
        createTable(TABLE_NAME);

        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME).append("(id, id_book, id_user, timestamp)")
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

}
