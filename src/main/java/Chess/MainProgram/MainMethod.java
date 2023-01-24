package Chess.MainProgram;
//CreateTime: 2022-03-11 6:14 p.m.

import Chess.ChessBoard.ChessBoardComponent;
import Chess.ChessBoard.ChessBoardLayout;
import Chess.ChessBoard.ChessBoardPanel;
import Chess.ChessBoard.MomeryBoard2D;
import Chess.Listeners.ChessBoardMouseListener;
import Chess.Listeners.ChessPiecesMouseListener;
import Chess.Parameters.CONTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PiecesParentAndInterface.PiecesComponent;
import Chess.ShowRoundComponent.RoundComponent;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

public class MainMethod extends JFrame {
    public MainMethod(int ...time) {
        int upsecond;
        int upminute;
        int downsecond;
        int downminute;
        if (time.length == 1){
            upminute = Math.min(time[0], 99);
            downminute = upminute;
            upsecond = 0;
            downsecond = 0;
        }else if (time.length == 2){
            upminute = Math.min(time[0], 99);
            upsecond = Math.min(time[1], 59);
            downminute = upminute;
            downsecond = upsecond;
        }else if (time.length == 3){
            upminute = Math.min(time[0], 99);
            upsecond = Math.min(time[1], 59);
            downminute = Math.min(time[2], 99);
            downsecond = 0;
        }else if (time.length >= 4){
            upminute = Math.min(time[0], 99);
            upsecond = Math.min(time[1], 59);
            downminute = Math.min(time[2], 99);
            downsecond = Math.min(time[3], 59);
        }else{
            upminute = 10;
            downminute = 10;
            upsecond = 0;
            downsecond = 0;
        }
        long uptime = (upminute * 60L + upsecond) * 1000;
        long downtime = (downminute * 60L + downsecond) * 1000;
        //set Window args
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(CONTANTPARAMETERS.ScreenOriginalLocationX,CONTANTPARAMETERS.ScreenOriginalLocationY);
        this.setSize(CONTANTPARAMETERS.ScreenOriginalSizeX,CONTANTPARAMETERS.ScreenOriginalSizeY);
        this.setTitle(CONTANTPARAMETERS.WindowName);
        STATICPARAMETERS.MainWindow = this;
        while (true) {
            //initialize Static paraments
            STATICPARAMETERS.ROUND_COMPONENT = new RoundComponent(uptime,downtime);
            STATICPARAMETERS.chessBoardComponents = new ChessBoardComponent[CONTANTPARAMETERS.ChessBoardLength][CONTANTPARAMETERS.ChessBoardLength];
            STATICPARAMETERS.chessPiecesList = new ArrayList<>(CONTANTPARAMETERS.TotalChessPieces);
            STATICPARAMETERS.ColorStatus = 1;
            STATICPARAMETERS.clicked = null;
            STATICPARAMETERS.Gaming = true;
            STATICPARAMETERS.PawnChangeTo = null;
            STATICPARAMETERS.Changing = false;
            STATICPARAMETERS.changeComponent = null;
            STATICPARAMETERS.NoneReStartExit = 0;
            STATICPARAMETERS.DrawTime = 0;
            STATICPARAMETERS.startTimer = false;

        /*
        Chess.AParty : Save a party(Black/White) pieces list, update the pieces coordinate
        MomeryBoard2D : Create a 2D Chess Board to save Pieces Location in the Chess board.
         */
            //create 2D chessBoard, WhitePieces list, BlackPieces list, allPieces list
            STATICPARAMETERS.ChessPiece2DList = new MomeryBoard2D(STATICPARAMETERS.chessPiecesList, CONTANTPARAMETERS.ChooseWhite);
            PiecesComponent currentComponent;
            MouseListener mouseListener = new ChessPiecesMouseListener();

            //create JPanel
            ChessBoardPanel chessBoardPanel = new ChessBoardPanel();
            this.setContentPane(chessBoardPanel);
            STATICPARAMETERS.Root = chessBoardPanel;

            //add component
            chessBoardPanel.add(CONTANTPARAMETERS.RESTART_EXIT_COMPONENT);
            for (int i = 0; i < STATICPARAMETERS.chessPiecesList.size(); i++) {
                currentComponent = (PiecesComponent) STATICPARAMETERS.chessPiecesList.get(i).getComponent();
                currentComponent.addMouseListener(mouseListener);
                chessBoardPanel.add(currentComponent);
            }
            for (int i = 0; i < CONTANTPARAMETERS.ChessBoardLength; i++) {
                for (int i1 = 0; i1 < CONTANTPARAMETERS.ChessBoardLength; i1++) {
                    ChessBoardComponent chessBoardComponent = new ChessBoardComponent(i1, i);
                    chessBoardComponent.addMouseListener(new ChessBoardMouseListener());
                    chessBoardPanel.add(chessBoardComponent);
                    STATICPARAMETERS.chessBoardComponents[i][i1] = chessBoardComponent;
                }
            }

            //set layout
            ChessBoardLayout chessBoardLayout = new ChessBoardLayout();
            chessBoardPanel.setLayout(chessBoardLayout);

            chessBoardPanel.add(STATICPARAMETERS.ROUND_COMPONENT);

            //Update GUI
            chessBoardPanel.updateUI();
            //set window visible
            this.setVisible(true);
            repaint();

            while (STATICPARAMETERS.Gaming) {
                repaint();
                STATICPARAMETERS.Change();
            }STATICPARAMETERS.downtimer.stop();
            STATICPARAMETERS.uptimer.stop();
            CONTANTPARAMETERS.RESTART_EXIT_COMPONENT.setVisible(true);
//            chessBoardPanel.updateUI();
            while (STATICPARAMETERS.NoneReStartExit == 0) {
                repaint();
            }
            if (STATICPARAMETERS.NoneReStartExit == 2) {
                System.exit(0);
            } else {
                STATICPARAMETERS.Root.setLayout(null);
            }
        }
    }
}
