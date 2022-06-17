package core.game;

import gameObject.GameObject;
import gameObject.GameObjectPositionChangeListener;
import math.Vector;

import java.awt.*;

public class Camera extends GameObject implements GameObjectPositionChangeListener {

    private final Dimension gameDimension;
    private GameObject gameObject;

    public Camera(int startX, int startY, int cameraWidth, int cameraHeight, Dimension gameDimension) {
        super(startX, startY, cameraWidth, cameraHeight);

        this.gameDimension = gameDimension;
    }

    // Ausrichtung der x-Ausprägung nach links und rechts.
    // Befindet sich die Kamera von der Ausprägung am linken oder rechten Rand des Spiels, werden die X-Ausprägungen auf 0 oder (Spielfeldbreit - Sichtbreit) gesetzt.
    private int alignX(int x) {

        if (x < 0) {
            x = 0;
        } else if (x > gameDimension.width - width) {
            x = gameDimension.width - width;
        }

        return x;
    }

    // Ausrichtung der y-Ausprägung nach oben und unten.
    // Befindet sich die Kamera von der Ausprägung am oberen oder unteren Rand des Spiels, werden die y-Ausprägungen auf 0 oder (Spielfeldhöhe - Sichthöhe) gesetzt.
    private int alignY(int y) {

        if (y < 0) {
            y = 0;
        } else if (y > gameDimension.height - height) {
            y = gameDimension.height - height;
        }

        return y;
    }

    /*
     * Das übergebene Spielobjekt wird als Ankerpunkt gesetzt.
     * Die Kamera entfernt sich als Listener dem noch hinterlegten Spielobjekt.
     * Anschließend wird das neue Spielobjekt gesetzt.
     * Die Kamera registriert sich als Listener dem neuen Spielobjekt.
     */
    public void focusOn(final GameObject gameObject) {

        if (gameObject != null && this.gameObject != gameObject) {

            removePositionChangeListener();
            setGameObject(gameObject);
            addPositionChangeListener(gameObject);
            centerOnGameObject(gameObject);
        }
    }

    // Entfernt die Kamera als GameObjektListener aus dem aktuellen Spielobjekt (Ankerobjekt).
    private void removePositionChangeListener() {
        if (gameObject != null) {
            gameObject.removePositionChangeListener(this);
        }
    }

    private void addPositionChangeListener(final GameObject gameObject) {
        if (gameObject != null) {
            gameObject.addPositionChangeListener(this);
        }
    }

    // Setzt das übergebene Spielobjekt als aktuelles Ankerobjekt.
    private void setGameObject(final GameObject gameObject) {
        this.gameObject = gameObject;
    }

    // Die Kamera wird auf das übergebene Spielobjekt gesetzt.
    private void centerOnGameObject(final GameObject gameObject) {

        // Mittelpunkt des <GameObjects>.
        final Vector gameObjectCenter = gameObject.getCenterPosition();

        // Camera Position (TOP-LEFT)
        int cX = (int) (gameObjectCenter.x - width * 0.5f);
        int cY = (int) (gameObjectCenter.y - height * 0.5f);

        // Ausrichten der x-Position | Prüfen, ob die Grenzen erreicht wurden.
        setX(alignX(cX));

        // Ausrichten der y-Position | Prüfen, ob die Grenzen erreicht wurden.
        setY(alignY(cY));
    }


    @Override
    public void positionChanged(GameObject gameObject, int deltaX, int deltaY) {
        centerOnGameObject(gameObject);
    }
}
