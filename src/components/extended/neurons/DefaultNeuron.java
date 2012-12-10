package components.extended.neurons;

import components.basic.Connection;
import components.basic.Neuron;

/**
 * The default implementation of a neuron
 * @author Maarten Slenter
 */
public class DefaultNeuron extends Neuron
{
    /**
     * The threshold of this neuron
     */
    double threshold;
    
    /**
     * Initializes this neuron with the provided threshold
     * @param threshold The threshold of this neuron
     */
    public DefaultNeuron(double threshold)
    {
        this.threshold = threshold;
    }
    
    /**
     * Updates the outputs of this neuron
     */
    @Override
    public void update()
    {
        double totalActivation = 0;
        
        for(Connection connection : inputs)
        {
            totalActivation += connection.getOutputValue();
        }
        
        if(totalActivation >= threshold)
        {
            for(Connection connection : outputs)
            {
                connection.setInputValue(1);
            }
        }
    }
}