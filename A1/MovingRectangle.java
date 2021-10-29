/*
 *	===============================================================================
 *	MovingRectangle.java : A subclass of MovingShape.
 *	Have 3 override constructors, a draw method, a getArea method and a contains method.
 *  Name: Xiaolin Li
 *	UPI: xli556
 *	===============================================================================
 */

import java.awt.*;

public class MovingRectangle extends MovingShape{
    public MovingRectangle(){
        super();
    }
    public MovingRectangle(int size){
        super(size);
    }
    public MovingRectangle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType){
        super(x, y, w, h, mw, mh, bc, fc, pathType);
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(fillColor);
        g2d.fillRect(x, y, width, height);
        g2d.setPaint(borderColor);
        g2d.drawRect(x, y, width, height);
        //Call selected method
        drawHandles(g);
    }
    public double getArea(){
        return this.width * this.height;
    }
    public boolean contains(Point p){
        if(x <= p.x && p.x <= x+width && y <= p.y && p.y <= y+height){
            return true;
        }
        else{
            return false;
        }
    }
}