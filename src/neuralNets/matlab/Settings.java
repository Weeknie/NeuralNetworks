package neuralNets.matlab;

import java.util.HashMap;

/**
 * Represents settings for the program.
 * Used to modify how the program run from MATLAB.
 * @author Maarten Slenter
 */
public class Settings 
{
    /**
     * The settings
     */
    private HashMap<String, Object> settings = new HashMap<String, Object>();
    
    /**
     * The default settings
     */
    private static final HashMap<String, Object> defaults = new HashMap<String, Object>();
    
    static
    {
        //General
        defaults.put("nonConvergingGenCount", 50);
        defaults.put("runBatch", true);
        
        //Single runs
        defaults.put("renderUpdateTimeBase", 50);
        defaults.put("slowdownFactor", 10);
        defaults.put("testCaseUpdateTimeBase", 1);
        defaults.put("updateCountMax", 50);
        defaults.put("updateCountMin", 1);
        
        //Batch runs
        defaults.put("runToTarget", false);
        defaults.put("solutionTarget", 25);
        defaults.put("threadCount", 4);
    }
    
    /**
     * Sets a property
     * @param key The property to set
     * @param value The new value of the property
     */
    public void set(String key, Object value)
    {
        settings.put(key, value);
    }
    
    /**
     * Returns the value under key as an integer, or the default if no value is set.
     * @param key The key to fetch the value for
     * @return The value under the specified key, as an integer.
     */
    public int getInt(String key)
    {
        Object value;
        if(settings.containsKey(key))
        {
            value = settings.get(key);
        }
        else
        {
            value = defaults.get(key);
        }
        
        if(value instanceof Number)
        {
            Number number = (Number) value;
            return number.intValue();
        }
        
        throw new RuntimeException("The value under key '" + key + "' is not a number");
    }
    
    /**
     * Returns the value under key as a boolean, or the default if no value is set.
     * @param key The key to fetch the value for
     * @return The value under the specified key, as a boolean.
     */
    public boolean getBool(String key)
    {
        Object value;
        if(settings.containsKey(key))
        {
            value = settings.get(key);
        }
        else
        {
            value = defaults.get(key);
        }
        
        if(value instanceof Boolean)
        {
            return (Boolean) value;
        }
        
        throw new RuntimeException("The value under key '" + key + "' is not a boolean");
    }
}
