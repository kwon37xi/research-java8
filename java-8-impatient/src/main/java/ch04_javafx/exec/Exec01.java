package ch04_javafx.exec;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 텍스트 필드와 레이블이 있고, 텍스트 필드를 업데이트하면 레이블도 업데이트 한다.
 */
public class Exec01 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label();
        label.setFont(new Font(100));

        TextField text = new TextField();
        text.setText("Hello, FX");

        // 바인딩은 자동으로 변하는 측이 주가 되어한다.
        label.textProperty().bind(text.textProperty());

        VBox box = new VBox(10);
        box.getChildren()
            .addAll(label, text);

        final Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
