//package org.app.wine2;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.util.Callback;
//import org.app.Server.SQLDatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.SQLException;
//
//public class WineTableViewController {
//
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
//
//    private ObservableList<Wine> wineList = FXCollections.observableArrayList();
//
//    public void initialize() {
//        // إعداد الأعمدة لربطها مع الخصائص في كائنات Wine
////        fixedAcidityColumn.setCellValueFactory(cellData -> cellData.getValue().fixedAcidityProperty().asObject());
////        volatileAcidityColumn.setCellValueFactory(cellData -> cellData.getValue().volatileAcidityProperty().asObject());
////        citricAcidColumn.setCellValueFactory(cellData -> cellData.getValue().citricAcidProperty().asObject());
////        residualSugarColumn.setCellValueFactory(cellData -> cellData.getValue().residualSugarProperty().asObject());
////        chloridesColumn.setCellValueFactory(cellData -> cellData.getValue().chloridesProperty().asObject());
////        freeSulfurDioxideColumn.setCellValueFactory(cellData -> cellData.getValue().freeSulfurDioxideProperty().asObject());
////        totalSulfurDioxideColumn.setCellValueFactory(cellData -> cellData.getValue().totalSulfurDioxideProperty().asObject());
////        densityColumn.setCellValueFactory(cellData -> cellData.getValue().densityProperty().asObject());
////        pHColumn.setCellValueFactory(cellData -> cellData.getValue().pHProperty().asObject());
////        sulphatesColumn.setCellValueFactory(cellData -> cellData.getValue().sulphatesProperty().asObject());
////        alcoholColumn.setCellValueFactory(cellData -> cellData.getValue().alcoholProperty().asObject());
////        qualityColumn.setCellValueFactory(cellData -> cellData.getValue().qualityProperty());
////        colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
////
////        // تحميل البيانات من قاعدة البيانات
////        loadData();
//    }
//
//    private void loadData() {
//        SQLDatabaseConnection dbConnection = new SQLDatabaseConnection();
//        Connection conn = dbConnection.connect();
//
//        String query = "SELECT * FROM wine";
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
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
//                        rs.getString("color")
//                );
//
//                wineList.add(wine);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // تحديث الجدول بعد تحميل البيانات
//        wineTableView.setItems(wineList);
//    }
//}
package org.app.wine2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FullScreenTableController {

    @FXML private TableView<Wine> wineTableView;
    @FXML private TableColumn<Wine, Double> fixedAcidityColumn;
    @FXML private TableColumn<Wine, Double> volatileAcidityColumn;
    @FXML private TableColumn<Wine, Double> citricAcidColumn;
    @FXML private TableColumn<Wine, Double> residualSugarColumn;
    @FXML private TableColumn<Wine, Double> chloridesColumn;
    @FXML private TableColumn<Wine, Double> freeSulfurDioxideColumn;
    @FXML private TableColumn<Wine, Double> totalSulfurDioxideColumn;
    @FXML private TableColumn<Wine, Double> densityColumn;
    @FXML private TableColumn<Wine, Double> pHColumn;
    @FXML private TableColumn<Wine, Double> sulphatesColumn;
    @FXML private TableColumn<Wine, Double> alcoholColumn;
    @FXML private TableColumn<Wine, String> qualityColumn;
    @FXML private TableColumn<Wine, String> colorColumn;

    private Stage stage;
    @FXML
    public void initialize() {
        // Bind columns to Wine properties
        fixedAcidityColumn.setCellValueFactory(cellData -> cellData.getValue().fixedAcidityProperty().asObject());
        volatileAcidityColumn.setCellValueFactory(cellData -> cellData.getValue().volatileAcidityProperty().asObject());
        citricAcidColumn.setCellValueFactory(cellData -> cellData.getValue().citricAcidProperty().asObject());
        residualSugarColumn.setCellValueFactory(cellData -> cellData.getValue().residualSugarProperty().asObject());
        chloridesColumn.setCellValueFactory(cellData -> cellData.getValue().chloridesProperty().asObject());
        freeSulfurDioxideColumn.setCellValueFactory(cellData -> cellData.getValue().freeSulfurDioxideProperty().asObject());
        totalSulfurDioxideColumn.setCellValueFactory(cellData -> cellData.getValue().totalSulfurDioxideProperty().asObject());
        densityColumn.setCellValueFactory(cellData -> cellData.getValue().densityProperty().asObject());
        pHColumn.setCellValueFactory(cellData -> cellData.getValue().pHProperty().asObject());
        sulphatesColumn.setCellValueFactory(cellData -> cellData.getValue().sulphatesProperty().asObject());
        alcoholColumn.setCellValueFactory(cellData -> cellData.getValue().alcoholProperty().asObject());
        qualityColumn.setCellValueFactory(cellData -> cellData.getValue().qualityProperty());
        colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTableData(ObservableList<Wine> wineList) {
        wineTableView.setItems(wineList);
    }

    @FXML
    private void handleBack() {
        stage.close(); // Close the full-screen window and return to the main scene
    }
}

