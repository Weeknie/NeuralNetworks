package neuralNets.lib.graphics.networks;

import java.util.ArrayList;

import neuralNets.lib.components.basic.Connection;
import neuralNets.lib.components.basic.Layer;
import neuralNets.lib.components.basic.Network;
import neuralNets.lib.components.basic.Neuron;
import neuralNets.lib.graphics.components.DrawableConnection;
import neuralNets.lib.graphics.components.DrawableNeuron;
import neuralNets.lib.graphics.entities.Circle;
import neuralNets.lib.graphics.entities.Line;
import neuralNets.main.Renderer;

/**
 *
 * @author Maarten Slenter
 */
public class DrawableDefaultNetwork
{    
    public static void drawNetwork(Network network, Renderer renderer)
    {
        ArrayList<Layer> layers = network.getLayers();
        int maxWidth = 0;
        
        /**
         * Calculate the maximum width of the layers.
         * This will later be used to center all layers that are smaller than the widest one
         */
        for(Layer layer : layers)
        {
            maxWidth = Math.max(maxWidth, layer.getSize() * 50);
        }
        
        /**
         * Draw the input layer + input connections
         */
        ArrayList<Neuron> neurons = layers.get(0).getNeurons();
        for(int i = 0; i <= neurons.size() - 1; i++)
        {
            int x = i * (maxWidth / neurons.size()) + (maxWidth / (2 * neurons.size())) + 25;
            renderer.addDrawable(new DrawableNeuron(neurons.get(i), new Circle(x, 50, 10)));
            renderer.addDrawable(new DrawableConnection(neurons.get(i).getInputs().get(0), new Line(x, 20, x, 40)));
        }
        
        /**
         * Draw the hidden layers + connections to previous input layers + the output layer
         */
        for(int i = 1; i <= layers.size() - 1; i++)
        {
            neurons = layers.get(i).getNeurons();
            for(int j = 0; j <= neurons.size() - 1; j++)
            {
                int x = j * (maxWidth / neurons.size()) + (maxWidth / (2 * neurons.size())) + 25;
                renderer.addDrawable(new DrawableNeuron(neurons.get(j), new Circle(x, i * 50 + 50, 10)));
                
                ArrayList<Connection> connections = neurons.get(j).getInputs();
                for(int k = 0; k <= connections.size() - 1; k++)
                {
                    int otherX = k * (maxWidth / layers.get(i - 1).getSize()) + (maxWidth / (2 * layers.get(i - 1).getSize())) + 25;
                    renderer.addDrawable(new DrawableConnection(connections.get(k), new Line(otherX, (i - 1) * 50 + 60, x, i * 50 + 40)));
                }
            }
        }
        
        /**
         * Draw the output connections
         */
        neurons = layers.get(layers.size() - 1).getNeurons();
        for(int i = 0; i <= neurons.size() - 1; i++)
        {
            int x = i * (maxWidth / neurons.size()) + (maxWidth / (2 * neurons.size())) + 25;
            renderer.addDrawable(new DrawableConnection(neurons.get(i).getOutputs().get(0), new Line(x, (layers.size() - 1) * 50 + 60 , x, (layers.size() - 1) * 50 + 80)));
        }
    }
}
