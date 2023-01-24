package Chess.RestartOrExitComponent;
//CreateTime: 2022-03-18 9:22 p.m.

import javax.swing.*;
import java.awt.*;

public class RestartExitComponent extends JPanel {

    public RestartExitComponent() {
        this.setBackground(new Color(255,255,255,100));
        this.setVisible(false);
        RestartComponent restartComponent = new RestartComponent();
        ExitComponent exitComponent = new ExitComponent();
        this.setLayout(new RestartExitLayout());
        this.add(restartComponent);
        this.add(exitComponent);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
