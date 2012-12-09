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
        this.color = Color.black;
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
    
    /**
     * Sets the color of this line
     * @param color The new color
     */
    public void setColor(Color color)
    {
        this.color = color;
    }
    
    /**
     * Sets the x start coordinate of this line
     * @param x The new x start coordinate
     */
    public void setXStart(int x)
    {
        xStart = x;
    }
    
    /**
     * Sets the x end coordinate of this line
     * @param x The new x end coordinate
     */
    public void setXEnd(int x)
    {
        xEnd = x;
    }
    
    /**
     * Sets the y start coordinate of this line
     * @param y The new y start coordinate
     */
    public void setYStart(int y)
    {
        yStart = y;
    }
    
    /**
     * Sets the y end coordinate of this line
     * @param y The new y end coordinate
     */
    public void setYEnd(int y)
    {
        yEnd = y;
    }
    
    /**
     * Sets both the start and end x coordinate of this line
     * @param x The new x start and end coordinate
     */
    public void setBothX(int x)
    {
        xStart = x;
        xEnd = x;
    }
    
    /**
     * Sets both the start and end y coordinate of this line
     * @param y The new y start and end coordinate
     */
    public void setBothY(int y)
    {
        yStart = y;
        yEnd = y;
    }
}
