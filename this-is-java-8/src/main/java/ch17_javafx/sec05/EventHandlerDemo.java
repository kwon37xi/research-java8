package ch17_javafx.sec05;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EventHandlerDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox();
        root.setPrefSize(200, 50);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        Button btn1 = new Button("버튼1");
        btn1.setOnAction(event -> System.out.println("버튼 1 클릭"));

        Button btn2 = new Button("버튼2");
        btn2.setOnAction(event -> System.out.println("버튼 2 클릭"));

        root.getChildren().addAll(btn1, btn2);
        Scene scene = new Scene(root);
        primaryStage.setTitle("EventHandler Demo");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> System.out.println("종료 클릭"));
        primaryStage.show();
    }
}
