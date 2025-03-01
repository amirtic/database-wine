

package org.app.Server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.wine2.Wine;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queries {
    private static final Logger LOGGER = Logger.getLogger(Queries.class.getName());

    /**
     * Fetches the pH values of wines based on their color.
     *
     * @param conn     Database connection
     * @param reqColor Requested wine color
     * @return A string containing pH values
     */
    public String fetchpHByColor(Connection conn, String reqColor) {
        StringBuilder result = new StringBuilder();
        String sql = "SELECT pH FROM wine WHERE color = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reqColor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.append("pH: ").append(rs.getDouble("pH")).append("\n");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Query Error: ", e);
            return "Query Error: " + e.getMessage();
        }
        return result.toString();
    }

    /**
     * Counts the number of red and white wines in the database.
     *
     * @param conn Database connection
     * @return A string containing the count of red and white wines
     */
    public String counterOfRedWhite(Connection conn) {
        String sql = "SELECT color, COUNT(*) AS count FROM wine WHERE color IN ('red', 'white') GROUP BY color";
        int whiteCount = 0, redCount = 0;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String color = rs.getString("color");
                int count = rs.getInt("count");
                if ("white".equalsIgnoreCase(color)) {
                    whiteCount = count;
                } else if ("red".equalsIgnoreCase(color)) {
                    redCount = count;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Query Error: ", e);
            return "Query Error: " + e.getMessage();
        }
        return String.format("Number of Whites: %d\nNumber of Reds: %d\nTotal: %d\n", whiteCount, redCount, whiteCount + redCount);
    }

    /**
     * Retrieves a list of wines with pH lower than the specified value.
     *
     * @param connection Database connection
     * @param pHValue    Maximum pH value
     * @return ObservableList of Wine objects
     */
    public static ObservableList<Wine> phSmallerThan(Connection connection, double pHValue) {
        ObservableList<Wine> filteredWines = FXCollections.observableArrayList();
        String query = "SELECT * FROM wine WHERE pH < ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, pHValue);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Wine wine = new Wine(
                            rs.getDouble("fixed acidity"),
                            rs.getDouble("volatile acidity"),
                            rs.getDouble("citric acid"),
                            rs.getDouble("residual sugar"),
                            rs.getDouble("chlorides"),
                            rs.getDouble("free sulfur dioxide"),
                            rs.getDouble("total sulfur dioxide"),
                            rs.getDouble("density"),
                            rs.getDouble("pH"),
                            rs.getDouble("sulphates"),
                            rs.getDouble("alcohol"),
                            rs.getString("quality"),
                            rs.getString("color"),
                            rs.getInt("id"),
                            rs.getString("hire_date")
                    );
                    filteredWines.add(wine);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Query Error: ", e);
        }
        return filteredWines;
    }

    /**
     * Fetches the maximum and minimum free sulfur dioxide values from the database.
     *
     * @param conn Database connection
     * @return A string containing the max and min values
     */
    public String selectMaxMinValue(Connection conn) {
        String query = "SELECT MAX(`free sulfur dioxide`) AS maxSulfur, MIN(`free sulfur dioxide`) AS minSulfur FROM wine";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return String.format("Max free sulfur dioxide: %.2f\nMin free sulfur dioxide: %.2f",
                                     rs.getDouble("maxSulfur"), rs.getDouble("minSulfur"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Query Error: ", e);
            return "Query Error: " + e.getMessage();
        }
        return "No data found.";
    }
}








//package org.app.Server;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import org.app.wine2.Wine;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//public class Queries {
//
//
//    public String fetchpHByColor(Connection conn, String reqColor) {
//        StringBuilder result = new StringBuilder();
//        String sql = "SELECT pH FROM wine WHERE color = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, reqColor);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    result.append("pH: ").append(rs.getDouble("pH")).append("\n");
//                }
//            }
//        } catch (SQLException e) {
//            result.append("Query Error: ").append(e.getMessage());
//        }
//        return result.toString();
//    }
//
//
//
//
//    public String counterOfRedWhite(Connection conn) {
//        StringBuilder result = new StringBuilder();
//        String sql = "SELECT color, COUNT(*) AS count FROM wine WHERE color IN ('red', 'white') GROUP BY color";
//
//        int whiteCount = 0, redCount = 0;
//        try (PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                String color = rs.getString("color");
//                int count = rs.getInt("count");
//                if ("white".equalsIgnoreCase(color)) {
//                    whiteCount = count;
//                } else if ("red".equalsIgnoreCase(color)) {
//                    redCount = count;
//                }
//            }
//            result.append("Number of Whites: ").append(whiteCount).append("\n")
//                    .append("Number of Reds: ").append(redCount).append("\n")
//                    .append("Total of Reds & Whites: ").append(whiteCount + redCount).append("\n");
//        } catch (SQLException e) {
//            result.append("Query Error: ").append(e.getMessage());
//        }
//        return result.toString();
//    }
//
//
//
//
//
//
//
//    public static ObservableList<Wine> phSmallerThan(Connection connection, double pHValue) {
//        ObservableList<Wine> filteredWines = FXCollections.observableArrayList();
//
//        String query = "SELECT * FROM wine WHERE pH < ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setDouble(1, pHValue);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Wine wine = new Wine(
//                        rs.getDouble("fixed acidity"),
//                        rs.getDouble("volatile acidity"),
//                        rs.getDouble("citric acid"),
//                        rs.getDouble("residual sugar"),
//                        rs.getDouble("chlorides"),
//                        rs.getDouble("free sulfur dioxide"),
//                        rs.getDouble("total sulfur dioxide"),
//                        rs.getDouble("density"),
//                        rs.getDouble("pH"),
//                        rs.getDouble("sulphates"),
//                        rs.getDouble("alcohol"),
//                        rs.getString("quality"),
//                        rs.getString("color"),
//                        rs.getInt("id"),
//                        rs.getString("hire_date")
//                );
//
//                filteredWines.add(wine);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return filteredWines;
//    }
//
//
//
//
//    public String selectMaxMinValue(Connection conn) {
//        StringBuilder result = new StringBuilder();
//        String query = "SELECT MAX(`free sulfur dioxide`) AS maxSulfur, MIN(`free sulfur dioxide`) AS minSulfur FROM wine";
//        try (PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                result.append("Maximum free sulfur dioxide: ").append(rs.getDouble("maxSulfur")).append("\n")
//                        .append("Minimum free sulfur dioxide: ").append(rs.getDouble("minSulfur")).append("\n");
//            }
//        } catch (SQLException e) {
//            result.append("Query Error: ").append(e.getMessage());
//        }
//        return result.toString();
//    }
//
//
//}
//
//
//
//

