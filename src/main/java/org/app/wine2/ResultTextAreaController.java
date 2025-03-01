package org.app.wine2;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ResultTextAreaController {

    @FXML
    private TextArea resultTextArea;




    public void displayResults(String result) {
        resultTextArea.setText(result);
    }


}
