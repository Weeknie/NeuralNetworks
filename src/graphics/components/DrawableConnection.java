package graphics.components;

import java.awt.Color;
import java.awt.Graphics;

import components.basic.Connection;
import graphics.Drawable;
import graphics.entities.Line;

/**
 * Represents one drawable connection
 * @author Maarten Slenter
 */
public class DrawableConnection implements Drawable
{
    /**
     * The connection this drawable represents
     */
    Connection connection;
    
    /**
     * The line that represents this connection
     */
    Line line;
    
    /**
     * 
     * @param connection The connection that this drawable connection represents
     * @param line The line that represents this drawable connection
     */
    public DrawableConnection(Connection connection, Line line)
    {
        this.connection = connection;
        this.line = line;
    }

    /**
     * Draws the connection
     * @param graphics The graphics object to use while drawing
     */
    @Override
    public void draw(Graphics graphics)
    {
        line.setColor(connection.getInputValue() != 0 ? Color.red : Color.black);
        line.draw(graphics);
    }
}
