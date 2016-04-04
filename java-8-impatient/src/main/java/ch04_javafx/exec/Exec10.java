package ch04_javafx.exec;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * 뒤로가기 버튼을 포함하는 브라우저 구현. WebEngine.getHistory() 사용.
 */
public class Exec10 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        final WebHistory history = webEngine.getHistory();
        Button backButton = new Button("Back");
        backButton.setOnAction(event1 -> {
            final int currentIndex = history.getCurrentIndex();
            System.out.println("backbutton clicked - current index : " + currentIndex);
            history.go(-1);
        });

        backButton.disableProperty().bind(Bindings.lessThan(history.currentIndexProperty(), 1));

        Label locationLabel = new Label("Location : ");
        TextField location = new TextField();

        webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
            location.setText(newValue);
        });

        Button goButton = new Button("Go");

        goButton.setOnAction(event -> webEngine.load(location.getText()));

        HBox toolbar = new HBox(10);
        toolbar.getChildren()
            .addAll(backButton, locationLabel, location, goButton);
        HBox.setHgrow(location, Priority.ALWAYS);

        VBox browserArea = new VBox(5);
        browserArea.getChildren().addAll(toolbar, browser);
        browserArea.setPrefHeight(1000);
        VBox.setVgrow(browser, Priority.ALWAYS);

        Scene scene = new Scene(browserArea);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setTitle("Mini Browser");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
