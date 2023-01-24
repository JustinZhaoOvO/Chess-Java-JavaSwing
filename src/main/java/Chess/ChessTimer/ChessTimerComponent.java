package Chess.ChessTimer;
//CreateTime: 2022-04-02 7:05 p.m.

import Chess.Parameters.STATICPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class ChessTimerComponent extends JPanel {

    private final ChessTimer chessTimer;
    public final JLabel display;
    public ChessTimerComponent(long time){
        this.chessTimer = new ChessTimer(time);
        this.display = new JLabel();
        this.setLayout(new ChessTimerLayout(display));
        this.add(display);
        display.setOpaque(false);
        display.setForeground(new Color(35, 255, 35));
        this.setOpaque(false);
    }

    public void start(){
        this.chessTimer.start();
    }

    public void stop(){
        this.chessTimer.stop();
    }

    public void changeTime(int time){
        this.chessTimer.ChangeTime(time);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        long displaytime = this.chessTimer.getRemainTime();
        long minute;
        long second;
        if (displaytime < 0){
            STATICPARAMETERS.Gaming = false;
            minute = 0;
            second = 0;
        }else{
            minute = displaytime / 60000;
            second = (displaytime/ 1000) % 60;
        }display.setText(String.format("<html>%s%d <br/> %s%d</html>",minute < 10?"0":"",minute,second < 10?"0":"",second));


    }


}
