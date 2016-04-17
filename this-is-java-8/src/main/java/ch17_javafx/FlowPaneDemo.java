package ch17_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root = FXMLLoader.load(getClass().getResource("/flow_pane.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
