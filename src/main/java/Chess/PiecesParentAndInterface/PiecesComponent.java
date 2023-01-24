package Chess.PiecesParentAndInterface;
//CreateTime: 2022-03-15 11:01 p.m.

import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class PiecesComponent extends JPanel {
    private final int ComID = -1;
    private Pieces myPiece;
    private final int componentColor = 2;
    private int[] comcoordinate;
    private Color currentColor;
    private Image pieceImage;

    public Image getPieceImage() {
        return pieceImage;
    }


    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public int[] getComcoordinate() {
        return comcoordinate;
    }

    public void setComcoordinate(int[] comcoordinate) {
        this.comcoordinate = comcoordinate;
    }
    public int getComID() {
        return ComID;
    }

    public Pieces getMyPiece(){
        return myPiece;
    }

    public int getComponentColor() {
        return componentColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        changeBoundsAndColor();
        g.setColor(this.getCurrentColor());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(this.getPieceImage(), 0,0,this.getWidth(),getHeight(),null);
        if (STATICPARAMETERS.UnderMouseCursor == this){
            g.setColor(CONTANTPARAMETERS.Cyan);
            for (int i = 0; i < this.getWidth() / 6; i+=2) {
                g.drawRect(i,i,this.getWidth() - i*2,getHeight() - i*2);
            }
        }
    }

    public void changeBoundsAndColor(){
        this.setBounds(getMyPiece().coordinate[0] * this.getWidth(),getMyPiece().coordinate[1] * this.getWidth(),this.getWidth(),getHeight());
        if (STATICPARAMETERS.clicked != null){
            if(STATICPARAMETERS.clicked == this){
                this.setCurrentColor(CONTANTPARAMETERS.LightYellow);
            }else{
                if(STATICPARAMETERS.IsContains(STATICPARAMETERS.clickedPieceCanMove,getMyPiece().coordinate)){

                    this.setCurrentColor(CONTANTPARAMETERS.Red);
                }else {
                    if (this.getMyPiece().coordinate[0] % 2 != this.getMyPiece().coordinate[1] % 2) {
                        this.setCurrentColor(CONTANTPARAMETERS.DarkGreen);
                    } else {
                        this.setCurrentColor(CONTANTPARAMETERS.YelloWhite);
                    }
                }
            }
        }else {
            if (this.getMyPiece().coordinate[0] % 2 != this.getMyPiece().coordinate[1] % 2) {
                this.setCurrentColor(CONTANTPARAMETERS.DarkGreen);
            } else {
                this.setCurrentColor(CONTANTPARAMETERS.YelloWhite);
            }
        }
    }
}
