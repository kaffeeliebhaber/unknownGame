package entity;

import core.game.Game;
import core.game.GameBase;
import gfx.BufferedImageHelper;
import main.GamePanel;
import handler.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    protected int speed;

    // IMAGE
    private BufferedImage image;

    // GAME OBJECTS
    private final Game game;


    public Player(final Game game) {

        this.game = game;

        this.setDefaultValues();
        this.getImage();
    }

    private void setDefaultValues() {

        x = 100;
        y = 100;

        speed = 4;
    }

    private void getImage() {

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
            image = BufferedImageHelper.scale(image, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*

     TODO / ÜBERLEGUNG:

        > Man greift vom 'Player' nicht direkt den 'KeyListener' zu, sondern setzt von ausse - aufrufendes Objekt -
        Setter - Methode des 'PLayers' - setLeft, setRight, usw.


     */

    public void update() {

        if (game.getKeyHandler().leftPressed) {
            x -= speed;
        }

        if (game.getKeyHandler().rightPressed) {
            x += speed;
        }

        if (game.getKeyHandler().upPressed) {
            y -= speed;
        }

        if (game.getKeyHandler().downPressed) {
            y += speed;
        }

    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.WHITE);
        g2d.drawImage(image, x, y, null);
    }
}
