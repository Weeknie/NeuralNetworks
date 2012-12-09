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
    
    public DrawableConnection(Connection connection, Line line)
    {
        this.connection = connection;
        this.line = line;
    }

    @Override
    public void draw(Graphics graphics)
    {
        line.setColor(connection.getInputValue() != 0 ? Color.red : Color.black);
        line.draw(graphics);
    }
}
