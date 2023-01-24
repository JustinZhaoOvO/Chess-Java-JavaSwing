package Chess.Listeners;
//CreateTime: 2022-03-17 12:53 p.m.


import Chess.Parameters.STATICPARAMETERS;
import Chess.PawnChangeComponent.ChangeToBishop;
import Chess.PawnChangeComponent.ChangeToQueen;
import Chess.PawnChangeComponent.ChangeToRook;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ChangeComponentMouseListener extends MouseListenerAdapter {


    @Override
    public void mouseClicked(MouseEvent e) {
        Component component = e.getComponent();
        if (component instanceof ChangeToQueen){
            STATICPARAMETERS.PawnChangeTo = "Queen";
        }else if(component instanceof ChangeToRook){
            STATICPARAMETERS.PawnChangeTo = "Rook";
        }else if(component instanceof ChangeToBishop){
            STATICPARAMETERS.PawnChangeTo = "Bishop";
        }else{
            STATICPARAMETERS.PawnChangeTo = "Knight";
        }STATICPARAMETERS.Changing = false;
    }
}
