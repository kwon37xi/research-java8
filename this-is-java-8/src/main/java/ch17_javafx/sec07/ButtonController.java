package ch17_javafx.sec07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ButtonController extends Application implements Initializable {
    @FXML
    private CheckBox chk1;
    @FXML
    private CheckBox chk2;
    @FXML
    private ImageView checkImageView;
    @FXML
    private ToggleGroup group;
    @FXML
    private ImageView radioImageView;
    @FXML
    private Button btnExit;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/buttons.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            Image image = new Image(getClass().getResource("/images/" + newValue.getUserData().toString() + ".png").toString());
            radioImageView.setImage(image);
        });
    }

    // ActionEvent를 실수로 awt 클래스로 import 해서 작동하지 않는 현상이 발생했었음.
    public void handleChkAction(ActionEvent e) {
        String imageName = null;

        if (chk1.isSelected() && chk2.isSelected()) {
            imageName = "geek-glasses-hair.gif";
        } else if (chk1.isSelected()) {
            imageName = "geek-glasses.gif";
        } else if (chk2.isSelected()) {
            imageName = "geek-hair.gif";
        } else {
            imageName = "geek.gif";
        }

        checkImageView.setImage(new Image(getClass().getResource("/images/" + imageName).toString()));
    }

    public void handleBtnExitAction(ActionEvent e) {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
