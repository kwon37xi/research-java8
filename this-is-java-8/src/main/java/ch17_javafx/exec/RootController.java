package ch17_javafx.exec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class RootController implements Initializable {
    @FXML private Button addButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(this::handleAddButton);
    }

    public void handleAddButton(ActionEvent event) {
        System.out.println("add button clicked.");

        try {
            final Window primaryWindow = addButton.getScene().getWindow();
            Stage addDialog = new Stage(StageStyle.UTILITY);
            addDialog.initModality(Modality.WINDOW_MODAL);
            addDialog.initOwner(primaryWindow);
            addDialog.setTitle("추가");

            final Parent addDialogForm = FXMLLoader.load(getClass().getResource("/exec_form.fxml"));
            Scene addScene = new Scene(addDialogForm);
            addDialog.setResizable(false);
            addDialog.setScene(addScene);
            addDialog.show();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
}
