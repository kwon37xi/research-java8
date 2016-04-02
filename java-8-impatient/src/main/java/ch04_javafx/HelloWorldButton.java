package ch04_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorldButton extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label message = new Label("Hello, JavaFX!");
        message.setFont(new Font(100));
        Button redButton = new Button("Red");
        redButton.setOnAction(event -> message.setTextFill(Color.RED));

        VBox root = new VBox();

        root.getChildren().addAll(redButton, message);
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello");
        primaryStage.show();
    }
}
