package components.basic;

/**
 * Represents one connection between two neurons, or into/from a neuron (input/output)
 * @author Maarten Slenter
 */
public class Connection 
{
    /**
     * The source neuron
     */
    protected Neuron source;
    
    /**
     * The destination neuron
     */
    protected Neuron destination;
    
    /**
     * The input value of this connection
     */
    protected double inputValue = 0;
    
    /**
     * The weight of this connection
     */
    protected double weight = 0;
    
    public Connection(double weight)
    {
        this.weight = weight;
    }
    
    /**
     * Sets the source of this connection
     * @param neuron The source neuron
     */
    void setSource(Neuron neuron)
    {
        source = neuron;
    }
    
    /**
     * Sets the destination of this connection
     * @param neuron The destination neuron
     */
    void setDestination(Neuron neuron)
    {
        destination = neuron;
    }
    
    /**
     * 
     * @return The weight of this connection
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * Sets the weight of this connection
     * @param newWeight The new weight
     */
    public void setWeight(double newWeight)
    {
        weight = newWeight;
    }
    
    /**
     * Sets the input value
     * @param newValue The new input value
     */
    public void setInputValue(double newValue)
    {
        inputValue = newValue;
    }
    
    /**
     * 
     * @return The input value
     */
    public double getInputValue()
    {
        return inputValue;
    }
    
    /**
     * 
     * @return The output value of this connection
     */
    public double getOutputValue()
    {
        return inputValue * weight;
    }
}
