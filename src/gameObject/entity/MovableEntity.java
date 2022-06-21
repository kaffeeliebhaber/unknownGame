package gameObject.entity;

import animation.Direction;
import gameObject.CollisionArea;

public class MovableEntity extends Entity {

    protected int movingSpeed;
    protected boolean moveLeft, moveUp, moveRight, moveDown;
    protected boolean canMoveLeft, canMoveRight, canMoveUp, canMoveDown;

    public MovableEntity(int x, int y, int width, int height, int movingSpeed) {
        super(x, y, width, height);

        this.movingSpeed = movingSpeed;
    }

    public void moveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void moveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void moveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void moveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean isMoveLeft() { return moveLeft; }

    public boolean isMoveUp() { return moveUp; }

    public boolean isMoveRight() { return moveRight; }

    public boolean isMoveDown() { return moveDown; }

    public void setCanMoveLeft(boolean canMoveLeft) { this.canMoveLeft = canMoveLeft; }

    public void setCanMoveUp(boolean canMoveUp) { this.canMoveUp = canMoveUp; }

    public void setCanMoveRight(boolean canMoveRight) { this.canMoveRight = canMoveRight; }

    public void setCanMoveDown(boolean canMoveDown) { this.canMoveDown = canMoveDown; }

    public boolean canMoveLeft() { return canMoveLeft; }

    public boolean canMoveRight() { return canMoveRight; }

    public boolean canMoveUp() { return canMoveUp; }

    public boolean canMoveDown() { return canMoveDown; }

    private void resetCanMove() {
        canMoveLeft = canMoveUp = canMoveRight = canMoveDown = true;
    }

    private void move() {

        if (moveLeft && canMoveLeft) {
            translateX(-movingSpeed);
        }

        if (moveUp && canMoveUp) {
            translateY(-movingSpeed);
        }

        if (moveRight && canMoveRight) {
            translateX(movingSpeed);
        }

        if (moveDown && canMoveDown) {
            translateY(movingSpeed);
        }
    }

    public void update() {

        move();

        resetCanMove();
    }

    public int getMovingSpeed() {
        return movingSpeed;
    }

    public int getXLeftAfterMoving() {
        return x + movingSpeed;
    }

    public int getXRightAfterMoving() {
        return getXRight() + movingSpeed;
    }

    public int getYTopAfterMoving() {
        return y + movingSpeed;
    }

    public int getYBottomAfterMoving() {
        return getYBottom() + movingSpeed;
    }

    public boolean intersects(final Entity entity) {
        return getCollisionAreaAfterMoving().intersects(entity.getCollisionArea());
    }

    public CollisionArea getCollisionAreaAfterMoving() {
        return new CollisionArea(
                getCollisionAreaXLeftAfterMoving(),
                getCollisionAreaYTopAfterMoving(),
                getCollisionAreaXRightAfterMoving() - getCollisionAreaXLeftAfterMoving(),
                getCollisionAreaYBottomAfterMoving() - getCollisionAreaYTopAfterMoving());
    }

    public CollisionArea getCollisionAreaAfterMoving(final Direction movingDirection) {

        int x = 0;
        int y = 0;

        switch (movingDirection) {

            case UP:
            case DOWN:
                x = getCollisionArea().getX();
                y = getCollisionAreaYTopAfterMoving();

                break;

            case LEFT:
            case RIGHT:

                x = getCollisionAreaXLeftAfterMoving();
                y = getCollisionArea().getY();

                break;
        }

        return new CollisionArea(x, y, getCollisionArea().getWidth(), getCollisionArea().getHeight());
    }

    public int getCollisionAreaXLeftAfterMoving() {

        int xLeftAfterMoving = getCollisionArea().getX();

        if (moveLeft) {
            xLeftAfterMoving -= movingSpeed;
        } else if (moveRight) {
            xLeftAfterMoving += movingSpeed;
        }

        return xLeftAfterMoving;
    }

    public int getCollisionAreaXRightAfterMoving() {

        int xRightAfterMoving = getCollisionArea().getXRight();

        if (moveLeft) {
            xRightAfterMoving -= movingSpeed;
        } else if (moveRight) {
            xRightAfterMoving += movingSpeed;
        }

        return xRightAfterMoving;
    }

    public int getCollisionAreaYTopAfterMoving() {

        int yTopAfterMoving = getCollisionArea().getY();

        if (moveUp) {
            yTopAfterMoving -= movingSpeed;
        } else if (moveDown) {
            yTopAfterMoving += movingSpeed;
        }

        return yTopAfterMoving;
    }

    public int getCollisionAreaYBottomAfterMoving() {

        int yBottomAfterMoving = getCollisionArea().getYBottom();

        if (moveUp) {
            yBottomAfterMoving -= movingSpeed;
        } else if (moveDown) {
            yBottomAfterMoving += movingSpeed;
        }

        return yBottomAfterMoving;
    }

    public String getClassNameForToString() {
        return "MovableEntity";
    }
}
