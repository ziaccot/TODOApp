package controller;

import dbService.DAO.Entity.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class EditDialogController {
    private Task task;
    @FXML
    private TextField taskField;

    @FXML
    private CheckBox checkDone;

    public Task getTask() {
        return task;
    }

    public void modifyTask(){
        task.setTask_todo(taskField.getText());
        task.setDone(checkDone.isSelected());
    }

    public void setTask(Task task) {
        this.task = task;
        setTaskField(task.getTask_todo());
        setCheckDone(task.isDone());
    }

    public void setTaskField(String taskField) {
        this.taskField.setText(taskField);;
    }

    public void setCheckDone(boolean done) {
        this.checkDone.setDisable(done);
    }
}
