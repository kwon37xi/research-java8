package ch17_javafx.sec11;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class RunLaterController extends Application implements Initializable {
    @FXML
    private Label lblTime;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;

    private boolean stop;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/run_later.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(event -> handleBtnStart(event));
        btnStop.setOnAction(event -> handleBtnStop(event));
    }

    private void handleBtnStart(ActionEvent event) {
        stop = false;
        Thread thread = new Thread() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                while (!stop) {
                    String strTime = sdf.format(new Date());
                    Platform.runLater(() -> {
                        lblTime.setText(strTime);
                    });

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    private void handleBtnStop(ActionEvent event) {
        stop = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
