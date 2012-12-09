package components.basic;

import java.util.ArrayList;

/**
 * Represents one neuron in the neural network
 * @author Maarten Slenter
 */
public abstract class Neuron 
{
    /**
     * The inputs of this neuron
     */
    protected ArrayList<Connection> inputs = new ArrayList<>();
    
    /**
     * The outputs of this neuron
     */
    protected ArrayList<Connection> outputs = new ArrayList<>();
    
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
     * Updates the outputs of this neuron
     */
    public abstract void update();
}
