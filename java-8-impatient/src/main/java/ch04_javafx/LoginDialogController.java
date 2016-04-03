package ch04_javafx;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class LoginDialogController implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button okButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        okButton.disableProperty().bind(
            Bindings.or(Bindings.isEmpty(username.textProperty()),
                Bindings.isEmpty(password.textProperty()))
        );

        okButton.setOnAction(event ->
            System.out.println("Verifying " + username.getText() + ":" + password.getText())
        );
    }
}
