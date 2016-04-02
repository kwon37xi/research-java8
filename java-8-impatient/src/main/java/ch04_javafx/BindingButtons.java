package ch04_javafx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BindingButtons extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button smaller = new Button("Smaller");
        Button larger = new Button("larger");
        Rectangle gauge = new Rectangle(0, 5, 50, 15);
        Rectangle outline = new Rectangle(0, 5, 100, 15);
        outline.setFill(null);
        outline.setStroke(Color.BLACK);

        Pane pane = new Pane();
        pane.getChildren().addAll(gauge, outline);
        smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
        larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));
        smaller.disableProperty().bind(Bindings.lessThanOrEqual(gauge.widthProperty(), 0));
        larger.disableProperty().bind(Bindings.greaterThanOrEqual(gauge.widthProperty(), 100));

        HBox box = new HBox(10);
        box.getChildren().addAll(smaller, pane, larger);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
