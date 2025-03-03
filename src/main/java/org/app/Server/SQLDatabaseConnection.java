//
//package org.app.Server;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class SQLDatabaseConnection {
//    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
//    private static final String USER = "root";
//    private static final String PASSWORD = "amir";
//    private static final Logger LOGGER = Logger.getLogger(SQLDatabaseConnection.class.getName());
//
//    private final Map<String, Connection> connections = new HashMap<>();
//
//    /**
//     * Connects to the default database "wine".
//     *
//     * @return Connection object or null if the connection fails.
//     */
//    public Connection connect() {
//        return connect("wine");
//    }
//
//    /**
//     * Connects to a specific database.
//     *
//     * @param databaseName Name of the database to connect to.
//     * @return Connection object or null if the connection fails.
//     */
//    public Connection connect(String databaseName) {
//        if (connections.containsKey(databaseName)) {
//            Connection existingConnection = connections.get(databaseName);
//            try {
//                if (existingConnection != null && existingConnection.isValid(2)) {
//                    LOGGER.info("Already connected to database: " + databaseName);
//                    return existingConnection;
//                }
//            } catch (SQLException e) {
//                LOGGER.log(Level.WARNING, "Connection check failed, reconnecting...", e);
//            }
//        }
//
//        String url = BASE_URL + databaseName;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(url, USER, PASSWORD);
//            connections.put(databaseName, conn);
//            LOGGER.info("Connected to database: " + databaseName);
//            return conn;
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "SQL Error: ", e);
//        } catch (ClassNotFoundException e) {
//            LOGGER.log(Level.SEVERE, "Driver not found: ", e);
//        }
//        return null;
//    }
//
//    /**
//     * Closes the connection to a specific database.
//     *
//     * @param databaseName Name of the database connection to close.
//     */
//    public void closeConnection(String databaseName) {
//        if (connections.containsKey(databaseName)) {
//            try (Connection conn = connections.get(databaseName)) {
//                conn.close();
//                connections.remove(databaseName);
//                LOGGER.info("Connection to " + databaseName + " closed.");
//            } catch (SQLException e) {
//                LOGGER.log(Level.WARNING, "Error closing connection: ", e);
//            }
//        } else {
//            LOGGER.warning("No active connection to " + databaseName);
//        }
//    }
//
//    /**
//     * Closes all active database connections.
//     */
//    public void closeAllConnections() {
//        for (String dbName : connections.keySet()) {
//            closeConnection(dbName);
//        }
//        connections.clear();
//        LOGGER.info("All database connections closed.");
//    }
//}




//package org.app.Server;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class SQLDatabaseConnection {
//    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
//    private static final Logger LOGGER = Logger.getLogger(SQLDatabaseConnection.class.getName());
//    private Properties properties;
//
//    public SQLDatabaseConnection() {
//        properties = new Properties();
//        try (FileInputStream fis = new FileInputStream("config.properties")) {
//            properties.load(fis);
//        } catch (IOException e) {
//            LOGGER.log(Level.SEVERE, "Error loading config properties", e);
//        }
//    }
//
//    /**
//     * Connects to the database as specified in the config properties.
//     *
//     * @return Connection object or null if the connection fails.
//     */
//    public Connection connect() {
//        String databaseName = properties.getProperty("database");
//        String user = properties.getProperty("user");
//        String password = properties.getProperty("password");
//        String url = BASE_URL + databaseName;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(url, user, password);
//            LOGGER.info("Connected to database: " + databaseName);
//            return conn;
//        } catch (SQLException | ClassNotFoundException e) {
//            LOGGER.log(Level.SEVERE, "Connection Error: ", e);
//            return null;
//        }
//    }
//
//    /**
//     * Closes the connection to the database.
//     */
//    public void closeConnection(Connection conn) {
//        if (conn != null) {
//            try {
//                conn.close();
//                LOGGER.info("Connection closed.");
//            } catch (SQLException e) {
//                LOGGER.log(Level.WARNING, "Error closing connection: ", e);
//            }
//        }
//    }
//}






package org.app.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/wine";
    private static final String USER = "root";
    private static final String PASSWORD = "amir";

    public Connection connect()  {
        Connection conn = null;
        try {
            // Register the JDBC driver (optional in newer versions of JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
        return conn;
}
}






//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class SQLDatabaseConnection {
//    private static final String CONFIG_FILE = "config.properties";  // קובץ הקונפיגורציה
//
//    public Connection connect() {
//        Properties props = new Properties();
//        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
//            // טוען את פרטי ההתחברות מקובץ ה- config
//            props.load(fis);
//            String url = props.getProperty("db.url");
//            String user = props.getProperty("db.user");
//            String password = props.getProperty("db.password");
//
//            // יצירת החיבור למסד הנתונים
//            Connection conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected to the database successfully!");
//            return conn;
//        } catch (IOException e) {
//            System.err.println("Failed to load config file: " + e.getMessage());
//        } catch (SQLException e) {
//            System.err.println("SQL Error: " + e.getMessage());
//        }
//        return null;
//    }
//}
//




