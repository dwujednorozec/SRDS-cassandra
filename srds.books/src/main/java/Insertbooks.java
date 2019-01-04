import CassBackend.Backend;

public class Insertbooks {

    public void insertbookByTitle() {

        String query1 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"

                + " VALUES(1,'Potop', 10, 10);" ;

        String query2 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"


                + " VALUES(1,'Dziady', 100, 120);" ;

        String query3 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"

                + " VALUES(1,'Hari Pota', 20, 80);" ;

        String query4 = "INSERT INTO book_table (id_book, book_name, nr_of_free_books, total_books)"

                + " VALUES(1,'Hari Pota 2', 10, 180);" ;


        Backend backend = new Backend("config.properties");


        session = backend.getSession();

        session.execute(query1);

        session.execute(query2);

        session.execute(query3);

        session.execute(query4);
    }

}
