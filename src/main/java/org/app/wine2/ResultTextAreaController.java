
package org.app.wine2;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Controller for managing a {@link TextArea} that displays results.
 * This class allows setting and updating the text content of the result area.
 */
public class ResultTextAreaController {

    @FXML
    private TextArea resultTextArea;

    /**
     * Displays the given result text in the {@link TextArea}.
     *
     * @param result The result text to be displayed.
     */
    public void displayResults(String result) {
        resultTextArea.setText(result);
    }
}
