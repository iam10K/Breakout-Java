import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tyler on 1/29/17.
 */
public class BreakoutGame extends JPanel implements Settings, KeyListener {

    private Ball ball;
    private Player player;
    private Brick[] bricks;

    private String message = "Press any key to start. Use arrow keys to play.";
    private boolean playing = false;

    private Timer timer;


    public BreakoutGame() {
        setBackground(Color.DARK_GRAY);
    }

    private void initObjects() {
        // Setup timer
        timer = new Timer();

        ball = new Ball(BALL_POS_X, BALL_POS_Y, BALL_DIAMETER);
        player = new Player(PLAYER_POS_X, PLAYER_POS_Y, PLAYER_WIDTH, PLAYER_HEIGHT);

        bricks = new Brick[BRICK_ROWS * BRICK_COLS];
        int coli = 0;
        for (int i = 0; i < bricks.length; i++) {
            // Update column every 10th brick
            coli += i % BRICK_COLS == 0 ? 1 : 0;
            // Update row index every brick
            int rowi = i % BRICK_COLS;

            // Calculate brick x & y positions
            int x = rowi * BRICK_WIDTH + BRICK_SIDE_PADDING + 2 * rowi;
            int y = coli * BRICK_HEIGHT + BRICK_TOP_PADDING + 2 * coli;
            // Increase strength if 2 rows have been made
            bricks[i] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, BRICK_STRENGTH);
        }
    }

    private void startGame() {
        initObjects();

        playing = true;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                ball.updatePosition();
                player.updatePosition();

                // Check if ball collided with any bricks
                checkBallPosition();

                // Repaint the gui
                repaint();
            }
        }, GAME_LOAD_TIME, GAME_UPDATE_INTERVAL);
    }

    private void endGame() {
        playing = false;

        timer.cancel();

        // Setup timer
        timer = new Timer();
    }

    // http://stackoverflow.com/questions/5446396/#5446768
    // Paint the components
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        if (playing) {
            paintBreakoutObjects(graphics2D);
        } else {
            paintMessage(graphics2D);
        }
    }

    private void paintBreakoutObjects(Graphics2D graphics2D) {
        graphics2D.setColor(Color.CYAN);
        graphics2D.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        graphics2D.setColor(Color.RED);
        graphics2D.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

        graphics2D.setColor(Color.ORANGE);
        for (Brick b : bricks) {
            // Render all non broken bricks
            if (!b.isBroken()) {
                graphics2D.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        }

    }

    private void paintMessage(Graphics2D graphics2D) {
        Font font = new Font("Arial", Font.PLAIN, 16);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(font);
        // Center the text based on width
        int x = (WINDOW_WIDTH - fontMetrics.stringWidth(message)) / 2;
        graphics2D.drawString(message, x, WINDOW_HEIGHT / 2);
    }

    public void checkBallPosition() {

        if (ball.getBottom() > GAME_BOTTOM) {
            // Lose game
            message = "Game over, you missed the ball. Press any key to restart.";

            endGame();

            return;
        }

        // Check if ball hits the player
        if (ball.getEllipse().intersects(player.getRectangle())) {
            ball.setyDirection(-1);

            // Set direction based on which half of player hits the ball
            // Left half the ball goes left, right the ball goes right
            if (ball.getXCenter() < player.getXCenter()) {
                ball.setxDirection(-1);
            } else {
                ball.setxDirection(1);
            }
        }

        int broken = 0;
        for (Brick b : bricks) {
            if (b.isBroken()) {
                broken++;
                continue;
            }

            if (ball.getEllipse().intersects(b.getRectangle())) {
                b.damageBrick();

                int ballTop = ball.getY();
                int ballBottom = ball.getBottom();
                int ballLeft = ball.getX();
                int ballRight = ball.getRight();
                int ballCenter = ball.getXCenter();

                // right portion of ball hits the brick
                if (b.getRectangle().contains(ballRight, ballTop)
                        || b.getRectangle().contains(ballRight, ballBottom)) {
                    // Bounce left or right depending on which half of the brick the ball hits
                    if (ballCenter < b.getXCenter()) {
                        ball.setxDirection(-1);
                    } else {
                        ball.setxDirection(1);
                    }
                } else if (b.getRectangle().contains(ballLeft, ballTop)
                        || b.getRectangle().contains(ballLeft, ballBottom)) { // Left portion of ball hits the brick
                    // Bounce left or right depending on which half of the brick the ball hits
                    if (ballCenter < b.getXCenter()) {
                        ball.setxDirection(1);
                    } else {
                        ball.setxDirection(-1);
                    }
                }

                // Top portion of ball hits brick
                if (b.getRectangle().contains(ballCenter, ballTop)) {
                    ball.setyDirection(1);
                } else if (b.getRectangle().contains(ballCenter, ballBottom)) { // Bottom portion of ball hits brick
                    ball.setyDirection(-1);
                }

            }
        }

        // Check if game is over
        if (broken == bricks.length) {
            // End game
            message = "You Won! Press any key to play again.";

            endGame();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (playing) {
            player.keyHandler(e, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (playing) {
            player.keyHandler(e, false);
        } else {
            startGame();
        }
    }
}
