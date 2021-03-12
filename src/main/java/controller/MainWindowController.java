package controller;

import dialog.AboutDialog;
import javafx.application.Platform;

public class MainWindowController {

    public void onMenuAboutClick() {
        AboutDialog dialog = new AboutDialog();
        dialog.showAndWait();
    }

    public void closeApp(){
        Platform.exit();
    }
}
