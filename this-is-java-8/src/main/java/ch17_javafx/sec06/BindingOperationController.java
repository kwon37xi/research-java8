package ch17_javafx.sec06;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BindingOperationController extends Application implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Circle circle;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final AnchorPane root = FXMLLoader.load(getClass().getResource("/binding_operation.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        circle.centerXProperty().bind(Bindings.divide(root.widthProperty(), 2));
        circle.centerYProperty().bind(Bindings.divide(root.heightProperty(), 2));
        // 넓이와 높이의 반중에서 더 작은쪽에 맞춰서 반지름 지정.
        circle.radiusProperty().bind(Bindings.min(Bindings.divide(root.widthProperty(), 2), Bindings.divide(root.heightProperty(), 2)));
    }
}
