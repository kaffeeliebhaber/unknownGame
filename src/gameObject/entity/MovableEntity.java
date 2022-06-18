package gameObject.entity;

import animation.Direction;

public class MovableEntity extends Entity {

    protected Direction movingDirection;
    protected int movingSpeed;
    protected boolean moveLeft, moveUp, moveRight, moveDown;

    public MovableEntity(int x, int y, int width, int height, int movingSpeed) {
        super(x, y, width, height);

        this.movingSpeed = movingSpeed;
        this.movingDirection = Direction.NONE;
    }

    public void moveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
        movingDirection = Direction.LEFT;
    }

    public void moveRight(boolean moveRight) {
        this.moveRight = moveRight;
        movingDirection = Direction.RIGHT;
    }

    public void moveUp(boolean moveUp) {
        this.moveUp = moveUp;
        movingDirection = Direction.UP;
    }

    public void moveDown(boolean moveDown) {
        this.moveDown = moveDown;
        movingDirection = Direction.DOWN;
    }

    public void move() {

        if (moveLeft) {
            translateX(-movingSpeed);
        }

        if (moveUp) {
            translateY(-movingSpeed);
        }

        if (moveRight) {
            translateX(movingSpeed);
        }

        if (moveDown) {
            translateY(movingSpeed);
        }
    }

    // TODO:
    private void updateMovingDirection() {



        boolean onlyLeft = moveLeft && !moveRight;
        boolean onlyRight = !moveLeft && moveRight;
        boolean onlyUp = moveUp && !moveDown;
        boolean onlyDown = !moveUp && moveDown;


    }

    public void update() {
        move();
        //TODO: updateMovingDirection();

        /*
        if (!moveLeft && !moveUp && !moveRight && !moveDown) {
            movingDirection = Direction.NONE;
        }


         */
    }

    public Direction getMovingDirection() {
        return movingDirection;
    }

    public int getMovingSpeed() {
        return movingSpeed;
    }

    public String getClassNameForToString() {
        return "MovableEntity";
    }
}
