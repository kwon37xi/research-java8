package ch04_javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 */
public class HboxLayout extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final double rem = new Text("").getLayoutBounds().getHeight();

        HBox buttons = new HBox(10); // 컨트롤간 10px
        buttons.getChildren()
            .addAll(new Button("Yes"), new Button("No"), new Button("Maybe"));

        VBox pane = new VBox(10);
        pane.getChildren().addAll(new Label("Will you attend?"), buttons);
        pane.setPadding(new Insets(0.8 * rem)); // layout 내부 padding
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
