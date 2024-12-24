package org.app.wine2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.app.Server.Queries;
import org.app.Server.SQLDatabaseConnection;

import java.io.IOException;
import java.sql.Connection;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javafx.scene.Parent;

public class ButtonsController {

    private Queries queries;
    private Connection connection;

//    @FXML private TableView<Wine> wineTableView;
//    @FXML private TableColumn<Wine, Double> fixedAcidityColumn;
//    @FXML private TableColumn<Wine, Double> volatileAcidityColumn;
//    @FXML private TableColumn<Wine, Double> citricAcidColumn;
//    @FXML private TableColumn<Wine, Double> residualSugarColumn;
//    @FXML private TableColumn<Wine, Double> chloridesColumn;
//    @FXML private TableColumn<Wine, Double> freeSulfurDioxideColumn;
//    @FXML private TableColumn<Wine, Double> totalSulfurDioxideColumn;
//    @FXML private TableColumn<Wine, Double> densityColumn;
//    @FXML private TableColumn<Wine, Double> pHColumn;
//    @FXML private TableColumn<Wine, Double> sulphatesColumn;
//    @FXML private TableColumn<Wine, Double> alcoholColumn;
//    @FXML private TableColumn<Wine, String> qualityColumn;
//    @FXML private TableColumn<Wine, String> colorColumn;

    private ObservableList<Wine> wineList = FXCollections.observableArrayList();

    public void initialize() {
        queries = new Queries();
        connection = new SQLDatabaseConnection().connect();
    }
//        fixedAcidityColumn.setCellValueFactory(cellData -> cellData.getValue().fixedAcidityProperty().asObject());
//        volatileAcidityColumn.setCellValueFactory(cellData -> cellData.getValue().volatileAcidityProperty().asObject());
//        citricAcidColumn.setCellValueFactory(cellData -> cellData.getValue().citricAcidProperty().asObject());
//        residualSugarColumn.setCellValueFactory(cellData -> cellData.getValue().residualSugarProperty().asObject());
//        chloridesColumn.setCellValueFactory(cellData -> cellData.getValue().chloridesProperty().asObject());
//        freeSulfurDioxideColumn.setCellValueFactory(cellData -> cellData.getValue().freeSulfurDioxideProperty().asObject());
//        totalSulfurDioxideColumn.setCellValueFactory(cellData -> cellData.getValue().totalSulfurDioxideProperty().asObject());
//        densityColumn.setCellValueFactory(cellData -> cellData.getValue().densityProperty().asObject());
//        pHColumn.setCellValueFactory(cellData -> cellData.getValue().pHProperty().asObject());
//        sulphatesColumn.setCellValueFactory(cellData -> cellData.getValue().sulphatesProperty().asObject());
//        alcoholColumn.setCellValueFactory(cellData -> cellData.getValue().alcoholProperty().asObject());
//        qualityColumn.setCellValueFactory(cellData -> cellData.getValue().qualityProperty());
//        colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
//
//        // تحميل البيانات من قاعدة البيانات
////        loadData();
//    }

    @FXML
    private ComboBox<String> colorComboBox;

    @FXML
    private TextField pHValueField;


//    @FXML
//    private void handleFetchData() {
//        // Ensure the data is loaded (if not already loaded)
//        if (wineTableView.getItems().isEmpty()) {
//            loadTableData();
//        }
//        // Show the table
//        wineTableView.setVisible(true);
//    }
//
//    private void loadTableData() {
//        // Load data into the table (Example logic)
//        ObservableList<Wine> data = FXCollections.observableArrayList(
//                new Wine(7.4, 0.7, 0.0, 1.9, 0.076, 11.0, 34.0, 0.9978, 3.51, 0.56, 9.4, "High", "Red"),
//                new Wine(7.8, 0.88, 0.0, 2.6, 0.098, 25.0, 67.0, 0.9968, 3.2, 0.68, 9.8, "Medium", "White")
//        );
//        wineTableView.setItems(data);
//    }

    @FXML
    private void handleFetchData() {
        // Fetch and load data into the table
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
                        rs.getString("color")
                );

                wineList.add(wine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load the full-screen FXML
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FullScreenTable.fxml"));
            Parent root = loader.load();

            // Get the controller and pass data
            FullScreenTableController controller = loader.getController();
            controller.setTableData(wineList);

            // Create a new Stage for full-screen
            Stage fullScreenStage = new Stage();
            controller.setStage(fullScreenStage);
            fullScreenStage.setScene(new Scene(root));
            fullScreenStage.setFullScreen(true);
            fullScreenStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @FXML
//    private void handleFetchData() {
//        String result = queries.showData(connection);
//        showResultsInNewWindow(result);
//    }

//    @FXML
//   private void handleShowData() {
//        String result = queries.showData(connection);
//        showResultsInNewWindow(result);
//    }


    @FXML
    private void handleFetchpH() {
        // جلب اللون المختار من ComboBox
        String selectedColor = colorComboBox.getValue();

        if (selectedColor != null) {
            String result = queries.fetchpHByColor(connection, selectedColor);
            showResultsInNewWindow(result);
        } else {
            // عرض رسالة تنبيه إذا لم يتم اختيار اللون
            showResultsInNewWindow("Please select a color first.");
        }
    }



    @FXML
    private void handleCounterRedWhite() {
        String result = queries.counterOfRedWhite(connection);
        showResultsInNewWindow(result);
    }

    @FXML
    private void handlePHSmallerThan() {
        // الحصول على القيمة المدخلة من قبل المستخدم
        String pHInput = pHValueField.getText();
//        SQLDatabaseConnection dbConnection = new SQLDatabaseConnection();
//        Connection conn = dbConnection.connect();

        if (pHInput != null && !pHInput.isEmpty()) {
            try {
                double pHValue = Double.parseDouble(pHInput);
                String result = Queries.phSmallerThan(connection, pHValue);
                showResultsInNewWindow(result);
            } catch (NumberFormatException e) {
                showResultsInNewWindow("Invalid pH value. Please enter a valid number.");
            }
        } else {
            showResultsInNewWindow("Please enter a pH value.");
        }
    }

//    @FXML
//    private void handlePHSmallerThan6() {
//        String result = queries.phSmallerThanSix(connection);
//        showResultsInNewWindow(result);
//    }

    @FXML
    private void handleSelectMaxMin() {
        String result = queries.selectMaxMinValue(connection);
        showResultsInNewWindow(result);
    }





    private void showResultsInNewWindow(String result) {
        try {
            // טוען את ממשק התוצאה
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResultTextArea.fxml"));
            StackPane newRoot = loader.load();

            // השגת בקר ה-ResultTextAreaController
            ResultTextAreaController controller = loader.getController();
            controller.displayResults(result);  // הצגת התוצאה ב-TextArea

            // הגדרת סצנה חדשה
            Scene newScene = new Scene(newRoot, 800, 600);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Results");

            // הצגת הסצנה
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
