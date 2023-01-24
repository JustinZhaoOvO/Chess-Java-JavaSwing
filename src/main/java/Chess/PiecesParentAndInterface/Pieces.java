package Chess.PiecesParentAndInterface;
//CreateTime: 2022-03-11 5:08 p.m.

import java.awt.*;
import java.util.ArrayList;

public abstract class Pieces implements Comparable{
    //Black 0 White 1
    public final int color;
    //[Vertical, Horizontal]
    public int[] coordinate;
    //the ID of this piece
    public final int ID;
    //Component
    public Component component;


    //instead of the Pieces()
    public Pieces(int color, int ID,int[] coordinate){
        this.color = color;
        this.ID = ID;
        this.coordinate = coordinate;
    }

    public abstract ArrayList<int[]> getAllCanMoveCoordinates();


    public abstract ArrayList<int[]> getAllCanMoveCoordinatesCheck();

    public abstract void move(int[] coordinate);



    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Pieces){
            Pieces p = (Pieces) o;
            if (this.ID < p.ID){
                return -1;
            }else{
                return 1;
            }
        }else{
            throw new RuntimeException("data type is incorrect");
        }
    }

}
