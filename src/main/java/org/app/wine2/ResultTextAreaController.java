package org.app.wine2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ResultTextAreaController {

    @FXML
    private TextArea resultTextArea;



    // يمكنك إضافة وظائف إضافية هنا للتحكم في الـ TextArea
    public void displayResults(String result) {
        resultTextArea.setText(result);
    }


}
