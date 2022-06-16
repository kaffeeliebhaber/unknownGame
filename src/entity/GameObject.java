package entity;

import math.Vector;

public abstract class GameObject {

    protected int x, y;
    protected int width, height;
    protected boolean active;

    public GameObject(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector getCenterPosition() {
        return new Vector((int) (x + width * 0.5f), (int) (y + height * 0.5f));
    }

    public boolean isActive() {
        return active;
    }

    protected void active() {
        active = true;
    }

    protected void deactive() {
        active = false;
    }

    @Override
    public String toString() {
        return "GameObject [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }

}