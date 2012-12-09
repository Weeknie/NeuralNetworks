package graphics.entities;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Drawable;

/**
 *
 * @author Maarten Slenter
 */
public class Circle implements Drawable
{
    /**
     * The x coordinate of the center of the circle
     */
    double x;
    
    /**
     * The y coordinate of the center of the circle
     */
    double y;
    
    /**
     * The radius of this circle
     */
    int radius;
    
    /**
     * The color of this circle
     */
    Color color = Color.black;
    
    public Circle(double x, double y, int radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * Draws this circle
     * @param graphics The graphics object to use while drawing
     */
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(color);
        graphics.drawOval((int) x - radius, (int) y - radius, radius * 2, radius * 2);
    }
    
    /**
     * Sets the coordinates of this circle
     * @param x The new x coordinate
     * @param y The new y coordinate
     */
    public void setCoordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the color of this circle
     * @param color The new color
     */
    public void setColor(Color color)
    {
        this.color = color;
    }
    
    /**
     * 
     * @return The x coordinate of this circle
     */
    public double getX()
    {
        return x;
    }
    
    /**
     * 
     * @return The y coordinate of this circle
     */
    public double getY()
    {
        return y;
    }
    
    /**
     * Adds a value to the x coordinate
     * @param increase The amount to add to the x coordinate (can be negative)
     */
    public void increaseX(double increase)
    {
        x += increase;
    }
    
    /**
     * Adds a value to the y coordinate
     * @param increase The amount to add to the y coordinate (can be negative)
     */
    public void increaseY(double increase)
    {
        y += increase;
    }
}
