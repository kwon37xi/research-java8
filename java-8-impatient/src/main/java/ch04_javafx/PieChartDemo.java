package ch04_javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 *
 */
public class PieChartDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                new PieChart.Data("Asia", 4298723000.0),
                new PieChart.Data("North America", 355361000.0),
                new PieChart.Data("South America", 616644000.0),
                new PieChart.Data("Europe", 742452000.0),
                new PieChart.Data("Africa", 1110635000.0),
                new PieChart.Data("Oceania", 38304000.0)
            );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Population of the Continents");

        Group group = new Group(chart);
        Scene scene = new Scene(group);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
