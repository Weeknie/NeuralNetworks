package components.extended.networks;

import java.util.ArrayList;

import components.basic.Connection;
import components.basic.Layer;
import components.basic.Network;
import components.basic.Neuron;
import components.extended.neurons.DefaultNeuron;
import components.extended.neurons.SigmoidNeuron;

/**
 * Represents 
 * @author Maarten Slenter
 */
public class SigmoidNetwork extends Network
{
    public SigmoidNetwork(int inputs, int outputs, int hiddenLayers, int neuronsPerLayer, double compensationFactor)
    {
        inputLayer = new Layer();
        for(int i = 1; i <= inputs; i++)
        {
            Neuron neuron = new SigmoidNeuron(compensationFactor);
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
                Neuron neuron = new SigmoidNeuron(compensationFactor);
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
            Neuron neuron = new SigmoidNeuron(compensationFactor);
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
