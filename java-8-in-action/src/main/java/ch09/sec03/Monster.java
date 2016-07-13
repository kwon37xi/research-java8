package ch09.sec03;

import ch09.sec01.Resizable;
import javafx.animation.RotateTransition;

/**
 *
 */
public class Monster implements Rotatable, Moveable, Resizable {
    private int x;
    private int y;
    private int width;
    private int height;
    private int rotationAngle;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

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
        System.out.println(String.format("Drawing monster! x : %d, y : %d, w: %d, h: %d, rotationAngle : %d." , x, y, width, height, rotationAngle));
    }

    @Override
    public void setRotationAngle(int angleInDegress) {
        this.rotationAngle = angleInDegress;
    }

    @Override
    public int getRotationAngle() {
        return rotationAngle;
    }

    public static void main(String[] args) {
        Monster m = new Monster();

        m.draw();

        m.rotateBy(180);
        m.moveVertically(10);

        m.draw();
    }
}
