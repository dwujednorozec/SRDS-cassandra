

public class RequestBook extends CassandraTableModel {
    private static final String TABLE_NAME = "BookRequest";
    private int requestId;
    private int id_book;
    private int id_user;
    private int availableBooks;
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

    public int getAvailableBooks() {
        return availableBooks;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
