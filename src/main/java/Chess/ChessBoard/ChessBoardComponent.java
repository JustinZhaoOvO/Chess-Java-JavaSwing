package Chess.ChessBoard;
//CreateTime: 2022-03-15 10:47 p.m.

import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class ChessBoardComponent extends JPanel {
    private Color backGroundColor;
    private final int xValue;
    private final int yValue;
    private final int[] Compcoordinate;
    public ChessBoardComponent(int x , int y) {
        this.setOpaque(false);
        this.xValue = x;
        this.yValue = y;
        this.Compcoordinate = new int[]{x,y};
        if (x % 2 == y % 2){
            backGroundColor = CONTANTPARAMETERS.YelloWhite;
        }else{
            backGroundColor = CONTANTPARAMETERS.DarkGreen;
        }
    }

    public int getXValue() {
        return xValue;
    }

    public int getYValue() {
        return yValue;
    }

    public int[] getCompcoordinate() {
        return Compcoordinate;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backGroundColor);
        g.fillRect(0,0,this.getWidth(), this.getHeight());
        if (STATICPARAMETERS.UnderMouseCursor == this){
            g.setColor(CONTANTPARAMETERS.Cyan);
            for (int i = 0; i < this.getWidth() / 6; i+=2) {
                g.drawRect(i,i,this.getWidth() - i*2,getHeight() - i*2);
            }
        }
        if(STATICPARAMETERS.clicked != null && STATICPARAMETERS.IsContains(STATICPARAMETERS.clickedPieceCanMove,this.Compcoordinate)){
            g.setColor(CONTANTPARAMETERS.translucentRed);
            g.fillOval(this.getWidth()/3,this.getHeight()/3,this.getWidth()/3,this.getHeight()/3);
        }
    }

    @Override
    public String toString(){
        return String.format("%d  %d",xValue,yValue);
    }
}
