package neuralNets.matlab;

import neuralNets.main.Main;

/**
 * The API for MATLAB
 * @author Maarten Slenter
 */
public class Matlab 
{
    /**
     * Indicates if tests are running
     */
    private boolean running = false;
    
    /**
     * Starts the tests, using the default settings
     */
    public void start()
    {
        start(new Settings());
    }
    
    /**
     * Same as start()
     */
    public void run()
    {
        start();
    }
    
    /**
     * Start the tests
     * @param settings The settings
     */
    public void start(Settings settings)
    {
        if(isRunning())
        {
            System.out.println("Tests are already running");
        }
        else
        {
            Main.main(false, settings);
            System.out.println("Tests running");
        }
    }
    
    /**
     * Same as start()
     * @param settings The settings 
     */
    public void run(Settings settings)
    {
        start(settings);
    }
    
    /**
     * Polls the current results of all threads
     * @return The results from up to this moment
     */
    public int[] poll()
    {
        return Main.poll();
    }
    
    /**
     * Check whether tests are running
     * @return True if tests are running, false otherwise
     */
    public boolean isRunning()
    {
        return Main.isRunning();
    }
    
    /**
     * Stop the tests
     */
    public void stop()
    {
        if(isRunning())
        {
            Main.stop();
            System.out.println("Tests stopped");
        }
        else
        {
            System.out.println("No tests running");
        }
    }
    
    /**
     * Same as stop()
     */
    public void exit()
    {
        stop();
    }
}
