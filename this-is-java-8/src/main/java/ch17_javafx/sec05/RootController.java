package ch17_javafx.sec05;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    @FXML private Button btn1;
    @FXML private Button btn2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn1.setOnAction(this::handleBtn1Action);
        btn2.setOnAction(this::handleBtn2Action);
    }

    private void handleBtn1Action(ActionEvent actionEvent) {
        System.out.println("버튼 1 클릭");
    }

    private void handleBtn2Action(ActionEvent actionEvent) {
        System.out.println("버튼 2 클릭");
    }

    // public 이어야 한다.
    public void handleBtn3Action(ActionEvent actionEvent) {
        System.out.println("버튼 3 클릭 by FXML");
    }
}
