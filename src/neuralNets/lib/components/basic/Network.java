package neuralNets.lib.components.basic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents one neural network
 * @author Maarten Slenter
 */
public abstract class Network
{
    /**
     * The input layer of this network
     */
    protected Layer inputLayer;
    
    /**
     * The hidden layers of this network
     */
    protected ArrayList<Layer> hiddenLayers = new ArrayList<Layer>();
    
    /**
     * The output layer of this network
     */
    protected Layer outputLayer;
    
    /**
     * The connections of the inputs
     */
    protected ArrayList<Connection> inputConnections = new ArrayList<Connection>();
    
    /**
     * The connections of the outputs
     */
    protected ArrayList<Connection> outputConnections = new ArrayList<Connection>();
    
    /**
     * The list of connections which can be adjusted during learning
     */
    protected ArrayList<Connection> adjustableConnections = new ArrayList<Connection>();
    
    /**
     * The random number generator for generating new weights
     */
    private Random random = new Random();
    
    /**
     * Sets the inputs of the network
     * @param inputs The inputs of this network
     */
    public void setInputs(double ... inputs)
    {
        if(inputs.length != inputConnections.size())
        {
            return;
        }
        
        for(int i = 0; i <= inputs.length - 1; i++)
        {
            inputConnections.get(i).setInputValue(inputs[i]);
        }
    }

    /**
     * 
     * @return The outputs of this network
     */
    public double[] getOutputs()
    {
        int numOutputs = outputConnections.size();
        double[] array = new double[numOutputs];
        for(int i = 0; i <= numOutputs - 1; i++)
        {
            array[i] = outputConnections.get(i).getOutputValue();
        }
        
        return array;
    }
    
    /**
     * Returns a list of all layers, from top to bottom.
     * @return The list of layers
     */
    public ArrayList<Layer> getLayers()
    {
       ArrayList<Layer> list = new ArrayList<Layer>();
       
       list.add(inputLayer);
       list.addAll(hiddenLayers);
       list.add(outputLayer);
       
       return list;
    }
    
    /**
     * 
     * @return A list with the weights of all adjustable connections in this network
     */
    public ArrayList<Double> getAdjustables()
    {
        ArrayList<Double> adjustables = new ArrayList<Double>();
        
        for(Connection connection : adjustableConnections)
        {
            adjustables.add(connection.getWeight());
        }
        
        ArrayList<Neuron> neurons = new ArrayList<Neuron>();
        neurons.addAll(inputLayer.getNeurons());
        for(Layer layer : hiddenLayers)
        {
            neurons.addAll(layer.getNeurons());
        }
        neurons.addAll(outputLayer.getNeurons());
        
        for(Neuron neuron : neurons)
        {
            adjustables.addAll(neuron.getAdjustables());
        }
        
        return adjustables;
    }
    
    /**
     * Sets the adjustables of this network to the corresponding values in the supplied list
     * @param adjustables The new list of weights
     */
    public void setAdjustables(ArrayList<Double> adjustables)
    {
        ArrayList<Neuron> neurons = new ArrayList<Neuron>();
        neurons.addAll(inputLayer.getNeurons());
        for(Layer layer : hiddenLayers)
        {
            neurons.addAll(layer.getNeurons());
        }
        neurons.addAll(outputLayer.getNeurons());
        
        int i = 0;
        for(; i <= adjustables.size() - 1;)
        {
            if(i <= adjustableConnections.size() - 1)
            {   
                adjustableConnections.get(i).setWeight(adjustables.get(i));
                i++;
            }
            else
            {
                break;
            }
        }
        
        for(Neuron neuron : neurons)
        {
            i += neuron.setAdjustables(adjustables.subList(i, adjustables.size()));
        }
    }
    
    /**
     * Updates the network, calculating the new outputs
     */
    public void update()
    {
        inputLayer.update();
        
        for(Layer hiddenLayer : hiddenLayers)
        {
            hiddenLayer.update();
        }
        
        outputLayer.update();
    }
    
    /**
     * Generates a random weight between -1 and 1
     * @return The generated weight
     */
    protected double generateWeight()
    {
        return generateDouble();
    }
    
    /**
     * Generates a random threshold between 0 and maxThreshold
     * @param maxThreshold The maximum threshold
     * @return The generated threshold
     */
    protected double generateThreshold(int maxThreshold)
    {
        return random.nextInt(maxThreshold) * generateDouble();
    }
    
    /**
     * Generates a random threshold between 0 and 1
     * @return The generated threshold
     */
    protected double generateThreshold()
    {
        return random.nextDouble();
    }
    
    /**
     * Generates a double between -1 and 1
     * @return The generated double
     */
    private double generateDouble()
    {
        return random.nextDouble() * (random.nextBoolean() ? 1 : -1);
    }
}
