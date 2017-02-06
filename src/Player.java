import java.awt.event.KeyEvent;

/**
 * Created by tyler on 1/29/17.
 */
public class Player extends BMoveableObject {

    public Player(int x, int y) {
        super(x, y);
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void updatePosition() {
        x += xDirection;

        // Hit left wall
        if (x <= 0) {
            xDirection = 0;
        }
        // Hit right wall
        if (getRight() >= Settings.WINDOW_WIDTH) {
            xDirection = 0;
        }
    }

    /**
     * Update the direction of the player
     * @param e KeyEvent
     * @param isPressed true if key is being pressed. False if it is being released
     */
    public void keyHandler(KeyEvent e, boolean isPressed) {
        int key = e.getKeyCode();

        // Set moving object to move left if not already at the wall
        if (key == KeyEvent.VK_LEFT && x != 0) {
            xDirection = isPressed ? -Settings.PLAYER_INC_SPEED : 0;
        }

        // Set the moving object to the right, if not already at wall
        if (key == KeyEvent.VK_RIGHT && x + width != Settings.WINDOW_WIDTH) {
            xDirection = isPressed ? Settings.PLAYER_INC_SPEED : 0;
        }
    }
}
