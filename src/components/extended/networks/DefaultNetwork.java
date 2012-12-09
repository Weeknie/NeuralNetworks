package components.extended.networks;

import java.util.ArrayList;

import components.basic.Connection;
import components.basic.Layer;
import components.basic.Network;
import components.basic.Neuron;
import components.extended.neurons.DefaultNeuron;

/**
 * The default neural network.
 * Consists of:
 *  - an input layer with a neuron for each input, one input per neuron
 *  - an output layer with a neuron for each output, one output per neuron
 *  - an x amount of hidden layers, with all neurons completely interconnected
 * 
 * Each neuron will output a 1 if its threshold is crossed.
 * The weights of each connection are randomly chosen between -1 and 1;
 * @author Maarten Slenter
 */
public class DefaultNetwork extends Network
{
    /**
     * Constructs the network
     * @param inputs
     * @param outputs
     * @param hiddenLayers
     * @param neuronsPerLayer
     */
    public DefaultNetwork(int inputs, int outputs, int hiddenLayers, int neuronsPerLayer, int maxInputThreshold, int maxOtherThreshold)
    {
        inputLayer = new Layer();
        for(int i = 1; i <= inputs; i++)
        {
            Neuron neuron = new DefaultNeuron(generateThreshold(maxInputThreshold));
            inputLayer.addNeuron(neuron);
            
            Connection connection = new Connection(generateWeight());
            neuron.addInput(connection);
            inputConnections.add(connection);
        }
        
        ArrayList<Neuron> previousNeurons = inputLayer.getNeurons();
        
        for(int j = 1; j <= hiddenLayers; j++)
        {
            ArrayList<Neuron> newNeurons = new ArrayList<>();
            
            Layer layer = new Layer();
            for(int i = 1; i <= neuronsPerLayer; i++)
            {
                Neuron neuron = new DefaultNeuron(generateThreshold(neuronsPerLayer));
                layer.addNeuron(neuron);
                newNeurons.add(neuron);
                
                for(Neuron previousNeuron : previousNeurons)
                {
                    Connection connection = new Connection(generateWeight());
                    previousNeuron.addOutput(connection);
                    neuron.addInput(connection);
                }
            }
            this.hiddenLayers.add(layer);
            previousNeurons = newNeurons;
        }
        
        outputLayer = new Layer();
        for(int i = 1; i <= outputs; i++)
        {
            Neuron neuron = new DefaultNeuron(generateThreshold(maxOtherThreshold));
            outputLayer.addNeuron(neuron);
            
            for(Neuron previousNeuron : previousNeurons)
            {
                Connection connection = new Connection(generateWeight());
                previousNeuron.addOutput(connection);
                neuron.addInput(connection);
            }
            
            Connection connection = new Connection(1);
            neuron.addOutput(connection);
            outputConnections.add(connection);
        }
    }
}
