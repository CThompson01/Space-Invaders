package gui;

import gameobjects.Bullet;
import gameobjects.Enemies;
import gameobjects.PlayerShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine {

    Canvas gameCanvas;
    EngineRender renderer;

    private final int FPS = 120;

    // Game Objects
    private PlayerShip player;
    private Bullet bullet;
    private Enemies[][] enemies;

    private final int ROWS_OF_ENEMIES = 3;
    private final int COLS_OF_ENEMIES = 8;

    private int ticks = 0;
    private int enemyDx = -1;

    public GameEngine(Canvas c) {
        gameCanvas = c;

        renderer = new EngineRender(gameCanvas);
        resetStage();

        Timer gameTimer = new Timer(1000/FPS, e -> {
            tick();
            render();
        });

        gameTimer.start();
    }

    private void tick() {
        ticks++;

        // Collisions
        playerCollisions();
        bulletCollisions();

        // Moves objects
        updateGameObjects();
    }

    private void playerCollisions() {
        // Handles player movement
        if (player.moveLeft && player.dx > -player.MAX_VELOCITY)
            player.dx += -1;
        else if (player.moveRight && player.dx < player.MAX_VELOCITY)
            player.dx += 1;
        else if (player.dx > 0)
            player.dx += -1;
        else if (player.dx < 0)
            player.dx += 1;

        // Checks for wall collisions
        int leftPlayer = player.getX();
        int rightPlayer = player.getX() + player.getWidth();
        if (leftPlayer <= 0 && player.dx < 0)
            player.dx = 0;
        else if (rightPlayer >= gameCanvas.getWidth() && player.dx > 0)
            player.dx = 0;

        // Checks for the enemies hitting the player
        for (int i = enemies.length-1; i > 0; i--) {
            for (int x = 0; x < enemies[i].length; x++) {
                if (enemies[i][x].getY() + enemies[i][x].getHeight() >= player.getY() && !enemies[i][x].isDead) {
                    JOptionPane.showMessageDialog(gameCanvas, "YOU HAVE DIED!");
                    resetStage();
                } else if (!enemies[i][x].isDead)
                    break;
            }
        }
    }

    private void bulletCollisions() {
        int topOfBullet = bullet.getY();
        int botOfBullet = bullet.getY() + bullet.getHeight();
        int leftOfBullet = bullet.getX();
        int rightOfBullet = bullet.getX() + bullet.getWidth();

        if (botOfBullet <= 0)
            bullet.killBullet();

        for (Enemies[] enemyRow : enemies) {
            for (Enemies enemy : enemyRow) {
                if (topOfBullet <= enemy.getY() + enemy.getHeight() &&
                        botOfBullet >= enemy.getY() &&
                        leftOfBullet <= enemy.getX() + enemy.getWidth() &&
                        rightOfBullet >= enemy.getX() && !enemy.isDead) {
                    enemy.isDead = true;
                    bullet.killBullet();
                }
            }
        }
    }

    private void updateGameObjects() {
        // Updates the player position
        player.translatePosition();

        // Updates the bullets position
        bullet.translatePosition();

        // Moves the enemies
//        if (ticks % 2 == 0) {
            for (Enemies[] enemyRow : enemies)
                for (Enemies enemy : enemyRow)
                    enemy.translatePosition(enemyDx,0);
//        }


        // Checks if the enemies are hitting a wall
        if (enemyDx < 0) {
            for (int i = 0; i < enemies[0].length; i++) {
                for (int x = 0; x < enemies.length; x++) {
                    if (enemies[x][i].getX() <= 0 && !enemies[x][i].isDead) {
                        dropEnemies();
                        enemyDx = 1;
                        break;
                    }
                }
            }
        } else {
            for (int i = enemies[0].length - 1; i > 0; i--) {
                for (int x = 0; x < enemies.length; x++) {
                    if (enemies[x][i].getX() + enemies[x][i].getWidth() >= gameCanvas.getWidth() && !enemies[x][i].isDead) {
                        dropEnemies();
                        enemyDx = -1;
                        break;
                    }
                }
            }
        }
    }

    private void dropEnemies() {
        for (Enemies[] enemyRow : enemies) {
            for (Enemies enemy : enemyRow) {
                enemy.translatePosition(0,(gameCanvas.getHeight()/40));
            }
        }
    }

    private void render() {
        renderer.renderScreen(player, bullet, enemies);
    }

    public void playerMovement(char dir) {
        if (dir == 'l')
            player.moveLeft = true;
        else if (dir == 'r')
            player.moveRight = true;
        else {
            player.moveLeft = false;
            player.moveRight = false;
        }
    }

    public void shootBullets() {
        if (!bullet.exists)
            bullet.createBullet(player, gameCanvas);
    }

    private void resetStage() {
        // Initializes player in the middle of the screen
        player = new PlayerShip(gameCanvas.getWidth()/2,gameCanvas.getHeight(),gameCanvas.getWidth()/15,gameCanvas.getWidth()/10);
        player.translatePosition(-player.getWidth()/2, -player.getHeight());
        player.setColor(Color.CYAN);

        // Initializes bullet
        bullet = new Bullet(Integer.MAX_VALUE,Integer.MAX_VALUE,5,5);

        // Initializes the enemies
        enemies = new Enemies[ROWS_OF_ENEMIES][COLS_OF_ENEMIES];
        for (int i = 0; i < enemies.length; i++) {
            for (int x = 0; x < enemies[i].length; x++) {
                enemies[i][x] = new Enemies(((gameCanvas.getWidth()/(COLS_OF_ENEMIES * 2))*x),(gameCanvas.getHeight()/(ROWS_OF_ENEMIES * 13)) * i,
                        gameCanvas.getWidth()/20, gameCanvas.getWidth()/20);
                enemies[i][x].translatePosition(((gameCanvas.getWidth()/COLS_OF_ENEMIES* (x+1)) - enemies[i][x].getWidth())/2,((gameCanvas.getHeight()/20) * (i)) + 10);
                enemies[i][x].setColor(Color.PINK);
            }
        }
    }
}
