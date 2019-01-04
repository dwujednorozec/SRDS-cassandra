

public class RequestBook extends CassandraTableModel {
    private static final String TABLE_NAME = "BookRequest";
    private int requestId;
    private int id_book;
    private int id_user;
    private int requestedBooks;
    private long timestamp;


    public int getRequestId() {
        return requestId;
    }

    public int getBookID() {
        return id_book;
    }

    public int getUserid() {
        return id_user;
    }

    public int getRequestedBooks() {
        return requestedBooks;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public RequestBook(Session session, int requestId, int id_book, int id_user, int requestedBooks) {
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
                .append("VALUES (").append(UUID.randomUUID())
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
