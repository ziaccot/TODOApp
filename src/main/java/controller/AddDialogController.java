package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddDialogController {
    @FXML
    private TextField taskField;

    public String getTask(){
        return taskField.getText();
    }
}
