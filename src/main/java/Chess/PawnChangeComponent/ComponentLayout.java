package Chess.PawnChangeComponent;
//CreateTime: 2022-03-17 3:14 p.m.

import Chess.ChessBoard.LayoutAdapter;

import java.awt.*;

public class ComponentLayout extends LayoutAdapter {
    private Component[] components;

    public ComponentLayout(Component[] components){
        this.components = components;
    }


    @Override
    public void layoutContainer(Container parent) {
        int Comwidth = parent.getWidth()/2;
        int Comheight = parent.getHeight()/2;

        for (Component component : components) {
            if (component instanceof ChangeToQueen){
                component.setBounds(0,0,Comwidth,Comheight);
            }else if(component instanceof  ChangeToRook){
                component.setBounds(Comwidth,0,Comwidth,Comheight);
            }else if(component instanceof ChangeToBishop){
                component.setBounds(0,Comheight,Comwidth,Comheight);
            }else{
                component.setBounds(Comwidth,Comheight,Comwidth,Comheight);
            }
        }
    }
}
