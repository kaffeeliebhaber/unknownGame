package core.game;

import java.awt.*;

public interface Game {

    void init();

    void keyPressed(final int keyCode);

    void keyReleased(final int keyCode);

    void update();

    void draw(Graphics2D g2D);

}
