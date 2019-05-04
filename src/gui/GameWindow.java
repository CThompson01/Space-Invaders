package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener {
    Canvas gameCanvas;
    GameEngine game;

    public GameWindow() {
        this.setTitle("Space Invaders");
        this.setSize(new Dimension(800,800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        gameCanvas = new Canvas();
        gameCanvas.addKeyListener(this);
        this.add(gameCanvas);
        gameCanvas.createBufferStrategy(3);
        gameCanvas.requestFocus();

        game = new GameEngine(gameCanvas);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == 65)
            game.playerMovement('l');
        if (keyCode == 68) // Right press
            game.playerMovement('r');
        if (keyCode == 87 || keyCode == 32) // Shoot
            game.shootBullets();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == 65) // Left press
            game.playerMovement('n');
        if (keyCode == 68) // Right press
            game.playerMovement('n');
    }
}
