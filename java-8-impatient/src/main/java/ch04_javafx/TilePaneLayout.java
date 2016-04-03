package ch04_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 */
public class TilePaneLayout extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final double em = Font.getDefault().getSize();

        TilePane pane = new TilePane();

        pane.setPrefColumns(1);
        for (int i = 0; i < 10; i++) {
            pane.getChildren().add(new Button("" + i * 3));
        }
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
