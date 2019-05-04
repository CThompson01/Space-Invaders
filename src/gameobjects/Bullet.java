package gameobjects;

import java.awt.*;

public class Bullet extends GameObject {
    private final int DEFAULT_SPEED = 4;
    private int dy;
    public boolean exists;

    public Bullet() {
        super();
        setColor(Color.RED);
    }

    public Bullet(int defaultX, int defaultY, int width, int height) {
        super(defaultX, defaultY, width, height);
        setColor(Color.RED);
        killBullet();
    }

    public void translatePosition() { translatePosition(0, this.dy); }

    public void createBullet(GameObject parentObject, Canvas c) {
        this.width = c.getWidth()/100;
        this.height = c.getWidth()/100;
        setPosition((parentObject.x + (parentObject.getWidth()/2)) - (this.width/2), parentObject.y - this.width);
        this.dy = -DEFAULT_SPEED;
        this.exists = true;
    }

    public void killBullet() {
        setPosition(Integer.MAX_VALUE,Integer.MIN_VALUE);
        this.dy = 0;
        this.exists = false;
    }
}
