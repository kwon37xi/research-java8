package ch04_javafx.exec;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * 중앙에 원이 머물면서 적어도 씬의 두 면은 항상 닿게하기
 */
public class Exec04 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle();
        circle.setFill(Color.RED);
        Pane pane = new Pane();
        pane.setPrefHeight(200);
        pane.setPrefWidth(200);
        pane.getChildren().add(circle);
        Scene scene = new Scene(pane);
        circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));

        // 아래 바인딩이 핵심이다.
        circle.radiusProperty().bind(Bindings.divide(Bindings.min(pane.heightProperty(), pane.widthProperty()),2));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
