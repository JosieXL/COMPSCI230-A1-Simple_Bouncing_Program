/*
 *	===============================================================================
 *	MovingBounsShape.java : A subclass of MovingSquare and also is a subclass of MovingShape.
 *	Have 2 override constructors, a override draw method.
 *  The getArea method and contains method are same method used in the MovingEllipse class.
 *  Name: Xiaolin Li
 *	UPI: xli556
 *	===============================================================================
 */

import java.awt.*;

public class MovingBounsShape extends MovingShape{
    public MovingBounsShape(){
        super();
    }
    public MovingBounsShape(int size){
        super(size);
    }
    public MovingBounsShape(int x, int y, int w, int h, int aw, int ah, int mw, int mh, Color bc, Color fc, int pathType){
        super(x, y, w, h, aw, ah, mw, mh, bc, fc, pathType);
    }
    public double getArea(){
        return this.width * this.height - this.arcWidth * this.arcHeight * (4 - Math.PI);
    }
    //To be easy to be calculated, the contains() for MovingBounsShape (roundRectangle) is the same as MovingRectangle one.
    public boolean contains(Point p){
        if(x <= p.x && p.x <= x+width && y <= p.y && p.y <= y+height){
            return true;
        }
        else{
            return false;
        }
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // the round rectangle
        g2d.setPaint(fillColor);
        g2d.fillRoundRect(this.x, this.y, this.width, this.height, this.arcWidth, this.arcHeight);
        g2d.setPaint(borderColor);
        g2d.drawRoundRect(this.x, this.y, this.width, this.height, this.arcWidth, this.arcHeight);
        // two lines inside the round rectangle to cut the round rectangle into 4 pieces with same area
        g2d.setPaint(borderColor);
        g2d.drawLine(this.x, (int)(this.y + 0.5 * this.height), this.x + this.width, (int)(this.y + 0.5 * this.height));
        g2d.drawLine((int)(this.x + 0.8 * this.width), this.y, (int)(this.x + 0.8 * this.width), this.y + this.height);
        // add a text in the shape
        g2d.setFont(new Font(null, Font.BOLD, this.width/10));
        g2d.drawString("Java is cool!", (int)(this.x+0.1*this.width), (int)(this.y+0.25*this.height));
        //Call selected method
        drawHandles(g);
    }
}