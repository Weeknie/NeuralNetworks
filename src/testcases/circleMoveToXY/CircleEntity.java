package testcases.circleMoveToXY;

import java.awt.Color;

import components.basic.Network;
import graphics.entities.Circle;
import graphics.entities.Line;
import main.Renderer;

/**
 * Represents a test case circle
 * @author Maarten Slenter
 */
public class CircleEntity 
{
    /**
     * The circle that represents this circle entity
     */
    private Circle circle;
    
    /**
     * The network of this circle entity
     */
    private Network network;
    
    /**
     * The renderer object (stored for tracking the entities movement)
     */
    private Renderer renderer;
    
    /**
     * 
     * @param x The x starting position
     * @param y The y starting position
     * @param radius The radius of the circle
     * @param network The neural network that will guide this circle
     * @param renderer The renderer object
     */
    public CircleEntity(int x, int y, int radius, Network network, Renderer renderer)
    {
        circle = new Circle(x, y, radius);
        this.network = network;
        this.renderer = renderer;
    }
    
    /**
     * 
     * @return The circle that represents this circle entity
     */
    public Circle getCircle()
    {
        return circle;
    }
    
    /**
     * Updates the circle entity.
     * This will first run the current coordinates of the circle through the neural network and then update the position with its outputs.
     * This will also draw a line from the entities old position to its new position, to enable tracking of its movement.
     */
    public void update()
    {
        network.setInputs(circle.getX() - 200 > 0 ? -1 : 1, circle.getY() - 200 > 0 ? -1 : 1);
        network.update();
        double[] outputs = network.getOutputs();
        
        double oldX = circle.getX();
        double oldY = circle.getY();
        
        circle.increaseX(outputs[0]);
        circle.increaseY(outputs[1]);
        
        renderer.drawOnce(new Line((int) circle.getX(), (int) circle.getY(), (int) oldX, (int) oldY, Color.red));
    }
}
