package neuralNets.matlab;

import neuralNets.main.Main;

/**
 * The API for MATLAB
 * @author Maarten Slenter
 */
public class Matlab 
{    
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
            if(settings.getBool("verbose"))
            {
                System.out.println("Tests are already running");
            }
        }
        else
        {
            if(settings.getBool("block") && !(settings.getBool("runBatch") && settings.getBool("runToTarget")))
            {
                System.out.println("Can not run in block mode, not running batch and/or not running to target");
            }
            
            Main.main(false, settings);
            if(settings.getBool("verbose"))
            {
                System.out.println("Tests running");
            }
            
            if(settings.getBool("block"))
            {
                while(Main.isRunning())
                {}
                
                if(settings.getBool("verbose"))
                {
                    System.out.println("Simulations finished");
                }
            }
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
    public double[] poll()
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
