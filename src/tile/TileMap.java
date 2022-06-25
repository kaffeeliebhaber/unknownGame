package tile;

import animation.Direction;
import core.game.Camera;
import gameObject.entity.MovableEntity;
import gfx.BufferedImageHelper;
import gfx.ImageLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {

    private TileHighlighter tileHighlighter;
    private Tile[] tiles;
    private short[][] world;

    private final int imageTileSize;

    public TileMap(final int imageTileSize) {

        this.imageTileSize = imageTileSize;

        tiles = new Tile[10];

        world = new short[50][50];

        tileHighlighter = new TileHighlighter(GamePanel.TILE_SIZE, 4);
        tileHighlighter.activate();
    }

    public void highlight(int x, int y) {
        tileHighlighter.highlight(getColFromX(x), getRowFromY(y));
    }

    public boolean intersects(final MovableEntity movableEntity, final Direction checkDirection) {

        boolean collision = false;

        short tileXLeft, tileXRight;
        short tileYTop, tileYBottom;

        switch (checkDirection) {
            case UP:

                tileXLeft = (short) (movableEntity.getCollisionArea().getX() / GamePanel.TILE_SIZE);
                tileXRight = (short) (movableEntity.getCollisionArea().getXRight() / GamePanel.TILE_SIZE);
                tileYTop = (short) (movableEntity.getCollisionAreaYTopAfterMoving() / GamePanel.TILE_SIZE);

                collision = getTile(tileXLeft, tileYTop).isSolid() || getTile(tileXRight, tileYTop).isSolid();

                break;
            case RIGHT:

                tileXRight = (short) (movableEntity.getCollisionAreaXRightAfterMoving() / GamePanel.TILE_SIZE);
                tileYTop = (short) (movableEntity.getCollisionArea().getY() / GamePanel.TILE_SIZE);
                tileYBottom = (short) (movableEntity.getCollisionArea().getYBottom() / GamePanel.TILE_SIZE);

                collision = getTile(tileXRight, tileYTop).isSolid() || getTile(tileXRight, tileYBottom).isSolid();

                break;
            case DOWN:

                tileXLeft = (short) (movableEntity.getCollisionArea().getX() / GamePanel.TILE_SIZE);
                tileXRight = (short) (movableEntity.getCollisionArea().getXRight() / GamePanel.TILE_SIZE);
                tileYBottom = (short) (movableEntity.getCollisionAreaYBottomAfterMoving() / GamePanel.TILE_SIZE);

                collision = getTile(tileXLeft, tileYBottom).isSolid() || getTile(tileXRight, tileYBottom).isSolid();

                break;
            case LEFT:

                tileXLeft = (short) (movableEntity.getCollisionAreaXLeftAfterMoving() / GamePanel.TILE_SIZE);
                tileYTop = (short) (movableEntity.getCollisionArea().getY() / GamePanel.TILE_SIZE);
                tileYBottom = (short) (movableEntity.getCollisionArea().getYBottom() / GamePanel.TILE_SIZE);

                collision = getTile(tileXLeft, tileYTop).isSolid() || getTile(tileXLeft, tileYBottom).isSolid();

                break;
        }

        return collision;
    }

    public void load() {
        loadTiles("tile/tileSetRaw.png");
        loadMap("/maps/map01.txt");
    }

    private void loadTiles(final String path) {

        final BufferedImage spritesheet = ImageLoader.loadImage(path);

        tiles[0] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage(            0, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), false, 0);    // GRAS
        tiles[1] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage(        imageTileSize, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), true, 1);    // WATER
        tiles[2] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage( 2 * imageTileSize, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), false, 2);    // EARTH
        tiles[3] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage( 3 * imageTileSize, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), true, 3);    // TREE
    }

    private void loadMap(final String path) {

        try {

            final InputStream input = getClass().getResourceAsStream(path);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line = reader.readLine();

            int row = 0;

            while (line != null) {

                String[] lineSplitted = line.split(" ");

                for (int col = 0; col < lineSplitted.length; col++) {
                    world[col][row] = Short.parseShort(lineSplitted[col]);
                }

                row++;
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int getColFromX(int x) {
        return x / GamePanel.TILE_SIZE;
    }

    private int getRowFromY(int y) {
        return y / GamePanel.TILE_SIZE;
    }

    public void draw(Graphics2D g2D, Camera camera) {

        final int offset = 1;

        int minX = (camera.getX() / GamePanel.TILE_SIZE) - offset;
        int minY = (camera.getY() / GamePanel.TILE_SIZE) - offset;

        int maxX = ((camera.getX() + camera.getWidth()) / GamePanel.TILE_SIZE) + offset;
        int maxY = ((camera.getY() + camera.getHeight()) / GamePanel.TILE_SIZE) + offset;

        if (minX < 0) minX = 0;
        if (maxX > getWidthInTiles()) maxX = getWidthInTiles();
        if (minY < 0) minY = 0;
        if (maxY > getHeightInTiles()) maxY = getHeightInTiles();

        for (int col = minX; col < maxX; col++) {
            for (int row = minY; row < maxY; row++) {
                g2D.drawImage(
                        tiles[world[col][row]].getImage(),
                        col * GamePanel.TILE_SIZE - camera.getX(),
                        row * GamePanel.TILE_SIZE - camera.getY(),
                        null);

                if (GamePanel.debug) {
                    g2D.setColor(Color.MAGENTA);
                    g2D.drawRect(
                            col * GamePanel.TILE_SIZE - camera.getX(),
                            row * GamePanel.TILE_SIZE - camera.getY(),
                            GamePanel.TILE_SIZE,
                            GamePanel.TILE_SIZE
                    );
                }
            }
        }

        tileHightlighter.draw(g2D, camera);
    }

    public int getWidthInTiles() {
        return world[0].length;
    }

    public int getHeightInTiles() {
        return world.length;
    }

    public int getWidth() {
        return getWidthInTiles() * GamePanel.TILE_SIZE;
    }

    public int getHeight() {
        return getHeightInTiles() * GamePanel.TILE_SIZE;
    }

    private Tile getTile(final int col, final int row) {
        return tiles[getTileID(col, row)];
    }

    private short getTileID(final int col, final int row) {
        return world[col][row];
    }
}