package ch17_javafx.sec05;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FxmlControllerDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = FXMLLoader.load(getClass().getResource("/fxml_controller.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("FxmlControllerDemo");
        primaryStage.show();
    }
}
