package Chess.ChessPiecesPackage;
//CreateTime: 2022-03-11 5:02 p.m.

import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PiecesParentAndInterface.Pieces;
import Chess.PiecesParentAndInterface.PiecesComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class King extends Pieces{
    public Component component;
    public boolean shortcastling = true;
    public boolean longcasting = true;
    private Pieces RightSideRook;
    private Pieces LeftSideRook;
    public King(int color, int ID, int[] coordinate) {
        super(color, ID, coordinate);
        component = new KingComponent(this);
    }

    public void setRightSideRook(Pieces rightSideRook) {
        RightSideRook = rightSideRook;
    }

    public void setLeftSideRook(Pieces leftSideRook) {
        LeftSideRook = leftSideRook;
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
        int[][] mayCanMove = new int[][]{{x-1,y + 1},{x-1,y},{x-1,y-1},{x,y+1},{x,y-1},{x+1,y+1},{x+1,y},{x+1,y-1}};
        for (int[] ints1 : mayCanMove) {
            if (ints1[0] >= 0 &&  7 >= ints1[0] && ints1[1] >= 0 &&  7 >= ints1[1] && (STATICPARAMETERS.ChessPiece2DList.board[ints1[1]][ints1[0]] == null || STATICPARAMETERS.ChessPiece2DList.board[ints1[1]][ints1[0]].color != this.color)){
                ints.add(ints1);
            }
        }if (this.castlingKingSide()){
            ints.add(new int[]{6,y});
        }if (this.castlingQueenSide()){
            ints.add(new int[]{2,y});
        }return ints;
    }

    @Override
    public ArrayList<int[]> getAllCanMoveCoordinatesCheck() {
        int x = this.coordinate[0];
        int y = this.coordinate[1];
        ArrayList<int[]> ints = new ArrayList<>(8);
        int[][] mayCanMove = new int[][]{{x-1,y + 1},{x-1,y},{x-1,y-1},{x,y+1},{x,y-1},{x+1,y+1},{x+1,y},{x+1,y-1}};
        for (int[] ints1 : mayCanMove) {
            if (ints1[0] >= 0 &&  7 >= ints1[0] && ints1[1] >= 0 &&  7 >= ints1[1] && (STATICPARAMETERS.ChessPiece2DList.board[ints1[1]][ints1[0]] == null || STATICPARAMETERS.ChessPiece2DList.board[ints1[1]][ints1[0]].color != this.color)){
                if (STATICPARAMETERS.CheckCanMove(this.coordinate,ints1,this)){
                    ints.add(ints1);
                }
            }
        }if (this.castlingKingSide()){
            ints.add( new int[]{6,y});

        }if (this.castlingQueenSide()){
            ints.add(new int[]{2, y});

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
        int i = this.coordinate[0] - coordinate[0];
        if (Math.abs(i)== 2) {
            if (i == -2){
                int[] ints = new int[]{this.coordinate[0] + 1,this.coordinate[1]};
                STATICPARAMETERS.ChessPiece2DList.updateboard(RightSideRook.coordinate,ints);
                RightSideRook.move(ints);
            }else{
                int[] ints = new int[]{this.coordinate[0] - 1,this.coordinate[1]};
                STATICPARAMETERS.ChessPiece2DList.updateboard(LeftSideRook.coordinate,ints);
                LeftSideRook.move(ints);
            }
        }
        this.shortcastling = false;
        this.longcasting = false;
        this.coordinate = coordinate;
    }
    //short
    public boolean castlingKingSide() {
        boolean b;
        if (this.shortcastling && STATICPARAMETERS.ChessPiece2DList.board[this.coordinate[1] ][this.coordinate[0]+ 1] == null && STATICPARAMETERS.ChessPiece2DList.board[this.coordinate[1] ][this.coordinate[0]+ 2] == null) {
            int[] ints1 = new int[]{this.coordinate[0]  + 1, this.coordinate[1]};
            int[] ints2 = new int[]{this.coordinate[0]  + 2, this.coordinate[1]};
            int[] ints3 = new int[]{this.coordinate[0] , this.coordinate[1]};
            if (this.ID > 16) {
                for (int i = 0; i < 16; i++) {
                    if (STATICPARAMETERS.chessPiecesList.get(i)  == null||(i == 4 && (((King)STATICPARAMETERS.chessPiecesList.get(4)).longcasting) && (((King)STATICPARAMETERS.chessPiecesList.get(4)).shortcastling))){
                        continue;
                    }else {
                        b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), ints1, ints2,ints3, this.coordinate);
                    }if (b) {

                        return false;
                    }
                }
            } else {
                for (int i = 16; i < 32; i++) {
                    if (STATICPARAMETERS.chessPiecesList.get(i)  == null||(i == 20 && (((King)STATICPARAMETERS.chessPiecesList.get(20)).longcasting) && (((King)STATICPARAMETERS.chessPiecesList.get(20)).shortcastling))){
                        continue;
                    }else {
                        b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), ints1, ints2,ints3, this.coordinate);
                    }if (b) {
                        return false;
                    }
                }
            }return true;
        }return false;
    }
    //long
    public boolean castlingQueenSide(){
        boolean b;
        if (this.longcasting && STATICPARAMETERS.ChessPiece2DList.board[this.coordinate[1] ][this.coordinate[0] - 1] == null && STATICPARAMETERS.ChessPiece2DList.board[this.coordinate[1] ][this.coordinate[0]- 2] == null && STATICPARAMETERS.ChessPiece2DList.board[this.coordinate[1] ][this.coordinate[0]- 3] == null) {
            int[] ints1 = new int[]{this.coordinate[0], this.coordinate[1]};
            int[] ints2 = new int[]{this.coordinate[0]  - 1, this.coordinate[1]};
            int[] ints3 = new int[]{this.coordinate[0]  - 2, this.coordinate[1]};
            if (this.ID > 16) {
                for (int i = 0; i < 16; i++) {
                    if (STATICPARAMETERS.chessPiecesList.get(i)  == null||(i == 4 && (((King)STATICPARAMETERS.chessPiecesList.get(4)).longcasting) && (((King)STATICPARAMETERS.chessPiecesList.get(4)).shortcastling))){
                        continue;
                    }else {
                        b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), ints1, ints2, ints3 , this.coordinate);
                    }if (b) {
                        return false;
                    }
                }
            } else {
                for (int i = 16; i < 32; i++) {
                    if (STATICPARAMETERS.chessPiecesList.get(i)  == null||(i == 20 && (((King)STATICPARAMETERS.chessPiecesList.get(20)).longcasting) && (((King)STATICPARAMETERS.chessPiecesList.get(20)).shortcastling))){
                        continue;
                    }else {
                        b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), ints1, ints2,ints3, this.coordinate);
                    }if (b) {
                        return false;
                    }
                }
            }return true;
        }return false;
    }


    public void setShortcastling() {
        this.shortcastling = false;
    }


    public void setLongcasting() {
        this.longcasting = false;
    }

    @Override
    public String toString(){
        return "King" + ID;
    }

    public class KingComponent extends PiecesComponent {
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

        public KingComponent(Pieces pieces){
            this.myPiece = pieces;
            if (this.getComponentColor() == CONTANTPARAMETERS.Black){
                pieceImage = new ImageIcon(STATICPARAMETERS.getImageData(  "KingBlack.png")).getImage();
                STATICPARAMETERS.BlackKingID = this.ComID;
            }else{
                pieceImage = new ImageIcon(STATICPARAMETERS.getImageData(   "KingWhite.png")).getImage();
                STATICPARAMETERS.WhiteKingID = this.ComID;
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