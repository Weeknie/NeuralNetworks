package testcases;

import main.Renderer;

/**
 *
 * @author Maarten Slenter
 */
public interface TestCase 
{
    public void initialize(Renderer renderer);
    public void update();
}
