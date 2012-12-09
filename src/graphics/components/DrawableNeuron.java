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
    
    public DrawableNeuron(Neuron neuron, Circle circle)
    {
        this.neuron = neuron;
        this.circle = circle;
    }
    
    @Override
    public void draw(Graphics graphics)
    {
        circle.draw(graphics);
    }
}
