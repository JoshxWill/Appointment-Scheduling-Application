package utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**Database Query Class**/
public class DatabaseQuery {
    private static Statement statement;
    public static void setApptStatement(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }

    public static Statement getApptStatement() {
        return statement;
    }
}
