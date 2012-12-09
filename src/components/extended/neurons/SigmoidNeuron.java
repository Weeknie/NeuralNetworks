package components.extended.neurons;

import components.basic.Connection;
import components.basic.Neuron;

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
        
        double outputValue = multFactor/(1 + Math.exp(-totalValue / compFactor)) - (multFactor/2);
        
        for(Connection connection : outputs)
        {
            connection.setInputValue(outputValue);
        }
    }
    
}
