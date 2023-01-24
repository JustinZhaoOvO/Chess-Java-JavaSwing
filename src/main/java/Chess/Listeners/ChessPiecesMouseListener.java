package Chess.Listeners;
//CreateTime: 2022-03-15 6:34 p.m.

import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PiecesParentAndInterface.PiecesComponent;

import java.awt.event.MouseEvent;

import static Chess.Parameters.STATICPARAMETERS.clicked;
import static Chess.Parameters.STATICPARAMETERS.clickedPieceCanMove;

public class ChessPiecesMouseListener extends MouseListenerAdapter {


    @Override
    public void mouseClicked(MouseEvent e) {
        if(STATICPARAMETERS.Changing || !STATICPARAMETERS.Gaming){
            return;
        }
        PiecesComponent component = (PiecesComponent) e.getComponent();
        if ( clicked == null ||clicked == component ) {
            if (component.getComponentColor() == STATICPARAMETERS.ColorStatus) {
                if (clicked == null) {
                    if (!STATICPARAMETERS.startTimer){
                        if (CONTANTPARAMETERS.ChooseWhite){
                            if (STATICPARAMETERS.ColorStatus == 1){
                                STATICPARAMETERS.downtimer.start();
                            }else{
                                STATICPARAMETERS.uptimer.start();
                            }
                        }else{
                            if (STATICPARAMETERS.ColorStatus == 0){
                                STATICPARAMETERS.downtimer.start();
                            }else{
                                STATICPARAMETERS.uptimer.start();
                            }
                        }
                        STATICPARAMETERS.startTimer = true;
                    }clicked = component;
                    clickedPieceCanMove = component.getMyPiece().getAllCanMoveCoordinatesCheck();
                } else {
                    clicked = null;
                }
            }
        }else{
            if (STATICPARAMETERS.ColorStatus != component.getComponentColor()) {
                if (STATICPARAMETERS.IsContains(clickedPieceCanMove, component.getComcoordinate())) {
                    if (component.getComID() == STATICPARAMETERS.WhiteKingID) {
                        STATICPARAMETERS.Win = 0;
                        STATICPARAMETERS.Gaming = false;
                    } else if (component.getComID() == STATICPARAMETERS.BlackKingID) {
                        STATICPARAMETERS.Win = 1;
                        STATICPARAMETERS.Gaming = false;
                    }
                    STATICPARAMETERS.Root.remove(component);
                    STATICPARAMETERS.chessPiecesList.set(component.getMyPiece().ID,null);
                    int[] ints = new int[]{component.getComcoordinate()[0], component.getComcoordinate()[1]};
                    STATICPARAMETERS.ChessPiece2DList.updateboard(clicked.getMyPiece().coordinate, ints);
                    STATICPARAMETERS.ColorStatus = STATICPARAMETERS.ColorStatus == 0 ? 1 : 0;
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
                    clicked.getMyPiece().move(ints);
                    clicked.setComcoordinate(ints);
                    STATICPARAMETERS.DrawTime += 1;


                    clicked = null;
                    STATICPARAMETERS.playSound();
                    STATICPARAMETERS.GameOver();
                }
            }else{
                clicked = component;
                clickedPieceCanMove = component.getMyPiece().getAllCanMoveCoordinatesCheck();
            }
        }
    }
}
