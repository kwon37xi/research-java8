package ch02;

public class Apple {
    private String color;
    private Integer weight;

    public Apple() {
    }

    public Apple(String color, Integer weight) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
            "color='" + color + '\'' +
            ", weight=" + weight +
            '}';
    }
}
