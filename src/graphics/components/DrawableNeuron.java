package graphics.components;

import java.awt.Graphics;

import components.basic.Neuron;
import graphics.Drawable;
import graphics.entities.Circle;

/**
 * Represents one drawable neuron
 * @author Maarten Slenter
 */
public class DrawableNeuron implements Drawable
{
    /**
     * The neuron this drawable represents
     */
    Neuron neuron;
    
    /**
     * The circle that represents this neuron
     */
    Circle circle;
    
    /**
     * 
     * @param neuron The neuron that this drawable neuron represents
     * @param circle The circle that represents this drawable neuron
     */
    public DrawableNeuron(Neuron neuron, Circle circle)
    {
        this.neuron = neuron;
        this.circle = circle;
    }
    
    /**
     * Draws the neuron
     * @param graphics The graphics object to use while drawing
     */
    @Override
    public void draw(Graphics graphics)
    {
        circle.draw(graphics);
    }
}
