package ch17_javafx.sec07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewControlsController extends Application implements Initializable {
    @FXML private ListView<String> listView;
    @FXML private TableView<Phone> tableView;
    @FXML private ImageView imageView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view_controls.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(FXCollections.observableArrayList("갤럭시S1", "갤럭시S2", "갤럭시S3", "갤럭시S4", "갤럭시S5", "갤럭시S6", "갤럭시S7"));

        listView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            tableView.getSelectionModel().select(newValue.intValue());
            tableView.scrollTo(newValue.intValue());
        });

        ObservableList phoneList = FXCollections.observableArrayList(
            new Phone("갤럭시S1", "phone01.png"),
            new Phone("갤럭시S2", "phone02.png"),
            new Phone("갤럭시S3", "phone03.png"),
            new Phone("갤럭시S4", "phone04.png"),
            new Phone("갤럭시S5", "phone05.png"),
            new Phone("갤럭시S6", "phone06.png"),
            new Phone("갤럭시S7", "phone07.png")
        );

        TableColumn tcSmartPhone = tableView.getColumns().get(0);
        tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone")); // 모델의 smartPhone 프라퍼티 매핑?
        tcSmartPhone.setStyle("-fx-alignment: CENTER;");

        TableColumn tcImage = tableView.getColumns().get(1);
        tcImage.setCellValueFactory(new PropertyValueFactory("image"));
        tcImage.setStyle("fc-alignment: CENTER;");

        tableView.setItems(phoneList);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("선택됨 : " + oldValue + " -> " + newValue);
            if (newValue != null) {
                imageView.setImage(new Image(getClass().getResource("/images/" + newValue.getImage()).toString()));
            }
        });
    }

    public void handleBtnOkAction(ActionEvent e) {
        String item = listView.getSelectionModel().getSelectedItem();
        System.out.println("ListView 스마트폰: " + item);

        Phone phone = tableView.getSelectionModel().getSelectedItem();
        System.out.println("Table view 스마트폰: " + phone.getSmartPhone());
        System.out.println("TableView 이미지: " + phone.getImage());
    }

    public void handleBtnCancelAction(ActionEvent e) {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
