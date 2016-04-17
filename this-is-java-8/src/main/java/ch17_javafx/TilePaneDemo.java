package ch17_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TilePane root = FXMLLoader.load(getClass().getResource("/tile_pane.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
