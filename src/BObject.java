import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by tyler on 1/29/17.
 */
public class BObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color color;

    public BObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXCenter() { return x + width/2; }

    public int getYCenter() { return y + height/2; }

    public int getRight() { return x + width; }

    public int getBottom() { return y + height; }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}
