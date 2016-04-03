package ch04_javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class VideoDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Path path = Paths.get("java-8-impatient/moonlanding.mp4");
        String location = path.toUri().toString();
        Media media = new Media(location);
        MediaPlayer player = new MediaPlayer(media);
        player.setAutoPlay(true);

        MediaView view = new MediaView(player);
        view.setOnError(e -> System.out.println(e));
        HBox box = new HBox(view);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
