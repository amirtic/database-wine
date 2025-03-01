
package org.app.wine2;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WineApp extends Application {

    private VBox buttonsVbox;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        
        this.primaryStage = primaryStage;

        // יצירת המסך הראשוני עם ברוך הבא
        VBox welcomeVBox = createWelcomeScreen();

        // יצירת סצנה עם המסך הראשוני
        Scene welcomeScene = new Scene(welcomeVBox, 1100, 800);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();

        // הוספת אפקט תצוגה (Fade In)
        applyFadeInEffect(welcomeVBox);

        // מעבר למסך הראשי אחרי 3 שניות
        navigateToMainScreenAfterDelay();
    }

    // יצירת המסך הראשוני
    private VBox createWelcomeScreen() {
        VBox welcomeVBox = new VBox();
        welcomeVBox.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: #282c34;"); // עיצוב רקע כהה

        Label welcomeLabel = new Label("Welcome to  Wine Application");
        welcomeLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #ffffff;"); // עיצוב טקסט

        welcomeVBox.getChildren().add(welcomeLabel);
        return welcomeVBox;
    }

    // הוספת אפקט תצוגה (Fade In) על תווית הברוך הבא
    private void applyFadeInEffect(VBox welcomeVBox) {
        Label welcomeLabel = (Label) welcomeVBox.getChildren().get(0); // גישה לתווית הברוך הבא
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), welcomeLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    // מעבר למסך הראשי אחרי השהיית זמן
    private void navigateToMainScreenAfterDelay() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            // אפקט יציאה (Fade Out)
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), primaryStage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> showMainApp());
            fadeOut.play();
        });
        pause.play();
    }

    // הצגת המסך הראשי לאחר יציאה מהמסך הקודם
    private void showMainApp() {
        try {
            // טעינת ה-FXML של המסך הראשי
            buttonsVbox = FXMLLoader.load(getClass().getResource("/fxml/Buttons.fxml"));
            VBox mainVBox = new VBox(20);
            mainVBox.setStyle("-fx-alignment: center;");
            mainVBox.getChildren().add(buttonsVbox);

            // הגדרת סצנה עם מסך ראשי
            Scene mainScene = new Scene(mainVBox, 1100, 800);
            primaryStage.setTitle("Wine Database");
            primaryStage.setScene(mainScene);
            mainScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Error loading the FXML file"); // הצגת הודעת שגיאה במידה ויש בעיה
        }
    }

    // הצגת הודעת שגיאה במידה ויש בעיה
    private void showErrorDialog(String message) {
        // פונקציה להציג הודעת שגיאה (למשל, דיאלוג עם כפתור סגירה)
        System.err.println(message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
