package Chess.RestartOrExitComponent;
//CreateTime: 2022-03-18 11:02 p.m.

import Chess.ChessBoard.LayoutAdapter;

import java.awt.*;

public class RestartExitLayout extends LayoutAdapter {

    public RestartExitLayout() {

    }

    @Override
    public void layoutContainer(Container parent) {
        Component[] components = parent.getComponents();
        components[0].setBounds(parent.getHeight()/4,parent.getHeight()/4, parent.getWidth()/4,parent.getHeight()/2);
        components[1].setBounds(parent.getHeight()/4 + parent.getHeight(),parent.getHeight()/4, parent.getWidth()/4,parent.getHeight()/2);
    }
}
