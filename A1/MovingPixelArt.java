/*
 *	===============================================================================
 *	MovingPixelArt.java : A subclass of MovingSquare and also is a subclass of MovingShape.
 *	Have 2 override constructors, a override draw method.
 *  The getArea method and contains method are same method used in the MovingEllipse class.
 *  Name: Xiaolin Li
 *	UPI: xli556
 *	===============================================================================
 */

import java.awt.*;

public class MovingPixelArt extends MovingSquare{
    public MovingPixelArt(){
        super();
    }
    public MovingPixelArt(int x, int y, int l, int mw, int mh, Color bc, Color fc, int pathType){
        super(x, y, l, mw, mh, bc, fc, pathType);
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        String[] values = {"1111111111", "1111551111", "1111551111", "1115555111", "1115555111",
                "1155555511", "1555555551", "1111881111", "1111881111", "1111111111"};
        Color[] colours = {Color.black, Color.white, Color.red, Color.orange, Color.yellow,
                Color.green, Color.cyan, Color.blue, Color.gray, Color.DARK_GRAY};
        int w = this.width/10;
        int h = this.height/10;
        int dx = this.x;
        int dy = this.y;
        for(int i=0; i<values.length; i++){
            for(int j=0; j<values[i].length(); j++) {
                int index = Character.getNumericValue(values[i].charAt(j));
                g2d.setPaint(colours[index]);
                g2d.fillRect(dx, dy, w, h);
                dx += w;
            }
            dx=this.x;
            dy += h;
        }
        //Call selected method
        drawHandles(g);
    }
}