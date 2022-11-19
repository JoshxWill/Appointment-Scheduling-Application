package utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Data Connection
 *
 */
public class JDBC {
 private static final String protocol = "jdbc";
     private static final String vendor = ":mysql:";
         private static final String location = "//localhost/";
             private static final String databaseName = "client_schedule";
                 private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
        private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
        private static final String userName = "sqlUser"; // Username
        private static String password = "Passw0rd!"; // Password
        private static Connection connection = null;  // Connection Interface

    /**
     * Start Connection
     * @return Connection
     */
    public static Connection makeConnection() {

          try {
              Class.forName(driver); // Locate Driver
              //password = Details.getPassword(); // Assign password
              connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
              System.out.println("Connection successful!");
          }
                  catch(ClassNotFoundException e) {
                      e.printStackTrace();
                  }
                  catch(SQLException e) {
                      e.printStackTrace();
                  }
                return connection;
          }

    /**
     * Getter Connection
     * @return Connection
     */
    public static Connection getConnection() {

             return connection;
            }

    /**
     * Close Connection
     */
    public static void closeConnection() {
                 try {
                     connection.close();
                     System.out.println("Connection closed!");
                 }
                 catch (Exception e) {
                     e.printStackTrace();
                 }
             }
}