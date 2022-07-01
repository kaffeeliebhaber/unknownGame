package handler;

import core.game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener, MouseListener {

    private final Game game;

    public MouseHandler(final Game game) {
        this.game = game;
    }

    // MOUSE MOTION LISTENER.
    @Override public void mouseDragged(MouseEvent e) { game.mouseDragged(e); }

    @Override public void mouseMoved(MouseEvent e) { game.mouseMoved(e); }

    // MOUSE LISTENER.

    @Override public void mouseClicked(MouseEvent e) { }

    @Override public void mousePressed(MouseEvent e) { }

    @Override public void mouseReleased(MouseEvent e) { }

    @Override public void mouseEntered(MouseEvent e) { game.mouseEntered(e); }

    @Override public void mouseExited(MouseEvent e) { game.mouseExited(e); }
}
