package Chess.ChessPiecesPackage;
//CreateTime: 2022-03-11 5:13 p.m.

import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PawnChangeComponent.ComponentPanel;
import Chess.PiecesParentAndInterface.Pieces;
import Chess.PiecesParentAndInterface.PiecesComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Pieces{
    public Component component;
    private boolean canMove2 = true;
    public Pawn(int color, int ID, int[] coordinate) {
        super(color, ID, coordinate);
       component = new PawnComponent(this);
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
        //vertically moving forward or backward
        int direction = CONTANTPARAMETERS.ChooseWhite ^ (this.color == 1)?1:-1;
        Pieces[][] board = STATICPARAMETERS.ChessPiece2DList.board;
        ArrayList<int[]> ints = new ArrayList<>(5);
        if (direction > 0? y +  direction <= 7 : y +  direction >= 0) {
            if (board[y+direction][x] == null){
                ints.add(new int[]{x, y + direction});
                if (this.canMove2 && board[y+2* direction][x] == null ) {
                    ints.add(new int[]{x, y + 2 * direction});
                }
            }if (x - 1 >= 0 && board[y+direction][x - 1] != null &&  board[y+ direction][x - 1].color != this.color){
                ints.add(new int[]{x-1,y+ direction});
            }if (x + 1 <= 7 && board[y+direction][x + 1] != null &&  board[y+ direction][x + 1].color != this.color){
                ints.add(new int[]{x+1,y+ direction});
            }
        }if (((this.color == 0)?STATICPARAMETERS.whitePawnMove1Step:STATICPARAMETERS.blackPawnMove1Step) != null && Math.abs((((this.color == 0)?STATICPARAMETERS.whitePawnMove2Step:STATICPARAMETERS.blackPawnMove2Step)[0] - this.coordinate[0])) == 1 && ((this.color == 0)?STATICPARAMETERS.whitePawnMove2Step:STATICPARAMETERS.blackPawnMove2Step)[1] == this.coordinate[1]){
            ints.add(((this.color == 0)?STATICPARAMETERS.whitePawnMove1Step:STATICPARAMETERS.blackPawnMove1Step));
        }
        return ints;
    }

    @Override
    public ArrayList<int[]> getAllCanMoveCoordinatesCheck() {
        int x = this.coordinate[0];
        int y = this.coordinate[1];
        int direction = CONTANTPARAMETERS.ChooseWhite ^ (this.color == 1)?1:-1;
        Pieces[][] board = STATICPARAMETERS.ChessPiece2DList.board;
        ArrayList<int[]> ints = new ArrayList<>(5);
        if (direction > 0? y +  direction <= 7 : y +  direction >= 0) {
            if (board[y+direction][x] == null){
                int[] ints1 = new int[]{x,y+direction};
                if (STATICPARAMETERS.CheckCanMove(this.coordinate,ints1,this)) {
                    ints.add(ints1);
                }
                if (this.canMove2 && board[y+2* direction][x] == null ) {
                    ints1 = new int[]{x, y + 2 * direction};
                    if (STATICPARAMETERS.CheckCanMove(this.coordinate,ints1,this)) {
                        ints.add(ints1);
                    }
                }
            }if (x - 1 >= 0 && board[y+direction][x - 1] != null &&  board[y+ direction][x - 1].color != this.color){
                int[] ints1 = new int[]{x-1,y+ direction};
                if (STATICPARAMETERS.CheckCanMove(this.coordinate,ints1,this)) {
                    ints.add(ints1);
                }
            }if (x + 1 <= 7 && board[y+direction][x + 1] != null &&  board[y+ direction][x + 1].color != this.color){
                int[] ints1 = new int[]{x+1,y+ direction};
                if (STATICPARAMETERS.CheckCanMove(this.coordinate,ints1,this)) {
                    ints.add(ints1);
                }
            }
        }if (((this.color == 0)?STATICPARAMETERS.whitePawnMove1Step:STATICPARAMETERS.blackPawnMove1Step) != null && Math.abs((((this.color == 0)?STATICPARAMETERS.whitePawnMove2Step:STATICPARAMETERS.blackPawnMove2Step)[0] - this.coordinate[0])) == 1 && ((this.color == 0)?STATICPARAMETERS.whitePawnMove2Step:STATICPARAMETERS.blackPawnMove2Step)[1] == this.coordinate[1]){
            if (STATICPARAMETERS.CheckCanMove(this.coordinate,((this.color == 0)?STATICPARAMETERS.whitePawnMove1Step:STATICPARAMETERS.blackPawnMove1Step),this)){
                ints.add(((this.color == 0) ? STATICPARAMETERS.whitePawnMove1Step : STATICPARAMETERS.blackPawnMove1Step));
            }
        }
        return ints;
    }

    @Override
    public void move(int[] coordinate) {
        STATICPARAMETERS.DrawTime = 0;
        if (Arrays.equals(coordinate,((this.color == 0)?STATICPARAMETERS.whitePawnMove1Step:STATICPARAMETERS.blackPawnMove1Step))){
            STATICPARAMETERS.Root.remove(((this.color == 0)?STATICPARAMETERS.whiteMove2Step:STATICPARAMETERS.blackMove2Step));
            STATICPARAMETERS.chessPiecesList.set(((this.color == 0)?STATICPARAMETERS.whiteMove2Step:STATICPARAMETERS.blackMove2Step).getMyPiece().ID,null);
            STATICPARAMETERS.ChessPiece2DList.deletePiece(((this.color == 0)?STATICPARAMETERS.whitePawnMove2Step:STATICPARAMETERS.blackPawnMove2Step));
        }
        if (Math.abs(this.coordinate[1] - coordinate[1]) == 2){
            int direction = (coordinate[1] - this.coordinate[1] )/ 2;
            if (this.color == 0){
                STATICPARAMETERS.blackPawnMove1Step = new int[]{this.coordinate[0],this.coordinate[1] + direction};
                STATICPARAMETERS.blackPawnMove2Step = new int[]{coordinate[0],coordinate[1]};
                STATICPARAMETERS.blackMove2Step = (PiecesComponent) this.component;
            }else{
                STATICPARAMETERS.whitePawnMove1Step = new int[]{this.coordinate[0],this.coordinate[1] + direction};
                STATICPARAMETERS.whitePawnMove2Step = new int[]{coordinate[0],coordinate[1]};
                STATICPARAMETERS.whiteMove2Step = (PiecesComponent) this.component;
            }
        }
        this.canMove2 = false;
        this.coordinate = coordinate;
        if (this.color == 0){
            STATICPARAMETERS.whitePawnMove1Step = null;
            STATICPARAMETERS.whitePawnMove2Step = null;
        }else{
            STATICPARAMETERS.blackPawnMove1Step = null;
            STATICPARAMETERS.blackPawnMove2Step = null;
        }
        if (coordinate[1] == ((CONTANTPARAMETERS.ChooseWhite)?((this.color == 1)?0:7):(this.color == 1)?7:0)){
            STATICPARAMETERS.uptimer.stop();
            STATICPARAMETERS.downtimer.stop();
            STATICPARAMETERS.Changing = true;
            STATICPARAMETERS.needToInstead = this;
            ComponentPanel componentPanel = new ComponentPanel(this.color);
            Component[] components = STATICPARAMETERS.Root.getComponents();
            STATICPARAMETERS.changeComponent = componentPanel;
            for (Component component1 : components) {
                STATICPARAMETERS.Root.remove(component1);
            }STATICPARAMETERS.Root.add(componentPanel);
            for (Component component1 : components) {
                STATICPARAMETERS.Root.add(component1);
            }try {
                STATICPARAMETERS.Root.updateUI();
            } catch (Exception ignored) {

            }
        }
    }



    @Override
    public String toString(){
        return "Pawn" + ID;
    }

    public class PawnComponent extends PiecesComponent {
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

        public PawnComponent(Pieces pieces){
            this.myPiece = pieces;
            if (this.getComponentColor() == CONTANTPARAMETERS.Black){
                pieceImage = new ImageIcon(STATICPARAMETERS.getImageData("PawnBlack.png")).getImage();
            }else{
                pieceImage = new ImageIcon(STATICPARAMETERS.getImageData("PawnWhite.png")).getImage();
            }
        }

        public Image getPieceImage() {
            return pieceImage;
        }

        public void setPieceImage(Image pieceImage) {
            this.pieceImage = pieceImage;
        }

        public String toString(){
            return "color: " + color + " coordinate: " + comcoordinate[0] + " " + comcoordinate[1] + " ID: " +  ID;
        }
    }
}
