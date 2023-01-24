package Chess.ChessBoard;
//CreateTime: 2022-03-11 5:36 p.m.

import Chess.ChessPiecesPackage.*;
import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PiecesParentAndInterface.Pieces;

import java.util.ArrayList;
import java.util.Collections;

public class MomeryBoard2D {
    public Pieces[][] board;
    private int ID = 0;
    private int color;
    public ArrayList<Pieces> piecesArrayList;
    private Pieces nowPieces;

    public MomeryBoard2D(ArrayList<Pieces> piecesArrayList, boolean white){ // boolean white : choose white or not white(black)
        this.piecesArrayList = piecesArrayList;
        this.board = new Pieces[CONTANTPARAMETERS.ChessBoardLength][CONTANTPARAMETERS.ChessBoardLength];
        final int[] needTofill = new int[]{CONTANTPARAMETERS.oppositeKingRow,CONTANTPARAMETERS.oppositePawnRow,CONTANTPARAMETERS.myKingRow,CONTANTPARAMETERS.myPawnRow};
        for (int i : needTofill) {
           if (i == CONTANTPARAMETERS.oppositePawnRow || i == CONTANTPARAMETERS.myPawnRow ){
               color = ((i == CONTANTPARAMETERS.oppositePawnRow) ^ white)?CONTANTPARAMETERS.White:CONTANTPARAMETERS.Black;
               for (int i1 = 0; i1 < CONTANTPARAMETERS.ChessBoardLength; i1++) {
                   nowPieces = new Pawn(color,ID++,new int[]{i1,i});
                   board[i][i1] = nowPieces;
                   piecesArrayList.add(nowPieces);
               }
           }else{
               color = ((i == CONTANTPARAMETERS.oppositeKingRow) ^ white)?CONTANTPARAMETERS.White:CONTANTPARAMETERS.Black;
               for (int i1 = 0; i1 < CONTANTPARAMETERS.ChessBoardLength; i1++) {
                   if (i1 == 0 || i1 == 7){
                       nowPieces = new Rook(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }else if(i1 == 1 || i1 == 6){
                       nowPieces = new Knight(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }else if(i1 == 2 || i1 == 5){
                       nowPieces = new Bishop(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }else if(i1 == 3){
                       nowPieces = new Queen(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }else{
                       nowPieces = new King(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }
               }

           }
        }
        Collections.sort(piecesArrayList);
        ((King) piecesArrayList.get(STATICPARAMETERS.BlackKingID)).setLeftSideRook(piecesArrayList.get(STATICPARAMETERS.BlackKingID - 4));
        ((King) piecesArrayList.get(STATICPARAMETERS.BlackKingID)).setRightSideRook(piecesArrayList.get(STATICPARAMETERS.BlackKingID + 3));
        ((King) piecesArrayList.get(STATICPARAMETERS.WhiteKingID)).setLeftSideRook(piecesArrayList.get(STATICPARAMETERS.WhiteKingID - 4));
        ((King) piecesArrayList.get(STATICPARAMETERS.WhiteKingID)).setRightSideRook(piecesArrayList.get(STATICPARAMETERS.WhiteKingID + 3));
    }

    public void updateboard(int[] original, int[] now){
        board[now[1]][now[0]] = board[original[1]][original[0]];
        board[original[1]][original[0]] = null;
    }

    public void deletePiece(int[] coordinate){
        board[coordinate[1]][coordinate[0]] = null;
    }
}
