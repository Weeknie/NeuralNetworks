package components.extended.networks;

import java.util.ArrayList;

import components.basic.Connection;
import components.basic.Layer;
import components.basic.Network;
import components.basic.Neuron;
import components.extended.neurons.SigmoidNeuron;

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
public class SigmoidNetwork extends Network
{
    public SigmoidNetwork(int inputs, int outputs, int hiddenLayers, int neuronsPerLayer, double compFactor, double multFactor)
    {
        /**
         * Construct the input layer, one connection per neuron
         */
        inputLayer = new Layer();
        for(int i = 1; i <= inputs; i++)
        {
            Neuron neuron = new SigmoidNeuron(compFactor, multFactor);
            inputLayer.addNeuron(neuron);
            
            Connection connection = new Connection(generateWeight());
            neuron.addInput(connection);
            inputConnections.add(connection);
                    
            adjustableConnections.add(connection);
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
            ArrayList<Neuron> newNeurons = new ArrayList<>();
            
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
