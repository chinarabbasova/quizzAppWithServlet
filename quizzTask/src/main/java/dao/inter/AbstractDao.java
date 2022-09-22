package dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDao {
    public Connection connect() throws Exception{
        Class.forName("org.postgresql.Driver");
        String url1 = "jdbc:postgresql:postgres";
        String username = "postgres";
        String password = "admin";
        Connection c = DriverManager.getConnection(url1, username, password);
        return c;
    }
}
