package ch17_javafx.sec07;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class DialogsController extends Application implements Initializable {
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dialogs.fxml"));

        Parent root = loader.load();

        // Controller에 primaryStage 주입
        DialogsController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Dialogs");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleOpenFileChooser(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
            new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            System.out.println(selectedFile.getPath());
        }
    }

    public void handleSaveFileChooser(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showSaveDialog(primaryStage);
        if (selectedFile != null) {
            System.out.println(selectedFile.getPath());
        }
    }

    public void handleDirectoryChooser(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDir = directoryChooser.showDialog(primaryStage);
        if (selectedDir != null) {
            System.out.println(selectedDir.getPath());
        }
    }

    public void handlePopup(ActionEvent actionEvent) throws IOException {
        Popup popup = new Popup();

        HBox hbox = FXMLLoader.load(getClass().getResource("/popup.fxml"));
        ImageView imageView = (ImageView) hbox.lookup("#imgMessage");
        imageView.setImage(new Image(getClass().getResource("/images/dialog-info.png").toString()));
        imageView.setOnMouseClicked(event -> popup.hide());
        Label lblMessage = (Label) hbox.lookup("#lblMessage");
        lblMessage.setText("메시지가 도착했습니다.");

        popup.getContent().add(hbox);
        popup.setAutoHide(true);
        popup.show(primaryStage);
    }

    public void handleCustom(ActionEvent actionEvent) throws IOException {
        Stage dialog = new Stage(StageStyle.UTILITY);

        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("확인");

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/custom_dialog.fxml"));
        Label txtTitle = (Label) anchorPane.lookup("#txtTitle");
        txtTitle .setText("확인하셨습니까?");
        Button btnOk = (Button)anchorPane.lookup("#btnOk");
        btnOk.setOnAction(event -> dialog.close());

        Scene scene = new Scene(anchorPane);
        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
