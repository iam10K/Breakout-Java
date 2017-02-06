/**
 * Created by tyler on 2/5/17.
 */
public class BMoveableObject extends BObject {

    protected int xDirection;
    protected int yDirection;

    public BMoveableObject(int x, int y) {
        super(x, y);
    }

    public BMoveableObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void updatePosition() {
        x += xDirection;
        y += yDirection;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

}
