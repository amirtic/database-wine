

package org.app.wine2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.app.Server.Queries;
import org.app.Server.SQLDatabaseConnection;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

/**
 * Controller for handling button actions in the application.
 * This controller is responsible for fetching and displaying data from the wine database.
 */
public class ButtonsController {

    private Queries queries;
    private Connection connection;

    private ObservableList<Wine> wineList = FXCollections.observableArrayList();

    /**
     * Initializes the controller by setting up queries and database connection.
     */
    public void initialize() {
        queries = new Queries();
        connection = new SQLDatabaseConnection().connect();
    }

    @FXML
    private ComboBox<String> colorComboBox;

    @FXML
    private TextField pHValueField;

    @FXML
    private TableView<Wine> wineTableView;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    /**
     * Handles the action of fetching all wine data from the database and displaying it in a new window.
     */
    @FXML
    private void handleFetchData() {
        SQLDatabaseConnection dbConnection = new SQLDatabaseConnection();
        Connection conn = dbConnection.connect();

        String query = "SELECT * FROM wine";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            wineList.clear();

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
                        rs.getString("hire_Date")
                );

                wineList.add(wine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FullScreenTable.fxml"));
            Parent root = loader.load();

            FullScreenTableController controller = loader.getController();
            controller.setTableData(wineList);

            Stage fullScreenStage = new Stage();
            controller.setStage(fullScreenStage);
            fullScreenStage.setScene(new Scene(root));
            fullScreenStage.setFullScreen(true);
            fullScreenStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of fetching the pH value by the selected color.
     * Displays the result in a text window.
     */
    @FXML
    private void handleFetchpH() {

        String selectedColor = colorComboBox.getValue();

        if (selectedColor != null) {
            String result = queries.fetchpHByColor(connection, selectedColor);
            showResultsInTextWindow(result);
        } else {
            showResultsInTextWindow("Please select a color first.");
        }
    }

    /**
     * Handles the action of counting the number of red and white wines.
     * Displays the result in a text window.
     */
    @FXML
    private void handleCounterRedWhite() {
        String result = queries.counterOfRedWhite(connection);
        showResultsInTextWindow(result);
    }

    /**
     * Handles the action of fetching wines with pH smaller than the entered value.
     * Validates the pH value and displays the results in a full-screen table.
     */
    @FXML
    private void handlePHSmallerThan() {
        String pHInput = pHValueField.getText();

        if (pHInput != null && !pHInput.isEmpty()) {
            try {
                double pHValue = Double.parseDouble(pHInput);

                if (pHValue < 2.73 || pHValue > 4.02) {
                    showResultsInTextWindow("The selected pH range is out of bounds. Please select a pH value between 2.73 and 4.02.");
                    return;
                }

                ObservableList<Wine> filteredWines = Queries.phSmallerThan(connection, pHValue);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FullScreenTable.fxml"));
                Parent root = loader.load();

                FullScreenTableController controller = loader.getController();
                controller.setTableData(filteredWines);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setFullScreen(true);
                stage.show();

            } catch (NumberFormatException e) {
                showResultsInTextWindow("Invalid pH value. Please enter a valid number between 2.73 and 4.02.");
            } catch (IOException e) {
                showResultsInTextWindow("An error occurred while loading the results window. Please try again.");
                e.printStackTrace();
            }
        } else {
            showResultsInTextWindow("Please enter a pH value between 2.73 and 4.01.");
        }
    }

    /**
     * Handles the action of selecting the maximum and minimum values for wine quality.
     * Displays the result in a text window.
     */
    @FXML
    private void handleSelectMaxMin() {
        String result = queries.selectMaxMinValue(connection);
        showResultsInTextWindow(result);
    }

    /**
     * Handles the action of filtering wines by the selected date range.
     * Validates the dates and displays the results in a full-screen table.
     */
    @FXML
    private void handleFilterByDate() {
        java.sql.Date minDate = java.sql.Date.valueOf("2022-01-01");
        java.sql.Date maxDate = java.sql.Date.valueOf("2024-12-10");

        if (startDatePicker.getValue() != null && endDatePicker.getValue() != null) {
            java.sql.Date startDate = java.sql.Date.valueOf(startDatePicker.getValue());
            java.sql.Date endDate = java.sql.Date.valueOf(endDatePicker.getValue());

            if (startDate.before(minDate) || startDate.after(maxDate) || endDate.before(minDate) || endDate.after(maxDate)) {
                showResultsInTextWindow("The selected date range is out of bounds. Please select a range between 2022-01-01 and 2024-12-10.");
                return;
            }

            ObservableList<Wine> filteredWines = filterWinesByDate(connection, startDate, endDate);

            if (!filteredWines.isEmpty()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FullScreenTable.fxml"));
                    Parent root = loader.load();

                    FullScreenTableController controller = loader.getController();
                    controller.setTableData(filteredWines);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setFullScreen(true);
                    stage.show();
                } catch (IOException e) {
                    showResultsInTextWindow("An error occurred while loading the results window. Please try again.");
                    e.printStackTrace();
                }
            } else {
                showResultsInTextWindow("No wines found in the selected date range.");
            }
        } else {
            showResultsInTextWindow("Please select both start and end dates between 2022-01-01 and 2024-12-10.");
        }
    }

