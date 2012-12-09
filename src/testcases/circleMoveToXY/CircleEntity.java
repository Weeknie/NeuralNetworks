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
    private Circle circle;
    private Network network;
    private Renderer renderer;
    
    public CircleEntity(int x, int y, int radius, Network network, Renderer renderer)
    {
        circle = new Circle(x, y, radius);
        this.network = network;
        this.renderer = renderer;
    }
    
    public Circle getCircle()
    {
        return circle;
    }
    
    /**
     * Updates the circle entity.
     * This will first run the current coordinates of the circle through the neural network
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
