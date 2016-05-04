package ch17_javafx.sec11;

import javafx.application.Application;
import javafx.concurrent.Service;
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

/**
 *
 */
public class ServiceController extends Application implements Initializable {
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

    private TimeService timeService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/service.fxml"));
        primaryStage.setTitle("Service Task");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(event -> handleBtnStart(event));
        btnStop.setOnAction(event -> handleBtnStop(event));

        timeService = new TimeService();
        timeService.start();
    }

    private void handleBtnStart(ActionEvent event) {
        lblResult.setText("");
        timeService.restart();
    }

    private void handleBtnStop(ActionEvent event) {
        timeService.cancel();
    }

    class TimeService extends Service<Integer> {

        @Override
        protected Task<Integer> createTask() {
            Task<Integer> task = new Task<Integer>() {

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
                        } catch (InterruptedException ex) {
                            if (isCancelled()) {
                                break;
                            }
                        }
                    }
                    return result;
                }
            };

            progressBar.progressProperty().bind(task.progressProperty());
            lblWorkDone.textProperty().bind(task.messageProperty());
            return task;
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
