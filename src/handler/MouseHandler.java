package handler;

import core.game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener {

    private final Game game;

    public MouseHandler(final Game game) {
        this.game = game;
    }

    @Override public void mouseDragged(MouseEvent e) { game.mouseDragged(e); }

    @Override public void mouseMoved(MouseEvent e) { game.mouseMoved(e); }
}
