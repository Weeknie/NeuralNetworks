package neuralNets.lib.components.extended.neurons;

import java.util.ArrayList;
import java.util.List;

import neuralNets.lib.components.basic.Connection;
import neuralNets.lib.components.basic.Neuron;

/**
 * A sigmoid neuron, which output follows a sigmoid curve.
 * The used formula is: multFactor / (1 + exp(-activation / compFactor)) - multFactor / 2.
 * Its output ranges from -multFactor / 2 to multFactor / 2
 * @author Maarten Slenter
 */
public class SigmoidNeuron extends Neuron
{
    /**
     * The compensation factor
     */
    double compFactor = 1;
    
    /**
     * The multiplication factor
     */
    double multFactor = 1;
    
    /**
     * 
     * @param compFactor The initial compensation factor for this neuron
     * @param multFactor The initial multiplication factor for this neuron
     */
    public SigmoidNeuron(double compFactor, double multFactor)
    {
        this.compFactor = compFactor;
        this.multFactor = multFactor;
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
        
        outputValue = multFactor/(1 + Math.exp(-totalValue / compFactor)) - (multFactor/2);
        
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
        array.add(compFactor);
        array.add(multFactor);
        return array;
    }

    /**
     * Sets the adjustables of this neuron
     * @param adjustables The adjustables
     * @return 2, to indicate 2 adjustables have been consumed
     */
    @Override
    public int setAdjustables(List<Double> adjustables)
    {
        compFactor = adjustables.get(0);
        multFactor = adjustables.get(1);
        return 2;
    }
}
