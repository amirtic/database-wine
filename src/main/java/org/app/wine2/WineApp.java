package org.app.wine2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class WineApp extends Application {

    private StackPane resultTextAreaStackPane;
    private StackPane wineTableViewStackPane;
    private VBox buttonsVbox;


    @Override
    public void start(Stage primaryStage) throws Exception {
        // טעינת כל הרכבים מתוך FXML
        resultTextAreaStackPane = FXMLLoader.load(getClass().getResource("/ResultTextArea.fxml"));
        wineTableViewStackPane = FXMLLoader.load(getClass().getResource("/WineTableView.fxml"));
        buttonsVbox = FXMLLoader.load(getClass().getResource("/Buttons.fxml"));

        // הגדרת הכפתורים
        Button showDataButton = new Button("Show Data");
        showDataButton.setOnAction(e -> showData(primaryStage));

        VBox initialView = new VBox(20);
        initialView.setStyle("-fx-alignment: center;");
        initialView.getChildren().addAll(buttonsVbox, showDataButton);

        // הגדרת הסצנה
        Scene scene = new Scene(initialView, 1100, 800);
        primaryStage.setTitle("Wine Database");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.show();
    }

    // מתודה להצגת הטבלה בלחיצה על הכפתור
    private void showData(Stage stage) {
        // إعداد زر "Back"
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> goBack(stage));

        // הגדרת הממשק השני עם הטבלה ,וכפתור "Back"
        VBox secondView = new VBox(20);
        secondView.setStyle("-fx-alignment: center;");
        secondView.getChildren().addAll(wineTableViewStackPane, backButton);

        // עדכון מחדש של הסצנה כדי להציג את הטבלה,וכפתור  "Back"
        Scene scene = new Scene(secondView, 1100, 800);
        stage.setScene(scene);
        stage.show();
    }

    // מתודה לחזרה למסך הראשי
    private void goBack(Stage stage) {
        // החזרת המסך הראשון
        VBox initialView = new VBox(20);
        initialView.setStyle("-fx-alignment: center;");
        Button showDataButton = new Button("Show Data");
        showDataButton.setOnAction(e -> showData(stage));
        initialView.getChildren().addAll(buttonsVbox, showDataButton);

        Scene scene = new Scene(initialView, 1100, 800);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
