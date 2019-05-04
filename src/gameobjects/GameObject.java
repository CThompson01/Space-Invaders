package gameobjects;

import java.awt.*;

public class GameObject {
    int x;
    int y;
    int width;
    int height;
    Color color;

    public GameObject() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        color = Color.BLUE;
    }

    public GameObject(int defaultX, int defaultY, int width, int height) {
        this.x = defaultX;
        this.y = defaultY;
        this.width = width;
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void translatePosition(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
