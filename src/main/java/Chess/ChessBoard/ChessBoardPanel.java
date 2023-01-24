package Chess.ChessBoard;
//CreateTime: 2022-03-14 10:45 p.m.

import Chess.Parameters.CONTANTPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(CONTANTPARAMETERS.LightBlue);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
    }
}
