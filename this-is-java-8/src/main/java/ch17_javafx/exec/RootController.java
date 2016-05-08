package ch17_javafx.exec;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class RootController implements Initializable {
    @FXML private Button addButton;
    @FXML private TableView<Student> gradeTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(this::handleAddButton);

        final ObservableList<TableColumn<Student, ?>> columns = gradeTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<>("korean"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<>("math"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<>("english"));
    }

    public void handleAddButton(ActionEvent event) {
        System.out.println("add button clicked.");

        try {
            final Window primaryWindow = addButton.getScene().getWindow();
            Stage addDialog = new Stage(StageStyle.UTILITY);
            addDialog.initModality(Modality.WINDOW_MODAL);
            addDialog.initOwner(primaryWindow);
            addDialog.setTitle("추가");

            FXMLLoader addDialogFormLoader = new FXMLLoader(getClass().getResource("/exec_form.fxml"));
            final Parent addDialogForm = addDialogFormLoader.load();

            final AddFormController addFormController = addDialogFormLoader.getController();
            addFormController.setGradeTable(gradeTable);
            addFormController.setAddDialog(addDialog);

            Scene addScene = new Scene(addDialogForm);
            addDialog.setResizable(false);
            addDialog.setScene(addScene);
            addDialog.show();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
}
