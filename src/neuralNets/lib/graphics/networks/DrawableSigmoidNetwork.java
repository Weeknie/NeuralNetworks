package neuralNets.lib.graphics.networks;

import java.awt.Color;
import java.util.ArrayList;

import neuralNets.lib.components.basic.Connection;
import neuralNets.lib.components.basic.Layer;
import neuralNets.lib.components.basic.Network;
import neuralNets.lib.components.basic.Neuron;
import neuralNets.lib.graphics.components.DrawableConnection;
import neuralNets.lib.graphics.components.DrawableNeuron;
import neuralNets.lib.graphics.entities.Circle;
import neuralNets.lib.graphics.entities.Line;
import neuralNets.lib.graphics.entities.Rectangle;
import neuralNets.main.Renderer;

/**
 *
 * @author Maarten Slenter
 */
public class DrawableSigmoidNetwork 
{
    public static void drawNetwork(Network network, Renderer renderer, int xOffset, int yOffset, double compensationFactor, Color backgroundColor)
    {
        ArrayList<Layer> layers = network.getLayers();
        int width = 0;
        
        /**
         * Calculate the maximum width of the layers.
         * This will later be used to center all layers that are smaller than the widest one
         */
        for(Layer layer : layers)
        {
            width = Math.max(width, (layer.getSize() - 1) * 50 + 40);
        }
        
        int height = (layers.size() + 1) * 50;
        
        renderer.addDrawable(new Rectangle(xOffset, yOffset, width, height, backgroundColor, false));
        
        /**
         * Draw the input layer + input connections
         */
        ArrayList<Neuron> neurons = layers.get(0).getNeurons();
        for(int i = 0; i <= neurons.size() - 1; i++)
        {
            int x = i * (width / neurons.size()) + (width / (2 * neurons.size()));
            renderer.addDrawable(new DrawableNeuron(neurons.get(i), new Circle(x + xOffset, 50 + yOffset, 10), compensationFactor));
            renderer.addDrawable(new DrawableConnection(neurons.get(i).getInputs().get(0), new Line(x + xOffset, 20 + yOffset, x + xOffset, 40 + yOffset), compensationFactor));
        }
        
        /**
         * Draw the hidden layers + connections to previous input layers + the output layer
         */
        for(int i = 1; i <= layers.size() - 1; i++)
        {
            neurons = layers.get(i).getNeurons();
            for(int j = 0; j <= neurons.size() - 1; j++)
            {
                int x = j * (width / neurons.size()) + (width / (2 * neurons.size()));
                renderer.addDrawable(new DrawableNeuron(neurons.get(j), new Circle(x + xOffset, i * 50 + 50 + yOffset, 10), compensationFactor));
                
                ArrayList<Connection> connections = neurons.get(j).getInputs();
                for(int k = 0; k <= connections.size() - 1; k++)
                {
                    int otherX = k * (width / layers.get(i - 1).getSize()) + (width / (2 * layers.get(i - 1).getSize()));
                    renderer.addDrawable(new DrawableConnection(connections.get(k), new Line(otherX + xOffset, (i - 1) * 50 + 60 + yOffset, x + xOffset, i * 50 + 40 + yOffset), compensationFactor));
                }
            }
        }
        
        /**
         * Draw the output connections
         */
        neurons = layers.get(layers.size() - 1).getNeurons();
        for(int i = 0; i <= neurons.size() - 1; i++)
        {
            int x = i * (width / neurons.size()) + (width / (2 * neurons.size()));
            renderer.addDrawable(new DrawableConnection(neurons.get(i).getOutputs().get(0), new Line(x + xOffset, (layers.size() - 1) * 50 + 60 + yOffset, x + xOffset, (layers.size() - 1) * 50 + 80 + yOffset), compensationFactor));
        }
    }
}
