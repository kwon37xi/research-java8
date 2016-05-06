package ch17_javafx.exec;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class RootController implements Initializable {
    @FXML private Button addButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(e -> System.out.println("add button clicked"));
    }
}
