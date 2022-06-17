package entity.renderer;

import core.game.Camera;

import java.awt.*;

public interface EntityRenderer {

    void draw(Graphics2D g2D, Camera camera);

}
