package neuralNets.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

import neuralNets.lib.graphics.Drawable;

/**
 * Graphics renderer. Keeps a list of all the objects that need to be rendered. 
 * @author Maarten Slenter
 */
public class Renderer extends Canvas
{
    /**
     * The list of drawables to draw
     */
    private ArrayList<Drawable> drawList = new ArrayList<Drawable>();
    
    /**
     * The list of drawables to draw only once
     */
    private ArrayList<Drawable> singleDrawList = new ArrayList<Drawable>();
    
    public Renderer()
    {
    }
    
    /**
     * Paints all the drawables
     * @param graphics The graphics object to use for drawing
     */
    @Override
    public synchronized void paint(Graphics graphics) 
    {
        for(Drawable drawable : drawList)
        {
            drawable.draw(graphics);
        }
        
        for(Drawable drawable : singleDrawList)
        {
            drawable.draw(graphics);
        }
        singleDrawList.clear();
    }
    
    /**
     * Adds a drawable to the drawlist
     * @param drawable The drawable to add
     */
    public synchronized void addDrawable(Drawable drawable)
    {
        drawList.add(drawable);
    }
    
    /**
     * Draws the provided drawable once, then removes it
     * @param drawable The drawable to draw once
     */
    public synchronized void drawOnce(Drawable drawable)
    {
        singleDrawList.add(drawable);
    }
    
    /**
     * Clears all drawables
     */
    public synchronized void clearDrawables()
    {
        drawList.clear();
        singleDrawList.clear();
    }
    
    /**
     * Removes a drawable from the draw list
     * @param drawable The drawable to remove
     */
    public synchronized void removeDrawable(Drawable drawable)
    {
        drawList.remove(drawable);
    }
}
