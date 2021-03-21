package dialog;

import javafx.scene.control.Alert;

public class ErrorDialog extends Alert {
    public ErrorDialog(String title, String header, String content){
        super(AlertType.ERROR);
        setTitle(title);
        setHeaderText(header);
        setContentText(content);
    }
}
