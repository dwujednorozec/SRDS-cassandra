import CassBackend.Backend;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

public class Insertbooks extends CassandraTableModel{
 //   private Session session;
    private static final String TABLE_NAME = "BookRequest";
    private List<String> titles = new ArrayList<String>();
//    private int id_book;
//    private String book_name;
//    private int nr_of_free_books;
//    private int total_books;

    public Insertbooks(Session session) {
        super(session);
    }


    //  private int requestedBooks;

//    public Insertbooks(Backend backend) {
//        this.session = backend.getSession();
//    }

//    public void insertbookByTitle() {
//
//        String query1 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"
//
//                + " VALUES(1,'Potop', 10, 10);" ;
//
//        String query2 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"
//
//
//                + " VALUES(1,'Dziady', 100, 120);" ;
//
//        String query3 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"
//
//                + " VALUES(1,'Hari Pota', 20, 80);" ;
//
//        String query4 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"
//
//                + " VALUES(1,'Hari Pota 2', 10, 180);" ;
//
//
//        //wpisanie do bazy tytułów
//        session.execute(query1);
//
//        session.execute(query2);
//
//        session.execute(query3);
//
//        session.execute(query4);
//    }

    public void saveBooks(int id_book, String book_name, int nr_of_free_books, int total_books) {
        createBookTable(TABLE_NAME);

        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME).append("(id_book, book_name, nr_of_free_books, total_books)")
                .append("VALUES (").append(id_book)
                .append(", ").append(book_name)
                .append(", ").append(nr_of_free_books)
                .append(", ").append(total_books)
                .append(");");

        String query = sb.toString();
        execute(query);
        titles.add("book_name");
    }

    public List<String> getTitles() {
//        List<String> titles = new ArrayList<String>();
//        titles.add("Potop");
//        titles.add("Dziady");
//        titles.add("Hari Pota");
//        titles.add("Hari Pota 2");
        return titles;
        //return new String[] { "Potop, Dziady, Hari Pota, Hari Pota 2" };
    }
}
