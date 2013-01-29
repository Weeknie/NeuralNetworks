package neuralNets.lib.components.basic;

import java.util.ArrayList;

/**
 * Represents one layer of the neural network
 * @author Maarten Slenter
 */
public class Layer 
{
    /**
     * The list of neurons in this layer
     */
    private ArrayList<Neuron> neurons = new ArrayList<Neuron>();
    
    /**
     * Adds a neuron to this layer
     * @param neuron The neuron to add
     */
    public void addNeuron(Neuron neuron)
    {
        neurons.add(neuron);
    }
    
    /**
     * Updates the neurons in this layer
     */
    public void update()
    {
        for(Neuron neuron : neurons)
        {
            neuron.update();
        }
    }
    
    /**
     * 
     * @return The list with all neurons in this layer
     */
    public ArrayList<Neuron> getNeurons()
    {
        return neurons;
    }
    
    /**
     * 
     * @return The size of this layer (the amount of neurons in it)
     */
    public int getSize()
    {
        return neurons.size();
    }
}
