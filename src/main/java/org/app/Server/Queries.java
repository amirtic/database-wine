package org.app.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Queries {

    // Method to fetch and print all columns for the specified color
//    public String fetchData(Connection conn) {
//        StringBuilder result = new StringBuilder();
//        String sql = "Select * from wine";
//        try (PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            Scanner input = new Scanner(System.in);
//            System.out.print("Enter the color: ");
//            String reqColor = input.next();
//            while (rs.next()) {
//                String color = rs.getString("color");
//                if (reqColor.equalsIgnoreCase(color)) {
//                    result.append("fixed acidity: ").append(rs.getDouble("fixed acidity")).append(" ");
//                    result.append("volatile acidity: ").append(rs.getDouble("volatile acidity")).append(" ");
//                    result.append("citric acid: ").append(rs.getDouble("citric acid")).append(" ");
//                    result.append("residual sugar: ").append(rs.getDouble("residual sugar")).append(" ");
//                    result.append("chlorides: ").append(rs.getDouble("chlorides")).append(" ");
//                    result.append("free sulfur dioxide: ").append(rs.getInt("free sulfur dioxide")).append(" ");
//                    result.append("total sulfur dioxide: ").append(rs.getInt("total sulfur dioxide")).append(" ");
//                    result.append("density: ").append(rs.getDouble("density")).append(" ");
//                    result.append("pH: ").append(rs.getDouble("pH")).append(" ");
//                    result.append("sulphates: ").append(rs.getDouble("sulphates")).append(" ");
//                    result.append("alcohol: ").append(rs.getDouble("alcohol")).append(" ");
//                    result.append("quality: ").append(rs.getString("quality")).append(" ");
//                    result.append("color: ").append(rs.getString("color")).append("\n");
//                }
//            }
//        } catch (SQLException e) {
//            result.append("Query Error: ").append(e.getMessage());
//        }
//        return result.toString();
//    }

//    public String fetchpH(Connection conn) {
//        StringBuilder result = new StringBuilder();
//        String sql = "Select pH, color from wine";
//        try (PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            Scanner input = new Scanner(System.in);
//            System.out.println("Enter the color:");
//            String reqColor = input.next();
//            while (rs.next()) {
//                String color = rs.getString("color");
//                if (reqColor.equalsIgnoreCase(color)) {
//                    result.append("pH: ").append(rs.getDouble("pH")).append(" ");
//                    result.append("color: ").append(rs.getString("color")).append("\n");
//                }
//            }
//        } catch (SQLException e) {
//            result.append("Query Error: ").append(e.getMessage());
//        }
//        return result.toString();
//    }

    public String counterOfRedWhite(Connection conn) {
        StringBuilder result = new StringBuilder();
        String sql = "SELECT color FROM wine;";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            String white = "white", red = "red";
            int white_counter = 0, red_counter = 0;
            while (rs.next()) {
                String color = rs.getString("color");
                if (white.equalsIgnoreCase(color)) {
                    white_counter++;
                } else if (red.equalsIgnoreCase(color)) {
                    red_counter++;
                }
            }
            result.append("Number of Whites: ").append(white_counter).append("\n");
            result.append("Number of Reds: ").append(red_counter).append("\n");
            result.append("Total of Reds & Whites: ").append(white_counter + red_counter).append("\n");

        } catch (SQLException e) {
            result.append("Query Error: ").append(e.getMessage());
        }
        return result.toString();
    }

