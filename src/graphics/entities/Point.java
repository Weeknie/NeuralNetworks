package graphics.entities;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Drawable;

/**
 * Represents a drawable point (a line of length 0)
 * @author Maarten Slenter
 */
public class Point implements Drawable
{
    /**
     * The x coordinate of this point
     */
    int x;
    
    /**
     * The y coordinate of this point
     */
    int y;
    
    /**
     * The color of this point
     */
    Color color;
    
    public Point(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    /**
     * Draw this point
     * The point is drawn by drawing a line to the same x/y coordinates as it is drawn from
     * @param graphics The graphics object to use while drawing
     */
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(color);
        graphics.drawLine(x, y, x, y);
    }
}
