package ch17_javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class AppMain extends Application {
    public AppMain() {
        System.out.println(Thread.currentThread().getName() + " : 생성자 호출");
    }

    @Override
    public void init() throws Exception {
        System.out.println(Thread.currentThread().getName() + " : init() 호출");

        Parameters params = getParameters();
        List<String> list = params.getRaw();
        Map<String, String> map = params.getNamed();

        System.out.println("Parameter list : " + list);
        System.out.println("Parameter named : " + map);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(Thread.currentThread().getName() + " : start() 호출");
        primaryStage.setTitle("AppMain thread test");

        VBox root = new VBox();
        root.setPrefWidth(350);
        root.setPrefHeight(150);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        Label label = new Label();
        label.setText("Hello, JavaFX");
        label.setFont(new Font(50));

        Button button = new Button();
        button.setText("확인");
        button.setOnAction(event -> Platform.exit());

        root.getChildren().addAll(label, button);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println(Thread.currentThread().getName() + " : stop() 호출");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : main() 호출");
        launch(args);
    }
}
