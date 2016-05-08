package ch17_javafx.exec;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddFormController implements Initializable {
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField koreanField;
    @FXML
    private TextField mathField;
    @FXML
    private TextField englishField;

    private Stage addDialog;

    private TableView<Student> gradeTable;

    public void setAddDialog(Stage addDialog) {
        this.addDialog = addDialog;
    }

    public void setGradeTable(TableView<Student> gradeTable) {
        this.gradeTable = gradeTable;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnAction(this::handleSave);
        cancelButton.setOnAction(this::handleCancel);
    }

    private void handleSave(ActionEvent actionEvent) {
        System.out.println("Save called.");
        final ObservableList<Student> items = gradeTable.getItems();

        Student student = new Student(nameField.getText(),
            Integer.parseInt(koreanField.getText()),
            Integer.parseInt(mathField.getText()),
            Integer.parseInt(englishField.getText()));

        items.add(student);

        addDialog.close();
    }

    private void handleCancel(ActionEvent actionEvent) {
        System.out.println("Save cancel called.");
        // Dialog 를 닫으려면 Stage 객체를 확보하여 close() 를 호출한다.
        addDialog.close();
    }
}
