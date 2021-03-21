package dialog;

import dbService.DAO.Entity.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class DeleteDialog extends Alert {
    public DeleteDialog(Task task){
        super(AlertType.CONFIRMATION);
        setTitle("Delete Task");
        setHeaderText("Are you sure want to delete this task?");
        setContentText(task.getTask_todo());

        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        getButtonTypes().setAll(okButton, noButton);
    }
}
