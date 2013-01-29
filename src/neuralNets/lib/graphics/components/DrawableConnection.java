package neuralNets.lib.graphics.components;

import java.awt.Color;
import java.awt.Graphics;

import neuralNets.lib.components.basic.Connection;
import neuralNets.lib.graphics.Drawable;
import neuralNets.lib.graphics.entities.Line;

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
     * The compensation factor for the color of this connection
     */
    double colorCompensation;
    
    /**
     * 
     * @param connection The connection that this drawable connection represents
     * @param line The line that represents this drawable connection
     */
    public DrawableConnection(Connection connection, Line line)
    {
        this(connection, line, 1);
    }
    
    /**
     * 
     * @param connection The connection that this drawable connection represents
     * @param line The line that represents this drawable connection
     * @param colorCompensation The compensation factor for the color of this connection
     */
    public DrawableConnection(Connection connection, Line line, double colorCompensation)
    {
        this.connection = connection;
        this.line = line;
        this.colorCompensation = colorCompensation;
    }

    /**
     * Draws the connection
     * @param graphics The graphics object to use while drawing
     */
    @Override
    public void draw(Graphics graphics)
    {
        double outputValue = connection.getOutputValue();
        line.setColor(new Color(clip((int) (outputValue/colorCompensation * 255)), 0, clip((int) (-outputValue/colorCompensation * 255))));
        line.draw(graphics);
    }
    
    /**
     * Clips the input value to a value between 0 and 255, inclusive
     * @param value The value to clip
     * @return The clipped value 
     */
    private int clip(int value)
    {
        return value < 0 ? 0 : (value > 255 ? 255 : value);
    }
}
