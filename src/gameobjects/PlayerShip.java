package gameobjects;

public class PlayerShip extends GameObject {
    public int dx;
    public int dy;
    public final int MAX_VELOCITY = 5;
    public boolean moveLeft, moveRight;

    public PlayerShip() {
        super();
        initVariables();
    }

    public PlayerShip(int defaultX, int defaultY, int width, int height) {
        super(defaultX, defaultY, width, height);
        initVariables();
    }

    private void initVariables() {
        this.dx = 0;
        this.dy = 0;
        moveLeft = false;
        moveRight = false;
    }

    public void setVelocity(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void translatePosition() {
        translatePosition(this.dx, this.dy);
    }
}
