
package org.app.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLDatabaseConnection {
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "amir";
    private static final Logger LOGGER = Logger.getLogger(SQLDatabaseConnection.class.getName());

    private final Map<String, Connection> connections = new HashMap<>();

    /**
     * Connects to the default database "wine".
     *
     * @return Connection object or null if the connection fails.
     */
    public Connection connect() {
        return connect("wine");
    }

    /**
     * Connects to a specific database.
     *
     * @param databaseName Name of the database to connect to.
     * @return Connection object or null if the connection fails.
     */
    public Connection connect(String databaseName) {
        if (connections.containsKey(databaseName)) {
            Connection existingConnection = connections.get(databaseName);
            try {
                if (existingConnection != null && existingConnection.isValid(2)) {
                    LOGGER.info("Already connected to database: " + databaseName);
                    return existingConnection;
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Connection check failed, reconnecting...", e);
            }
        }

        String url = BASE_URL + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, USER, PASSWORD);
            connections.put(databaseName, conn);
            LOGGER.info("Connected to database: " + databaseName);
            return conn;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Error: ", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Driver not found: ", e);
        }
        return null;
    }

    /**
     * Closes the connection to a specific database.
     *
     * @param databaseName Name of the database connection to close.
     */
    public void closeConnection(String databaseName) {
        if (connections.containsKey(databaseName)) {
            try (Connection conn = connections.get(databaseName)) {
                conn.close();
                connections.remove(databaseName);
                LOGGER.info("Connection to " + databaseName + " closed.");
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing connection: ", e);
            }
        } else {
            LOGGER.warning("No active connection to " + databaseName);
        }
    }

    /**
     * Closes all active database connections.
     */
    public void closeAllConnections() {
        for (String dbName : connections.keySet()) {
            closeConnection(dbName);
        }
        connections.clear();
        LOGGER.info("All database connections closed.");
    }
}







//package org.app.Server;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class SQLDatabaseConnection {
//    private static final String URL = "jdbc:mysql://localhost:3306/wine";
//    private static final String USER = "root";
//    private static final String PASSWORD = "amir";
//
//    public Connection connect()  {
//        Connection conn = null;
//        try {
//            // Register the JDBC driver (optional in newer versions of JDBC)
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish the connection
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected to the database successfully!");
//        } catch (SQLException e) {
//            System.out.println("SQL Error: " + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver not found: " + e.getMessage());
//        }
//        return conn;
//}
//}
//
//
//
//
//









//
//package org.app.Server;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SQLDatabaseConnection {
//    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
//    private static final String USER = "root";
//    private static final String PASSWORD = "amir";
//
//    private final Map<String, Connection> connections = new HashMap<>();
//
//    // חיבור למסד הנתונים "wine" כברירת מחדל
//    public Connection connect() {
//        return connect("wine");
//    }
//
//    // חיבור למסד נתונים עם שם מותאם אישית
//    public Connection connect(String databaseName) {
//        if (connections.containsKey(databaseName)) {
//            System.out.println("Already connected to database: " + databaseName);
//            return connections.get(databaseName);
//        }
//
//        String url = BASE_URL + databaseName;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(url, USER, PASSWORD);
//            connections.put(databaseName, conn);
//            System.out.println("Connected to database: " + databaseName);
//            return conn;
//        } catch (SQLException e) {
//            System.out.println("SQL Error: " + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver not found: " + e.getMessage());
//        }
//        return null;
//    }
//
//    public void closeConnection(String databaseName) {
//        if (connections.containsKey(databaseName)) {
//            try {
//                connections.get(databaseName).close();
//                connections.remove(databaseName);
//                System.out.println("Connection to " + databaseName + " closed.");
//            } catch (SQLException e) {
//                System.out.println("Error closing connection: " + e.getMessage());
//            }
//        } else {
//            System.out.println("No active connection to " + databaseName);
//        }
//    }
//
//    public void closeAllConnections() {
//        for (String dbName : connections.keySet()) {
//            closeConnection(dbName);
//        }
//        connections.clear();
//        System.out.println("All database connections closed.");
//    }
//}







//
//package org.app.Server;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SQLDatabaseConnection {
//    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
//    private static final String USER = "root";
//    private static final String PASSWORD = "amir";
//
//    private final Map<String, Connection> connections = new HashMap<>();
//
//    public Connection connect(String databaseName) {
//        if (connections.containsKey(databaseName)) {
//            System.out.println("Already connected to database: " + databaseName);
//            return connections.get(databaseName);
//        }
//
//        String url = BASE_URL + databaseName;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(url, USER, PASSWORD);
//            connections.put(databaseName, conn);
//            System.out.println("Connected to database: " + databaseName);
//            return conn;
//        } catch (SQLException e) {
//            System.out.println("SQL Error: " + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver not found: " + e.getMessage());
//        }
//        return null;
//    }
//
//    public void closeConnection(String databaseName) {
//        if (connections.containsKey(databaseName)) {
//            try {
//                connections.get(databaseName).close();
//                connections.remove(databaseName);
//                System.out.println("Connection to " + databaseName + " closed.");
//            } catch (SQLException e) {
//                System.out.println("Error closing connection: " + e.getMessage());
//            }
//        } else {
//            System.out.println("No active connection to " + databaseName);
//        }
//    }
//
//    public void closeAllConnections() {
//        for (String dbName : connections.keySet()) {
//            closeConnection(dbName);
//        }
//        connections.clear();
//        System.out.println("All database connections closed.");
//    }
//}







//
//package org.app.Server;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SQLDatabaseConnection {
//    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
//    private static final String USER = "root";
//    private static final String PASSWORD = "amir";
//
//    // Map לשמירת חיבורים פעילים
//    private final Map<String, Connection> connections = new HashMap<>();
//
//    // חיבור ברירת מחדל למסד הנתונים "wine"
//    public Connection connect() {
//        return connect("wine");
//    }
//
//    // חיבור למסד נתונים לפי שם
//    public Connection connect(String databaseName) {
//        if (connections.containsKey(databaseName)) {
//            return connections.get(databaseName); // החזרת חיבור קיים
//        }
//
//        String url = BASE_URL + databaseName;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(url, USER, PASSWORD);
//            connections.put(databaseName, conn); // שמירת החיבור במפה
//            System.out.println("Connected to database: " + databaseName + " successfully!");
//            return conn;
//        } catch (SQLException e) {
//            System.out.println("SQL Error: " + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver not found: " + e.getMessage());
//        }
//
//        return null;
//    }
//
//    // סגירת חיבור של מסד נתונים מסוים
//    public void closeConnection(String databaseName) {
//        if (connections.containsKey(databaseName)) {
//            try {
//                connections.get(databaseName).close();
//                connections.remove(databaseName);
//                System.out.println("Connection to " + databaseName + " closed.");
//            } catch (SQLException e) {
//                System.out.println("Error closing connection: " + e.getMessage());
//            }
//        }
//    }
//
//    // סגירת כל החיבורים
//    public void closeAllConnections() {
//        for (String dbName : connections.keySet()) {
//            closeConnection(dbName);
//        }
//    }
//}


