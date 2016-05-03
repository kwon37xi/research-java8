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

public class TaskCallBackController extends Application implements Initializable {
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label lblWorkDone;
    @FXML
    private Label lblResult;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;

    private Task<Integer> task;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/task_callback.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(event -> handleBtnStart(event));
        btnStop.setOnAction(event -> handleBtnStop(event));
    }

    private void handleBtnStart(ActionEvent event) {
        task = new Task<Integer>() {

            @Override
            protected Integer call() throws Exception {
                int result = 0;
                for (int i = 0; i <= 100; i++) {
                    if (isCancelled()) {
                        break;
                    }

                    result += i;

                    updateProgress(i, 100);
                    updateMessage(String.valueOf(i));

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        if (isCancelled()) {
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            protected void succeeded() {
                lblResult.setText(String.valueOf(getValue()));
            }

            @Override
            protected void cancelled() {
                lblResult.setText("취소됨");
            }

            @Override
            protected void failed() {
                lblResult.setText("실패");
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        lblWorkDone.textProperty().bind(task.messageProperty());
        lblResult.setText("");

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
