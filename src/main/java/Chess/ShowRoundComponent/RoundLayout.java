package Chess.ShowRoundComponent;
//CreateTime: 2022-04-02 9:18 p.m.

import Chess.ChessBoard.LayoutAdapter;
import Chess.Parameters.STATICPARAMETERS;

import java.awt.*;

public class RoundLayout extends LayoutAdapter {

    @Override
    public void layoutContainer(Container parent) {
        STATICPARAMETERS.uptimer.setBounds(0,0,parent.getWidth(),parent.getHeight()/5);
        STATICPARAMETERS.uptimer.display.setFont(new Font("宋体",Font.BOLD,parent.getWidth()/2));
        STATICPARAMETERS.downtimer.setBounds(0,(int) (parent.getHeight()/5 * 3.9),parent.getWidth(),parent.getHeight()/5);
        STATICPARAMETERS.downtimer.display.setFont(new Font("宋体",Font.BOLD,parent.getWidth()/2));
    }
}
