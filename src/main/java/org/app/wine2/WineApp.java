package org.app.wine2;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.app.Server.Queries;
import org.app.Server.SQLDatabaseConnection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

public class WineApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {





        SQLDatabaseConnection dbConnection = new SQLDatabaseConnection();
        Connection conn = dbConnection.connect();

        Queries queries = new Queries();

        // VBox to hold the buttons
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        // Create buttons for each query
        Button fetchDataBtn = new Button("Fetch Data");
        Button fetchPHBtn = new Button("Fetch pH");
        Button counterRedWhiteBtn = new Button("Counter of Red and White Wines");
        Button phSmallerThanSixBtn = new Button("pH Smaller Than 6");
        Button selectMaxMinBtn = new Button("Select Max/Min Value");
        Button showData = new Button("Show Data");

        // Button actions
        fetchDataBtn.setOnAction(e -> queries.fetchData(conn));
        fetchPHBtn.setOnAction(e -> queries.fetchpH(conn));
        counterRedWhiteBtn.setOnAction(e -> queries.counterOfRedWhite(conn));
        phSmallerThanSixBtn.setOnAction(e -> queries.phSmallerThanSix(conn));
        selectMaxMinBtn.setOnAction(e -> queries.selectMaxMinValue(conn));
        showData.setOnAction(e -> queries.showData());

        // Add buttons to VBox
        vbox.getChildren().addAll(showData, fetchDataBtn, counterRedWhiteBtn, phSmallerThanSixBtn, selectMaxMinBtn, fetchPHBtn);

        // TableView setup
        TableView<Wine> wineTableView = new TableView<>();
        TableColumn<Wine, Double> fixedAcidityColumn = createDoubleColumn("Fixed Acidity", "fixedAcidity");
        TableColumn<Wine, Double> volatileAcidityColumn = createDoubleColumn("Volatile Acidity", "volatileAcidity");
        TableColumn<Wine, Double> citricAcidColumn = createDoubleColumn("Citric Acid", "citricAcid");
        TableColumn<Wine, Double> residualSugarColumn = createDoubleColumn("Residual Sugar", "residualSugar");
        TableColumn<Wine, Double> chloridesColumn = createDoubleColumn("Chlorides", "chlorides");
        TableColumn<Wine, Double> freeSulfurDioxideColumn = createDoubleColumn("Free Sulfur Dioxide", "freeSulfurDioxide");
        TableColumn<Wine, Double> totalSulfurDioxideColumn = createDoubleColumn("Total Sulfur Dioxide", "totalSulfurDioxide");
        TableColumn<Wine, Double> densityColumn = createDoubleColumn("Density", "density");
        TableColumn<Wine, Double> pHColumn = createDoubleColumn("pH", "pH");
        TableColumn<Wine, Double> sulphatesColumn = createDoubleColumn("Sulphates", "sulphates");
        TableColumn<Wine, Double> alcoholColumn = createDoubleColumn("Alcohol", "alcohol");

        // Define columns for String properties
        TableColumn<Wine, String> qualityColumn = new TableColumn<>("Quality");
        qualityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuality()));

        TableColumn<Wine, String> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColor()));
        // Add all columns to the TableView

        // Add all columns to the TableView
        wineTableView.getColumns().addAll(
                fixedAcidityColumn, volatileAcidityColumn, citricAcidColumn,
                residualSugarColumn, chloridesColumn, freeSulfurDioxideColumn,
                totalSulfurDioxideColumn, densityColumn, pHColumn, sulphatesColumn,
                alcoholColumn, qualityColumn, colorColumn
        );

        // Add TableView to VBox
        vbox.getChildren().add(wineTableView);




        // Create scene and set stage
        Scene scene = new Scene(vbox, 1100, 800);


//        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("Wine Database");
        primaryStage.setScene(scene);
        primaryStage.show();



    }





    // Helper method to create Double columns
    private TableColumn<Wine, Double> createDoubleColumn(String name, String getterMethod) {
        TableColumn<Wine, Double> column = new TableColumn<>(name);
        column.setCellValueFactory(cellData -> {
            try {
                // استدعاء دالة الـ getter باستخدام Reflection
                Object result = cellData.getValue().getClass()
                        .getMethod(getterMethod)
                        .invoke(cellData.getValue());

                if (result instanceof Double) {
                    return new SimpleDoubleProperty((Double) result).asObject();
                } else {
                    // في حالة أن القيمة التي أرجعها الـ getter ليست من نوع Double
                    System.err.println("Expected a Double value from " + getterMethod);
                    return null;
                }
            } catch (NoSuchMethodException e) {
                System.err.println("No such method: " + getterMethod);
                e.printStackTrace();
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.err.println("Error invoking method: " + getterMethod);
                e.printStackTrace();
            }
            return null;
        });
        return column;
    }

    public static void main(String[] args) {
        launch(args);
    }
}