package gameState;

import animation.Direction;
import core.game.Camera;
import core.game.Game;
import gameObject.CollisionArea;
import gameObject.entity.Dummy;
import gameObject.entity.Player;
import gameObject.renderer.ImageEntityRenderer;
import gfx.BufferedImageHelper;
import gfx.ImageLoader;
import main.GamePanel;
import tile.TileManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameStatePlayState extends GameState {

    private final Game game;
    private Player player;
    private TileManager tileManager;
    private Camera camera;

    // TEST OBJECTS
    private Dummy dummy;

    public GameStatePlayState(GameStateManager gameStateManager, GameStateID gameStateID, final Game game) {
        super(gameStateManager, gameStateID);

        this.game = game;

    }

    public void keyPressed(final int keyCode) {

        if (keyCode == KeyEvent.VK_ENTER) {
            gameStateManager.changeTo(GameStateID.PAUSE);
        }

        if (keyCode == KeyEvent.VK_D) {
            player.moveRight(true);
        }

        // MOVE: LEFT
        if (keyCode == KeyEvent.VK_A) {
            player.moveLeft(true);
        }

        // MOVE: UP
        if (keyCode == KeyEvent.VK_W) {
            player.moveUp(true);
        }

        // MOVE: DOWN
        if (keyCode == KeyEvent.VK_S) {
            player.moveDown(true);
        }

        // TEST CODE: CHECK FOR <focusOn> functionality. It works very well.
        if (keyCode == KeyEvent.VK_F5) {
            camera.focusOn(player);
        }

        if (keyCode == KeyEvent.VK_F6) {
            camera.focusOn(dummy);
        }

    }

    public void keyReleased(final int keyCode) {
        if (keyCode == KeyEvent.VK_D) {

            // CHECK FOR PLAYER COLLISION
            // KANN ES EINE METHODE (CHECK AUF COLLISION GEBEN), IN DER ICH DIREKT IN ALLE RICHTUNGEN AUF COLLISION PRÜFE?
            // ES WÜRDE DIE ABFRAGEN VERMINDERN.


            // POSITIV BEMERKUNG: DA WIR DIE BEWEGUNG NUR IMMER DANN PRÜFEN, WENN WIR EINE TASTE GEDRÜCKT WURDE,
            // BRAUCHEN WIR DIE PLAYER - POSITION NICHT JEDES MAL NEU PÜRFEN!!

            player.moveRight(false);
        }

        if (keyCode == KeyEvent.VK_A) {
            player.moveLeft(false);
        }

        if (keyCode == KeyEvent.VK_W) {
            player.moveUp(false);
        }

        if (keyCode == KeyEvent.VK_S) {
            player.moveDown(false);
        }
    }

    public void init() {

        // TODO: Dient aktuell lediglich zu Testzwecken.
        final BufferedImage playerImageScaled = BufferedImageHelper.scale(ImageLoader.loadImage("player/player.png"), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

        // CREATE PLAYER
        player = new Player(game);
        player.setEntityRenderer(new ImageEntityRenderer(player, playerImageScaled));
        player.setCollisionArea(new CollisionArea(100, 100, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));

        // CREATE DUMMY OBJECT
        dummy = new Dummy(10, 10, 38, 38, 0);
        //dummy.setCollisionArea(new CollisionArea(10, 10, 38, 38));

        // CREATE TILEMANAGER
        tileManager = new TileManager(GamePanel.ORIG_TILE_SIZE);
        tileManager.load();

        // CREATE CAMERA - FOSUC CAMERA ON PLAYER
        camera = new Camera(
                0,
                0,
                GamePanel.SCREEN_WIDTH,
                GamePanel.SCREEN_HEIGHT,
                new Dimension(tileManager.getWidthInTiles() * GamePanel.TILE_SIZE, tileManager.getHeightInTiles() * GamePanel.TILE_SIZE));

        camera.focusOn(player);
    }

    public void update() {

        //preUpdateCheckForMapCollision();

        player.update();


        // TODO: Wir müssen die Überprüfung auf Basis der CollisionArea durchführen. NICHT auf der Dimension des Spielers!
        // CHECK LEFT SIDE.
        if (player.getX() < 0) {
            player.translateX(-player.getX());
        }

        // CHECK TOP SIDE.
        if (player.getY() < 0) {
            player.translateY(-player.getY());
        }

        // CHECK RIGHT SIDE.
        if ((player.getX() + player.getWidth()) > tileManager.getWidth()) {
            player.translateX(tileManager.getWidth() - player.getX() - player.getWidth());
        }

        // CHECK BOTTOM SIDE.
        if ((player.getY() + player.getHeight()) > tileManager.getHeight()) {
            player.translateY(tileManager.getHeight() - player.getY() - player.getHeight());
        }
    }

    // TODO: Test Methode
    private void preUpdateCheckForMapCollision() {
        switch (player.getMovingDirection()) {
            case UP:
                if (!tileManager.intersects(player, Direction.UP)) player.update();
                break;
            case RIGHT:
                if (!tileManager.intersects(player, Direction.RIGHT)) player.update();

                break;
            case DOWN:
                if (!tileManager.intersects(player, Direction.DOWN)) player.update();
                break;
            case LEFT:
                if (!tileManager.intersects(player, Direction.LEFT)) player.update();
                break;

        }
    }

    public void draw(Graphics2D g2D) {

        tileManager.draw(g2D, camera);

        dummy.draw(g2D, camera);

        player.draw(g2D, camera);

    }

}
