package ch17_javafx.exec;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 테이블 데이터 모델
 */
public class Student {
    private SimpleStringProperty nameProperty;
    private SimpleIntegerProperty koreanProperty;
    private SimpleIntegerProperty mathProperty;
    private SimpleIntegerProperty englishProperty;

    public Student(String name, Integer korean, Integer math, Integer english) {
        nameProperty = new SimpleStringProperty(name);
        koreanProperty = new SimpleIntegerProperty(korean);
        mathProperty = new SimpleIntegerProperty(math);
        englishProperty = new SimpleIntegerProperty(english);
    }

    public String getName() {
        return nameProperty.getValue();
    }

    public void setName(String name) {
        nameProperty.setValue(name);
    }

    public Integer getKorean() {
        return koreanProperty.getValue();
    }

    public void setKorean(Integer korean) {
        koreanProperty.setValue(korean);
    }

    public Integer getMath() {
        return mathProperty.getValue();
    }

    public void setMath(Integer math) {
        mathProperty.setValue(math);
    }

    public Integer getEnglish() {
        return englishProperty.getValue();
    }

    public void setEnglish(Integer english) {
        englishProperty.setValue(english);
    }

    @Override
    public String toString() {
        return "Student{" +
            "nameProperty=" + nameProperty +
            ", koreanProperty=" + koreanProperty +
            ", mathProperty=" + mathProperty +
            ", englishProperty=" + englishProperty +
            '}';
    }
}
