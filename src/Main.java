import javax.swing.*;
import java.awt.*;

/**
 * Created by tyler on 1/29/17.
 */
public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();


        BreakoutGame breakoutGame = new BreakoutGame();
        window.add(breakoutGame);
        window.addKeyListener(breakoutGame);

        window.setTitle("Breakout Game");
        window.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        window.setResizable(false);
        window.setVisible(true);

        window.setBackground(Color.DARK_GRAY);
    }
}
