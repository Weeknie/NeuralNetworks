package testcases;

import main.Renderer;

/**
 * Basic interface for a testcase
 * @author Maarten Slenter
 */
public interface TestCase 
{
    /**
     * Initializes the test case, adding all drawables to the renderer
     * @param renderer The renderer instance
     */
    public void initialize(Renderer renderer);
    
    /**
     * Updates the test case.
     * This usually includes running the new inputs through the neural networks and updating its entities.
     */
    public void update();
}
