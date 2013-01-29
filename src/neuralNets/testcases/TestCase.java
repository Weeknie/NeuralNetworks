package neuralNets.testcases;

import neuralNets.lib.components.basic.NetworkFactory;
import neuralNets.main.Renderer;

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
     * The network factory
     */
    protected NetworkFactory networkFactory;
    
    /**
     * Initializes the test case without visualization
     */
    abstract public void initialize();
    
    /**
     * Initializes this test case, adding all drawables to the renderer
     * @param renderer The renderer instance
     */
    public void initialize(Renderer renderer)
    {}
    
    /**
     * Reinitializes the test case without visualization
     */
    abstract public void reinitialize();
    
    /**
     * Reinitializes the test case, with visualization (see initialize(Renderer))
     * @param renderer The renderer instance
     */
    public void reinitialize(Renderer renderer)
    {}
    
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
    
    /**
     * 
     * @return The network factory
     */
    public NetworkFactory getNetworkFactory()
    {
        return networkFactory;
    }
    
    @Override
    public abstract TestCase clone();
}
