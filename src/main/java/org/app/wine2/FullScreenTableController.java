package org.app.wine2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class FullScreenTableController {

    @FXML private TableView<Wine> wineTableView;
    @FXML
    private TableColumn<Wine, Integer> idColumn;
    @FXML
    private TableColumn<Wine, String> hireDateColumn;
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
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        hireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDate());

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
        Stage stage = (Stage) wineTableView.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage mainStage = new Stage();
            mainStage.setScene(scene);
            mainStage.setTitle("Main Screen");
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

