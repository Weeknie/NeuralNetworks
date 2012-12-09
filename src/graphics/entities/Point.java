package graphics.entities;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Drawable;

/**
 * 
 * @author Maarten Slenter
 */
public class Point implements Drawable
{
    int x;
    int y;
    Color color;
    
    public Point(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    public void draw(Graphics graphics)
    {
        graphics.setColor(color);
        graphics.drawLine(x, y, x, y);
    }
}
