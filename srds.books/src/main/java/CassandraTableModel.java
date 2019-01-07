import com.datastax.driver.core.*;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;

public class CassandraTableModel {
    protected Session session;

    public CassandraTableModel(Session session) {
        this.session = session;
    }

    public void createRequestTable(String tableName) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(tableName).append("(")
                .append("id uuid,")
                .append("id_book int,")
                .append("id_user int,")
                .append("req_books int,")
                .append("returned boolean,")
                .append("timestamp bigint,")
                .append("PRIMARY KEY (id, id_book));");
        //nie wiem czy nie dobrze by bylo dodac returned do klucza zeby tylko przetrzymywane dawalo

        String query = sb.toString();
        session.execute(query);
    }

    public void createBookTable(String tableName) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(tableName).append("(")
                .append("id_book int,")
                .append("book_name text,")
                .append("nr_of_free_books int,")
                .append("total_books int,")
                .append("PRIMARY KEY (id_book, book_name));");
                //.append("PRIMARY KEY (id_book));");

        String query = sb.toString();
        session.execute(query);

    }

    public ResultSet execute(String query) {;
        return session.execute(query);
    }

    public ResultSet executeQuorum(String query) {
        Statement statement = new SimpleStatement(query).setConsistencyLevel(ConsistencyLevel.QUORUM);
        return session.execute(statement);
    }
}