//    public String phSmallerThanSix(Connection conn) {
//        StringBuilder result = new StringBuilder();
//        String sql = "Select * from wine";
//        try (PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            int i = 0;
//            while (rs.next()) {
//                double ph = rs.getDouble("pH");
//                if (ph < 6) {
//                    result.append("ID: ").append(++i).append(" ");
//                    result.append("fixed acidity: ").append(rs.getDouble("fixed acidity")).append(" ");
//                    result.append("volatile acidity: ").append(rs.getDouble("volatile acidity")).append(" ");
//                    result.append("citric acid: ").append(rs.getDouble("citric acid")).append(" ");
//                    result.append("residual sugar: ").append(rs.getDouble("residual sugar")).append(" ");
//                    result.append("chlorides: ").append(rs.getDouble("chlorides")).append(" ");
//                    result.append("free sulfur dioxide: ").append(rs.getInt("free sulfur dioxide")).append(" ");
//                    result.append("total sulfur dioxide: ").append(rs.getInt("total sulfur dioxide")).append(" ");
//                    result.append("density: ").append(rs.getDouble("density")).append(" ");
//                    result.append("pH: ").append(rs.getDouble("pH")).append(" ");
//                    result.append("sulphates: ").append(rs.getDouble("sulphates")).append(" ");
//                    result.append("alcohol: ").append(rs.getDouble("alcohol")).append(" ");
//                    result.append("quality: ").append(rs.getString("quality")).append(" ");
//                    result.append("color: ").append(rs.getString("color")).append("\n");
//                }
//            }
//        } catch (SQLException e) {
//            result.append("Query Error: ").append(e.getMessage());
//        }
//        return result.toString();
//    }

    public String selectMaxMinValue(Connection conn) {
        StringBuilder result = new StringBuilder();
        String query = "select * from wine;";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            int maximum = Integer.MIN_VALUE, minimum = Integer.MAX_VALUE;
            while (rs.next()) {
                int freeSulfurDioxide = rs.getInt("free sulfur dioxide");
                if (freeSulfurDioxide > maximum) {
                    maximum = freeSulfurDioxide;
                }
                if (freeSulfurDioxide < minimum) {
                    minimum = freeSulfurDioxide;
                }
            }
            result.append("Maximum free sulfur dioxide: ").append(maximum).append("\n");
            result.append("Minimum free sulfur dioxide: ").append(minimum).append("\n");
        } catch (SQLException e) {
            result.append("Query Error: ").append(e.getMessage());
        }
        return result.toString();
    }

    public String showData(Connection conn) {
        StringBuilder result = new StringBuilder();
        String sql = "Select * from wine";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                result.append("fixed acidity: ").append(rs.getDouble("fixed acidity")).append(" ");
                result.append("volatile acidity: ").append(rs.getDouble("volatile acidity")).append(" ");
                result.append("citric acid: ").append(rs.getDouble("citric acid")).append(" ");
                result.append("residual sugar: ").append(rs.getDouble("residual sugar")).append(" ");
                result.append("chlorides: ").append(rs.getDouble("chlorides")).append(" ");
                result.append("free sulfur dioxide: ").append(rs.getInt("free sulfur dioxide")).append(" ");
                result.append("total sulfur dioxide: ").append(rs.getInt("total sulfur dioxide")).append(" ");
                result.append("density: ").append(rs.getDouble("density")).append(" ");
                result.append("pH: ").append(rs.getDouble("pH")).append(" ");
                result.append("sulphates: ").append(rs.getDouble("sulphates")).append(" ");
                result.append("alcohol: ").append(rs.getDouble("alcohol")).append(" ");
                result.append("quality: ").append(rs.getString("quality")).append(" ");
                result.append("color: ").append(rs.getString("color")).append("\n");
            }
        } catch (SQLException e) {
            result.append("Query Error: ").append(e.getMessage());
        }
        return result.toString();
    }


    public String fetchpHByColor(Connection conn, String reqColor) {
        StringBuilder result = new StringBuilder();
        String sql = "SELECT pH, color FROM wine";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String color = rs.getString("color");
                if (reqColor.equalsIgnoreCase(color)) {
                    result.append("pH: ").append(rs.getDouble("pH")).append(" ");
                    result.append("color: ").append(rs.getString("color")).append("\n");
                }
            }
        } catch (SQLException e) {
            result.append("Query Error: ").append(e.getMessage());
        }
        return result.toString();
    }



    public static String phSmallerThan(Connection connection, double pHValue) {
        String query = "SELECT * FROM wine WHERE pH < ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, pHValue); // تعيين قيمة pH المدخلة في الاستعلام
            ResultSet rs = pstmt.executeQuery();

            StringBuilder result = new StringBuilder();
            int i = 0;  // عداد لتتبع رقم الخمر
            while (rs.next()) {
                result.append("Wine ID: ").append(++i).append("\n");
                result.append("fixed acidity: ").append(rs.getDouble("fixed acidity")).append(" ");
                    result.append("volatile acidity: ").append(rs.getDouble("volatile acidity")).append(" ");
                    result.append("citric acid: ").append(rs.getDouble("citric acid")).append(" ");
                    result.append("residual sugar: ").append(rs.getDouble("residual sugar")).append(" ");
                    result.append("chlorides: ").append(rs.getDouble("chlorides")).append(" ");
                    result.append("free sulfur dioxide: ").append(rs.getInt("free sulfur dioxide")).append(" ");
                    result.append("total sulfur dioxide: ").append(rs.getInt("total sulfur dioxide")).append(" ");
                    result.append("density: ").append(rs.getDouble("density")).append(" ");
                    result.append("pH: ").append(rs.getDouble("pH")).append(" ");
                    result.append("sulphates: ").append(rs.getDouble("sulphates")).append(" ");
                    result.append("alcohol: ").append(rs.getDouble("alcohol")).append(" ");
                    result.append("quality: ").append(rs.getString("quality")).append(" ");
                    result.append("color: ").append(rs.getString("color")).append("\n");
                result.append("---------------\n");
            }

            if (i == 0) {
                result.append("No wines found with pH smaller than ").append(pHValue).append(".");
            }

            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred while fetching data.";
        }
    }




}