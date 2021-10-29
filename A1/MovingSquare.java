/*
 *	===============================================================================
 *	MovingSquare.java : A subclass of MovingRectangle and also is a subclass of MovingShape.
 *	Have 2 override constructors, a setWidth method and setHeight method.
 *  The draw method, getArea method and contains method are same method used in the MovingRectangle class.
 *  Name: Xiaolin Li
 *	UPI: xli556
 *	===============================================================================
 */

import java.awt.*;

public class MovingSquare extends MovingRectangle{
    public MovingSquare(){
        super(50);
    }
    public MovingSquare(int x, int y, int l, int mw, int mh, Color bc, Color fc, int pathType){
        super(x, y, l, l, mw, mh, bc, fc, pathType);
    }
    public void setWidth(int newWidth){
        width = newWidth;
        height = newWidth;
    }
    public void setHeight(int newHeight){
        width = newHeight;
        height = newHeight;
    }
}