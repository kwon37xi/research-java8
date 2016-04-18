package ch17_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = FXMLLoader.load(getClass().getResource("/grid_pane.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
