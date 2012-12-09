package components.extended.neurons;

import components.basic.Connection;
import components.basic.Neuron;

/**
 *
 * @author Maarten Slenter
 */
public class SigmoidNeuron extends Neuron
{
    /**
     * The compensation factor to use in the power of e
     */
    double compensationFactor = 1;
    
    public SigmoidNeuron(double compensationFactor)
    {
        this.compensationFactor = compensationFactor;
    }
    
    /**
     * Updates this neuron
     */
    @Override
    public void update()
    {
        double totalValue = 0;
        
        for(Connection connection : inputs)
        {
            totalValue += connection.getOutputValue();
        }
        
        double max = 10;
        double outputValue = max/(1 + Math.exp(-totalValue)) - (max/2);
        
        for(Connection connection : outputs)
        {
            connection.setInputValue(outputValue);
        }
    }
    
}
