package gameObject.entity;

import gameObject.CollisionArea;

public class MovableEntity extends Entity {


    protected int movingSpeed;
    protected boolean moveLeft, moveUp, moveRight, moveDown;

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

    public void update() {
        move();
    }


}
