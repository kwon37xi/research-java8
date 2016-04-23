package ch17_javafx.sec07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InputControlsController extends Application implements Initializable {
    @FXML private TextField txtTitle;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> comboPublic;
    @FXML private DatePicker dateExit;
    @FXML private TextArea txtContent;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/input_controls.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleBtnRegAction(ActionEvent e) {
        String title= txtTitle.getText();
        System.out.println("title: " + title);

        String password = txtPassword.getText();
        System.out.println("password: " + password);

        String strPublic = comboPublic.getValue();
        System.out.println("public: " + strPublic);

        LocalDate localDate = dateExit.getValue();
        if (localDate != null) {
            System.out.println("dateExit: " + localDate.toString());
        }

        String content = txtContent.getText();
        System.out.println("content: " + content);
    }

    public void handleBtnCancelAction(ActionEvent e) {
        Platform.exit();
    }
}
