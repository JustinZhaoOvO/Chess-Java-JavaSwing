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

public class Bishop extends Pieces{

    public Component component;

    public Bishop(int color, int ID, int[] coordinate) {
        super(color, ID, coordinate);
        component = new BishopComponent(this);
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
        ArrayList<int[]> ints = new ArrayList<>(14);
        int[][] ints1 = new int[][]{{1,-1},{1,1},{-1,1},{-1,-1}};
        Pieces[][] map = STATICPARAMETERS.ChessPiece2DList.board;
        for (int[] ints2 : ints1) {
            int x = this.coordinate[0];
            int y = this.coordinate[1];
            int x1 = ints2[0];
            int y1 = ints2[1];

            while(((x1 > 0)?x<7:x>0) && ((y1 > 0)?y<7:y>0)){
                if (map[y += y1][x +=x1] == null){
                    ints.add(new int[]{x,y});
                }else{
                    if (map[y][x].color != this.color){
                        ints.add(new int[]{x,y});
                    }break;
                }
            }
        }return ints;
    }

    @Override
    public ArrayList<int[]> getAllCanMoveCoordinatesCheck() {
        ArrayList<int[]> ints = new ArrayList<>(14);
        int[][] ints1 = new int[][]{{1,-1},{1,1},{-1,1},{-1,-1}};
        Pieces[][] map = STATICPARAMETERS.ChessPiece2DList.board;
        for (int[] ints2 : ints1) {
            int x = this.coordinate[0];
            int y = this.coordinate[1];
            int x1 = ints2[0];
            int y1 = ints2[1];

            while(((x1 > 0)?x<7:x>0) && ((y1 > 0)?y<7:y>0)){
                if (map[y += y1][x +=x1] == null){
                    int[] ints3 = new int[]{x, y};
                    if (STATICPARAMETERS.CheckCanMove(this.coordinate,ints3,this)) {
                        ints.add(ints3);
                    }
                }else{
                    if (map[y][x].color != this.color){
                        int[] ints3 = new int[]{x, y};
                        if (STATICPARAMETERS.CheckCanMove(this.coordinate,ints3,this)) {
                            ints.add(ints3);
                        }
                    }break;
                }
            }
        }return ints;
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
        return "Bishop" + ID;
    }

    public class BishopComponent extends PiecesComponent {
        public final int componentColor = color;
        public final int ComID = ID;
        private Pieces myPiece;
        private Image pieceImage;
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

        public BishopComponent(Pieces pieces){
            this.myPiece = pieces;
            if (this.getComponentColor() == CONTANTPARAMETERS.Black){
                pieceImage = new ImageIcon(STATICPARAMETERS.getImageData("BishopBlack.png")).getImage();
            }else{
                pieceImage = new ImageIcon(STATICPARAMETERS.getImageData("BishopWhite.png")).getImage();
            }
        }

        public Image getPieceImage() {
            return pieceImage;
        }

        public String toString(){
            return "color: " + color + " coordinate: " + comcoordinate[0] + " " + comcoordinate[1] + " ID: " +  ID;
        }
    }
}
