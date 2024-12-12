package org.app.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Queries {




    // Method to fetch and print all columns for "white" wine
    public void fetchData(Connection conn) {
        // SQL query to select all columns from the 'wine' table

//        String sql = "select fixed acidity,volatile acidity,citric acid,residual sugar,chlorides,free sulfur dioxide,total sulfur dioxide,density,pH,sulphates,alcohol,quality,color from wine"; // 'wine' is the table name (adjust if needed)
        String sql = "Select * from wine";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the color : ");
            String reqColor = input.next();
            while (rs.next()) {
                // Retrieve the color column to check if it's white
                String color = rs.getString("color");
                if (reqColor.equalsIgnoreCase(color)) {
                    // Print all columns when the color is "white"
                    System.out.print("fixed acidity: " + rs.getDouble("fixed acidity") + " ");
                    System.out.print("volatile acidity: " + rs.getDouble("volatile acidity")+ " ");
                    System.out.print("citric acid: " + rs.getDouble("citric acid")+ " ");
                    System.out.print("residual sugar: " + rs.getDouble("residual sugar")+ " ");
                    System.out.print("chlorides: " + rs.getDouble("chlorides")+ " ");
                    System.out.print("free sulfur dioxide: " + rs.getInt("free sulfur dioxide")+ " ");
                    System.out.print("total sulfur dioxide: " + rs.getInt("total sulfur dioxide")+ " ");
                    System.out.print("density: " + rs.getDouble("density")+ " ");
                    System.out.print("pH: " + rs.getDouble("pH")+ " ");
                    System.out.print("sulphates: " + rs.getDouble("sulphates")+ " ");
                    System.out.print("alcohol: " + rs.getDouble("alcohol")+ " ");
                    System.out.print("quality: " + rs.getString("quality")+ " ");
                    System.out.print("color: " + rs.getString("color") + "\n");
                }
            }

        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
        }
    }
    public void fetchpH(Connection conn) {
        String sql = "Select pH,color from wine";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the color");
            String reqColor = input.next();
            while (rs.next()) {
                String color = rs.getString("color");
                if (reqColor.equalsIgnoreCase(color)) {
                    System.out.print("pH: " + rs.getDouble("pH")+ " ");
                    System.out.print("color: " + rs.getString("color") + "\n");
                }
            }

        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
        }




    }


    public void counterOfRedWhite(Connection conn) {
        String sql = "SELECT color FROM wine;";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            String white = "white", red = "red";
            int white_counter = 0, red_counter = 0;
            while (rs.next()) {
                String color = rs.getString("color");
                if (white.equalsIgnoreCase(color)) {
                    white_counter++;
                }else if(red.equalsIgnoreCase(color)){
                    red_counter++;
                }
            }
            System.out.println("Number of Whites : " + white_counter);
            System.out.println("Number of Reds : " + red_counter);
            System.out.println("Number of reds & whites : " + ( white_counter + red_counter));

        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
        }
    }

    public void phSmallerThanSix(Connection conn) {
        String sql = "Select * from wine";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            int i = 0;
            while (rs.next()) {
                double ph = rs.getDouble("pH");
                if (ph < 6) {
                    System.out.print("ID: " + ++i);
                    System.out.print(" fixed acidity: " + rs.getDouble("fixed acidity") + " ");
                    System.out.print("volatile acidity: " + rs.getDouble("volatile acidity")+ " ");
                    System.out.print("citric acid: " + rs.getDouble("citric acid")+ " ");
                    System.out.print("residual sugar: " + rs.getDouble("residual sugar")+ " ");
                    System.out.print("chlorides: " + rs.getDouble("chlorides")+ " ");
                    System.out.print("free sulfur dioxide: " + rs.getInt("free sulfur dioxide")+ " ");
                    System.out.print("total sulfur dioxide: " + rs.getInt("total sulfur dioxide")+ " ");
                    System.out.print("density: " + rs.getDouble("density")+ " ");
                    System.out.print("pH: " + rs.getDouble("pH")+ " ");
                    System.out.print("sulphates: " + rs.getDouble("sulphates")+ " ");
                    System.out.print("alcohol: " + rs.getDouble("alcohol")+ " ");
                    System.out.print("quality: " + rs.getString("quality")+ " ");
                    System.out.print("color: " + rs.getString("color") + "\n");
                }
            }

        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
        }
    }

    public void selectMaxMinValue(Connection conn) {
        String query = "select * from wine;";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            int maximum = Integer.MIN_VALUE, minimum = Integer.MAX_VALUE;
            while(rs.next()){
                if (rs.getInt("free sulfur dioxide") > maximum){
                    maximum = rs.getInt("free sulfur dioxide");
                }
                if (rs.getInt("free sulfur dioxide") < minimum){
                    minimum = rs.getInt("free sulfur dioxide");
                }
            }
            System.out.println("maximum free sulfur dioxide is " + maximum);
            System.out.println("minimum free sulfur dioxide is " + minimum);
        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
        }

    }







    public void showData() {
        // Create an instance of SQLDatabaseConnection
        SQLDatabaseConnection connectionInstance = new SQLDatabaseConnection();

        try (Connection conn = connectionInstance.connect()) {
            // Now you can use the conn object for queries
            String sql = "Select * from wine";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Retrieve the color column to check if it's white
                    // Print all columns when the color is "white"
                    System.out.print("fixed acidity: " + rs.getDouble("fixed acidity") + " ");
                    System.out.print("volatile acidity: " + rs.getDouble("volatile acidity")+ " ");
                    System.out.print("citric acid: " + rs.getDouble("citric acid")+ " ");
                    System.out.print("residual sugar: " + rs.getDouble("residual sugar")+ " ");
                    System.out.print("chlorides: " + rs.getDouble("chlorides")+ " ");
                    System.out.print("free sulfur dioxide: " + rs.getInt("free sulfur dioxide")+ " ");
                    System.out.print("total sulfur dioxide: " + rs.getInt("total sulfur dioxide")+ " ");
                    System.out.print("density: " + rs.getDouble("density")+ " ");
                    System.out.print("pH: " + rs.getDouble("pH")+ " ");
                    System.out.print("sulphates: " + rs.getDouble("sulphates")+ " ");
                    System.out.print("alcohol: " + rs.getDouble("alcohol")+ " ");
                    System.out.print("quality: " + rs.getString("quality")+ " ");
                    System.out.print("color: " + rs.getString("color") + "\n");

                }
            } catch (SQLException e) {
                System.out.println("Query Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
