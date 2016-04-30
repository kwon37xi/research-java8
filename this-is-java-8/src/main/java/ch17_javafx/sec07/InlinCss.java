package ch17_javafx.sec07;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InlinCss extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/inline_css.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
