package Chess.ChessTimer;
//CreateTime: 2022-04-02 8:51 p.m.

import Chess.ChessBoard.LayoutAdapter;

import javax.swing.*;
import java.awt.*;

public class ChessTimerLayout extends LayoutAdapter {

    private final JLabel jLabel;

    public ChessTimerLayout(JLabel jLabel){
        this.jLabel = jLabel;
    }

    @Override
    public void layoutContainer(Container parent) {
        jLabel.setBounds(parent.getWidth() / 5,0,parent.getWidth(),parent.getHeight());
    }
}
