package neuralNets.main;

import neuralNets.main.threads.BatchThread;
import neuralNets.main.threads.SingleThread;
import neuralNets.matlab.Settings;
import neuralNets.testcases.TestCase;
import neuralNets.testcases.circleMoveToXYDirection.CircleMoveToXYDirection;

/**
 * 
 * @author s109367
 */
public class Main
{
    /**
     * The test case to run
     */
    static TestCase testCase;
    
    /**
     * The SingleThread instance, if batch testing is used
     */
    private static SingleThread singleThread;
    
    /**
     * The BatchThread instance, if batch testing is used
     */
    private static BatchThread batchThread;
    
    /**
     * The settings for this run
     */
    private static Settings settings;
    
    /**
     * Initializes the test case and initiates single or batch testing.
     * @param exitOnClose Indicates whether the java process should be closed when closing the rendering window
     * @param settings The settings to use
     */
    public static void main(boolean exitOnClose, Settings settings)
    {
        testCase = new CircleMoveToXYDirection(settings);
        Main.settings = settings;
        if(settings.getBool("runBatch"))
        {
            batchThread = new BatchThread(testCase, settings);
            batchThread.start();
        }
        else
        {
            singleThread = new SingleThread(testCase, settings);
            singleThread.start();
        }
    }
    
    /**
     * Initializes the test case and initiates single or batch testing.
     * @param args 
     */
    public static void main(String[] args)
    {
        main(true, new Settings());
    }
    
    /**
     * Polls the current results of all threads
     * @return The results from up to this moment
     */
    public static double[] poll()
    {
        if(settings.getBool("runBatch"))
        {
            return batchThread.poll();
        }
        
        throw new RuntimeException("Code is not running in batch mode.");
    }
    
    /**
     * Checks if any tests are running
     * @return True if tests are running, false otherwise
     */
    public static boolean isRunning()
    {
        if(settings == null)
        {
            return false;
        }
        
        if(settings.getBool("runBatch"))
        {
            if(batchThread == null)
            {
                return false;
            }
            
            return batchThread.isRunning();
        }
        else
        {
            if(singleThread == null)
            {
                return false;
            }
            
            return singleThread.isRunning();
        }
    }
    
    /**
     * Stops the program
     */
    public static void stop()
    {
        if(settings.getBool("runBatch"))
        {
            batchThread.exit();
        }
        else
        {
            singleThread.exit();
        }
    }
}
