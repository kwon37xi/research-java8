package ch17_javafx.sec12;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimationLoginController implements Initializable {
    @FXML private BorderPane login;
    @FXML private Button btnMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnMain.setOnAction(event -> handleBtnMain(event));
    }

    public void handleBtnMain(ActionEvent event) {
        try {
            StackPane root = (StackPane) btnMain.getScene().getRoot();

            login.setTranslateX(0);

            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(login.translateXProperty(), 350);
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(500),
                timelineEvent -> root.getChildren().remove(login),
                keyValue
            );

            timeline.getKeyFrames().addAll(keyFrame);
            timeline.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
