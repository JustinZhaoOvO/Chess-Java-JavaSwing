package Chess.RestartOrExitComponent;
//CreateTime: 2022-03-18 11:02 p.m.

import Chess.Listeners.RestartComponentMouseListener;
import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RestartComponent extends JPanel {
    private final Image restart;

    public RestartComponent() {
        restart = new ImageIcon(STATICPARAMETERS.getImageData("restart.png") ).getImage();
        this.setOpaque(false);
        this.addMouseListener(new RestartComponentMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(restart,0,0,this.getWidth(),this.getHeight(),null);
        if (STATICPARAMETERS.UnderMouseCursor == this){
            g.setColor(CONTANTPARAMETERS.Cyan);
            for (int i = 0; i < this.getWidth() / 5; i+=2) {
                g.drawRect(i,i,this.getWidth() - i*2,getHeight() - i*2);
            }
        }
    }
}
