package testcases;

import main.Renderer;

/**
 * Basic interface for a testcase
 * @author Maarten Slenter
 */
public abstract class TestCase 
{
    /**
     * The generation number
     */
    protected int generation = 0;
    
    /**
     * Initializes the test case, adding all drawables to the renderer
     * @param renderer The renderer instance
     */
    abstract public void initialize(Renderer renderer);
    
    /**
     * Updates the test case.
     * This usually includes running the new inputs through the neural networks and updating its entities.
     */
    abstract public void update();
    
    /**
     * Proxy method for the test cases chromosome pool
     * @return The average fitness of the chromosome pool
     */
    abstract public double getAvgFitness();
    
    /**
     * 
     * @return The generation number
     */
    public int getGeneration()
    {
        return generation;
    }
}
