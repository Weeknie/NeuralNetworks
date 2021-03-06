package neuralNets.testcases.circleMoveToXYDirection;


import neuralNets.lib.components.basic.Network;
import neuralNets.lib.graphics.entities.Circle;

/**
 * Represents a test case circle
 * @author Maarten Slenter
 */
public class CircleEntity 
{
    /**
     * The x start coordinate of this circle entity;
     */
    private final int startX;
    
    /**
     * The y start coordinate of this circle entity
     */
    private final int startY;
    
    /**
     * The x goal coordinate
     */
    private final int goalX;
    
    /**
     * The y goal coordinate
     */
    private final int goalY;
    
    /**
     * The circle that represents this circle entity
     */
    private Circle circle;
    
    /**
     * The network of this circle entity
     */
    private Network network;
    
    /**
     * 
     * @param x The x starting position
     * @param y The y starting position
     * @param goalX The x goal coordinate
     * @param goalY The y goal coordinate
     * @param radius The radius of the circle
     * @param network The neural network that will guide this circle
     * @param renderer The renderer object
     */
    public CircleEntity(int x, int y, int goalX, int goalY, int radius, Network network)
    {
        circle = new Circle(x, y, radius);
        this.network = network;
        this.startX = x;
        this.startY = y;
        this.goalX = goalX;
        this.goalY = goalY;
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
     * 
     * @return The neural network that operates this circle
     */
    public Network getNetwork()
    {
        return network;
    }
    
    /**
     * Reset this circle entity to its original position
     */
    public void reset()
    {
        this.circle.setCoordinates(startX, startY);
    }
    
    /**
     * Updates the circle entity.
     * This will first run the current coordinates of the circle through the neural network and then update the position with its outputs.
     * This will also draw a line from the entities old position to its new position, to enable tracking of its movement.
     */
    public void update()
    {
        network.setInputs(circle.getX() - goalX > 0 ? -1 : 1, circle.getY() - goalY > 0 ? -1 : 1);
        network.update();
        double[] outputs = network.getOutputs();
        
        circle.increaseX(outputs[0]);
        circle.increaseY(outputs[1]);
    }
    
    /**
     * Proxy method for getting the x coordinate of this circle entity
     * @return The x coordinate of this circle entity
     */
    public double getX()
    {
        return circle.getX();
    }
    
    /**
     * Proxy method for getting the y coordinate of this circle entity
     * @return The y coordinate of this circle entity
     */
    public double getY()
    {
        return circle.getY();
    }
}
