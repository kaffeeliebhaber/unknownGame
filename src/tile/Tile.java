package tile;

import java.awt.image.BufferedImage;

public class Tile {

    private final BufferedImage image;
    private boolean solid;

    public Tile(final BufferedImage image, boolean solid) {

        this.image = image;
        this.solid = solid;

    }

    public boolean isSolid() {
        return solid;
    }

    public BufferedImage getImage() {
        return image;
    }

}
