
/**
 * Created by tyler on 1/29/17.
 */
public class Brick extends BObject {

    private int strength;

    public Brick(int x, int y) {
        super(x, y);
    }

    public Brick(int x, int y, int width, int height, int strength) {
        super(x, y, width, height);
        this.strength = strength;
    }

    public void damageBrick() {
        strength--;
    }

    public boolean isBroken() {
        return strength == 0;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
