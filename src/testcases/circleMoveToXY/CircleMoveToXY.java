package testcases.circleMoveToXY;

import java.awt.Color;
import java.util.ArrayList;

import components.extended.networks.SigmoidNetwork;
import graphics.entities.Line;
import main.Renderer;
import testcases.TestCase;

/**
 * Testcase CircleMoveToXY
 * Goal: The circles must move to a certain X Y coordinate
 * Network: Sigmoid network
 * Learning: Default learning
 * @author Maarten Slenter
 */
public class CircleMoveToXY implements TestCase
{
    /**
     * The circle entity instances
     */
    ArrayList<CircleEntity> circleEntities = new ArrayList<>();
    
    /**
     * The x coordinate the circles should move to
     */
    public static int goalX = 200;
    
    /**
     * The y coordinate the circles should move to
     */
    public static int goalY = 200;
    
    /**
     * The line that represents the x coordinate goal
     */
    private Line goalLineX = new Line(goalX, 0, goalX, 1500, Color.green);
    
    /**
     * The line that represents the y coordinate goal
     */
    private Line goalLineY = new Line(0, goalY, 1500, goalY, Color.green);

    /**
     * Initializes the testcase, instantiating all cricle entities and 
     * @param renderer The renderer instance
     */
    @Override
    public void initialize(Renderer renderer)
    {
        renderer.addDrawable(goalLineX);
        renderer.addDrawable(goalLineY);
        
        for(int i = 0; i <= 100; i++)
        {
            CircleEntity circleEntity = new CircleEntity(400, 400, 5, new SigmoidNetwork(2, 2, 1, 6, 1, 10), renderer);
            circleEntities.add(circleEntity);
            renderer.addDrawable(circleEntity.getCircle());
        }
    }

    /**
     * Updates the testcase.
     * Loops through all circle entities and updates them
     */
    @Override
    public void update()
    {
        for(CircleEntity circleEntity : circleEntities)
        {
            circleEntity.update();
        }
    }
}
