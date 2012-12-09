package graphics;

import java.awt.*;

/**
 * Represents one drawable object
 * @author Maarten Slenter
 */
public interface Drawable 
{
    /**
     * Draws this object
     * @param graphics The graphics object to use for drawing
     */
    public void draw(Graphics graphics);
}
