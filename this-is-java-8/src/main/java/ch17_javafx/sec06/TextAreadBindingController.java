package ch17_javafx.sec06;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TextAreadBindingController extends Application implements Initializable {
    @FXML
    private TextArea textArea1;
    @FXML
    TextArea textArea2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = FXMLLoader.load(getClass().getResource("/text_area_binding.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Bindings.bindBidirectional(textArea1.textProperty(), textArea2.textProperty());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
