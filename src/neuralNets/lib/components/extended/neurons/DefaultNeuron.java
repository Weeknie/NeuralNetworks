package neuralNets.lib.components.extended.neurons;

import java.util.ArrayList;
import java.util.List;

import neuralNets.lib.components.basic.Connection;
import neuralNets.lib.components.basic.Neuron;

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
        
        outputValue = totalActivation >= threshold ? 1 : 0;
        
        for(Connection connection : outputs)
        {
            connection.setInputValue(outputValue);
        }
    }

    /**
     * 
     * @return The adjustables of this neuron
     */
    @Override
    public ArrayList<Double> getAdjustables()
    {
        ArrayList<Double> array = new ArrayList<Double>();
        array.add(threshold);
        return array;
    }

    /**
     * Sets the adjustables of this neuron
     * @param adjustables The new numbers
     * @return 1, to indicate 1 adjustable has been consumed
     */
    @Override
    public int setAdjustables(List<Double> adjustables)
    {
        threshold = adjustables.get(0);
        return 1;
    }
}