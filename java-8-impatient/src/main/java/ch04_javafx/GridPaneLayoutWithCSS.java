package ch04_javafx;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GridPane with CSS
 */
public class GridPaneLayoutWithCSS extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setId("pane"); // CSS에서 참조할 수 있게 ID 설정.

        // css 로 아래를 설정할 것임.
//        pane.setHgap(0.8 * rem);
//        pane.setVgap(0.8 * rem);
//        pane.setPadding(new Insets(0.8 * rem));

        Label usernameLabel = new Label("User name:");
        Label passwordLabel = new Label("Password:");
        TextField username = new TextField();
        PasswordField password = new PasswordField();

        Button okButton = new Button("Ok");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getStyleClass().add("buttonrow");
        buttons.getChildren().addAll(okButton, cancelButton);
        buttons.setAlignment(Pos.CENTER);

        pane.add(usernameLabel, 0, 0);
        pane.add(username, 1, 0);
        pane.add(passwordLabel, 0, 1);
        pane.add(password, 1, 1);
        pane.add(buttons, 0, 2, 2, 1);

        GridPane.setHalignment(usernameLabel, HPos.RIGHT);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);

        final Scene scene = new Scene(pane);
        scene.getStylesheets().add("/scene.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
