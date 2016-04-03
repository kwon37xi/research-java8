package ch04_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Pane 이란 레이아웃 정책을 갖춘 컨테이너를 말한다.
 * BorderPane은 동서남북중 5개 영역
 */
public class BorderPaneLayout extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        pane.setTop(new Button("Top"));
        pane.setLeft(new Button("Left"));
        pane.setCenter(new Button("Center"));
        pane.setRight(new Button("Right"));
        pane.setBottom(new Button("Button"));
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
