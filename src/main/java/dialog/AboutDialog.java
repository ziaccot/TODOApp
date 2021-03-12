package dialog;


import javafx.scene.control.Alert;

public class AboutDialog extends Alert {
    private final String title = "About TODO App";
    private final String content = "Just a boring App to test hibernate and Openjfx. " +
                                    "\n Hibernate are configured to work with PostgreSQL.";

    public AboutDialog(){
        super(AlertType.INFORMATION);
        setTitle(title);
        setContentText(content);
        setHeaderText(null);
    }
}
