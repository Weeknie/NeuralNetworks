package neuralNets.lib.components.extended.networkFactories;

import java.util.ArrayList;

import neuralNets.lib.components.basic.Connection;
import neuralNets.lib.components.basic.Layer;
import neuralNets.lib.components.basic.Network;
import neuralNets.lib.components.basic.NetworkFactory;
import neuralNets.lib.components.basic.Neuron;
import neuralNets.lib.components.extended.neurons.DefaultNeuron;

/**
 *
 * @author Maarten Slenter
 */
public class DefaultFactory implements NetworkFactory
{
    /**
     * The number of inputs
     */
    private int inputs;
    
    /**
     * The number of outputs
     */
    private int outputs;
    
    /**
     * The number of hidden layers
     */
    private int hiddenLayers;
    
    /**
     * The number of neurons per hidden layer
     */
    private int neuronsPerLayer;
    
    /**
     * The threshold of all input neurons
     */
    private int maxInputThreshold;
    
    /**
     * The threshold of all neurons except input neurons
     */
    private int maxOtherThreshold;
    
    /**
     * Sets the topology for newly created networks
     * @param args The topology arguments
     */
    @Override
    public void setTopology(int ... args)
    {
        if(args.length != 6)
        {
            throw new IllegalArgumentException("The topology argument list does not have the correct length");
        }
        
        this.inputs = args[0];
        this.outputs = args[1];
        this.hiddenLayers = args[2];
        this.neuronsPerLayer = args[3];
        this.maxInputThreshold = args[4];
        this.maxOtherThreshold = args[5];
    }
    
    /**
     * Create the network
     * @return The created network
     */
    @Override
    public Network create()
    {
        return new DefaultNetwork(inputs, outputs, hiddenLayers, neuronsPerLayer, maxInputThreshold, maxOtherThreshold);
    }
    
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
    private class DefaultNetwork extends Network
    {
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

                adjustableConnections.add(connection);
            }

            ArrayList<Neuron> previousNeurons = inputLayer.getNeurons();

            for(int j = 1; j <= hiddenLayers; j++)
            {
                ArrayList<Neuron> newNeurons = new ArrayList<Neuron>();

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

                        adjustableConnections.add(connection);
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
                    adjustableConnections.add(connection);
                }

                Connection connection = new Connection(1);
                neuron.addOutput(connection);
                outputConnections.add(connection);
            }
        }
    }
}
