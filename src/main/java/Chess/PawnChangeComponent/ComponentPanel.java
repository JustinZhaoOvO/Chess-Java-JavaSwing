package Chess.PawnChangeComponent;
//CreateTime: 2022-03-17 11:22 a.m.

import Chess.Listeners.ChangeComponentMouseListener;
import Chess.Parameters.CONTANTPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class ComponentPanel extends JPanel {
    private Component[] components;

    public ComponentPanel(int color) {
        components = new Component[4];
        components[0] = new ChangeToBishop(color);
        components[1] = new ChangeToRook(color);
        components[2] = new ChangeToKnight(color);
        components[3]  = new ChangeToQueen(color);
        for (Component component : components) {
            component.addMouseListener(new ChangeComponentMouseListener());
            this.add(component);
        } this.setLayout(new ComponentLayout(components));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(CONTANTPARAMETERS.Red);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
    }
}
