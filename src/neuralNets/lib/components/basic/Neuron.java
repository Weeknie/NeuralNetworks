package neuralNets.lib.components.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents one neuron in the neural network
 * @author Maarten Slenter
 */
public abstract class Neuron 
{
    /**
     * The inputs of this neuron
     */
    protected ArrayList<Connection> inputs = new ArrayList<Connection>();
    
    /**
     * The outputs of this neuron
     */
    protected ArrayList<Connection> outputs = new ArrayList<Connection>();
    
    /**
     * The output value of this neuron
     */
    protected double outputValue = 0;
    
    /**
     * Adds an input to this neuron
     * @param connection The input connection
     */
    public void addInput(Connection connection)
    {
        inputs.add(connection);
        connection.setDestination(this);
    }
    
    /**
     * Adds an output to this neuron
     * @param connection The output connection
     */
    public void addOutput(Connection connection)
    {
        outputs.add(connection);
        connection.setSource(this);
    }
    
    /**
     * 
     * @return The list of inputs of this neuron
     */
    public ArrayList<Connection> getInputs()
    {
        return inputs;
    }
    
    /**
     * 
     * @return The list of outputs of this neuron
     */
    public ArrayList<Connection> getOutputs()
    {
        return outputs;
    }
    
    /**
     * 
     * @return The output value of this neuron
     */
    public double getOutputValue()
    {
        return outputValue;
    }
    
    /**
     * Updates the outputs of this neuron
     */
    public abstract void update();
    
    /**
     * 
     * @return The adjustable of this neuron
     */
    public abstract List<Double> getAdjustables();
    
    /**
     * Sets the adjustable for this neuron
     * @param adjustables The new numbers
     * @return The number of adjustables that have been consumed
     */
    public abstract int setAdjustables(List<Double> adjustables);
}
