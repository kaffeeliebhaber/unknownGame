package tile;

import core.game.Camera;

import java.awt.Graphics2D;
import java.awt.Color;

public class TileHighlighter {

    private int col;
    private int row;

    private boolean active;

    private final int tileSize;
    private int indent;

    public TileHighlighter(int tileSize, int indent) {
        this.tileSize = tileSize;
        this.indent = indent;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void highlight(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void toggleTileHighlighter() {
        active = !active;
    }

    public void draw(Graphics2D g2D, Camera camera) {

        if (isActive()) {
            g2D.setColor(Color.RED);
            g2D.drawRect(
                    col * tileSize - camera.getX() + indent,
                    row * tileSize - camera.getY() + indent,
                    tileSize - 2 * indent,
                    tileSize - 2 * indent);
        }

    }
}
