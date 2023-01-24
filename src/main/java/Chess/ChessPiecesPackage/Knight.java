package Chess.ChessPiecesPackage;
//CreateTime: 2022-03-11 5:13 p.m.

import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PiecesParentAndInterface.Pieces;
import Chess.PiecesParentAndInterface.PiecesComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Knight extends Pieces{
    public Component component;

    public Knight(int color, int ID, int[] coordinate) {
        super(color, ID, coordinate);
        component = new KnightComponent(this);
    }
    @Override
    public Component getComponent() {
        return component;
    }
    @Override
    public void setComponent(Component component) {
        this.component = component;
    }


    @Override
    public ArrayList<int[]> getAllCanMoveCoordinates() {
        int x = this.coordinate[0];
        int y = this.coordinate[1];
        ArrayList<int[]> ints = new ArrayList<>(8);
        int[][] mayCanMove = new int[][]{{x-2,y + 1},{x-2,y - 1},{x+2,y+1},{x + 2,y-1},{x + 1,y+2},{x+1,y-2},{x-1,y-2},{x-1,y+2}};
        for (int[] ints1 : mayCanMove) {
            if (ints1[0] >= 0 &&  7 >= ints1[0] && ints1[1] >= 0 &&  7 >= ints1[1] && (STATICPARAMETERS.ChessPiece2DList.board[ints1[1]][ints1[0]] == null || STATICPARAMETERS.ChessPiece2DList.board[ints1[1]][ints1[0]].color != this.color)){
                ints.add(ints1);
            }
        }
        return ints;
    }

    @Override
    public ArrayList<int[]> getAllCanMoveCoordinatesCheck() {
        int x = this.coordinate[0];
        int y = this.coordinate[1];
        ArrayList<int[]> ints = new ArrayList<>(8);
        int[][] mayCanMove = new int[][]{{x-2,y + 1},{x-2,y - 1},{x+2,y+1},{x + 2,y-1},{x + 1,y+2},{x+1,y-2},{x-1,y-2},{x-1,y+2}};
        for (int[] ints1 : mayCanMove) {
            if (ints1[0] >= 0 &&  7 >= ints1[0] && ints1[1] >= 0 &&  7 >= ints1[1] && (STATICPARAMETERS.ChessPiece2DList.board[ints1[1]][ints1[0]] == null || STATICPARAMETERS.ChessPiece2DList.board[ints1[1]][ints1[0]].color != this.color)){
                if (STATICPARAMETERS.CheckCanMove(this.coordinate,ints1,this)) {
                    ints.add(ints1);
                }
            }
        }
        return ints;
    }

    @Override
    public void move(int[] coordinate) {
        if (this.color == 0){
            STATICPARAMETERS.whitePawnMove1Step = null;
            STATICPARAMETERS.whitePawnMove2Step = null;
        }else{
            STATICPARAMETERS.blackPawnMove1Step = null;
            STATICPARAMETERS.blackPawnMove2Step = null;
        }
        this.coordinate = coordinate;
    }

    @Override
    public String toString(){
        return "Knight" + ID;
    }

    public class KnightComponent extends PiecesComponent {
        public final int componentColor = color;
        public final int ComID = ID;
        private Pieces myPiece;
        private Image pieceImage;
        private int imageX;
        private int imageY;
        private int[] comcoordinate = coordinate;
        private Color currentColor;

        @Override
        public Color getCurrentColor() {
            return currentColor;
        }

        @Override
        public void setCurrentColor(Color currentColor) {
            this.currentColor = currentColor;
        }

        public int[] getComcoordinate() {
            return comcoordinate;
        }

        public void setComcoordinate(int[] comcoordinate) {
            this.comcoordinate = comcoordinate;
        }
        @Override
        public int getComID() {
            return ComID;
        }

        public int getComponentColor() {
            return componentColor;
        }

        @Override
        public Pieces getMyPiece() {
            return myPiece;
        }

        public KnightComponent(Pieces pieces){
            this.myPiece = pieces;
            if (this.getComponentColor() == CONTANTPARAMETERS.Black){
                pieceImage = new ImageIcon(STATICPARAMETERS.getImageData("KnightBlack.png")).getImage();
            }else{
                pieceImage = new ImageIcon(STATICPARAMETERS.getImageData("KnightWhite.png")).getImage();
            }imageX = pieceImage.getWidth(null);
            imageY = pieceImage.getHeight(null);

        }

        public Image getPieceImage() {
            return this.pieceImage;
        }

        public void setPieceImage(Image pieceImage) {
            this.pieceImage = pieceImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            changeBoundsAndColor();
            g.setColor(this.getCurrentColor());
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(this.getPieceImage(), (this.getWidth() - (this.getWidth() * imageX / imageY)) / 2, 0, this.getWidth() * imageX / imageY, getHeight(), null);
            if (STATICPARAMETERS.UnderMouseCursor == this) {
                g.setColor(CONTANTPARAMETERS.Cyan);
                for (int i = 0; i < this.getWidth() / 6; i += 2) {
                    g.drawRect(i, i, this.getWidth() - i * 2, getHeight() - i * 2);
                }

            }
        }public String toString(){
            return "color: " + color + " coordinate: " + comcoordinate[0] + " " + comcoordinate[1] + " ID: " +  ID;
        }
    }
}
