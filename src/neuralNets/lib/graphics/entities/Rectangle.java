package neuralNets.lib.graphics.entities;

import java.awt.Color;
import java.awt.Graphics;

import neuralNets.lib.graphics.Drawable;

/**
 *
 * @author Maarten Slenter
 */
public class Rectangle implements Drawable
{
    /**
     * The x coordinate of the center of the square
     */
    int x;
    
    /**
     * The y coordinate of the center of the square
     */
    int y;
    
    /**
     * The width of this rectangle
     */
    int width;
    
    /**
     * The height of this rectangle
     */
    int height;
    
    /**
     * The color of this circle
     */
    Color color = Color.black;
    
    /**
     * Indicates whether this circle should be filled or not
     */
    boolean fill;
    
    public Rectangle(int x, int y, int width, int height)
    {
        this(x, y, width, height, Color.black, false);
    }
    
    public Rectangle(int x, int y, int width, int height, Color color)
    {
        this(x, y, width, height, color, false);
    }
    
    public Rectangle(int x, int y, int width, int height, boolean fill)
    {
        this(x, y, width, height, Color.black, fill);
    }
    
    public Rectangle(int x, int y, int width, int height, Color color, boolean fill)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.fill = fill;
    }

    /**
     * Draws this circle
     * @param graphics The graphics object to use while drawing
     */
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(color);
        if(fill)
        {
            graphics.fillRect(x, y, width, height);
        }
        graphics.drawRect(x, y, width, height);
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
     * Sets whether this circle should be filled or not
     * @param fill True if the circle should be filled, false otherwise
     */
    public void setFill(boolean fill)
    {
        this.fill = fill;
    }
}
