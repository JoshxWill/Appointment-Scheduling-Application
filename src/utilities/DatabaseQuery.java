package utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**Database Query Class**/
public class DatabaseQuery {
    private static Statement statement;

    /**
     * Set Statement
     * @param connection Database Connection
     * @throws SQLException SQLLoader
     */
    public static void setApptStatement(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }

    /**
     * Getter Appointment Statement
     * @return Statement
     */
    public static Statement getApptStatement() {
        return statement;
    }
}
