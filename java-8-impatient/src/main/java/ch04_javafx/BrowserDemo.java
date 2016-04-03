package ch04_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 */
public class BrowserDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String location = "http://kwonnam.pe.kr/wiki";
        WebView browser = new WebView();
        WebEngine engine = browser.getEngine();
        engine.load(location);

        Scene scene = new Scene(browser);
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
