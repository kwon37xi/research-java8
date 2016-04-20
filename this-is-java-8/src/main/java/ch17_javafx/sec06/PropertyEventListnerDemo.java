package ch17_javafx.sec06;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PropertyEventListnerDemo {
    public static void main(String[] args) {
        StringProperty text = new SimpleStringProperty();
        text.addListener((observable, oldValue, newValue) -> {
            System.out.printf("text changed from %s -> %s.%n", oldValue, newValue);
        });

        text.set("Hello");
        text.set("World");
        text.set("안녕~");
    }
}
