import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame1 extends JPanel implements ActionListener {
    private static final int SCREEN_WIDTH = 300;
    private static final int SCREEN_HEIGHT = 300;
    private static final int UNIT_SIZE = 10;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 75;
    private final int[] snakeX = new int[GAME_UNITS];
    private final int[] snakeY = new int[GAME_UNITS];
    private int snakeLength = 6;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private static final Logger logger = Logger.getLogger(SnakeGame1.class.getName());

    public SnakeGame1() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        snakeX[0] = 2 * UNIT_SIZE;
        snakeY[0] = 2 * UNIT_SIZE;
        spawnApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void spawnApple() {
        appleX = (int) (Math.random() * (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = (int) (Math.random() * (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = snakeLength; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (direction) {
            case 'U':
                snakeY[0] -= UNIT_SIZE;
                break;
            case 'D':
                snakeY[0] += UNIT_SIZE;
                break;
            case 'L':
                snakeX[0] -= UNIT_SIZE;
                break;
            case 'R':
                snakeX[0] += UNIT_SIZE;
                break;
        }
    }

    public void checkCollision() {
        // Check if the snake collides with the boundaries
        if (snakeX[0] < 0 || snakeY[0] < 0 || snakeX[0] >= SCREEN_WIDTH || snakeY[0] >= SCREEN_HEIGHT) {
            running = false;
            timer.stop();
            logger.log(Level.INFO, "Snake collided with boundaries");
        }

        // Check if the snake collides with itself
        for (int i = 1; i < snakeLength; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                running = false;
                timer.stop();
                logger.log(Level.INFO, "Snake collided with itself");
                break;
            }
        }

        // Check if the snake eats the apple
        if (snakeX[0] == appleX && snakeY[0] == appleY) {
            snakeLength++;
            spawnApple();
            logger.log(Level.INFO, "Snake ate the apple");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Draw apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Draw snake
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(Color.orange);
                }
                g.fillRect(snakeX[i], snakeY[i], UNIT_SIZE, UNIT_SIZE);
            }
        } else {
            // Game over
            // Display "Game Over" message
            g.setColor(Color.red);
            g.setFont(new java.awt.Font("Sans Serif", Font.BOLD, 40));
            g.drawString("Game Over", SCREEN_WIDTH / 4, SCREEN_HEIGHT / 2);
        }
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                        logger.log(Level.INFO, "Left key pressed");
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                        logger.log(Level.INFO, "Right key pressed");
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                        logger.log(Level.INFO, "Up key pressed");
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                        logger.log(Level.INFO, "Down key pressed");
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame1 game = new SnakeGame1();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
