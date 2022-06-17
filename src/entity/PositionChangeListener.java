package entity;

public interface PositionChangeListener {

    void positionChanged(GameObject gameObject, int oldX, int oldY, int newX, int newY);

}
