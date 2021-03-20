package controller;

import dbService.DAO.Entity.Task;
import dbService.DBService;
import dialog.AboutDialog;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    private TableView<Task> tableView;

    public void onMenuAboutClick() {
        AboutDialog dialog = new AboutDialog();
        dialog.showAndWait();
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

    }
}
