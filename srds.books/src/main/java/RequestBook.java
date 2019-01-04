

public class RequestBook extends CassandraTableModel {
    private static final String TABLE_NAME = "BookRequest";
    private int requestId;
    private int id_book;
    private int id_user;
    private int availableBooks;
    private long timestamp;

}
