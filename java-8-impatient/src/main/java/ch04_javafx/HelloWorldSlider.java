package ch04_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorldSlider extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label message = new Label("Hello, JavaFX!");
        message.setFont(new Font(100));
        Slider slider = new Slider(10, 200, 100);
//        slider.valueProperty().addListener(property -> message.setFont(new Font(slider.getValue())));
        slider.valueProperty().addListener((property, oldValue, newValue) -> message.setFont(new Font(newValue.doubleValue()))); // with ChangeListener
        VBox root = new VBox();
        root.getChildren().addAll(slider, message);
        final Scene scene = new Scene(root );
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello");
        primaryStage.show();
    }
}
