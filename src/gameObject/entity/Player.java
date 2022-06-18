package gameObject.entity;

public class Player extends MovableEntity {

    public Player(final int x, final int y, final int width, final int height, final int movingSpeed) {
        super(x, y, width, height, movingSpeed);
    }

    public String getClassNameForToString() {
        return "Player";
    }

}
