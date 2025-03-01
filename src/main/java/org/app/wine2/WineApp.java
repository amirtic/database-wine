

package org.app.wine2;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WineApp extends Application {

    private VBox buttonsVbox;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        // יצירת מסך פתיחה
        VBox welcomeVBox = createWelcomeScreen();

        // יצירת סצנה ראשונית
        Scene welcomeScene = new Scene(welcomeVBox, 1100, 800);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();

        // אפקט כניסה
        applyFadeInEffect(welcomeVBox);

        // מעבר אוטומטי למסך הראשי
        navigateToMainScreenAfterDelay();
    }

    /**
     * יצירת מסך הפתיחה עם טקסט "ברוך הבא".
     */
    private VBox createWelcomeScreen() {
        VBox welcomeVBox = new VBox();
        welcomeVBox.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: #282c34;");

        Label welcomeLabel = new Label("Welcome to Wine Application");
        welcomeLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        welcomeVBox.getChildren().add(welcomeLabel);
        return welcomeVBox;
    }

    /**
     * הוספת אפקט מעבר (Fade In) למסך הפתיחה.
     */
    private void applyFadeInEffect(VBox welcomeVBox) {
        Label welcomeLabel = (Label) welcomeVBox.getChildren().get(0);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), welcomeLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    /**
     * מעבר למסך הראשי אחרי 3 שניות.
     */
    private void navigateToMainScreenAfterDelay() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), primaryStage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> Platform.runLater(this::showMainApp));
            fadeOut.play();
        });
        pause.play();
    }

    /**
     * טעינת המסך הראשי עם FXML וסטיילים.
     */
    private void showMainApp() {
        try {
            buttonsVbox = FXMLLoader.load(getClass().getResource("/fxml/Buttons.fxml"));
            VBox mainVBox = new VBox(20);
            mainVBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
            mainVBox.getChildren().add(buttonsVbox);

            Scene mainScene = new Scene(mainVBox, 1100, 800);
            primaryStage.setTitle("Wine Database");
            primaryStage.setScene(mainScene);
            mainScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Error loading the FXML file: " + e.getMessage());
        }
    }

    /**
     * הצגת הודעת שגיאה אם מתרחשת בעיה בטעינת ה־FXML.
     */
    private void showErrorDialog(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
