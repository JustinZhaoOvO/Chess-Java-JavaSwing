package Chess.Parameters;
//CreateTime: 2022-03-15 2:54 p.m.


import Chess.RestartOrExitComponent.RestartExitComponent;

import java.awt.*;

public class CONTANTPARAMETERS {
    //whether to choose white
    public static boolean ChooseWhite = false;
    //two party has 32 pieces
    public final static int TotalChessPieces = 32;
    //8 * 8 chess board
    public final static int ChessBoardLength = 8;
    //color code
    public final static int Black = 0;
    //color code
    public final static int White = 1;
    //topleft center to screen location  : width
    public final static int ScreenOriginalLocationX = 450;
    //topleft center to screen location  : height
    public final static int ScreenOriginalLocationY = 250;
    //screen width diffenrence
    public final static int ScreenVisibleX = 14;   //7 | 14
    //screen height diffenrence
    public final static int ScreenVisibleY = 37;  //31 | 37
    //screen width
    public final static int ScreenOriginalSizeX = 600 + ScreenVisibleX;
    //screen height
    public final static int ScreenOriginalSizeY = 400 + ScreenVisibleY;
    //Restart button and Exit button
    public final static RestartExitComponent RESTART_EXIT_COMPONENT = new RestartExitComponent();
    //chess pieces layout
    public final static int oppositeKingRow = 0;
    public final static int oppositePawnRow = 1;
    public final static int myKingRow = 7;
    public final static int myPawnRow = 6;
    //window name
    public final static String WindowName = "Chess";
    //background
    public final static Color LightBlue = new Color(108, 158, 210);
    //chessboard
    public final static Color DarkGreen = new Color(39, 114, 1);
    //chessboard
    public final static Color YelloWhite = new Color(243, 235, 205);
    //selected
    public final static Color LightYellow = new Color(245, 255, 118);
    //can kill
    public final static Color Red = new Color(217, 8, 8);
    //can move
    public final static Color translucentRed = new Color(171, 1, 1,200);
    //frame
    public final static Color Cyan = new Color(6, 202, 208);
    //
    public final static Color ChooseComponentColor = new Color(197, 203, 255);

    public final static Color lightGreen = new Color(57, 232, 23);

}

