package neuralNets.main.threads;

import neuralNets.matlab.Settings;
import neuralNets.testcases.TestCase;
import neuralNets.testcases.TestCaseThread;

/**
 * The thread that will command all test case threads
 * @author Maarten Slenter
 */
public class BatchThread extends Thread
{
    /**
     * The test case to run
     */
    private TestCase testCase;
    
    /**
     * The TestCaseThread instances
     */
    private TestCaseThread[] threads;
    
    /**
     * The settings
     */
    private Settings settings;
    
    /**
     * The results of the tests
     */
    private int[] results = {0, 0, 0};
    
    /**
     * Indicates whether this thread should continue running
     */
    private boolean run = true;
    
    public BatchThread(TestCase testCase, Settings settings)
    {
        this.testCase = testCase;
        this.settings = settings;
    }
    
    /**
     * Run the tests
     */
    @Override
    public void run()
    {
        int numberOfThreads = settings.getInt("threadCount");
        
        threads = new TestCaseThread[numberOfThreads];
        for(int i = 0; i <= numberOfThreads - 1; i++)
        {
            threads[i] = new TestCaseThread(i + 1, testCase.clone(), settings);
            threads[i].start();
        }
        
        if(settings.getBool("runToTarget"))
        {
            boolean stillRunning;

            while(run)
            {
                stillRunning = false;
                for(int i = 0; i <= numberOfThreads - 1; i++)
                {
                    if(threads[i].isRunning())
                    {
                        stillRunning = true;
                        break;
                    }
                }

                if(!stillRunning)
                {
                    for(int i = 0; i <= numberOfThreads - 1; i++)
                    {
                        int[] result = threads[i].getResults();
                        results[0] += result[0];
                        results[1] += result[1];
                        results[2] += result[2];
                    }
                    
                    System.out.println("Simulation results: (" + results[0] + " : " + results[1] + " : " + (double) Math.round((double) results[2]/ (double) (results[0] + results[1]) * 100) / 100 + ")");
                    run = false;
                }
            }
        }
    }
    
    /**
     * Polls the current results of all threads
     * @return The results from up to this moment
     */
    public int[] poll()
    {
        int[] returnResults = {0, 0, 0};
        
        for(int i = 0; i <= settings.getInt("threadCount") - 1; i++)
        {
            int[] result = threads[i].getResults();
            returnResults[0] += result[0];
            returnResults[1] += result[1];
            returnResults[2] += result[2];
        }
        
        return returnResults;
    }
    
    /**
     * Checks whether the tests are still running
     * @return True if the tests are still running, false otherwise
     */
    public boolean isRunning()
    {
        return run;
    }
    
    /**
     * Exits the tests
     */
    public void exit()
    {
        run = false;
        for(TestCaseThread thread : threads)
        {
            thread.exit();
        }
    }
}
