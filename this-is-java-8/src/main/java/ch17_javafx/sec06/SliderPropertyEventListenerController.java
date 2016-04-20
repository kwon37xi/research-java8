package ch17_javafx.sec06;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SliderPropertyEventListenerController extends Application implements Initializable {
    @FXML
    private Slider slider;
    @FXML
    private Label label;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("/slider_property_event_listener.fxml"));

        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setTitle("slider_property_event_listener");
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setFont(new Font(newValue.doubleValue()));
        });
    }
}
