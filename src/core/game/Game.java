package core.game;

import handler.KeyHandler;

import java.awt.*;

public interface Game {

    void init();

    void setKeyHandler(final KeyHandler keyHandler);

    KeyHandler getKeyHandler();

    void keyPressed(final int keyCode);

    void keyReleased(final int keyCode);

    void update();

    void draw(Graphics2D g2D);

}
