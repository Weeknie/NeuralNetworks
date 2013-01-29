package neuralNets.lib.graphics.components;

import java.awt.Color;
import java.awt.Graphics;

import neuralNets.lib.components.basic.Neuron;
import neuralNets.lib.graphics.Drawable;
import neuralNets.lib.graphics.entities.Circle;

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
     * The compensation factor for the color of this neuron
     */
    double colorCompensation;
    
    /**
     * 
     * @param neuron The neuron that this drawable neuron represents
     * @param circle The circle that represents this drawable neuron
     */
    public DrawableNeuron(Neuron neuron, Circle circle)
    {
        this(neuron, circle, 1);
    }
    
    /**
     * 
     * @param neuron The neuron that this drawable neuron represents
     * @param circle The circle that represents this drawable neuron
     * @param colorCompensation The color compensation factor for this neuron
     */
    public DrawableNeuron(Neuron neuron, Circle circle, double colorCompensation)
    {
        this.neuron = neuron;
        this.circle = circle;
        this.colorCompensation = colorCompensation;
    }
    
    /**
     * Draws the neuron
     * @param graphics The graphics object to use while drawing
     */
    @Override
    public void draw(Graphics graphics)
    {
        double outputValue = neuron.getOutputValue();
        circle.setColor(new Color(clip((int) (outputValue/colorCompensation * 255)), 0, clip((int) (-outputValue/colorCompensation * 255))));
        circle.draw(graphics);
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
