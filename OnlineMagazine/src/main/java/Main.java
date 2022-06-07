import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/OnlineMagazine";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static void main(String[] args) {

        Connection conn = connect();

        try (Statement statement = conn.createStatement()) {
            createTableOrders(statement);

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    private static void createTableOrders(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE Orders (\n" +
                " OrderID int,\n" +
                " LastName varchar(255),\n" +
                " FirstName varchar(255),\n" +
                " Address varchar(255),\n" +
                " City varchar(255)\n" +
                ");");
    }

    public static Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgresSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
