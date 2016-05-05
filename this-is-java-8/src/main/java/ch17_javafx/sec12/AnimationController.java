package ch17_javafx.sec12;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnimationController extends Application implements Initializable {
    @FXML private Button btnLogin;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/animation.fxml"));
        primaryStage.setScene(new Scene(root));

        primaryStage.setWidth(350);
        primaryStage.setHeight(500);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction(event -> handleBtnLogin(event));
    }

    public void handleBtnLogin(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("/animation_login.fxml"));
            StackPane root = (StackPane) btnLogin.getScene().getRoot();
            login.setTranslateX(350); // 시작값
            root.getChildren().add(login);


            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(login.translateXProperty(), 0);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