    /**
     * Filters wines based on the specified date range.
     * @param connection The database connection.
     * @param startDate The start date for filtering.
     * @param endDate The end date for filtering.
     * @return A list of wines that match the date range.
     */
    private ObservableList<Wine> filterWinesByDate(Connection connection, java.sql.Date startDate, java.sql.Date endDate) {
        ObservableList<Wine> filteredWines = FXCollections.observableArrayList();
        String query = "SELECT * FROM wine WHERE hire_date BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);

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
            e.printStackTrace();
        }

        return filteredWines;
    }

    /**
     * Runs multiple database queries concurrently and displays the results.
     */
    @FXML
    private void handleRunQueries() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        final String[] result1 = new String[1];
        final String[] result2 = new String[1];

        CountDownLatch latch = new CountDownLatch(2);

        executor.submit(() -> {
            try {
                result1[0] = queries.fetchpHByColor(connection, "Red");
            } catch (Exception e) {
                result1[0] = "Error fetching data";
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        });

        executor.submit(() -> {
            try {
                result2[0] = queries.counterOfRedWhite(connection);
            } catch (Exception e) {
                result2[0] = "Error fetching data";
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        });

        executor.shutdown();

        new Thread(() -> {
            try {
                latch.await();
                Platform.runLater(() -> showResultInWindow(result1[0], result2[0]));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Displays the results of the queries in a new window.
     * @param result1 The result of the first query.
     * @param result2 The result of the second query.
     */
    private void showResultInWindow(String result1, String result2) {
        try {
            Label label1 = new Label("pH by Color (Red):");
            label1.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #2c3e50;");

            Label label2 = new Label("Red and White Wine Count:");
            label2.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #2c3e50;");

            TextArea query1TextArea = new TextArea(result1);
            query1TextArea.setEditable(false);
            query1TextArea.setWrapText(true);
            query1TextArea.setPrefWidth(600);
            query1TextArea.setPrefHeight(350);
            query1TextArea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #34495e; -fx-background-color: #ecf0f1; -fx-border-radius: 10px; -fx-padding: 10px;");

            TextArea query2TextArea = new TextArea(result2);
            query2TextArea.setEditable(false);
            query2TextArea.setWrapText(true);
            query2TextArea.setPrefWidth(600);
            query2TextArea.setPrefHeight(350);
            query2TextArea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #34495e; -fx-background-color: #ecf0f1; -fx-border-radius: 10px; -fx-padding: 10px;");

            VBox vbox1 = new VBox(20, label1, query1TextArea);
            vbox1.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f9; -fx-border-radius: 10px; -fx-effect: dropshadow(gaussian, #bdc3c7, 15, 0, 0, 5);");

            VBox vbox2 = new VBox(20, label2, query2TextArea);
            vbox2.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f9; -fx-border-radius: 10px; -fx-effect: dropshadow(gaussian, #bdc3c7, 15, 0, 0, 5);");

            SplitPane splitPane = new SplitPane();
            splitPane.getItems().addAll(vbox1, vbox2);
            splitPane.setDividerPositions(0.5);

            Stage stage = new Stage();
            stage.setTitle("Query Results");
            stage.setScene(new Scene(splitPane, 1200, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the results in a text window.
     * @param result The result to display.
     */
    private void showResultsInTextWindow(String result) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ResultTextArea.fxml"));
            StackPane newRoot = loader.load();

            ResultTextAreaController controller = loader.getController();
            controller.displayResults(result);
            Scene newScene = new Scene(newRoot, 800, 600);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Results");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


