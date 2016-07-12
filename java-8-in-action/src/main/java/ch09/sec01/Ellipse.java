package ch09.sec01;

public class Ellipse implements Resizable {
    private int width;
    private int height;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setAbsoluteSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw() {
        System.out.println("Draw " + getWidth() + ", " + getHeight());
    }
}
