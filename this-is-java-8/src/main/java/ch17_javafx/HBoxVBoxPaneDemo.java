package ch17_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HBoxVBoxPaneDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = FXMLLoader.load(getClass().getResource("/hboxvbox_pane.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
