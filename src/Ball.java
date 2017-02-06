import java.awt.geom.Ellipse2D;

/**
 * Created by tyler on 1/29/17.
 */
public class Ball extends BMoveableObject {

    public Ball(int x, int y, int diameter) {
        super(x, y, diameter, diameter);

        xDirection = 1;
        yDirection = -1;
    }

    public void updatePosition() {
        x += xDirection;
        y += yDirection;


        // Hit left side
        if (x <= 0) {
            // Bounce off, send ball right
            xDirection = 1;
        }

        // Hit right side
        if (x + width >= Settings.WINDOW_WIDTH) {
            // Bounce off send ball left
            xDirection = -1;
        }

        // Hit top
        if (y == 0) {
            // Bounce off, send ball down(up in c0ordinates)
            yDirection = 1;
        }
    }

    public Ellipse2D getEllipse() {
        return new Ellipse2D.Double(x,y,width,height);
    }
}
