package ch17_javafx.exec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PieChartController implements Initializable {
    @FXML private Button closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton.setOnAction(this::handleCloseButton);
    }

    private void handleCloseButton(ActionEvent actionEvent) {
        ((Stage)closeButton.getScene().getWindow()).close();
    }
}
