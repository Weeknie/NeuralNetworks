package neuralNets.lib.components.extended.networkFactories;

import java.util.ArrayList;

import neuralNets.lib.components.basic.Connection;
import neuralNets.lib.components.basic.Layer;
import neuralNets.lib.components.basic.Network;
import neuralNets.lib.components.basic.NetworkFactory;
import neuralNets.lib.components.basic.Neuron;
import neuralNets.lib.components.extended.neurons.SigmoidNeuron;

/**
 *
 * @author Maarten Slenter
 */
public class SigmoidFactory implements NetworkFactory
{
    /**
     * The number of inputs
     */
    private int inputs = 1;
    
    /**
     * The number of outputs
     */
    private int outputs = 1;
    
    /**
     * The number of hidden layers
     */
    private int hiddenLayers = 0;
    
    /**
     * The number of neurons per hidden layer
     */
    private int neuronsPerLayer = 0;
    
    /**
     * The compensation factor for all input neurons
     */
    private double inputCompFactor = 1;
    
    /**
     * The compensation factor of all neurons except input neurons
     */
    private double compFactor = 1;
    
    /**
     * The multiplication factor of all neurons
     */
    private double multFactor = 1;
    
    /**
     * Sets the topology for newly created networks
     * @param args The topology arguments
     */
    @Override
    public void setTopology(int ... args)
    {
        if(args.length != 7)
        {
            throw new IllegalArgumentException("The topology argument list does not have the correct length");
        }
        
        this.inputs = args[0];
        this.outputs = args[1];
        this.hiddenLayers = args[2];
        this.neuronsPerLayer = args[3];
        this.inputCompFactor = args[4];
        this.compFactor = args[5];
        this.multFactor = args[6];
    }

    /**
     * Creates the network
     * @return The created network
     */
    @Override
    public Network create()
    {
        return new SigmoidNetwork(inputs, outputs, hiddenLayers, neuronsPerLayer, inputCompFactor, compFactor, multFactor);
    }
    
    /**
     * A sigmoid neural network
     * Consists of:
     *  - an input layer with a neuron for each input, one input per neuron
     *  - an output layer with a neuron for each output, one output per neuron
     *  - an x amount of hidden layers, with all neurons completely interconnected
     * 
     * The output of each neuron is calculated with the following formula: multFactor/(1 + exp(-activation / compFactor) - multFactor / 2
     * The output follows an s curve from -multFactor / 2 to multFactor / 2.
     * The weights of each connection are randomly chosen between -1 and 1
     * @author Maarten Slenter
     */
    private class SigmoidNetwork extends Network
    {
        SigmoidNetwork(int inputs, int outputs, int hiddenLayers, int neuronsPerLayer, double inputCompFactor, double compFactor, double multFactor)
        {
            /**
             * Construct the input layer, one connection per neuron
             */
            inputLayer = new Layer();
            for(int i = 1; i <= inputs; i++)
            {
                Neuron neuron = new SigmoidNeuron(inputCompFactor, multFactor);
                inputLayer.addNeuron(neuron);

                Connection connection = new Connection(1);
                neuron.addInput(connection);
                inputConnections.add(connection);
            }

            /**
             * Initialize the previous neurons list, which will keep track of where all neurons in the previous layer were, 
             * to easily build all necessary connections
             */
            ArrayList<Neuron> previousNeurons = inputLayer.getNeurons();

            /**
             * Build the hidden layers and fully interconnect them with each previous layer
             */
            for(int j = 1; j <= hiddenLayers; j++)
            {
                ArrayList<Neuron> newNeurons = new ArrayList<Neuron>();

                Layer layer = new Layer();
                for(int i = 1; i <= neuronsPerLayer; i++)
                {
                    Neuron neuron = new SigmoidNeuron(compFactor, multFactor);
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

            /**
             * Build the output layer and fully interconnect it with the last hidden layer.
             * Also add one connection per output neuron, with weight 1 (to ensure the outputs aren't distorted by the final connection)
             */
            outputLayer = new Layer();
            for(int i = 1; i <= outputs; i++)
            {
                Neuron neuron = new SigmoidNeuron(compFactor, multFactor);
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
