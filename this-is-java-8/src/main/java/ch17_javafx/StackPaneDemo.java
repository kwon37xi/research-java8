package ch17_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackPaneDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = FXMLLoader.load(getClass().getResource("/stack_pane.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
