package ch17_javafx.sec11;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class TaskController extends Application implements Initializable {
    @FXML private ProgressBar progressBar;
    @FXML private Label lblWorkDone;
    @FXML private Button btnStart;
    @FXML private Button btnStop;

    private Task<Void> task;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/task.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(event -> handleBtnStart(event));
        btnStop.setOnAction(event -> handleBtnStop(event));
    }

    private void handleBtnStart(ActionEvent event) {
        task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    if (isCancelled()) {
                        updateMessage("취소됨");
                        break;
                    }

                    updateProgress(i, 100);
                    updateMessage(String.valueOf(i));

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        if (isCancelled()) {
                            updateMessage("취소됨");
                            break;
                        }
                    }
                }
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        lblWorkDone.textProperty().bind(task.messageProperty());


        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void handleBtnStop(ActionEvent event) {
        task.cancel();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
