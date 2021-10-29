/*
 *	===============================================================================
 *	MovingQuadCircles.java : A subclass of MovingCircle and also is a subclass of MovingShape.
 *	Have 2 override constructors, a override draw method.
 *  The getArea method and contains method are same method used in the MovingEllipse class.
 *  Name: Xiaolin Li
 *	UPI: xli556
 *	===============================================================================
 */

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class MovingQuadCircles extends MovingCircle {
    public MovingQuadCircles() {
        super();
    }

    public MovingQuadCircles(int x, int y, int l, int mw, int mh, Color bc, Color fc, int pathType) {
        super(x, y, l, mw, mh, bc, fc, pathType);
    }

    public void draw(Graphics g) {
        //create Ellipse2D objects for 4 circles
        Ellipse2D cTop = new Ellipse2D.Double(x+0.25*width, y, width*0.5, height*0.5);
        Ellipse2D cLeft = new Ellipse2D.Double(x, y+0.25*height, width*0.5, height*0.5);
        Ellipse2D cBottom = new Ellipse2D.Double(x+0.25*width, y+0.5*height, width*0.5, height*0.5);
        Ellipse2D cRight = new Ellipse2D.Double(x+0.5*width, y+0.25*height, width*0.5, height*0.5);
        // draw circles
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(borderColor);
        g2d.draw(cTop);
        g2d.setPaint(borderColor);
        g2d.draw(cLeft);
        g2d.setPaint(borderColor);
        g2d.draw(cBottom);
        g2d.setPaint(borderColor);
        g2d.draw(cRight);
        //create Area objects to calculate the area of the circles
        Area a1 = new Area(cTop);
        Area a2 = new Area(cLeft);
        Area a3 = new Area(cBottom);
        Area a4 = new Area(cRight);
        //fill the intersection between top and left circles
        g2d.setPaint(fillColor);
        a1.intersect(a2);
        g2d.fill(a1);//a1 is the intersection of top and left circle or a1 = a1 intersects with a2
        //fill the intersection between left and bottom circles
        g2d.setPaint(fillColor);
        a2.intersect(a3);
        g2d.fill(a2);
        //fill the intersection between bottom and right circles
        g2d.setPaint(fillColor);
        a3.intersect(a4);
        g2d.fill(a3);
        //fill the intersection between right and top circles
        Area a5 = new Area(cTop);
        //need to create another top circle area for color the intersection of top and right circles.
        g2d.setPaint(fillColor);
        a4.intersect(a5);
        g2d.fill(a4);
        //Call selected method
        drawHandles(g);
    }
}


