package tile;

import animation.Direction;
import core.game.Camera;
import core.game.Game;
import gameObject.CollisionArea;
import gameObject.entity.MovableEntity;
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

    // TODO: IMPLEMENT METHOD
    public boolean intersects(final MovableEntity movableEntity, final Direction direction) {

        boolean collision = false;

        switch (direction) {
            case UP: break;
            case RIGHT:

                final CollisionArea movableEntityCollisionArea = movableEntity.getCollisionArea();

                //System.out.println("(TileManager.intersects) CollisionArea= " + movableEntityCollisionArea);

                short posXRight = (short) ((movableEntity.getCollisionArea().getXRight() + movableEntity.getMovingSpeed()) / GamePanel.TILE_SIZE);
                short posYTop = (short) ((movableEntity.getCollisionArea().getY())/ GamePanel.TILE_SIZE);
                short posYBottom = (short) ((movableEntity.getCollisionArea().getYBottom()) / GamePanel.TILE_SIZE);
                short top = world[posYTop][posXRight];
                short bottom = world[posYBottom][posXRight];

                if (tiles[top].isSolid() || tiles[bottom].isSolid()) {
                    collision = true;

                    //System.out.println("(TileManager.intersects.collisionDetected)");
                }

                break;
            case DOWN: break;
            case LEFT: break;
        }

        return collision;
    }

    public boolean intersects(final CollisionArea collisionArea, final Direction direction) {

        boolean collision = false;
/*
        switch (direction) {
            case LEFT:

                break;
            case UP:

                break;
            case RIGHT:

                short posX = (short) (collisionArea.getXRight() / GamePanel.TILE_SIZE);
                short posYTop = (short) (collisionArea.getY() / GamePanel.TILE_SIZE);
                short posYBottom = (short) (collisionArea.getYBottom() / GamePanel.TILE_SIZE);

                short indexTop = world[posYTop][posX];
                short indexBottom = world[posYBottom][posX];

                Tile top = tiles[indexTop];
                Tile bottom = tiles[indexBottom];


                System.out.println("POSX= " + posX);
                //System.out.println("PosX= " + posX + ", PosYTop= " + posYTop + ", PosYBottom= " + posYBottom + ", IndexTop= " + indexTop + ", IndexBottom= " + indexBottom + ", TOP: " + top + ", BOTTOM= " + bottom);

                if (top.isSolid() || bottom.isSolid()) {
                    collision = true;
                }

                break;
            case DOWN:

                break;

        }

        */
        /*
        WIE LÄUFT DIE ÜBERPRÜFUNG AB?

        [] HOLE ALLE BETROFFENEN TILES AUS DEN INFORMATIONEN DER <COLLISIONAREA>.
        [] ERSTELLE EINE <COLLISIONAREA> FÜR JEDES BETROFFENE TILE An.
        [] PRÜFE JEDE NEU ERSTELLTE <COLLISIONAREA> MIT DER ÜBERGEBENEN <COLLISIONAREA>.
        [] DAS RESULTAT DER METHODE IST <TRUE>, WENN NUR EINE DER BETROFFENEN TILES EINE COLLISION MIT DER ÜBERGEBENEN <COLLISIONAREA> HAT.
        [] ANSONSTEN <FALSE>.

        */


        return collision;
    }

    public void load() {
        loadTiles("tile/tileSetRaw.png");
        loadMap("/maps/map01.txt");
    }

    public void loadTiles(final String path) {

        final BufferedImage spritesheet = ImageLoader.loadImage(path);

        tiles[0] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage(            0, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), false, 0);    // GRAS
        tiles[1] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage(        imageTileSize, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), true, 1);    // WATER
        tiles[2] = new Tile(BufferedImageHelper.scale(spritesheet.getSubimage( 2 * imageTileSize, 0, imageTileSize, imageTileSize), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE), false, 2);    // EARTH
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
        if (maxX > getWidthInTiles()) maxX = getWidthInTiles();
        if (minY < 0) minY = 0;
        if (maxY > getHeightInTiles()) maxY = getHeightInTiles();

        for (int row = minY; row < maxY; row++) {
            for (int col = minX; col < maxX; col++) {
                g2D.drawImage(
                        tiles[world[row][col]].getImage(),
                        col * GamePanel.TILE_SIZE - camera.getX(),
                        row * GamePanel.TILE_SIZE - camera.getY(),
                        null);
            }
        }
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
}