package gameobjects;

import java.awt.*;

public class Enemies extends GameObject {
    public boolean isDead = false;

    public Enemies() {
        super();
        isDead = false;
        setColor(Color.PINK);
    }

    public Enemies(int defaultX, int defaultY, int width, int height) {
        super(defaultX, defaultY, width, height);
        isDead = false;
        setColor(Color.PINK);
    }
}
