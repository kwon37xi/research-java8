package ch17_javafx.sec07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Menu 의 text를 "_File" 형태로 하면 F 가 mnemonic이 되어 Alt+F 로 호출가능해진다.
 */
public class MenuControlsController extends Application implements Initializable {
    @FXML private TextArea textArea;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/menu_controls.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleNew(ActionEvent e) {
        textArea.appendText("New\n");
    }

    public void handleOpen(ActionEvent e) {
        textArea.appendText("Open\n");
    }

    public void handleSave(ActionEvent e) {
        textArea.appendText("Save\n");
    }

    public void handleExit(ActionEvent e) {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
