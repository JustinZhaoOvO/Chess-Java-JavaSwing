package Chess.ChessTimer;
//CreateTime: 2022-04-03 4:39 p.m.

import Chess.Listeners.MouseListenerAdapter;
import Chess.Parameters.STATICPARAMETERS;

import java.awt.event.MouseEvent;

public class ChessTimerMouseListener extends MouseListenerAdapter {
    public static void main(String[] args) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (STATICPARAMETERS.startTimer){
            return;
        }ChessTimerComponent timer = (ChessTimerComponent) e.getComponent();
        if (e.getButton() == MouseEvent.BUTTON1){
            if (e.getY() > e.getComponent().getHeight() / 2) {
                timer.changeTime(1000);
            }else{
                timer.changeTime(60000);
            }
        }else{
            if (e.getY() > e.getComponent().getHeight() / 2) {
                timer.changeTime(-1000);
            }else{
                timer.changeTime(-60000);
            }
        }
    }
}
