package tile;

import core.game.Camera;
import gfx.BufferedImageHelper;
import gfx.ImageLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    private Tile[] tiles;
    private short[][] world;

    private final int imageTileSize;

    public TileManager(final int imageTileSize) {

        this.imageTileSize = imageTileSize;

        tiles = new Tile[10];

        world = new short[50][50];

    }

    public void load() {
        loadTiles("tile/tileSetRaw.png");
        loadMap("/maps/map01.txt");
    }

    public void loadTiles(final String path) {

        final BufferedImage spritesheet = ImageLoader.loadImage(path);

        tiles[0] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage(            0, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), false);    // GRAS
        tiles[1] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage(        imageTileSize, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), true);    // WATER
        tiles[2] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage( 2 * imageTileSize, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), false);    // EARTH
    }

    public void loadMap(final String path) {


        try {

            final InputStream input = getClass().getResourceAsStream(path);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line = reader.readLine();

            int row = 0;

            while (line != null) {

                String[] lineSplitted = line.split(" ");

                for (int col = 0; col < lineSplitted.length; col++) {
                    world[row][col] = Short.parseShort(lineSplitted[col]);
                }

                row++;
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2D, Camera camera) {

        final int offset = 1;

        int minX = (camera.getX() / GamePanel.TILE_SIZE) - offset;
        int minY = (camera.getY() / GamePanel.TILE_SIZE) - offset;

        int maxX = ((camera.getX() + camera.getWidth()) / GamePanel.TILE_SIZE) + offset;
        int maxY = ((camera.getY() + camera.getHeight()) / GamePanel.TILE_SIZE) + offset;

        if (minX < 0) minX = 0;
        if (maxX > 50) maxX = 50;
        if (minY < 0) minY = 0;
        if (maxY > 50) maxY = 0;


        for (int row = minY; row < maxY; row++) {
            for (int col = minX; col < maxX; col++) {
                g2D.drawImage(
                        tiles[
                                world[row][col]
                                ].getImage(),
                        col * GamePanel.TILE_SIZE - camera.getX(),
                        row * GamePanel.TILE_SIZE - camera.getY(),
                        null);
            }
        }

    }
}