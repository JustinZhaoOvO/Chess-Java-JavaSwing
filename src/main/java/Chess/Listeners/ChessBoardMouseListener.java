package Chess.Listeners;
//CreateTime: 2022-03-16 11:17 a.m.

import Chess.ChessBoard.ChessBoardComponent;
import Chess.ChessPiecesPackage.King;
import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PiecesParentAndInterface.Pieces;

import java.awt.event.MouseEvent;

import static Chess.Parameters.STATICPARAMETERS.clicked;

public class ChessBoardMouseListener extends MouseListenerAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        if(STATICPARAMETERS.Changing || !STATICPARAMETERS.Gaming){
            return;
        }
        if (STATICPARAMETERS.clicked != null){
            ChessBoardComponent component = (ChessBoardComponent) e.getComponent();
            //
            if (!(clicked instanceof King.KingComponent)) {
                STATICPARAMETERS.transientBoard = STATICPARAMETERS.deepCopy(STATICPARAMETERS.ChessPiece2DList.board);

                STATICPARAMETERS.ChessPiece2DList.updateboard(clicked.getMyPiece().coordinate, new int[]{component.getCompcoordinate()[0], component.getCompcoordinate()[1]});
                if (STATICPARAMETERS.ColorStatus == 0) {
                    Pieces pieces = STATICPARAMETERS.chessPiecesList.get(STATICPARAMETERS.BlackKingID);
                    if (CONTANTPARAMETERS.ChooseWhite) {
                        for (int i = 16; i < STATICPARAMETERS.chessPiecesList.size(); i++) {
                            if (STATICPARAMETERS.chessPiecesList.get(i) == null) {
                                continue;
                            }
                            boolean b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), pieces.coordinate);
                            if (b) {
                                STATICPARAMETERS.ChessPiece2DList.board = STATICPARAMETERS.transientBoard;
                                return;
                            }
                        }
                    } else {
                        for (int i = 0; i < 16; i++) {
                            if (STATICPARAMETERS.chessPiecesList.get(i) == null) {
                                continue;
                            }
                            boolean b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), pieces.coordinate);
                            if (b) {
                                STATICPARAMETERS.ChessPiece2DList.board = STATICPARAMETERS.transientBoard;
                                return;
                            }
                        }
                    }
                } else {
                    Pieces pieces = STATICPARAMETERS.chessPiecesList.get(STATICPARAMETERS.WhiteKingID);
                    if (!CONTANTPARAMETERS.ChooseWhite) {
                        for (int i = 16; i < STATICPARAMETERS.chessPiecesList.size(); i++) {
                            if (STATICPARAMETERS.chessPiecesList.get(i) == null) {
                                continue;
                            }
                            boolean b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), pieces.coordinate);
                            if (b) {
                                STATICPARAMETERS.ChessPiece2DList.board = STATICPARAMETERS.transientBoard;
                                return;
                            }
                        }
                    } else {
                        for (int i = 0; i < 16; i++) {
                            if (STATICPARAMETERS.chessPiecesList.get(i) == null) {
                                continue;
                            }
                            boolean b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), pieces.coordinate);
                            if (b) {
                                STATICPARAMETERS.ChessPiece2DList.board = STATICPARAMETERS.transientBoard;
                                return;
                            }
                        }
                    }
                }
                STATICPARAMETERS.ChessPiece2DList.board = STATICPARAMETERS.transientBoard;
            }
            if(STATICPARAMETERS.IsContains(STATICPARAMETERS.clickedPieceCanMove,component.getCompcoordinate())){

                int[] ints = new int[]{component.getXValue(),component.getYValue()};
                STATICPARAMETERS.ChessPiece2DList.updateboard(STATICPARAMETERS.clicked.getMyPiece().coordinate,ints);
                STATICPARAMETERS.DrawTime += 1;
                STATICPARAMETERS.ColorStatus = STATICPARAMETERS.ColorStatus == 0?1:0;
                if (CONTANTPARAMETERS.ChooseWhite){
                    if (STATICPARAMETERS.ColorStatus == 1){
                        STATICPARAMETERS.uptimer.stop();
                        STATICPARAMETERS.downtimer.start();
                    }else{
                        STATICPARAMETERS.downtimer.stop();
                        STATICPARAMETERS.uptimer.start();
                    }
                }else{
                    if (STATICPARAMETERS.ColorStatus == 0){
                        STATICPARAMETERS.uptimer.stop();
                        STATICPARAMETERS.downtimer.start();
                    }else{
                        STATICPARAMETERS.downtimer.stop();
                        STATICPARAMETERS.uptimer.start();
                    }
                }
                STATICPARAMETERS.clicked.getMyPiece().move(ints);
                STATICPARAMETERS.clicked.setComcoordinate(ints);
                STATICPARAMETERS.clicked = null;
                STATICPARAMETERS.playSound();

                STATICPARAMETERS.GameOver();

            }
        }
    }
}
