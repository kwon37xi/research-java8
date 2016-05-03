package ch17_javafx.sec07;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExternalCssSkin extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/external_css_skin.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/external_css_skin.css").toString());

        primaryStage.setTitle("External CSS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}