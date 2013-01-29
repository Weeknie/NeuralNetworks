package neuralNets.testcases;

import neuralNets.matlab.Settings;

/**
 * A test case thread, which will run updates on a test case in one thread.
 * This will disable any graphics, this is intended for batch testing only
 * @author Maarten Slenter
 */
public class TestCaseThread extends Thread
{
    /**
     * Indicates whether this thread should keep running
     */
    private boolean run = true;
    
    /**
     * The test case that this thread should run
     */
    private TestCase testCase;
    
    /**
     * The settings
     */
    private Settings settings;
    
    /**
     * The number of correct solutions.
     */
    private int correctSolutions = 0;
    
    /**
     * The number of this thread
     */
    private int number;
    
    /**
     * The total number of generations run
     */
    private int totalGenerations = 0;
    
    /**
     * The amount of non converging simulations.
     * A non converging simulation is one where, after a specified number of generation, a solution still hasn't been found.
     * It will also be indicated by an average fitness that seems to fluctuate around a certain value,
     * but which doesn't increase as fast as it would normally.
     */
    private int nonConverging = 0;
    
    public TestCaseThread(int number, TestCase testCase, Settings settings)
    {
        super(Integer.toString(number));
        this.number = number;
        this.testCase = testCase;
        this.settings = settings;
        testCase.initialize();
    }
    
    @Override
    public void run()
    {
        while(run)
        {
            testCase.update();
            if(testCase.getAvgFitness() == Double.POSITIVE_INFINITY)
            {
                correctSolutions++;
                totalGenerations += testCase.getGeneration();
                testCase.reinitialize();
            }

            if(testCase.getGeneration() == settings.getInt("nonConvergingGenCount"))
            {
                nonConverging++;
                totalGenerations += testCase.getGeneration();
                System.out.println(number + ": Did not converge, #" + nonConverging + " / " + (nonConverging + correctSolutions));
                testCase.reinitialize();
            }
            
            if(settings.getBool("runToTarget") && correctSolutions + nonConverging == settings.getInt("solutionTarget"))
            {
                run = false;
            }
        }
    }
    
    /**
     * Exits this thread.
     * Sets run to false, thus commanding the while loop in run to stop.
     */
    public synchronized void exit()
    {
        run = false;
    }
    
    /**
     * 
     * @return The results of this simulation thread
     */
    public int[] getResults()
    {
        int[] array = {correctSolutions, nonConverging, totalGenerations};
        return array;
    }
    
    /**
     * 
     * @return True if this thread is running, false otherwise
     */
    public synchronized boolean isRunning()
    {
        return run;
    }
}
