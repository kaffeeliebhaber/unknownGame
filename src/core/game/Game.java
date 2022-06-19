package core.game;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface Game {

    void init();

    void keyPressed(final int keyCode);

    void keyReleased(final int keyCode);

    void mouseDragged(MouseEvent e);

    void mouseMoved(MouseEvent e);

    void update();

    void draw(Graphics2D g2D);

}
