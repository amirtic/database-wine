package org.app.wine2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.app.Server.SQLDatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WineController {
    @FXML
    private TableView<Wine> wineTableView;

    @FXML
    private TableColumn<Wine, Double> fixedAcidityColumn;
    @FXML
    private TableColumn<Wine, Double> volatileAcidityColumn;
    @FXML
    private TableColumn<Wine, Double> citricAcidColumn;
    @FXML
    private TableColumn<Wine, Double> residualSugarColumn;
    @FXML
    private TableColumn<Wine, Double> chloridesColumn;
    @FXML
    private TableColumn<Wine, Double> freeSulfurDioxideColumn;
    @FXML
    private TableColumn<Wine, Double> totalSulfurDioxideColumn;
    @FXML
    private TableColumn<Wine, Double> densityColumn;
    @FXML
    private TableColumn<Wine, Double> pHColumn;
    @FXML
    private TableColumn<Wine, Double> sulphatesColumn;
    @FXML
    private TableColumn<Wine, Double> alcoholColumn;
    @FXML
    private TableColumn<Wine, String> qualityColumn;
    @FXML
    private TableColumn<Wine, String> colorColumn;

    private final ObservableList<Wine> wineList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the columns
        fixedAcidityColumn.setCellValueFactory(new PropertyValueFactory<>("fixedAcidity"));
        volatileAcidityColumn.setCellValueFactory(new PropertyValueFactory<>("volatileAcidity"));
        citricAcidColumn.setCellValueFactory(new PropertyValueFactory<>("citricAcid"));
        residualSugarColumn.setCellValueFactory(new PropertyValueFactory<>("residualSugar"));
        chloridesColumn.setCellValueFactory(new PropertyValueFactory<>("chlorides"));
        freeSulfurDioxideColumn.setCellValueFactory(new PropertyValueFactory<>("freeSulfurDioxide"));
        totalSulfurDioxideColumn.setCellValueFactory(new PropertyValueFactory<>("totalSulfurDioxide"));
        densityColumn.setCellValueFactory(new PropertyValueFactory<>("density"));
        pHColumn.setCellValueFactory(new PropertyValueFactory<>("pH"));
        sulphatesColumn.setCellValueFactory(new PropertyValueFactory<>("sulphates"));
        alcoholColumn.setCellValueFactory(new PropertyValueFactory<>("alcohol"));
        qualityColumn.setCellValueFactory(new PropertyValueFactory<>("quality"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));


        // Load data from database

        loadData();
    }

    private void loadData() {
        SQLDatabaseConnection dbConnection = new SQLDatabaseConnection();
        Connection conn = dbConnection.connect();

        String query = "SELECT * FROM wine";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                wineList.add(new Wine(
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
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        wineTableView.setItems(wineList);
    }
}

