package neuralNets.main.threads;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import neuralNets.main.Renderer;
import neuralNets.matlab.Settings;
import neuralNets.testcases.TestCase;

/**
 * The thread that will command all test case threads
 * @author Maarten Slenter
 */
public class SingleThread extends Thread
{
    /**
     * The test case to run
     */
    private TestCase testCase;
    
    /**
     * The gui JFrame instance
     */
    private JFrame frame;
    
    /**
     * The settings
     */
    private Settings settings;
    
    /**
     * The count variables that helps determine when exactly the test case should be updated.
     * It allows for dynamically changing the update rate, based on e.g. the average fitness.
     */
    int count = 0;
    
    /**
     * The number of last checked generation.
     * Used in checking whether a "found solution" is actually correct
     */
    int lastChecked = 0;
    
    /**
     * The number of correct solutions.
     */
    int correctSolutions = 0;
    
    /**
     * The amount of non converging simulations.
     * A non converging simulation is one where, after 200 generation, a solution still hasn't been found.
     * It will also be indicated by an average fitness that seems to fluctuate around a certain value,
     * but which doesn't increase as fast as it would normally.
     */
    int nonConverging = 0;
    
    /**
     * The current generation according to this class.
     * When this isn't equal to testCase.getGeneration(), it will mean another generation has passed
     */
    int currentGeneration = 0;
    
    /**
     * Indicates whether the tests are running
     */
    private boolean running = true;
    
    /**
     * The timer instance for updating the test case and the renderer
     */
    Timer timer = new Timer();
    
    public SingleThread(TestCase testCase, Settings settings)
    {
        this.testCase = testCase;
        this.settings = settings;
    }
    
    /**
     * Run the test case
     */
    @Override
    public void run()
    {
        final Renderer renderer = new Renderer();
        testCase.initialize(renderer);
        
        frame = new JFrame();
        frame.setSize(settings.getInt("windowWidth"), settings.getInt("windowHeight"));
        frame.getContentPane().add(renderer);
        frame.setVisible(true);
        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run()
            {
                count++;
                
                if(count == (int) Math.min(settings.getInt("updateCountMax"), Math.max((testCase.getAvgFitness()), settings.getInt("updateCountMin"))))
                {
                    testCase.update();
                    if(currentGeneration != testCase.getGeneration())
                    {
                        System.out.println("Generation #" + testCase.getGeneration() + " : " + testCase.getAvgFitness());
                        currentGeneration = testCase.getGeneration();
                    }
                    
                    if(testCase.getAvgFitness() == Double.POSITIVE_INFINITY)
                    {
                        correctSolutions++;
                        System.out.println("Solution found, #" + correctSolutions + " / " + (nonConverging + correctSolutions));
                        testCase.reinitialize(renderer);
                        currentGeneration = 0;
                    }

                    if(testCase.getGeneration() == settings.getInt("nonConvergingGenCount"))
                    {
                        nonConverging++;
                        System.out.println("Did not converge, #" + nonConverging + " / " + (nonConverging + correctSolutions));
                        testCase.reinitialize(renderer);
                        currentGeneration = 0;
                    }

                    lastChecked = testCase.getGeneration();
                    count = 0;
                }
            }
        }, 0, settings.getInt("testCaseUpdateTimeBase") * settings.getInt("slowdownFactor"));
        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run()
            {
                renderer.repaint();
            }
        }, 0, settings.getInt("renderUpdateTimeBase") * settings.getInt("slowdownFactor"));
    }
    
    /**
     * Checks whether the tests are still running
     * @return True if the tests are still running, false otherwise
     */
    public boolean isRunning()
    {
        return running;
    }
    
    /**
     * Exits the program
     */
    public void exit()
    {
        running = false;
        frame.dispose();
        timer.cancel();
    }
}
