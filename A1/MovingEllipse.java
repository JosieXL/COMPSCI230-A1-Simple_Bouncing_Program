/*
 *	===============================================================================
 *	MovingEllipse.java : A subclass of MovingShape.
 *	Have 3 override constructors, a draw method, a getArea method and a contains method.
 *  Name: Xiaolin Li
 *	UPI: xli556
 *	===============================================================================
 */

import java.awt.*;

public class MovingEllipse extends MovingShape{
    public MovingEllipse(){
        super();
    }
    public MovingEllipse(int size){
        super(size);
    }
    public MovingEllipse(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType){
        super(x, y, w, h, mw, mh, bc, fc, pathType);
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(fillColor);
        g2d.fillOval(x, y, width, height);
        g2d.setPaint(borderColor);
        g2d.drawOval(x, y, width, height);
        //Call selected method
        drawHandles(g);
    }
    public double getArea(){
        return Math.PI * (0.5 * this.width) * (0.5 * this.height);
    }
    public boolean contains(Point p){
        double dx, dy;
        Point EndPt = new Point(x + width, y + height);
        dx = (2 * p.x - x - EndPt.x) / (double) width;
        dy = (2 * p.y - y - EndPt.y) / (double) height;
        return dx * dx + dy * dy < 1.0;
    }
}