package org.app.wine2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.app.Server.Queries;
import org.app.Server.SQLDatabaseConnection;

import java.io.IOException;
import java.sql.Connection;

public class ButtonsController {

    private Queries queries;
    private Connection connection;

    public void initialize() {
        queries = new Queries();
        connection = new SQLDatabaseConnection().connect();
    }

    @FXML
    private ComboBox<String> colorComboBox;

    @FXML
    private void handleFetchData() {
        String result = queries.showData(connection);
        showResultsInNewWindow(result);
    }


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
    private void handlePHSmallerThan6() {
        String result = queries.phSmallerThanSix(connection);
        showResultsInNewWindow(result);
    }

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
