package ch17_javafx.sec07;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MediaViewControlsController extends Application implements Initializable {
    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnStop;
    @FXML
    private Label labelTime;
    @FXML
    private Slider sliderVolume;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;

    private boolean endOfMedia;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/media_view.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Media media = new Media(getClass().getResource("/media/video.mp4").toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnReady(() -> {
            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                final double currentTimeSeconds = mediaPlayer.getCurrentTime().toSeconds();
                final double totalDurationSeconds = mediaPlayer.getTotalDuration().toSeconds();
                double progress = currentTimeSeconds / totalDurationSeconds;
                progressBar.setProgress(progress);
                progressIndicator.setProgress(progress);
                labelTime.setText((int) currentTimeSeconds + "/" + (int) totalDurationSeconds + " sec");
            });

            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);

            if (mediaPlayer.isAutoPlay()) {
                mediaView.setVisible(false);
            }
        });

        mediaPlayer.setOnPlaying(() -> {
            btnPlay.setDisable(true);
            btnPause.setDisable(false);
            btnStop.setDisable(false);

        });

        mediaPlayer.setOnPaused(() -> {
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(false);
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            progressBar.setProgress(1.0);
            progressIndicator.setProgress(1.0);

            endOfMedia = true;

            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
        });

        mediaPlayer.setOnStopped(() -> {
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
        });

        btnPlay.setOnAction(event -> {
            if (endOfMedia) {
                mediaPlayer.stop();
                mediaPlayer.seek(mediaPlayer.getStartTime()); // 재생 시간 초기화
            }
            mediaPlayer.play();
            endOfMedia = false;
        });

        btnPause.setOnAction(event -> mediaPlayer.pause());
        btnStop.setOnAction(event -> mediaPlayer.stop());

        sliderVolume.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(sliderVolume.getValue() / 100.0);
        });

        sliderVolume.setValue(50.0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
