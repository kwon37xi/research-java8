package ch04_javafx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 */
public class FXMLLayoutWithId extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layoutwithid.fxml")); // 클래스와 다른 패키지라서 절대 경로로 지정 필요.
        // lookup에 실패하면 그냥 null을 리턴한다.
        TextField username = (TextField) root.lookup("#username");
        PasswordField password = (PasswordField) root.lookup("#password");
        Button okButton = (Button) root.lookup("#okButton");

        okButton.disableProperty().bind(
            Bindings.createBooleanBinding(
                () -> username.getText().length() == 0 ||
                    password.getText().length() == 0,
                username.textProperty(),
                password.textProperty())
        );

        okButton.setOnAction(event ->
            System.out.println("Verifying " + username.getText() + ":" + password.getText())
        );

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
