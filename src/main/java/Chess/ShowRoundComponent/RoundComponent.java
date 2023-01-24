package Chess.ShowRoundComponent;
//CreateTime: 2022-03-18 5:49 p.m.

import Chess.ChessTimer.ChessTimerComponent;
import Chess.ChessTimer.ChessTimerMouseListener;
import Chess.Parameters.STATICPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class RoundComponent extends JPanel {

    public RoundComponent(long uptime, long downtime){
        STATICPARAMETERS.uptimer = new ChessTimerComponent(uptime);
        STATICPARAMETERS.uptimer.addMouseListener(new ChessTimerMouseListener());
        STATICPARAMETERS.downtimer = new ChessTimerComponent(downtime);
        STATICPARAMETERS.downtimer.addMouseListener(new ChessTimerMouseListener());
        this.setLayout(new RoundLayout());
        this.add(STATICPARAMETERS.uptimer);
        this.add(STATICPARAMETERS.downtimer);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if( STATICPARAMETERS.ColorStatus == 0){
            this.setBackground(new Color(0,0,0));
        }else{
            this.setBackground(new Color(255, 255, 255));
        }
    }
}
