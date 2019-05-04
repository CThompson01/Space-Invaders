package gui;

import gameobjects.Bullet;
import gameobjects.Enemies;
import gameobjects.GameObject;
import gameobjects.PlayerShip;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class EngineRender {
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics2D graphics;

    private final int DEFAULT_SHADOW_OFFSET = 2;

    EngineRender(Canvas c) {
        canvas = c;
        bufferStrategy = canvas.getBufferStrategy();
    }

    void renderScreen(PlayerShip playerShip, Bullet bullet, Enemies[][] enemies) {
        graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

        clear();

        // Draw shit here
        render(playerShip);
        render(bullet);

        for (Enemies[] enemyRow : enemies)
            for (Enemies enemy : enemyRow)
                if (!enemy.isDead)
                    render(enemy);

        graphics.dispose();

        bufferStrategy.show();

        Toolkit.getDefaultToolkit().sync();
    }

    private void render(GameObject gameObject) {
        graphics.setColor(gameObject.getColor());
        graphics.fillRect(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
    }

    private void renderShadow(GameObject gameObject) {
        renderShadow(gameObject, DEFAULT_SHADOW_OFFSET);
    }

    private void renderShadow(GameObject gameObject, int offset) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(gameObject.getX() + offset, gameObject.getY() + offset, gameObject.getWidth(), gameObject.getHeight());
    }

    private void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
}
