package controller;

import dbService.DAO.Entity.Task;
import dbService.DBService;
import dialog.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private TableView<Task> tableView;

    public void onMenuAboutClick() {
        AboutDialog dialog = new AboutDialog();
        dialog.showAndWait();
    }

    public void onAddTaskClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/add_dialog.fxml"));
            DialogPane addTaskPane = fxmlLoader.load();
            AddDialogController controller = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addTaskPane);
            dialog.setTitle("Add Task");

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.APPLY){
                if (controller.getTask().isEmpty())
                    new ErrorDialog("Empty Task", null, "You can not add an empty task").showAndWait();
                else {
                    Task task = new Task(controller.getTask());
                    tableView.getItems().add(task);
                    new DBService().addTask(task);
                }
            }
        } catch (IOException e) {
            new ErrorDialog("FXML Error", e.getMessage(), e.getStackTrace().toString());
        }
    }

    public void onEditTaskClick(){
        if (tableView.getSelectionModel().getSelectedItem() == null) return;

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/edit_dialog.fxml"));
            DialogPane edtitTaskPane = fxmlLoader.load();
            EditDialogController controller = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(edtitTaskPane);
            dialog.setTitle("Edit Task");

            controller.setTask(tableView.getSelectionModel().getSelectedItem());
            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.APPLY){
                controller.modifyTask();
                tableView.getItems().set(tableView.getSelectionModel().getSelectedIndex(), controller.getTask());
                new DBService().addTask(controller.getTask());
            }
        }catch (IOException e){
            new ErrorDialog("FXML Error", e.getMessage(), e.getStackTrace().toString());
        }
    }

    public void onDeleteTaskClick(){
        if (tableView.getSelectionModel().getSelectedItem() == null) return;

        DeleteDialog dialog = new DeleteDialog(tableView.getSelectionModel().getSelectedItem());
        Optional<ButtonType> clickedButton = dialog.showAndWait();

        if (clickedButton.get().getButtonData().isDefaultButton()){
            new DBService().deleteTask(tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
        }
    }


    public void closeApp(){
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTableView();
    }

    private void loadTableView() {
        TableColumn<Task, String> taskColumn = new TableColumn<>("To Do");
        TableColumn<Task, Boolean> doneColumn = new TableColumn<>("Done");

        taskColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task_todo"));
        doneColumn.setCellValueFactory(new PropertyValueFactory<Task, Boolean>("done"));


        ObservableList<Task> list = FXCollections.observableList(new DBService().getAllTask());
        tableView.setItems(list);
        tableView.getColumns().addAll(taskColumn, doneColumn);

        tableView.getColumns().get(0).setMinWidth(280);
        tableView.getColumns().get(0).setPrefWidth(280);

    }

}
