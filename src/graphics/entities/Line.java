package graphics.entities;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Drawable;

/**
 *
 * @author Maarten Slenter
 */
public class Line implements Drawable
{
    /**
     * The start x coordinate
     */
    int xStart;
    
    /**
     * The end x coordinate
     */
    int xEnd;
    
    /**
     * The start y coordinate
     */
    int yStart;
    
    /**
     * The end y coordinate
     */    
    int yEnd;
    
    /**
     * The color of this line
     */
    Color color;
    
    public Line(int xStart, int yStart, int xEnd, int yEnd)
    {
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
    }
    
    public Line(int xStart, int yStart, int xEnd, int yEnd, Color color)
    {
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.color = color;
    }
    
    /**
     * Draws this line
     * @param graphics The graphics object to use while drawing 
     */
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(color);
        graphics.drawLine(xStart, yStart, xEnd, yEnd);
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }
}
