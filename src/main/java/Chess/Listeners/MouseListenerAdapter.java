package Chess.Listeners;
//CreateTime: 2022-03-17 3:29 p.m.

import Chess.Parameters.STATICPARAMETERS;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class MouseListenerAdapter implements MouseListener {


    @Override
    public abstract void mouseClicked(MouseEvent e);

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        STATICPARAMETERS.UnderMouseCursor = e.getComponent();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        STATICPARAMETERS.UnderMouseCursor = null;
    }
}
