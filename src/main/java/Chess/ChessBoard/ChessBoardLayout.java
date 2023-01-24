package Chess.ChessBoard;
//CreateTime: 2022-03-13 11:51 p.m.

import Chess.ChessPiecesPackage.*;
import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PiecesParentAndInterface.Pieces;

import java.awt.*;

public class ChessBoardLayout extends LayoutAdapter {
    private Pieces currentPiece;

    @Override
    public void layoutContainer(Container parent) {
        int height;

        double ratio = 0.842; //h/w
        if (parent.getHeight()/(parent.getWidth() * 1.0) > ratio){
            height = (int) (parent.getWidth() * ratio);
        }else{
            height = parent.getHeight();
        }

        final int len = height/8;
        final int Roundswidth =(int)(len * 1.5);

        for (Pieces pieces : STATICPARAMETERS.chessPiecesList) {
            currentPiece = pieces;
            if (currentPiece instanceof Pawn) {
                Pawn p = (Pawn) currentPiece;
                p.component.setBounds(currentPiece.coordinate[0] * len, currentPiece.coordinate[1] * len, len, len);
            } else if (currentPiece instanceof Rook) {
                Rook r = (Rook) currentPiece;
                r.component.setBounds(currentPiece.coordinate[0] * len, currentPiece.coordinate[1] * len, len, len);
            } else if (currentPiece instanceof Knight) {
                Knight k = (Knight) currentPiece;
                k.component.setBounds(currentPiece.coordinate[0] * len, currentPiece.coordinate[1] * len, len, len);
            } else if (currentPiece instanceof Bishop) {
                Bishop b = (Bishop) currentPiece;
                b.component.setBounds(currentPiece.coordinate[0] * len, currentPiece.coordinate[1] * len, len, len);
            } else if (currentPiece instanceof Queen) {
                Queen q = (Queen) currentPiece;
                q.component.setBounds(currentPiece.coordinate[0] * len, currentPiece.coordinate[1] * len, len, len);
            } else if (currentPiece instanceof King){
                King k1 = (King) currentPiece;
                k1.component.setBounds(currentPiece.coordinate[0] * len, currentPiece.coordinate[1] * len, len, len);
            }

        }
        for (int i = 0; i < STATICPARAMETERS.chessBoardComponents.length; i++) {
            for (int i1 = 0; i1 < STATICPARAMETERS.chessBoardComponents[i].length; i1++) {
                STATICPARAMETERS.chessBoardComponents[i][i1].setBounds(i1 * len,i * len,len,len);
            }
        }
        if (STATICPARAMETERS.changeComponent != null){
            STATICPARAMETERS.changeComponent.setBounds(len * 2,len * 2,len * 4,len * 4);
        }
        STATICPARAMETERS.ROUND_COMPONENT.setBounds(8 * len,0,Roundswidth,height);
        CONTANTPARAMETERS.RESTART_EXIT_COMPONENT.setBounds(0,height/4,8 * len,height/2);
    }
}
