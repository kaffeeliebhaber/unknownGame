package handler;

import core.game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private final Game game;

    public KeyHandler(final Game game) {
        this.game = game;
    }

    @Override public void keyTyped(KeyEvent e) {}

    @Override public void keyPressed(KeyEvent e) { game.keyPressed(e.getKeyCode()); };

    @Override public void keyReleased(KeyEvent e) { game.keyReleased(e.getKeyCode()); }
}
