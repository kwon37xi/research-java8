package ch17_javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 */
public class AppMainPaddingMargin extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(50, 10, 10, 50));
        Button button = new Button();
        button.setPrefSize(100, 100);
//
//        HBox hbox = new HBox();
//        Button button = new Button();
//        button.setPrefSize(100, 100);
//        HBox.setMargin(button, new Insets(10, 10, 50, 50));
//
        hbox.getChildren().add(button);

        primaryStage.setTitle("AppMain with margin/padding");
        primaryStage.setScene(new Scene(hbox));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
